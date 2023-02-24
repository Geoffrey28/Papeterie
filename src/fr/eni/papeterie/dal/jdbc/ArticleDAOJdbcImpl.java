package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DALException;

public class ArticleDAOJdbcImpl {
	
	private final static String urldb = Settings.getProperty("url");
	private final static String user = Settings.getProperty("user");
	private final static String pwd = Settings.getProperty("password");
	
	private final static String TYPE_RAM = "Ramette";
	private final static String TYPE_STL = "Stylo";
	
	private final static String INSERT = "insert into articles(reference,marque,designation,prixUnitaire,qteStock,grammage,couleur,type) values(?,?,?,?,?,?,?,?)";
	private final static String SELECTBYID = "select idArticle,reference,marque,designation,prixUnitaire,qteStock,grammage,couleur,type from articles where idArticle = ?";
	private static final String SELECTALL = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type from articles";
	private final static String UPDATE = "update articles set reference=?, marque=?, designation=?, prixUnitaire=?, qteStock=?,grammage=?,couleur=? where idArticle=";
	private final static String DELETE = "delete from articles where idArticle=?";
	
	
	public Connection getConnection() throws SQLException {
		Connection connect = null;
		try {
			Class.forName(Settings.getProperty("driverJDBC"));
			
			if (connect == null || connect.isClosed()) {
				connect = DriverManager.getConnection(urldb, user, pwd);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connect;
	}
	
	public void insert(Article article) throws DALException {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			setParameter(stmt, article);
			if (article instanceof Ramette) {
				stmt.setString(8, TYPE_RAM);
			}
			if (article instanceof Stylo) {
				stmt.setString(8, TYPE_STL);
			}
			int nbRows = stmt.executeUpdate();
			if (nbRows == 1) {
				try (ResultSet rs = stmt.getGeneratedKeys();) {
					if (rs.next()) {
						article.setIdArticle(rs.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new DALException("Insert failed - " + article, e);
		}
	}
	
	
	public Article selectById(int id) throws DALException {
		Article art = null;
		try {
			PreparedStatement stmt = getConnection().prepareStatement(SELECTBYID);
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery();) {
				if(rs.next()) {
					art = forSelect(rs);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return art;
	}
	
	public List<Article> selectAll() {
		List<Article> List = new ArrayList<Article>();
		Article art = null;
		try {
			Statement stmt = getConnection().createStatement();
			try (ResultSet rs = stmt.executeQuery(SELECTALL);) {
				while(rs.next()) {
					art = forSelect(rs);
					List.add(art);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return List;
	}
	
	public void update(Article article) throws DALException {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(UPDATE + article.getIdArticle());
			setParameter(stmt, article);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Insert failed - " + article, e);
		}
	}
	
	public void delete(int idArticle) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(DELETE);
			stmt.setInt(1, idArticle);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Article forSelect(ResultSet rs) throws SQLException {
		Article art = null;
		int idArticle = rs.getInt("idArticle");
		String reference = rs.getString("reference");
		String marque = rs.getString("marque");
		String designation = rs.getString("designation");
		float prixUnitaire = rs.getFloat("prixUnitaire");
		int qteStock = rs.getInt("qteStock");
		
		if (TYPE_RAM.equalsIgnoreCase(rs.getString("type").trim())) {
			int grammage = rs.getInt("grammage");
			art = new Ramette(idArticle, marque, reference, designation, prixUnitaire, qteStock, grammage);
		}
		if (TYPE_STL.equalsIgnoreCase(rs.getString("type").trim())) {
			String couleur = rs.getString("couleur");
			art = new Stylo(idArticle, marque, reference, designation, prixUnitaire, qteStock, couleur);
		}
		return art;
	}
	
	private void setParameter(PreparedStatement stmt, Article article) throws SQLException {
		stmt.setString(1, article.getReference());
		stmt.setString(2, article.getMarque());
		stmt.setString(3, article.getDesignation());
		stmt.setFloat(4, article.getPrixUnitaire());
		stmt.setInt(5, article.getQteStock());
		if (article instanceof Ramette) {
			Ramette rmt = (Ramette) article;
			stmt.setInt(6, rmt.getGrammage());
			stmt.setNull(7, Types.VARCHAR);
		}
		if (article instanceof Stylo) {
			Stylo stl = (Stylo) article;
			stmt.setNull(6, Types.INTEGER);
			stmt.setString(7, stl.getCouleur());
		}
	}
}

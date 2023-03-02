package fr.eni.papeterie.dal.jdbc;

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
import fr.eni.papeterie.dal.ArticleDAO;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private final static String TYPE_RAM = "Ramette";
	private final static String TYPE_STL = "Stylo";
	
	private final static String INSERT = "insert into articles(reference,marque,designation,prixUnitaire,qteStock,grammage,couleur,type) values(?,?,?,?,?,?,?,?)";
	private final static String SELECTBYID = "select idArticle,reference,marque,designation,prixUnitaire,qteStock,grammage,couleur,type from articles where idArticle = ?";
	private static final String SELECTALL = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type from articles";
	private final static String UPDATE = "update articles set reference=?, marque=?, designation=?, prixUnitaire=?, qteStock=?,grammage=?,couleur=? where idArticle=";
	private final static String DELETE = "delete from articles where idArticle=?";
	private final static String SELECTBYMARQUE = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type"
			+ " from articles where marque = ?";
	private final static String SELECTBYMOTCLE = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type"
			+ "from articles where marque like ? or designation like ?";
	
	
	@Override
	public void insert(Article article) throws DALException {
		try {
			PreparedStatement stmt = JdbcTools.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			setParameter(stmt, article);
			if (article instanceof Ramette) {
				stmt.setString(8, TYPE_RAM);
			}
			if (article instanceof Stylo) {
				stmt.setString(8, TYPE_STL);
			}
			int nbRows = stmt.executeUpdate();
			if (nbRows == 1) {
				try (ResultSet rs = stmt.getGeneratedKeys()) {
					if(rs.next()) {
						article.setIdArticle(rs.getInt(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			throw new DALException("Insert failed - " + article, e);
		}
		
	}
	
	@Override
	public Article selectById(int id) throws DALException {
		Article art = null;
		try {
			PreparedStatement stmt = JdbcTools.getConnection().prepareStatement(SELECTBYID);
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					art = forSelect(rs);
				}	
			}
		} catch (SQLException e) {
			throw new DALException("Select failed from id - " + id, e);
		}
		return art;
	}
	
	@Override
	public List<Article> selectAll() throws DALException {
		List<Article> List = new ArrayList<Article>();
		Article art = null;
		try {
			Statement stmt = JdbcTools.getConnection().createStatement();
			try (ResultSet rs = stmt.executeQuery(SELECTALL);) {
				while(rs.next()) {
					art = forSelect(rs);
					List.add(art);
				}
			}
		} catch (SQLException e) {
			throw new DALException("Select all failed - ", e);
		}
		return List;
	}
	
	@Override
	public void update(Article article) throws DALException {
		try {
			PreparedStatement stmt = JdbcTools.getConnection().prepareStatement(UPDATE + article.getIdArticle());
			setParameter(stmt, article);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Update failed - " + article, e);
		}
	}
	
	@Override
	public void delete(int idArticle) throws DALException {
		try {
			PreparedStatement stmt = JdbcTools.getConnection().prepareStatement(DELETE);
			stmt.setInt(1, idArticle);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete failed from id -" + idArticle, e);
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

	@Override
	public List<Article> selectByMarque(String marque) throws DALException {
		List<Article> List = new ArrayList<Article>();
		Article art = null;
		try {
			PreparedStatement stmt = JdbcTools.getConnection().prepareStatement(SELECTBYMARQUE);
			stmt.setString(1, marque);
			try (ResultSet rs = stmt.executeQuery();) {
				while(rs.next()) {
					art = forSelect(rs);
					List.add(art);
				}
			}
		} catch (SQLException e) {
			throw new DALException("Select by marque failed - ", e);
		}
		return List;
	}

	@Override
	public List<Article> selectByMotCle(String motCle) throws DALException {
		List<Article> List = new ArrayList<Article>();
		Article art = null;
		try {
			PreparedStatement stmt = JdbcTools.getConnection().prepareStatement(SELECTBYMOTCLE);
			stmt.setString(1, motCle);
			stmt.setString(2, motCle);
			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					art = forSelect(rs);
					List.add(art);
				}
			}
		} catch (SQLException e) {
			throw new DALException("Select by mot clé failed - ", e);
		}
		return List;
	}
}

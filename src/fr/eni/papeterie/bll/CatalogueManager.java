package fr.eni.papeterie.bll;

import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAOFactory;

public class CatalogueManager {

	private static CatalogueManager instance;
	
	private ArticleDAO daoArticles;
	
	private CatalogueManager() {
		daoArticles = DAOFactory.getArticleDAO();
	}
	
	public static synchronized CatalogueManager getInstance() throws BLLException {
		if (instance == null) {
			instance = new CatalogueManager();
		}
		return instance;
	}
	
	public List<Article> getCatalogue() throws BLLException {
		try {
			return daoArticles.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de chargement du catalogue", e);
		}
	}
	
	public void addArticle(Article article) throws BLLException {
		if (article.getIdArticle() != null) {
			throw new BLLException("Article déjà existant");
		}
		try {
			validerArticle(article);
			daoArticles.insert(article);
		} catch (DALException e) {
			throw new BLLException("Echec de l'ajout - article: " + article, e);
		}
	}
	
	public void updateArticle(Article article) throws BLLException {
		try {
			validerArticle(article);
			daoArticles.update(article);
		} catch (DALException e) {
			throw new BLLException("Echec de la mise a jour - article: " + article, e);
		}
	}
	
	public Article getArticle(int idArticle) throws BLLException {
		try {
			return daoArticles.selectById(idArticle);
		} catch (DALException e) {
			throw new BLLException("Echec du chargement de l'article avec ID: " + idArticle, e);
		}
	}
	
	public void removeArticles(int idArticle) throws BLLException {
		try {
			daoArticles.delete(idArticle);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression de l'article avec ID: " + idArticle, e);
		}
	}
	
	public void validerArticle(Article art) throws BLLException {
		boolean validate = true;
		StringBuffer sb = new StringBuffer();
		
		if (art == null) {
			throw new BLLException("Article null");
		}
		
		if (art.getReference() == null || art.getReference().trim().length() == 0) {
			sb.append("La reference de l'article est obligatoire.\n");
			validate = false;
		}
		if (art.getMarque() == null || art.getMarque().trim().length() == 0) {
			sb.append("La marque de l'article est obligatoire.\n");
			validate = false;
		}
		if (art.getDesignation() == null || art.getDesignation().trim().length() == 0) {
			sb.append("La designation de l'article est obligatoire.\n");
			validate = false;
		}
		
		if (art instanceof Ramette && ((Ramette) art).getGrammage() <= 0) {
			sb.append("Le grammage doit avoir une valeur positive.\n");
			validate = false;
		}
		if (art instanceof Stylo) {
			Stylo stl = (Stylo) art; 
			if (stl.getCouleur() == null || stl.getCouleur().trim().length() == 0) {
				sb.append("La couleur du stylo est obligatoire.\n");
				validate = false;
			}
		}
		
		if (!validate) {
			throw new BLLException(sb.toString());
		}
	}
	
}
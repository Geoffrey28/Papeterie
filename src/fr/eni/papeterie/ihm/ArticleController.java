package fr.eni.papeterie.ihm;

import java.util.List;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;

public class ArticleController {
	
	private EcranArticle ecrArticle;
	
	private int indexCatalogue;
	private CatalogueManager manager;
	private List<Article> catalogue;
	private static ArticleController instance;
	
	/*
	 * @Params ArticleController
	 */
	private ArticleController() {
		try {
			manager = CatalogueManager.getInstance();
			catalogue = manager.getCatalogue();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ArticleController get(){
		if(instance == null){
			instance = new ArticleController();
		}
		return instance;
	}
	
	public void startApp(){
		ecrArticle = new EcranArticle();
		
		afficherPremierArticle();
		ecrArticle.setVisible(true);
		
		EcranCatalogue ecrCatalogue = new EcranCatalogue();
		ecrCatalogue.setVisible(true);
	}
	
	public void afficherPremierArticle(){
		if(catalogue.size()>0){
			indexCatalogue = 0;
			ecrArticle.afficherArticle(catalogue.get(indexCatalogue));
		}else{
			indexCatalogue = -1;
			ecrArticle.afficherNouveau();
		}
	}

}

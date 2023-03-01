package fr.eni.papeterie.ihm;

import javax.swing.SwingUtilities;

import fr.eni.papeterie.ihm.controller.ArticleController;
import fr.eni.papeterie.ihm.controller.CatalogueController;

public class PapeterieApp {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ArticleController article = new ArticleController();
				article.startApp();

				CatalogueController catalogue = new CatalogueController();
				catalogue.showEcranCatalogue();
				
				article.ajouterCatalogueObserver(catalogue);
			}	
		});
	}
}

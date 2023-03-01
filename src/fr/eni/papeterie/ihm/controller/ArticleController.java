package fr.eni.papeterie.ihm.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.ihm.observer.ICatalogueObserver;
import fr.eni.papeterie.ihm.view.EcranArticle;

public class ArticleController {
	
	private EcranArticle ecrArticle;
	
	private int indexCatalogue;
	private CatalogueManager manager;
	private List<Article> catalogue;
	private static ArticleController instance;
	
	private List<ICatalogueObserver> lstCatalogueObservers;
	
	public void ajouterCatalogueObserver(ICatalogueObserver observer) {
		if (lstCatalogueObservers == null) {
			lstCatalogueObservers = new ArrayList<>();
		}
		lstCatalogueObservers.add(observer);
	}
	
	/*
	 * @Params ArticleController
	 */
	public ArticleController() {

	}

	private void initData() {
		// Initialisation du catalogue en mémoire
		try {
			manager = CatalogueManager.getInstance();
			catalogue = manager.getCatalogue();
			indexCatalogue = 0;
		} catch (BLLException e) {
			ecrArticle.infoErreur(e.getMessage());
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
		panelButton();
		initData();
		afficherPremierArticle();
		ecrArticle.setVisible(true);
	}
	
	public void afficherPremierArticle(){
		if(catalogue.size() > 0){
			indexCatalogue = 0;
			ecrArticle.afficherArticle(catalogue.get(indexCatalogue));
		} else {
			indexCatalogue = -1;
			ecrArticle.afficherNouveau();
		}
	}

	public void precedent() {
		if (indexCatalogue > 0) {
			indexCatalogue--;
			ecrArticle.afficherArticle(catalogue.get(indexCatalogue));
		}

	}

	public void suivant() {
		if (indexCatalogue < catalogue.size() - 1) {
			indexCatalogue++;
			ecrArticle.afficherArticle(catalogue.get(indexCatalogue));
		}

	}

	public void nouveau() {
		indexCatalogue = catalogue.size();
		ecrArticle.afficherNouveau();
	}

	public void enregistrer() {
		Article articleAffiche = ecrArticle.getArticle();

		try {
			if (articleAffiche.getIdArticle() == null) {
				manager.addArticle(articleAffiche);
				System.out.println("article: " + articleAffiche);
				catalogue.add(articleAffiche);
				ecrArticle.afficherArticle(articleAffiche);
				ecrArticle.information("Nouvel article sauvegardé.");
			} else {
				manager.updateArticle(articleAffiche);
				catalogue.set(indexCatalogue, articleAffiche);
				ecrArticle.information("Mise à jour effectuée.");
			}
		} catch (BLLException e) {
			ecrArticle.infoErreur(e.getMessage());
		}

	}

	public void supprimer() {

		try {
			Article art = catalogue.get(indexCatalogue);
			manager.removeArticles(art);
			catalogue.remove(indexCatalogue);
			ecrArticle.information("Suppression de l'article réalisée.");
			if (indexCatalogue < catalogue.size()) {
				// Afficher l'article suivant
				ecrArticle.afficherArticle(catalogue.get(indexCatalogue));
			} else if (indexCatalogue > 0) {
				indexCatalogue--;
				ecrArticle.afficherArticle(catalogue.get(indexCatalogue));
			} else {
				ecrArticle.afficherNouveau();
			}
		} catch (BLLException e) {
			ecrArticle.infoErreur(e.getMessage());
		}
	}

	public List<Article> getCatalogue() {
		return catalogue;
	}

	public void panelButton() {
		ecrArticle.getPreviousBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				precedent();
			}

		});

		ecrArticle.getNewBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nouveau();
			}

		});

		ecrArticle.getSaveBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				enregistrer();
			}

		});

		ecrArticle.getDeleteBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				supprimer();
			}

		});

		ecrArticle.getNextBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				suivant();
			}

		});
	}
}

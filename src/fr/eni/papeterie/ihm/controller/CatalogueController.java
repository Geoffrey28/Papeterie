package fr.eni.papeterie.ihm.controller;

import javax.swing.JOptionPane;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.ihm.observer.ICatalogueObserver;
import fr.eni.papeterie.ihm.view.EcranCatalogue;
import fr.eni.papeterie.ihm.view.TableCatalogueModel;

public class CatalogueController implements ICatalogueObserver {
	
	private EcranCatalogue ecranC;
	private CatalogueManager manager;
	
	public CatalogueController() {
		ecranC = new EcranCatalogue();
	}
	
	public void showEcranCatalogue() {
		try {
			manager = CatalogueManager.getInstance();
			ecranC.initComposants(manager.getCatalogue());
		} catch (BLLException e) {
			ecranC.popup(e.getMessage(), "Erreur technique", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void miseAJourDesDonnees() {
		try {
			ecranC.getTblCatalogue().setModel(new TableCatalogueModel(manager.getCatalogue()));
			ecranC.getTblCatalogue().miseAJour();
		} catch (BLLException e) {
			ecranC.popup(e.getMessage(), "Erreur Technique", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}

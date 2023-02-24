package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {

	private float montant;
	private List<Ligne> lignesPanier;
	
	
	public Panier() {
		lignesPanier = new ArrayList<>();
		montant = 0f;
	}


	/**
	 * @return the montant
	 */
	public float getMontant() {
		return montant;
	}

	/**
	 * @return the ligne
	 */
	public Ligne getLigne(int index) {
		return lignesPanier.get(index);
	}

	/**
	 * @return the lignesPanier
	 */
	public List<Ligne> getLignesPanier() {
		return lignesPanier;
	}
	
	/**
	 * @param new ligne
	 */
	public void addLigne(Article article, int qte) {
		Ligne ligneToAdd = new Ligne(article, qte);
		lignesPanier.add(ligneToAdd);
		montant += ligneToAdd.getPrix() * qte;
	}
	
	/**
	 * @param update ligne
	 */
	public void updateLigne(int index, int newQte) {
		Ligne ligneToUpdate = this.getLigne(index);
		montant -= ligneToUpdate.getPrix()*ligneToUpdate.qte;
		ligneToUpdate.setQte(newQte);
		montant += ligneToUpdate.getPrix()*ligneToUpdate.qte;
	}
	
	/**
	 * @param remove ligne
	 */
	public void removeLigne(int index) {
		Ligne ligneToRemove = lignesPanier.remove(index);
		montant -= ligneToRemove.getPrix() * ligneToRemove.qte;
	}
	
	/**
	 * @param the montant
	 */
	public void setMontant(float montant) {
		this.montant = montant;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Panier :\n\n");
		for(Ligne ligne: lignesPanier) {
			sb.append("Ligne ");
			sb.append(lignesPanier.indexOf(ligne));
			sb.append(" :\t");
			sb.append(ligne.toString());
			sb.append("\n");
		}
		sb.append("\nValeur du panier :" + getMontant());
		sb.append("\n\n");
		return sb.toString();
	}
	
}

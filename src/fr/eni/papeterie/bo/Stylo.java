package fr.eni.papeterie.bo;

public class Stylo extends Article {

	private String couleur;

	
	public Stylo() {
		super();
	}

	/**
	 * @param idArticle
	 * @param marque
	 * @param ref
	 * @param designation
	 * @param pu
	 * @param qte
	 * @param couleur
	 */
	public Stylo(int idArticle, String marque, String ref, String designation, float pu, int qte, String couleur) {
		super(idArticle, marque, ref, designation, pu, qte);
		this.couleur = couleur;
	}

	/**
	 * @param marque
	 * @param ref
	 * @param designation
	 * @param pu
	 * @param qte
	 * @param couleur
	 */
	public Stylo(String marque, String ref, String designation, float pu, int qte, String couleur) {
		super(marque, ref, designation, pu, qte);
		this.couleur = couleur;
	}

	/**
	 * @return the couleur
	 */
	public String getCouleur() {
		return couleur;
	}

	/**
	 * @param couleur the couleur to set
	 */
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append(" Style [Couleur=");
		sb.append(this.getCouleur());
		sb.append("]");
		return sb.toString();
	}	
}

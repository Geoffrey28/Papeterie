package fr.eni.papeterie.bo;

public abstract class Article {
	
	private Integer idArticle;
	private String reference;
	private String marque;
	private String designation;
	private float prixUnitaire;
	private int qteStock;
	
	
	public Article() {	
	}
	
	/**
	 * @param idArticle
	 * @param reference
	 * @param marque
	 * @param designation
	 * @param prixUnitaire
	 * @param qteStock
	 */
	public Article(Integer idArticle, String marque, String ref, String designation, float pu, int qte) {
		this.idArticle = idArticle;
		this.marque = marque;
		this.reference = ref;
		this.designation = designation;
		this.prixUnitaire = pu;
		this.qteStock = qte;
	}
	
	/**
	 * @param marque
	 * @param ref
	 * @param designation
	 * @param pu
	 * @param qte
	 */
	public Article(String marque, String ref, String designation, float pu, int qte) {
		this.marque = marque;
		this.reference = ref;
		this.designation = designation;
		this.prixUnitaire = pu;
		this.qteStock = qte;
	}

	/**
	 * @return the idArticle
	 */
	public Integer getIdArticle() {
		return idArticle;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @return the prixUnitaire
	 */
	public float getPrixUnitaire() {
		return prixUnitaire;
	}

	/**
	 * @return the qteStock
	 */
	public int getQteStock() {
		return qteStock;
	}

	/**
	 * @param idArticle the idArticle to set
	 */
	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @param marque the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @param prixUnitaire the prixUnitaire to set
	 */
	public void setPrixUnitaire(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	/**
	 * @param qteStock the qteStock to set
	 */
	public void setQteStock(int qteStock) {
		this.qteStock = qteStock;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Article [idArticle=");
		sb.append(this.getIdArticle());
		sb.append(", reference=");
		sb.append(this.getReference());
		sb.append(", marque=");
		sb.append(this.getMarque());
		sb.append(", designation=");
		sb.append(this.getDesignation());
		sb.append(", prixUnitaire=");
		sb.append(this.getPrixUnitaire());
		sb.append(", qteStock=");
		sb.append(this.getQteStock());
		sb.append("]");
		return sb.toString();
		
	}
}

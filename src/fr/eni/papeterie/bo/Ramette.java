package fr.eni.papeterie.bo;

public class Ramette extends Article {

	private int grammage;

	
	public Ramette() {
		
	}

	/**
	 * @param idArticle
	 * @param marque
	 * @param ref
	 * @param designation
	 * @param pu
	 * @param qte
	 * @param grammage
	 */
	public Ramette(Integer idArticle, String marque, String ref, String designation, float pu, int qte, int grammage) {
		super(idArticle, marque, ref, designation, pu, qte);
		this.grammage = grammage;
	}
	
	/**
	 * @param marque
	 * @param ref
	 * @param designation
	 * @param pu
	 * @param qte
	 * @param grammage
	 */
	public Ramette(String marque, String ref, String designation, float pu, int qte, int grammage) {
		super(marque, ref, designation, pu, qte);
		this.grammage = grammage;
	}

	/**
	 * @return the grammage
	 */
	public int getGrammage() {
		return grammage;
	}

	/**
	 * @param grammage the grammage to set
	 */
	public void setGrammage(int grammage) {
		this.grammage = grammage;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append(" Ramette [grammage=");
		sb.append(this.getGrammage());
		sb.append("]");
		return sb.toString();
	}
}

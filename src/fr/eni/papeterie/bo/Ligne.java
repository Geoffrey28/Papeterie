package fr.eni.papeterie.bo;

public class Ligne {

	protected int qte;
	private Article article;
	
	/**
	 * @param qte
	 * @param article
	 */
	public Ligne(Article article, int qte) {
		setArticle(article);
		setQte(qte);
	}

	/**
	 * @return the qte
	 */
	public int getQte() {
		return qte;
	}

	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}
	
	/**
	 * 
	 * @return the prix
	 */
	public float getPrix() {
		return article.getPrixUnitaire();
	}

	/**
	 * @param qte the qte to set
	 */
	public void setQte(int qte) {
		this.qte = qte;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Ligne [qte=");
		sb.append(this.getQte());
		sb.append(", prix=");
		sb.append(this.getPrix());
		sb.append(", article=");
		sb.append(this.article.toString());
		sb.append("]");
		return sb.toString();
	}
}

package fr.eni.papeterie.dal;

import java.util.List;

import fr.eni.papeterie.bo.Article;

public interface ArticleDAO {
	
	public List<Article> selectByMarque(String marque) throws DALException;
	public List<Article> selectByMotCle(String motCle) throws DALException;
}

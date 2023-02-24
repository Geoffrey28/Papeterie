package fr.eni.papeterie.dal;

import java.util.List;

import fr.eni.papeterie.bo.Article;

public interface ArticleDAO {
	public void insert(Article article) throws DALException;
		
		public void update(Article article) throws DALException;
		
		public Article selectById(int id) throws DALException;
		
		public List<Article> selectAll() throws DALException;
		
		public void delete(int id) throws DALException;
}

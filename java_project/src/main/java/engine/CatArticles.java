
package engine;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

class CatArticles{

	private Map<Long,Article> catArticle;
	private long dupedArticles;
	private long uniqueReviews;


	CatArticles(){
		this.catArticle = new TreeMap<>();
		this.dupedArticles = 0;
		this.uniqueReviews = 0;
	}

	public Map<Long,Article> getCatArticles(){
		return this.catArticle.entrySet().stream().collect(Collectors.toMap(f->f.getKey(),f->f.getValue().clone()));
	}

	public long getDupedArticles(){
		return dupedArticles;
	}

	public long getUniqueReviews(){
		return uniqueReviews;
	}

	public void addDupedArticle(){
		this.dupedArticles++;
	}

	public void addUniqueReview(){
		this.uniqueReviews++;
	}

	public int size(){
		return this.catArticle.size();
	}

	public void addArticle(Article article){
		this.catArticle.put(article.getId(),article);
	}

	public void addReview(Long idArticle, Review review){
		this.catArticle.get(idArticle).addReview(review);
	}

	public boolean containsArtigo(Long idArtigo){
		return this.catArticle.containsKey(idArtigo);
	}

	public boolean containsReview(Long idArticle, Long idReview){
		return this.catArticle.get(idArticle).containsReview(idReview);
	}

	public void changeTitle(Long idArticle, String title){
		this.catArticle.get(idArticle).setTitle(title);
	}


}

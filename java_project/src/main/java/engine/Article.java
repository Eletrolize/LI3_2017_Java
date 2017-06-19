package engine;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;


class Article{

	private String title;
	private long id;
	private Map<Long,Review> reviews;


	Article(String title, long id){
		this.title = title;
		this.id = id;
		reviews = new HashMap<Long,Review>();
	}

	private Article(Article a){
		this.title = a.getTitle();
		this.id = a.getId();
		this.reviews = a.getReviews();
	}

	String getTitle(){
		return this.title;
	}

	long getId(){
		return this.id;
	}

	Map<Long,Review> getReviews(){
		return this.reviews.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
	}

	void setTitle(String title){
		this.title = title;
	}


	boolean containsReview(Long idReview){
		return this.reviews.containsKey(idReview);
	}

	long getMaxTextWords(){
		long r;

		ComparatorReviewByTextWords comparator = new ComparatorReviewByTextWords();

		Review review = this.reviews.values().stream().min(comparator).get();
		r = review.getTextWords();

		return r;
	}

	long getMaxTextSize(){
		// percorrer os review e devolver o maior
		long r;

		ComparatorReviewByTextSize comparator = new ComparatorReviewByTextSize();

		Review review = this.reviews.values().stream().min(comparator).get();
		r = review.getTextSize();

		return r;
	}

	boolean isTitlePrefix(String prefix){
		return title.startsWith(prefix);
	}

	/*
		@param review Revisao a adicionar/atualizar
	*/
	void addReview(Review review){
		this.reviews.put(review.getIdReview(),review);
	}

	public Article clone(){
		return new Article(this);
	}

	public boolean equals(Object obj){
		if(obj == this)
			return true;
		if(obj == null || obj.getClass() != this.getClass())
			return false;
		Article a = (Article) obj;
		return a.getTitle().equals(title)
				&& a.getId() == id
				&& a.getReviews().equals(reviews);
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Titulo do artigo com o id ").append(id).append(" ").append(title).append(" tem ").append(this.getMaxTextWords()).append(" palavras e ocupa ").append(this.getMaxTextSize()).append(" bytes.").append("\n");
		return sb.toString();
	}
}

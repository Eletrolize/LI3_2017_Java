package engine;


import java.util.Comparator;

class ComparatorArticleByTextWords implements Comparator<Article>{
	public int compare(Article article1, Article article2){
		long r = article1.getMaxTextWords() - article2.getMaxTextWords();
		if (r == 0)
			return 0;
		else if (r < 0)
			return 1;
		else
			return -1;
	}
}		

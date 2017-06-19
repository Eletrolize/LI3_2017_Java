package engine;

import java.util.Comparator;


class ComparatorArticleByTextSize implements Comparator<Article>{
	public int compare(Article article1, Article article2){
		long r = article1.getMaxTextSize() - article2.getMaxTextSize();
		if (r == 0)
			return 0;
		else if (r < 0)
			return 1;
		else
			return -1;
	}
}	

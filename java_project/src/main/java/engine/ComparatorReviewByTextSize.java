package engine;

import java.util.Comparator;


class ComparatorReviewByTextSize implements Comparator<Review>{
	public int compare(Review review1, Review review2){
		long r = review1.getTextSize() - review2.getTextSize();
		if (r == 0)
			return 0;
		else if (r < 0)
			return 1;
		else
			return -1;
	}
}

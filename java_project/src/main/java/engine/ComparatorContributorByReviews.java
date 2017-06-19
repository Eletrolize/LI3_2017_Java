package engine;

import java.util.Comparator;

class ComparatorContributorByReviews implements Comparator<Contributor>{
	public int compare(Contributor contributor1, Contributor contributor2){
		long r = contributor1.getReviewNumber() - contributor2.getReviewNumber();
		if (r == 0)
			return 0;
		else if(r < 0)
			return 1;
		else
			return -1;
	}
}		

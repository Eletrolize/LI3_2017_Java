package engine;

import li3.Interface;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLStreamException;


public class QueryEngineImpl implements Interface {

	private CatArticles catArticles;
	private CatContributors catContributors;

    public void init() {
		this.catArticles = new CatArticles();
		this.catContributors = new CatContributors();
	}

    public void load(int nsnaps, ArrayList<String> snaps_paths) {
		for(int i = 0 ; i < nsnaps ; i++){
			try{
				Parser parser = new Parser();
				String name = snaps_paths.get(i);
				System.out.println("Parsing file: " + name);
				parser.parseFile(name,catArticles,catContributors);
			}catch (FileNotFoundException|XMLStreamException|UnsupportedEncodingException e) {
				System.out.println(e.getMessage());
			}
    	}
	}

    public long all_articles() {
		long r = this.catArticles.size();
		r += this.catArticles.getDupedArticles();
        return r;
    }

    public long unique_articles() {
		long r = catArticles.size();
        return r;
    }

    public long all_revisions() {
		long r = this.catArticles.getUniqueReviews();
        return r;
    }

    public ArrayList<Long> top_10_contributors() {
		ArrayList<Long> r;
		ComparatorContributorByReviews comparator = new ComparatorContributorByReviews();
		r = this.catContributors.getCatContributors().values().stream().sorted(comparator).limit(10).map(e->e.getIdContributor()).collect(Collectors.toCollection(ArrayList::new));
		return r;
    }

    public String contributor_name(long contributor_id) {
		return this.catContributors.getCatContributors().get(contributor_id).getUsername();
    }

    public ArrayList<Long> top_20_largest_articles() {
		ArrayList<Long> r;
		ComparatorArticleByTextSize comparator =  new ComparatorArticleByTextSize();
		r = this.catArticles.getCatArticles().values().stream().sorted(comparator).limit(20).map(e->e.getId()).collect(Collectors.toCollection(ArrayList::new));
        return r;
    }

    public String article_title(long article_id) {
		String r;
		r = this.catArticles.getCatArticles().get(article_id).getTitle();
        return r;
    }

    public ArrayList<Long> top_N_articles_with_more_words(int n) {
		ArrayList<Long> r;
		ComparatorArticleByTextWords comparator = new ComparatorArticleByTextWords();
		r = this.catArticles.getCatArticles().values().stream().sorted(comparator).limit(n).map(e->e.getId()).collect(Collectors.toCollection(ArrayList::new));
		return r;
    }

    public ArrayList<String> titles_with_prefix(String prefix) {
		ArrayList<String> r;
		ComparatorStringTitle comparator = new ComparatorStringTitle();
		r = this.catArticles.getCatArticles().values().stream().filter(e->e.isTitlePrefix(prefix)).map(e->e.getTitle()).sorted(comparator).collect(Collectors.toCollection(ArrayList::new));
        return r;
    }

    public String article_timestamp(long article_id, long revision_id) {
		String r = null;
		Article article = this.catArticles.getCatArticles().get(article_id);
		Review review = null;
		if(article != null){
			review = article.getReviews().get(revision_id);
		}
		if(review != null){
			r = review.getTimestamp();
		}
        return r;
    }

    public void clean() {
		catArticles = null;
		catContributors = null;
		System.gc();
    }
}

package engine;

class Review{

	private long idReview;
	private String timestamp;
	private long textSize;
	private long textWords;

	Review(long idReview, String timestamp, long textSize, long textWords){
		this.idReview = idReview;
		this.timestamp = timestamp;
		this.textSize = textSize;
		this.textWords = textWords;
	}


	Review(Review r){
		this.idReview = r.getIdReview();
		this.timestamp = r.getTimestamp();
		this.textSize = r.getTextSize();
		this.textWords = r.getTextWords();
	}

	long getIdReview(){
		return this.idReview;
	}

	String getTimestamp(){
		return this.timestamp;
	}


	long getTextSize(){
		return this.textSize;
	}

	long getTextWords(){
		return this.textWords;
	}

	public Review clone(){
		return new Review(this);
	}

	public boolean equals(Object obj){
		if(obj == this)
			return true;
		if(obj == null | obj.getClass() != this.getClass())
			return false;
		Review r = (Review) obj;
		return r.getIdReview() == idReview
				&& r.getTimestamp().equals(timestamp)
				&& r.getTextSize() == textSize
				&& r.getTextWords() == textWords;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("A revisão com o id ").append(idReview).append(" com o timestamp ").append(timestamp).append(" tem ").append(textWords).append(" palavras e ocupa ").append(textSize).append(" bytes.\n");
		return sb.toString();
	}
}

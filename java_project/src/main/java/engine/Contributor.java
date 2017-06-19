package engine;


class Contributor{

	private long idContributor;
	private String username;
	private long reviewNumber;

	Contributor(long idContributor, String username){

		this.idContributor = idContributor;
		this.username = username;
		this.reviewNumber = 1;
	}

	private Contributor(Contributor c) {
		this.idContributor = c.getIdContributor();
		this.username = c.getUsername();
		this.reviewNumber = c.getReviewNumber();
	}

	long getIdContributor(){
		return this.idContributor;
	}

	String getUsername(){
		return this.username;
	}

	long getReviewNumber(){
		return this.reviewNumber;
	}

	void addContribution(){
		this.reviewNumber++;
	}

	public Contributor clone(){
		return new Contributor(this);
	}

	public boolean equals(Object obj){
		if(obj == this)
			return true;
		if(obj == null | obj.getClass() != this.getClass())
			return false;
		Contributor c = (Contributor) obj;
		return c.getIdContributor() == idContributor
				&& c.getUsername().equals(username)
				&& c.getReviewNumber() == reviewNumber;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Contribuidor com o username ").append(username).append(" e com o id ").append(idContributor).append(" contribuiu ").append(reviewNumber).append(" vezes.\n");
		return sb.toString();
	}
}

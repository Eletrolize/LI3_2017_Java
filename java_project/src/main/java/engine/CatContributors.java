package engine;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

class CatContributors{

	private Map<Long,Contributor> catContributors;

	CatContributors(){
		this.catContributors = new TreeMap<>();
	}

	Map<Long,Contributor> getCatContributors(){
		return this.catContributors.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
	}

	void addContributor(Contributor contributor){
		this.catContributors.put(contributor.getIdContributor(),contributor);
	}

	void addContribution(Long idContributor){
		this.catContributors.get(idContributor).addContribution();
	}


	boolean containsContributor(Long idContributor){
		return this.catContributors.containsKey(idContributor);
	}
}

package engine;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;


import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

class Parser{

	Parser(){}

	void parseFile(String filename,CatArticles catA, CatContributors catB) throws FileNotFoundException, XMLStreamException, UnsupportedEncodingException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		factory.setProperty(XMLInputFactory.IS_COALESCING,true); // PARA NAO CORTAR TEXTO
		XMLStreamReader reader =  factory.createXMLStreamReader(new FileInputStream(new File(filename)));

		String text = "", timestamp = "", username = "", title = "";
		int n = 0;
		Article article = null;
		Review review = null;
		Contributor contributor = null;
		boolean newReview = false, ipMode = false;
		Long idArticle = Long.valueOf(-1);
		Long idContributor = Long.valueOf(-1);
		Long idReview = Long.valueOf(-1);
		Long textWords = Long.valueOf(-1);
		Long textSize = Long.valueOf(-1);

		while(reader.hasNext()){
			int event = reader.next();

			switch (event) {

				case XMLStreamConstants.CHARACTERS:

					text = reader.getText();
					break;

				case XMLStreamConstants.END_ELEMENT:

					switch (reader.getLocalName()) {
						case "id":
							switch (n) {
								case 0:
									idArticle = Long.valueOf(text);
									n++;
									break;

								case 1:
									idReview = Long.valueOf(text);
									n++;
									break;

								case 2:
									idContributor = Long.valueOf(text);
									break;

							}
							break;

						case "timestamp":
							timestamp = text;
							break;

						case "username":
							username = text;
							break;

						case "title":
							title = text;
							break;

						case "text":

							textSize = Long.valueOf(text.getBytes("UTF-8").length);
							textWords = countWord(text);
							break;

						case "ip":
							ipMode = true;
							break;

						case "page":
							n = 0;
							newReview = false;
							ipMode = false;
							idArticle = Long.valueOf(-1);
							idContributor = Long.valueOf(-1);
							idReview = Long.valueOf(-1);
							break;

						case "revision":
							/*Adicionar artigo*/
							if(catA.containsArtigo(idArticle)){
								// mudamos o titulo se ele existir
								catA.changeTitle(idArticle,title);
								catA.addDupedArticle();
								// Vemos se nao existe a revisão no artigo
								if(!catA.containsReview(idArticle,idReview)) {
									review = new Review(idReview, timestamp, textSize, textWords);
									newReview = true;
									catA.addReview(idArticle, review);
									catA.addUniqueReview();
								}
							}else {
								// senão criamos um novo
								article = new Article(title, idArticle);
								review = new Review(idReview,timestamp,textSize,textWords);
								newReview = true;
								// e adicionamos ao catalogo
								catA.addArticle(article);
								catA.addReview(idArticle,review);
								catA.addUniqueReview();
							}

							if(newReview){
								if(!ipMode){
									if(catB.containsContributor(idContributor)){
										catB.addContribution(idContributor);
									}else{
										contributor = new Contributor(idContributor,username);
										catB.addContributor(contributor);
									}
								}
							}

							break;

						default:
							break;

					}

				default:
					break;

			}
		}
	}

	private long countWord (String text){
		boolean w;
		long r;
		String trimmed = text.trim();
		w = trimmed.isEmpty();
		if(w)
			r = 0;
		else
			r = trimmed.split("\\s+").length;
		return r;
	}
}


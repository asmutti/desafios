package redditCrawler;

import java.util.ArrayList;
import java.util.List;
import com.github.jreddit.entity.Submission;
import com.github.jreddit.retrieval.Submissions;
import com.github.jreddit.retrieval.params.SubmissionSort;
import com.github.jreddit.utils.restclient.HttpRestClient;
import com.github.jreddit.utils.restclient.RestClient;

public class Crawler {
	
	private List<String> subReddits;
	private static final long hotTopicScore = 5000;
	private String redditURL = "https://www.reddit.com";
	private String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36";
	
	public Crawler() {
		subReddits = new ArrayList<String>();
	}
	
	/**
	 * Trata o input e monta em uma lista a ser pesquisada.
	 */
	private List<String> addSubReddit(String input) {		
		String[] subReddit = input.split(";");
		
		for (int i = 0; i <= subReddit.length -1; i++) {
			subReddits.add(subReddit[i]);
		}
				
		return subReddits;
	}
	

	private List<Submission> buscaHotTopics(Submissions s, String subReddit) {
		List<Submission> topicosHot = new ArrayList<Submission>();
		
		//faz uma busca em um subReddit, que irá retornar os 25 hot topics (25 = número de exibição em uma página).
		List<Submission> topicos = 
				s.ofSubreddit(subReddit, SubmissionSort.TOP, -1, 25, null, null, true);
		
		//seleciona os hot topics com o score >5000
		for (Submission submission : topicos) {
			if(submission.getScore() >= hotTopicScore) {
				topicosHot.add(submission);
			}
		}
	
		return topicosHot;
	}
	
	public void run(String subReddit) {
		RestClient restClient = new HttpRestClient();
		restClient.setUserAgent(userAgent);
		Submissions s = new Submissions(restClient);
		List<String> listaSubReddit;
		List<Submission> topicosHot = new ArrayList<Submission>();
		
		listaSubReddit = this.addSubReddit(subReddit);
		//System.out.println("Lista criada com sucesso.");
		
		for (String sub : listaSubReddit) {
			topicosHot = buscaHotTopics(s, sub);
			for (Submission submission : topicosHot) {
				System.out.println();
				System.out.println("------------------------------------------------------------------");
				System.out.println("Sub: " + submission.getSubreddit());
				System.out.println("Título: " + submission.getTitle());
				System.out.println("Upvotes: " + submission.getUpVotes());
				System.out.println("Link: " + submission.getURL());
				
				//se o link do tópico for um link externo, exibe um novo output com o link dos comentários
				if(!submission.getURL().contains(redditURL)) {
					System.out.println("Link Comentários: " + redditURL.concat(submission.getPermalink()));
				}
				System.out.println("Número de comentários: " + submission.getCommentCount());
			}
			System.out.println("********************************************************************\n");
		}
	}
	
}

package redditCrawler;

import java.util.Scanner;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.github.jreddit.retrieval.Submissions;
import com.github.jreddit.utils.restclient.HttpRestClient;
import com.github.jreddit.utils.restclient.RestClient;

public class App {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String listaSubReddits = "";
		boolean menu = false;
		Crawler crawler = new Crawler();
		
		//inicia a API TelegramBot
		ApiContextInitializer.init();
		TelegramBotsApi botsApi = new TelegramBotsApi();
		
		
		//Registra o bot
        try {
            botsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
		
		while(!menu) {
			System.out.println("Por favor digite os subreddits em uma linha, separado por ; (ponto e vírgula)\n");
			listaSubReddits = scanner.nextLine();
			
			if(listaSubReddits.equals("")) {
				System.out.println("Erro! A lista não pode estar vazia.");
			} else if(!listaSubReddits.contains(";")) {
				System.out.println("Os campos precisam ser separados por ;\n Ex: battlestations;cats;apple");
			} else {
				menu = true;
			}
		}
		
		//executa o crawler
		crawler.run(listaSubReddits);
		
		
	}
}

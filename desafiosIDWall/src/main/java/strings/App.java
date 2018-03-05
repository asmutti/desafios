package strings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App {
	public static void main(String[] args) throws Exception {
		Manipulador manipulador = new Manipulador();
		List<String> linhas = new ArrayList<String>();
		java.io.BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String texto = null;
		int opcao = 0;
		int tamanhoLinha;
		
		
		
		System.out.println("Para texto alinhado e não justificado, digite 1.");
		System.out.println("Para texto justificado, digite 2.");
		System.out.println();
		
		opcao = Integer.parseInt(in.readLine());
		System.out.println();

		System.out.println("Defina o tamanho de cada linha:");
		tamanhoLinha = Integer.parseInt(in.readLine());
		System.out.println();
		
		System.out.println("Copie e cole o seu texto aqui:");
		texto = in.readLine();
		System.out.println();
		
		
		
		if(opcao == 1) {
			texto = manipulador.formatador(texto, tamanhoLinha);
			System.out.println(texto);
		} else if(opcao == 2) {
			String palavras[] = texto.toString().split(" ");
			linhas = manipulador.justificadorTexto(palavras, tamanhoLinha);
			
			for (Iterator iterator = linhas.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				System.out.println(string);			
			}
		}		
		
	}
}
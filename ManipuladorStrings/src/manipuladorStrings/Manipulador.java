package manipuladorStrings;
import java.nio.file.attribute.AclEntry.Builder;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Manipulador {
	
	  String formatador(String pTexto, int pComprimento) throws Exception {
		
		Locale locale = Locale.getDefault();		
		BreakIterator limiteLinhas = BreakIterator.getLineInstance(locale);
		limiteLinhas.setText(pTexto);		
		StringBuilder textoFormatado = new StringBuilder();
		
		//variaveis que tamPalavraem o inicio e fim de cada string tamPalavraida no texto
		int inicio = limiteLinhas.first();
		int fim = limiteLinhas.next();
		
		//guarda o tamanho da linha atual
		int tamanhoLinha = 0;
		
		while(fim != BreakIterator.DONE) {			
			String palavra = pTexto.substring(inicio, fim);
			
			if(palavra.length() > pComprimento) {
				throw new Exception("Uma palavra no seu texto é maior que o comprimento de linha desejado. Por favor altere o comprimento da linha ou altere a palavra.");
			}
			
			tamanhoLinha = tamanhoLinha + palavra.length();
			
			if(tamanhoLinha >= pComprimento) {
				textoFormatado.append(System.lineSeparator());
				tamanhoLinha = palavra.length();
			} 
			
			textoFormatado.append(palavra);
			
			inicio = fim;
			fim = limiteLinhas.next();
		}
				
		return textoFormatado.toString();
				
	}
	  /*
	   * Cada linha do texto é salvo em um slot da lista.
	   * 
	   */
	  List<String> justificadorTexto (String[] pPalavras, int pComprimento) {
		
		List<String> linhas = new ArrayList<String>();
		int i = 0;
		
		while(i < pPalavras.length) {
			int tamPalavra = pPalavras[i].length();
			int j = i + 1;
			
			while(j < pPalavras.length) {
				if(pPalavras[j].length() + tamPalavra + 1 > pComprimento) {
					break;
				}
				tamPalavra = tamPalavra + 1 + pPalavras[j].length();
				j++;
			}
			
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append(pPalavras[i]);
			int dif = j - i - 1; 
			
			
			//justificado a esquerda
			if(j == pPalavras.length || dif == 0) {
				for (int k = i + 1; k < j; k++) {
					sBuilder.append(" ");
					sBuilder.append(pPalavras[k]);					
				}
				
				for(int k = sBuilder.length(); k < pComprimento; k++) {
					sBuilder.append(" ");
				}
			//justificado central
			} else {
				int espacos = (pComprimento - tamPalavra) / dif;
				int aux = (pComprimento - tamPalavra) % dif;
				
				for(int k = i + 1; k < j; k++) {
					for(int s = espacos; s > 0; s--) {
						sBuilder.append(" ");
					}
					
					//se for necessário mais de um espaço entre determinado conjunto de palavras.
					if(aux > 0) {
						sBuilder.append(" ");
						aux--;
					}
					
					sBuilder.append(" ");
					sBuilder.append(pPalavras[k]);
				}
			}
			linhas.add(sBuilder.toString());
			i = j;
		}		
		return linhas;	
	}
	
}

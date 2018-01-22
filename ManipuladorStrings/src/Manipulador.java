import java.text.BreakIterator;
import java.util.Locale;
import java.util.Scanner;

public class Manipulador {
	
	static String formatador(String pTexto, int pComprimento) throws Exception {
		
		Locale locale = Locale.getDefault();		
		BreakIterator limiteLinhas = BreakIterator.getLineInstance(locale);
		limiteLinhas.setText(pTexto);		
		StringBuilder textoFormatado = new StringBuilder();
		
		//variaveis que contem o inicio e fim de cada string contida no texto
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

	public static void main(String[] args) throws Exception {		
		String texto =
				"And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
		
	
		
		
		texto = formatador(texto, 40);
		System.out.println(texto);

	}

}

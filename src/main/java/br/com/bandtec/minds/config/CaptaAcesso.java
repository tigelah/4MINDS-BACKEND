package br.com.bandtec.minds.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import br.com.bandtec.minds.domain.Psicologo;

public class CaptaAcesso {

	public static void gravaLista(ArrayList<Psicologo> lista, boolean arqTXT) { // inserir no Array List a classe que
																				// ser� captada
		FileWriter arq = null;
		Formatter saida = null;
		boolean error = false;
		String nomeArquivo = "C:\\.txt"; // definir o nome do arquivo TXT

		new File(nomeArquivo).mkdir(); // cria��o do diret�rio

		try {
			arq = new FileWriter(nomeArquivo, true);
			saida = new Formatter(arq);
		} catch (IOException erro) {
			System.err.println("Erro ao abrir arquivo");
			System.exit(1);
		}

		try {
			for (Psicologo a : lista) { // inserir a classe que ser� captada

				saida.format("%d %s %.2f%n", a.getEmail()); // inserir os gets da classe que ser� captada
			}
		} catch (FormatterClosedException erro) {
			System.err.println("Erro ao gravar no arquivo");
			error = true;
		} finally {
			saida.close();
			try {
				arq.close();
			}

			catch (IOException erro) {
				System.err.println("Erro ao fechar arquivo.");
				error = true;
			}
			if (error) {
				System.exit(1);
			}
		}

	}

	public static void leExibeArquivo(boolean arqTXT) {
		FileReader arq = null;
		Scanner entrada = null;
		String nomeArquivo = ".txt";
		boolean error = false;

		try {
			arq = new FileReader(nomeArquivo);
			entrada = new Scanner(arq);
		} catch (FileNotFoundException erro) {
			System.err.println("Arquivo n�o encontrado");
			System.exit(1);
		}

		try {

			System.out.printf("%-8s%-10s%7s\n", "XX"); // modificar com base no tipo do espa�amento
			while (entrada.hasNext()) {
				String XX = entrada.next();
				System.out.printf("%-8d%-10s%7.2f\n", XX); // Inserir as variaveis que ser�o lidas
			}
		} catch (NoSuchElementException erro) {
			System.err.println("Arquivo com problemas.");
			error = true;
		} catch (IllegalStateException erro) {
			System.err.println("Erro na leitura do arquivo.");
			error = true;
		} finally {
			entrada.close();
			try {
				arq.close();
			} catch (IOException erro) {
				System.err.println("Erro ao fechar arquivo.");
				error = true;
			}
			if (error) {
				System.exit(1);
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

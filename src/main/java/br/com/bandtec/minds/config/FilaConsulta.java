package br.com.bandtec.minds.config;

import java.util.concurrent.ArrayBlockingQueue;

import br.com.bandtec.minds.domain.Consulta;

public class FilaConsulta {

	public String agendaPsicologo() {
		
		ArrayBlockingQueue<Consulta> fila = new ArrayBlockingQueue<Consulta>(5);

		try {
			fila.add(new Consulta());
		}
		catch(IllegalStateException erro) {
			System.out.println("Fila cheia");
		}

		return "Consulta agendada: "+ fila.poll();
	}

}


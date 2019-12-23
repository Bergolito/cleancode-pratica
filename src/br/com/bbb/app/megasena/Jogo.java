package br.com.bbb.app.megasena;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Jogo {

	// constantes
	private static final int QTD_NUMEROS_SORTEADOS = 6;
	private static final int MAXIMO_NUMERO = 60;
	private static final String DIVISOR = "=====================================================";
	
	// atributos
	private List<Integer> numerosApostados = new ArrayList<>();
	private List<Integer> numerosSorteados = new ArrayList<>();
	private List<String> jogosGanhadoresQuadra = new ArrayList<>();
	private List<String> jogosGanhadoresQuina = new ArrayList<>();
	private List<String> jogosGanhadoresSena = new ArrayList<>();
	
	// construtores
	public Jogo() {
		
	}
	
	public Jogo(List<Integer> numerosApostados, List<Integer> numerosSorteados) {
		super();
		this.numerosApostados = numerosApostados;
		this.numerosSorteados = numerosSorteados;
	}

	// métodos
	public List<Integer> geraNumerosApostados(int numeroApostas) {
		Set<Integer> numerosSemRepecticao = retornaConjuntoNumerosAleatorios(QTD_NUMEROS_SORTEADOS);
		Iterator<Integer> iter = numerosSemRepecticao.iterator();
		while (iter.hasNext()) {
			numerosApostados.add(iter.next());
		}
		Collections.sort(numerosApostados);
		
		return numerosApostados; 
	}
	
	public Set<Integer> retornaConjuntoNumerosAleatorios(int qtdItens){
		Set<Integer> numerosSemRepecticao = null;
		do {
			numerosSemRepecticao = new HashSet<>();
			for (int i = 0; i < qtdItens; i++) {
				numerosSemRepecticao.add(geraNumeroAleatoriaEntreUme60());
			}
			
		} while(numerosSemRepecticao.size() != 6);

		return numerosSemRepecticao;
	}
	
	public List<Integer> geraNumerosSorteados() {
		Set<Integer> numerosSemRepecticao = retornaConjuntoNumerosAleatorios(QTD_NUMEROS_SORTEADOS);
		Iterator<Integer> iter = numerosSemRepecticao.iterator();
		while (iter.hasNext()) {
			numerosSorteados.add(iter.next());
		}
		Collections.sort(numerosSorteados);
			
		return numerosSorteados;		
	}
	
	public static int geraNumeroAleatoriaEntreUme60() {
		int numeroGerado = -1;
		Random random = new Random();
		do {
			numeroGerado = (int)(random.nextInt(MAXIMO_NUMERO + 1) );
		}while(numeroGerado == 0);
		
		return numeroGerado;
	}
	
	public int calculaNumeroAcertos(
			List<Integer> numerosApostados, 
			List<Integer> numerosSorteados) {
		
		//
		Set<Integer> result = numerosApostados.stream()
				  .distinct()
				  .filter(numerosSorteados::contains)
				  .collect(Collectors.toSet());

		return result.size();
	}

	public String retornaJogoGanhador(List<Integer> numerosApostados) {
		StringBuilder str = new StringBuilder("[");
		numerosApostados.forEach(num -> {
			str.append(" "+num);
		});
		str.append(" ]");
		return str.toString();
	}
	
	public int executaJogo(List<Integer> numerosSorteados) {
		int numeroApostas = 6;
		
		Jogo jogo = new Jogo();
		numerosApostados = jogo.geraNumerosApostados(numeroApostas);
		System.out.println(DIVISOR);
		System.out.print("Numeros Apostados = [");
		for (int i = 0; i < numerosApostados.size(); i++) {
			System.out.print(" "+numerosApostados.get(i));
		}
		System.out.print("] ");
		int numeroAcertos = jogo.calculaNumeroAcertos(numerosApostados, numerosSorteados);
		System.out.println("Acertos = "+numeroAcertos);
		
		// guarda os jogos vencedores
		if(numeroAcertos >= 4 && numeroAcertos <= 6) {
			if(numeroAcertos == 4) {
				jogosGanhadoresQuadra.add(retornaJogoGanhador(numerosApostados));
			} 
			else if(numeroAcertos == 5) {
				jogosGanhadoresQuina.add(retornaJogoGanhador(numerosApostados));
			} 
			else if(numeroAcertos == 6) {
				jogosGanhadoresSena.add(retornaJogoGanhador(numerosApostados));
			}
		}

		return numeroAcertos; 
	}
	
	public static void main(String[] args) {
		long tempoInicial=System.currentTimeMillis();
		long tempoFinal=0;
		double valorPremioConcurso = 10_000_000;
		int qtdJogos = 1_000_000;//1_000_000
		List<Integer> numerosSorteados = new ArrayList<>();
		
		MegaSena mega = new MegaSena(valorPremioConcurso);
		
		Jogo jogo = new Jogo();
		numerosSorteados = jogo.geraNumerosSorteados();

		int numeroAcertos = -1;
		int iteracao = 0;
		List<Ganhador> ganhadores = new ArrayList<>();
		for (int i = 1; i <= qtdJogos; i++) {
			iteracao++;
			numeroAcertos = jogo.executaJogo(numerosSorteados);
			if(numeroAcertos >= 4 && numeroAcertos <= 6) {
				ganhadores.add(new Ganhador(iteracao, numeroAcertos));
			} 
		}
		
		if(!ganhadores.isEmpty()) {
			
			System.out.println(DIVISOR);
			System.out.print("Numeros Sorteados = [");
			for (int i = 0; i < numerosSorteados.size(); i++) {
				System.out.print(" "+numerosSorteados.get(i));
			}
			System.out.println("]");
			System.out.println(DIVISOR);
			
			if(ganhadores.size() == 1) {
				System.out.println(DIVISOR);
				System.out.println("Para "+qtdJogos+" jogos realizados, achou UM ganhador!!!");
			} else if(ganhadores.size() >=2 ) {
				System.out.println("Para "+qtdJogos+" jogos realizados, achou "+ganhadores.size()+" ganhadores!!!");
			}
			int contadorQuadra = 0;
			int contadorQuina = 0;
			int contadorSena = 0;
			for (Ganhador ganhador : ganhadores) {
				if(ganhador.getNumeroAcertos() == 4) {
					contadorQuadra++;
				}
				if(ganhador.getNumeroAcertos() == 5) {
					contadorQuina++;
				}
				if(ganhador.getNumeroAcertos() == 6) {
					contadorSena++;
				}
			}
			System.out.println("Ganhadores da Quadra = "+contadorQuadra);
			System.out.println("Ganhadores da Quina = "+contadorQuina);
			System.out.println("Ganhadores da Sena = "+contadorSena);
			System.out.println(DIVISOR);
		} else {
			System.out.println("\n\n\n Não Achou ganhador!!!");
		}
		tempoFinal=System.currentTimeMillis();
		long tempoGasto = tempoFinal-tempoInicial; 
		double tempoGastoSegs = (tempoGasto/1000.0);
		System.out.println("Tempo total para exeuctar "+qtdJogos+" jogos = "+tempoGastoSegs+" s");
		
		if(!jogo.getJogosGanhadoresQuadra().isEmpty()) {
			System.out.println(DIVISOR);
			System.out.println("Ganhadores da Quadra");
			for (String quad : jogo.getJogosGanhadoresQuadra()) {
				System.out.println(quad);
			}
			System.out.println(DIVISOR);
		}

		if(!jogo.getJogosGanhadoresQuina().isEmpty()) {
			System.out.println(DIVISOR);
			System.out.println("Ganhadores da Quina");
			for (String quad : jogo.getJogosGanhadoresQuina()) {
				System.out.println(quad);
			}
			System.out.println(DIVISOR);
		}

		if(!jogo.getJogosGanhadoresSena().isEmpty()) {
			System.out.println(DIVISOR);
			System.out.println("Ganhadores da Sena");
			for (String quad : jogo.getJogosGanhadoresSena()) {
				System.out.println(quad);
			}
			System.out.println(DIVISOR);
		}

	}

	public List<Integer> getNumerosApostados() {
		return numerosApostados;
	}

	public void setNumerosApostados(List<Integer> numerosApostados) {
		this.numerosApostados = numerosApostados;
	}

	public List<Integer> getNumerosSorteados() {
		return numerosSorteados;
	}

	public void setNumerosSorteados(List<Integer> numerosSorteados) {
		this.numerosSorteados = numerosSorteados;
	}

	public List<String> getJogosGanhadoresQuadra() {
		return jogosGanhadoresQuadra;
	}

	public void setJogosGanhadoresQuadra(List<String> jogosGanhadoresQuadra) {
		this.jogosGanhadoresQuadra = jogosGanhadoresQuadra;
	}

	public List<String> getJogosGanhadoresQuina() {
		return jogosGanhadoresQuina;
	}

	public void setJogosGanhadoresQuina(List<String> jogosGanhadoresQuina) {
		this.jogosGanhadoresQuina = jogosGanhadoresQuina;
	}

	public List<String> getJogosGanhadoresSena() {
		return jogosGanhadoresSena;
	}

	public void setJogosGanhadoresSena(List<String> jogosGanhadoresSena) {
		this.jogosGanhadoresSena = jogosGanhadoresSena;
	}
}
package br.com.bbb.app.pedrapapeltesoura;

/**
 * Requisitos do Melhor de 3 (Pedra, Papel e Tesoura)
 * 1. Criar uma simulação de dois jogadores jogando “pedra, papel e
 * tesoura” por um número variável de rodadas (3, 5 etc).
 * 2. O jogo deve parar se um jogador já for o vencedor antes da última
 * rodada (cenário de vitória antecipada).
 * 3. Ao final deve ser indicado o vencedor (ou empate) e listados os
 * resultados de todas as rodadas.
 * 
 * Pedra vence Tesoura
 * Papel vence Pedra
 * Tesoura vence Papel
 * 
 */
public class PedraPapelTesoura {

	//
	public static final String PEDRA = "PE";
	public static final String PAPEL = "PA";
	public static final String TESOURA = "TE";
	
	public static final String[] POSSIBILIDADES = {
			PEDRA, PAPEL, TESOURA
	};
	
	//
	public static final int JOGADOR_0 = 0;
	public static final int JOGADOR_1 = 1;
	
	private int vitoriasJogador0 = 0;
	private int vitoriasJogador1 = 0;
	private int resultadoEmpate = 0;
	private int numeroRodadas = 0;
	private String nomeJogador0;
	private String nomeJogador1;

	public PedraPapelTesoura() {
		
	}

	public PedraPapelTesoura(int numRodadas) {
		this.numeroRodadas = numRodadas;
	}

	public PedraPapelTesoura(int numRodadas, String jogador1,String jogador2) {
		this.numeroRodadas = numRodadas;
		this.nomeJogador0 = jogador1;
		this.nomeJogador1 = jogador2;
	}
	
	public boolean vence(String jogador0, String jogador1) {
		if(jogador0.equals(PEDRA) && jogador1.equals(TESOURA)) {
			return true;
		}
		if(jogador0.equals(PAPEL) && jogador1.equals(PEDRA)) {
			return true;
		}
		if(jogador0.equals(TESOURA) && jogador1.equals(PAPEL)) {
			return true;
		}
		
		return false;
	}
	
	public void executaJogo() {
		String jogador0 = "";
		String jogador1 = "";
		int indice = -1;
		
		for (int i = 0; i < getNumeroRodadas(); i++) {
			indice = ((int)(Math.random()*100) % POSSIBILIDADES.length);
			jogador0 = POSSIBILIDADES[indice];
			indice = ((int)(Math.random()*100) % POSSIBILIDADES.length);
			jogador1 = POSSIBILIDADES[indice];;
			
			if(vence(jogador0, jogador1)) {
				vitoriasJogador0++;
			}
			else if(vence(jogador1, jogador0)) {
				vitoriasJogador1++;
			} else {
				resultadoEmpate++;
			}
		}
		System.out.println("=========================================================");
		System.out.println("Jogo Pedra-Papel-Tesoura");
		System.out.println("---------------------------------------------------------");
		System.out.println("Numero de Rodadas="+getNumeroRodadas());
		System.out.println("Vitórias Jogador "+getNomeJogador0()+"="+vitoriasJogador0);
		System.out.println("Vitórias Jogador "+getNomeJogador1()+"="+vitoriasJogador1);
		System.out.println("Empates="+resultadoEmpate);
		System.out.println("=========================================================\n");
	}
	
	
	
	public int getVitoriasJogador0() {
		return vitoriasJogador0;
	}

	public void setVitoriasJogador0(int vitoriasJogador0) {
		this.vitoriasJogador0 = vitoriasJogador0;
	}

	public int getVitoriasJogador1() {
		return vitoriasJogador1;
	}

	public void setVitoriasJogador1(int vitoriasJogador1) {
		this.vitoriasJogador1 = vitoriasJogador1;
	}

	public int getResultadoEmpate() {
		return resultadoEmpate;
	}

	public void setResultadoEmpate(int resultadoEmpate) {
		this.resultadoEmpate = resultadoEmpate;
	}
	

	public int getNumeroRodadas() {
		return numeroRodadas;
	}

	public void setNumeroRodadas(int numeroRodadas) {
		this.numeroRodadas = numeroRodadas;
	}

	
	public String getNomeJogador0() {
		return nomeJogador0;
	}

	public void setNomeJogador0(String nomeJogador0) {
		this.nomeJogador0 = nomeJogador0;
	}

	public String getNomeJogador1() {
		return nomeJogador1;
	}

	public void setNomeJogador1(String nomeJogador1) {
		this.nomeJogador1 = nomeJogador1;
	}

	public static void main(String[] args) {
		PedraPapelTesoura jogo = new PedraPapelTesoura(7, "Roberto", "Paulo");
		jogo.executaJogo();
	}
}

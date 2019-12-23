package br.com.bbb.app.megasena;

public class Ganhador {

	private int iteracao;
	private int numeroAcertos;
	
	public Ganhador() {
		super();
	}
	public Ganhador(int iteracao, int numeroAcertos) {
		this.iteracao = iteracao;
		this.numeroAcertos = numeroAcertos;
	}
	
	public int getIteracao() {
		return iteracao;
	}
	public void setIteracao(int iteracao) {
		this.iteracao = iteracao;
	}
	public int getNumeroAcertos() {
		return numeroAcertos;
	}
	public void setNumeroAcertos(int numeroAcertos) {
		this.numeroAcertos = numeroAcertos;
	}
	
	
}

package br.com.bbb.app.megasena;

public enum TipoPremio {

	QUADRA("QUADRA", 5),
	QUINA("QUINA", 20),
	SENA("SENA", 100);
	
	private TipoPremio(String nome, float porcentagem) {
		this.nome = nome;
		this.porcentagem = porcentagem;
	}
	
	private String nome;
	private float porcentagem;
	
	public String getNome() {
		return nome;
	}
	public float getPorcentagem() {
		return porcentagem;
	}
	
}

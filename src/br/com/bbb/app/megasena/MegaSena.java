package br.com.bbb.app.megasena;

public class MegaSena {

	private Double valorPremioTotal;
	private Double valorPremioGanhador;
	
	public MegaSena() {
		//
	}

	public MegaSena(double valor) {
		this.valorPremioTotal = valor;
	}

	public Double calculaPremio(int numeroAcertos) {
		Double valorPremio = 0.0;
		
		if(numeroAcertos == 4) {
			// quadra
			valorPremio = getValorPremioTotal() * (TipoPremio.QUADRA.getPorcentagem()/100);
			
		} else if(numeroAcertos == 5) {
			// quina
			valorPremio = getValorPremioTotal() * (TipoPremio.QUINA.getPorcentagem()/100);
			
		} else if(numeroAcertos == 6) {
			// sena
			valorPremio =  getValorPremioTotal() * (TipoPremio.SENA.getPorcentagem()/100);
		}
		
		return valorPremio;
	}

	public Double getValorPremioTotal() {
		return valorPremioTotal;
	}

	public void setValorPremioTotal(Double valorPremioMega) {
		this.valorPremioTotal = valorPremioMega;
	}

	public Double getValorPremioGanhador() {
		return valorPremioGanhador;
	}

	public void setValorPremioGanhador(Double valorPremioGanhador) {
		this.valorPremioGanhador = valorPremioGanhador;
	}
	
}

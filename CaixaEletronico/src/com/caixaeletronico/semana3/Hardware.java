package com.caixaeletronico.semana3;

public interface Hardware {

	public boolean pegarNumeroDaContaCartao(int numeroCartao);
	public void entregarDinheiro();
	public void lerEnvelope(ContaCorrente contaCorrenteParaAtualizarSaldo, Double valor);
	public void listaDeContas(ContaCorrente contaCorrente);
}

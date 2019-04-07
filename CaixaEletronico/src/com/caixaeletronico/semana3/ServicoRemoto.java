package com.caixaeletronico.semana3;

public interface ServicoRemoto {

	public void  recuperarConta(int numeroConta);
	public void persistirConta();
	public void adicionaContaCorrente(ContaCorrente contaCorrente);
}

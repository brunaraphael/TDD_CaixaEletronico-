package com.caixaeletronico.semana3;
import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.List;

public class MockHardware implements Hardware {

	private List<ContaCorrente> listContasCorrentes = new ArrayList<ContaCorrente>();
	boolean erro = false;
	boolean recuperaConta = false;
	
	
	@Override
	public boolean pegarNumeroDaContaCartao(int numeroCartao) {
		for(ContaCorrente contaCorrente : listContasCorrentes) {
			if(contaCorrente.getNumeroCartao() == numeroCartao) {
				System.out.println("Conta encontrada! Nº da conta: " + String.format("%05d", listContasCorrentes.indexOf(contaCorrente)));
				recuperaConta = true;
			}
		}	
		return recuperaConta;
	}

	@Override
	public void entregarDinheiro() {
		System.out.println("Retire seu dinheiro");
	
	}


	@Override
	public void lerEnvelope(ContaCorrente contaCorrenteParaAtualizarSaldo, Double valor) {
		if (contaCorrenteParaAtualizarSaldo != null && valor != 0) {
			System.out.println("Lendo o envelope...");
		}
		
	}

	@Override
	public void listaDeContas(ContaCorrente contaCorrente) {
		if(erro) {
			throw new RuntimeException("Problema simulado pelo mockHardware");
		}
		listContasCorrentes.add(contaCorrente);		
	}
	
	public void forcarErro() {
		erro = true;
		
	}

}

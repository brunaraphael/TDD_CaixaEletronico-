package com.caixaeletronico.semana3;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CaixaEletronico {
	private Hardware hardware;
	List<ContaCorrente> listContasCorrentes = new ArrayList<ContaCorrente>();
	private ServicoRemoto servicoRemoto;
	
	
	public void adicionaNovaContaCorrente(ContaCorrente contaCorrente) {
		listContasCorrentes.add(contaCorrente);				
		if (servicoRemoto != null) {			
			servicoRemoto.adicionaContaCorrente(contaCorrente);
			System.out.println("Conta criada com sucesso!" + String.format("%05d", listContasCorrentes.size()-1));
		}
		if(hardware != null) {
			try {
				hardware.listaDeContas(contaCorrente);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
			
	}

	public List<ContaCorrente> getListaContas() {
		return listContasCorrentes;		
	}

	public String login(String usuario, String senha) {
		boolean login = false;
		String resultadoLogin = "Oh ou! Verifique seu usuario e senha!";
		
		for (ContaCorrente contaCorrente : listContasCorrentes) {
			login = (senha == contaCorrente.getDadosUsuario().get(usuario));
			if(login == true) {
				resultadoLogin = "Login realizado com sucesso!!!";
				break;	
				}
		}
		System.out.println(resultadoLogin);
		return resultadoLogin;
	}
	
	public void adicionaServicoRemoto(ServicoRemoto mockServicoRemoto) {
		this.servicoRemoto = mockServicoRemoto;		
	}

	public void adicionaHardware(Hardware mockHardware) {
		this.hardware = mockHardware;
		
	}

	public void depositar(ContaCorrente contaCorrenteParaAtualizarSaldo, Double valor) {
		hardware.lerEnvelope(contaCorrenteParaAtualizarSaldo, valor);
		for (ContaCorrente contaCorrente : listContasCorrentes) {
			if(contaCorrente.equals(contaCorrenteParaAtualizarSaldo)) {
				contaCorrente.setSaldo(valor);
				System.out.println("Deposito de " + valor + " realizado com sucesso!");
			}
		}
	}
	
	public Double saldo(ContaCorrente contaCorrenteRecebida) {
		Double saldoatual = 0.0;
		System.out.println("Pesquisando saldo atual...");
		for (ContaCorrente contaCorrente : listContasCorrentes) {
			if(contaCorrente.equals(contaCorrenteRecebida)) {
				saldoatual = contaCorrente.getSaldo();
				break;
			}
		}
		System.out.println("Seu saldo é de :" + saldoatual);
		return saldoatual;
	}

	public void sacar(ContaCorrente contaCorrenteRecebida, Double valor) {
		Double saldoatualizado = 0.0;
		for (ContaCorrente contaCorrente : listContasCorrentes) {
			if(contaCorrente.equals(contaCorrenteRecebida)) {				
				saldoatualizado = contaCorrente.getSaldo() - valor;
				if(saldoatualizado >= 0) {
				contaCorrente.setSaldo(saldoatualizado);
				System.out.println("Solicitação de saque autorizada. Valor: " + valor);
				hardware.entregarDinheiro();
				}else {
					System.out.println("Saldo insuficiente. Saldo atual: " + contaCorrente.getSaldo());
				}
				break;
			}
		}
		
	}
	
	

}

package com.caixaeletronico.semana3;
import java.util.HashMap;
import java.util.Map;

public class ContaCorrente {

	Map<String, String> dadosUsuario = new HashMap<String, String>();
	double saldo = 0;	
	int numeroCartao;

	public int getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(int numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public ContaCorrente(String usuario, String senha, int numeroCartao) {
		 dadosUsuario.put(usuario, senha);
		 this.numeroCartao = numeroCartao;
	}

	 public Map<String, String> getDadosUsuario() {
		return dadosUsuario;
	}

		public double getSaldo() {
			return saldo;
		}

		public void setSaldo(double saldo) {
			this.saldo = saldo;
		}


}

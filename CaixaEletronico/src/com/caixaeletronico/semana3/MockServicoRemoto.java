package com.caixaeletronico.semana3;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockServicoRemoto implements ServicoRemoto {

	List<ContaCorrente> listContasCorrentes = new ArrayList<ContaCorrente>();
	Map<String, String> dadosUsuario = new HashMap<String, String>();
	
	@Override
	public void recuperarConta(int numeroConta) {
		this.dadosUsuario = listContasCorrentes.get(numeroConta).getDadosUsuario();	
	}

	@Override
	public void persistirConta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adicionaContaCorrente(ContaCorrente contaCorrente) {
		listContasCorrentes.add(contaCorrente);
		
	}

	public boolean verificaRecuperacaoConta(String usuario, String senha) {
		Map<String, String> dadosUsuarioRecebido = new HashMap<String, String>();
		dadosUsuarioRecebido.put(usuario, senha);
		return dadosUsuario.equals(dadosUsuarioRecebido);
	}

}

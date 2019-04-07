package Testes;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.caixaeletronico.semana3.CaixaEletronico;
import com.caixaeletronico.semana3.ContaCorrente;
import com.caixaeletronico.semana3.MockHardware;
import com.caixaeletronico.semana3.MockServicoRemoto;

public class TestesCaixaEletronico {

	CaixaEletronico caixaEletronico;
	ContaCorrente contaCorrente0;
	ContaCorrente contaCorrente1;
	
	@BeforeEach
	public void setup() {
	caixaEletronico = new CaixaEletronico();
	contaCorrente0 = new ContaCorrente("admin","123", 123456);
	contaCorrente0 = new ContaCorrente("admin","123", 123456);
	contaCorrente1 = new ContaCorrente("nova","111", 000000);
	}
	
	@Test
	public void criarContaCorrente() {	
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente0);
		List<ContaCorrente> listContasCorrentesAtuais = caixaEletronico.getListaContas();
		assertEquals(listContasCorrentesAtuais.get(0).getDadosUsuario().get("admin"),"123");	
	}
	
	@Test
	public void criarDuasContasCorrentes() {
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente0);
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente1);
		List<ContaCorrente> listContasCorrentesAtuais = caixaEletronico.getListaContas();
		assertEquals(listContasCorrentesAtuais.get(0).getDadosUsuario().get("admin"),"123");		
		assertEquals(listContasCorrentesAtuais.get(1).getDadosUsuario().get("nova"),"111");	
	}
	
	@Test
	public void loginValido() {	
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente0);
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente1);
		String resultadoLogin = caixaEletronico.login("nova","111");
		assertEquals("Login realizado com sucesso!!!", resultadoLogin);		
	}
	@Test
	public void loginInvalido() {
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente0);
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente1);
		String resultadoLogin = caixaEletronico.login("usuarioInvalido","senhaInvalida");
		assertEquals("Oh ou! Verifique seu usuario e senha!", resultadoLogin);		
	}
	
	
	 	
	@Test
	public void recuperaConta() {
		MockServicoRemoto mockServicoRemoto = new MockServicoRemoto();	
		caixaEletronico.adicionaServicoRemoto(mockServicoRemoto);
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente0);	
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente1);
		mockServicoRemoto.recuperarConta(1);
		assertTrue(mockServicoRemoto.verificaRecuperacaoConta("nova","111"));		
	}
	
	@Test
	public void deposito() {
		MockHardware mockHardware = new MockHardware();
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente0);	
		caixaEletronico.adicionaHardware(mockHardware);
		caixaEletronico.depositar(contaCorrente0, 100.0 );
		assertEquals(caixaEletronico.saldo(contaCorrente0), 100, 0);
		
	}
	
	@Test
	public void saque() {
		MockHardware mockHardware = new MockHardware();
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente0);	
		caixaEletronico.adicionaHardware(mockHardware);
		caixaEletronico.depositar(contaCorrente0, 100.0 );
		caixaEletronico.sacar(contaCorrente0, 100.0);
		assertEquals(caixaEletronico.saldo(contaCorrente0), 0, 0);		
	}
	
	
	@Test
	public void saqueInvalido() {
		CaixaEletronico caixaEletronico = new CaixaEletronico();
		MockHardware mockHardware = new MockHardware();
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente0);	
		caixaEletronico.adicionaHardware(mockHardware);
		caixaEletronico.depositar(contaCorrente0, 100.0 );
		caixaEletronico.sacar(contaCorrente0, 1000.0);
		assertEquals(caixaEletronico.saldo(contaCorrente0), 100.0, 0);		
	}
	
	@Test
	public void leitorDeCartao() {
		MockHardware mockHardware = new MockHardware();
		caixaEletronico.adicionaHardware(mockHardware);
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente0);	
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente1);	
		assertTrue(mockHardware.pegarNumeroDaContaCartao(123456));
	}
	
	@Test
	public void notificandoComErroMockHardware() {
		MockHardware mockHardware = new MockHardware();
		caixaEletronico.adicionaHardware(mockHardware);
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente0);	
		mockHardware.forcarErro();
		caixaEletronico.adicionaNovaContaCorrente(contaCorrente1);			
		assertFalse(mockHardware.pegarNumeroDaContaCartao(111111));
	}
}


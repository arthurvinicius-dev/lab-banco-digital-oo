package main.java.br.com.bancao.domain;

import java.math.BigDecimal;

public interface IConta {
	void sacar(BigDecimal valor) throws Exception;

	void depositar(BigDecimal valor) throws Exception;

	void transferir(BigDecimal valor, IConta contaDestino) throws Exception;

	void imprimirExtrato();
}

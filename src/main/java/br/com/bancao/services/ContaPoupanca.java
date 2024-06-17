package main.java.br.com.bancao.services;

import main.java.br.com.bancao.domain.Cliente;
import main.java.br.com.bancao.domain.Conta;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(Cliente cliente) {
		super(cliente);
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Poupan�a ===");
		super.imprimirInfosComuns();
	}
}

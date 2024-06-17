package main.java.br.com.bancao.services;

import main.java.br.com.bancao.domain.Cliente;
import main.java.br.com.bancao.domain.Conta;

public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Corrente ===");
        super.imprimirInfosComuns();
    }
}

package main.java.br.com.bancao.domain;

import java.math.BigDecimal;

public abstract class Conta implements IConta {
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected BigDecimal saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.saldo = BigDecimal.ZERO;
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns() {
        System.out.printf("Titular: %s%nAgencia: %d%nNumero: %d%nSaldo: %.2f%n", cliente.getNome(), agencia, numero, saldo);
    }

    @Override
    public void sacar(BigDecimal valor) throws Exception {
        if (saldo.compareTo(valor) >= 0) {
            saldo = saldo.subtract(valor);
        } else {
            throw new Exception("Saldo insuficiente!");
        }
    }

    @Override
    public void depositar(BigDecimal valor) {
        saldo = saldo.add(valor);
    }

    @Override
    public void transferir(BigDecimal valor, IConta contaDestino) throws Exception {
        this.sacar(valor);
        contaDestino.depositar(valor);
    }
}

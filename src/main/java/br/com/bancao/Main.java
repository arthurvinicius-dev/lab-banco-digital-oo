package main.java.br.com.bancao;

import java.math.BigDecimal;
import java.time.LocalDate;

import main.java.br.com.bancao.domain.Cliente;
import main.java.br.com.bancao.domain.Conta;
import main.java.br.com.bancao.services.ClienteService;
import main.java.br.com.bancao.services.ContaCorrente;
import main.java.br.com.bancao.services.ContaPoupanca;

public class Main {
    public static void main(String[] args) throws Exception {
        ClienteService clienteService = new ClienteService();

        Cliente venilton = new Cliente("Venilton silva",  LocalDate.of(2003, 5, 2),
        "439.147.970-29", "Rua A, 123", "(88) 994587888");
        Cliente maria = new Cliente("Maria", LocalDate.of(1992, 5, 20), "987.654.321-00", "Avenida Exemplo, 456", "(88) 994587858");
        // OBS. esse CPF foi gerador por um Gerador de CPF .
        // https://www.4devs.com.br/gerador_de_cpf

        // Adicionar clientes
        System.out.println("Adicionando : " + clienteService.adicionarCliente(venilton));
        System.out.println("Adicionando : " + clienteService.adicionarCliente(maria));

        // Buscar cliente por CPF
        Cliente clienteEncontrado = clienteService.buscarClientePorCpf("439.147.970-29");
        System.out.println("Cliente encontrado: " + clienteEncontrado);

        // Alterar cliente
        venilton.setEndereco("Rua Nova, 123");
        System.out.println("Alterando Venilton: " + clienteService.alterarCliente(venilton));

        // Remover cliente
        System.out.println("Removendo Maria: " + clienteService.removerCliente("987.654.321-00"));

        // Listar todos os clientes
        System.out.println("Todos os clientes: " + clienteService.getTodosClientes());

        // Testar contas
        Conta cc = new ContaCorrente(venilton);
        Conta poupanca = new ContaPoupanca(venilton);

        cc.depositar(new BigDecimal(100));
        cc.sacar(new BigDecimal(30));
        cc.transferir(new BigDecimal(50), poupanca);

        cc.imprimirExtrato();
        poupanca.imprimirExtrato();
    }
}

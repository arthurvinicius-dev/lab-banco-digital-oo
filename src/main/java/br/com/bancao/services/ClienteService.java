package main.java.br.com.bancao.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.java.br.com.bancao.domain.Cliente;
import main.java.br.com.bancao.util.ValidacaoClienteUtil;

public class ClienteService {
    private List<Cliente> clientes;

    public ClienteService() {
        this.clientes = new ArrayList<>();
    }

    public boolean adicionarCliente(Cliente cliente) {
        if (cliente == null || cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            System.out.println("Erro: CPF não pode ser nulo ou vazio.");
            return false;
        }
        if (clientes.stream().anyMatch(c -> c.getCpf().equals(cliente.getCpf()))) {
            System.out.println("Erro: Cliente com CPF já cadastrado.");
            return false;
        }
        boolean isValid = validarCampos(cliente);
        if (isValid) {
            clientes.add(cliente);
            System.out.println("Cliente adicionado com sucesso: " + cliente);
        } else {
            System.out.println("Erro ao adicionar cliente. Verifique os campos.");
        }
        return isValid;
    }

    public boolean alterarCliente(Cliente cliente) {
        if (cliente == null || cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            System.out.println("Erro: CPF não pode ser nulo ou vazio.");
            return false;
        }
        Optional<Cliente> clienteExistente = clientes.stream()
                .filter(c -> c.getCpf().equals(cliente.getCpf()))
                .findFirst();
        if (clienteExistente.isPresent() && validarCampos(cliente)) {
            clientes.remove(clienteExistente.get());
            clientes.add(cliente);
            System.out.println("Cliente alterado com sucesso: " + cliente);
            return true;
        }
        System.out.println("Erro ao alterar cliente. Verifique os campos.");
        return false;
    }

    public boolean removerCliente(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            System.out.println("Erro: CPF não pode ser nulo ou vazio.");
            return false;
        }
        return clientes.removeIf(cliente -> cliente.getCpf().equals(cpf));
    }

    public Cliente buscarClientePorCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            System.out.println("Erro: CPF não pode ser nulo ou vazio.");
            return null;
        }
        return clientes.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public List<Cliente> getTodosClientes() {
        return new ArrayList<>(clientes);
    }

    private boolean validarCampos(Cliente cliente) {
        boolean isValid = true;
        if (!ValidacaoClienteUtil.validarNome(cliente.getNome())) {
            System.out.println("Erro: Nome inválido.");
            isValid = false;
        }
        if (!ValidacaoClienteUtil.validarIdade(cliente.getDataNascimento())) {
            System.out.println("Erro: Idade inválida.");
            isValid = false;
        }
        if (!ValidacaoClienteUtil.validarCpf(cliente.getCpf())) {
            System.out.println("Erro: CPF inválido.");
            isValid = false;
        }
        if (cliente.getEndereco() == null || cliente.getEndereco().isEmpty()) {
            System.out.println("Erro: Endereço não pode ser nulo ou vazio.");
            isValid = false;
        }
        if (!ValidacaoClienteUtil.validarTelefone(cliente.getTelefone())) {
            System.out.println("Erro: Telefone inválido.");
            isValid = false;
        }
        return isValid;
    }
}

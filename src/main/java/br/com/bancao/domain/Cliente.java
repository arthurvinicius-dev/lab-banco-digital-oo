package main.java.br.com.bancao.domain;

import java.time.LocalDate;

public class Cliente {
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String endereco;
    private String telefone;

    public Cliente(String nome, LocalDate dataNascimento, String cpf, String endereco, String telefone) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("{")
                .append("nome = ").append(nome)
                .append(", data de nascimento = ").append(dataNascimento)
                .append(", cpf = ").append(cpf)
                .append(", endereco = ").append(endereco)
                .append(", telefone = ").append(telefone)
                .append('}')
                .toString();
    }
}

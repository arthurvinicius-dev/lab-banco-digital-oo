package main.java.br.com.bancao.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacaoClienteUtil {
    public static boolean validarNome(String nome) {
        String regex = "^[A-Za-zÀ-ú]+(\\s+[A-Za-zÀ-ú]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nome);
        return matcher.matches();
    }

    public static boolean validarIdade(LocalDate dataNascimento) {
        LocalDate dataAtual = LocalDate.now();
        int idade = Period.between(dataNascimento, dataAtual).getYears();
        return idade >= 0 && idade <= 100;
    }

    public static String formatarData(LocalDate dataNascimento) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataNascimento.format(formatador);
    }

    public static boolean validarCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        int[] multiplicadoresPrimeiroDigito = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] multiplicadoresSegundoDigito = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        return validarDigito(cpf, multiplicadoresPrimeiroDigito) &&
               validarDigito(cpf, multiplicadoresSegundoDigito);
    }

    private static boolean validarDigito(String cpf, int[] multiplicadores) {
        int soma = 0;
        for (int i = 0; i < multiplicadores.length; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * multiplicadores[i];
        }
        int resto = soma % 11;
        int digitoVerificador = resto < 2 ? 0 : 11 - resto;
        return digitoVerificador == Character.getNumericValue(cpf.charAt(multiplicadores.length));
    }

    public static String imprimirCpf(String cpf) {
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static boolean validarTelefone(String telefone) {
        String apenasDigitos = telefone.replaceAll("\\D", "");
        if (apenasDigitos.length() < 10 || apenasDigitos.length() > 11) return false;
        if (apenasDigitos.length() == 11 && apenasDigitos.charAt(2) != '9') return false;
        if (apenasDigitos.chars().distinct().count() == 1) return false;
        int ddd = Integer.parseInt(apenasDigitos.substring(0, 2));
        return Set.of(11, 12, 13, 14, 15, 16, 17, 18, 19,
                      21, 22, 24, 27, 28, 31, 32, 33, 34,
                      35, 37, 38, 41, 42, 43, 44, 45, 46,
                      47, 48, 49, 51, 53, 54, 55, 61, 62,
                      64, 63, 65, 66, 67, 68, 69, 71, 73,
                      74, 75, 77, 79, 81, 82, 83, 84, 85,
                      86, 87, 88, 89, 91, 92, 93, 94, 95,
                      96, 97, 98, 99).contains(ddd);
    }
}

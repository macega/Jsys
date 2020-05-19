package br.com.testes;

import java.util.Optional;

public class OptionalExemploBasico {

    public static void main(String[] args) {
        Optional<String> linguagem = Optional.of("JAVA");
        String respostaPreenchida = "Sim";
        String respostaNula = null;

        System.out.println("Optional Não Vazio:" + linguagem);
        System.out.println("Optional Não Vazio: Obtem o valor: " + linguagem.get());
        System.out.println("Optional Vazio: " + Optional.empty());

        System.out.println("Chamada do método ofNullable() em Optional Não Vazio: " + Optional.ofNullable(respostaPreenchida));
        System.out.println("Chamada do método ofNullable() em Optional Vazio: " + Optional.ofNullable(respostaNula));

        // Ocorre uma java.lang.NullPointerException na linha abaixo
        System.out.println("Chamada do método of() Optional Não Vazio: " + Optional.of(respostaNula));
    }
}

package br.com.testes;

import br.sql.util.Validar;
import java.util.Optional;

/**
 *
 * @author julia
 */
public class Main {

    public static void main(String[] args) {
        
        System.out.println("java inicio");

        Optional<String> linguagem = Optional.of("JAVA");
        String respostaPreenchida = "Sim";
        String respostaNula = null;

        if (Validar.present(respostaNula).isPresent()) {
            System.out.println(" entrou!!! ");
        }
        
        System.out.println("java fim");
        
    }

}

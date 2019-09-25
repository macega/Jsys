package br.sql.util;

/**
 *
 * @author Juliano Alves Medina
 */
public class Matrizes {
    
    /**
     * estrutura dos dados tpEvento, descEvento, xjust(0 falso, 1 verdadeiro)
     * @return 
     */
    @SuppressWarnings("unchecked")
    public static MatrizDinamica<String> tiposEventos() {
        MatrizDinamica tiposEventos = new MatrizDinamica();
        tiposEventos.set(0, 0, "210200");
        tiposEventos.set(0, 1, "Confirmacao da Operacao");
        tiposEventos.set(0, 2, "0");
        
        tiposEventos.set(1, 0, "210210");
        tiposEventos.set(1, 1, "Ciencia da Operacao");
        tiposEventos.set(1, 2, "0");
        
        tiposEventos.set(2, 0, "210220");
        tiposEventos.set(2, 1, "Desconhecimento da Operacao");
        tiposEventos.set(2, 2, "0");
        
        tiposEventos.set(3, 0, "210240");
        tiposEventos.set(3, 1, "Operacao nao Realizada");
        tiposEventos.set(3, 2, "1");
        
        return tiposEventos;
    }
    
}

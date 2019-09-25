package br.sql.acesso;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Juliano Alves Medina
 * @param <T>
 */
public class MatrizDinamica<T> {

    private final Map<Integer, Map<Integer, T>> elementos = new HashMap<>();

    public void set(int linha, int coluna, T elemento) {
        Map<Integer, T> colunas = getColunas(linha);
        Integer chave = coluna;
        if (elemento != null) {
            colunas.put(chave, elemento);
        } else {
            colunas.remove(chave);
        }
    }

    public int getLengthLinha() {
        return elementos.size();
    }

    public int getLengthColuna(int linha) {
        return getColunas(linha).size();
    }

    public T get(int linha, int coluna) {
        Map<Integer, T> colunas = getColunas(linha);
        Integer chave = coluna;
        T elemento = colunas.get(chave);
        return elemento;
    }

    private Map<Integer, T> getColunas(int linha) {
        int chave = linha;
        Map<Integer, T> colunas = elementos.get(chave);
        if (colunas == null) {
            colunas = new HashMap<>();
            elementos.put(chave, colunas);
        }
        return colunas;
    }

}

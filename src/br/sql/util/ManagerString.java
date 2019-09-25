package br.sql.util;

import br.sql.log.Log;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Juliano alves medina
 */
public class ManagerString {
    
    /**
     *
     * @param value valor a verificar se esta null
     * @param retorno se nulo vai retornar esse valor
     * @return
     */
    public static String noNull(String value, String retorno) {
        return (value == null | "".equals(value)) ? retorno : value;
    }

    /**
     * CEP - resultado: 81580-200 format("#####-###", "81580200"); CPF -
     * resultado 012.345.699-01 format("###.###.###-##", "01234569905"); CNPJ -
     * resultado: 01.234.569/9052-34 format("##.###.###/####-##",
     * "01234569905234");
     *
     * @param pattern
     * @param value
     * @return
     */
    public static String format(String pattern, Object value) {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param text
     * @param length
     * @return
     */
    public static String Left(String text, int length) {
        return text.length() > length ? text.substring(0, length) : text;
    }

    /**
     * corta o texte do fim para a inicio
     *
     * @param text
     * @param length
     * @return
     */
    public static String Right(String text, int length) {
        return text.length() > length ? text.substring(text.length() - length, text.length()) : text;
    }

    /**
     *
     * @param text
     * @param start
     * @param end
     * @return
     */
    public static String Mid(String text, int start, int end) {
        return text.substring(start, end);
    }

    /**
     *
     * @param text
     * @param start
     * @return
     */
    public static String Mid(String text, int start) {
        return text.substring(start, text.length());
    }

    /**
     *
     * @param CpfCnpj
     * @return
     */
    public static String RemoveFormatoCpfCnpj(String CpfCnpj) {
        CpfCnpj = CpfCnpj.replace(".", "").replace("-", "").replace("/", "");
        return CpfCnpj;
    }

    /**
     *
     * @param cep
     * @return
     */
    public static String RemoveFormatoCep(String cep) {
        return RemoveFormato(cep); //cep.replaceAll("[^0-9]", "");
    }

    /**
     *
     * @param valor recebe uma string com uma fomato ex: 78975-123
     * @return retona a String somente com os mumeros ex: 78975123
     */
    public static String RemoveFormato(String valor) {
        return valor.replaceAll("[^0-9]", "");
    }

    private static String espacosL(String valor) {
        StringBuilder v = new StringBuilder(valor);
        return v.append("                                                                                                             ").toString();
    }

    private static String espacosR(String valor) {
        StringBuilder e = new StringBuilder("                                                                                                             ");
        return e.append(valor).toString();
    }

    private static String rachitagueR(String valor) {
        StringBuilder e = new StringBuilder(" ######################################################################################################################################### ");
        return e.append(valor).toString();
    }

    private static String rachitagueL(String valor) {
        StringBuilder v = new StringBuilder(valor);
        return v.append(" ######################################################################################################################################### ").toString();
    }

    /**
     *
     * @param valor passar um string que vai receber os zerros
     * @param tamanho tamanho que vai ficar a string
     * @return retorna a strig com 000 no iniciao ex: zeros("jose") = 000jose
     */
    public static String zeros(String valor, int tamanho) {
        StringBuilder zeros = new StringBuilder("00000000000000000000000000");
        return Right(zeros.append(valor).toString(), tamanho);
    }

    /**
     *
     * @param valor passar o codigo do produto
     * @return retorna o valor com os 0000 no inicio Exemplo: 208 = 0000208
     */
    public static String idProduto(String valor) {
        return zeros(valor, 7);
    }

    /**
     *
     * @param valor passar o codigo do orçamento
     * @return retorna o valor com os 0000 no inicio exemplo: 12 = 000012
     */
    public static String idOrcamento(String valor) {
        return zeros(valor, 6);
    }

    /**
     *
     * @param valor passar o codigo do funcionario
     * @return retorna o valor com os 0000 no inicio exemplo: 12 = 000012
     */
    public static String idFuncionario(String valor) {
        return zeros(valor, 6);
    }

    /**
     *
     * @param valor passar o codigo do funcionario
     * @return retorna o valor com os 0000 no inicio exemplo: 12 = 000012
     */
    public static String idFuncionario(Integer valor) {
        return zeros(valor.toString(), 6);
    }

    /**
     *
     * @param valor
     * @param tamanho
     * @return
     */
    public static String espacosDireita(String valor, int tamanho) {
        return Left(espacosL(valor), tamanho);
    }

    /**
     *
     * @param valor
     * @param tamanho
     * @return
     */
    public static String espacosEsquerda(String valor, int tamanho) {
        return Right(espacosR(valor), tamanho);
    }

    /**
     *
     * @param valor passa a string que vai recerber ###
     * @param tamanho tamanho da string desejada
     * @return retorma a string quem ## no final da string 45,00###
     */
    public static String rachitagueDireita(String valor, int tamanho) {
        return Left(rachitagueL(valor), tamanho);
    }

    /**
     *
     * @param valor passar um string que vai receber ### no inicio da string
     * @param tamanho tamanho da striting desejada
     * @return retorna a string com ## no inicina da strina ###45,00
     */
    public static String rachitagueEsquerda(String valor, int tamanho) {
        return Right(rachitagueR(valor), tamanho);
    }

    public static String removeZerosEsquerda(String valor) {
        try {
            return String.valueOf(Integer.parseInt(valor.replaceAll("\\.", "").replaceAll(",", valor)));
        } catch (NumberFormatException ignore) {
            return null;
        }

    }

    public static String lerXML(StringBuilder caminhoArquivo) {
        return lerXML(caminhoArquivo.toString());
    }

    public static String lerXML(String caminhoArquivo) {
        try {
            String linha = null;
            StringBuilder xml = new StringBuilder();

            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(caminhoArquivo), "ISO-8859-1")) //"ISO-8859-1" , "UTF-8"
                    ) {
                while ((linha = in.readLine()) != null) {
                    xml.append(linha);
                }
            }
            return xml.toString();
        } catch (IOException e) {
            Log.registraErro("ManagerString", "lerXML", e);
            return null;
        }
    }

    public static String normalizeXML(String xml) {
        if ((xml != null) && (!"".equals(xml))) {
            xml = xml.replaceAll("\\r\\n", "");
            xml = xml.replaceAll(" standalone=\"no\"", "");
        }
        return xml;
    }

    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    /**
     * remove acentuação de palavras da string passada no parâmetro
     *
     * @param stringAcentuada
     * @return retorna a String sem acentuação
     */
    public static String normalizar(String stringAcentuada) {
        return Normalizer.normalize(stringAcentuada, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    /**
     *
     * @param str
     * @return a String sem a porra dos acentos e mais viadagens
     */
    public static String removeAcentos(String str) {
        CharSequence cs = new StringBuilder(str == null ? "" : str);
        return Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}

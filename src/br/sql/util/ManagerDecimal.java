package br.sql.util;

import br.sql.log.Log;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author Juliano Alves Medina
 */
public class ManagerDecimal {

    /**
     * Localizacao para formatacao de lingua.
     */
    public static Locale LOCAL = Locale.getDefault();

    public static final String TIPO_1 = "R$ #,##0.00";
    public static final String TIPO_2 = "#,##0.00";
    public static final String TIPO_3 = "#,##0";
    public static final String TIPO_4 = "##,#0%";

    private static String formatar(Object valor, Locale local) {
        java.text.NumberFormat df = NumberFormat.getCurrencyInstance(local);
        try {
            if (valor instanceof BigDecimal || valor instanceof Double) {
                return df.format(valor);
            }
        } catch (Exception e) {
            Log.registraErro(ManagerDecimal.class, "formata", e);
        }
        return df.format(0);
    }

    private static String formatar(Object valor, String formato) {
        java.text.DecimalFormat df = new java.text.DecimalFormat(formato);
        try {
            if (valor instanceof BigDecimal || valor instanceof Double) {
                return df.format(valor);
            }
        } catch (Exception e) {
            Log.registraErro(ManagerDecimal.class, "formata", e);
        }
        return df.format(0);
    }

    /**
     *
     * @param valor
     * @return uma string fortamada R$ #,##0.00
     */
    public static String converter(BigDecimal valor) {
        return formatar(valor, TIPO_1);
    }

    /**
     *
     * @param valor
     * @return uma string fortamada R$ #,##0.00
     */
    public static String converter(Double valor) {
        return formatar(valor, TIPO_1);
    }

    /**
     *
     * @param valor
     * @param formato tipo "R$ #,##0.00" ou "#,##0.00" ou "#,##0"
     * @return uma string fortamada com seu formato
     */
    public static String converter(Double valor, String formato) {
        return formatar(valor, formato);
    }

    /**
     *
     * @param valor
     * @param formato tipo "R$ #,##0.00" ou "#,##0.00" ou "#,##0"
     * @return uma string fortamada com seu formato
     */
    public static String converter(Object valor, String formato) {
        return formatar(valor, formato);
    }

    /**
     *
     * @param valor
     * @param formato tipo "R$ #,##0.00" ou "#,##0.00" ou "#,##0"
     * @return uma string fortamada com seu formato
     */
    public static String converter(BigDecimal valor, String formato) {
        return formatar(valor, formato);
    }

    /**
     *
     * @param valor
     * @param local Locale("pt", "BR"), new Locale("en", "US"), new Locale("fr",
     * "CA"), new Locale("en", "GB"), new Locale("ru")
     * @return uma string fortamada com seu formato
     */
    public static String converter(BigDecimal valor, Locale local) {
        return formatar(valor, local);
    }

    /**
     *
     * @param valor
     * @return uma string com o formato 22.00
     */
    public static String converterXmlNFe(BigDecimal valor) {
        return formatar(valor, java.util.Locale.US).replace("$", "").replace(",", "");
    }

    /**
     *
     * @param valor
     * @return recebe uma strig e retonna um double (resolve o proplema do ponto
     * e virgula)
     */
    public static Double StringToDouble(String valor) {
        if (valor.isEmpty()) {
            return 0.0;
        }
        valor = valor.replace("R", "").replace("$", "").replace(" ", "");
        if (Validar.isDouble(valor)) {
            return Double.valueOf(valor);
        }
//        int virgulas = 0;
//        for (int i = 0; i < valor.length(); i++) {
//            if (",".equals(String.valueOf(valor.charAt(i)))) {
//                virgulas++;
//            }
//        }
//
//        while (virgulas >= 2) {
//
//            virgulas--;
//        }
        try {
            Double d = (Double) getDecimalFormat(TIPO_2).parseObject(valor);
            return d;
        } catch (Exception ignore) {
        }
        try {
            if (!Validar.isNumber(valor)) {
                valor = valor.replace(".", "").replace(",", ".");
            }
            return Double.valueOf(valor);
        } catch (Exception ignore) {
        }
        try {
            Double d = (Double) getDecimalFormat(TIPO_1).parseObject(valor);
            return d;
        } catch (Exception ignore) {
        }
        try {
            Double d = (Double) getDecimalFormat(TIPO_3).parseObject(valor);
            return d;
        } catch (Exception ignore) {
        }
        try {
            Double d = (Double) getDecimalFormat(TIPO_4).parseObject(valor);
            return d;
        } catch (Exception ignore) {
        }
        return 0.0;
    }

    private static DecimalFormat getDecimalFormat(String formato) {
        DecimalFormat df = new DecimalFormat(formato, new DecimalFormatSymbols(new Locale("pt", "BR")));
        return df;
    }

    /**
     *
     * @param valor
     * @return um BigDecimal
     */
    public static BigDecimal StringToBigDecimal(String valor) throws NumberFormatException {
        if (!Validar.isNumber(valor)) {
            valor = valor.replace(".", "");
            valor = valor.replace("R", "");
            valor = valor.replace("$", "");
            valor = valor.replace(",", ".");
        }
        if (!Validar.isNull(valor)
                & !valor.isEmpty()
                & !Objects.equals(valor, "-")
                & Validar.isDouble(valor)) {
            return new BigDecimal(valor);
        }
        return null;
    }

    public static String valorExtenso(Double valor) {
        Extenso extenso = new Extenso(valor);
        return extenso.toString();
    }

    public static String valorExtenso(BigDecimal valor) {
        Extenso extenso = new Extenso(valor);
        return extenso.toString();
    }

    /**
     * Metodo que faz a formatacao de numeros com inteiros e fracoes
     *
     * @param valor o valor a ser formatado
     * @param inteiros o minimo de inteiros, que serao completados com ZEROS se
     * preciso
     * @param decimal o minimo de decimais, que serao completados com ZEROS se
     * preciso
     * @param grupo se sera colocado separador de grupo de milhar
     * @return uma String com o numero formatado
     */
    public static String formataNumero(double valor, int inteiros, int decimal, boolean grupo) {
        NumberFormat nf = NumberFormat.getIntegerInstance(LOCAL);
        nf.setMinimumIntegerDigits(inteiros);
        nf.setMinimumFractionDigits(decimal);
        nf.setMaximumFractionDigits(decimal);
        nf.setGroupingUsed(grupo);
        return nf.format(valor);
    }

    /**
     *
     * @param value
     * @return retorna zero se o valor for nulo
     */
    public static BigDecimal noNull(BigDecimal value) {
        return (value == null ? BigDecimal.ZERO : value);
    }
}

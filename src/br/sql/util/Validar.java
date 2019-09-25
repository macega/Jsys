package br.sql.util;

import br.gov.sintegra.ie.InscricaoEstadual;
import br.gov.sintegra.ie.InscricaoEstadualFactory;
import br.sql.bean.Usuarios;
import br.sql.util.Criptografia.Security;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.brazilutils.br.cpfcnpj.CpfCnpj;

/**
 *
 * @author Juliano Alves Medina
 */
public class Validar {

    public static boolean isNull(Object... args) {
        for (Object o : args) {
            return o == null;
        }
        return true;
    }

    public static boolean isNullOrWhite(String... args) {
        for (String o : args) {
            return o == null || o.isEmpty() || "".equals(o.trim());
        }
        return true;
    }

    public static boolean isNotNull(Object... args) {
        for (Object o : args) {
            return o != null;
        }
        return false;
    }

    public static boolean isNotNullOrWhite(String... args) {
        for (String o : args) {
            if (o == null) {
                return false;
            }
            if (o.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param data
     * @return 1 se a data e valida 0 se a data e invalida -1 se a data e nula
     */
    public static int data(Date data) {
        try {
            if (new Date(253402228800000L).after(data)) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     *
     * @param ususario
     * @param senha
     * @return Usuarios
     */
    public static Usuarios Senha(String ususario, char[] senha) {
        try {
            Map<Object, Object> filtro = new HashMap<>();
            Security s = new Security();
            String password = s.getHexadecimal(String.valueOf(senha), "SHA-256", "UTF-8");
            filtro.put("usuario", ususario);
            filtro.put("password", password);
            filtro.put("bloqueado", false);
            return (Usuarios) br.sql.util.Retorna.findOneResult("Usuarios.findByAcesso", filtro);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(Validar.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Exemplo: verificaInt("456") retona verdadeiro verificaInt("456a") retorna
     * falso
     *
     * @param valor
     * @return vai retornar verdadeiro se conter somente numeros na String
     * passada
     *
     */
    public static Boolean Int(String valor) {
        try {
            Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Boolean isDouble(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     *
     * @param valor, pasar o codigo do produto
     * @return verdadeiro ou falso caso o produto nao seja emcontrado
     */
    public static Boolean Produto(String valor) {
        Map<Object, Object> filtro = new HashMap<>();
        filtro.put("codigoBarra", valor);
        return !br.sql.util.Retorna.findList("JsysIdProdutos.findByCodigoBarra", filtro).isEmpty();
    }

    public static Boolean Produto(Integer valor) {
        Map<Object, Object> filtro = new HashMap<>();
        filtro.put("codigoBarra", valor.toString());
        return !br.sql.util.Retorna.findList("JsysIdProdutos.findByCodigoBarra", filtro).isEmpty();
    }

//    public static boolean isNumerico(String campo) {
//        return campo.matches("[0-9]{" + campo.length() + "}");
//    }
    
    public static boolean isNumber(String campo) {
        for (int i = 0; i < campo.length(); i++) {
            if (!Character.isDigit(campo.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param valor
     * @return verdadeio ou falso
     */
    public static Boolean Funcionario(Integer valor) {
        Map<Object, Object> filtro = new HashMap<>();
        filtro.put("idFuncionario", valor);
        return !br.sql.util.Retorna.findList("JsysFuncionarios.findByIdFuncionario", filtro).isEmpty();
    }

    /**
     * dfuncionario
     *
     * @param valor
     * @return verdadeio ou falso
     */
    public static Boolean Cliente(Integer valor) {
        Map<Object, Object> filtro = new HashMap<>();
        filtro.put("id", ManagerString.idFuncionario(valor));
        return !br.sql.util.Retorna.findList("JsysClientes.findByIdClientes", filtro).isEmpty();
    }

    /**
     *
     * @param valor
     * @return verdadeio ou falso
     */
    public static Boolean Venda(Integer valor) {
        Map<Object, Object> filtro = new HashMap<>();
        filtro.put("idOrcamento", valor);
        return !br.sql.util.Retorna.findList("JsysOrcamento.findByFechado", filtro).isEmpty();
    }
    
    /**
     *
     * @param valor
     * @return verdadeio ou falso
     */
    public static Boolean NFe(String valor) {
        Map<Object, Object> filtro = new HashMap<>();
        filtro.put("venda", valor);
        return !br.sql.util.Retorna.findList("NFe.findByVenda", filtro).isEmpty();
    }

    /**
     *
     * @param valor deve ser passado com ou sem formato EX:
     * Validar.cpfCnpj("05.894.211/0001-39") ou
     * Validar.cpfCnpj("05894211000139")
     * @return verdadeiro se o valor for um CPF ou CNPJ valido
     */
    public static boolean cpfCnpj(String valor) {
        valor = ManagerString.RemoveFormato(valor); 
        if ("11111111111".equals(valor)
                | "22222222222".equals(valor)
                | "33333333333".equals(valor)
                | "44444444444".equals(valor)
                | "55555555555".equals(valor)
                | "66666666666".equals(valor)
                | "77777777777".equals(valor)
                | "88888888888".equals(valor)
                | "99999999999".equals(valor)
                | "00000000000".equals(valor)
                | "11111111111111".equals(valor)
                | "22222222222222".equals(valor)
                | "33333333333333".equals(valor)
                | "44444444444444".equals(valor)
                | "55555555555555".equals(valor)
                | "66666666666666".equals(valor)
                | "77777777777777".equals(valor)
                | "88888888888888".equals(valor)
                | "99999999999999".equals(valor)
                | "00000000000000".equals(valor)) {
            return false;
        }
        CpfCnpj validate = new CpfCnpj();
        validate.setCpfCnpj(valor);
        return validate.isValid();
    }

    /**
     *
     * @param valor deve ser passado com ou sem formato EX:
     * Validar.cpfCnpj("05.894.211/0001-39") ou
     * Validar.cpfCnpj("05894211000139")
     * @return verdadeiro se o valor passado for um CNPJ
     */
    public static boolean cnpj(String valor) {
        CpfCnpj validate = new CpfCnpj();
        validate.setCpfCnpj(valor);
        return validate.isCnpj();
    }

    public static boolean PIS(String strPIS) {
        char i, j, somatorio = 0;
        char chDigitoVerificador;
        char chPISAux;

        try {
            for (i = 0, j = 4; j >= 2; i++, j--) {
                somatorio += ((strPIS.charAt(i) - 0x30) * j);
            }
            for (j = 9; j >= 2; i++, j--) {
                somatorio += ((strPIS.charAt(i) - 0x30) * j);
            }
            if ((somatorio % 11) < 2) {
                chDigitoVerificador = 0;
            } else {
                chDigitoVerificador = (char) (11 - (somatorio % 11));
            }
            chPISAux = (char) (chDigitoVerificador + '0');
            return strPIS.charAt(11) == chPISAux;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean IE(String estado, String ie) {
        try {
            if (ie.equals("ISENTO")) {
                return true;
            } else {
                InscricaoEstadual validar = InscricaoEstadualFactory.getInstance(estado);
                return validar.validar(ie);
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static String gerarChaveDeAcessoNfe(String chaveSemDigito) throws InputMismatchException {

        // UMA CHAVE DE ACESSO DE NF-E TEM 44 DIGITOS, ENTAO O CALCULO SE DÁ COM OS 43 ANTERIORES
        if (chaveSemDigito.length() != 43) {
            throw new InputMismatchException("Chave Invalida possui " + chaveSemDigito.length());
        }
        int[] aux = new int[chaveSemDigito.length()];
        int variavel = 2;
        int total = 0;
        int dv = 0;
        for (int i = aux.length - 1; i >= 0; i--) {
            aux[i] = Integer.parseInt("" + chaveSemDigito.charAt(i));
            aux[i] = aux[i] * variavel;
            variavel++;
            if (variavel > 9) {
                variavel = 2;
            }
            total += aux[i];
        }

        if (total == 0 || total == 1) {
            dv = 0;
        } else {
            dv = 11 - (total % 11);
        }

        String chaveFinal = (chaveSemDigito + dv);
        return chaveFinal;
    }

    public static boolean isValidCodigoBarra(String codigo) {
        if (isNumber(codigo) && (codigo.length() == 8
                || codigo.length() == 12
                || codigo.length() == 13
                || codigo.length() == 14)) {
            int factor = 3;
            int sum = 0;

            for (int i = codigo.length() - 1; i > 0; --i) {
                int mult = Integer.valueOf(codigo.substring(i - 1, i));
                sum = sum + mult * factor;
                factor = 4 - factor;
            }

            int cc = ((1000 - sum) % 10);
            int ca = Integer.valueOf(codigo.substring(codigo.length() - 1));

            if (cc == ca) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }
}

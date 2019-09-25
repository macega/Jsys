package br.sql.nfe.xml;

import br.sql.bean.JsysParametros;
import br.sql.log.Log;
import br.sql.util.ManagerData;
import br.sql.util.Retorna;
import java.util.Date;

/**
 *
 * @author Juliano Alves Medina
 */
public class ChaveAcessoNFe {

    private final String cUF; // Código da UF do emitente do Documento Fiscal.
    private final String dataAAMM; // Ano e Mês de emissão da NF-e.
    private final String cnpjCpf; // CNPJ do emitente.
    private final String mod; // Modelo do Documento Fiscal.
    private final String serie; // Série do Documento Fiscal.
    private final String tpEmis; // Forma de emissão da NF-e.
    private final String nNF; // Número do Documento Fiscal.
    private final String cNF; // Código Numérico que compõe a Chave de Acesso.
    private final JsysParametros par = Retorna.JsysParametros();

    /**
     *
     * @param data Ano e Mês de emissão da NF-e.
     * @param mod Modelo do Documento Fiscal.
     * @param serie Série do Documento Fiscal.
     * @param tpEmis Forma de emissão da NF-e 1-Emissão Normal 2-Contingência em
     * Formulário de Segurança 3-Contingência SCAN (desativado) 4-Contingência
     * EPEC 5-Contingência em Formulário de Segurança FS-DA 6-Contingência
     * SVC-AN 7-Contingência SVC-RS
     * @param nNF Número do Documento Fiscal.
     */
    public ChaveAcessoNFe(Date data, String mod, String serie, String tpEmis, String nNF) {
        String dv = String.valueOf((int) (Math.random() * 99999999));
        String uf = String.valueOf(par.getCodMunicipio()).substring(0, 2);
        String cnpj = par.getCnpj();
        this.cUF = uf;
        this.dataAAMM = ManagerData.convertDate(data, "yyMM");
        this.cnpjCpf = cnpj;
        this.mod = mod;
        this.serie = serie;
        this.tpEmis = tpEmis;
        this.nNF = nNF;
        this.cNF = dv; //Código Numérico que compõe a Chave de Acesso.
    }

    public String getChaveAcessoNFe() {
        try {
            StringBuilder chave = new StringBuilder();
            chave.append(lpadTo(cUF, 2, '0'));
            chave.append(lpadTo(dataAAMM, 4, '0'));
            chave.append(lpadTo(cnpjCpf.replaceAll("\\D", ""), 14, '0'));
            chave.append(lpadTo(mod, 2, '0'));
            chave.append(lpadTo(serie, 3, '0'));
            chave.append(lpadTo(String.valueOf(nNF), 9, '0'));
            chave.append(lpadTo(tpEmis, 1, '0'));
            chave.append(lpadTo(cNF, 8, '0'));
            chave.append(modulo11(chave.toString()));
            chave.insert(0, "NFe");
            return chave.toString();
        } catch (Exception e) {
            Log.registraErro(this.getClass().getName(), "getChaveAcessoNFe", e);
            return null;
        }
    }

    public static int modulo11(String chave) {
        int total = 0;
        int peso = 2;

        for (int i = 0; i < chave.length(); i++) {
            total += (chave.charAt((chave.length() - 1) - i) - '0') * peso;
            peso++;
            if (peso == 10) {
                peso = 2;
            }
        }
        int resto = total % 11;
        return (resto == 0 || resto == 1) ? 0 : (11 - resto);
    }

    public static String lpadTo(String input, int width, char ch) {
        StringBuilder sb = new StringBuilder(input.trim());
        while (sb.length() < width) {
            sb.insert(0, ch);
        }
        String strPad = sb.toString();

        if (strPad.length() > width) {
            strPad = strPad.substring(0, width);
        }
        return strPad;
    }
}

package br.sql.util;

import br.sql.bean.JsysClientes;
import br.sql.bean.JsysOrcamento;
import br.sql.bean.JsysParametros;
import br.sql.bean.JsysReceber;
import br.sql.log.Log;
import java.awt.HeadlessException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Juliano Alves Medina
 */
public class Impressao {

    private static final Map<Object, Object> filtro = new HashMap<>();

    /**
     * vai vericiar se o orcamento esta pago para assim fazer a impressao
     *
     * @param orcamento
     * @return
     */
    public static boolean vendaPaga(Integer orcamento) {
        filtro.clear();
        filtro.put("idReceber", orcamento);
        JsysReceber r = (JsysReceber) Retorna.findOneResult("JsysReceber.findByIdReceber", filtro);
        if (r.getQuitado()) {
            return orcamento(orcamento);
        }
        return false;
    }

    /**
     * vai vericiar se o orcamento esta pago para assim fazer a impressao
     *
     * @param orcamento
     * @return
     */
    public static boolean vendaPaga(String orcamento) {
        return vendaPaga(Integer.valueOf(orcamento));
    }

    /**
     * imprime o orcamento do codigo passado
     *
     * @param orcamento
     * @return
     */
    public static boolean vendaAPagar(Integer orcamento) {
        return orcamento(orcamento);
    }

    /**
     *
     * @param orcamento passar o id do orcamente para imprimir o mesmo
     * @return
     */
    private static boolean orcamento(Integer orcamento) {
        ESCPrinter p = new ESCPrinter(br.JavaApplicationJsys.INI.getString("LISTA IMP", "IMP1").replace("\\","\\\\"), true);
        try {
            JsysParametros par = Retorna.JsysParametros(); //(br.sql.bean.JsysParametros) Retorna.findOneResult("JsysParametros.findAll");
            filtro.clear();
            filtro.put("idOrcamento", orcamento);
            JsysOrcamento dataVenda = (JsysOrcamento) Retorna.findOneResult("JsysOrcamento.findByIdOrcamento", filtro);

            filtro.clear();
            filtro.put("idCliente", dataVenda.getIdCliente().getIdCliente());
            JsysClientes dataCliente = (JsysClientes) Retorna.findOneResult("JsysClientes.findByIdCliente", filtro);

            filtro.clear();
            filtro.put("idCliente", dataVenda.getIdFuncionario().getIdCliente());
            JsysClientes dataVendedor = (JsysClientes) Retorna.findOneResult("JsysClientes.findByIdCliente", filtro);

            String empresa = par.getFantasia();
            String endereco = par.getEndereco();
            String numero = par.getNumero();
            String bairro = par.getBairro();
            String cidade = par.getCidade();
            String uf = par.getUf();
            String cnpj = par.getCnpj();
            String fone = par.getFone();
            if (p.initialize()) {
                int repetimpr = 1;
                if (!dataVenda.getFormaPagto().equals("00")) {
                    repetimpr = 2;
                }
                for (int r = 0; r < repetimpr; r++) {
                    p.setCharacterSet(ESCPrinter.BRAZIL);
                    p.setTamanhoPapel(33);
                    p.center();
                    p.selectRoman();
                    p.negrito(true);
                    p.print(empresa, true);
                    p.negrito(false);
                    p.print(endereco
                            + " - " + numero
                            + " - " + bairro
                            + " - " + fone, true);
                    p.negrito(true);
                    if (dataVenda.getTipoVenda().equals("Devolução")) {
                        p.print("Troca - " + orcamento + " | Ficha - " + dataVenda.getFicha(), true);
                    } else {
                        p.print("Pre-Venda - " + orcamento + " | Ficha - " + dataVenda.getFicha(), true);
                    }
                    p.print("NAO TEM VALOR FISCAL", true);
                    p.negrito(false);
                    p.print("--------------------------------------------------------------------------------", false);
                    p.esquerda();
                    p.print(ManagerString.espacosDireita("Cliente.: " + dataCliente.getIdCliente() + " - " + dataCliente.getNomeCorentista(), 80), true);
                    p.print(ManagerString.espacosDireita("Data....: "
                            + ManagerData.convertBrDate(dataVenda.getData())
                            + " - Hora.: " + ManagerData.getHoraAtualTypeString(0)
                            + " - Vendedor.: " + dataVendedor.getIdCliente() + " - "
                            + dataVendedor.getNomeCorentista(), 80), true);
                    p.print("--------------------------------------------------------------------------------", false);
                    p.print("Codigo    Qt Produto                           Marca       Valor Unit      Total", false);
                    p.print("--------------------------------------------------------------------------------", false);
                    filtro.clear();
                    filtro.put("idOrcamento", orcamento);
                    for (Object I : Retorna.findList("JsysOrcamentoItens.findByIdOrcamento", filtro)) {
                        br.sql.bean.JsysOrcamentoItens i = (br.sql.bean.JsysOrcamentoItens) I;
                        p.print(ManagerString.espacosEsquerda(i.getJsysProdutosT().getIdProduto().toString(), 7)
                                + ManagerString.espacosEsquerda(String.valueOf(i.getQuantidade().intValue()), 5)
                                + " " + ManagerString.espacosDireita(i.getJsysProdutosT().getNomeProduto(), 33)
                                + " " + ManagerString.espacosDireita(i.getJsysProdutosT().getMarca(), 11)
                                + ManagerString.espacosEsquerda(ManagerDecimal.converter(i.getPrecoVenda(), "#,##0.00"), 11)
                                + ManagerString.espacosEsquerda(ManagerDecimal.converter(i.getTotalProduto(), "#,##0.00"), 11), true);
                    }
                    p.print("--------------------------------------------------------------------------------", false);
                    p.print(ManagerString.espacosDireita("Forma de Pagamento.: "
                            + dataVenda.getFormaPagto(), 50), false);
                    p.print("Total Bruto...:" + ManagerString.espacosEsquerda(ManagerDecimal.converter(dataVenda.getValorBruto(), "#,##0.00"), 15), true);
                    p.print("Titulo........: " + dataVenda.getIdTituloEntrada(), false);
                    p.print(ManagerString.espacosEsquerda("Desconto......:" + ManagerString.espacosEsquerda(ManagerDecimal.converter(dataVenda.getValorDesconto(), "#,##0.00"), 15), 60), true);
                    p.print(ManagerString.espacosEsquerda("Total Liquido.:" + ManagerString.espacosEsquerda(ManagerDecimal.converter(dataVenda.getValorLiquido(), "#,##0.00"), 15), 80), true);
                    if (dataVenda.getTipoVenda().equals("Devolução")) {
                        p.print("Troca - " + orcamento + " | Ficha - " + dataVenda.getFicha(), true);
                    } else {
                        p.print("Pre-Venda - " + orcamento + " | Ficha - " + dataVenda.getFicha(), true);
                    }
                    p.formFeed();
                }
                // inicia a impressao das promissorias
                filtro.clear();
                filtro.put("idReceber", orcamento);
                for (Object R : Retorna.findList("JsysReceber.findByIdReceber", filtro)) {
                    JsysReceber r = (JsysReceber) R;
                    // verificar que e uma venda a prazo, se nao nao vai fazer nada
                    if (!r.getDataEmissao().equals(r.getDataVencimento())) {
                        p.print("================================================================================", false);
                        p.print(ManagerString.espacosDireita("N: "
                                + r.getJsysReceberPK().getIdReceber() + "-" + r.getJsysReceberPK().getSeqReceber(), 40)
                                + ManagerString.espacosEsquerda("Vencimento: "
                                        + ManagerData.convertBrDate(r.getDataVencimento()), 40), false);
                        p.print(ManagerString.espacosEsquerda("Valor: "
                                + ManagerDecimal.converter(r.getValorTitulo()), 80), true);
                        p.print(ManagerString.rachitagueDireita(
                                ManagerData.convertDate(r.getDataVencimento(), "'Ao(s)' dd 'de' MMMM 'de' yyyy"), 90)
                                + " pagarei por esta unica via de ", false);
                        p.negrito(true);
                        p.print("NOTA PROMISSORIA", true);
                        p.negrito(false);
                        p.lineFeed();
                        p.print("a " + ManagerString.rachitagueDireita(empresa, 30) + " CNPJ: " + cnpj, true);
                        p.lineFeed();
                        p.print("ou a sua ordem, a quantia de "
                                + ManagerString.rachitagueDireita(ManagerDecimal.valorExtenso(r.getValorTitulo()), 90)
                                + " Em moeda corrente deste Pais.", true);
                        p.print("Pagavel em: " + cidade + " - " + uf, true);
                        p.print(ManagerString.espacosDireita("Emitente  : " + dataCliente.getIdCliente() + " - " + dataCliente.getNomeCorentista(), 80), false);
                        p.print("CNPJ/CPF  : " + dataCliente.getCnpjCpf(), true);
                        p.print(ManagerString.espacosDireita("Endereco  : " + dataCliente.getEndereco() + " - " + dataCliente.getNumero(), 80), false);
                        p.print("Bairro    : " + dataCliente.getBairro(), true);
                        p.print(ManagerString.espacosDireita("Avalista  : " + "", 80), false);
                        p.print("Aval CPF  : " + "", true);
                        p.print("Emissao   : " + cidade + " - " + uf + ", " + ManagerData.convertDate(ManagerData.getDate(), "dd 'de' MMMM 'de' yyyy"), true);
                        p.lineFeed();
                        p.center();
                        p.print("_________________________          __________________________", true);
                        p.print("         Cliente                            Avalista         ", true);
                        p.esquerda();
                        p.print("================================================================================", false);
                        p.formFeed();
                    }
                }
                p.close();
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível Conectar a impressora", "ERRO", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (HeadlessException e) {
            Log.registraErro("Impressao", "orcamento", e);
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}

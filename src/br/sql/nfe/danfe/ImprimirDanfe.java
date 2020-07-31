package br.sql.nfe.danfe;

import static br.JavaApplicationJsys.OPTIONS;
import br.com.swconsultoria.nfe.schema_4.retEnviNFe.TNfeProc;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.sql.bean.DanfeNFCe;
import br.sql.bean.DanfeNFCeIten;
import br.sql.bean.DanfeNFCePagamentos;
import br.sql.bean.JsysNFe;
import br.sql.bean.JsysParametros;
import br.sql.nfe.links.LigacaoServicos;
import br.sql.nfe.links.Servicos;
import br.sql.log.Log;
import br.sql.util.ManagerString;
import br.sql.util.ReportUtils;
import br.sql.util.Retorna;
import java.awt.HeadlessException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Juliano Alves Medina
 */
public class ImprimirDanfe {

    private static final JsysParametros PAR = Retorna.JsysParametros();

    public static void nfce(String chaveAcesso) throws NumberFormatException, HeadlessException, NoSuchAlgorithmException {
        if (JOptionPane.showOptionDialog(null,
                "Deseja Imprimir a NFC-e?",
                "NFC-e",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, OPTIONS, OPTIONS[1]) == 0) {
            JsysNFe nfe = getJsysNFe(chaveAcesso);
            if (nfe != null) {
                try {
                    TNfeProc NFeProc = XmlNfeUtil.xmlToObject(nfe.getProcNFe(), TNfeProc.class);
                    //TNfeProc NFeProc = GerandoNFeProc.gerar(nfe.getEnviNFe(), nfe.getRetConsReciNFe());
                    String tpAmb = NFeProc.getNFe().getInfNFe().getIde().getTpAmb();
                    String tpImp = NFeProc.getNFe().getInfNFe().getIde().getTpImp();
                    String tpEmis = NFeProc.getNFe().getInfNFe().getIde().getTpEmis();
                    int mod = Integer.parseInt(NFeProc.getNFe().getInfNFe().getIde().getMod());
                    String uf = NFeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getUF().value();
                    Servicos ser = new LigacaoServicos().ligacaoServicos(uf, tpAmb, mod);
                    if (tpImp.equals("4") | tpImp.equals("5")) {
                        List<String> emissao = new ArrayList<>();
                        if (!tpEmis.equals("1")) {
                            if (PAR.getVias()) {
                                emissao.add(" - Via do Estabelecimento");
                                emissao.add(" - Via do Consumidor");
                            } else {
                                emissao.add(" - Via do Consumidor");
                            }
                        } else {
                            emissao.add(" - Via do Consumidor");
                        }
                        for (String via : emissao) {
                            ArrayList<DanfeNFCe> dados = new ArrayList<>();
                            DanfeNFCe danf = new DanfeNFCe();
                            danf.setxNome(NFeProc.getNFe().getInfNFe().getEmit().getXNome());
                            danf.setCnpj(NFeProc.getNFe().getInfNFe().getEmit().getCNPJ());
                            danf.setIe(NFeProc.getNFe().getInfNFe().getEmit().getIE());
                            danf.setIm(NFeProc.getNFe().getInfNFe().getEmit().getIM());
                            StringBuilder end = new StringBuilder();
                            end.append(NFeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getXLgr()).append(", ")
                                    .append(NFeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getNro()).append(", ")
                                    .append(NFeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getXBairro()).append(", ")
                                    .append(NFeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getXMun()).append(" - ")
                                    .append(NFeProc.getNFe().getInfNFe().getEmit().getEnderEmit().getUF());
                            danf.setEndereco(end.toString());
                            danf.setDivInfoFixas1("DANFE NFC-e - Documento Auxiliar da Nota Fiscal de Consumidor Eletrõnica");
                            danf.setDivInfoFixas2("Não permite aproveitamento de crédito de ICMS");
                            List<DanfeNFCeIten> listProd = new ArrayList<>();
                            for (br.com.swconsultoria.nfe.schema_4.retEnviNFe.TNFe.InfNFe.Det det : NFeProc.getNFe().getInfNFe().getDet()) {
                                DanfeNFCeIten prod = new DanfeNFCeIten();
                                prod.setIdProduto(Integer.valueOf(det.getProd().getCProd()));
                                prod.setDescricao(det.getProd().getXProd());
                                prod.setQtde(new BigDecimal(det.getProd().getQCom()));
                                prod.setUn(det.getProd().getUCom());
                                prod.setvTotal(new BigDecimal(det.getProd().getVProd()));
                                prod.setvTributos(BigDecimal.ZERO);
                                prod.setvUnit(new BigDecimal(det.getProd().getVUnCom()));
                                listProd.add(prod);
                            }
                            danf.setItens(listProd);
                            danf.setQtdeItens(new BigDecimal(NFeProc.getNFe().getInfNFe().getDet().size()));
                            danf.setvTotalNfce(new BigDecimal(NFeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVNF()));
                            List<DanfeNFCePagamentos> pags = new ArrayList<>();
                            for (br.com.swconsultoria.nfe.schema_4.retEnviNFe.TNFe.InfNFe.Pag.DetPag p : NFeProc.getNFe().getInfNFe().getPag().getDetPag()) {
                                DanfeNFCePagamentos pag = new DanfeNFCePagamentos();
                                pag.setFormaPagamento(p.getTPag());
                                pag.setvTotalPago(p.getVPag());
                                pags.add(pag);
                            }
                            danf.setPagamentos(pags);
                            danf.setvTotalTibutos(NFeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVTotTrib());
                            danf.setInfCpl(NFeProc.getNFe().getInfNFe().getInfAdic().getInfCpl());
                            String menFiscal = new String();
                            if (tpAmb.equals("2")) {
                                menFiscal = "EMITIDA EM AMBIENTE DE HOMOLOGAÇÃO – SEM VALOR FISCAL";
                            }
                            if (!tpEmis.equals("1")) {
                                menFiscal = menFiscal + " EMITIDA EM CONTINGÊNCIA";
                            } else {
                                danf.setProtocoloAutorizacao(NFeProc.getProtNFe().getInfProt().getNProt());
                                danf.setDataHoraAutorizacao(NFeProc.getProtNFe().getInfProt().getDhRecbto());
                            }
                            danf.setMenFiscal(menFiscal.trim());
                            danf.setnNfce(NFeProc.getNFe().getInfNFe().getIde().getNNF());
                            danf.setSerie(NFeProc.getNFe().getInfNFe().getIde().getSerie());
                            danf.setDhEmi(NFeProc.getNFe().getInfNFe().getIde().getDhEmi());
                            danf.setVia(via);
                            danf.setLinkConsulta(ser.getNFCeConsulta());
                            danf.setChaveAcesso(ManagerString.Right(NFeProc.getNFe().getInfNFe().getId(), 44));
                            // Divisão VII – Informações sobre o Consumidor
                            // verifica se foi informado o destinatario
                            if (NFeProc.getNFe().getInfNFe().getDest() != null) {
                                danf.setcCnpj(NFeProc.getNFe().getInfNFe().getDest().getCNPJ());
                                danf.setcCpf(NFeProc.getNFe().getInfNFe().getDest().getCPF());
                                danf.setcIdEstrang(NFeProc.getNFe().getInfNFe().getDest().getIdEstrangeiro());
                                if (!"".equals(NFeProc.getNFe().getInfNFe().getDest().getXNome())) {
                                    danf.setcNome(NFeProc.getNFe().getInfNFe().getDest().getXNome());
                                }
                                // verifica se foi informado o endereço do destinatario
                                if (NFeProc.getNFe().getInfNFe().getDest().getEnderDest() != null) {
                                    if (!"".equals(NFeProc.getNFe().getInfNFe().getDest().getEnderDest().getXLgr())) {
                                        danf.setcLogradouro(NFeProc.getNFe().getInfNFe().getDest().getEnderDest().getXLgr());
                                    }
                                    if (!"".equals(NFeProc.getNFe().getInfNFe().getDest().getEnderDest().getNro())) {
                                        danf.setcNumero(NFeProc.getNFe().getInfNFe().getDest().getEnderDest().getNro());
                                    }
                                    if (!"".equals(NFeProc.getNFe().getInfNFe().getDest().getEnderDest().getNro())) {
                                        danf.setcNumero(NFeProc.getNFe().getInfNFe().getDest().getEnderDest().getNro());
                                    }
                                    if (!"".equals(NFeProc.getNFe().getInfNFe().getDest().getEnderDest().getXBairro())) {
                                        danf.setcBairro(NFeProc.getNFe().getInfNFe().getDest().getEnderDest().getXBairro());
                                    }
                                    if (!"".equals(NFeProc.getNFe().getInfNFe().getDest().getEnderDest().getXMun())) {
                                        danf.setcMunicipio(NFeProc.getNFe().getInfNFe().getDest().getEnderDest().getXMun());
                                    }
                                }
                            }
                            //danf.setQRCode( getQRCode(NFeProc, tpAmb, ser.getNFCeConsultaQRCode()));
                            danf.setQRCode(NFeProc.getNFe().getInfNFeSupl().getQrCode());
                            danf.setMen(PAR.getMensagem());
                            dados.add(danf);
                            try {
                                Map<Object, Object> parametros = new HashMap<>();
                                ReportUtils.printReport("/br/rel/fiscal/impNfce79.jasper",
                                        parametros,
                                        new JRBeanCollectionDataSource(dados),
                                        br.JavaApplicationJsys.INI.getString("LISTA IMP", "IMP2"));
                            } catch (Exception e) {
                                Log.registraErro("ImprimirFiscal", "nfce1", e);
                            }
                        }
                    }
                } catch (JAXBException ex) {
                    Log.registraErro(ImprimirDanfe.class, "nfce", ex);
                }
            }
        }
    }

    public static void nfe(String chaveAcesso) {
        if (JOptionPane.showOptionDialog(null,
                "Deseja Imprimir a NF-e?",
                "NF-e",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, OPTIONS, OPTIONS[0]) == 0) {
            JsysNFe nfe = getJsysNFe(chaveAcesso);
            if (nfe != null) {
                try {
                    //xml.
                    String xml = nfe.getProcNFe();
                    Document doc = loadXMLFromString(xml);
                    //Fonte de Dados.
                    JRXmlDataSource xmlDataSource
                            = new JRXmlDataSource(doc, "/nfeProc/NFe/infNFe/det");
                    // Parametros.
                    Map<String, Object> param = getFaturas(doc);
                    param.put("Logo", PAR.getLogo());
                    ReportUtils.openReport("Imprimindo NF-e",
                            "/br/rel/fiscal/danfeR.jasper",
                            param,
                            xmlDataSource);
                } catch (JRException e) {
                    Log.registraErro("ImprimirFiscal", "nfe", e);
                }
            }
        }
    }

    private static JsysNFe getJsysNFe(String chaveAcesso) {
        if (!"".equals(chaveAcesso)) {
            Map<Object, Object> filtro = new HashMap<>();
            filtro.put("chaveAcesso", chaveAcesso);
            return (JsysNFe) Retorna.findOneResult("JsysNFe.findByChaveAcesso", filtro);
        }
        return null;
    }

    public static byte[] nfePdf(String chaveAcesso) {
        byte[] pdf = null;
        JsysNFe nfe = getJsysNFe(chaveAcesso);
        if (nfe != null) {
            try {
                //xml.
                String xml = nfe.getProcNFe(); //XmlNfeUtil.criaNfeProc(nfe.getEnviNFe(), nfe.getRetConsReciNFe());
                Document doc = loadXMLFromString(xml);
                //estrutura do xml.
                String recordPath = "/nfeProc/NFe/infNFe/det";
                //Fonte de Dados.
                JRXmlDataSource xmlDataSource = new JRXmlDataSource(doc, recordPath);
                InputStream inputStream = ImprimirDanfe.class.getResourceAsStream("/br/rel/fiscal/danfeR.jasper");
                // Parametros.
                Map<String, Object> param = getFaturas(doc);
                param.put("Logo", PAR.getLogo());
                /**
                 * Gerando o relatorio
                 */
                JasperPrint print = JasperFillManager.fillReport(inputStream, param, xmlDataSource);

                /**
                 * Exportando em pdf
                 */
                pdf = JasperExportManager.exportReportToPdf(print);
            } catch (JRException e) {
                Log.registraErro("ImprimirDanfe", "nfePdf", e);
                pdf = null;
            }
        }
        return pdf;
    }

    public static Map<String, Object> getFaturas(Document doc) {
        Map<String, Object> map = new HashMap<>();

        for (int i = 0; i < doc.getElementsByTagName("dup").getLength(); i++) {
            /**
             * Seta uma duplicata
             */
            Node dup = doc.getElementsByTagName("dup").item(i);

            /**
             * Seta o numero
             */
            Node nDoc = dup.getChildNodes().item(0);
            String numero = nDoc.getFirstChild().getNodeValue();
            map.put("FAT_NUMERO" + (i + 1), numero);

            /**
             * Seta a data
             */
            Node nData = dup.getChildNodes().item(1);
            String data = nData.getFirstChild().getNodeValue();

            try {
                map.put("FAT_VENCIMENTO" + (i + 1), new SimpleDateFormat("yyyy-MM-dd").parse(data));
            } catch (Exception e) {
                map.put("FAT_VENCIMENTO" + (i + 1), null);

            }

            /**
             * Seta o valor
             */
            Node nValor = dup.getChildNodes().item(2);
            String valor = nValor.getFirstChild().getNodeValue();
            map.put("FAT_VALOR" + (i + 1), Double.parseDouble(valor));
        }
        return map;
    }

    /**
     * Gera um objeto DOM do xml
     */
    private static Document loadXMLFromString(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            Log.registraErro("ImprimirDanfe", "loadXMLFromString", e);
        }
        org.w3c.dom.Document document = null;
        try {
            document = builder.parse(new ByteArrayInputStream(xml.getBytes()));
        } catch (SAXException | IOException e) {
            Log.registraErro("ImprimirDanfe", "loadXMLFromString", e);
        }
        return document;
    }

//    private static String getQRCode(TNfeProc NFeProc, String tpAmb, String urlConsulta) {
//        if (NFeProc.getNFe().getInfNFeSupl() == null) {
//            DanfeNFCeQRCode code = new DanfeNFCeQRCode();
//            code.setUrlConsulta(urlConsulta);
//            code.setChNFe(NFeProc.getProtNFe().getInfProt().getChNFe());
//            code.setnVersao("100");
//            code.setTpAmb(tpAmb);
//            if (NFeProc.getNFe().getInfNFe().getDest() != null) {
//                String cDest = new String();
//                if (NFeProc.getNFe().getInfNFe().getDest().getCNPJ() != null) {
//                    cDest = NFeProc.getNFe().getInfNFe().getDest().getCNPJ();
//                }
//                if (NFeProc.getNFe().getInfNFe().getDest().getCPF() != null) {
//                    cDest = NFeProc.getNFe().getInfNFe().getDest().getCPF();
//                }
//                if (NFeProc.getNFe().getInfNFe().getDest().getIdEstrangeiro() != null) {
//                    cDest = NFeProc.getNFe().getInfNFe().getDest().getIdEstrangeiro();
//                }
//                code.setcDest(cDest);
//            }
//            code.setDhEmi(NFeProc.getNFe().getInfNFe().getIde().getDhEmi());
//            code.setvNF(NFeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVNF());
//            code.setvICMS(NFeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVICMS());
//            try {
//                code.setDigVal(NFeProc.getProtNFe().getInfProt().getDigVal());
//            } catch (UnsupportedEncodingException e) {
//                salvaLog.registraErro("ImprimirFiscal", "nfce", e);
//            }
//            code.setcIdToken(par.getcIdToken());
//            code.setCSC(par.getCSC());
//            return code.getCodeQRCode();
//        } else {
//            return NFeProc.getNFe().getInfNFeSupl().getQrCode();
//        }
//    }
}

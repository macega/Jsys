/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean;

import br.sql.util.ManagerData;
import br.sql.util.ManagerString;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Juliano Alves Medina
 */
public class DanfeNFCe {

    //Divisão I - Informações do Cabeçalho 
    private String xNome;
    private String cnpj;
    private String ie;
    private String im;
    private String endereco;
    // Divisão II – Informações Fixas do DANFE NFC-e
    private String divInfoFixas1;
    private String divInfoFixas2;
    // Divisão III – Informações de Detalhe da Venda
    private List<DanfeNFCeIten> itens;
    // Divisão IV – Informações de Total do DANFE NFC-e
    private BigDecimal qtdeItens; //QTD. TOTAL DE ITENS = somatório da quantidade de itens;
    private BigDecimal vTotalNfce; // VALOR TOTAL = somatório dos valores totais dos itens somados os acréscimos e subtraído dos descontos;
    private List<DanfeNFCePagamentos> pagamentos;

    // Divisão V – Informações dos Tributos no DANFE NFC-e 
    private BigDecimal vTotalTibutos;
    // Divisão Va – Mensagem de Interesse do Contribuinte
    private String infCpl;
    // Divisão VI – Mensagem Fiscal e Informações da Consulta via Chave de Acesso 
    private String menFiscal; // Área de Mensagem Fiscal. Quando for o caso deve ser incluídas as seguintes mensagens: “EMITIDA EM CONTINGÊNCIA”, “EMITIDA EM AMBIENTE DE HOMOLOGAÇÃO – SEM VALOR FISCAL”)
    private String nNfce; //Número da NFC-e
    private String serie; //Série da NFC-e
    private String dhEmi; // Data e Hora de Emissão da NFC-e (observação: a data de emissão apesar de constar no arquivo XML da NFC-e em formato UTC deverá ser impressa no DANFE NFC-e sempre convertida para o horário local)
    private String via; // texto se e via do cliente ou estabelecimento
    private String linkConsulta; //O texto “Consulte pela Chave de Acesso em” seguido do endereço eletrônico para consulta pública da NFC-e no Portal da Secretaria da Fazenda do Estado do contribuinte;
    private String chaveAcesso; // A chave de acesso impressa em 11 blocos de quatro dígitos, com um espaço entre cada bloco;
    // Divisão VII – Informações sobre o Consumidor 
    private String cCnpj;
    private String cCpf;
    private String cIdEstrang;
    private String cNome;
    private String cLogradouro;
    private String cNumero;
    private String cBairro;
    private String cMunicipio;
    // Divisão VIII – Informações da Consulta via QR Code 
    private String protocoloAutorizacao;
    private String dataHoraAutorizacao;
    private String QRCode;
    // mensagem FIm
    private String men;

    public String getxNome() {
        return xNome;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = " IM:" + im;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDivInfoFixas1() {
        return divInfoFixas1;
    }

    public void setDivInfoFixas1(String divInfoFixas1) {
        this.divInfoFixas1 = divInfoFixas1;
    }

    public String getDivInfoFixas2() {
        return divInfoFixas2;
    }

    public void setDivInfoFixas2(String divInfoFixas2) {
        this.divInfoFixas2 = divInfoFixas2;
    }

    public List<DanfeNFCeIten> getItens() {
        return itens;
    }

    public void setItens(List<DanfeNFCeIten> itens) {
        this.itens = itens;
    }

    public BigDecimal getQtdeItens() {
        return qtdeItens;
    }

    public void setQtdeItens(BigDecimal qtdeItens) {
        this.qtdeItens = qtdeItens;
    }

    public BigDecimal getvTotalNfce() {
        return vTotalNfce;
    }

    public void setvTotalNfce(BigDecimal vTotalNfce) {
        this.vTotalNfce = vTotalNfce;
    }

    public List<DanfeNFCePagamentos> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<DanfeNFCePagamentos> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public BigDecimal getvTotalTibutos() {
        return vTotalTibutos;
    }

    public void setvTotalTibutos(String vTotalTibutos) {
        this.vTotalTibutos = new BigDecimal(vTotalTibutos);
    }

    public String getInfCpl() {
        return infCpl;
    }

    public void setInfCpl(String infCpl) {
        this.infCpl = infCpl;
    }

    public String getMenFiscal() {
        return menFiscal;
    }

    public void setMenFiscal(String menFiscal) {
        this.menFiscal = menFiscal;
    }

    public String getnNfce() {
        return nNfce;
    }

    public void setnNfce(String nNfce) {
        this.nNfce = nNfce;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDhEmi() {
        return dhEmi;
    }

    public void setDhEmi(String dhEmi) {
        Date d = ManagerData.formataData(dhEmi, ManagerData.FORMATO_NFE);
        this.dhEmi = ManagerData.convertDate(d, "dd/MM/yyyy HH:mm:ss");
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getLinkConsulta() {
        return linkConsulta;
    }

    public void setLinkConsulta(String linkConsulta) {
        this.linkConsulta = linkConsulta;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = ManagerString.format("#### #### #### #### #### #### #### #### #### #### ####", chaveAcesso);
    }

    public String getcCnpj() {
        return cCnpj;
    }

    public void setcCnpj(String cCnpj) {
        if (cCnpj != null & !"".equals(cCnpj)) {
            this.cCnpj = ManagerString.format("##.###.###/####-##", cCnpj);
        }
    }

    public String getcCpf() {
        return cCpf;
    }

    public void setcCpf(String cCpf) {
        if (cCpf != null & !"".equals(cCpf)) {
            this.cCpf = ManagerString.format("###.###.###-##", cCpf);
        }
    }

    public String getcIdEstrang() {
        return cIdEstrang;
    }

    public void setcIdEstrang(String cIdEstrang) {
        if (cIdEstrang != null & !"".equals(cIdEstrang)) {
            this.cIdEstrang = cIdEstrang;
        }
    }

    public String getcNome() {
        return cNome;
    }

    public void setcNome(String cNome) {
        this.cNome = cNome;
    }

    public String getcLogradouro() {
        return cLogradouro;
    }

    public void setcLogradouro(String cLogradouro) {
        this.cLogradouro = cLogradouro;
    }

    public String getcNumero() {
        return cNumero;
    }

    public void setcNumero(String cNumero) {
        this.cNumero = cNumero;
    }

    public String getcBairro() {
        return cBairro;
    }

    public void setcBairro(String cBairro) {
        this.cBairro = cBairro == null ? "" : cBairro;
    }

    public String getcMunicipio() {
        return cMunicipio;
    }

    public void setcMunicipio(String cMunicipio) {
        this.cMunicipio = cMunicipio == null ? "" : cMunicipio;
    }

    public String getProtocoloAutorizacao() {
        return protocoloAutorizacao;
    }

    public void setProtocoloAutorizacao(String protocoloAutorizacao) {
        this.protocoloAutorizacao = protocoloAutorizacao;
    }

    public String getDataHoraAutorizacao() {
        return dataHoraAutorizacao;
    }

    public void setDataHoraAutorizacao(String dataHoraAutorizacao) {
        Date d = ManagerData.formataData(dataHoraAutorizacao, "yyyy-MM-dd'T'HH:mm:ssXXX");
        this.dataHoraAutorizacao = ManagerData.convertDate(d, "dd/MM/yyyy HH:mm:ss");
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public String getMen() {
        return men;
    }

    public void setMen(String men) {
        this.men = men;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o); //To change body of generated methods, choose Tools | Templates.
    }
}

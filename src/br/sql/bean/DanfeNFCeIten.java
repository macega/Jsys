/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.bean;

import java.math.BigDecimal;

/**
 *
 * @author Juliano Alves Medina
 */
public class DanfeNFCeIten {

    private int idProduto;
    private String descricao; 
    private BigDecimal qtde; 
    private String un;
    private BigDecimal vUnit;
    private BigDecimal vTotal;
    private BigDecimal vTributos;

    /**
     * 
     * @return código do produto adotado pelo estabelecimento
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     * 
     * @param idProduto código do produto adotado pelo estabelecimento
     */
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * 
     * @return descrição do produto
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * 
     * @param descricao descrição do produto
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * 
     * @return quantidade de unidades do produto adquiridas pelo consumidor
     */
    public BigDecimal getQtde() {
        return qtde;
    }

    /**
     * 
     * @param qtde quantidade de unidades do produto adquiridas pelo consumidor
     */
    public void setQtde(BigDecimal qtde) {
        this.qtde = qtde;
    }

    /**
     * 
     * @return unidade de medida do produto
     */
    public String getUn() {
        return un;
    }

    /**
     * 
     * @param un unidade de medida do produto
     */
    public void setUn(String un) {
        this.un = un;
    }

    /**
     * 
     * @return valor de uma unidade do produto
     */
    public BigDecimal getvUnit() {
        return vUnit;
    }

    /**
     * 
     * @param vUnit valor de uma unidade do produto
     */
    public void setvUnit(BigDecimal vUnit) {
        this.vUnit = vUnit;
    }

    /**
     * 
     * @return valor total do produto (Qtde x Valor unit)
     */
    public BigDecimal getvTotal() {
        return vTotal;
    }

    /**
     * 
     * @param vTotal valor total do produto (Qtde x Valor unit)
     */
    public void setvTotal(BigDecimal vTotal) {
        this.vTotal = vTotal;
    }

    /**
     * 
     * @return valor aproximado dos tributos
     */
    public BigDecimal getvTributos() {
        return vTributos;
    }

    /**
     * 
     * @param vTributos valor aproximado dos tributos
     */
    public void setvTributos(BigDecimal vTributos) {
        this.vTributos = vTributos;
    }

}

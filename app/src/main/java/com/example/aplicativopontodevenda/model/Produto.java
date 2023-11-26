package com.example.aplicativopontodevenda.model;

public class Produto {

    private int codigo;
    private String nomeProduto;
    private double valorProduto;

    public Produto() {
    }

    public Produto(int codigo, String nomeProduto, double valorProduto) {
        this.codigo = codigo;
        this.nomeProduto = nomeProduto;
        this.valorProduto = valorProduto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }
}

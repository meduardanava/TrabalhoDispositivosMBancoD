package com.example.aplicativopontodevenda.model;

import java.util.ArrayList;

public class Venda {

    private int codigo;
    private Cliente cliente;
    private ArrayList<Produto> produtos;
    private ArrayList<Integer> qtdProdutos;
    private double valorVenda;

    public Venda() {
    }

    public Venda(int codigo, Cliente cliente, ArrayList<Produto> produtos, ArrayList<Integer> qtdProdutos, double valorVenda) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.produtos = produtos;
        this.qtdProdutos = qtdProdutos;
        this.valorVenda = valorVenda;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public ArrayList<Integer> getQtdProdutos() {
        return qtdProdutos;
    }

    public void setQtdProdutos(ArrayList<Integer> qtdProdutos) {
        this.qtdProdutos = qtdProdutos;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public double subtotalVenda () {
        double subtotal = 0;
        for (int i = 0; i < this.produtos.size(); i++) {
            subtotal+= (this.produtos.get(i).getValorProduto()*this.qtdProdutos.get(i));
        }

        return subtotal;
    }
}
package com.example.aplicativopontodevenda.controller;

import android.content.Context;

import com.example.aplicativopontodevenda.dao.ProdutoDao;
import com.example.aplicativopontodevenda.model.Produto;

import java.util.ArrayList;

public class ProdutoController {

    private Context context;

    public ProdutoController(Context context) {
        this.context = context;
    }

    public String salvarProduto(String codigo, String nomeProduto, String valorProduto) {

        try {
            if (codigo.equals("") || codigo.isEmpty()) {
                return  "Informe o CÓDIGO do Produto!";
            }
            if (nomeProduto.equals("") || nomeProduto.isEmpty()) {
                return "Informe o NOME do Produto!";
            }
            if (valorProduto.equals("") || valorProduto.isEmpty()) {
                return "Informe o VALOR do Produto!";
            }

            Produto produto = ProdutoDao.getInstancia(context)
                    .getById(Integer.parseInt(codigo));

            if (produto != null) {
                return "O CÓDIGO (" + codigo + ") já está cadastrado!";
            }else {
                produto = new Produto();
                produto.setCodigo(Integer.parseInt(codigo));
                produto.setNomeProduto(nomeProduto);
                produto.setValorProduto(Double.parseDouble(valorProduto));

                ProdutoDao.getInstancia(context).insert(produto);
            }

        }catch (Exception ex){
            return "Erro ao Gravar Produto!";
        }
        return null;
    }

    public ArrayList<Produto> retornarTodosProdutos() {
        return ProdutoDao.getInstancia(context).getAll();
    }

}

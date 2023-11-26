package com.example.aplicativopontodevenda.controller;

import android.content.Context;

import com.example.aplicativopontodevenda.dao.ProdutoDao;
import com.example.aplicativopontodevenda.dao.VendaDao;
import com.example.aplicativopontodevenda.model.Produto;
import com.example.aplicativopontodevenda.model.Venda;

import java.util.ArrayList;

public class VendaController {

    private Context context;

    public VendaController(Context context) {
        this.context = context;
    }

    public ArrayList<Venda> retornarTodasVendas() {
        return VendaDao.getInstancia(context).getAll();
    }
}

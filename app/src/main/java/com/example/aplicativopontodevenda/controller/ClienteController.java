package com.example.aplicativopontodevenda.controller;

import android.content.Context;

import com.example.aplicativopontodevenda.dao.ClienteDao;
import com.example.aplicativopontodevenda.model.Cliente;

import java.util.ArrayList;

public class ClienteController {

    private Context context;

    public ClienteController(Context context) {
        this.context = context;
    }

    public String salvarCliente(String codigo, String razaoSocial, String cnpj, String endereco) {

        try {
            if (codigo.equals("") || codigo.isEmpty()) {
                return  "Informe o CÓDIGO do Cliente!";
            }
            if (razaoSocial.equals("") || razaoSocial.isEmpty()) {
                return "Informe a RAZÃO SOCIAL do Cliente!";
            }
            if (cnpj.equals("") || cnpj.isEmpty()) {
                return "Informe o CNPJ do Cliente!";
            }
            if (endereco.equals("") || endereco.isEmpty()) {
                return "Informe o ENDEREÇO do Cliente!";
            }

            Cliente cliente = ClienteDao.getInstancia(context)
                    .getById(Integer.parseInt(codigo));

            if (cliente != null) {
                return "O CÓDIGO (" + codigo + ") já está cadastrado!";
            }else {
                cliente = new Cliente();
                cliente.setCodigo(Integer.parseInt(codigo));
                cliente.setRazaoSocial(razaoSocial);
                cliente.setCnpj(cnpj);
                cliente.setEndereco(endereco);

                ClienteDao.getInstancia(context).insert(cliente);
            }

        }catch (Exception ex){
            return "Erro ao Gravar Cliente!";
        }
        return null;
    }

    public ArrayList<Cliente> retornarTodosClientes() {
        return ClienteDao.getInstancia(context).getAll();
    }
}

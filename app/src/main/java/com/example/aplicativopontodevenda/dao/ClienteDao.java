package com.example.aplicativopontodevenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aplicativopontodevenda.helper.SQLiteDataHelper;
import com.example.aplicativopontodevenda.model.Cliente;

import java.util.ArrayList;

public class ClienteDao implements GenericDao<Cliente> {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase baseDados;
    private String[]colunas = {"CODIGO", "RAZAOSOCIAL", "CNPJ", "ENDERECO"};
    private String tabela = "CLIENTE";
    private Context context;
    private static ClienteDao instancia;

    public static ClienteDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new ClienteDao(context);
        }else {
            return instancia;
        }
    }

    public ClienteDao(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "NAVA", null, 1);
        baseDados = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Cliente obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getCodigo());
            valores.put(colunas[1], obj.getRazaoSocial());
            valores.put(colunas[2], obj.getCnpj());
            valores.put(colunas[3], obj.getEndereco());

            return baseDados.insert(tabela,null, valores);

        }catch (SQLException ex){
            Log.e("NAVA", "ERRO: ClienteDao.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Cliente obj) {

        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getRazaoSocial());

            String[]identificador = {String.valueOf(obj.getCnpj())};

            return baseDados.update(tabela, valores, colunas[0]+ "= ?", identificador);
        }catch (SQLException ex){
            Log.e("NAVA", "ERRO: ClienteDao.update() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Cliente obj) {

        try {
            String[]identificador = {String.valueOf(obj.getCnpj())};

            return baseDados.delete(tabela, colunas[0] + "= ?", identificador);
        }catch (SQLException ex){
            Log.e("NAVA", "ERRO: ClienteDao.delete() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Cliente> getAll() {
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            Cursor cursor = baseDados.query(tabela, colunas,
                    null, null, null,
                    null, colunas[0]);

            if (cursor.moveToFirst()) {
                do {
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(cursor.getInt(0));
                    cliente.setRazaoSocial(cursor.getString(1));
                    cliente.setCnpj(cursor.getString(2));
                    cliente.setEndereco(cursor.getString(3));

                    lista.add(cliente);

                }while (cursor.moveToNext());
            }

        }catch (SQLException ex){
            Log.e("NAVA", "ERRO: ClienteDao.getAll() "+ex.getMessage());
        }
        return lista;
    }

    @Override
    public Cliente getById(int id) {

        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0] + "= ?", identificador,
                    null, null, null);

            if (cursor.moveToFirst()) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(cursor.getInt(0));
                cliente.setRazaoSocial(cursor.getString(1));
                cliente.setCnpj(cursor.getString(2));
                cliente.setEndereco(cursor.getString(3));

                return  cliente;
            }

        }catch (SQLException ex){
            Log.e("NAVA", "ERRO: ClienteDao.getById() "+ex.getMessage());
        }

        return null;
    }
}
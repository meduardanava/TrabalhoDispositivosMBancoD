package com.example.aplicativopontodevenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aplicativopontodevenda.helper.SQLiteDataHelper;
import com.example.aplicativopontodevenda.model.Produto;

import java.util.ArrayList;

public class ProdutoDao implements GenericDao<Produto> {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase baseDados;
    private String[]colunas = {"CODIGO", "NOME", "VALORPRODUTO"};
    private String tabela = "PRODUTO";
    private Context context;
    private static ProdutoDao instancia;

    public static ProdutoDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new ProdutoDao(context);
        }else {
            return instancia;
        }
    }

    public ProdutoDao(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "NAVA", null, 1);
        baseDados = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Produto obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getCodigo());
            valores.put(colunas[1], obj.getNomeProduto());
            valores.put(colunas[2], obj.getValorProduto());

            return baseDados.insert(tabela,null, valores);

        }catch (SQLException ex){
            Log.e("NAVA", "ERRO: ProdutoDao.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Produto obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getNomeProduto());

            String[]identificador = {String.valueOf(obj.getCodigo())};

            return baseDados.update(tabela, valores, colunas[0]+
                    "= ?", identificador);

        }catch (SQLException ex){
            Log.e("NAVA", "ERRO: ProdutoDao.update() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Produto obj) {
        try {
            String[]identificador = {String.valueOf(obj.getCodigo())};

            return baseDados.delete(tabela, colunas[0] +
                    "= ?", identificador);

        }catch (SQLException ex){
            Log.e("NAVA", "ERRO: ProdutoDao.delete() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Produto> getAll() {
        ArrayList<Produto> lista = new ArrayList<>();

        try {
            Cursor cursor = baseDados.query(tabela, colunas,
                    null, null, null,
                    null, colunas[0] + "desc");

            if (cursor.moveToFirst()) {
                do {
                    Produto produto = new Produto();
                    produto.setCodigo(cursor.getInt(0));
                    produto.setNomeProduto(cursor.getString(1));
                    produto.setValorProduto(cursor.getDouble(2));

                    lista.add(produto);

                }while (cursor.moveToNext());
            }

        }catch (SQLException ex){
            Log.e("NAVA", "ERRO: ProdutoDao.getAll() "+ex.getMessage());
        }
        return lista;
    }

    @Override
    public Produto getById(int id) {

        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0] + "= ?", identificador,
                    null, null, null);

            if (cursor.moveToFirst()) {
                Produto produto = new Produto();
                produto.setCodigo(cursor.getInt(0));
                produto.setNomeProduto(cursor.getString(1));
                produto.setValorProduto(cursor.getDouble(2));

                return  produto;
            }

        }catch (SQLException ex){
            Log.e("NAVA", "ERRO: ProdutoDao.getById() "+ex.getMessage());
        }
        return null;
    }
}
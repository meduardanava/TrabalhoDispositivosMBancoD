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
import com.example.aplicativopontodevenda.model.Produto;
import com.example.aplicativopontodevenda.model.Venda;

import java.util.ArrayList;

public class VendaDao implements GenericDao<Venda> {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase baseDados;
    private String[]colunasVenda = {"CODIGO", "CODCLIENTE", "SUBTOTAL"};
    private String tabelaVenda = "VENDA";
    private String[]colunasProdutoVendido = {"CODIGO", "CODVENDA", "CODPRODUTO", "QTDPRODUTO", "PRECOUND"};
    private String tabelaProdutoVendido = "PRODUTOVENDIDO";
    private Context context;
    private static VendaDao instancia;

    public static VendaDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new VendaDao(context);
        }else {
            return instancia;
        }
    }

    public VendaDao(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "NAVA", null, 1);
        baseDados = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Venda obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunasVenda[0], obj.getCodigo());
            valores.put(colunasVenda[1], obj.getCliente().getCodigo());
            valores.put(colunasVenda[2], obj.subtotalVenda());

            return baseDados.insert(tabelaVenda,null, valores);

        }catch (SQLException ex){
            Log.e("NAVA", "ERRO: ProdutoDao.insert() "+ex.getMessage());
        }

        for (int i = 0; i < obj.getProdutos().size(); i++) {
            try {
                ContentValues valores = new ContentValues();
                valores.put(colunasProdutoVendido[0], getAllVendidos().size());
                valores.put(colunasProdutoVendido[1], obj.getCodigo());
                valores.put(colunasProdutoVendido[2], obj.getProdutos().get(i).getCodigo());
                valores.put(colunasProdutoVendido[3], obj.getQtdProdutos().get(i));
                valores.put(colunasProdutoVendido[4], obj.getProdutos().get(i).getValorProduto());

                return baseDados.insert(tabelaVenda,null, valores);

            }catch (SQLException ex){
                Log.e("NAVA", "ERRO: ProdutoDao.insert() "+ex.getMessage());
                break;
            }
        }
        return 0;
    }

    @Override
    public long update(Venda obj) {
        return 0;
    }

    @Override
    public long delete(Venda obj) {
        return 0;
    }

    @Override
    public ArrayList<Venda> getAll() {
        ArrayList<Venda> lista = new ArrayList<>();

        try {
            Cursor cursor = baseDados.query(tabelaVenda, colunasVenda,
                    null, null, null,
                    null, colunasVenda[0] + "desc");

            if (cursor.moveToFirst()) {
                do {
                    Venda venda = new Venda();
                    venda.setCodigo(cursor.getInt(0));
                    Cliente cliente = ClienteDao.getInstancia(context).getById(cursor.getInt(1));
                    venda.setCliente(cliente);
                    venda.setValorVenda(cursor.getDouble(2));

                    lista.add(venda);

                }while (cursor.moveToNext());
            }

        }catch (SQLException ex){
            Log.e("NAVA", "ERRO: ProdutoDao.getAll() "+ex.getMessage());
        }
        return lista;
    }

    public ArrayList<Produto> getAllVendidos() {
        ArrayList<Produto> lista = new ArrayList<>();

        try {
            Cursor cursor = baseDados.query(tabelaProdutoVendido, colunasProdutoVendido,
                    null, null, null,
                    null, colunasProdutoVendido[0] + "desc");

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
            Log.e("NAVA", "ERRO: VendaDao.getAllVendidos() "+ex.getMessage());
        }
        return lista;
    }

    @Override
    public Venda getById(int id) {
        return null;
    }
}

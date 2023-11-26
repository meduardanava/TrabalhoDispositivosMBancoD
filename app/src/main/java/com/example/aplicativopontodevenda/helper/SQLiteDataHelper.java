package com.example.aplicativopontodevenda.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context,
                            @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CLIENTE (CODIGO INTEGER PRIMARY KEY, RAZAOSOCIAL VARCHAR(100), CNPJ VARCHAR(14), ENDERECO VARCHAR(150))");
        sqLiteDatabase.execSQL("CREATE TABLE PRODUTO (CODIGO INTEGER PRIMARY KEY, NOME VARCHAR(100), VALORPRODUTO NUMERIC)");
        sqLiteDatabase.execSQL("CREATE TABLE VENDA (CODIGO INTEGER PRIMARY KEY, CODCLIENTE INTEGER REFERENCES CLIENTE(CODIGO), SUBTOTAL NUMERIC)");
        sqLiteDatabase.execSQL("CREATE TABLE PRODUTOVENDIDO (CODIGO INTEGER PRIMARY KEY, CODVENDA INTEGER REFERENCES VENDA(CODIGO), CODPRODUTO INTEGER REFERENCES PRODUTO(CODIGO)" +
                ", QTDPRODUTO INTEGER, PRECOUND NUMERIC)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}

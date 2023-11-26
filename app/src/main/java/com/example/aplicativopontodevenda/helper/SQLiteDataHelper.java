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
        sqLiteDatabase.execSQL("CREATE TABLE CLIENTE (CODIGO INTEGER, RAZAOSOCIAL VARCHAR(100), CNPJ VARCHAR(14), ENDERECO VARCHAR(150))");
        sqLiteDatabase.execSQL("CREATE TABLE PRODUTO (CODIGO INTEGER, NOME VARCHAR(100), VALORPRODUTO NUMERIC)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}

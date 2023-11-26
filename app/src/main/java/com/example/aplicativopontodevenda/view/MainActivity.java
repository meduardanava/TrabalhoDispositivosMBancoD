package com.example.aplicativopontodevenda.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aplicativopontodevenda.R;
import com.example.aplicativopontodevenda.dao.ClienteDao;
import com.example.aplicativopontodevenda.dao.ProdutoDao;
import com.example.aplicativopontodevenda.dao.VendaDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClienteDao.getInstancia(this);
        ProdutoDao.getInstancia(this);
        VendaDao.getInstancia(this);
    }

    public void abrirCadastroCliente(View view) {
        Intent intent = new Intent(MainActivity.this, ClienteActivity.class);
        startActivity(intent);
    }

    public void abrirCadastroProduto(View view) {
        Intent intent = new Intent(MainActivity.this, ProdutoActivity.class);
        startActivity(intent);
    }

    public void abrirVenda(View view) {
        Intent intent = new Intent(MainActivity.this, VendaActivity.class);
        startActivity(intent);
    }

}
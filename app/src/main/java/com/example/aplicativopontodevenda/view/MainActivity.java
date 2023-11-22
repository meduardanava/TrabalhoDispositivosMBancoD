package com.example.aplicativopontodevenda.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aplicativopontodevenda.R;
import com.example.aplicativopontodevenda.dao.ClienteDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClienteDao.getInstancia(this);
    }

    public void abrirCadastroCliente(View view) {

        Intent intent = new Intent(MainActivity.this, ClienteActivity.class);
        startActivity(intent);
    }
}
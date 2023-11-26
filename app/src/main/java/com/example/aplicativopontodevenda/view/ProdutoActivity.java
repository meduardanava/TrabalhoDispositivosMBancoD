package com.example.aplicativopontodevenda.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicativopontodevenda.R;
import com.example.aplicativopontodevenda.adapter.ProdutoListAdapter;
import com.example.aplicativopontodevenda.controller.ProdutoController;
import com.example.aplicativopontodevenda.model.Produto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProdutoActivity extends AppCompatActivity {

    private FloatingActionButton btCadastroProduto;
    private AlertDialog dialog;
    private ProdutoController controller;
    private EditText edCodigo;
    private EditText edNomeProduto;
    private EditText edValorProduto;
    private View viewAlert;
    private RecyclerView rvProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        controller = new ProdutoController(this);
        rvProdutos = findViewById(R.id.rvProdutos);
        btCadastroProduto = findViewById(R.id.btCadastroProduto);
        btCadastroProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastro();
            }
        });

        atualizarListaProduto();
    }

    private void abrirCadastro(){
        viewAlert = getLayoutInflater().inflate(R.layout.dialog_cadastro_produto, null);
        edCodigo = viewAlert.findViewById(R.id.edCodigo);
        edNomeProduto = viewAlert.findViewById(R.id.edNomeProduto);
        edValorProduto = viewAlert.findViewById(R.id.edValorProduto);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CADASTRO DE PRODUTOS");
        builder.setView(viewAlert);
        builder.setCancelable(false);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                salvarDados();
            }
        });

        dialog = builder.create();
        dialog.show();
    }

    public void salvarDados() {
        String retorno = controller.salvarProduto(edCodigo.getText().toString(),
                edNomeProduto.getText().toString(), edValorProduto.getText().toString());

        if (retorno != null) {
            if (retorno.contains("CODIGO")) {
                edCodigo.setError(retorno);
                edCodigo.requestFocus();
            }
            if (retorno.contains("NOME DO PRODUTO")) {
                edNomeProduto.setError(retorno);
                edNomeProduto.requestFocus();
            }
            if (retorno.contains("VALOR")) {
                edValorProduto.setError(retorno);
                edValorProduto.requestFocus();
            }
        }else {
            Toast.makeText(this, "Produto salvo com sucesso!", Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }
    }

    private void atualizarListaProduto() {
        ArrayList<Produto> listaProduto = controller.retornarTodosProdutos();
        ProdutoListAdapter adapter = new ProdutoListAdapter(listaProduto, this);
        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
        rvProdutos.setAdapter(adapter);
    }
}
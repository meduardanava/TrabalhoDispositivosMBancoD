package com.example.aplicativopontodevenda.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicativopontodevenda.R;
import com.example.aplicativopontodevenda.adapter.ClienteListAdapter;
import com.example.aplicativopontodevenda.controller.ClienteController;
import com.example.aplicativopontodevenda.model.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ClienteActivity extends AppCompatActivity {

    private FloatingActionButton btCadastroCliente;
    private AlertDialog dialog;
    private ClienteController controller;
    private EditText edCodigo;
    private EditText edRazaoSocial;
    private EditText edCnpj;
    private EditText edEndereco;
    private View viewAlert;
    private RecyclerView rvClientes;

    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        controller = new ClienteController(this);
        rvClientes = findViewById(R.id.rvClientes);
        btCadastroCliente = findViewById(R.id.btCadastroCliente);
        btCadastroCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastro();
            }
        });

        atualizarListaCliente();
    }

    private void abrirCadastro(){

        viewAlert = getLayoutInflater().inflate(R.layout.dialog_cadastro_cliente, null);
        edCodigo = viewAlert.findViewById(R.id.edCodigo);
        edRazaoSocial = viewAlert.findViewById(R.id.edRazaoSocial);
        edCnpj = viewAlert.findViewById(R.id.edCnpj);
        edEndereco = viewAlert.findViewById(R.id.edEndereco);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CADASTRO DE CLIENTE");
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

        /*
        builder.setPositiveButton("Salvar", null);

        dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button bt = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        salvarDados();
                    }
                });
            }
        });*/

        dialog = builder.create();
        dialog.show();
    }

    public void salvarDados() {
        String retorno = controller.salvarCliente(edCodigo.getText().toString(),
                edRazaoSocial.getText().toString(), edCnpj.getText().toString(),
                edEndereco.getText().toString());

        if (retorno != null) {
            if (retorno.contains("CODIGO")) {
                edCodigo.setError(retorno);
                edCodigo.requestFocus();
            }
            if (retorno.contains("RAZÃO SOCIAL")) {
                edRazaoSocial.setError(retorno);
                edRazaoSocial.requestFocus();
            }
            if (retorno.contains("CNPJ")) {
                edCnpj.setError(retorno);
                edCnpj.requestFocus();
            }
            if (retorno.contains("ENDEREÇO")) {
                edEndereco.setError(retorno);
                edEndereco.requestFocus();
            }
        }else {
            Toast.makeText(this, "Cliente salvo com sucesso!",
                    Toast.LENGTH_LONG).show();

            dialog.dismiss();
        }
    }


    private void atualizarListaCliente() {

        ArrayList<Cliente> listaClientes = controller.retornarTodosClientes();
        ClienteListAdapter adapter = new ClienteListAdapter(listaClientes, this);
        rvClientes.setLayoutManager(new LinearLayoutManager(this));
        rvClientes.setAdapter(adapter);
    }

}
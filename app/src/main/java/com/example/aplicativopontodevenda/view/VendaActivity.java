package com.example.aplicativopontodevenda.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aplicativopontodevenda.R;
import com.example.aplicativopontodevenda.dao.ClienteDao;
import com.example.aplicativopontodevenda.dao.ProdutoDao;
import com.example.aplicativopontodevenda.dao.VendaDao;
import com.example.aplicativopontodevenda.model.Cliente;
import com.example.aplicativopontodevenda.model.Produto;
import com.example.aplicativopontodevenda.model.Venda;

import java.util.ArrayList;

public class VendaActivity extends AppCompatActivity {

    private TextView tvNumeroVenda;
    private AutoCompleteTextView autoCliente;
    private AutoCompleteTextView autoAdicionarProduto;
    private EditText edQtdProduto;
    private Button btAdicionarProduto;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Produto> listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);

        tvNumeroVenda = findViewById(R.id.tvNumeroPedido);
        autoCliente = findViewById(R.id.autoCliente);
        autoAdicionarProduto = findViewById(R.id.autoAdicionarProduto);
        edQtdProduto = findViewById(R.id.edQtdProduto);
        btAdicionarProduto = findViewById(R.id.btAdicionarProduto);

        numVendaAutomatico();
        carregarClientes();
        carregaProdutos();
    }

    private void carregarClientes() {
        listaClientes = ClienteDao.getInstancia(this).getAll();
        String[] vetClientes = new String[listaClientes.size()];
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            vetClientes[i] = cliente.getRazaoSocial();
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, vetClientes);

        autoCliente.setAdapter(adapter);
    }

    private void carregaProdutos() {
        listaProdutos = ProdutoDao.getInstancia(this).getAll();
        String[] vetProdutos = new String[listaProdutos.size()];
        for (int i = 0; i < listaProdutos.size(); i++) {
            Produto produto = listaProdutos.get(i);
            vetProdutos[i] = produto.getNomeProduto() + " - R$ " + produto.getValorProduto();
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, vetProdutos);

        autoAdicionarProduto.setAdapter(adapter);
    }

    private void numVendaAutomatico(){
        ArrayList<Venda> lista = VendaDao.getInstancia(this).getAll();
        String numPedido = String.valueOf(lista.size() + 1);

        tvNumeroVenda.setText(numPedido);
    }

}
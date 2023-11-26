package com.example.aplicativopontodevenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicativopontodevenda.R;
import com.example.aplicativopontodevenda.model.Cliente;

import java.util.ArrayList;

public class ClienteListAdapter extends
        RecyclerView.Adapter<ClienteListAdapter.ViewHolder> {

    private ArrayList<Cliente> listaClientes;
    private Context context;

    public ClienteListAdapter(ArrayList<Cliente> listaClientes, Context context) {
        this.listaClientes = listaClientes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_cliente, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteListAdapter.ViewHolder holder, int position) {
        Cliente clienteSelecionado = listaClientes.get(position);
        holder.tvCodigo.setText(String.valueOf(clienteSelecionado.getCodigo()));
        holder.tvRazaoSocial.setText(clienteSelecionado.getRazaoSocial());
        holder.tvCnpj.setText(clienteSelecionado.getCnpj());
        holder.tvEndereco.setText(clienteSelecionado.getEndereco());
    }

    @Override
    public int getItemCount() {
        return this.listaClientes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCodigo;
        public TextView tvRazaoSocial;
        public TextView tvCnpj;
        public TextView tvEndereco;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            this.tvCodigo = itemView.findViewById(R.id.tvCodigo);
            this.tvRazaoSocial = itemView.findViewById(R.id.tvRazaoSocial);
            this.tvCnpj = itemView.findViewById(R.id.tvCnpj);
            this.tvEndereco = itemView.findViewById(R.id.tvEndereco);
        }
    }
}

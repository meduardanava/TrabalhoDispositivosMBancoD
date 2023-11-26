package com.example.aplicativopontodevenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicativopontodevenda.R;
import com.example.aplicativopontodevenda.model.Produto;

import java.util.ArrayList;

public class VendaAtualListAdapter extends RecyclerView.Adapter<VendaAtualListAdapter.ViewHolder> {

    private ArrayList<Produto> listaProduto;
    private Context context;
    private int quantidade;
    private double valorTotal;

    public VendaAtualListAdapter(ArrayList<Produto> listaProduto, Context context) {
        this.listaProduto = listaProduto;
        this.context = context;
    }

    public void setValores(int qtd, double total) {
        quantidade = qtd;
        valorTotal = total;
    }

    @NonNull
    @Override
    public VendaAtualListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_venda_atual, parent, false);
        return new VendaAtualListAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull VendaAtualListAdapter.ViewHolder holder, int position) {
        holder.tvQuantidade.setText(String.valueOf(quantidade));
        holder.tvNomeProduto.setText(listaProduto.get(position).getNomeProduto());
        holder.tvValorProduto.setText(String.valueOf(listaProduto.get(position).getValorProduto()));
        holder.tvValorTotal.setText(String.valueOf(valorTotal));
    }

    @Override
    public int getItemCount() {
        return this.listaProduto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvQuantidade;
        public TextView tvNomeProduto;
        public TextView tvValorProduto;
        public TextView tvValorTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvQuantidade = itemView.findViewById(R.id.tvQuantidade);
            this.tvNomeProduto = itemView.findViewById(R.id.tvNomeProduto);
            this.tvValorProduto = itemView.findViewById(R.id.tvValorProduto);
            this.tvValorTotal = itemView.findViewById(R.id.tvValorTotal);
        }
    }
}
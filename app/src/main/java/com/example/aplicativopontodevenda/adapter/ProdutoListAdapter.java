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

public class ProdutoListAdapter extends RecyclerView.Adapter<ProdutoListAdapter.ViewHolder> {

    private ArrayList<Produto> listaProduto;
    private Context context;

    public ProdutoListAdapter(ArrayList<Produto> listaProduto, Context context) {
        this.listaProduto = listaProduto;
        this.context = context;
    }

    @NonNull
    @Override
    public ProdutoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_produto, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoListAdapter.ViewHolder holder, int position) {
        holder.tvCodigo.setText(String.valueOf(listaProduto.get(position).getCodigo()));
        holder.tvNomeProduto.setText(listaProduto.get(position).getNomeProduto());
        holder.tvValorProduto.setText(String.valueOf(listaProduto.get(position).getValorProduto()));
    }

    @Override
    public int getItemCount() {
        return this.listaProduto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCodigo;
        public TextView tvNomeProduto;
        public TextView tvValorProduto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvCodigo = itemView.findViewById(R.id.tvCodigo);
            this.tvNomeProduto = itemView.findViewById(R.id.tvNomeProduto);
            this.tvValorProduto = itemView.findViewById(R.id.tvValorProduto);
        }
    }
}
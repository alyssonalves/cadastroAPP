package com.example.aalve.cadastroapp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aalve on 19/08/2017.
 */

public class PessoaAdaptador extends BaseAdapter {

    private final List<Pessoa> pessoas;
    private final Activity activity;

    public PessoaAdaptador(List<Pessoa> pessoas, Activity activity) {
        this.pessoas = pessoas;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return this.pessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.pessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.pessoas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View linha = convertView;

        Pessoa pessoa = pessoas.get(position);

        if(linha == null){
            linha = this.activity.getLayoutInflater().inflate(
                    R.layout.celula_pessoa,parent, false);
        }

        TextView nome = (TextView) linha.findViewById(R.id.idnomecelula);
        TextView telefone = (TextView) linha.findViewById(R.id.idtelefonecelula);
        TextView email = (TextView) linha.findViewById(R.id.idemailcelula);
        TextView endereco = (TextView) linha.findViewById(R.id.idenderecocelula);
        TextView site = (TextView) linha.findViewById(R.id.idsitecelula);

        nome.setText(pessoa.getNome());
        telefone.setText(pessoa.getTelefone());

        if(email != null){
            email.setText(pessoa.getEmail());
        }

        if(endereco != null){
            endereco.setText(pessoa.getEndereco());
        }

        if(site != null){
            site.setText(pessoa.getSite());
        }

        return linha;
    }
}

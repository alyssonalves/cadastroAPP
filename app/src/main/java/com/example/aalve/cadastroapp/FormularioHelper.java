package com.example.aalve.cadastroapp;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by aalve on 18/08/2017.
 */

public class FormularioHelper {

    private Pessoa pessoa;

    private EditText nome;
    private EditText email;
    private EditText telefone;
    private EditText endereco;
    private EditText site;

    public FormularioHelper(FormularioActivity activity) {
        this.pessoa = new Pessoa();
        this.nome = (EditText) activity.findViewById(R.id.idnomeform);
        this.email = (EditText) activity.findViewById(R.id.idemailform);
        this.telefone = (EditText) activity.findViewById(R.id.idtelefoneform);
        this.endereco = (EditText) activity.findViewById(R.id.idenderecoform);
        this.site = (EditText) activity.findViewById(R.id.idsiteform);
    }

    public Pessoa pegarPessoaForm(){
        pessoa.setNome(nome.getText().toString());
        pessoa.setEmail(email.getText().toString());
        pessoa.setTelefone(telefone.getText().toString());
        pessoa.setEndereco(endereco.getText().toString());
        pessoa.setSite(site.getText().toString());

        return pessoa;
    }

    public void colocarPessoaForm(Pessoa pessoa){
        nome.setText(pessoa.getNome());
        email.setText(pessoa.getEmail());
        telefone.setText(pessoa.getTelefone());
        endereco.setText(pessoa.getEndereco());
        site.setText(pessoa.getSite());

        this.pessoa = pessoa; // o erro foi causado por falta dessa atribuição.

    }
}

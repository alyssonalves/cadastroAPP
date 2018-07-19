package com.example.aalve.cadastroapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormularioActivity extends AppCompatActivity {

    private Button btnSalvar;
    private FormularioHelper helper;

    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = this.getIntent();
        this.pessoa = (Pessoa) intent.getSerializableExtra(
                "pessoaSelecionada");

        if(this.pessoa != null){
            this.helper.colocarPessoaForm(this.pessoa);
        }

        btnSalvar = (Button) findViewById(R.id.idbtnsalvarform);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pessoa pessoa = helper.pegarPessoaForm();

                PessoaDAO pessoaDAO = new PessoaDAO(FormularioActivity.this);

                if(pessoa.getId() == null){
                    pessoaDAO.inserirPessoa(pessoa);
                }else{
                    pessoaDAO.alterarPessoa(pessoa);
                }

                pessoaDAO.close();
                finish();
            }
        });

        Log.i("Meu Log: ",helper.pegarPessoaForm().toString());


    }
}

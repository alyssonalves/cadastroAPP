package com.example.aalve.cadastroapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView minhalista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNovo = (Button) findViewById(R.id.idnovo);

        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        MainActivity.this,FormularioActivity.class);
                startActivity(intent);

            }
        });

        minhalista = (ListView) findViewById(R.id.idminhalista);

        registerForContextMenu(minhalista);

        minhalista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Pessoa pessoa;
                pessoa = (Pessoa) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("pessoaSelecionada", pessoa);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume(){
        carregarLista();
        super.onResume();
    }

    private void carregarLista(){
        PessoaDAO pessoaDAO = new PessoaDAO(this);

        List<Pessoa> pessoas = pessoaDAO.getLista();

        PessoaAdaptador adaptador = new PessoaAdaptador(pessoas, this);

        this.minhalista.setAdapter(adaptador);


    }



}

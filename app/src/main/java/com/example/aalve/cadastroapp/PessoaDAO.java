package com.example.aalve.cadastroapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aalve on 19/08/2017.
 */

public class PessoaDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String TABELA = "TB_CADASTRO";
    private static final String DATABASE = "BD_CADASTRO";

    public PessoaDAO(Context contex) {
        super(contex, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String ddl = "CREATE TABLE " + TABELA
                + " (id INTEGER PRIMARY KEY, "
                + " nome TEXT NOT NULL, "
                + " telefone TEXT, "
                + " email TEXT, "
                + " endereco TEXT, "
                + " site TEXT);";
        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserirPessoa(Pessoa pessoa){
        ContentValues values = new ContentValues();

        values.put("nome", pessoa.getNome());
        values.put("telefone", pessoa.getTelefone());
        values.put("email", pessoa.getEmail());
        values.put("endereco", pessoa.getEndereco());
        values.put("site", pessoa.getSite());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public void excluirPessoa(Pessoa pessoa){

        SQLiteDatabase db = getWritableDatabase();
        String[] args = {pessoa.getId().toString()};
        db.delete(TABELA, "id = ?", args);
    }

    public void alterarPessoa(Pessoa pessoa){

        ContentValues values = new ContentValues();

        values.put("nome", pessoa.getNome());
        values.put("telefone", pessoa.getTelefone());
        values.put("email", pessoa.getEmail());
        values.put("endereco", pessoa.getEndereco());
        values.put("site", pessoa.getSite());

        String[] idParaAlterar = {pessoa.getId().toString()};

        getWritableDatabase().update(
                TABELA, values, "id = ?", idParaAlterar);
    }

    public List<Pessoa> getLista(){

        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        Cursor c = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABELA + ";", null);

        while(c.moveToNext()){

            Pessoa p = new Pessoa();

            p.setId(c.getLong(c.getColumnIndex("id")));
            p.setNome(c.getString(c.getColumnIndex("nome")));
            p.setTelefone(c.getString(c.getColumnIndex("telefone")));
            p.setEndereco(c.getString(c.getColumnIndex("endereco")));
            p.setEmail(c.getString(c.getColumnIndex("email")));
            p.setSite(c.getString(c.getColumnIndex("site")));

            pessoas.add(p);
        }
        c.close();

        return pessoas;

    }

    public boolean isPessoa(String telefone){

        String[] parametros = {telefone};

        Cursor rawQuery = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABELA + "where telefone = ?",parametros);

        int total = rawQuery.getCount();

        return total > 0;

    }
}

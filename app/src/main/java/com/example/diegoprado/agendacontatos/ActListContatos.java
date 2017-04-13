package com.example.diegoprado.agendacontatos;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.database.sqlite.*;
import android.database.*;

import com.example.diegoprado.agendacontatos.dominio.RepositorioContato;

import com.example.diegoprado.agendacontatos.database.DataBase;

public class ActListContatos extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btnAdicionar;
    private EditText edtPesquisa;
    private ListView lstContato;
    private ArrayAdapter<String> adpContatos;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioContato repositorioContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_contato);

        btnAdicionar = (ImageButton) findViewById(R.id.btnAdd);
        edtPesquisa = (EditText) findViewById(R.id.edtPesquisa);
        lstContato = (ListView) findViewById(R.id.lstContatos);

        btnAdicionar.setOnClickListener(this);

        try
        {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioContato = new RepositorioContato(conn);

            adpContatos = repositorioContato.buscaContatos(this);

            lstContato.setAdapter(adpContatos);


        }catch (SQLException ex)
        {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o banco: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ActCadastroContato.class);
        startActivity(intent);
    }
}

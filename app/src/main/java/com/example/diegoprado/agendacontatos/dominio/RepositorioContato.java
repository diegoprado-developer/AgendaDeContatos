package com.example.diegoprado.agendacontatos.dominio;


import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.diegoprado.agendacontatos.dominio.entidades.Contato;

public class RepositorioContato {

    private SQLiteDatabase conn;


    public RepositorioContato(SQLiteDatabase conn)
    {
        this.conn = conn;
    }

    public void inserir(Contato contato){
        ContentValues values = new ContentValues();

        values.put("NOME", contato.getNome());
        values.put("TELEFONE", contato.getTelefone());
        values.put("TIPOTELEFONE", contato.getTipoTelefone());
        values.put("EMAIL", contato.getEmail());
        values.put("TIPOEMAIL", contato.getTipoEmail());
        values.put("ENDERECO", contato.getEndereco());
        values.put("TIPOENDERECO", contato.getTipoEndereco());
        values.put("DATASESPECIAIS", contato.getDatasEspesciais().getTime());
        values.put("TIPODATASESPECIAIS", contato.getTipoDatasEspeciais());
        values.put("GRUPOS", contato.getGrupos());

        conn.insertOrThrow("CONTATO", null, values);
    }

    /*
    public void testeInserirContatos(){

        for(int i = 0; i <= 10; i++)
        {
            //ContentValues reponsavel por armazenar/inserir os dados na base de dados
            ContentValues values = new ContentValues();
            values.put("TELEFONE", "2138548665");

            //Metodo responsavel pra inserir os dados
            //E consegue informar erro, caso tenha algo de errado
            //E retorna o ID da chave primaria da tabela
            conn.insertOrThrow("CONTATO", null, values);
        }
    }*/

    public ArrayAdapter<String> buscaContatos(Context context)
    {
        ArrayAdapter<String> adpContatos = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);

        //Classe para acesso sql -  Armazenar os dados tambem

        //Primeiro parametro passamos o nome da tabela
        Cursor cursor = conn.query("CONTATO", null, null, null, null, null, null);

        if(cursor.getCount() > 0)
        {
            //Metodo posiciona o cursor para o primeiro registro
            cursor.moveToFirst();

            //percorre ate encontrar o telefone
            do
            {

                String telefone = cursor.getString(1);
                adpContatos.add(telefone);

            }while(cursor.moveToNext());
        }

        return adpContatos;
    }
}

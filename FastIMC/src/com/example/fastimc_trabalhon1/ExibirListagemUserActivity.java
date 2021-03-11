package com.example.fastimc_trabalhon1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("DefaultLocale")
public class ExibirListagemUserActivity extends Activity{
	private ListView lvDadosUser;
	private String sendUpId, sendUpNomeUser, sendUpSenhaUser;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exibir_litagem_user);
        
        listaUsuarios();
        
        lvDadosUser = (ListView) findViewById(R.id.lvDadosUser);
        lvDadosUser.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				View view = lvDadosUser.getChildAt(arg2);

				TextView tvIdUser = (TextView) view.findViewById(R.id.tvIdUser);
				sendUpId = tvIdUser.getText().toString();
				TextView tvNomeUser= (TextView) view.findViewById(R.id.tvNomeUser);
				sendUpNomeUser = tvNomeUser.getText().toString();
				TextView tvSenhaUser= (TextView) view.findViewById(R.id.tvSenhaUser);
				sendUpSenhaUser = tvSenhaUser.getText().toString();
				
				abrirAtualizar();
				
				finish();
			}
        	
		});
	
	}
	
	public void abrirAtualizar(){

		Intent i = new Intent(ExibirListagemUserActivity.this,Login_Up.class);
		i.putExtra("sendUpId", sendUpId);
		i.putExtra("sendUpNomeUser", sendUpNomeUser);
		i.putExtra("sendUpSenhaUser", sendUpSenhaUser);

		ExibirListagemUserActivity.this.startActivity(i);
	}
	
	public void listaUsuarios(){
		
		Login_DAO ldao = new Login_DAO(this);
		
    	StringBuilder sqlBuscar= new StringBuilder();
    	sqlBuscar.append("SELECT * FROM LoginIMC");
    	Cursor dados = ldao.con.rawQuery(sqlBuscar.toString(), null);
    	
    	int[] to= {R.id.tvIdUser,R.id.tvNomeUser, R.id.tvSenhaUser};
    	String[] from= {"_id","user","pass"};
    	
    	try{
    		
    		SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.listagem_user, dados, from, to,0);
    		lvDadosUser = (ListView) findViewById(R.id.lvDadosUser);
    		lvDadosUser.setAdapter(ad);

    	}
    	catch(Exception ex)
    	{
    		Toast.makeText(getBaseContext(), sqlBuscar.toString() + "Erro = "+ ex.getMessage(), Toast.LENGTH_LONG).show();
    	}
	}
}

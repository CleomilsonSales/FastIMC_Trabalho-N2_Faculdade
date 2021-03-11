package com.example.fastimc_trabalhon1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

@SuppressLint("SimpleDateFormat") 
public class Login_DAO{
	
	protected SQLiteDatabase con;
	private String usuarioLogado; 
	
	public Login_DAO(Context c){
		Conexao auxCon = new Conexao(c);
		con = auxCon.getWritableDatabase();
		
	}
	
	public void inserirUser(Login l){
		
		ContentValues valores = new ContentValues();
		valores.put("user", l.getvUser());
		valores.put("pass", l.getvPass());
		
		con.insert("LoginIMC", null, valores);

    }
	public void atualizarUser(Login l){
		
		String where = "_id="+l.getvId();
		
		ContentValues valores = new ContentValues();
		valores.put("user", l.getvUser());
		valores.put("pass", l.getvPass());
		
		con.update("LoginIMC", valores, where,null);

    }
	
	public void deletarUser(Login l){
		
		String where = "_id="+l.getvId();
		con.delete("LoginIMC", where,null);

    }

	public boolean CarregarLogin(String user,String pass){
		Boolean retorno = false;
		
		String[] colunas = new String[]{"_id","user","pass"};
		String where = "user='"+user+"'";
		
		Cursor cursor = con.query("LoginIMC", colunas, where, null, null, null, "_id DESC");
		
		if(cursor.getCount() > 0){
			cursor.moveToFirst();
			 do{
				 if ((cursor.getString(1).equals(user))&& (cursor.getString(2).equals(pass))){ 
					 retorno = true;

					 setUsuarioLogado(cursor.getString(1));

					 break;
				 }
			 }while (cursor.moveToNext());
			 
		}
		return retorno;
	}

	public String getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}

package com.example.fastimc_trabalhon1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


@SuppressLint("DefaultLocale")
public class Login_Up extends Activity {
	private String nomeAdmin, nomeAdminR; 
	private Button btUpUser,btDeleteUser;
	private EditText etUpIdUser,etUpNomeUser, etUpSenhaUser,etUpSenhaUserR;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_up);
        
        nomeAdmin = getString(R.string.nomeAdmin).toLowerCase();
        
        etUpNomeUser = (EditText) findViewById(R.id.etUpNomeUser);
        etUpIdUser = (EditText) findViewById(R.id.etUpIdUser);
        etUpSenhaUser = (EditText) findViewById(R.id.etUpSenhaUser);
        etUpSenhaUserR = (EditText) findViewById(R.id.etUpSenhaUserR);
        
        recebendoDados();
        
        btUpUser = (Button) findViewById(R.id.btUpUser);

        btUpUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Auxiliar aux = new Auxiliar(); 
		    	if (aux.verificarCamposUser(etUpNomeUser, etUpSenhaUser, etUpSenhaUserR)){
		    		enviandoAtualizar();
		    	}else{
		    		exibirMensagens(getString(R.string.mensagemCamposVazios));
		    	}
			}
		});
        
        btDeleteUser = (Button) findViewById(R.id.btDeleteUser);

        btDeleteUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String msgBtSim = getString(R.string.sim);
				String msgBtNao = getString(R.string.nao);
				String msgDeletar = getString(R.string.mensagemSimNaoDeletar);
				
				mensagemSimNao(msgDeletar,msgBtSim,msgBtNao);
			}
		});

    }
    
	public void exibirMensagens(String msgRetorno){
        String textBotaoEnviar = getString(R.string.textBotaoEnviar);
        String textTitulo = getString(R.string.textTitulo);
        
    	AlertDialog.Builder msg = new AlertDialog.Builder(this);
    	msg.setTitle(textTitulo);
    	msg.setNeutralButton(textBotaoEnviar, null);
		msg.setMessage(msgRetorno);
		msg.show();
    }
	
	private void recebendoDados() {
		Intent valores = getIntent();
		
        etUpIdUser.setText(valores.getStringExtra("sendUpId"));
        etUpNomeUser.setText(valores.getStringExtra("sendUpNomeUser"));
        nomeAdminR = valores.getStringExtra("sendUpNomeUser");
        etUpSenhaUser.setText(valores.getStringExtra("sendUpSenhaUser"));
        etUpSenhaUserR.setText(valores.getStringExtra("sendUpSenhaUser"));
	}

	public void enviandoAtualizar(){
		try{
			String senha = etUpSenhaUser.getText().toString();
			String senhaR = etUpSenhaUserR.getText().toString();
			String userEditado = getString(R.string.userEditado);
			if(senha.equals(senhaR)){
				Login l = new Login();
				l.setvId(Integer.parseInt(etUpIdUser.getText().toString()));
				l.setvUser(etUpNomeUser.getText().toString());
				l.setvPass(etUpSenhaUser.getText().toString());
				Login_DAO ldao = new Login_DAO(this);
				
				if (!nomeAdminR.equals(nomeAdmin)){
					ldao.atualizarUser(l);
					Toast.makeText(getBaseContext(),l.getvUser()+" "+ userEditado, Toast.LENGTH_LONG).show();
					finish();
		        }else{
		        	String msgErroAdmin = getString(R.string.msgErroAdmin);
		        	Toast.makeText(getBaseContext(),msgErroAdmin, Toast.LENGTH_LONG).show();
		        	finish();
		        }

			}else{
				String erroRepetirSenha = getString(R.string.erroRepetirSenha);
				Toast.makeText(getBaseContext(), erroRepetirSenha, Toast.LENGTH_LONG).show();
			}
			
		}catch(SQLException ex){
			String erroAtualizarUser = getString(R.string.erroAtualizarUser);
			Toast.makeText(getBaseContext(), erroAtualizarUser+" "+ex.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	public void enviandoDelete(){
		String userDeletado = getString(R.string.userDeletado);
		Login_DAO ldao = new Login_DAO(this);     
		try{   
			Login l = new Login();
			l.setvId(Integer.parseInt(etUpIdUser.getText().toString()));
			l.setvUser(etUpNomeUser.getText().toString());
			
			if (!nomeAdminR.equals(nomeAdmin)){
				ldao.deletarUser(l);
				Toast.makeText(getBaseContext(),userDeletado, Toast.LENGTH_LONG).show();
				finish();
	        }else{
	        	String msgErroAdmin = getString(R.string.msgErroAdmin);
	        	Toast.makeText(getBaseContext(),msgErroAdmin, Toast.LENGTH_LONG).show();
	        	finish();
	        }

		}catch(SQLException ex){
			String erroDeleteUser = getString(R.string.erroDeleteUser);
			Toast.makeText(getBaseContext(), erroDeleteUser+" "+ex.getMessage(), Toast.LENGTH_LONG).show();
		}
			
	}
	public void mensagemSimNao(String msgDeletar,String msgBtSim,String msgBtNao){
		//Alert com Sim ou Nao
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
	           switch (which){
	           case DialogInterface.BUTTON_POSITIVE:
	        	   enviandoDelete();
	               break;

	           case DialogInterface.BUTTON_NEGATIVE:
	               //Sem acao
	               break;
	           }
	       }
		};

	   AlertDialog.Builder builder = new AlertDialog.Builder(this);
	   builder.setMessage(msgDeletar).setPositiveButton(msgBtSim, dialogClickListener)
	       .setNegativeButton(msgBtNao, dialogClickListener).show();
		
		//--
	}
}

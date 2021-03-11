package com.example.fastimc_trabalhon1;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login_Add extends Activity {
	
	private Button btLogarAddUser;
	private EditText etUser, etPass,etPassR;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_add);
        
        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);
        etPassR = (EditText) findViewById(R.id.etPassR);
        
        btLogarAddUser = (Button) findViewById(R.id.btLogarAddUser);

        btLogarAddUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Auxiliar aux = new Auxiliar(); 
		    	if (aux.verificarCamposUser(etUser, etPass, etPassR)){
		    		enviandoInserir();
		    	}else{
			    		exibirMensagens(getString(R.string.mensagemCamposVazios));
		    	}
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
	
	public void enviandoInserir(){
		try{
			String senha = etPass.getText().toString();
			String senhaR = etPassR.getText().toString();
			String userInserido = getString(R.string.userInserido);
			if(senha.equals(senhaR)){
				Login l = new Login();
				l.setvUser(etUser.getText().toString());
				l.setvPass(etPass.getText().toString());
				Login_DAO ldao = new Login_DAO(this);
				ldao.inserirUser(l);
				Toast.makeText(getBaseContext(), userInserido+" "+l.getvUser(), Toast.LENGTH_LONG).show();
				finish();
			}else{
				String erroRepetirSenha = getString(R.string.erroRepetirSenha);
				Toast.makeText(getBaseContext(), erroRepetirSenha, Toast.LENGTH_LONG).show();
			}
			
		}catch(SQLException ex){
			String erroInsirirUser = getString(R.string.erroInsirirUser);
			Toast.makeText(getBaseContext(), erroInsirirUser+" "+ex.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

}

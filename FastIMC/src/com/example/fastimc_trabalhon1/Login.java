package com.example.fastimc_trabalhon1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	
	private int vId;
	private String vUser,vPass;
	private Button btLogar;
	private EditText etUser, etPass;
	private TextView tvCriarUser;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);
        btLogar = (Button) findViewById(R.id.btLogar);
        tvCriarUser = (TextView) findViewById(R.id.tvNovoUser);

        tvCriarUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AbrirAddUser();
			}
		});
        
        btLogar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				validandoUser();

			}
		});

    }
	
	public void AbrirAddUser(){
			Intent i = new Intent(Login.this,Login_Add.class);
			Login.this.startActivity(i);
		
	}
	
	public void validandoUser(){
		Login_DAO ldao = new Login_DAO(this);
		if (ldao.CarregarLogin(etUser.getText().toString(),etPass.getText().toString())){
			Intent i = new Intent(Login.this,Principal.class);
			i.putExtra("usuarioLogado", ldao.getUsuarioLogado());

			Login.this.startActivity(i);
		}else{
			String msgUserInvalido = getString(R.string.msgUsuarioInvalido);
			Toast.makeText(getBaseContext(), msgUserInvalido, Toast.LENGTH_LONG).show();
		}
	}

	public void carrendandoVarMsg(Auxiliar aux){
    	aux.setMensagemIMCErro(getString(R.string.mensagemIMCErro));
    	aux.setMensagemIMCBaixo(getString(R.string.mensagemIMCBaixo));
    	aux.setMensagemIMCNormal(getString(R.string.mensagemIMCNormal));
    	aux.setMensagemIMCMagAcima(getString(R.string.mensagemIMCMagAcima));
    	aux.setMensagemIMCAcima(getString(R.string.mensagemIMCAcima));
    	aux.setMensagemIMCObeso(getString(R.string.mensagemIMCObeso));
    	aux.setMensagemCamposVazios(getString(R.string.mensagemCamposVazios));
	}

	
	public int getvId() {
		return vId;
	}

	public void setvId(int vId) {
		this.vId = vId;
	}

	public String getvUser() {
		return vUser;
	}

	public void setvUser(String vUser) {
		this.vUser = vUser;
	}

	public String getvPass() {
		return vPass;
	}

	public void setvPass(String vPass) {
		this.vPass = vPass;
	}
	
    

}

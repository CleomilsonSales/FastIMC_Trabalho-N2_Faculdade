package com.example.fastimc_trabalhon1;

public class Historico {
	
	protected int vId;
	protected double vPeso, vAltura; 
	protected String vUser,vSexo,vData,vResultado,vResultTexto;
	
	public Historico(){
		//vazio mesmo
	}
	

	public String getvData() {
		return vData;
	}

	public void setvData(String vData) {
		this.vData = vData;
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

	public double getvPeso() {
		return vPeso;
	}

	public void setvPeso(double vPeso) {
		this.vPeso = vPeso;
	}

	public double getvAltura() {
		return vAltura;
	}

	public void setvAltura(double vAltura) {
		this.vAltura = vAltura;
	}


	public String getvResultado() {
		return vResultado;
	}


	public void setvResultado(String vResultado) {
		this.vResultado = vResultado;
	}


	public String getvSexo() {
		return vSexo;
	}

	public void setvSexo(String vSexo) {
		this.vSexo = vSexo;
	}


	public String getvResultTexto() {
		return vResultTexto;
	}


	public void setvResultTexto(String vResultTexto) {
		this.vResultTexto = vResultTexto;
	}
	

}

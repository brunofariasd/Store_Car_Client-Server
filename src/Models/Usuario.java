package Models;

import java.io.Serializable;

import json.JSONObject;

public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String cpf;
	private String senha;
	private int tipo;
	
	public Usuario(String nome, String cpf, String senha, int tipo){	
		this.setNome(nome);
		this.setCpf(cpf);
		this.setSenha(senha);
		this.setTipo(tipo);
	}
	
	public Usuario(JSONObject j) {
		this.nome = j.getString("nome");
		this.cpf = j.getString("cpf");
		this.senha = j.getString("senha");	
		this.setTipo(j.getInt("tipo"));
	}
	
	public json.JSONObject toJson() {
		json.JSONObject json = new json.JSONObject();
		json.put("nome", this.nome);
		json.put("cpf", this.cpf);
		json.put("senha", this.senha);		
		json.put("tipo", this.tipo);
		return json;
	}	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
}

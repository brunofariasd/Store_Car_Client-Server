package controllers;

import java.util.ArrayList;

import Models.Usuario;

public class Auth {
	
	static public boolean adicionarUsuario(String nome, String cpf, String senha, int tipo) {
		ArrayList<Usuario> listaDeUsuarios = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Usuarios.txt");
		
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		for (int i = 0; i < jA.length(); i++) {
			Usuario usuario= new Usuario(jA.getJSONObject(i));
			listaDeUsuarios.add(usuario);
		}
		
		Usuario Usuario = new Usuario(nome, cpf, senha, tipo);
		listaDeUsuarios.add(Usuario);
		
		json.JSONArray jsArray = new json.JSONArray();
		for (Usuario l : listaDeUsuarios) {
			jsArray.put(l.toJson());
		}
		Arquivo.gravarArquivo("src/data/Usuarios.txt", jsArray);
		
		return true;
	}
	
	public static Usuario buscaUsuarioCPF(String cpf) {
		ArrayList<Usuario> listaDeUsuarios = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Usuarios.txt");
		Usuario result = null;
		
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		for (int i = 0; i < jA.length(); i++) {
			Usuario usuario= new Usuario(jA.getJSONObject(i));
			if (usuario.getCpf().equals(cpf)) { 
				result = usuario;
			}
			listaDeUsuarios.add(usuario);
		}

		json.JSONArray jsArray = new json.JSONArray();
		for (Usuario l : listaDeUsuarios) {
			jsArray.put(l.toJson());
		}
		Arquivo.gravarArquivo("src/data/Usuarios.txt", jsArray);
		
		return result;
	}
	
	public static String logar(String cpf, String senha) {
		String textoCompleto = Arquivo.lerArquivo("src/data/Usuarios.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		String logou = "auth:Login falhou";
		for (int i = 0; i < jA.length(); i++) {
			Usuario contaUsuario= new Usuario(jA.getJSONObject(i));
			
			if(contaUsuario.getCpf().equals(cpf) && contaUsuario.getSenha().equals(senha)) {
				logou = "auth:Logado com sucesso:"+contaUsuario.getTipo();
			}
		}		
		return logou;
	}

}

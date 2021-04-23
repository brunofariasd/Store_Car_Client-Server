package controllers;

import java.util.ArrayList;

import Models.Carro;
import Models.notaFiscal;


public class CarroController {
	
	static public boolean adicionarCarro(String nome, String categoria, int ano, double preco, String renavam) {
		ArrayList<Carro> listaDeCarros = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Carros.txt");
		
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		for (int i = 0; i < jA.length(); i++) {
			Carro Carro= new Carro(jA.getJSONObject(i));
			listaDeCarros.add(Carro);
		}
		
		Carro carro = new Carro(nome, categoria, ano, preco, renavam);
		listaDeCarros.add(carro);
		
		json.JSONArray jsArray = new json.JSONArray();
		for (Carro l : listaDeCarros) {
			jsArray.put(l.toJson());
		}
		Arquivo.gravarArquivo("src/data/Carros.txt", jsArray);
		
		return true;
	}	
	
	static public String alterarCarro(String campoConsulta, String valorConsulta, String nome, String categoria, int ano, double preco, String renavam) {
		
		ArrayList<Carro> listaDeCarros = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Carros.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		
		String mensagem = "O Carro correspondente a esse Nome ou Renavam não foi encontrado, ou ja foi excluido.";
		boolean achou = false;
		for (int i = 0; i < jA.length(); i++) {
			Carro carro= null;			
			carro = new Carro(jA.getJSONObject(i));	
			
			if (achou == false) {
				if (campoConsulta.equalsIgnoreCase("nome")) {
				    if (carro.getNome().toUpperCase().contains(valorConsulta.toUpperCase())) {
				    	Carro carroAlterado = new Carro(nome, categoria, ano, preco, renavam);
						listaDeCarros.add(carroAlterado);
						achou = true;
						mensagem = "Dados do carro alterado com sucesso";
				    } else { listaDeCarros.add(carro); }
				} else {
					if (carro.getRenavam().toUpperCase().contains(valorConsulta.toUpperCase())) {
						Carro carroAlterado = new Carro(nome, categoria, ano, preco, renavam);
						listaDeCarros.add(carroAlterado);
						achou = true;
						mensagem = "Dados do carro alterado com sucesso";
				    } else { listaDeCarros.add(carro); }
				}
			} else {
			
				listaDeCarros.add(carro);
			}
		}
		
		
		
		json.JSONArray jsArray = new json.JSONArray();
		for (Carro l : listaDeCarros) {
			jsArray.put(l.toJson());
		}
		Arquivo.gravarArquivo("src/data/Carros.txt", jsArray);
		System.out.println(mensagem);
		return mensagem;
	}
	
	static public String apagarCarro(String renavam) {
		
		ArrayList<Carro> listaDeCarros = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Carros.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		
		String mensagem = "O Carro correspondente a esse Renavam não foi encontrado, ou ja foi excluido.";
		for (int i = 0; i < jA.length(); i++) {
			Carro carro= null;			
			carro = new Carro(jA.getJSONObject(i));	
			if(carro.getRenavam().equals(renavam)) {
				mensagem  = "Operacao bem sucedida.";
			}else {
				listaDeCarros.add(carro);	
			}			
		}
		json.JSONArray jsArray = new json.JSONArray();
		for (Carro l : listaDeCarros) {
			jsArray.put(l.toJson());
		}
		Arquivo.gravarArquivo("src/data/Carros.txt", jsArray);
		System.out.println(mensagem);
		return mensagem;
	}

	static public String listarCarro(String campoConsulta, String valorConsulta) {
		ArrayList<Carro> listaDeCarros = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Carros.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		String mensagem = "Lista de Carros: ;";
		for (int i = 0; i < jA.length(); i++) {
			Carro carro= null;			
			carro = new Carro(jA.getJSONObject(i));	
			if(campoConsulta.equalsIgnoreCase("nome")) {
				if(carro.getNome().toUpperCase().contains(valorConsulta.toUpperCase())) {									
					mensagem = mensagem +   ";Nome: "+carro.getNome()+";"+
											"Categoria: "+carro.getCategoria()+";"+
											"Ano: "+carro.getAno()+";"+
											"Preco: "+carro.getPreco()+";"+
											"Renavam: "+carro.getRenavam()+";"+
											";";	
				}
			}else {
				if(carro.getRenavam().toUpperCase().contains(valorConsulta.toUpperCase())) {									
					mensagem = mensagem +   ";Nome: "+carro.getNome()+";"+
											"Categoria: "+carro.getCategoria()+";"+
											"Ano: "+carro.getAno()+";"+
											"Preco: "+carro.getPreco()+";"+
											"Renavam: "+carro.getRenavam()+";"+
											";";	
				}
			}
			listaDeCarros.add(carro);
		}		
		System.out.println(mensagem);
		return mensagem;
	}
	
	static public String listarCarros(String categoria) {
		ArrayList<Carro> listaDeCarros = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Carros.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		String mensagem = "Lista de Carros: ;";
		for (int i = 0; i < jA.length(); i++) {
			Carro carro= null;			
			carro = new Carro(jA.getJSONObject(i));	
			if(categoria.equalsIgnoreCase("all")) {
				mensagem = mensagem +   ";Nome: "+carro.getNome()+";"+
										"Categoria: "+carro.getCategoria()+";"+
										"Ano: "+carro.getAno()+";"+
										"Preco: "+carro.getPreco()+";"+
										"Renavam: "+carro.getRenavam()+";"+
										";";
			}else {
				if(carro.getCategoria().equals(categoria)) {									
					mensagem = mensagem +   ";Nome: "+carro.getNome()+";"+
											"Categoria: "+carro.getCategoria()+";"+
											"Ano: "+carro.getAno()+";"+
											"Preco: "+carro.getPreco()+";"+
											"Renavam: "+carro.getRenavam()+";"+
											";";	
				}
			}
			listaDeCarros.add(carro);
		}		
		System.out.println(mensagem);
		return mensagem;
	}
	
	static public String listaQtdCarros() {
		
		String textoCompleto = Arquivo.lerArquivo("src/data/Carros.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		
		String mensagem = "Quantidade de Carros cadastrados no sistema: "+jA.length();
		System.out.println(mensagem);
		return mensagem;
	}
	
	static public String compraCarro(String nomeUsuario, String Cpf, String nascimentoUsuario, String endereco, String Renavam) {
		
		ArrayList<Carro> listaDeCarros = new ArrayList<>();
		ArrayList<notaFiscal> carrosComprados = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Carros.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		
		notaFiscal notaFiscalComprador = null;
		
		String mensagem = "Lista da Nota Fiscal: ;";
		for (int i = 0; i < jA.length(); i++) {
			Carro carro= null;			
			carro = new Carro(jA.getJSONObject(i));	
			if(carro.getRenavam().toUpperCase().contains(Renavam.toUpperCase())) {									
				mensagem = mensagem +   ";Nome Comprador: "+nomeUsuario+";"+
										"CPF: "+Cpf+";"+
										"Data de Nascimento: "+nascimentoUsuario+";"+
										"Endereco: "+endereco+";"+
										";Carro: "+carro.getNome()+";"+
										"Ano: "+carro.getAno()+";"+
										"Preco: "+carro.getPreco()+";"+
										"Renavam: "+carro.getRenavam()+";"+
										";";	
				notaFiscalComprador = new notaFiscal(nomeUsuario, Cpf, nascimentoUsuario, endereco, carro.getNome(), carro.getAno(), carro.getPreco(), carro.getRenavam());
			} else { listaDeCarros.add(carro); }
		}
		
		json.JSONArray jsArray = new json.JSONArray();
		for (Carro l : listaDeCarros) {
			jsArray.put(l.toJson());
		}
		Arquivo.gravarArquivo("src/data/Carros.txt", jsArray);
		

		textoCompleto = Arquivo.lerArquivo("src/data/NotasFiscais.txt");
		jA = new json.JSONArray(textoCompleto);
		
		for (int i = 0; i < jA.length(); i++) {
			notaFiscal nota= new notaFiscal(jA.getJSONObject(i));
			carrosComprados.add(nota);
		}
		if (notaFiscalComprador != null) { carrosComprados.add(notaFiscalComprador); }
		
		jsArray = new json.JSONArray();
		for (notaFiscal l : carrosComprados) {
			jsArray.put(l.toJson());
		}
		Arquivo.gravarArquivo("src/data/NotasFiscais.txt", jsArray);
		
		System.out.println(mensagem);
		return mensagem;
	}


}

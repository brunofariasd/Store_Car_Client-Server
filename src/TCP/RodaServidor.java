package TCP;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import controllers.Auth;

public class RodaServidor {
	ArrayList<String> nomeDosArquivos = new ArrayList<String>();
	ArrayList<String> caminhoDosArquivos = new ArrayList<String>();
	public RodaServidor() throws IOException {

		ServerSocket server = new ServerSocket(12345);
		Thread aceitarClientes =new Thread(new Runnable() {

		
			@Override
			public void run() {

				while(true){
					System.out.println("\nAguardando conexão...");
					Socket con;
					try {
						con = server.accept();
						System.out.println("\nCliente conectado...");
						Thread t;
						t = new Thread(new Servidor(con));
						t.start();	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			              
				}
			}
		});
		aceitarClientes.start();
		
		Scanner sc=new Scanner(System.in);
		
		System.out.print("Você ja possui conta na nossa Loja (y/n): ");
		String verificaConta = sc.nextLine();
		if (verificaConta.equalsIgnoreCase("n")) {
			System.out.print("Quantas contas deseja criar: ");
			int n = sc.nextInt();
			for (int i = 0; i < n; i++) {
				sc.nextLine();
				System.out.print("Digite o seu Nome: ");
				String nome = sc.nextLine();
				System.out.print("Digite o seu CPF (apenas numeros): ");
				String cpf = sc.nextLine();
				System.out.print("Digite a sua Senha: ");
				String senha = sc.nextLine();
				System.out.print("Digite o tipo de conta... 0 para funcionarios e 1 para cliente: ");
				int tipo = sc.nextInt();
				
				Auth.adicionarUsuario(nome, cpf, senha, (tipo == 0) ? 0 : 1);
								
				System.out.println("Conta cadastrada com Sucesso!!!");
			}
			System.out.println("Para realizar seu login utilize seu CPF e Senha, na aba do cliente!!!");
			System.out.print("\nNumero de contas no Sistema: "+n);
		} else {
			System.out.println("Por favor realize seu Login na aba de Clientes!!!");
		}		
	}
	
	public static void main(String [] args) throws UnknownHostException, IOException {
		RodaServidor s = new RodaServidor();
	}
	
}


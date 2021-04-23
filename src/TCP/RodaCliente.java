package TCP;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;


public class RodaCliente {
	
	public static ArrayList<String> dataCar=new ArrayList<String>();
	public static ArrayList<String> buyCar=new ArrayList<String>();
	Scanner sc=new Scanner(System.in);
	ClienteRunnable c;
	public RodaCliente() throws UnknownHostException, IOException {
		Thread escutarServidor =new Thread(new Runnable() {
			@Override
			public void run() {
				Socket socket;
				try {
					socket = new Socket("127.0.0.1", 12345);
					/**Cria um novo objeto Cliente com a conexão socket para que seja executado em um novo
						processo. Permitindo assim a conexão de vário clientes com o servidor.*/
					c = new ClienteRunnable(socket);
					Thread t = new Thread(c);
					t.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
		escutarServidor.start();
		
		System.out.println("Olá bem vindo a loja BadUser666!!!");
		System.out.println("Para conseguir realizar operações sobre a sua conta, siga com os passos da autenticação !");
		
		System.out.print("Digite seu CPF: ");
		String cpf = sc.nextLine();
		System.out.print("Digite sua senha: ");
		String senha = sc.nextLine();
		c.enviarMensagem("auth"+","+cpf+","+senha);
		
		System.out.println("\nAguardando Status do Servidor ...\n");
		while(c.getStatusLogin().equalsIgnoreCase("waiting")) {
			// AGUARDA RESPOSTA DO SERVIDOR
		}
		
		int escolha = 0;
		if (c.getStatusLogin().equalsIgnoreCase("OK")) {
			escolha = -1;
		} else { System.exit(0); }
		
		while(escolha != 0) {
			exibeMenu(c.getTipoCliente());
			System.out.print("Escolha uma opção: ");
			escolha = sc.nextInt();
		
			sc.nextLine();
			if (c.getTipoCliente() == 1) {
				
				switch(escolha) {
				case 0: 
					c.encerrarConexao();
					System.exit(0);
					break;
				case 1: 
					System.out.print("\n\nPor qual campo deseja buscar o carro (Nome/Renavam): ");
					String srchTipo = sc.nextLine();
					System.out.print("Digite os dados do campo a ser Buscado: ");
					String srchCampo = sc.nextLine();
					c.enviarMensagem("srch"+","+srchTipo+","+srchCampo);
					break;
				case 2:
					System.out.print("\nDigite a categoria dos carros a serem buscados ou all para serem buscados todos: ");
					String categoria = sc.nextLine();
					c.enviarMensagem("cons"+","+categoria);
					break;
				case 3:
					c.enviarMensagem("qtdC");
					break;
				case 4: 
					coletaDadosComprador();
					c.enviarMensagem("buyC"+","+buyCar.get(0)+","+buyCar.get(1)+","+buyCar.get(2)+","+buyCar.get(3)+","+buyCar.get(4));
					break;
				default: 
					System.out.print("Opção invalida: ");
					break;
			}
				
			} else {
				switch(escolha) {
					case 0: 
						c.encerrarConexao();
						System.exit(0);
						break;
						
					case 1: 
						System.out.println("\n######### INSIRA OS DADOS DO NOVO CARRO #########");
						coletaDadosCarro();
						c.enviarMensagem("addC"+","+dataCar.get(0)+","+dataCar.get(1)+","+dataCar.get(2)+","+dataCar.get(3)+","+dataCar.get(4));
						break;				
					case 2: 
						System.out.print("\n\nPor qual campo deseja buscar o carro ao ser alterado (Nome/Renavam): ");
						String tipoCons = sc.nextLine();
						System.out.print("Digite os dados do campo a ser Buscado: ");
						String campoCons = sc.nextLine();
						System.out.println("\n########Insira os novos dados do Carro Alterado !########\n");
						coletaDadosCarro();
						c.enviarMensagem("edit"+","+tipoCons+","+campoCons+","+dataCar.get(0)+","+dataCar.get(1)+","+dataCar.get(2)+","+dataCar.get(3)+","+dataCar.get(4));
						break;
					case 3: 
						System.out.println("\nDigite o Renavam do carro a ser apagado: ");
						String carroDelete = sc.nextLine();
						c.enviarMensagem("delC"+","+carroDelete);
						break;
					case 4: 
						System.out.print("\n\nPor qual campo deseja buscar o carro ao ser alterado (Nome/Renavam): ");
						String srchTipo = sc.nextLine();
						System.out.print("Digite os dados do campo a ser Buscado: ");
						String srchCampo = sc.nextLine();
						c.enviarMensagem("srch"+","+srchTipo+","+srchCampo);
						break;
					case 5:
						System.out.print("\nDigite a categoria dos carros a serem buscados ou all para serem buscados todos: ");
						String categoria = sc.nextLine();
						c.enviarMensagem("cons"+","+categoria);
						break;
					case 6:
						c.enviarMensagem("qtdC");
						break;
					case 7: 
						coletaDadosComprador();
						c.enviarMensagem("buyC"+","+buyCar.get(0)+","+buyCar.get(1)+","+buyCar.get(2)+","+buyCar.get(3)+","+buyCar.get(4));
						break;
					default: 
						System.out.print("Opção invalida: ");
						break;
				}
			}
		}
		sc.close();
	}
	
	public void coletaDadosComprador() {
		
		buyCar.clear();
		System.out.print("Digite o seu nome completo: ");
		buyCar.add(sc.nextLine());
		System.out.print("Digite o seu CPF: ");
		buyCar.add(sc.nextLine());
		System.out.print("Digite a sua data de Nascimento: ");
		buyCar.add(sc.nextLine());
		System.out.print("Digite o seu endereco: ");
		buyCar.add(sc.nextLine());
		System.out.print("\nDigite o renavam do Carro: ");
		buyCar.add(sc.nextLine());
		
	}
	
	public void coletaDadosCarro() {
		
		dataCar.clear();
		System.out.print("Digite o nome do Carro: ");
		dataCar.add(sc.nextLine());
		System.out.print("Digite categoria do Carro: ");
		dataCar.add(sc.nextLine());
		System.out.print("Digite o ano do Carro: ");
		dataCar.add(sc.nextLine());
		System.out.print("Digite o valor do Carro: ");
		dataCar.add(sc.nextLine());
		System.out.print("Digite o renavam do Carro: ");
		dataCar.add(sc.nextLine());
		
	}	
	
	public void exibeMenu(int tipoUsuario) {
		if (tipoUsuario == 0) {
			
			System.out.println("\n------Menu da Loja------\n");
			System.out.println("0- Sair");
			System.out.println("1- Adicionar Carro");
			System.out.println("2- Alterar Carro");
			System.out.println("3- Apagar Carro");
			System.out.println("4- Listar Carro pelo Nome/Renavam");
			System.out.println("5- Listar Carro pela categoria");
			System.out.println("6- Listar Quantidade de Carros Cadastrados");
			System.out.println("7- Comprar Carro");	
			
		} else {
			
			System.out.println("\n------Menu da Loja------\n\n");
			System.out.println("0- Sair");
			System.out.println("1- Listar Carro pelo Nome/Renavam");
			System.out.println("2- Listar Carro pela categoria");
			System.out.println("3- Listar Quantidade de Carros Cadastrados");
			System.out.println("4- Comprar Carro");
		}
	} 
	
	public static String [] msgSeparada(String msg) {
		
		String [] arrayString = msg.split(":");
		return arrayString;
	}
	
	public static void main(String args[]) throws UnknownHostException, IOException {
		new RodaCliente(); 
	}
		
}
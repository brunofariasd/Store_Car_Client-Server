package TCP;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import controllers.Auth;
import controllers.CarroController;
import controllers.metodosEncriptacao;


public class Servidor implements Runnable{
public Socket client;
boolean auth = false;
private static ArrayList<Socket> clientesConectados = new ArrayList<Socket>();

public Servidor(Socket cliente) throws IOException{
	this.client = cliente;	
	Servidor.clientesConectados.add(cliente);
}

public Socket getCliente() {
	return client;
}
/* A classe Thread, que foi instancia no servidor, implementa Runnable.
 Então você terá que implementar sua lógica de troca de mensagens dentro deste método 'run'. */
 public void run(){
	try {
		
		String dados = "";
		String dadosDoUsuario[];
		Scanner s = null;
		s = new Scanner(this.client.getInputStream());
		String rcv;
		
		while(s.hasNextLine()){
			rcv = s.nextLine();
			System.out.println("Texto encriptado"+ rcv);
			rcv = metodosEncriptacao.decriptarCifraCesar(3, rcv);
			System.out.println("Texto decriptado"+ rcv);
			if (rcv.equalsIgnoreCase("fim"))
				break;
			else {			
				
				String metodo = rcv.substring(0, 4);
				if ( rcv.length() > 4) {  dados = rcv.substring(5, rcv.length()); }
 				
				
				switch (metodo) {
				case "auth":
					dadosDoUsuario = msgSeparada(dados);					
					enviarMensagem(Auth.logar(dadosDoUsuario[0], dadosDoUsuario[1]));
					break;
				case "addC":
					dadosDoUsuario = msgSeparada(dados);
					if (CarroController.adicionarCarro(dadosDoUsuario[0], dadosDoUsuario[1], Integer.parseInt(dadosDoUsuario[2]), Double.parseDouble(dadosDoUsuario[3]), dadosDoUsuario[4])) {
						enviarMensagem("Carro cadastrado !");
						enviarMensagemBroadcast(CarroController.listarCarros("all"));
					} else { enviarMensagem("Erro ao adicionar o Carro !"); }
					break;
				case "edit":
					dadosDoUsuario = msgSeparada(dados);
					enviarMensagem(CarroController.alterarCarro(dadosDoUsuario[0], dadosDoUsuario[1], dadosDoUsuario[2], dadosDoUsuario[3], Integer.parseInt(dadosDoUsuario[4]), Double.parseDouble(dadosDoUsuario[5]), dadosDoUsuario[6]));				
					enviarMensagemBroadcast(CarroController.listarCarros("all"));
					break;
				case "delC":
					dadosDoUsuario = msgSeparada(dados);
					enviarMensagem(CarroController.apagarCarro(dadosDoUsuario[0]));
					enviarMensagemBroadcast(CarroController.listarCarros("all"));
					break;
				case "srch":
					dadosDoUsuario = msgSeparada(dados);
					enviarMensagem(CarroController.listarCarro(dadosDoUsuario[0], dadosDoUsuario[1]));
					break;
				case "cons":
					dadosDoUsuario = msgSeparada(dados);
					enviarMensagem(CarroController.listarCarros(dadosDoUsuario[0]));
					break;
				case "qtdC":
					enviarMensagem(CarroController.listaQtdCarros());
					break;
				case "buyC":
					dadosDoUsuario = msgSeparada(dados);
					enviarMensagem(CarroController.compraCarro(dadosDoUsuario[0], dadosDoUsuario[1], dadosDoUsuario[2], dadosDoUsuario[3], dadosDoUsuario[4]));
					enviarMensagemBroadcast(CarroController.listarCarros("all"));
					break;
				default:
					break;
				}
			}
	
		}
		//Finaliza scanner e socket
				s.close();
				this.client.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
 
	 public void enviarMensagem(String mensagem) throws IOException{
			PrintStream saida = new PrintStream(client.getOutputStream());
			saida.println(metodosEncriptacao.encriptarCifraCesar(3,mensagem));
	 }
 
	 public void enviarMensagemBroadcast(String mensagem) throws IOException {
		for (int counter = 0; counter < clientesConectados.size(); counter++) {
			try{
				PrintStream saida = new PrintStream(clientesConectados.get(counter).getOutputStream());
				saida.println(metodosEncriptacao.encriptarCifraCesar(3,mensagem));
			} catch(Exception ex){
			    	/*client.remove*/
				clientesConectados.remove(counter);
				System.out.println(ex.getMessage());
			}
		}
	 }
 
	public static String [] msgSeparada(String msg) {
		
		String [] arrayString = msg.split(",");
		return arrayString;
	}
 
}
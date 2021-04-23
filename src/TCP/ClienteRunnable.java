package TCP;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import controllers.metodosEncriptacao;
public class ClienteRunnable implements Runnable{

	private Socket cliente;
	private int tipoCliente;
	private String statusLogin;
	public ClienteRunnable(Socket c){
		this.cliente = c;
		this.tipoCliente = -1;
		this.statusLogin = "waiting";
	}

	public void run() {
		//Escutar servidor
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				Scanner s = null;
				try {				
					s = new Scanner(cliente.getInputStream());
					String mensagem;
					String [] msg;
					 //Exibe mensagem no console
					while(s.hasNextLine()){
						mensagem = s.nextLine();
	
							mensagem = metodosEncriptacao.decriptarCifraCesar(3, mensagem);
							
							System.out.println("\n\n##########################  NOVA MENSAGEM RECEBIDA!  ##########################\n");
							msg = msgSeparada(mensagem);
							if (msg[0].equalsIgnoreCase("auth")) {
								if (msg[1].contains("sucesso")) {
									setTipoCliente(Integer.parseInt(msg[2]));
									setStatusLogin("OK");
									System.out.println(msg[1]);
								} else {
									setStatusLogin("BAD");
									System.out.println(msg[1]); 
								}
							} else {
								if (mensagem.substring(0, 5).equalsIgnoreCase("lista")) {
									System.out.println("################################# (BROADCAST) #################################\n");
									System.out.println(mensagem.replaceAll(";", "\n"));
								} else { System.out.println(mensagem); }
							}
							System.out.println("\n###############################################################################\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				s.close();
			}
		});
		
		t.start();	
	}

	public void enviarMensagem(String mensagem) throws IOException {
		PrintStream saida = new PrintStream(cliente.getOutputStream());
		saida.println(metodosEncriptacao.encriptarCifraCesar(3,mensagem));
	}
	
	public void encerrarConexao() throws IOException {
		PrintStream saida = new PrintStream(cliente.getOutputStream());	
		saida.println("fim");
		saida.close();
		cliente.close();
		
		System.out.println("Cliente finaliza conexão.");
	}

	 public static String [] msgSeparada(String msg) {
			
		String [] arrayString = msg.split(":");
		
		return arrayString;
	}

	public int getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(int tipocliente) {
		this.tipoCliente = tipocliente;
	}

	public String getStatusLogin() {
		return statusLogin;
	}

	public void setStatusLogin(String statusLogin) {
		this.statusLogin = statusLogin;
	}
	
}

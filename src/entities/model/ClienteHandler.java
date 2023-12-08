package entities.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClienteHandler implements Runnable {
	
	private Socket cliente;
	private PrintWriter clienteWriter;
	
	public ClienteHandler(Socket cliente, PrintWriter clienteWriter) {
		this.cliente = cliente;
		this.clienteWriter = clienteWriter;
	}
	
	@Override
	public void run() {
		try {
			
			BufferedReader fluxoEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			String msgEntrada;
			
			while(!(msgEntrada = fluxoEntrada.readLine()).equals("79466497")) {
				
				compartilharMensagens(msgEntrada);
			}
			compartilharMensagens("Cliente desconectado: " + cliente.getInetAddress());
			ServerControl.clienteWritersList.remove(clienteWriter);
			cliente.close();
			fluxoEntrada.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro de comunicação com o cliente...", "Servidor", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		
	}
	
	public synchronized static void compartilharMensagens(String msg) {
		for(PrintWriter pw : ServerControl.clienteWritersList) {
			pw.println(msg);
			pw.flush();
		}
	}

}

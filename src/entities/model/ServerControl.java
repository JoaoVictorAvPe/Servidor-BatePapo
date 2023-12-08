package entities.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.view.ServerGUI;

public class ServerControl {
	private ServerGUI serverGUI;
	private ServerSocket servidor;
	private Socket cliente;
	private Thread serverThread;
	private Thread clienteHandlerThread;
	public static ArrayList<PrintWriter> clienteWritersList = new ArrayList<>();

	public ServerControl(ServerGUI serverGUI) {
		this.serverGUI = serverGUI;
		eventos();
	}

	private void eventos() {
		serverGUI.getBtnIniciar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				iniciarServidor();
			}
		});

		serverGUI.getBtnEncerrar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {	
					servidor.close();
					cliente.close();
					serverThread.interrupt();
					clienteHandlerThread.interrupt();
					JOptionPane.showMessageDialog(serverGUI, "Servidor encerrado...", "Servidor", JOptionPane.INFORMATION_MESSAGE);
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(serverGUI, "Nenhum cliente conectado\nServidor encerrado...", "Servidor", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	public void iniciarServidor() {

		int porta = Integer.parseInt(serverGUI.getTxtPorta().getText());
		try {
			this.servidor = new ServerSocket(porta);
			InetAddress enderecoRede = InetAddress.getLocalHost();
			String ipServidor = enderecoRede.getHostAddress();
			serverGUI.getTxtIp().setText(ipServidor);
			JOptionPane.showMessageDialog(serverGUI, "Servidor iniciado...", "Server", JOptionPane.INFORMATION_MESSAGE);

			this.serverThread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						while (true) {
							System.out.println("Aguardando conexoes");
							cliente = servidor.accept();
							PrintWriter clienteWriter = new PrintWriter(cliente.getOutputStream(), true);
							clienteWriter.println("Bem Vindo");
							clienteWriter.flush();
							ClienteHandler.compartilharMensagens("Cliente conectado: " + cliente.getInetAddress());
							clienteWritersList.add(clienteWriter);

							clienteHandlerThread = new Thread(new ClienteHandler(cliente, clienteWriter));
							clienteHandlerThread.start();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
			});
			
			serverThread.start();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(serverGUI, "Erro inesperado...", "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public ServerGUI getServerGUI() {
		return serverGUI;
	}

	public ServerSocket getServidor() {
		return servidor;
	}

	public static ArrayList<PrintWriter> getClienteWritersList() {
		return clienteWritersList;
	}

}

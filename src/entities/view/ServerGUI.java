package entities.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServerGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel pnl1;
	private JLabel lblIp;
	
	private JPanel pnl2;
	private JTextField txtIp;
	
	private JPanel pnl3;
	private JLabel lblPorta;
	
	private JPanel pnl4;
	private JTextField txtPorta;
	
	private JPanel pnl5;
	private JButton btnIniciar;
	private JButton btnEncerrar;
	
	
	public ServerGUI() {
		iniciar();
		componentes();
		this.setSize(220, 250);
	}
	
	private void iniciar() {
		this.setTitle("Server");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(400, 200);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	private void componentes() {
		this.getContentPane().setLayout(new GridLayout(5, 1));
		this.getContentPane().add(getPnl1());
		this.getContentPane().add(getPnl2());
		this.getContentPane().add(getPnl3());
		this.getContentPane().add(getPnl4());
		this.getContentPane().add(getPnl5());
	}
	
	private JPanel getPnl1() {
		if(pnl1 == null) {
			pnl1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		}
		
		lblIp = new JLabel("IP:");
		pnl1.add(lblIp);
		
		return pnl1;
	}
	
	private JPanel getPnl2() {
		if(pnl2 == null) {
			pnl2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		}
		
		txtIp = new JTextField(15);
		txtIp.setEditable(false);
		pnl2.add(txtIp);
		
		return pnl2;
	}
	
	private JPanel getPnl3() {
		if(pnl3 == null) {
			pnl3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		}
		
		lblPorta = new JLabel("Porta:");
		pnl3.add(lblPorta);
		
		return pnl3;
	}
	
	private JPanel getPnl4() {
		if(pnl4 == null) {
			pnl4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		}
		
		txtPorta = new JTextField("5000", 15);
		pnl4.add(txtPorta);
		
		return pnl4;
	}
	
	private JPanel getPnl5() {
		if(pnl5 == null) {
			pnl5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		}
		
		btnIniciar = new JButton("Iniciar");
		btnEncerrar = new JButton("Encerrar");
		pnl5.add(btnIniciar);
		pnl5.add(btnEncerrar);
		
		return pnl5;
	}
	
	public JButton getBtnIniciar() {
		return btnIniciar;
	}
	
	public JButton getBtnEncerrar() {
		return btnEncerrar;
	}
	
	public JTextField getTxtPorta() {
		return txtPorta;
	}
	
	public JTextField getTxtIp() {
		return txtIp;
	}
}

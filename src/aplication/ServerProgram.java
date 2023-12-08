package aplication;

import entities.model.ServerControl;
import entities.view.ServerGUI;

public class ServerProgram {

	public static void main(String[] args) {
		ServerGUI serverGUI = new ServerGUI();
		new ServerControl(serverGUI);

	}

}

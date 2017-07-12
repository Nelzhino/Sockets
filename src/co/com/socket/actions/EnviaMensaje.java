package co.com.socket.actions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import co.com.socket.dto.TramaDto;
import co.com.socket.utils.AccesoTrama;

public class EnviaMensaje implements ActionListener {

	private TramaDto trama;
	
	
	
	public EnviaMensaje(TramaDto trama) {
		this.trama = trama;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			AccesoTrama inf = new AccesoTrama();
			inf.EnvioTrama(trama);
		} catch (UnknownHostException e1) {
			System.out.println(e1.getMessage());
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}

	}

	
}

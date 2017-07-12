package co.com.socket.actions;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import co.com.socket.dto.TramaDto;
import co.com.socket.enums.EstadoEnum;
import co.com.socket.utils.AccesoTrama;

public class EnvioOnline extends WindowAdapter{

	
	public void windowOpened(WindowEvent e){
		
		try {
			TramaDto trama = new TramaDto();
			trama.setEstado(EstadoEnum.DESCONECT);
			AccesoTrama info = new AccesoTrama();
			info.EnvioTrama(trama);
		} catch (UnknownHostException e1) {
			System.out.println(e1.getMessage());
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
	}
	
}

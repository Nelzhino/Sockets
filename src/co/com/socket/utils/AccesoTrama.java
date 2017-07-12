package co.com.socket.utils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import co.com.socket.dto.TramaDto;

public class AccesoTrama {

	public void EnvioTrama(TramaDto trama) throws UnknownHostException, IOException {
		Socket socket = new Socket(Constants.IPSERVER, Constants.PORT);

		ObjectOutputStream paqueteDatos = new ObjectOutputStream(socket.getOutputStream());
		paqueteDatos.writeObject(trama);
		socket.close();
	}
}

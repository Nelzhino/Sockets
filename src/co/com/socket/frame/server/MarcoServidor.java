/**
 * 
 */
package co.com.socket.frame.server;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import co.com.socket.dto.TramaDto;

/**
 * @author Usuario
 *
 */
public class MarcoServidor extends JFrame implements Runnable {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -6886895768692076749L;

	public MarcoServidor() {

		setBounds(1200, 300, 280, 350);
		JPanel milamina = new JPanel();
		milamina.setLayout(new BorderLayout());
		areatexto = new JTextArea();
		milamina.add(areatexto, BorderLayout.CENTER);
		add(milamina);
		setVisible(true);

		Thread hilos = new Thread(this);
		hilos.start();
	}

	private JTextArea areatexto;

	@Override
	public void run() {
		String ip, nick, mensaje;
		TramaDto trama;
		try {

			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(9090);

			// Se queda escuchando cuando le digo que debe recibir algun
			// mensaje.
			while (true) {
				Socket socket = server.accept();
				ObjectInputStream paqueteRecibido = new ObjectInputStream(socket.getInputStream());
				trama = (TramaDto) paqueteRecibido.readObject();

				nick = trama.getNick();
				ip = trama.getIp();
				mensaje = trama.getMensaje();

				areatexto.append(nick + "\t" + mensaje + "\t" + ip);
				
				ObtenerPaquete(ip, paqueteRecibido);
				
				socket.close();
			}

		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	private void ObtenerPaquete(String ip, ObjectInputStream paqueteRecibido) throws UnknownHostException, IOException {
		Socket envioDestinatario = new Socket(ip, 9090);		
		ObjectOutputStream paqueteReenvio = new ObjectOutputStream(envioDestinatario.getOutputStream());
		paqueteReenvio.writeObject(paqueteRecibido);
		paqueteReenvio.close();
		envioDestinatario.close();
	}
}

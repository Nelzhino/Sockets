/**
 * 
 */
package co.com.socket.frame.server;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import co.com.socket.dto.TramaDto;
import co.com.socket.enums.EstadoEnum;
import co.com.socket.utils.Constants;

/**
 * @author Usuario
 *
 */
public class MarcoServidor extends JFrame implements Runnable {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -6886895768692076749L;
	private List<String> listadoIpRemoto;

	public MarcoServidor() {

		listadoIpRemoto = new ArrayList<>();

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
			ServerSocket server = new ServerSocket(Constants.PORT);

			// Se queda escuchando cuando le digo que debe recibir algun
			// mensaje.
			while (true) {
				Socket socket = server.accept();

				ObjectInputStream paqueteRecibido = new ObjectInputStream(socket.getInputStream());
				trama = (TramaDto) paqueteRecibido.readObject();

				if (trama.getEstado().equals(EstadoEnum.DESCONECT)) {
					InetAddress localizacion = socket.getInetAddress();
					String ipRemoto = localizacion.getHostAddress();
					System.out.println(String.format("Online %s", ipRemoto));
					
					listadoIpRemoto.add(ipRemoto);
					
					trama.setEstado(EstadoEnum.CONECT);
					trama.setIpRemotas(listadoIpRemoto);
					
					
					Socket envioDestinatario = new Socket(ipRemoto, Constants.PORT);
					ObjectOutputStream paqueteReenvio = new ObjectOutputStream(envioDestinatario.getOutputStream());
					
					paqueteReenvio.writeObject(trama);
					paqueteReenvio.close();
					envioDestinatario.close();
					
				} else {
					nick = trama.getNick();
					ip = trama.getIp();
					mensaje = trama.getMensaje();
					areatexto.append(nick + "\t" + mensaje + "\t" + ip);
					ObtenerPaquete(ip, paqueteRecibido);
				}

				socket.close();
			}

		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	private void ObtenerPaquete(String ip, ObjectInputStream paqueteRecibido) throws UnknownHostException, IOException {
		Socket envioDestinatario = new Socket(ip, Constants.PORT);
		ObjectOutputStream paqueteReenvio = new ObjectOutputStream(envioDestinatario.getOutputStream());
		paqueteReenvio.writeObject(paqueteRecibido);
		paqueteReenvio.close();
		envioDestinatario.close();
	}

	public List<String> getListadoIpRemoto() {
		return listadoIpRemoto;
	}

}

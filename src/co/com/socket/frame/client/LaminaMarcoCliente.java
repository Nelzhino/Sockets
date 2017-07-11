/**
 * 
 */
package co.com.socket.frame.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import co.com.socket.dto.TramaDto;

/**
 * @author Usuario
 *
 */
public class LaminaMarcoCliente extends JPanel implements Runnable {
	private JTextField campo1, nick, ip;
	private JTextArea area;
	private JButton miboton;

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 1773195525925780316L;

	public LaminaMarcoCliente() {
		nick = new JTextField(5);
		ip = new JTextField(10);
		JLabel texto = new JLabel("-CHAT-");
		add(nick);
		add(texto);
		add(ip);
		area = new JTextArea(10, 20);
		campo1 = new JTextField(20);
		add(area);
		add(campo1);

		miboton = new JButton("Enviar");
		miboton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					EnvioTrama();
				} catch (UnknownHostException e1) {
					System.out.println(e1.getMessage());
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
			}

			private void EnvioTrama() throws UnknownHostException, IOException {
				Socket socket = new Socket("10.10.20.135", 9999);
				// DataOutputStream flujoSalida = new
				// DataOutputStream(socket.getOutputStream());
				// flujoSalida.writeUTF(campo1.getText());

				TramaDto trama = new TramaDto();

				trama.setIp(ip.getText());
				trama.setMensaje(campo1.getText());
				trama.setNick(nick.getText());

				ObjectOutputStream paqueteDatos = new ObjectOutputStream(socket.getOutputStream());
				paqueteDatos.writeObject(trama);
				socket.close();
			}
		});
		add(miboton);

		Thread miCliente = new Thread(this);
		miCliente.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ServerSocket servidorCliente = new ServerSocket(9090);
			Socket cliente;
			TramaDto tramaRecibido;

			while (true) {
				cliente = servidorCliente.accept();
				ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
				tramaRecibido = (TramaDto) flujoEntrada.readObject();
				area.append("\n" + tramaRecibido.getNick() + "\t" + tramaRecibido.getMensaje() + "\t"
						+ tramaRecibido.getIp());
			}
						
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Ha ocurrido un problema con el  servidor Cliente.");
		}

	}

}

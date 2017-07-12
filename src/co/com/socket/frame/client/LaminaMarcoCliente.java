/**
 * 
 */
package co.com.socket.frame.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import co.com.socket.actions.EnviaMensaje;
import co.com.socket.dto.TramaDto;
import co.com.socket.enums.EstadoEnum;
import co.com.socket.utils.Constants;;
/**
 * @author Usuario
 *
 */
public class LaminaMarcoCliente extends JPanel implements Runnable {
	private JTextField campo1;
	private JTextArea area;
	private JButton miboton;
	private JComboBox<String> ip;

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 1773195525925780316L;

	public LaminaMarcoCliente() {
		JLabel lblNick = new JLabel("Nick:");
		JLabel texto = new JLabel("Online");
		
		String nombre = JOptionPane.showInputDialog("Digite nick:");
		if(nombre == null || nombre.isEmpty()){
			nombre = "Anonimo";
		}
		
		JLabel nick = new JLabel(nombre);
		
		area = new JTextArea(10, 20);
		campo1 = new JTextField(20);
		
		ip = new JComboBox<String>();
		
		add(lblNick);
		add(nick);
		add(texto);
		add(ip);
		add(area);
		add(campo1);

		miboton = new JButton("Enviar");
		
		TramaDto trama = new TramaDto();

		trama.setIp(null);
		trama.setMensaje(campo1.getText());
		trama.setNick(nick.getText());		
		
		miboton.addActionListener(new EnviaMensaje(trama));
		add(miboton);

		Thread miCliente = new Thread(this);
		miCliente.start();
	}

	@Override
	public void run() {
		try {
			@SuppressWarnings("resource")
			ServerSocket servidorCliente = new ServerSocket(Constants.PORT);
			Socket cliente;
			TramaDto tramaRecibido;

			while (true) {
				cliente = servidorCliente.accept();
				ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
				tramaRecibido = (TramaDto) flujoEntrada.readObject();
				
				if(!tramaRecibido.getEstado().equals(EstadoEnum.CONECT)){
					area.append("\n" + tramaRecibido.getNick() + "\t" + tramaRecibido.getMensaje() + "\t"
							+ tramaRecibido.getIp());
				}else{
					for ( String ipRemota : tramaRecibido.getIpRemotas()) {
						ip.addItem(ipRemota);
					}
				}
				
			}
						
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Ha ocurrido un problema con el  servidor Cliente.");
		}

	}

}

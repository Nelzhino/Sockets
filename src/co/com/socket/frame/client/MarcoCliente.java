/**
 * 
 */
package co.com.socket.frame.client;

import javax.swing.JFrame;

/**
 * @author Usuario
 *
 */
public class MarcoCliente extends JFrame {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -1551799969218169973L;

	public MarcoCliente() {

		setBounds(600, 300, 280, 350);

		LaminaMarcoCliente milamina = new LaminaMarcoCliente();

		add(milamina);

		setVisible(true);
	}

}
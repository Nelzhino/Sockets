package co.com.socket.dto;

import java.io.Serializable;

public class TramaDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5473688610630103624L;
	private String ip;
	private String nick;
	private String mensaje;
	
	
	
	
	public TramaDto(String ip, String nick, String mensaje) {
		super();
		this.ip = ip;
		this.nick = nick;
		this.mensaje = mensaje;
	}


	public TramaDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	

}

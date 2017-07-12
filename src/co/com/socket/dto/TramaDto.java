package co.com.socket.dto;

import java.io.Serializable;
import java.util.List;

import co.com.socket.enums.EstadoEnum;

public class TramaDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5473688610630103624L;
	private String ip;
	private String nick;
	private String mensaje;
	private EstadoEnum estado;
	private List<String> ipRemotas;

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

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}


	public List<String> getIpRemotas() {
		return ipRemotas;
	}


	public void setIpRemotas(List<String> ipRemotas) {
		this.ipRemotas = ipRemotas;
	}
}

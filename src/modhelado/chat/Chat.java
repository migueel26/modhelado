package modhelado.chat;

import modhelado.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Chat {

	protected String fechaCreacion;
	private int idChat;
	protected List<Usuario> usuarios;
	protected static int contadorIDs = 0;
	protected List<Mensaje> historial;

	/**
	 * 
	 * @param creador
	 * @param fecha
	 */
	public Chat(Usuario creador, String fecha) {
		this.idChat = contadorIDs++;
		this.fechaCreacion = fecha;
		this.usuarios = new ArrayList<>();
		this.historial = new ArrayList<>();
		this.usuarios.add(creador);
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Mensaje> getHistorial() {
		return historial;
	}

	public int getIdChat() {
		return idChat;
	}

	/**
	 * 
	 * @param remitente
	 * @param texto
	 */
	public void enviarMensaje(Usuario remitente, String texto, String fechaActual) {
		if(usuarios.contains(remitente)){
			Mensaje nuevoMensaje = new Mensaje(texto, fechaActual, remitente);
			this.historial.add(nuevoMensaje);
			System.out.println("Mensaje enviado por " + remitente.getUsername() + ": " + texto);
		}else{
			System.out.println("Error: El usuario no pertenece a este chat");
		}
	}

}
package modhelado.chat;

import modhelado.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class Chat {

	protected String fechaCreacion;
	protected List<Usuario> usuarios;
	protected List<Mensaje> historial;

	/**
	 * 
	 * @param creador
	 * @param fecha
	 */
	public Chat(Usuario creador, String fecha) {
		this.fechaCreacion = fecha;
		this.usuarios = new ArrayList<>();
		this.historial = new ArrayList<>();
		this.usuarios.add(creador);
	}

	public Enumeration<Usuario> getUsuarios() {
		return Collections.enumeration(usuarios);
	}

	public Enumeration<Mensaje> getHistorial() {
		return Collections.enumeration(historial);
	}


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
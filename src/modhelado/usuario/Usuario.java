package modhelado.usuario;

import modhelado.tablon.TablonEventos;
import modhelado.tablon.TablonPublicacion;
import modhelado.usuario.conexion.Conexion;
import modhelado.tablon.evento.Evento;
import modhelado.chat.Mensaje;
import modhelado.chat.Chat;
import modhelado.interes.DescripcionInteres;

import java.util.List;

public class Usuario {
	private final TablonEventos tablonEventos;
	private final TablonPublicacion tablonPublicacion;
	private List<Conexion> conexiones;
	private List<Chat> chats;
	private String username;
	private String nombre;
	private String apellidos;
	private String correo;
	private String fechaNacimiento;
	private boolean vetado;

	public Usuario(String username, String nombre, String apellidos, String correo, String fechaNacimiento) {
		this.username = username;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.vetado = false;

		this.tablonEventos = new TablonEventos();
		this.tablonPublicacion = new TablonPublicacion();
	}

	/**
	 * 
	 * @param conexion
	 */
	protected void addConexion(Conexion conexion) {
		// TODO - implement modhelado.usuario.Usuario.addConexion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param descripcion
	 */
	protected void addInteres(DescripcionInteres descripcion) {
		// TODO - implement modhelado.usuario.Usuario.addInteres
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param contenido
	 * @param fecha
	 */
	public void crearPublicacion(String contenido, String fecha) {
		// TODO - implement modhelado.usuario.Usuario.crearPublicacion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mensaje
	 * @param chat
	 */
	public void enviarMensaje(Mensaje mensaje, Chat chat) {
		assert chat != null && mensaje != null;
		chat.enviarMensaje(username, mensaje);
	}

	/**
	 * 
	 * @param evento
	 */
	public void accederEvento(Evento evento) {
		// TODO - implement modhelado.usuario.Usuario.accederEvento
		throw new UnsupportedOperationException();
	}

	public String getUsername() {
		return username;
	}

}
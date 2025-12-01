package modhelado.usuario;

import modhelado.usuario.conexion.Conexion;
import modhelado.tablon.evento.Evento;
import modhelado.chat.Mensaje;
import modhelado.chat.Chat;
import modhelado.interes.DescripcionInteres;

public class Usuario {

	private String userName;
	private String nombre;
	private String apellidos;
	private int edad;
	private String correo;
	private String fechaNacimiento;
	private Boolean vetado;

	/**
	 * 
	 * @param userName
	 * @param nombre
	 * @param apellidos
	 * @param edad
	 * @param correo
	 * @param fechaNacimiento
	 */
	public Usuario(String userName, String nombre, String apellidos, int edad, String correo, String fechaNacimiento) {
		// TODO - implement modhelado.usuario.Usuario.modhelado.usuario.Usuario
		throw new UnsupportedOperationException();
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
		// TODO - implement modhelado.usuario.Usuario.enviarMensaje
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param evento
	 */
	public void accederEvento(Evento evento) {
		// TODO - implement modhelado.usuario.Usuario.accederEvento
		throw new UnsupportedOperationException();
	}

}
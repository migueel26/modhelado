package modhelado.usuario;

import modhelado.GestorBaseDatos;
import modhelado.interes.Interes;
import modhelado.tablon.TablonEventos;
import modhelado.tablon.TablonPublicacion;
import modhelado.tablon.publicacion.Publicacion;
import modhelado.usuario.conexion.Conexion;
import modhelado.tablon.evento.Evento;
import modhelado.chat.Mensaje;
import modhelado.chat.Chat;
import modhelado.interes.DescripcionInteres;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Usuario {
	private final TablonEventos tablonEventos;
	private final TablonPublicacion tablonPublicacion;
	private List<Conexion> conexiones;
	private List<Chat> chats;
	private List<DescripcionInteres> intereses;
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

	public Conexion getConexionCon(Usuario usuario) {
		Conexion result = null;
		for (Conexion conexion : conexiones) {
			if (result == null && (conexion.getUsuario1().equals(usuario) || conexion.getUsuario2().equals(usuario))) {
				result = conexion;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param descripcion
	 */
	protected void addInteres(DescripcionInteres descripcion) {
		assert descripcion != null;
		intereses.add(descripcion);
	}

	/**
	 * 
	 * @param contenido
	 * @param fecha
	 */
	public void crearPublicacion(String contenido, String fecha) {
		assert contenido != null && fecha != null;
		Publicacion publicacion = new Publicacion(fecha, contenido, intereses.stream().map(DescripcionInteres::getInteres).toList());
		GestorBaseDatos.guardar(publicacion.toString());
	}

	/**
	 * 
	 * @param mensaje
	 * @param chat
	 */
	public void enviarMensaje(String mensaje, Chat chat) {
		assert chat != null && mensaje != null;
		chat.enviarMensaje(this, new Date().toString(), mensaje);
	}

	/**
	 * 
	 * @param evento
	 */
	public void accederEvento(Evento evento) {
		System.out.println(evento.toString());
	}

	public String getUsername() {
		return username;
	}

}
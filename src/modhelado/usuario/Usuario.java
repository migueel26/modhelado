package modhelado.usuario;

import modhelado.GestorBaseDatos;
import modhelado.interes.Interes;
import modhelado.tablon.TablonEventos;
import modhelado.tablon.TablonPublicacion;
import modhelado.tablon.publicacion.Publicacion;
import modhelado.usuario.conexion.Conexion;
import modhelado.tablon.evento.Evento;
import modhelado.chat.Chat;
import modhelado.interes.DescripcionInteres;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private List<Publicacion> publicacionesCreadas;
	private List<Evento> eventos;

	public Usuario(String username, String nombre, String apellidos, String correo, String fechaNacimiento) {
		this.username = username;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.vetado = false;

		this.tablonEventos = new TablonEventos();
		this.tablonPublicacion = new TablonPublicacion();
		this.publicacionesCreadas = new ArrayList<>();
		this.conexiones = new ArrayList<>();
	}

	public void addConexion(Conexion conexion) {

	}

	public void crearConexion(Usuario usuario) {

	}

	public Conexion getConexionCon(Usuario usuario) {
		Conexion result = null;
		for (Conexion conexion : conexiones) {
			if (result == null && (conexion.getEmisor().equals(usuario) || conexion.getReceptor().equals(usuario))) {
				result = conexion;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param descripcion
	 */
	public void addInteres(DescripcionInteres descripcion) {
		assert descripcion != null;
		intereses.add(descripcion);
	}

	/**
	 *
	 * @param intereses
	 */
	protected void addIntereses(List<DescripcionInteres> intereses) {
		for(DescripcionInteres interes : intereses) {
			if(!this.intereses.contains(interes)) this.intereses.add(interes);
		}
	}

	/**
	 * 
	 * @param contenido
	 * @param fecha
	 */
	public void crearPublicacion(String contenido, String fecha, List<Interes> intereses) {
		assert contenido != null && fecha != null;
		//Publicacion publicacion = new Publicacion(this, fecha, contenido, intereses.stream().map(DescripcionInteres::getInteres).toList());
		Publicacion publicacion = new Publicacion(this, fecha, contenido, intereses);
		GestorBaseDatos.guardar(publicacion);
		this.publicacionesCreadas.add(publicacion);
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
	public void crearEvento(Evento evento) {
		evento.addUsuario(this);
		if(!eventos.contains(evento)) eventos.add(evento);
	}


	/**
	 * 
	 * @param evento
	 */
	public void accederEvento(Evento evento) {
		evento.addUsuario(this);
		if(!eventos.contains(evento)) eventos.add(evento);
	}


	public TablonEventos getTablonEventos() {return tablonEventos;}
	public TablonPublicacion getTablonPublicacion() {return tablonPublicacion;}



	public String getUsername() {
		return username;
	}

}
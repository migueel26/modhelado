package modhelado.usuario;

import modhelado.GestorBaseDatos;
import modhelado.interes.Interes;
import modhelado.tablon.TablonEventos;
import modhelado.tablon.TablonPublicacion;
import modhelado.tablon.publicacion.Publicacion;
import modhelado.usuario.conexion.Bloqueada;
import modhelado.usuario.conexion.Conexion;
import modhelado.tablon.evento.Evento;
import modhelado.chat.Chat;
import modhelado.interes.DescripcionInteres;
import modhelado.usuario.conexion.Pendiente;

import java.beans.PersistenceDelegate;
import java.util.*;

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
		this.intereses = new ArrayList<>();

		this.conexiones = new ArrayList<>();

		this.eventos = new ArrayList<>();
		this.tablonEventos = new TablonEventos();
		this.tablonPublicacion = new TablonPublicacion();
		this.publicacionesCreadas = new ArrayList<>();

		this.chats = new ArrayList<>();
	}

	public void addConexion(Conexion conexion) {
		assert conexion != null;
		if (!conexiones.contains(conexion)) {
			conexiones.add(conexion);
		}
	}

	public void enviarSolicitud(Usuario usuario) {
		new Conexion(this, usuario, new Date().toString(), Pendiente.pendiente());
	}

	public void aceptarConexion(Usuario usuario) {
		Optional<Conexion> conexion = buscarConexion(usuario);
		assert this.equals(conexion.get().getReceptor() == this);
		conexion.get().aceptar();
	}

	public void cancelarConexion(Usuario usuario) {
		Optional<Conexion> conexion = buscarConexion(usuario);
		if(!conexion.isEmpty()) conexion.get().cancelar();
	}

	public void bloquearConexion(Usuario usuario) {
		Optional<Conexion> conexion = buscarConexion(usuario);
		if (!conexion.isEmpty()) {
			conexion.get().bloquear();
		} else {
			new Conexion(this, usuario, new Date().toString(), Bloqueada.bloqueada());
		}
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

	private Optional<Conexion> buscarConexion(Usuario usuario) {
		Optional<Conexion> conexion = Optional.empty();
		Iterator<Conexion> it = conexiones.iterator();
		while (it.hasNext() && conexion.isEmpty()) {
			Conexion next = it.next();
			if ((next.getEmisor().equals(this) && next.getReceptor().equals(usuario))
					|| (next.getEmisor().equals(usuario) && next.getReceptor().equals(this))) {
				conexion = Optional.of(next);
			}
		}
		return conexion;
	}

	/**
	 * 
	 * @param descripcion
	 */
	public void addInteres(Interes interes, String descripcion) {
		assert interes != null && descripcion != null;
		intereses.add(new DescripcionInteres(descripcion, this, interes));
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

	public void crearEvento(String titulo, String fecha, Integer aforo, String lugar, List<Interes> intereses) {
		assert titulo != null && fecha != null && aforo != null && lugar != null && !intereses.isEmpty();
		Evento evento = new Evento(this, titulo, fecha, aforo, lugar, intereses);
		GestorBaseDatos.guardar(evento);
		eventos.add(evento);
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
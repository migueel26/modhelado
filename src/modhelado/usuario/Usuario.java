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

import java.util.*;

public class Usuario {
	private String username;
	private String nombre;
	private String apellidos;
	private String correo;
	private String fechaNacimiento;
	private boolean vetado;
	private List<DescripcionInteres> intereses;

	private List<Conexion> conexiones;

	private List<Evento> eventos;
	private List<Publicacion> publicacionesCreadas;
	private final TablonEventos tablonEventos;
	private final TablonPublicacion tablonPublicacion;

	private List<Chat> chats;


	//CONSTRUCTOR
	public Usuario(String username, String nombre, String apellidos, String correo, String fechaNacimiento) {
		assert GestorBaseDatos.consultar("SELECT username FROM USUARIOS WHERE username = '" + username + "'").isEmpty();
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




	//GESTIÓN USUARIO
	public boolean getEstadoVetado(){return vetado;}
	public void setVetado(boolean vetado) {
		this.vetado = vetado;
	}
	public String getNombre(){return nombre;}
	public String getApellidos(){return apellidos;}
	public String getCorreo(){return correo;}
	public String getFechaNacimiento(){return fechaNacimiento;}



	//GESTIÓN CONEXIONES
	public void addConexion(Conexion conexion) {
		assert conexion != null;
		if (!conexiones.contains(conexion)) {
			conexiones.add(conexion);
			chats.add(conexion.getChat());
		}
	}

	public void enviarSolicitud(Usuario usuario) {
		// Constraint: ConexionUnicaParUsuarios
		assert buscarConexion(usuario).isEmpty();
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
			// Constraint: ConexionUnicaParUsuarios
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

	public List<Conexion> getConexiones(){return conexiones;}


	//GESTIÓN INTERESES
	public List<DescripcionInteres> getIntereses() {return intereses;}

	public void addInteres(Interes interes, String descripcion) {
		assert interes != null && descripcion != null;
		intereses.add(new DescripcionInteres(descripcion, this, interes));
		tablonEventos.addInteres(interes);
		tablonPublicacion.addInteres(interes);
	}

	protected void addIntereses(List<DescripcionInteres> intereses) {
		for (DescripcionInteres interes : intereses) {
			if(!this.intereses.contains(interes)) this.intereses.add(interes);
			tablonPublicacion.addInteres(interes.getInteres());
			tablonEventos.addInteres(interes.getInteres());
		}
	}

	//GESTIÓN EVENTOS
	public List<Evento> getEventos(){return eventos;}

	public void crearEvento(String titulo, String fecha, Integer aforo, String lugar, List<Interes> intereses) {
		// Constraint: UsuarioVetado
		assert !vetado;

		assert titulo != null && fecha != null && aforo != null && lugar != null && !intereses.isEmpty();
		Evento evento = new Evento(this, titulo, fecha, aforo, lugar, intereses);
		//BBDD.guardarEvento(evento);
		GestorBaseDatos.guardar(evento);
		eventos.add(evento);
		chats.add(evento.getChat());
	}

	public void accederEvento(Evento evento) {
		// Constraint: UsuarioVetado
		assert !vetado;

		evento.addUsuario(this);
		if(!eventos.contains(evento)) {
			eventos.add(evento);
			chats.add(evento.getChat());
		}
	}



	//GESTIÓN PUBLICACIONES
	public List<Publicacion> getPublicacionesCreadas(){return publicacionesCreadas;}

	public void crearPublicacion(String contenido, String fecha, List<Interes> intereses) {
		assert contenido != null && fecha != null;
		Publicacion publicacion = new Publicacion(this, fecha, contenido, intereses);
		//BBDD.guardarPublicacion(publicacion);
		GestorBaseDatos.guardar(publicacion);
		this.publicacionesCreadas.add(publicacion);
	}

	public void darLike(Publicacion publicacion) {
		publicacion.darLike(this);
	}

	public void quitarLike(Publicacion publicacion) {
		publicacion.quitarLike(this);
	}

	//GESTIÓN TABLONES
	public TablonEventos getTablonEventos() {return tablonEventos;}

	public TablonPublicacion getTablonPublicacion() {return tablonPublicacion;}

	public String getUsername() {
		return username;
	}


	//GESTIÓN CHATS
	public List<Chat> getChats(){return chats;}

	public void enviarMensaje(String mensaje, Chat chat) {
		assert chat != null && mensaje != null;
		chat.enviarMensaje(this, new Date().toString(), mensaje);
	}


}
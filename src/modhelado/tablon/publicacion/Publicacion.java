package modhelado.tablon.publicacion;

import modhelado.interes.Interes;
import modhelado.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Publicacion {

	private List<Interes> intereses;
	private String fecha;
	private String contenido;
	private List<Usuario> usuarioLikes;
	private Usuario creador;
	private long ID;
	protected static int contadorIDs = 0;
	/**
	 * 
	 * @param fecha
	 * @param contenido
	 */
	public Publicacion(Usuario creador, String fecha, String contenido, List<Interes> intereses) {
		this.intereses = intereses;
		this.fecha = fecha;
		this.contenido = contenido;
		this.usuarioLikes = new ArrayList<>();
		this.creador = creador;
		this.ID = contadorIDs++;
	}

	public int getLikes() {
		return usuarioLikes.size();
	}

	public List<Usuario> getUsuarioLikes() {
		return usuarioLikes;
	}

	public String getContenido() {
		return this.contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getFecha() {
		return this.fecha;
	}
	public long getID() {
		return this.ID;
	}

	// GESTIÓN INTERESES
	public List<Interes> getIntereses(){
		return this.intereses;
	}

	protected void addInteres(Interes interes) {
		if (!intereses.contains(interes)) intereses.add(interes);
	}
	
	public Usuario getCreador() {
		return this.creador;
	}

	public void darLike(Usuario usuario) {
		//TODO: CONSTRAINT
		assert !usuarioLikes.contains(usuario);
		usuarioLikes.add(usuario);
	}

	public void quitarLike(Usuario usuario) {
		usuarioLikes.remove(usuario);
	}

	protected void addIntereses(List<Interes> intereses) {
		for (Interes interes : intereses) {
			if (!this.intereses.contains(interes)) this.intereses.add(interes);
		}
	}

	public void eliminarInteres(Interes interes) {
		if (this.intereses.contains(interes)) intereses.remove(interes);
	}
}
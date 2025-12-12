package modhelado.tablon.publicacion;

import modhelado.interes.Interes;
import modhelado.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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


	// GESTIÓN INTERESES
	public List<Interes> getIntereses(){
		return this.intereses;
	}

	protected void addInteres(Interes interes) {
		if (!intereses.contains(interes)) intereses.add(interes);
	}

	protected void addIntereses(List<Interes> intereses) {
		for (Interes interes : intereses) {
			if (!this.intereses.contains(interes)) this.intereses.add(interes);
		}
	}

	public void eliminarInteres(Interes interes) {
		if (this.intereses.contains(interes)) intereses.remove(interes);
	}

	public boolean matchIntereses(List<Interes> intereses){
		for (Interes interes : this.intereses) {
			if (intereses.contains(interes)) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder publicacion = new StringBuilder();
		publicacion.append("Autor: " + creador.getUsername() +
				"\nFecha de publicación: " + fecha +
				"\nContenido: " + contenido +
				"\nLikes: " + usuarioLikes.size() + "\n") ;

		StringJoiner intereses = new StringJoiner(", ", "[", "]");
		for (Interes interes : this.intereses){
			intereses.add(interes.interes());
		}
		return publicacion.toString() + "Intereses: " + intereses.toString() + "\n";
	}
}
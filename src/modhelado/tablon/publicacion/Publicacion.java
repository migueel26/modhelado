package modhelado.tablon.publicacion;

import modhelado.GestorBaseDatos;
import modhelado.interes.Interes;
import modhelado.usuario.Usuario;

import java.util.List;

public class Publicacion {

	private List<Interes> intereses;
	private String fecha;
	private String contenido;
	private int likes;
	private Usuario creador;

	/**
	 * 
	 * @param fecha
	 * @param contenido
	 */
	public Publicacion(Usuario creador, String fecha, String contenido, List<Interes> intereses) {
		this.intereses = intereses;
		this.fecha = fecha;
		this.contenido = contenido;
		this.likes = 0;
		this.creador = creador;

		GestorBaseDatos.guardar(this);
	}

	public void calcularLikes() {
		// Debe devolver un entero Integer
		Object likes_number = GestorBaseDatos.consultar("SELECT publicaciones FROM usuarios.likes COUNT").get(0);

		if(likes_number instanceof Integer) this.likes = (Integer) likes_number;
	}

	public int getLikes() {return likes;}

	/**
	 * 
	 * @param contenido
	 */
	public void actualizarContenido(String contenido) {this.contenido = contenido;}

	public String getContenido() {
		return this.contenido;
	}

	public String getFecha() {
		return this.fecha;
	}
	public List<Interes> getIntereses(){return this.intereses;}

	/**
	 * 
	 * @param interes
	 */
	protected void addInteres(Interes interes) {
		if(!intereses.contains(interes)) intereses.add(interes);
	}

	/**
	 *
	 * @param intereses
	 */
	protected void addIntereses(List<Interes> intereses) {
		for(Interes interes : intereses) {
			if(!this.intereses.contains(interes)) this.intereses.add(interes);
		}
	}

	public void eliminarInteres(Interes interes) {
		if(this.intereses.contains(interes)) intereses.remove(interes);
	}
}
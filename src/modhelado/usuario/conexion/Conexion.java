package modhelado.usuario.conexion;

import modhelado.usuario.Usuario;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringJoiner;

public class Conexion {
	private EstadoConexion estado;
	private String fecha;
	private Usuario usuario1;
	private Usuario usuario2;

	/**
	 * 
	 * @param usuario1
	 * @param usuario2
	 * @param fecha
	 */
	private Conexion(Usuario usuario1, Usuario usuario2, String fecha) {
		this.usuario1 = usuario1;
		this.usuario2 = usuario2;
		this.fecha = fecha;
	}

	public void cambiarEstado(EstadoConexion estado) {
		assert estado != null;
		this.estado = estado;
	}

	public String conexion() {
		return "Estado entre " + usuario1 + " y " + usuario2 + ": " + estado.conexion(this);
	}

	public void aceptar() {
		estado.aceptar(this);
	}

	public void cancelar() {
		estado.cancelar(this);
	}

	public void bloquear() {
		estado.bloquear(this);
	}

	@Override
	public String toString() {
		StringJoiner result = new StringJoiner(",","Conexion(",")");
		result.add(usuario1.getUsername());
		result.add(usuario2.getUsername());
		result.add(fecha);
		result.add(estado.conexion(this));
		return result.toString();
	}

	@Override
	public int hashCode() {
		return usuario1.hashCode() + usuario2.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Conexion that
				&& that.usuario1.equals(this.usuario1)
				&& that.usuario2.equals(this.usuario2);
	}
}
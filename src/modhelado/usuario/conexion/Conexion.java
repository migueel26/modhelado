package modhelado.usuario.conexion;

import modhelado.chat.ChatPrivado;
import modhelado.usuario.Usuario;

import java.util.StringJoiner;

public class Conexion {
	private EstadoConexion estado;
	private String fecha;
	private Usuario emisor;
	private Usuario receptor;
	private ChatPrivado chat;

	/**
	 * 
	 * @param emisor
	 * @param receptor
	 * @param fecha
	 */
	public Conexion(Usuario emisor, Usuario receptor, String fecha, EstadoConexion estado) {
		// Constraint: ConexionUsuariosDistintos
		assert !emisor.equals(receptor);
		// Constraint: AntiguedadConEstadoPendiente
		assert estado.equals(Aceptada.aceptada()) || fecha == null;

		this.emisor = emisor;
		this.receptor = receptor;
		this.fecha = fecha;
		this.estado = estado;
		this.chat = new ChatPrivado(emisor, receptor, fecha);

		emisor.addConexion(this);
		receptor.addConexion(this);
	}

	public void cambiarEstado(EstadoConexion estado) {
		assert estado != null;
		this.estado = estado;
	}

	public String conexion() {
		return estado.conexion(this);
	}

	public void aceptar() {
		estado.aceptar(this);
	}

	public void cancelar(Usuario bloqueador) {
		estado.cancelar(this, bloqueador);
	}

	public void bloquear(Usuario bloqueador) {
		estado.bloquear(this, bloqueador);
	}

	public EstadoConexion getEstado() {
		return estado;
	}

	public String getFecha() {
		return fecha;
	}

	public void intercambiarRoles() {
		Usuario aux = receptor;
		this.receptor = emisor;
		this.emisor = aux;
	}

	public Usuario getEmisor() {
		return emisor;
	}

	public Usuario getReceptor() {
		return receptor;
	}
	public ChatPrivado getChat(){return chat;}

	@Override
	public String toString() {
		StringJoiner result = new StringJoiner(",","Conexion(",")");
		result.add(emisor.getUsername());
		result.add(receptor.getUsername());
		result.add(fecha);
		result.add(estado.conexion(this));
		return result.toString();
	}

	@Override
	public int hashCode() {
		return emisor.hashCode() + receptor.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Conexion that
				&& that.emisor.equals(this.emisor)
				&& that.receptor.equals(this.receptor);
	}
}
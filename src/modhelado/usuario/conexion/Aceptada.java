package modhelado.usuario.conexion;

public class Aceptada implements EstadoConexion {
	// PATRÓN SINGLETON
	private static Aceptada aceptada;

	private Aceptada() {}

	public static Aceptada aceptada() {
		if (aceptada == null) {
			aceptada = new Aceptada();
		}
		return aceptada;
	}

	@Override
	public void aceptar(Conexion conexion) {
		assert conexion != null;
		throw new IllegalArgumentException("ERROR: No se puede aceptar una conexión Aceptada");
	}

	@Override
	public void cancelar(Conexion conexion) {
		assert conexion != null;
	}

	@Override
	public void bloquear(Conexion conexion) {
		assert conexion != null;
		conexion.cambiarEstado(Bloqueada.bloqueada());
	}

	@Override
	public String conexion(Conexion conexion) {
		return "ACEPTADO";
	}
}
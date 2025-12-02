package modhelado.usuario.conexion;

public class Pendiente implements EstadoConexion {
	// PATRÓN SINGLETON
	private static Pendiente pendiente;

	private Pendiente() {}

	public static Pendiente pendiente() {
		if (pendiente == null) {
			pendiente = new Pendiente();
		}
		return pendiente;
	}

	@Override
	public void aceptar(Conexion conexion) {
		assert conexion != null;
		conexion.cambiarEstado(Aceptada.aceptada());
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
		return "PENDIENTE";
	}
}
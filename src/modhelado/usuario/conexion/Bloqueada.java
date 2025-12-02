package modhelado.usuario.conexion;

public class Bloqueada implements EstadoConexion {
    private static Bloqueada bloqueada;
    private Bloqueada() {}
    public static Bloqueada bloqueada() {
        if (bloqueada == null) {
            bloqueada = new Bloqueada();
        }
        return bloqueada;
    }
    @Override
    public void aceptar(Conexion conexion) {

    }

    @Override
    public void cancelar(Conexion conexion) {

    }

    @Override
    public void bloquear(Conexion conexion) {
        assert conexion != null;
        throw new IllegalArgumentException("ERROR: No se puede bloquear a una persona Bloqueada");
    }

    @Override
    public String conexion(Conexion conexion) {
        return "BLOQUEADO";
    }
}

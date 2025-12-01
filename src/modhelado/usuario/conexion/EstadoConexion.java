package modhelado.usuario.conexion;

public interface EstadoConexion {
    void aceptar(Conexion conexion);
    void cancelar(Conexion conexion);
    void bloquear(Conexion conexion);
    String conexion(Conexion conexion);
}

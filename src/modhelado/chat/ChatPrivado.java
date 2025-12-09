package modhelado.chat;

import modhelado.GestorBaseDatos;
import modhelado.usuario.Usuario;
import modhelado.usuario.conexion.Aceptada;
import modhelado.usuario.conexion.Conexion;


public class ChatPrivado extends Chat {

	/**
	 * 
	 * @param usuario1
	 * @param usuario2
	 * @param fecha
	 */
	public ChatPrivado(Usuario usuario1, Usuario usuario2, String fecha) {
		super(usuario1, fecha);
		// Constraint: ChatPrivadoConexionAceptada
		/** TODO: HAY QUE REVISAR COMO HACEMOS LAS PETICIONES
		assert !(GestorBaseDatos.consultar("SELECT emisor FROM CONEXIONES WHERE (emisor = '" + usuario1.getUsername() + "' AND receptor = '" + usuario2.getUsername()
				+ "') OR (emisor = '" + usuario2.getUsername()+ "' AND receptor = '" + usuario1.getUsername() + "')").getFirst() instanceof Conexion conexion &&
				conexion.getEstado().equals(Aceptada.aceptada()));
		*/
		// Constraint: chatPrivadoUsuariosDiferentes
		assert !usuario1.equals(usuario2);

		this.usuarios.add(usuario2);
	}

}
package modhelado.chat;

import modhelado.usuario.Usuario;

public class ChatPrivado extends Chat {

	/**
	 * 
	 * @param usuario1
	 * @param usuario2
	 * @param fecha
	 */
	public ChatPrivado(Usuario usuario1, Usuario usuario2, String fecha) {
		super(usuario1, fecha);
		if(!usuario1.equals(usuario2)){
			this.usuarios.add(usuario2);
		}else{
			throw new IllegalArgumentException("Error: No se puede crear un chat privado con el mismo usuario");
		}
	}

}
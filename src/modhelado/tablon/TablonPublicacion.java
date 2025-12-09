package modhelado.tablon;

import modhelado.GestorBaseDatos;
import modhelado.interes.Interes;
import modhelado.tablon.evento.Evento;
import modhelado.tablon.publicacion.Publicacion;

import java.util.ArrayList;
import java.util.List;

public class TablonPublicacion extends Tablon {

	private List<Publicacion> publicaciones;

	public TablonPublicacion() {
		super();
		this.publicaciones = new ArrayList<>();
	}

	@Override
	public void ver() {
		personalizar(intereses);
		System.out.println("Publicaciones para ti" + "\n");
		for(Publicacion publicacion : publicaciones){
			System.out.println(publicacion);
		}
	}

	@Override
	public int getSize() {return publicaciones.size();}

	@Override
	public void personalizar(List<Interes> intereses) {

		// Debe devolver una lista de publicaciones de tipo Publicacion
		List<Object> query = GestorBaseDatos.consultar("SELECT publicacion FROM publicaciones WITH intereses");
		for (Object obj : query) {
			if (obj instanceof Publicacion) {
				this.publicaciones.add((Publicacion) obj);
			} else {
				System.err.println("Advertencia: El objeto de la BD no es una Publicacion.");
			}
		}
	}

}
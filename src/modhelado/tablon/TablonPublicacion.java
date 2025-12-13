package modhelado.tablon;

import modhelado.GestorBaseDatos;
import modhelado.interes.Interes;
import modhelado.tablon.evento.Evento;
import modhelado.tablon.publicacion.Publicacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class TablonPublicacion extends Tablon {

	private List<Publicacion> publicaciones;

	public TablonPublicacion() {
		super();
		this.publicaciones = new ArrayList<>();
	}

	public List<Publicacion> getTablonPublicacion() {
		return publicaciones;
	}

	@Override
	public void ver() {
		personalizar(intereses);
		System.out.println("Publicaciones para ti\n");
		for(Publicacion publicacion : publicaciones){
			System.out.println(publicacion + "\n");
		}
	}

	@Override
	public int getSize() {return publicaciones.size();}

	public void addPublicacion(Publicacion publicacion){
		if (!publicaciones.contains(publicacion)) publicaciones.add(publicacion);
	}
	public void addPublicaciones(List<Publicacion> publicaciones){
		for(Publicacion publicacion : publicaciones) addPublicacion(publicacion);
	}

	@Override
	public void personalizar(List<Interes> intereses) {
		// Se llama cada vez que un usuario accede al tablón
		publicaciones = GestorBaseDatos.consultarPublicaiones(intereses);
	}

	public Enumeration<Publicacion> getPublicaciones() {
		return Collections.enumeration(publicaciones);
	}
}
package modhelado.tablon;

import modhelado.GestorBaseDatos;
import modhelado.interes.Interes;
import modhelado.tablon.evento.Evento;
import modhelado.GestorBaseDatos.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;



public class TablonEventos extends Tablon {

	private List<Evento> eventos;

	public TablonEventos() {
		super();
		this.eventos = new ArrayList<>();
	}

	@Override
	public void ver() {
		personalizar(intereses);
		System.out.println("Eventos para ti\n");
		for(Evento evento : eventos){
			System.out.println(evento + "\n");
		}
	}

	@Override
	public int getSize() {return eventos.size();}

	public void addEvento(Evento evento){
		if (!eventos.contains(evento)) eventos.add(evento);
	}
	public void addEventos(List<Evento> eventos){
		for(Evento evento : eventos) addEvento(evento);
	}
	public Enumeration<Evento> getEventos() {
		return Collections.enumeration(eventos);
	}

	@Override
	public void personalizar(List<Interes> intereses) {
		// Se llama cada vez que un usuario accede al tablón
		eventos = GestorBaseDatos.consultarEventos(intereses);
	}
}
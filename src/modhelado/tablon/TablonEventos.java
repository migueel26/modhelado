package modhelado.tablon;

import modhelado.GestorBaseDatos;
import modhelado.interes.Interes;
import modhelado.tablon.evento.Evento;
import modhelado.GestorBaseDatos.*;

import java.util.ArrayList;
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
		System.out.println("Eventos para ti" + "\n");
		for(Evento evento : eventos){
			System.out.println(evento.toString());
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

	@Override
	public void personalizar(List<Interes> intereses) {
		addEventos(GestorBaseDatos.consultarEventos(intereses));
	}
}
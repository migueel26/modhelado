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

	@Override
	public void personalizar(List<Interes> intereses) {

		// Debe devolver una lista de eventos de tipo Evento
		List<Object> query = GestorBaseDatos.consultar("SELECT evento FROM eventos WITH intereses");

		for (Object obj : query) {
			if (obj instanceof Evento) {
				this.eventos.add((Evento) obj);
			} else {
				System.err.println("Advertencia: El objeto de la BD no es un Evento.");
			}
		}
	}
}
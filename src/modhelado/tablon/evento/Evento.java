package modhelado.tablon.evento;

import modhelado.interes.Interes;

import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

public class Evento {

	private List<Interes> intereses;
	private String titulo;
	private String fecha;
	private String aforo;
	private String lugar;

	/**
	 *
	 * @param titulo
	 * @param fecha
	 * @param aforo
	 * @param lugar
	 */
	public Evento(String titulo, String fecha, String aforo, String lugar, List<Interes> intereses) {
		this.intereses = intereses;
		this.titulo = titulo;
		this.fecha = fecha;
		this.aforo = aforo;
		this.lugar = lugar;
	}

	public List<Interes> getIntereses() {
		return intereses;
	}

	public void setIntereses(List<Interes> intereses) {
		this.intereses = intereses;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getAforo() {
		return aforo;
	}

	public void setAforo(String aforo) {
		this.aforo = aforo;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	/**
	 * 
	 * @param interes
	 */
	public void addInteres(Interes interes) {
		if(!intereses.contains(interes)) intereses.add(interes);
	}

	/**
	 *
	 * @param intereses
	 */
	protected void addIntereses(List<Interes> intereses) {
		for(Interes interes : intereses) {
			if(!this.intereses.contains(interes)) this.intereses.add(interes);
		}
	}

	public void eliminarInteres(Interes interes) {
		if(this.intereses.contains(interes)) intereses.remove(interes);
	}

	@Override
	public String toString() {
		StringBuilder evento = new StringBuilder();
		evento.append("Titulo: " + titulo +
					"\nSe celebra el " + fecha + " en " + lugar +
					"\nAforo máximo: " + aforo+ "\n") ;

		StringJoiner intereses = new StringJoiner(", ", "[", "]");
		for (Interes interes : this.intereses){
			intereses.add(interes.interes());
		}
		return evento.toString() + intereses.toString();
	}
}
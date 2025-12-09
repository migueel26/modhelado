package modhelado.tablon.evento;

import modhelado.GestorBaseDatos;
import modhelado.chat.Chat;
import modhelado.chat.ChatGrupal;
import modhelado.interes.Interes;
import modhelado.usuario.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

public class Evento {

	private List<Interes> intereses;
	private String titulo;
	private String fecha;
	private Integer aforo;
	private String lugar;
	private Usuario creador;
	private List<Usuario> participantes;
	private Chat chatGrupal;

	/**
	 *
	 * @param titulo
	 * @param fecha
	 * @param aforo
	 * @param lugar
	 */
	public Evento(Usuario creador, String titulo, String fecha, Integer aforo, String lugar, List<Interes> intereses) {
		this.intereses = intereses;
		this.titulo = titulo;
		this.fecha = fecha;
		this.aforo = aforo;
		this.lugar = lugar;
		this.creador = creador;
		this.chatGrupal = new ChatGrupal(creador, new Date().toString());

		this.participantes = new ArrayList<>();
		participantes.add(creador);

		GestorBaseDatos.guardar(this);
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

	public Integer getAforo() {
		return aforo;
	}

	public void setAforo(Integer aforo) {
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

	public void addUsuario(Usuario usuario) {
		if(!participantes.contains(usuario)) participantes.add(usuario);
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
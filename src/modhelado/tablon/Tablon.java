package modhelado.tablon;

import modhelado.interes.Interes;

import java.util.ArrayList;
import java.util.List;

public abstract class Tablon {

	protected List<Interes> intereses;

	protected Tablon(){this.intereses = new ArrayList<>();}

	public abstract void ver();

	public void addInteres(Interes interes) {
		if(!intereses.contains(interes)) intereses.add(interes);
	}

	protected void addIntereses(List<Interes> intereses) {
		for(Interes interes : intereses) {
			if(!this.intereses.contains(interes)) this.intereses.add(interes);
		}
	}

	public abstract int getSize();

	/**
	 * 
	 * @param intereses
	 */
	public abstract void personalizar(List<Interes> intereses);

}
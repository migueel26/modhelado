package modhelado.tablon;

import modhelado.interes.Interes;

import java.util.List;

public abstract class Tablon {

	protected Tablon(){}

	public abstract void ver();

	public abstract int getSize();

	/**
	 * 
	 * @param intereses
	 */
	public abstract void personalizar(List<Interes> intereses);

}
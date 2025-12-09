package modhelado.interes;

public class Parque extends Interes {
	private static Parque parque;

	private Parque() {}

	public static Parque parque() {
		if (parque == null) {
			parque = new Parque();
		}
		return parque;
	}

	@Override
	public String interes() {
		return "Parque";
	}
}
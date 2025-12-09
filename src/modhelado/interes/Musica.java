package modhelado.interes;

public class Musica extends Interes {
	private static Musica Musica;

	private Musica() {}

	public static Musica musica() {
		if (Musica == null) {
			Musica = new Musica();
		}
		return Musica;
	}

	@Override
	public String interes() {
		return "Musica";
	}
}
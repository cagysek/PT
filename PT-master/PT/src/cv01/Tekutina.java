package cv01;

public class Tekutina {
	private static int pocet;
	private int poradi;
	
	public Tekutina(){
		this.pocet++;
		this.poradi = pocet;
		System.out.printf("Pořadí: %s, počet instancí je %s\n",poradi,pocet);
	}
}

package cv01;

public class Napoj extends Tekutina{
	private static int pocet;
	private int poradi;
	
	public Napoj(){
	
	//	super();
		this.pocet++;
		this.poradi = pocet;
//		this.poradi++;
		System.out.printf("Pořadí: %s, počet instancí je %s\n",poradi,pocet);
		
	}
}

package cv01;

public class Pivo extends Napoj {
	private static int pocet;
	private int poradi;
	
	public Pivo(){
	//	super();
		this.pocet++;
		this.poradi = pocet;
//		this.poradi++;
		System.out.printf("Pořadí: %s, počet instancí je %s\n",poradi,pocet);		
	}
}

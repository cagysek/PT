
/**
 * Třída {@code Main} se stará o správu programu 
 * @author cagy
 *
 */

public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//vytvoření readeru
		Reader reader = new Reader();
		
		System.out.println("Načítám topologii");
		//vytvoření topologie
		reader.readTopology("links_.txt");
		
		System.out.println("Načteno");
		//vypsání topologie
	//	topology.printTopology();
		
		//čtení simulace
		reader.readSimulate("simulate_.txt");
		
		//topology.printTopology();
	
	}

}

package semestralka;
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
		
		//vytvoření topologie
		Topology topology = reader.readTopology("links.txt");
		
		//vypsání topologie
		topology.printTopology();
		
		//čtení simulace
		reader.readSimulate("simulate.txt");
		
	}

}

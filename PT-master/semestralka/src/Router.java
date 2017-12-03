
import java.util.*;
/**
 * Instance třídy {@code Router} představuje uzel v topologii
 * @author cagy
 *
 */
public class Router implements Comparable<Object> {
	/** Jmeno routeru */
	private String name;
	
	/** SmartStack */
//	private SmartStack stack;
	
	/**Aktuální packet na routeru */
	private Packet packet;
	
	//Atributy pro hledání optimální cesty
	/**Aktuální distance od poč. bodu */
	private double distance;
	
	/**Minimálni distance od poč. routeru */
	private double minDistance = Double.POSITIVE_INFINITY;
	
	/**Předchozí router v procházení sítě*/
	private Router previous;
	
	/**Seznam hran routeru */
	private List<Link> routerEdges;
	
	/**
	 * Vytvoření instance router
	 * @param name jmeno routeru
	 */
	public Router(String name){
		this.name = name;
//		this.stack = new SmartStack(); 
		this.routerEdges = new ArrayList<Link>();
				
	}
	
	/**
	 * get router name
	 * @return router name
	 */
	public String getName(){
		return name;
	}

	/**
	 * set router name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Metoda pro výpis jmena routeru
	 */
	public String toString() {
		return name;
	}
	
	
	
	/**
	 * Získání vzdálenosti routeru od počátku
	 * @return vzdálenost
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Nastavení vzdálenosti od začátku
	 * @param distance vzdálenost
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * Získání hran routeru
	 * @return seznam hran
	 */
	public List<Link> getRouterEdges() {
		return routerEdges;
	}

	/**
	 * Nastavení hran routeru
	 * @param routerEdges seznam hran
	 */
	public void setRouterEdges(List<Link> routerEdges) {
		this.routerEdges = routerEdges;
	}

	/**
	 * Získání minimální vzdálenosti od počátku
	 * @return vzdálenost od začatku
	 */
	public double getMinDistance() {
		return minDistance;
	}

	/**
	 * nastavení minimální vzdálenosti od začátku
	 * @param minDistance vzdálenost
	 */
	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	/**
	 * Získání předchozího routeru
	 * @return předchozí router
	 */
	public Router getPrevious() {
		return previous;
	}

	/**
	 * Nastavení předchozího routeru
	 * @param previous předchozí router
	 */
	public void setPrevious(Router previous) {
		this.previous = previous;
	}
	
	/**
	 * Metoda CompareTo pro porovnání min vzdálenosti
	 * @param other router
	 * @return min vzdálenost
	 */
	public int compareTo(Router other) {
		return Double.compare(minDistance, other.getMinDistance());
	}
	
	/**
	 * Získání datové šířky mezi sousedním routerem
	 * @param router druhý router
	 * @return datová šířka
	 */
	public double getBandwithToRouter(Router router) {
		for (Link link : routerEdges) {
			if(link.getToRouter().getName().equals(router.getName())) { return link.getBandwidth()*link.getReliability();}
		}
		return 0; 
		
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Metoda pro získání zásobníku
	 * @return zásobník
	 */
	public SmartStack getStack() {
		return null;
		//return this.stack;
	}

	/**
	 * Zjištění zda je router obsazený
	 * @return true/false
	 */
	public boolean isOccupied() {
		boolean occu;
		if(this.packet == null) {
			occu =  false;
		}else {
			occu = true;
		}
		return occu;
	}

	/**
	 * Získání packetu na routeru
	 * @return packet
	 */
	public Packet getPacket() {
		return packet;
	}

	/**
	 * Nastavení routeru packet
	 * @param packet packet
	 */
	public void setPacket(Packet packet) {
		this.packet = packet;
	}

   
	
	
}

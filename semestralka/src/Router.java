
import java.util.*;
/**
 * Instance třídy {@code Router} představuje uzel v topologii
 * @author cagy
 *
 */
public class Router implements Comparable {
	/** Jmeno routeru */
	private String name;
	
	/** SmartStack */
	private SmartStack stack;
	
	private List<Router> neighbors;
	
	private double distance;
	private double minDistance = Double.POSITIVE_INFINITY;
	private Router previous;
	private List<Link> routerEdges;
	/**
	 * Vytvoření instance router
	 * @param name jmeno routeru
	 */
	public Router(String name){
		this.name = name;
		this.stack = new SmartStack();
		this.neighbors = new ArrayList<Router>();
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
	
	public void addNeighbor(Router router) {
		neighbors.add(router);
	}
	
	public String toString() {
		return name;
	}
	
	public List<Router> getNeighbours(){
		return neighbors;
	}
	
	 @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((name == null) ? 0 : name.hashCode());
	        return result;
	    }
	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        Router other = (Router) obj;
	        if (name == null) {
	            if (other.name != null)
	                return false;
	        } else if (!name.equals(other.name))
	            return false;
	        return true;
	    }

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public List<Link> getRouterEdges() {
		return routerEdges;
	}

	public void setRouterEdges(List<Link> routerEdges) {
		this.routerEdges = routerEdges;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	public Router getPrevious() {
		return previous;
	}

	public void setPrevious(Router previous) {
		this.previous = previous;
	}
	public int compareTo(Router other) {
		return Double.compare(minDistance, other.getMinDistance());
	}
	public double getBandwithToRouter(Router router) {
		for (Link link : routerEdges) {
			if(link.getToRouter().getName().equals(router.getName())) return link.getBandwidth();
		}
		return 0;
		
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

   
	
	
}

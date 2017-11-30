package semestralka;
import java.util.*;
/**
 * Instance třídy {@code Router} představuje uzel v topologii
 * @author cagy
 *
 */
public class Router {
	/** Jmeno routeru */
	private String name;
	
	/** SmartStack */
	private SmartStack stack;
	
//	private List<Router> neighbors;
	
	private Map<Router, Integer> neighbors;
	
	/**
	 * Vytvoření instance router
	 * @param name jmeno routeru
	 */
	public Router(String name){
		this.name = name;
		this.stack = new SmartStack();
		this.neighbors = new HashMap<>();
		
		
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
	
	public void addNeighbor(Router router,int value) {
		neighbors.put(router,value);
	}
	
	public String toString() {
		return name;
	}
	
	public HashMap<Router,Integer> getNeighbours(){
		return (HashMap<Router, Integer>) neighbors;
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

   
	
	
}

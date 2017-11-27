package semestralka;
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
	
	/**
	 * Vytvoření instance router
	 * @param name jmeno routeru
	 */
	public Router(String name){
		this.name = name;
		this.stack = new SmartStack();
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
	
	
	
}

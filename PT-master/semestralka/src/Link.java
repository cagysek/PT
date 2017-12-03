



/***************************
 * Instance třídy {@code Link} představuje spoj mezi dvěma routery
 * @author cagy
 *
 */

public class Link {

	/** Počáteční router */
	private Router fromRouter;
	
	/** Koncový router */
	private Router toRouter;
	
	/** maximální propustnost spoje */
	private double bandwidth; 
	
	/** Spolehlivost spoje */
	private double reliability;
	//private double 
	
	/**
	 * Vytvoření instance třídy Link s parametry:
	 * @param toRouter koncový routeer
	 * @param bandwidth maximální propustnost
	 * @param reliability spolehlivost
	 */
	public Link(Router toRouter,double bandwidth,double reliability){
		this.toRouter = toRouter;
		this.bandwidth = bandwidth;
		this.reliability = reliability;
	}
	
	/**
	 * Získáni počátečního routeru
	 * @return the fromRouter počáteční router
	 */
	public Router getFromRouter() {
		return fromRouter;
	}

	/**
	 * Získání koncového routeru
	 * @return the toRouter koncový router
	 */
	public Router getToRouter() {
		return toRouter;
	}

	/**
	 * Získání šířky linky
	 * @return the bandwidth šířka linky
	 */
	public double getBandwidth() {
		return bandwidth;
	}

	/**
	 * Získání spolehlivosti linky
	 * @return the reliability spolehlivost
	 */
	public double getReliability() {
		return reliability;
	}

	/**
	 * Nastavení počátečního routeru
	 * @param fromRouter the fromRouter to set
	 */
	public void setFromRouter(Router fromRouter) {
		this.fromRouter = fromRouter;
	}

	/** Nastavení koncového routeru
	 * @param toRouter the toRouter to set
	 */
	public void setToRouter(Router toRouter) {
		this.toRouter = toRouter;
	}

	/**
	 * Nastavení šířky
	 * @param bandwidth the bandwidth to set
	 */
	public void setBandwidth(double bandwidth) {
		this.bandwidth = bandwidth;
	}

	/**
	 * Nastavení spolehlivosti
	 * @param reliability the reliability to set
	 */
	public void setReliability(double reliability) {
		this.reliability = reliability;
	}
	
	/**
	 * Získáni maximální propustnosti linky bez chyby
	 * @return maximální velikost bez ztráty
	 * @param bandwidth šířka linky
	 */
	public double getWeight(double bandwidth) {
		return ((bandwidth) - (this.bandwidth*this.reliability));
	}
	
}

package semestralka;



/***************************
 * Instance třídy {@ code Link} představuje spoj mezi dvěma routery
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
	 * @param fromRouter počáteční router
	 * @param toRouter koncový routeer
	 * @param bandwidth maximální propustnost
	 * @param reliability spolehlivost
	 */
	public Link(Router fromRouter, Router toRouter,double bandwidth,double reliability){
		this.fromRouter = fromRouter;
		this.toRouter = toRouter;
		this.bandwidth = bandwidth;
		this.reliability = reliability;
	}
	
	/**
	 * Metoda pro vypsání hodnot 
	 * @return String s hodnotami
	 */
	@Override
	public String toString(){
		return ("From Router: "+fromRouter.getName()+" to Router: "+toRouter.getName()+" bandwidth:"+bandwidth+ " reliability:" +reliability);
	}

	/**
	 * @return the fromRouter
	 */
	public Router getFromRouter() {
		return fromRouter;
	}

	/**
	 * @return the toRouter
	 */
	public Router getToRouter() {
		return toRouter;
	}

	/**
	 * @return the bandwidth
	 */
	public double getBandwidth() {
		return bandwidth;
	}

	/**
	 * @return the reliability
	 */
	public double getReliability() {
		return reliability;
	}

	/**
	 * @param fromRouter the fromRouter to set
	 */
	public void setFromRouter(Router fromRouter) {
		this.fromRouter = fromRouter;
	}

	/**
	 * @param toRouter the toRouter to set
	 */
	public void setToRouter(Router toRouter) {
		this.toRouter = toRouter;
	}

	/**
	 * @param bandwidth the bandwidth to set
	 */
	public void setBandwidth(double bandwidth) {
		this.bandwidth = bandwidth;
	}

	/**
	 * @param reliability the reliability to set
	 */
	public void setReliability(double reliability) {
		this.reliability = reliability;
	}
	
	
}

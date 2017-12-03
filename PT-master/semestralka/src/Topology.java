

import java.util.*;

/**************
 * Instance třídy {@code Topology} vytvoří topologii sítě
 * @author cagy
 *
 */
public class Topology {
	/** List pro ukládání uzlů */
	private final List<Link> LINK_LIST;
	
	/**List pro ukládání routerů */
	private final List<Router> ROUTER_LIST;
	
	/**
	 * Konstruktor pro vytvoření instace
	 */
	public Topology(){
		LINK_LIST = new ArrayList<>();
		ROUTER_LIST = new ArrayList<>();
	}
	
	/**
	 * přidání prvku do topologie
	 * @param link spoj mezi routery
	 */
	public void add_link(Link link){
		LINK_LIST.add(link);
	}
	
	/**
	 * Získání určitého linku
	 * @param r1 z routeru
	 * @param r2 do routeru
	 * @return link
	 */
	public Link getLink(Router r1, Router r2) {
		for (Link link : LINK_LIST) {
			if ((link.getFromRouter().getName().equals(r1.getName()) )&&(link.getToRouter().getName().equals(r2.getName()))) {
				return link;
			}
		}
		return null;
	}
	
	/**
	 * Metoda pro přidání routeru s kontrolou zda již v topologii neexistuje
	 * @param router router
	 */
	public void add_router(Router router){
		if(!ROUTER_LIST.stream().anyMatch(dto -> dto.getName().equals(router.getName()))) {
			
			ROUTER_LIST.add(router);
			
		}	
	}
	
	/**
	 * Kontrola routeru jestli se nenáchází v topologii pokud ano vrátí ho
	 * @param router hledaný router
	 * @return router
	 */
	public Router checkRouterName(Router router) {
		Router find = null;
		if(ROUTER_LIST.stream().anyMatch(dto -> dto.getName().equals(router.getName()))) {		
			find = getRouter(router.getName());
		}
		
		return find;
	}
	
	/**
	 * Získání routeru podle ID
	 * @param name ID routeru
	 * @return router 
	 */
	public Router getRouter(String name) { 
		
		for (Router router : ROUTER_LIST) {
	        if (router.getName().equals(name)){
	            return router;
	        }
	    }
	
		return null;
			
	}
	
	/**
	 * Metoda pro získání seznamu routerů
	 * @return seznam routerů
	 */
	public List<Router> getRouterList(){
		return this.ROUTER_LIST;
	}
	
	/**
	 * Metoda pro získání všech linků
	 * @return seznam linků
	 */
	public List<Link> getLinkList(){
		return this.LINK_LIST;
	}
	
	/**
	 * Vyčištění všem routerům předchůdce
	 * Kvůli hledání nejoptimálnější cesty
	 */
	public void clearFindingInfo() {
		for (Router router : ROUTER_LIST) {
			router.setPrevious(null);
			router.setMinDistance(Double.POSITIVE_INFINITY);
		}
	}
}



import java.awt.Color;
import java.util.*;
import java.util.stream.Collectors;

/**************
 * Instance třídy {@code Topology} vytvoří topologii sítě
 * @author cagy
 *
 */
public class Topology {
	/** List pro ukládání uzlů */
	List<Link> linkList;
	List<Router> routerList;
	
	/**
	 * Konstruktor pro vytvoření instace
	 */
	public Topology(){
		linkList = new ArrayList<>();
		routerList = new ArrayList<>();
	}
	
	/**
	 * přidání prvku do topologie
	 * @param link spoj mezi routery
	 */
	public void add_link(Link link){
		linkList.add(link);
	}
	public Link getLink(Router r1, Router r2) {
		for (Link link : linkList) {
			if ((link.getFromRouter().getName().equals(r1.getName()) )&&(link.getToRouter().getName().equals(r2.getName()))) {
				return link;
			}
		}
		return null;
	}
	
	public void add_router(Router router){
		if(routerList.stream().anyMatch(dto -> dto.getName().equals(router.getName())) == false) {
			
			routerList.add(router);
			
		}	
	}
	public Router checkRouterName(Router router) {
		Router find = null;
		if(routerList.stream().anyMatch(dto -> dto.getName().equals(router.getName())) == true) {		
			find = getRouter(router.getName());
		}
		
		return find;
	}
	
	public Router getRouter(String name) {
		
		for (Router router : routerList) {
	        if (router.getName().equals(name)){
	            return router;
	        }
	    }
	
		return null;
			
	}
	
	public List<Router> getRouterList(){
		return this.routerList;
	}
	
	public List<Link> getLinkList(){
		return this.linkList;
	}
	
	/**
	 * vypsání celé topologie
	 */
	public void printTopology(){
		System.out.println("Linky");
		for (Link link : linkList) {
			System.out.println(link.toString());
		}
		System.out.println("Routery");
		for (Router router : routerList) {
			System.out.println("Router:"+router.getName() +" route table: " +router.getNeighbours());
		}
	}
	public void clearFindingInfo() {
		for (Router router : routerList) {
			router.setPrevious(null);
			router.setMinDistance(Double.POSITIVE_INFINITY);
		}
	}
}

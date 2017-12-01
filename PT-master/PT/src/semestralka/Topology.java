package semestralka;

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
	List<Link> link_list;
	List<Router> router_list;
	
	/**
	 * Konstruktor pro vytvoření instace
	 */
	public Topology(){
		link_list = new ArrayList<>();
		router_list = new ArrayList<>();
	}
	
	/**
	 * přidání prvku do topologie
	 * @param link spoj mezi routery
	 */
	public void add_link(Link link){
		link_list.add(link);
	}
	
	public void add_router(Router router){
		if(router_list.stream().anyMatch(dto -> dto.getName().equals(router.getName())) == false) {
			
			router_list.add(router);
			
		}
		
		
		
	}
	
	/**
	 * Metoda pro přidání souseda k routeru 
	 * oboum přidá druhý router
	 * kontroluje jestli se už nenachází v listu, pokud ano změním ukazatele na router v poli
	 * @param router_one router 1
	 * @param router_two router 2
	 */
	public void addNeighbor(Router router) {
		//check jestli se router_one  už nenachází v topologii
		//Router test = 
		/*
		if(router_list.stream().anyMatch(dto -> dto.getName().equals(router_one.getName())) == true) {			
			Router find = getRouter(router_one.getName());
			find.addNeighbor(router_two);
			
		}else {
			router_one.addNeighbor(router_two);			
		}
		
		//check jestli se router_two  už nenachází v topologii
		if(router_list.stream().anyMatch(dto -> dto.getName().equals(router_two.getName())) == true) {			
			Router find = getRouter(router_two.getName());
			find.addNeighbor(router_one);
			
		}else {
			router_two.addNeighbor(router_one);
			
		}
		*/
		
		//this.addNeighbor(router);
		
	}
	
	public Router checkRouterName(Router router) {
		Router find = null;
		if(router_list.stream().anyMatch(dto -> dto.getName().equals(router.getName())) == true) {		
			find = getRouter(router.getName());
		}
		
		return find;
	}
	
	public Router getRouter(String name) {
		
		for (Router router : router_list) {
	        if (router.getName().equals(name)){
	            return router;
	        }
	    }
	
		return null;
			
	}
	
	public List<Router> getRouterList(){
		return this.router_list;
	}
	
	public List<Link> getLinkList(){
		return this.link_list;
	}
	
	/**
	 * vypsání celé topologie
	 */
	public void printTopology(){
		System.out.println("Linky");
		for (Link link : link_list) {
			System.out.println(link.toString());
		}
		System.out.println("Routery");
		for (Router router : router_list) {
			System.out.println("Router:"+router.getName() +" route table: " +router.getNeighbours());
		}
	}
}

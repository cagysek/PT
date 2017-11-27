package semestralka;

import java.util.*;

/**************
 * Instance třídy {@code Topology} vytvoří topologii sítě
 * @author cagy
 *
 */
public class Topology {
	/** List pro ukládání uzlů */
	List<Link> linkList;
	
	/**
	 * Konstruktor pro vytvoření instace
	 */
	public Topology(){
		linkList = new ArrayList<>();
	}
	
	/**
	 * přidání prvku do topologie
	 * @param link spoj mezi routery
	 */
	public void add(Link link){
		linkList.add(link);
	}
	
	/**
	 * vypsání celé topologie
	 */
	public void printTopology(){
		for (Link link : linkList) {
			System.out.println(link);
		}
	}
}

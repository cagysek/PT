package semestralka;

import java.io.*;
import java.util.*;
import java.time.*;
/**
 * Třída {@code Reader} se stará o načtení vstupního souboru a zároveň o jeho zpracování 
 * @author cagy
 *
 */
public class Reader {
	
	private Topology topology;
	
	/**
	 * Přečtení vstupního souboru s topologií a následně její vytvoření
	 * @param inputFile vstupní soubor s topologií
	 * @return topologie
	 */
	public Topology readTopology(String inputFile){
		topology = new Topology();

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
		    String line;
		    while ((line = br.readLine()) != null) {

			    	String[] data = line.split("-");
			    	Router routerFrom = new Router(data[0].trim());
			    	Router routerTo = new Router(data[1].trim());
			    	
			    	int maxVal = (int)(Double.parseDouble(data[2]) * Double.parseDouble(data[3]));
			    	Router check = topology.checkRouterName(routerFrom);
			    	if(check != null) {
			    		routerFrom = check;
			    	}
			    	
			    	check = topology.checkRouterName(routerTo);
			    	if(check != null) {
			    		routerTo = check;
			    	}
			    	
			    	routerFrom.addNeighbor(routerTo,maxVal);
			    	routerTo.addNeighbor(routerFrom,maxVal);
			    	
			    	
			    topology.add_router(routerFrom);
			    	topology.add_router(routerTo);
 	
			    //topology.addNeighbor(routerFrom, routerTo);
			    	
			    	Link link = new Link(routerFrom,routerTo, Double.parseDouble(data[2]), Double.parseDouble(data[3]));
//			    	Link link2 = new Link(routerTo,routerFrom, Double.parseDouble(data[2]), Double.parseDouble(data[3]));
			    //	System.out.println(link.toString());
			    	
			    	topology.add_link(link);
//			    	topology.add_link(link2);
		    	
		    }
		
		}catch(Exception ex){
			System.out.println("Špatně formátovaný vstupní soubor pro topologii");
			ex.printStackTrace();
		}
		
		return topology;
	}
	
	/**
	 * Přečtení vstupního souboru se simulací a postupným zpracováním
	 * @param inputFile vstupní soubor
	 */
	public void readSimulate(String inputFile){
		
		List<Router> routers = new ArrayList<Router>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
		    String line;
		    while ((line = br.readLine()) != null) {

			    	String[] data = line.split("-");
			    	String[] timeArray = data[0].split(":"); //for create time		    	
			    	LocalTime time = LocalTime.of(Integer.parseInt(timeArray[0]),Integer.parseInt(timeArray[1]));
			    	Router routerFrom = new Router(data[1].trim());
			    	Router routerTo = new Router(data[2].trim());
			    	
			    	
			    	Router check = topology.checkRouterName(routerFrom);
			    	if(check != null) {
			    		routerFrom = check;
			    	}
			    	
			    	check = topology.checkRouterName(routerTo);
			    	if(check != null) {
			    		routerTo = check;
			    	}
			    	
			    	
			    	
			    	
			    	int size = Integer.parseInt(data[3]);
			    	
			    	Task task = new Task(time,routerFrom,routerTo,size);
			//    	System.out.println("zadáno: "+task.toString());
			    	System.out.println("simulace");
			    	Simulate sim = new Simulate(task,topology);
			  //  	System.out.println("zpracovavam: "+sim.toString());
		    }
		
		}catch(Exception ex){
			System.out.println("Špatně formátovaný vstupní soubor pro simulaci");
			ex.printStackTrace();
		}
	}
	
}



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
	 */
	public void readTopology(String inputFile){
		topology = new Topology();

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
		    String line;
		    while ((line = br.readLine()) != null) {

			    	String[] data = line.split("-");
			    	Router routerFrom = new Router(data[0].trim());
			    	Router routerTo = new Router(data[1].trim());
			    	
			    	Router check = topology.checkRouterName(routerFrom);
			    	if(check != null) {
			    		routerFrom = check;
			    	}
			    	
			    	check = topology.checkRouterName(routerTo);
			    	if(check != null) {
			    		routerTo = check;
			    	}			    	
			    	
			    topology.add_router(routerFrom);
			    	topology.add_router(routerTo);
 	
			    	Link link1 = new Link(routerTo, Double.parseDouble(data[2]),Double.parseDouble(data[3]));
	    			routerFrom.getRouterEdges().add(link1);
	    			
	    			Link link2 = new Link(routerFrom, Double.parseDouble(data[2]),Double.parseDouble(data[3]));
			    routerTo.getRouterEdges().add(link2);
		    	
		    }
		
		}catch(Exception ex){
			System.out.println("Špatně formátovaný vstupní soubor pro topologii");
			ex.printStackTrace();
		}
		
		//return topology;
	}
	
	/**
	 * Přečtení vstupního souboru se simulací a postupným zpracováním
	 * @param inputFile vstupní soubor
	 */
	public void readSimulate(String inputFile){
		Router routerFrom;
		Router routerTo;
		List<Task> taskList = new ArrayList<Task>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
		    String line;
		    while ((line = br.readLine()) != null) {

			    	String[] data = line.split("-");
			    	String[] timeArray = data[0].split(":"); //for create time		    	
			    	LocalTime time = LocalTime.of(Integer.parseInt(timeArray[0]),Integer.parseInt(timeArray[1]));
			    	
			    	routerFrom = topology.getRouter(data[1].trim());
				    routerTo = topology.getRouter(data[2].trim());
				    
				    				    
				    if((routerFrom == null)||(routerTo==null)){
						System.out.println("Router " + data[1].trim() + " nebo " + data[2].trim() + " neexistuje v topologii ");
						continue;
				    }
			    	 	
			    	int size = Integer.parseInt(data[3]);
			    	
			    	Task task = new Task(time,routerFrom,routerTo,size);
			    	taskList.add(task);
			    	
		    }
		    System.out.println("simulace");
		    Simulate simulate = new Simulate(taskList,topology);
		    simulate.simulateCommunication();
		
		}catch(Exception ex){
			System.out.println("Špatně formátovaný vstupní soubor pro simulaci"); 
			ex.printStackTrace();
		}
	}
	
}

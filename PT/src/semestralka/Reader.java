package semestralka;

import java.io.*;
import java.time.*;
/**
 * Třída {@code Reader} se stará o načtení vstupního souboru a zároveň o jeho zpracování 
 * @author cagy
 *
 */
public class Reader {
	
	/**
	 * Přečtení vstupního souboru s topologií a následně její vytvoření
	 * @param inputFile vstupní soubor s topologií
	 * @return topologie
	 */
	public Topology readTopology(String inputFile){
		Topology topology = new Topology();

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
		    String line;
		    while ((line = br.readLine()) != null) {

		    	String[] data = line.split("-");
		    	Router routerFrom = new Router(data[0]);
		    	Router routerTo = new Router(data[1]);
		    	Link link = new Link(routerFrom,routerTo, Double.parseDouble(data[2]), Double.parseDouble(data[3]));
		    //	System.out.println(link.toString());
		    	topology.add(link);
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
		
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
		    String line;
		    while ((line = br.readLine()) != null) {

			    	String[] data = line.split("-");
			    	String[] timeArray = data[0].split(":"); //for create time		    	
			    	LocalTime time = LocalTime.of(Integer.parseInt(timeArray[0]),Integer.parseInt(timeArray[1]));
			    	Router routerFrom = new Router(data[1]);
			    	Router routerTo = new Router(data[2]);
			    	int size = Integer.parseInt(data[3]);
			    	
			    	Task task = new Task(time,routerFrom,routerTo,size);
			    	System.out.println("zadáno: "+task.toString());
			    	Simulate sim = new Simulate(task);
			    	System.out.println("zpracovavam: "+sim.toString());
		    }
		
		}catch(Exception ex){
			System.out.println("Špatně formátovaný vstupní soubor pro simulaci");
			ex.printStackTrace();
		}
	}
	
}

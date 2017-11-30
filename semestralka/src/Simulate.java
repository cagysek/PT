

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Třída {@code Simulate} se stará o simulaci komunikace mezi uzly
 * @author cagy
 *
 */
public class Simulate {

	/** Tasky které chceme provést */
	private List<Task> taskList;
	
	private Topology topology;
	
	private List<Packet> runningPacket= new CopyOnWriteArrayList<>();
	
	private int packetID = 0;
	
	private String log = "";
	
	
	
	/**
	 * Vytvoření simulace pro zadaný task
	 * @param task zadání
	 * @throws IOException 
	 */
	public Simulate(List<Task> taskList,Topology topology) throws IOException{
		this.taskList = taskList;
		this.topology = topology;
		simulateCommunication();
	}
	
	/**
	 * Metoda starající se o simulaci komunikace
	 * @throws IOException 
	 */
	private void simulateCommunication() throws IOException{
		
		
		for (Task task : taskList) {		
			this.topology.clearFindingInfo();
			long time = System.nanoTime();
			
			CommunicationPath cPath = new CommunicationPath();		
			cPath.computePaths(task.getFromRouter());
			
	        List<Router> path = cPath.getShortestPathTo(task.getToRouter());
	        
	        Packet packet = new Packet(packetID, path);
	        packetID++;
	        runningPacket.add(packet);
	        
	       
				makeStep();
			
		}
		
		while(runningPacket.size()>0) {
			
				makeStep();
		}
		
		generateFile();
	}
	
	private void makeStep(){
		
		
		System.out.println(runningPacket);
		log += runningPacket+"\n";
		Iterator<Packet> it = runningPacket.iterator();
		while(it.hasNext()){
			Packet packet = it.next();
				System.out.print(packet.getActualRouter()+" -> ");
				log += packet.getActualRouter()+" -> ";
				packet.moveNext();
				
				System.out.println(packet.getActualRouter());
				log += packet.getActualRouter()+"\n";
				if(packet.isAtDestination() == true) {
					runningPacket.removeIf(s -> s.equals(packet)); 
					System.out.println("END");
					log +="END\n";
				}
				System.out.println("=============================================");
				log += "=============================================\n";
		
		}
		
			
	}
	
	public void generateFile() throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(Paths.get("output.txt"), StandardCharsets.UTF_8);
		writer.write(log);
		writer.close();
	}
	
}

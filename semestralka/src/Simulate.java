

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
	
	private List<Packet> runningPacketList= new CopyOnWriteArrayList<>();
	
	private int packetID = 0;
	
	private String log = "";
	
	private Packet lastPacket;
	
	
	
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
	        
	        double maxSize = cPath.getMaxSize(path);
	        
	        Packet packet = new Packet(packetID, (double)task.getSize(), path);
	        System.out.print("Načítám packet: ");
	        System.out.println(packet);
	        
	        List<Packet> packetList = packet.splitPacket(maxSize);
	        
	        for (Packet packet_ : packetList) {
	        	runningPacketList.add(packet_);
	        	packetID++;
			}
	       
				makeStep();
			
		}
		
		while(runningPacketList.size()>0) {
			
				makeStep();
		}
		
		generateFile();
	}
	
	private void makeStep(){
		
		
		//System.out.println(runningPacketList);
		log += runningPacketList+"\n";
		Iterator<Packet> it = runningPacketList.iterator();
		while(it.hasNext()){
			Packet packet = it.next();
				System.out.print("Packet" + packet.getID() + ": ");
				System.out.print(packet.getActualRouter()+" -> ");
				log += packet.getActualRouter()+" -> ";
				
				packet.moveNext();
				
				//lastPacket = packet;
				
				System.out.println(packet.getActualRouter());
				log += packet.getActualRouter()+"\n";
				if(packet.isAtDestination() == true) {
					packet.getActualRouter().setOccupied(false);
					packet.getActualRouter().setPacket(null);
					runningPacketList.removeIf(s -> s.equals(packet)); 
					System.out.println("END");
					log +="END\n";
				}
				
				
		
		}
		log += "=============================================\n";
		System.out.println("=============================================");
			
	}
	
	public void generateFile() throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(Paths.get("output.txt"), StandardCharsets.UTF_8);
		writer.write(log);
		writer.close();
	}
	
}

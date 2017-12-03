

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Třída {@code Simulate} se stará o simulaci komunikace mezi uzly
 * @author cagy
 *
 */
public class Simulate {

	/** Tasky které chceme provést */
	private final List<Task> taskList;
	
	/**Topologie sítě */
	private final Topology topology;
	
	/**Seznam nedokončených packetů */
	private static List<Packet> runningPacketList= new CopyOnWriteArrayList<>();
	
	/**Promměná pro vytváření nových packetů */
	private int packetID = 0;
	
	/**String pro výsledný export simulace */
	private String log = "";
	
	/**Pro čtení uživatelského vstupu */
	public static Scanner sc = new Scanner(System.in);
	
	/**
	 * Vytvoření simulace pro zadaný task
	 * @param taskList seznam tasku
	 * @param topology topologie sítě
	 * @throws IOException výystupní soubor nenalezen
	 */
	public Simulate(List<Task> taskList,Topology topology) throws IOException{
		this.taskList = taskList;
		this.topology = topology;
		//simulateCommunication();
	}
	
	/**
	 * Metoda starající se o simulaci komunikace 
	 * @throws IOException chybí výstupní soubor
	 */
	@SuppressWarnings("static-access")
	public void simulateCommunication() throws IOException{
		
		//postupné čtení zadání
		for (Task task : taskList) {		
			this.topology.clearFindingInfo();
			
			CommunicationPath cPath = new CommunicationPath();		
			cPath.computePaths(task.getFromRouter());  //výpočet cest
			
	        List<Router> path = cPath.getShortestPathTo(task.getToRouter()); //získání cesty
	        
	        double maxSize = cPath.getMaxSize(path); //minimální bandwidth
	        
	        
	        Packet packet = new Packet(packetID, (double)task.getSize(), path);//vytvoření packetu
	        System.out.print("Načítám packet: ");
	        System.out.println(packet);
	        
	        List<Packet> packetList = packet.splitPacket(maxSize);//vytvoření sub-packetů
	        
	        for (Packet packet_ : packetList) {
	        	runningPacketList.add(packet_);
	        	packetID++;
			}
				
	        makeStep();
		}
		
		//doběhnutí zbylých packetu
		while(runningPacketList.size()>0) {
			
				makeStep();
		}
		
		generateFile();//generace běhu packetů
	}
	
	/**
	 * Metoda pro posunutí packetu o jeden krok
	 */
	private void makeStep(){
		
		sc.nextLine(); 
		
		System.out.println(runningPacketList);
		log += runningPacketList+"\n";
		Iterator<Packet> it = runningPacketList.iterator();
		while(it.hasNext()){
			Packet packet = it.next();
				System.out.print("Packet" + packet.getID() + ": ");
				System.out.print(packet.getActualRouter()+" -> ");
				log += packet.getActualRouter()+" -> ";
				
				//if(lastPacket)
				String logFromPacket = packet.moveNext();
				log += logFromPacket;
				
				
				
				System.out.println(packet.getActualRouter());
				log += packet.getActualRouter()+"\n";
				if(packet.isAtDestination()) {
					packet.getActualRouter().setPacket(null);
					runningPacketList.removeIf(s -> s.equals(packet)); 
					System.out.println("END");
					log +="END\n";
				}	
		}
		System.out.println("=============================================");
		log += "=============================================\n";
		
			
	}
	
	/**
	 * Metoda pro vygenerování souboru s během packetů
	 * @throws IOException neplatná cesta
	 */
	public void generateFile() throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(Paths.get("output.txt"), StandardCharsets.UTF_8);
		writer.write(log);
		writer.close();
	}
	
}

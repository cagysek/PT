import java.util.ArrayList;
import java.util.List;
/*******
 * Třída {@code Packet} představuje přenášená data v simulaci
 * @author cagy
 *
 */


public class Packet {
	/**ID packetu */
	private final int ID;
	
	/**velikost packetu */
	private double size;
	
	/**Cesta packetu */
	private List<Router> path;
	
	/**Aktuální pozice packetu v jeho cestě */ 
	private Router actualRouter;
	
	/**Další router kam půjde packet */
	private Router nextRouter;
	
	/**Index packetu v putování jeho cestou */
	private int pathIndex = 0;
	
	/**
	 * Konstruktor packetu
	 * @param ID id packetu
	 * @param size velikost packetu
	 * @param path cesta packetu
	 */
	public Packet(int ID ,double size,List<Router> path) {
		this.ID = ID;
		this.size = size;
		this.path = path;
		this.actualRouter = path.get(pathIndex);
		this.nextRouter = path.get(pathIndex+1);
		//this.pathIndex++;
	}
	
	/**
	 * Metoda pro rozdělení packetu, pro splnění velikosti toku linky
	 * @param maxSize datová šířka linky
	 * @return seznam sub-packetu
	 */
	public List<Packet> splitPacket(double maxSize){
		int id = this.ID;
		List<Packet> packetList = new ArrayList<Packet>();
		double countOfNewPackets = this.size/maxSize;
		if(countOfNewPackets>1){
			System.out.println("Rozděluji packet"+this.ID+" na " + countOfNewPackets + " packetů");
			for (int i = 0; i < countOfNewPackets; i++) {
				packetList.add(new Packet(id, (this.size/countOfNewPackets), this.path));
				id++;
			}
		}
		else{
			packetList.add(this);
		}
		return packetList;
	}
	
	/**
	 * nastavení velikosti packetu
	 * @param size velikost packetu
	 */
	public void setSize(double size){
		this.size = size;
	}
	
	/**
	 * Získání velikosti packetu
	 * @return velikost packetu
	 */
	public double getSize(){
		return this.size;
	}
	
	/**
	 * Nastavení cesty packetu
	 * @param path cesta packetu
	 */
	public void setPath(List<Router> path){
		this.path = path;
	}
	
	/**
	 * Získání cesty packetu
	 * @return cesta packetu
	 */
	public List<Router> getPath(){
		return this.path;
	}
	
	/**
	 * Získání aktuální pozice packetu
	 * @return router v jakém se nachází packet
	 */
	public Router getActualRouter(){
		return this.actualRouter;
	}
	
	/**
	 * Nastavení aktuální pozice packetu
	 * @param actualRouter router
	 */
	public void setActualRouter(Router actualRouter){
		this.actualRouter = actualRouter;
	}
	
	/**
	 * Metoda pro posunutí packetu o jeden krok
	 * V případě, že je router obsazený, nepošle se
	 * @return log k zapsání do souboru
	 */
	public String moveNext() {
		String log = "";
		if(path.get(pathIndex+1).isOccupied()){
				if(this.actualRouter.equals(path.get(pathIndex+1).getPacket().getNextRouter())&&(this.nextRouter.equals(this.nextRouter.getPacket().getActualRouter()))) {
					
					System.out.print("( Switching packets "+this.getID()+"-"+this.nextRouter.getPacket().getID()
															+" Packet"+this.nextRouter.getPacket().getID()+ ": " 
															+ this.nextRouter.getPacket().getActualRouter().getName() + " -> "
															+ this.getActualRouter().getName()+" ) -> ");
					
					log += "( Switching packets "+this.getID()+"-"+this.nextRouter.getPacket().getID()
							+" Packet"+this.nextRouter.getPacket().getID()+ ": " 
							+ this.nextRouter.getPacket().getActualRouter().getName() + " -> "
							+ this.getActualRouter().getName()+" ) -> ";
					
					Packet packet = this.nextRouter.getPacket();
					
					this.nextRouter.getPacket().setPathIndex(this.nextRouter.getPacket().getPathIndex()+1);
					this.nextRouter.getPacket().setNextRouter(this.nextRouter.getPacket().getPath().get(this.nextRouter.getPacket().getPathIndex()+1));
					this.nextRouter.getPacket().setActualRouter(this.nextRouter.getPacket().getPath().get(this.nextRouter.getPacket().getPathIndex()));
					
					this.actualRouter.setPacket(packet);
					this.actualRouter = this.nextRouter;
					this.pathIndex = this.pathIndex+1;
					this.nextRouter = this.path.get(this.pathIndex+1);
					
					this.actualRouter.setPacket(this);
					return log;
		}
			else {
				return log;
			}
		}
		else {
			actualRouter.setPacket(null);
			pathIndex++;
			actualRouter = path.get(pathIndex);
			//actualRouter.setOccupied(true);
			actualRouter.setPacket(this);
			if(!actualRouter.getName().equals("targetUser")) {
				nextRouter = path.get(pathIndex+1);
			} 
			return log;
		}
	}
	
	/**
	 * Získáni packet ID
	 * @return packed ID
	 */
	public int getID(){
		return this.ID;
	}
	
	
	/**
	 * Zjištění zda packet došel do cíle
	 * @return true/false
	 */
	public boolean isAtDestination() {
		boolean result;
		if(actualRouter.equals(path.get(path.size()-1))) {
			result = true;
		}else {
			result = false;
		}
		
		return result;
		
		
		
	}
	
	

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Packet" +this.ID+" [size=" + size + ", path=" + path + ", actualRouter=" + actualRouter + "\n";
	}

	/**
	 * Získání dalšího routeru v cestě
	 * @return další router
	 */
	public Router getNextRouter() {
		return nextRouter;
	}

	/**
	 * Nastavení dalšího routeru v cestě
	 * @param nextRouter další router
	 */
	public void setNextRouter(Router nextRouter) {
		this.nextRouter = nextRouter;
	}
	
	/**
	 * Získání indexu v cestě
	 * @return index v cestě
	 */
	public int getPathIndex(){
		return this.pathIndex;
	}
	
	/**
	 * Nastavení indexu v cestě
	 * @param index nový index v cestě
	 */
	public void setPathIndex(int index){
		this.pathIndex = index;
	}

	
	
	
	
}

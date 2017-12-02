import java.util.ArrayList;
import java.util.List;



public class Packet {
	private int ID;
	private double size;
	private List<Router> path;
	private Router actualRouter;
	private Router nextRouter;
	private int pathIndex = 0;
	
	public Packet(int ID ,double size,List<Router> path) {
		this.ID = ID;
		this.size = size;
		this.path = path;
		this.actualRouter = path.get(pathIndex);
		this.actualRouter.setOccupied(true);
		this.nextRouter = path.get(pathIndex+1);
		//this.pathIndex++;
	}
	
	public List<Packet> splitPacket(double maxSize){
		int id = this.ID;
		double size;
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
	
	public void setSize(double size){
		this.size = size;
	}
	
	public double getSize(){
		return this.size;
	}
	
	public void setPath(List<Router> path){
		this.path = path;
	}
	
	public List<Router> getPath(){
		return this.path;
	}
	
	public Router getActualRouter(){
		return this.actualRouter;
	}
	
	public void setActualRouter(Router actualRouter){
		this.actualRouter = actualRouter;
	}
	
	public void moveNext() {
		if(path.get(pathIndex+1).isOccupied()){
				if(this.actualRouter.equals(path.get(pathIndex+1).getPacket().getNextRouter())&&(this.nextRouter.equals(this.nextRouter.getPacket().getActualRouter()))) {
					
					System.out.print("( Switching packets "+this.getID()+"-"+this.nextRouter.getPacket().getID()+" Packet"+this.nextRouter.getPacket().getID()+ ": " + this.nextRouter.getPacket().getActualRouter().getName() + " -> "+ this.getActualRouter().getName()+" ) -> ");
					
					Packet packet = this.nextRouter.getPacket();
					
					this.nextRouter.getPacket().setPathIndex(this.nextRouter.getPacket().getPathIndex()+1);
					this.nextRouter.getPacket().setNextRouter(this.nextRouter.getPacket().getPath().get(this.nextRouter.getPacket().getPathIndex()+1));
					this.nextRouter.getPacket().setActualRouter(this.nextRouter.getPacket().getPath().get(this.nextRouter.getPacket().getPathIndex()));
					
					this.actualRouter.setPacket(packet);
					this.actualRouter = this.nextRouter;
					this.pathIndex = this.pathIndex+1;
					this.nextRouter = this.path.get(this.pathIndex+1);
					
					this.actualRouter.setPacket(this);
		}
			else return;
		}
		else {
		actualRouter.setPacket(null);
		pathIndex++;
		actualRouter = path.get(pathIndex);
		//actualRouter.setOccupied(true);
		actualRouter.setPacket(this);
		try {
			nextRouter = path.get(pathIndex+1);
		} catch (Exception e) {
		}
		
		
		}
	}
	
	public int getID(){
		return this.ID;
	}
	public void setID(int id){
		this.ID = id;
	}
	
	public boolean isAtDestination() {
		if(actualRouter.equals(path.get(path.size()-1))) {
			return true;
		}else {
			return false;
		}
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Packet" +this.ID+" [size=" + size + ", path=" + path + ", actualRouter=" + actualRouter + ", pathIndex=" + pathIndex
				+ "path size: "+(path.size()-1)+"]\n";
	}

	public Router getNextRouter() {
		return nextRouter;
	}

	public void setNextRouter(Router nextRouter) {
		this.nextRouter = nextRouter;
	}
	public int getPathIndex(){
		return this.pathIndex;
	}
	public void setPathIndex(int index){
		this.pathIndex = index;
	}

	
	
	
	
}

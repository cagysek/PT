import java.util.List;



public class Packet {
	private double size;
	private List<Router> path;
	private Router actualRouter;
	
	public Packet(double size,List<Router> path) {
		this.size = size;
		this.path = path;
	}
	
	public Packet splitPacket(){
		this.size = this.size/2;
		Packet packet = new Packet(this.size, this.path);
		return packet;
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
}

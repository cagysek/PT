import java.util.List;



public class Packet {
	private double size;
	private List<Router> path;
	private Router actualRouter;
	private int pathIndex = 0;
	
	public Packet(double size,List<Router> path) {
		this.size = size;
		this.path = path;
		this.actualRouter = path.get(pathIndex);
		//this.pathIndex++;
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
	
	public void moveNext() {
		pathIndex++;
		actualRouter = path.get(pathIndex);
		//pathIndex++;
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
		return "Packet [size=" + size + ", path=" + path + ", actualRouter=" + actualRouter + ", pathIndex=" + pathIndex
				+ "path size: "+(path.size()-1)+"]";
	}
	

	
	
	
	
}

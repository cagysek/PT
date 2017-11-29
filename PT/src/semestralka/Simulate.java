package semestralka;

import java.util.LinkedList;

/**
 * Třída {@code Simulate} se stará o simulaci komunikace mezi uzly
 * @author cagy
 *
 */
public class Simulate {

	/** Úkol který chceme provést */
	private Task task;
	
	private Topology topology;
	
	/**
	 * Vytvoření simulace pro zadaný task
	 * @param task zadání
	 */
	public Simulate(Task task,Topology topology){
		this.task = task;
		this.topology = topology;
		simulateCommunication();
		
	}
	
	/**
	 * Metoda starající se o simulaci komunikace
	 */
	private void simulateCommunication(){
		long time = System.nanoTime();
		CommunicationPath communicate = new CommunicationPath(topology);
		communicate.execute(task.getFromRouter());
		
        LinkedList<Router> path = communicate.getPath(task.getToRouter());
        long end = System.nanoTime() - time;
        System.out.println("doba pro tast" + (end/1000000));
        System.out.println("Path form "+task.getFromRouter()+" to router "+task.getToRouter());
        if(path != null) {	        
	        for( int i = 0 ; i<path.size()-1 ; i++) {
	        		System.out.print(path.get(i)+"  bandwidth: "+path.get(i).getNeighbours().get(path.get(i+1))+" ");
	        }
	        System.out.println("\n");
        }else {
        		System.out.println("Cesta nenalezena!\n");
        }
	}
	
	@Override
	public String toString(){
		return "Task:" +task.toString();
	}
}

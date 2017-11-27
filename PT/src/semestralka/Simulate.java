package semestralka;

/**
 * Třída {@code Simulate} se stará o simulaci komunikace mezi uzly
 * @author cagy
 *
 */
public class Simulate {

	/** Úkol který chceme provést */
	private Task task;
	
	/**
	 * Vytvoření simulace pro zadaný task
	 * @param task zadání
	 */
	public Simulate(Task task){
		this.task = task;
		simulateCommunication();
		
	}
	
	/**
	 * Metoda starající se o simulaci komunikace
	 */
	private void simulateCommunication(){
		
	}
	
	@Override
	public String toString(){
		return "Task:" +task.toString();
	}
}

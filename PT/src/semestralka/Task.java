package semestralka;

import java.time.*;

/******************
 * Instance třídy {@code Task} vytvoří přepravku s parametry čas,z routeru,do routeru, velikost dat  
 * @author cagy
 *
 */
public class Task {
	/** čas zadání úkolu */
	private final LocalTime time;
	
	/** z routeru */
	private final Router fromRouter;
	
	/** do routeru */
	private final Router toRouter;
	
	/** velikost dat */
	private final int size;
	
	/**
	 * Vytvoření přepravky
	 * @param time čas zadání
	 * @param fromRouter z routeru
	 * @param toRouter do routeru
	 * @param size velikost
	 */
	public Task(LocalTime time, Router fromRouter, Router toRouter, int size){
		this.time = time;
		this.fromRouter = fromRouter;
		this.toRouter = toRouter;
		this.size = size;
		
	}
	
	@Override
	public String toString(){
		return "time: "+time+" fromRouter: "+fromRouter.getName()+ " toRouter: "+toRouter.getName()+" Size: "+size;
				
	}
	
}

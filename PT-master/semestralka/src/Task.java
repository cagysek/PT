

import java.time.*;

/******************
 * Instance třídy {@code Task} vytvoří přepravku s parametry čas,z routeru,do routeru, velikost dat  
 * @author cagy
 *
 */
public class Task {
	/** čas zadání úkolu */
	private final LocalTime TIME;
	
	/** z routeru */
	private final Router FROM_ROUTER;
	
	/** do routeru */
	private final Router TO_ROUTER;
	
	/** velikost dat */
	private final int SIZE;
	
	/**
	 * Vytvoření přepravky
	 * @param time čas zadání
	 * @param fromRouter z routeru
	 * @param toRouter do routeru
	 * @param size velikost
	 */
	public Task(LocalTime time, Router fromRouter, Router toRouter, int size){
		this.TIME = time;
		this.FROM_ROUTER = fromRouter;
		this.TO_ROUTER = toRouter;
		this.SIZE = size;
		
	}
	
	@Override
	public String toString(){
		return "time: "+TIME+" fromRouter: "+FROM_ROUTER.getName()+ " toRouter: "+TO_ROUTER.getName()+" Size: "+SIZE;
				
	}

	/**
	 * získáni času tasku
	 * @return the time
	 */
	public LocalTime getTime() {
		return TIME;
	}

	/**
	 * získání počátečního routeru
	 * @return the fromRouter
	 */
	public Router getFromRouter() {
		return FROM_ROUTER;
	}

	/**
	 * Získání koncového routeru
	 * @return the toRouter 
	 */
	public Router getToRouter() {
		return TO_ROUTER;
	}

	/**
	 * Získání velikosti souboru
	 * @return the size
	 */
	public int getSize() {
		return SIZE;
	}
	
	
}

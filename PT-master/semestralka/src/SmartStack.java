

/**
 * Instance třídy {@code SmartStack} představuje zásobník, který router používá v případě,
 *  že dojde k přetečení toku na routeru
 * @author cagy
 *
 */
public class SmartStack {
	/** Vytvoření pole pro ukládání hodnot */
	  Packet[] data;
	  
	  /** Držení hodnoty volného indexu */
	  int freeIndex;
		
	  /**
	   * Metoda pro vkládání hodnot do zásobníku
	   * @param s vkládaná hodnota
	   */
	  public void push(Packet s){
	    if(data.length==freeIndex){ //kontrola velikosti pole 
	    	increaseCapacity();	//popřípadě zvětšení pole o dvojnásobek 
	    }
	    
		data[freeIndex] = s;
	    freeIndex++;
	    
	  }
		
	  /**
	   * Vytvoření instance
	   */
	  public SmartStack(){
	   this.data = new Packet[25];
	    freeIndex = 0;
	  }
	  
	  /**
	   * Vybrání posledního prvku z pole
	   * @return poslední prvek ze zásobníku
	   */
	  public Packet peek(){
		  if(freeIndex!=0){
			  return data[freeIndex-1];
		  }else{
			  return null;
		  }
			  
	  }
	  
	  /**
	   * Vybrání posledního prvku a jeho odstranění ze zásobníku
	   * @return podlední prvek ze zásobníku
	   */
	  public Packet pop(){
		  if(freeIndex!=0){
			  Packet pop = data[freeIndex-1];
			  data[freeIndex-1] = null;
			  freeIndex--;
			  return pop;
		  }else{
			  return null;
		  }
	  }
	  
	  /**
	   * Zvýšení kapacity zásobníku
	   */
	  public void increaseCapacity(){
		  Packet[] array = new Packet[data.length*2];
		  for(int i =0;i<data.length;i++){
			  array[i] = data[i];
		  }
		  this.data = array;
		  
	  }
	  
	  /**
	   * Metoda pro vrácení celého zásobníku
	   * @return zasobnik
	   */
	  public Packet[] getStack(){
		  return data;
	  }
}

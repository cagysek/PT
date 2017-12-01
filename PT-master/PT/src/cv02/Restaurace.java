package cv02;

import java.util.ArrayList;
import java.util.List;

public class Restaurace {
	List<Personal> listPersonal = new ArrayList<>();
	List<Zakaznici> listHost = new ArrayList<>();
	
	public Restaurace(){
		listPersonal.add(new Personal("BIG","BOSS"));
	}
	
	public List<Personal> getPersonal(){
		return listPersonal;
	}
	
	public List<Zakaznici> getZakaznici(){
		return listHost;
	}
}

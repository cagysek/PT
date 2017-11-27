package cv02;

import java.util.List;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Auto auto = new Auto();
		Motorka motorka = new Motorka();
		Kolo kolo = new Kolo();
		
		
		Zakaznici pepa = new Zakaznici("pepa","Zdepa");
		
		Restaurace restaurace = new Restaurace();
		List<Personal> personalList = restaurace.getPersonal();
		List<Zakaznici> zakaznikList = restaurace.getZakaznici();
		
		personalList.add(new Personal("Orland","Food"));
		personalList.add(new Personal("šéfík","kuchař"));
		
		zakaznikList.add(new Zakaznici("Mám","hlad"));
		
		
	}

}

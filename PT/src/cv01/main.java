package cv01;

public class main {

	public static void main(String[] args) {
		Tekutina tekutina = new Tekutina();
		Napoj napoj = new Napoj();
		Pivo pivo = new Pivo();
		
		
		Obdelnik obd = new Obdelnik(10,10);
		Trojuhelnik troj = new Trojuhelnik(10,10);
		Kruh kruh = new Kruh(10);
		
		System.out.println(obd.spoctiObsah()+troj.spoctiObsah()+kruh.spoctiObsah());
		
		
	}

}

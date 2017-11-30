package cv02;

public class Auto extends Vozidlo{
	private KoloAuto koloAutoLP;
	private KoloAuto koloAutoPP;
	private KoloAuto koloAutoLZ;
	private KoloAuto koloAutoPZ;
	private Kufr kufr;
	private Kapota kapota;

	
	public Auto(){
		super();		
		this.koloAutoLP = new KoloAuto();
		this.koloAutoPP = new KoloAuto();
		this.koloAutoLZ = new KoloAuto();
		this.koloAutoPZ = new KoloAuto();
		this.kufr = new Kufr();
		this.kapota = new Kapota();
		if(koloAutoLP == null && koloAutoPP == null && koloAutoLZ == null && koloAutoPZ == null && kufr == null && kapota==null){
			System.out.println("Auto není kompletní");
		}
	}
}

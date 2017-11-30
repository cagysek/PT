package cv01;

public class Trojuhelnik extends Obrazec{
	private double zakl;
	private double vyska;
	
	public Trojuhelnik(double zakl,double vyska){
		this.zakl = zakl;
		this.vyska = vyska;
	}
	@Override
	public double spoctiObsah() {
		
		return (zakl*vyska)/2;
	}
	

}

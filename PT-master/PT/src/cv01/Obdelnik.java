package cv01;

public class Obdelnik extends Obrazec {
	
	private double a;
	private double b;
	
	public Obdelnik(double a,double b){
		this.a = a;
		this.b = b;
	}

	@Override
	public double spoctiObsah() {
		
		return a*b;
	}

}

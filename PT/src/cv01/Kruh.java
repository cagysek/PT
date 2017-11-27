package cv01;

public class Kruh extends Obrazec {
	private double r;
	
	public Kruh(double r){
		this.r = r;
	}
	@Override
	public double spoctiObsah() {
		
		return Math.PI*r*r;
	}
}

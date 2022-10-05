package BusinessLayer;

import java.io.Serializable;
import java.util.Observable;

@SuppressWarnings({ "serial", "deprecation" })
public abstract class MenuItem extends Observable implements Serializable {

	private String nume;
	private double pret;

	public int hashCode() {
        final int prime = 31;
        int rezultat = 1;
        rezultat = prime * rezultat + ((nume == null) ? 0 : nume.hashCode());
        rezultat = (int) (prime * rezultat + Double.doubleToLongBits(pret));
        return rezultat;
    }
	public String getNume() {
		return this.nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public double getPret() {
		return pret;
	}

	public void setPret(double pret) {
		this.pret = pret;
	}
	

}

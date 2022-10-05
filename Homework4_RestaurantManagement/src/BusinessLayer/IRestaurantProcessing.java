package BusinessLayer;

import java.util.ArrayList;

public interface IRestaurantProcessing {
	
	public boolean isWellFormed();
	
	public void adaugareProdus(MenuItem produs);
	
	public void stergereProdus(String nume);
	
	public void editareProdus(String nume, double pret);
	
	public void creareComanda(Order comanda, ArrayList<MenuItem> lista);
	
	public double computePrice(int id);
	
	public void factura(int id);
}

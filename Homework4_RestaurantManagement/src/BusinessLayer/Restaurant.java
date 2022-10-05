package BusinessLayer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.JTable;

import DataLayer.FileWrite;
import DataLayer.RestaurantSerializator;

public class Restaurant extends Observable implements IRestaurantProcessing {

	private RestaurantSerializator serialization = new RestaurantSerializator();
	public static HashMap<Order, ArrayList<MenuItem>> comenzi = new HashMap<>();
	private ArrayList<MenuItem> produse = serialization.deserializationMenuItem();

	/**
	 * Aceasta metoda verifica daca elementele pe care lucram sunt instante ale
	 * clasei Restaurant.
	 * 
	 * @return - true in caz afirmativ si false in caz negativ
	 */
	public boolean isWellFormed() {
		if (this instanceof Restaurant)
			return true;
		return false;
	}

	/**
	 * Aceasta metoda adauga un produs in lista de produse si se face serializare
	 * pentru a memora datele introduse
	 * 
	 * @param produs - reprezinta produsul pe care dorim sa il inseram
	 * @pre produse.size() == null
	 * @post produse.size() == size * @pre + 1
	 */
	@Override
	public void adaugareProdus(MenuItem produs) {
		assert produs != null;
		assert isWellFormed();
		int size = produse.size();
		try {
			produse.add(produs);
			serialization.serializationMenuItem(produse);
		} catch (Exception e) {
			System.out.println("Adaugare esuata");
		}
		assert isWellFormed();
		assert produs != null;
		assert produse.size() == size + 1;
	}

	/**
	 * Aceasta metoda sterge un produs in lista de produse si se face serializare
	 * pentru a memora datele actualizate
	 * 
	 * @param nume - reprezinta identificatorul produsului pe care dorim sa il
	 *             stergem
	 * @pre produse.size() == size
	 * @post produse.size() == size * @pre - 1
	 */
	@Override
	public void stergereProdus(String nume) {
		assert nume != "";
		assert isWellFormed();
		int size = produse.size();
		try {
			ArrayList<MenuItem> deSters = new ArrayList<>();
			for (int i = 0; i < produse.size(); i++) {
				if (produse.get(i) instanceof BaseProduct) {
					if (produse.get(i).getNume().compareTo(nume) == 0) {
						deSters.add(produse.get(i));
					}
				}
				if (produse.get(i) instanceof CompositeProduct) {
					if (produse.get(i).getNume().compareTo(nume) == 0) {
						produse.remove(i);
					}

					for (MenuItem produs : ((CompositeProduct) produse.get(i)).getListaProduse()) {
						if (produs.getNume().compareTo(nume) == 0) {
							deSters.add(produse.get(i));
						}
					}
				}
			}

			for (MenuItem j : deSters) {
				produse.remove(j);
			}

			this.serialization.serializationMenuItem(produse);
		} catch (Exception e) {
			System.out.println("Stergere esuata");
		}
		assert isWellFormed();
		assert nume != "";
		assert produse.size() == size - 1;
	}

	/**
	 * Aceasta metoda actualizeaza pretul unui produs care se afla in lista de
	 * produse
	 * 
	 * @param nume - reprezinta identificatorul produsului pe care dorim sa il
	 *             actualizam
	 * @param pret - reprezinta parametrul pe care dorim sa il modificam, adica
	 *             pretul produsului
	 * @pre produse.size() == size
	 * @post produse.size() == @pre
	 */
	@Override
	public void editareProdus(String nume, double pret) {
		assert nume != "";
		assert isWellFormed();
		int size = produse.size();
		try {
			for (int i = 0; i < produse.size(); i++) {
				if (produse.get(i) instanceof BaseProduct) {
					if (produse.get(i).getNume().compareTo(nume) == 0) {
						produse.get(i).setPret(pret);
					}
				}
				if (produse.get(i) instanceof CompositeProduct) {
					if (produse.get(i).getNume().compareTo(nume) == 0) {
						produse.get(i).setPret(pret);
					}

					double price = 0;
					for (MenuItem produs : ((CompositeProduct) produse.get(i)).getListaProduse()) {
						if (produs.getNume().compareTo(nume) == 0) {
							System.out.println("HAUS");
							produs.setPret(pret);
						}
						price = price + produs.getPret();
					}

					produse.get(i).setPret(price);
				}

			}
			this.serialization.serializationMenuItem(produse);
		} catch (Exception e) {
			System.out.println("Editare esuata");
		}
		assert isWellFormed();
		assert nume != "";
		assert produse.size() == size;
	}

	/**
	 * Aceasta metoda creaza o comanda
	 * 
	 * @param comanda - reprezinta comanda facuta de chelner
	 * @param lista   - reprezinta lista in care se stocheaza aceste comenzi
	 * @pre lista.size() == null
	 * @post lista.size() == size * @pre + 1
	 */
	@Override
	public void creareComanda(Order comanda, ArrayList<MenuItem> lista) {
		assert comanda != null;
		assert isWellFormed();
		int size = lista.size();
		try {
			Restaurant.comenzi.put(comanda, lista);
		} catch (Exception e) {
			System.out.println("Creare comanda esuata");
		}
		assert lista.size() == size + 1;
		assert isWellFormed();
	}

	/**
	 * Aceasta metoda calculeaza pretul unei comenzi
	 * 
	 * @param id - este elementul prin care identificam comanda pentru care dorim sa
	 *           calculam pretul
	 * @return - pretul final al comenzii obtinut prin insumarea fiecarui produs
	 *         aflat in comanda
	 */
	@Override
	public double computePrice(int id) {
		double pretFinal = 0;

		for (Order order : Restaurant.comenzi.keySet()) {
			if (order.getIdComanda() == id) {
				for (MenuItem menuItem : Restaurant.comenzi.get(order)) {
					pretFinal = pretFinal + menuItem.getPret();
				}
				order.setPret(pretFinal);
			}
		}
		return pretFinal;

	}

	/**
	 * Aceasta metoda creaza un bon pentru o comanda
	 * 
	 * @param id - este elementul prin care identificam comanda pentru care dorim sa
	 *           generam bonul
	 * 
	 */
	@Override
	public void factura(int id) {
		for (Order order : Restaurant.comenzi.keySet()) {
			if (order.getIdComanda() == id) {
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("Bon" + FileWrite.index++ + ".txt", false));
					bw.write("Nota de plata");
					bw.newLine();

					bw.write("Produse...Pret");
					bw.newLine();
					for (MenuItem menuItem : Restaurant.comenzi.get(order)) {
						bw.write(menuItem.getNume() + "..." + menuItem.getPret());
						bw.newLine();
					}

					bw.write("Total: " + order.getPret());
					bw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public HashMap<Order, ArrayList<MenuItem>> getComenzi() {
		return comenzi;
	}

	public void setComenzi(HashMap<Order, ArrayList<MenuItem>> comenzi) {
		Restaurant.comenzi = comenzi;
	}

	public ArrayList<MenuItem> getProduse() {
		return produse;
	}

	public void setProduse(ArrayList<MenuItem> produse) {
		this.produse = produse;
	}

	public RestaurantSerializator getSerialization() {
		return serialization;
	}

	public JTable tabelProduse() {
		String[] coloane = { "Nume", "Pret" };
		Object[][] date = new Object[produse.size()][2];

		for (int i = 0; i < produse.size(); i++) {
			date[i][0] = produse.get(i).getNume();
			date[i][1] = produse.get(i).getPret();
		}
		return new JTable(date, coloane);
	}

	public JTable tabelComenzi() {
		String[] coloane = { "Id Comanda", "Data", "Masa", "Pret" };
		Object[][] date = new Object[Restaurant.comenzi.size()][4];

		int i = 0;
		for (Order comanda : Restaurant.comenzi.keySet()) {
			date[i][0] = comanda.getIdComanda();
			date[i][1] = comanda.getData();
			date[i][2] = comanda.getMasa();

			if (comanda.getPret() > 0) {
				date[i][3] = comanda.getPret();
			} else {
				date[i][3] = "-";
			}
			i++;
		}
		return new JTable(date, coloane);
	}

	public JTable tabelProduseComanda() {
		String[] coloane = { "Id Comanda", "Produs" };
		Object[][] date = new Object[100][100];

		int i = 0;
		for (Order order : Restaurant.comenzi.keySet()) {
			for (MenuItem menuItem : Restaurant.comenzi.get(order)) {
				date[i][0] = order.getIdComanda();
				date[i][1] = menuItem.getNume();
				i++;
			}
		}
		return new JTable(date, coloane);
	}

}

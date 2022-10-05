package BusinessLayer;

import java.util.*;

public class Order {

	public int idComanda;
	public Date data;
	public int masa;
	public double pret;
	public List<MenuItem> lista = new ArrayList<MenuItem>();

	public Order(int id, int masa) {
		this.idComanda = id;
		this.masa = masa;
		this.data = new Date();
	}
	
	public int getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<MenuItem> getLista() {
		return lista;
	}

	public void setLista(List<MenuItem> lista) {
		this.lista = lista;
	}

	public int getMasa() {
		return masa;
	}

	public void setMasa(int masa) {
		this.masa = masa;
	}

	public double getPret() {
		return pret;
	}

	public void setPret(double pret) {
		this.pret = pret;
	}

}

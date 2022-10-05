package BusinessLayer;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class CompositeProduct extends MenuItem
{
    private ArrayList<BaseProduct> listaProduse = new ArrayList<>();
    private String nume;
	private double pret;
    
    
    public CompositeProduct (String nume)
    {
    	this.nume = nume;
    }
    public CompositeProduct (ArrayList<BaseProduct> lista, String nume)
    {
    	this.listaProduse = lista;
    	this.nume = nume;
    }
	
    public void add(MenuItem e) {
		listaProduse.add((BaseProduct) e);
		
	}
    
	public void remove(MenuItem e)
    {
		listaProduse.remove((BaseProduct) e);
	} 
	
	public ArrayList<BaseProduct> getListaProduse() 
	{
		return listaProduse;
	}

	public void setListaProduse(ArrayList<BaseProduct> lista) 
	{
		this.listaProduse = lista;
	}
	
	public String getNume() {
		return nume;
	}
	
	public void setNume(String nume) {
		this.nume = nume;
	}
	

}

package PresentationLayer;

import javax.swing.*;

import BusinessLayer.Restaurant;

public class View extends JFrame{

	private static final long serialVersionUID = 1L;
	Controller controller = new Controller(this);
	private JFrame frameAdministrator = new JFrame();
	private JFrame frameChelner = new JFrame();
	private JFrame frameBucatar = new JFrame();
	
	//frame Rastaurant
	public JTable tabel;
	private JPanel panou = new JPanel();
	private JButton administrator = new JButton("Administrator");
	private JButton chelner = new JButton("Chelner");
	private JButton bucatar = new JButton("Bucatar");

	//frame Administrator
	private JPanel panelOperatii = new JPanel();
	private JPanel panelDate = new JPanel();
	private JPanel panelTabel = new JPanel();
	private JButton adaugareBaseProduct = new JButton("Adaugare BaseProduct");
	private JButton adaugareCompositeProduct = new JButton("Adaugare CompositeProduct");
	private JButton stergereProdus = new JButton("Stergere produs");
	private JButton editareProdus = new JButton("Editare produs");
	private JLabel numeProdus = new JLabel("Nume Produs:");
	private JLabel listaProduse = new JLabel("Lista Componente:");
	private JLabel pretProdus = new JLabel("Pret:");
	private JTextField textNumeProdus = new JTextField("", 20);
	private JTextField textListaProduse = new JTextField("", 20);
	private JTextField textPretProdus = new JTextField("", 20);

	//frame Chelner
	private JPanel panelOperatii2 = new JPanel();
	private JPanel panelDate2 = new JPanel();
	private JPanel panelTabel2 = new JPanel();
	private JButton creazaComanda = new JButton("Creaza comanda");
	private JButton calculeazaPret = new JButton("Calculeaza pret");
	private JButton creazaBon = new JButton("Creaza bon");
	private JButton afisareComanda = new JButton("Afisare");
	private JLabel numeComanda = new JLabel("Componente comanda:");
	private JLabel masa = new JLabel("Masa:");
	private JLabel id = new JLabel("Id comanda:");
	private JTextField textNumeComanda = new JTextField("", 20);
	private JTextField textMasa = new JTextField("", 5);
	private JTextField textId = new JTextField("", 5);

	//frame Bucatar
	private JPanel panelTabel3 = new JPanel();
	public JTable tabelBucatar = null;
	
	Restaurant restaurant = new Restaurant();
	public static int idComanda = 1;

	public View() {
		creareMeniu();
		panou.add(administrator);
		panou.add(chelner);
		panou.add(bucatar);
		this.add(panou);
		administrator.addActionListener(controller);
		adaugareBaseProduct.addActionListener(controller);
		adaugareCompositeProduct.addActionListener(controller);
		stergereProdus.addActionListener(controller);
		editareProdus.addActionListener(controller);
		chelner.addActionListener(controller);
		creazaComanda.addActionListener(controller);
		calculeazaPret.addActionListener(controller);
		creazaBon.addActionListener(controller);
		bucatar.addActionListener(controller);
		
	}
	
	public JPanel getPanelDate() {
		return panelDate;
	}

	public static int getIdComanda() {
		return idComanda;
	}

	public static void setIdComanda(int idComanda) {
		View.idComanda = idComanda;
	}

	public JPanel getPanelTabel() {
		return panelTabel;
	}

	public JPanel getPanelDate2() {
		return panelDate2;
	}

	public JPanel getPanelTabel2() {
		return panelTabel2;
	}

	public JLabel getId() {
		return id;
	}

	public JTextField getTextId() {
		return textId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JFrame getFrameAdministrator() {
		return frameAdministrator;
	}

	public JFrame getFrameChelner() {
		return frameChelner;
	}

	public JFrame getFrameBucatar() {
		return frameBucatar;
	}

	public JPanel getPanou() {
		return panou;
	}

	public JButton getAdministrator() {
		return administrator;
	}

	public JButton getCalculeazaPret() {
		return calculeazaPret;
	}

	public JButton getChelner() {
		return chelner;
	}

	public JButton getBucatar() {
		return bucatar;
	}

	public JPanel getPanelOperatii() {
		return panelOperatii;
	}

	public JTable getTabel() {
		return tabel;
	}

	public JButton getAdaugareBaseProduct() {
		return adaugareBaseProduct;
	}

	public JButton getAdaugareCompositeProduct() {
		return adaugareCompositeProduct;
	}

	public JButton getStergereProdus() {
		return stergereProdus;
	}

	public JButton getEditareProdus() {
		return editareProdus;
	}

	public JLabel getNumeProdus() {
		return numeProdus;
	}

	public JLabel getPretProdus() {
		return pretProdus;
	}

	public JTextField getTextNumeProdus() {
		return textNumeProdus;
	}

	public JTextField getTextPretProdus() {
		return textPretProdus;
	}

	public JPanel getPanelOperatii2() {
		return panelOperatii2;
	}

	public JButton getCreazaComanda() {
		return creazaComanda;
	}

	public JPanel getPanelTabel3() {
		return panelTabel3;
	}

	public JTextField getTextListaProduse() {
		return textListaProduse;
	}

	public JButton getCreazaBon() {
		return creazaBon;
	}

	public JButton getAfisareComanda() {
		return afisareComanda;
	}

	public JLabel getNumeComanda() {
		return numeComanda;
	}

	public JLabel getMasa() {
		return masa;
	}

	public JLabel getListaProduse() {
		return listaProduse;
	}

	public JTextField getTextNumeComanda() {
		return textNumeComanda;
	}

	public JTextField getTextMasa() {
		return textMasa;
	}

	public void setPanelTabel(JPanel panelTabel) {
		this.panelTabel = panelTabel;
	}

	public void setPanelTabel2(JPanel panelTabel) {
		this.panelTabel2 = panelTabel;
	}
	
	public void setPanelTabel3(JPanel panelTabel) {
		this.panelTabel3 = panelTabel;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	

	public void creareMeniu() {
		this.setVisible(true);
		this.setSize(450, 100);
		this.setTitle("Restaurant");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	public void setTabelBucatar(JTable table) {
		this.tabelBucatar = table;
	}
	
}

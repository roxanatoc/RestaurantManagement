package PresentationLayer;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.Restaurant;

public class Controller implements ActionListener {
	private View view;

	public Controller(View v) {
		this.view = v;
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.getAdministrator()) {
			view.getFrameAdministrator().setSize(1050, 550);
			view.getFrameAdministrator().setTitle("Administrator");
			view.getFrameAdministrator().setVisible(true);
			view.getFrameAdministrator().setResizable(false);
			view.getFrameAdministrator().setLayout(new FlowLayout());

			view.getFrameAdministrator().remove(view.getPanelTabel());

			view.getPanelDate().setLayout(new FlowLayout());
			view.getPanelDate().add(view.getNumeProdus());
			view.getPanelDate().add(view.getTextNumeProdus());			
			view.getPanelDate().add(view.getListaProduse());
			view.getPanelDate().add(view.getTextListaProduse());
			view.getPanelDate().add(view.getPretProdus());
			view.getPanelDate().add(view.getTextPretProdus());
			
			view.getFrameAdministrator().add(view.getPanelDate());

			view.getPanelOperatii().setLayout(new FlowLayout());
			view.getPanelOperatii().add(view.getAdaugareBaseProduct());
			view.getPanelOperatii().add(view.getAdaugareCompositeProduct());
			view.getPanelOperatii().add(view.getStergereProdus());
			view.getPanelOperatii().add(view.getEditareProdus());
			view.getFrameAdministrator().add(view.getPanelOperatii());
			
			view.getFrameAdministrator().remove(view.getPanelTabel());
			view.setPanelTabel(new JPanel());
			view.tabel = view.getRestaurant().tabelProduse();
			view.getPanelTabel().add(new JScrollPane(view.tabel));
			view.getFrameAdministrator().add(view.getPanelTabel());
			view.getFrameAdministrator().revalidate();
		}
		if (e.getSource() == view.getChelner()) {
			view.getFrameChelner().setSize(1050, 550);
			view.getFrameChelner().setTitle("Chelner");
			view.getFrameChelner().setVisible(true);
			view.getFrameChelner().setResizable(true);
			view.getFrameChelner().setLayout(new FlowLayout());

			view.getFrameChelner().remove(view.getPanelTabel2());

			view.getPanelDate2().setLayout(new FlowLayout());
			view.getPanelDate2().add(view.getNumeComanda());
			view.getPanelDate2().add(view.getTextNumeComanda());
			view.getPanelDate2().add(view.getMasa());
			view.getPanelDate2().add(view.getTextMasa());
			view.getPanelDate2().add(view.getId());
			view.getPanelDate2().add(view.getTextId());
			view.getFrameChelner().add(view.getPanelDate2());

			view.getPanelOperatii2().setLayout(new FlowLayout());
			view.getPanelOperatii2().add(view.getCreazaComanda());
			view.getPanelOperatii2().add(view.getCalculeazaPret());
			view.getPanelOperatii2().add(view.getCreazaBon());	
			view.getFrameChelner().add(view.getPanelOperatii2());
			
			view.getFrameChelner().remove(view.getPanelTabel());
			view.setPanelTabel(new JPanel());
			view.tabel = view.getRestaurant().tabelProduse();
			view.getPanelTabel().add(new JScrollPane(view.tabel));
			view.getFrameChelner().add(view.getPanelTabel());
			view.getFrameChelner().revalidate();
			
			if (Restaurant.comenzi.size() != 0) {
				view.getFrameChelner().remove(view.getPanelTabel2());
				view.setPanelTabel2(new JPanel());
				view.tabel = view.getRestaurant().tabelComenzi();
				view.getPanelTabel2().add(new JScrollPane(view.tabel));
				view.getFrameChelner().add(view.getPanelTabel2());
				view.getFrameChelner().revalidate();
				view.getTextNumeComanda().setText("");
				view.getTextMasa().setText("");
			}
		}
		if (e.getSource() == view.getBucatar()) {
			view.getFrameBucatar().setSize(550, 550);
			view.getFrameBucatar().setTitle("Bucatar");
			view.getFrameBucatar().setVisible(true);
			view.getFrameBucatar().setResizable(false);
			view.getFrameBucatar().setLayout(new FlowLayout());

			view.getFrameBucatar().remove(view.getPanelTabel3());
			view.setPanelTabel3(new JPanel());
			view.setTabelBucatar(view.getRestaurant().tabelProduseComanda());
			view.getPanelTabel3().add(new JScrollPane(view.tabelBucatar));
			view.getFrameBucatar().add(view.getPanelTabel3());
			view.getFrameBucatar().revalidate();
		}
		if (e.getSource() == view.getAdaugareBaseProduct()) {
			String nume = view.getTextNumeProdus().getText();
			double pret = Double.parseDouble(view.getTextPretProdus().getText());
			view.getRestaurant().adaugareProdus(new BaseProduct(nume, pret));
			view.getTextNumeProdus().setText("");
			view.getTextPretProdus().setText("");
			
			view.getFrameAdministrator().remove(view.getPanelTabel());
			view.setPanelTabel(new JPanel());
			view.tabel = view.getRestaurant().tabelProduse();
			view.getPanelTabel().add(new JScrollPane(view.tabel));
			view.getFrameAdministrator().add(view.getPanelTabel());
			view.getFrameAdministrator().revalidate();
				
		}
		if (e.getSource() == view.getAdaugareCompositeProduct()) {
			String nume = view.getTextNumeProdus().getText();
			double pret = 0;
			CompositeProduct p = new CompositeProduct(nume);
			String[] lista = view.getTextListaProduse().getText().split("[,]");
			for (int i = 0; i < lista.length; i++) {
				if (lista[i].charAt(0) == ' ') {
					lista[i] = lista[i].substring(1);
				}
			}
			int ok = 0;
			for (int i = 0; i < lista.length; i++) {
				for (int j = 0; j < view.getRestaurant().getProduse().size(); j++) {
					if(view.getRestaurant().getProduse().get(j).getNume().compareTo(lista[i]) == 0) {
						pret += view.getRestaurant().getProduse().get(j).getPret();
						p.add(view.getRestaurant().getProduse().get(j));
						ok = 1;
					}
				}
			}
			if (ok == 1) {
			p.setPret(pret);
			view.getRestaurant().adaugareProdus(p);
			}
			else {
				System.out.println("Produs indisponibil");
			}
			view.getTextNumeProdus().setText("");
			view.getTextListaProduse().setText("");
			view.getTextPretProdus().setText("");
			
			view.getFrameAdministrator().remove(view.getPanelTabel());
			view.setPanelTabel(new JPanel());
			view.tabel = view.getRestaurant().tabelProduse();
			view.getPanelTabel().add(new JScrollPane(view.tabel));
			view.getFrameAdministrator().add(view.getPanelTabel());
			view.getFrameAdministrator().revalidate();
				
		}
		if (e.getSource() == view.getStergereProdus()) { 
			String nume = view.getTextNumeProdus().getText();
			view.getRestaurant().stergereProdus(nume);
			view.getTextNumeProdus().setText("");
			view.getTextPretProdus().setText("");
			
			view.getFrameAdministrator().remove(view.getPanelTabel());
			view.setPanelTabel(new JPanel());
			view.tabel = view.getRestaurant().tabelProduse();
			view.getPanelTabel().add(new JScrollPane(view.tabel));
			view.getFrameAdministrator().add(view.getPanelTabel());
			view.getFrameAdministrator().revalidate();
		
		}
		if (e.getSource() == view.getEditareProdus()) { 
			String nume = view.getTextNumeProdus().getText();
			double pret = Double.parseDouble(view.getTextPretProdus().getText());
			view.getRestaurant().editareProdus(nume, pret);
			view.getTextNumeProdus().setText("");
			view.getTextPretProdus().setText("");
			
			view.getFrameAdministrator().remove(view.getPanelTabel());
			view.setPanelTabel(new JPanel());
			view.tabel = view.getRestaurant().tabelProduse();
			view.getPanelTabel().add(new JScrollPane(view.tabel));
			view.getFrameAdministrator().add(view.getPanelTabel());
			view.getFrameAdministrator().revalidate();
			
		}
		if (e.getSource() == view.getCreazaComanda()) {
			int masa = Integer.parseInt(view.getTextMasa().getText());

			Order o = new Order(view.getIdComanda(), masa);
			ArrayList<MenuItem> oArray = new ArrayList<>();
			String[] lista = view.getTextNumeComanda().getText().split("[,]");
			for (int i = 0; i < lista.length; i++) {
				if (lista[i].charAt(0) == ' ') {
					lista[i] = lista[i].substring(1);
				}
			}
			int ok = 0;
			for (int i = 0; i < lista.length; i++) {
				for (int j = 0; j < view.getRestaurant().getProduse().size(); j++) {
					if(view.getRestaurant().getProduse().get(j).getNume().compareTo(lista[i]) == 0) {
						oArray.add(view.getRestaurant().getProduse().get(j));
						ok = 1;
					}
				}
			}
			if (ok == 1) {
				view.getRestaurant().creareComanda(o, oArray);
				view.setIdComanda(view.getIdComanda() + 1);
			}
			else {
				System.out.println("Produs indisponibil");
			}
			
			view.getFrameChelner().remove(view.getPanelTabel2());
			view.setPanelTabel2(new JPanel());
			view.tabel = view.getRestaurant().tabelComenzi();
			view.getPanelTabel2().add(new JScrollPane(view.tabel));
			view.getFrameChelner().add(view.getPanelTabel2());
			view.getFrameChelner().revalidate();
			view.getTextNumeComanda().setText("");
			view.getTextMasa().setText("");
		}
		if (e.getSource() == view.getCalculeazaPret()) {
			int id = Integer.parseInt(view.getTextId().getText());
			view.getRestaurant().computePrice(id);
			view.getTextId().setText("");
			view.getFrameChelner().remove(view.getPanelTabel2());
			view.setPanelTabel2(new JPanel());
			view.tabel = view.getRestaurant().tabelComenzi();
			view.getPanelTabel2().add(new JScrollPane(view.tabel));
			view.getFrameChelner().add(view.getPanelTabel2());
			view.getFrameChelner().revalidate();
			view.getTextNumeComanda().setText("");
			view.getTextMasa().setText("");
		}
		if (e.getSource() == view.getCreazaBon()) {
			int id = Integer.parseInt(view.getTextId().getText());
			view.getRestaurant().factura(id);
			view.getTextId().setText("");
		}
	}

}

package DataLayer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import BusinessLayer.MenuItem;
import tema4.Main;

public class RestaurantSerializator {
	
	public void serializationMenuItem(ArrayList<MenuItem> produs) {
		try {
			FileOutputStream f = new FileOutputStream(Main.serFile);
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(produs);
			out.close();
			f.close();
			System.out.printf("Serialized data is saved in Restaurant.ser\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<MenuItem> deserializationMenuItem() {

		ArrayList<MenuItem> list = new ArrayList<MenuItem>();
		try {
			FileInputStream g = new FileInputStream(Main.serFile);
			ObjectInputStream in = new ObjectInputStream(g);
			list = (ArrayList<MenuItem>) in.readObject();
			in.close();
			g.close();
			return list;
		} catch (IOException e) {
			 e.printStackTrace();
			return list;
		} catch (ClassNotFoundException e) {
			System.out.println("EROARE");
			e.printStackTrace();
			return list;
		}
	}
}

package tema4;

import PresentationLayer.View;

public class Main {

	public static String serFile;
	
	public static void main(String[] args) {
		serFile = args[0];
		new View();
	}
}

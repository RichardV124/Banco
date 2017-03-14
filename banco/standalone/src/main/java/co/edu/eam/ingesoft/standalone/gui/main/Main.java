package co.edu.eam.ingesoft.standalone.gui.main;


import java.util.concurrent.ThreadLocalRandom;

import javax.naming.NamingException;

import co.edu.eam.ingesoft.standalone.gui.vista.VentanaPrincipal;

public class Main {

	public static void main(String[] args) throws NamingException {
		new VentanaPrincipal().setVisible(true);
		
//		Long numero = ThreadLocalRandom.current().nextLong(100000000000000L, 999999999999999L);
//		System.out.println(numero);
//		String cadena = numero.toString();
//		int ultimo = 1;
//		int digito = 0;
//		for (int i = 0; i < cadena.length(); i++) {
//
//			char letra = cadena.charAt(i);
//			if (Character.isDigit(letra)) {
//				digito = Integer.parseInt(String.valueOf(cadena.charAt(i)));
//
//				ultimo *= digito;
//			}
//		}
//		String fin = ultimo + "";
//		cadena = cadena + fin.charAt(fin.length() - 1);
//		System.out.println(cadena);
		
	}

}

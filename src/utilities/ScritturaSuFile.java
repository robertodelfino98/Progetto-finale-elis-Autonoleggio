package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ScritturaSuFile {
	public  static void scritturaCapienza(int c) {
		   try(BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\User\\eclipse-workspace\\Autonoleggio\\Dati_Autonoleggio_Capienza.txt"))){
			   out.write(String.valueOf(c));
		   } catch (IOException e) {
				
				e.printStackTrace();
		   }
	}
	
	public static int letturaCapienza() {
	       try(BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\User\\eclipse-workspace\\Autonoleggio\\Dati_Autonoleggio_Capienza.txt"))) {

	           String riga;
	           
	           while((riga = in.readLine()) != null) {
	        	   return Integer.parseInt(riga);
			   }
	        } catch(FileNotFoundException e) {
	           System.out.println("File non trovato!");
	       }
	       catch(IOException e) {
	           System.out.println("Eccezione IO");
	       }
	       return 0;
	   }
	   public static void scritturaPenale(int p) {
		   try(BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\User\\eclipse-workspace\\Autonoleggio\\Dati_Autonoleggio_Penale.txt"))){
			   out.write(String.valueOf(p));
		   } catch (IOException e) {
		
			e.printStackTrace();
		   }
	   }
	   public static int letturaPenale() {
	       try(BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\User\\eclipse-workspace\\Autonoleggio\\Dati_Autonoleggio_Penale.txt"))) {

	           String riga;
	           
	           while((riga = in.readLine()) != null) {
	        	   return Integer.parseInt(riga) ;
	           }
	          

	       }
	       catch(FileNotFoundException e) {
	           System.out.println("File non trovato!");
	       }
	       catch(IOException e) {
	           System.out.println("Eccezione IO");
	       }
	       return 0;
	   }
	   public static void scritturaNomeAutonoleggio(String s) {
		   try(BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\User\\eclipse-workspace\\Autonoleggio\\Dati_Autonoleggio_Nome_Autonoleggio.txt"))){
			   out.write(s);
		   } catch (IOException e) {
				e.printStackTrace();
		   }
	   }
	   public static String letturaNomeAutonoleggio() {
	       try(BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\User\\eclipse-workspace\\Autonoleggio\\Dati_Autonoleggio_Nome_Autonoleggio.txt"))) {

	           String riga;
	           
	           while((riga = in.readLine()) != null) {
	        	   return riga;
	           }
	          

	       }
	       catch(FileNotFoundException e) {
	           System.out.println("File non trovato!");
	       }
	       catch(IOException e) {
	           System.out.println("Eccezione IO");
	       }
	       return null;
	   }
	   public  static void scritturaGiornoAnnulamentoNoleggi(int c) {
		   try(BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\User\\eclipse-workspace\\Autonoleggio\\Dati_Autonoleggio_Giorno.txt"))){
			   out.write(String.valueOf(c));
		   } catch (IOException e) {
			
			e.printStackTrace();
		   }
	}
	   public static int letturaGiornoAnnulamentoNoleggi() {
	       try(BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\User\\eclipse-workspace\\Autonoleggio\\Dati_Autonoleggio_Giorno.txt"))) {

	           String riga;
	           
	           while((riga = in.readLine()) != null) {
	        	   return Integer.parseInt(riga) ;
	           }
	       }
	       catch(FileNotFoundException e) {
	           System.out.println("File non trovato!");
	       }
	       catch(IOException e) {
	           System.out.println("Eccezione IO");
	       }
	       return 0;
	   }
}
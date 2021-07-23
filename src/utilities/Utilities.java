package utilities;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.time.temporal.ChronoUnit;

import model.Automobile;
import model.Categoria;
import model.Noleggio;

public class Utilities {
	 private static SecureRandom random = new SecureRandom();

	    /** different dictionaries used */
	    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
	    private static final String NUMERIC = "0123456789";
	    private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";
	    

	    /**
	     * Method will generate random string based on the parameters
	     * 
	     * @param len
	     *            the length of the random string
	     * @param dic
	     *            the dictionary used to generate the password
	     * @return the random password
	     */

	    public static String generatePassword(){
	        Random r = new Random();
	        String result = "";
	        for(int i = 0; i<3; i++){
		        int index = r.nextInt((ALPHA_CAPS.length()-1));
		        result += ALPHA_CAPS.charAt(index);
	        }
	        for(int i=0; i<2; i++){
	            int index = r.nextInt((ALPHA.length()-1));
	            result += ALPHA.charAt(index);
	        }
	        result += NUMERIC.charAt(r.nextInt((NUMERIC.length()-1)));
	        result += SPECIAL_CHARS.charAt(r.nextInt((SPECIAL_CHARS.length()-1)));
			return result;
	    }
	    

		public boolean controllaPassword(String password){
			password = password.trim(); // trimmo la password affinch� gli spazi bianchi all'inizio ed alla fine, non contino
			int lunghezzaPassword = password.length();
			char carattere = ' ';
	        boolean lunghezzaGiusta = false;
	        boolean carattereSpeciale = false;
	        boolean isMaggioreDiZero = false;
	        boolean isMaiuscola = false;

	        //----------------------------
	        int contatoreNumeri = 0;
			int contatoreMaiuscola = 0;
	        //-----------------------------
	        
	        //controllo se la password � della
			if (lunghezzaPassword >= 7){ 
				lunghezzaGiusta = true;
			} else {
				lunghezzaGiusta = false;
			}

	        //controllo se presente almeno un carattere speciale
	        for(int i = 0; i < lunghezzaPassword; i++ ){
				carattere = password.charAt(i);	
				if( !( ( (carattere >= 'a' && carattere <= 'z') ||  (carattere >= 'A' && carattere <= 'Z') ) || (carattere >= '0' && carattere <= '9') ) ){
					carattereSpeciale = true;

				}

	            //controllo se presente almeno un numerico
				if ((carattere >= '0' && carattere <= '9')){
					contatoreNumeri += 1;
					}
					
	            //controllo se presente almeno un maiuscolo
				if (carattere >= 'A' && carattere <= 'Z'){
					contatoreMaiuscola += 1;
					}
			}

	        //controllo se presenti aleno un numero
			if (contatoreNumeri > 0){
				isMaggioreDiZero = true;	
			} else {
			    isMaggioreDiZero = false;
			}

	        //controllo se presente almeno una maiuscola
			if(contatoreMaiuscola >= 1){
				isMaiuscola = true;
			} else {
				isMaiuscola = false;
			}


			if(lunghezzaGiusta==true && carattereSpeciale==true && isMaggioreDiZero==true && isMaiuscola==true){
						return true;
					}else{
						return false;
					}

		}

		public static synchronized void controlloNoleggio(List<Automobile> automobiliFiltrate, LocalDate dI, LocalDate dF) {
			for(Automobile a : automobiliFiltrate) {
					for(Noleggio n:a.getNoleggios()) {
						LocalDate d = n.getDataInizio();
						if(d.isAfter(dI) && d.isBefore(dF)) {
							automobiliFiltrate.remove(n.getAutomobile());
						}
					}
				}
			for(int i= 0; i<automobiliFiltrate.size();i++) {
				
			}
	
		}

	    /*public static void sendMail(String mitt, String dest, String userPsw) throws Exception {
	        int PORT = 587; // inserire porta corretta
	        String BODY = String.join(
	    	    System.getProperty("line.separator"),
	    	    "<h1>Esempio Invio Email</h1>",
	    	    "<p>Queste sono le tue credenziali d'accesso come utente Staff:<br>", 
	    	    "<p>Email: "+dest+"<br>",
	            "<p>Password: "+userPsw+"<br>",
	    	    "<p>Inviato da "+mitt+"."
	    	);

	        // Create a Properties object to contain connection configuration information.
	    	Properties props = System.getProperties();
	    	props.put("mail.transport.protocol", "smtp");
	    	props.put("mail.smtp.port", PORT); 
	    	props.put("mail.smtp.starttls.enable", "true");
	    	props.put("mail.smtp.auth", "true");

	        // Create a Session object to represent a mail session with the specified properties. 
	    	Session session = Session.getDefaultInstance(props);

	        // Create a message with the specified information. 
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom(new InternetAddress(mitt,"Amministratore del sito."));
	        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(dest));
	        //msg.setSubject(SUBJECT);
	        msg.setContent(BODY,"text/html");
	        
	        // Add a configuration set header. Comment or delete the 
	        // next line if you are not using a configuration set
	        //msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
	            
	        // Create a transport.
	        Transport transport = session.getTransport();
	                    
	        // Send the message.
	        try
	        {
	            System.out.println("Sending...");
	            
	            // Connect to Amazon SES using the SMTP username and password you specified above.
	            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
	        	
	            // Send the email.
	            transport.sendMessage(msg, msg.getAllRecipients());
	            System.out.println("Email sent!");
	        }
	        catch (Exception ex) {
	            System.out.println("The email was not sent.");
	            System.out.println("Error message: " + ex.getMessage());
	        }
	        finally
	        {
	            // Close and terminate the connection.
	            transport.close();
	        }
	    }*/

	public static double calcoloPrezzoStimato(LocalDate dataInizio, LocalDate dataFine, Categoria c){
		
		//DAYS.between(dataInizio, dataFine);
		int giorni = (int) dataInizio.until(dataFine, ChronoUnit.DAYS);
		double prezzo = 0;
		while(giorni!=0){
			if(giorni%30==0){
				prezzo += c.getTariffaMensile();
				giorni -= 30;
			}else if(giorni%7==0){
				prezzo += c.getTariffaSettimanale();
				giorni -= 7;
			}else if(giorni%1==0){
				prezzo += c.getTariffaGiornaliera();
				giorni -= 1;
			}
		}

		return prezzo;
	}


}

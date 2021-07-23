package model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the utente database table.
 * 
 */
@Entity
@NamedQuery(name="Utente.findAll", query="SELECT u FROM Utente u")
public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private boolean accettato;

	@Column(name="codice_fiscale")
	private String codiceFiscale;

	private String cognome;

	
	@Column(name="data_di_nascita")
	private LocalDate dataDiNascita;

	@Column(name="data_rilascio_patente")
	private LocalDate dataRilascioPatente;

	@Column(name="data_scadenza_patente")
	private LocalDate dataScadenzaPatente;

	private byte diffide;

	private String email;

	private String nome;

	

	@Column(name="numero_patente")
	private String numeroPatente;

	private String password;



	//bi-directional many-to-one association to Noleggio
	@OneToMany(mappedBy="utente")
	private List<Noleggio> noleggios;

	//bi-directional many-to-one association to Ruolo
	@ManyToOne
	@JoinColumn(name="fk_ruolo")
	private Ruolo ruolo;

	public Utente() {
	}
	public Utente(int id){
		this.id=id;
	}

	public Utente(String password, String email){
		this.password=password;
		this.email=email;
	}

	

	public Utente(boolean accettato, String codiceFiscale, String cognome, LocalDate dataDiNascita, LocalDate dataRilascioPatente,
			LocalDate dataScadenzaPatente, byte diffide, String email, String nome, String numeroPatente, String password, List<Noleggio> noleggios, Ruolo ruolo) {
	
		this.accettato = accettato;
		this.codiceFiscale = codiceFiscale;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.dataRilascioPatente = dataRilascioPatente;
		this.dataScadenzaPatente = dataScadenzaPatente;
		this.diffide = diffide;
		this.email = email;
		this.nome = nome;
		this.numeroPatente = numeroPatente;
		this.password = password;
		
		this.noleggios = noleggios;
		this.ruolo = ruolo;
	}

	public Utente(String codiceFiscale, String cognome, LocalDate dataDiNascita, LocalDate dataRilascioPatente,
			LocalDate dataScadenzaPatente, String email, String nome, String numeroPatente, String password,
			List<Noleggio> noleggios, Ruolo ruolo) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.dataRilascioPatente = dataRilascioPatente;
		this.dataScadenzaPatente = dataScadenzaPatente;
		this.email = email;
		this.nome = nome;
		this.numeroPatente = numeroPatente;
		this.password = password;
		this.noleggios = noleggios;
		this.ruolo = ruolo;
		this.accettato = false;
		this.diffide = 0;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getAccettato() {
		return this.accettato;
	}

	public void setAccettato(boolean accettato) {
		this.accettato = accettato;
	}

	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}



	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataDiNascita() {
		return this.dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public LocalDate getDataRilascioPatente() {
		return this.dataRilascioPatente;
	}

	public void setDataRilascioPatente(LocalDate dataRilascioPatente) {
		this.dataRilascioPatente = dataRilascioPatente;
	}

	public LocalDate getDataScadenzaPatente() {
		return this.dataScadenzaPatente;
	}

	public void setDataScadenzaPatente(LocalDate dataScadenzaPatente) {
		this.dataScadenzaPatente = dataScadenzaPatente;
	}

	public byte getDiffide() {
		return this.diffide;
	}

	public void setDiffide(byte diffide) {
		this.diffide = diffide;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroPatente() {
		return this.numeroPatente;
	}

	public void setNumeroPatente(String numeroPatente) {
		this.numeroPatente = numeroPatente;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Noleggio> getNoleggios() {
		return this.noleggios;
	}

	public void setNoleggios(List<Noleggio> noleggios) {
		this.noleggios = noleggios;
	}

	public Noleggio addNoleggio(Noleggio noleggio) {
		getNoleggios().add(noleggio);
		noleggio.setUtente(this);

		return noleggio;
	}

	public Noleggio removeNoleggio(Noleggio noleggio) {
		getNoleggios().remove(noleggio);
		noleggio.setUtente(null);

		return noleggio;
	}

	public Ruolo getRuolo() {
		return this.ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
}
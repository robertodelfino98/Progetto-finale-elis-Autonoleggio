package model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;




/**
 * The persistent class for the noleggio database table.
 * 
 */
@Entity
@NamedQuery(name="Noleggio.findAll", query="SELECT n FROM Noleggio n")
public class Noleggio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="costo_totale")
	private double costoTotale;

	@Column(name="data_consegna", columnDefinition="DATE")
	private LocalDate dataConsegna;

	@Column(name="data_fine", columnDefinition="DATE")
	private LocalDate dataFine;

	@Column(name="data_inizio", columnDefinition="DATE")
	private LocalDate dataInizio;

	private boolean avviato;

	private boolean terminato;

	//bi-directional many-to-one association to Utente
	@ManyToOne
	@JoinColumn(name="fk_utente")
	private Utente utente;

	//bi-directional many-to-one association to Automobile
	@ManyToOne
	@JoinColumn(name="fk_automobile")
	private Automobile automobile;

	public Noleggio() {
	}
	
	public Noleggio( LocalDate dataFine, LocalDate dataInizio, Utente utente, Automobile automobile,double prezzo) {
		this.dataFine = dataFine;
		this.dataInizio = dataInizio;
		this.avviato = false;
		this.terminato = false;
		this.utente = utente;
		this.automobile = automobile;
		this.costoTotale = prezzo;
	}

	public Noleggio(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCostoTotale() {
		return this.costoTotale;
	}

	public void setCostoTotale(double costoTotale) {
		this.costoTotale = costoTotale;
	}

	public LocalDate getDataConsegna() {
		return this.dataConsegna;
	}

	public void setDataConsegna(LocalDate dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public LocalDate getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public LocalDate getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Automobile getAutomobile() {
		return this.automobile;
	}

	public void setAutomobile(Automobile automobile) {
		this.automobile = automobile;
	}

	public boolean isAvviato(){
		return this.avviato;
	}
	
	public void setAvviato(boolean scelta){
		this.avviato = scelta;
	}

	public boolean isTerminato(){
		return this.terminato;
	}

	public void setTerminato(boolean scelta){
		this.terminato = scelta;
	}

}
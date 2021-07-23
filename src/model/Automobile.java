package model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the automobile database table.
 * 
 */
@Entity
@NamedQuery(name="Automobile.findAll", query="SELECT a FROM Automobile a")
public class Automobile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double chilometri;

	private String colore;

	@Column(name="data_immatricolazione", columnDefinition="DATE")
	private LocalDate dataImmatricolazione;

	private boolean disponibilita;

	private String marca;

	private String modello;

	private boolean neopatentati;

	private String targa;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="fk_categoria")
	private Categoria categoria;

	//bi-directional many-to-one association to Noleggio
	@OneToMany(mappedBy="automobile")
	private List<Noleggio> noleggios;

	public Automobile() {
	}

	public Automobile(String marca, Categoria categoria) {
		this.marca=marca;
		this.categoria=categoria;
	}

	public Automobile(Categoria categoria){
		this.categoria=categoria;
	}
	public Automobile(Categoria categoria, String marca, String modello, String colore, double chilometri, String targa, LocalDate dataImmatricolazione){
		this.categoria=categoria;
		this.marca=marca;
		this.modello=modello;
		this.colore=colore;
		this.chilometri=chilometri;
		this.targa=targa;
		this.dataImmatricolazione=dataImmatricolazione;

	}

	public Automobile(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getChilometri() {
		return this.chilometri;
	}

	public void setChilometri(double chilometri) {
		this.chilometri = chilometri;
	}

	public String getColore() {
		return this.colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public LocalDate getDataImmatricolazione() {
		return this.dataImmatricolazione;
	}

	public void setDataImmatricolazione(LocalDate dataImmatricolazione) {
		this.dataImmatricolazione = dataImmatricolazione;
	}

	public boolean getDisponibilita() {
		return this.disponibilita;
	}

	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return this.modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public boolean getNeopatentati() {
		return this.neopatentati;
	}

	public void setNeopatentati(boolean neopatentati) {
		this.neopatentati = neopatentati;
	}

	public String getTarga() {
		return this.targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Noleggio> getNoleggios() {
		return this.noleggios;
	}

	public void setNoleggios(List<Noleggio> noleggios) {
		this.noleggios = noleggios;
	}

	public Noleggio addNoleggio(Noleggio noleggio) {
		getNoleggios().add(noleggio);
		noleggio.setAutomobile(this);

		return noleggio;
	}

	public Noleggio removeNoleggio(Noleggio noleggio) {
		getNoleggios().remove(noleggio);
		noleggio.setAutomobile(null);

		return noleggio;
	}

}
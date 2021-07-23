package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="nome_categoria")
	private String nomeCategoria;

	@Column(name="tariffa_giornaliera")
	private double tariffaGiornaliera;

	@Column(name="tariffa_mensile")
	private double tariffaMensile;

	@Column(name="tariffa_settimanale")
	private double tariffaSettimanale;

	//bi-directional many-to-one association to Automobile
	@OneToMany(mappedBy="categoria")
	private List<Automobile> automobiles;

	public Categoria() {
	}

	public Categoria(int id) {
		this.id=id;
	}

	public Categoria(String nome, double prezzoGiornaliero, double prezzoSettimanale, double prezzoMensile){
		this.nomeCategoria = nome;
		this.tariffaGiornaliera=prezzoGiornaliero;
		this.tariffaSettimanale=prezzoSettimanale;
		this.tariffaMensile = prezzoMensile;
	}


	public Categoria(double prezzoGiornaliero, double prezzoSettimanale, double prezzoMensile){
		
		this.tariffaGiornaliera=prezzoGiornaliero;
		this.tariffaSettimanale=prezzoSettimanale;
		this.tariffaMensile = prezzoMensile;
	}

	public Categoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCategoria() {
		return this.nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public double getTariffaGiornaliera() {
		return this.tariffaGiornaliera;
	}

	public void setTariffaGiornaliera(double tariffaGiornaliera) {
		this.tariffaGiornaliera = tariffaGiornaliera;
	}

	public double getTariffaMensile() {
		return this.tariffaMensile;
	}

	public void setTariffaMensile(double tariffaMensile) {
		this.tariffaMensile = tariffaMensile;
	}

	public double getTariffaSettimanale() {
		return this.tariffaSettimanale;
	}

	public void setTariffaSettimanale(double tariffaSettimanale) {
		this.tariffaSettimanale = tariffaSettimanale;
	}

	public List<Automobile> getAutomobiles() {
		return this.automobiles;
	}

	public void setAutomobiles(List<Automobile> automobiles) {
		this.automobiles = automobiles;
	}

	public Automobile addAutomobile(Automobile automobile) {
		getAutomobiles().add(automobile);
		automobile.setCategoria(this);

		return automobile;
	}

	public Automobile removeAutomobile(Automobile automobile) {
		getAutomobiles().remove(automobile);
		automobile.setCategoria(null);

		return automobile;
	}

}
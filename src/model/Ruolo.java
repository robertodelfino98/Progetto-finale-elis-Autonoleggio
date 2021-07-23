package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the ruolo database table.
 * 
 */
@Entity
@NamedQuery(name="Ruolo.findAll", query="SELECT r FROM Ruolo r")
public class Ruolo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;



	@Column(name="nome_ruolo")
	private String nomeRuolo;

	//bi-directional many-to-one association to Utente
	@OneToMany(mappedBy="ruolo")
	private List<Utente> utentes;

	public Ruolo() {
	}

	public Ruolo(String nome) {
		this.nomeRuolo=nome;
	}

	public Ruolo(int id, String nome){
		this.id=id;
		this.nomeRuolo=nome;
	}

	public Ruolo(int id){
		this.id=id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getNomeRuolo() {
		return this.nomeRuolo;
	}

	public void setNomeRuolo(String nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}

	public List<Utente> getUtentes() {
		return this.utentes;
	}

	public void setUtentes(List<Utente> utentes) {
		this.utentes = utentes;
	}

	public Utente addUtente(Utente utente) {
		getUtentes().add(utente);
		utente.setRuolo(this);

		return utente;
	}

	public Utente removeUtente(Utente utente) {
		getUtentes().remove(utente);
		utente.setRuolo(null);

		return utente;
	}

}
package model;

import java.sql.Date;

public class Karta {
	
	private int id;
	private Projekcija projekcija;
	private Sediste sediste;
	private Date datum;
	private Date vreme;
	private Korisnik korisnik;
	public Karta(int id, Projekcija projekcija, Sediste sediste, Date datum, Date vreme, Korisnik korisnik) {
		super();
		this.id = id;
		this.projekcija = projekcija;
		this.sediste = sediste;
		this.datum = datum;
		this.vreme = vreme;
		this.korisnik = korisnik;
	}
	public int getId() {
		return id;
	}
	public Projekcija getProjekcija() {
		return projekcija;
	}
	public void setProjekcija(Projekcija projekcija) {
		this.projekcija = projekcija;
	}
	public Sediste getSediste() {
		return sediste;
	}
	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public Date getVreme() {
		return vreme;
	}
	public void setVreme(Date vreme) {
		this.vreme = vreme;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	

}

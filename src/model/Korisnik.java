package model;

import java.sql.Date;

public class Korisnik {
	
	public enum Uloga{KORISNIK, ADMIN}
	
	private String korisnickoIme;
	private String lozinka;
	private Date datumRegistracije;
	private Uloga uloga;
	public Korisnik(String korisnickoIme, String lozinka, Date datumRegistracije, Uloga uloga) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.datumRegistracije = datumRegistracije;
		this.uloga = uloga.KORISNIK;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public Date getDatumRegistracije() {
		return datumRegistracije;
	}
	public void setDatumRegistracije(Date datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}
	public Uloga getUloga() {
		return uloga;
	}
	public void setTip(Uloga uloga) {
		this.uloga = uloga;
	}
	
	
	

}

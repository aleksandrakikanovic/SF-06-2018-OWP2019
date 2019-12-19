package model;

import java.sql.Date;

public class Korisnik {
	
	public enum ETipKorisnika{KORISNIK, ADMIN}
	
	private String korisnickoIme;
	private String lozinka;
	private Date datumRegistracije;
	private ETipKorisnika tip;
	public Korisnik(String korisnickoIme, String lozinka, Date datumRegistracije, ETipKorisnika tip) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.datumRegistracije = datumRegistracije;
		this.tip = tip.KORISNIK;
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
	public ETipKorisnika getTip() {
		return tip;
	}
	public void setTip(ETipKorisnika tip) {
		this.tip = tip;
	}
	
	
	

}

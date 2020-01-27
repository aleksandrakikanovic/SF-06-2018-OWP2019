package model;

import java.sql.Date;

import org.apache.tomcat.jni.Time;

public class Projekcija {
	
	public enum ETipProjekcije{	dvaD, triD, cetiriD}
		
	private int id;
	private Film film;
	private ETipProjekcije tipProjekcije;
	private Sala sala;
	private Date datum;
	private String vreme;
	private double cenaKarte;
	private Korisnik admin;
	
	public Projekcija(int id, Film film, ETipProjekcije tipProjekcije, Sala sala, Date datum, String vreme,
			double cenaKarte, Korisnik admin) {
		super();
		this.id = id;
		this.film = film;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.datum = datum;
		this.vreme = vreme;
		this.cenaKarte = cenaKarte;
		this.admin = admin;
	}
	public Projekcija( Film film, ETipProjekcije tipProjekcije, Sala sala, Date datum, String vreme,
			double cenaKarte, Korisnik admin) {
		super();
		this.film = film;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.datum = datum;
		this.vreme = vreme;
		this.cenaKarte = cenaKarte;
		this.admin = admin;
	}



	public int getId() {
		return id;
	}
	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public ETipProjekcije getTipProjekcije() {
		return tipProjekcije;
	}

	public void setTipProjekcije(ETipProjekcije tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public String getVreme() {
		return vreme;
	}
	public void setVreme(String vreme) {
		this.vreme = vreme;
	}
	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public Korisnik getAdmin() {
		return admin;
	}

	public void setAdmin(Korisnik admin) {
		this.admin = admin;
	}
	
	
	
	
		
	
	
	

}

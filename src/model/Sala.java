package model;
import model.Projekcija.ETipProjekcije;

public class Sala {
	
	private int id;
	private String naziv;
	private ETipProjekcije tipProjekcije;
	private String ostaleProjekcije;
	public Sala(int id, String naziv, ETipProjekcije tipProjekcije, String ostaleProjekcije) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tipProjekcije = tipProjekcije;
		this.ostaleProjekcije=ostaleProjekcije;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public ETipProjekcije getTipProjekcije() {
		return tipProjekcije;
	}
	public void setTipProjekcije(ETipProjekcije tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}
	public String getOstaleProjekcije() {
		return ostaleProjekcije;
	}
	public void setOstaleProjekcije(String ostaleProjekcije) {
		this.ostaleProjekcije = ostaleProjekcije;
	}
	

}

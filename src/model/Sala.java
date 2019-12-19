package model;

import java.util.ArrayList;

import model.Projekcija.ETipProjekcije;

public class Sala {
	
	private int id;
	private String naziv;
	private ArrayList<ETipProjekcije> listaProjekcija;
	public Sala(int id, String naziv, ArrayList<ETipProjekcije> listaProjekcija) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.listaProjekcija = listaProjekcija;
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
	public ArrayList<ETipProjekcije> getListaProjekcija() {
		return listaProjekcija;
	}
	public void setListaProjekcija(ArrayList<ETipProjekcije> listaProjekcija) {
		this.listaProjekcija = listaProjekcija;
	}
	
	

}

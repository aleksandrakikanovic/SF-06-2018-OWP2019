package model;

public class Sediste {
	
	private int id;
	private int redniBroj;
	private Sala sala;
	public Sediste(int id, int redniBroj, Sala sala) {
		super();
		this.id=id;
		this.redniBroj = redniBroj;
		this.sala = sala;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRedniBroj() {
		return redniBroj;
	}
	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	

}

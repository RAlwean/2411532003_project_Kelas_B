package model;

public class Customer {
	private String id;
	private String nama;
	private String email;
	private String alamat;
	private String nomorHp;

	public Customer(String id, String nama, String alamat, String nomorHp, String email) {
		super();
		this.id = id;
		this.nama = nama;
		this.alamat = alamat;
		this.nomorHp = nomorHp;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public String getNomorHp() {
		return nomorHp;
	}

	public String getEmail() {
		return email;
	}	
}
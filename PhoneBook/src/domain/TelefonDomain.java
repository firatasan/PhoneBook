package domain;

public class TelefonDomain {

	private int id;
	private String adi;
	private String soyadi;
	private String telefon;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}
	public String getSoyadi() {
		return soyadi;
	}
	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	
	public String toString() {
		
		
		return id + " - " + adi + "   " + soyadi + "  " + telefon;
	}
}

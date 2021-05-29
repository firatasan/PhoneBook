package database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.TelefonDomain;

public class DerbyJdbcBaglantisiDb {

	// jdbc:derby:C:\Users\firat\MyDB;create=true

	// private final String yol = "jdbc:derby:d:/telefonRehberiDb1;create=true";

	private final String yol = "jdbc:derby:D:\\Users\\firat\\MyDB;create=true";
	private final String kullaniciAdi = "";
	private final String sifre = "";

	static {

		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public Connection getConnection() {

		Connection baglanti = null;

		try {
			baglanti = DriverManager.getConnection(yol, kullaniciAdi, sifre);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return baglanti;
	}

	public DerbyJdbcBaglantisiDb() {
		initTablo();
	}

	public void initTablo() {

		Connection baglanti = getConnection();

		try {
			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate(
					"CREATE TABLE kisiler (id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
							+ "adi VARCHAR(50)," + "soyadi VARCHAR(50)," + "telefonu VARCHAR(50))");

			// NOT Burada sorgu ve baglantiyi kapatmassak bir süre sonra yapýsal hatlara
			// neden olabilir:
			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {

			System.out.println("Database baðlantýsý baþarýlý");

		}

	}

	public void yeniKayit(TelefonDomain yeniKayit) {

		Connection baglanti = getConnection();
		try {

			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate("INSERT INTO kisiler(adi, soyadi, telefonu) VALUES ('" + yeniKayit.getAdi() + "','"
					+ yeniKayit.getSoyadi() + "','" + yeniKayit.getTelefon() + "')");

			// NOT Burada sorgu ve baglantiyi kapatmassak bir süre sonra yapýsal hatlara
			// neden olabilir:
			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public List<TelefonDomain> listele() {

		List<TelefonDomain> list = new ArrayList<TelefonDomain>();
		Connection baglanti = getConnection();

		try {
			// Burada java sql olanlarý secmeyi unutma!!! rs ve statement
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM kisiler");

			while (rs.next()) {

				TelefonDomain yeniListe = new TelefonDomain();

				yeniListe.setId(rs.getInt("id"));
				yeniListe.setAdi(rs.getString("adi"));
				yeniListe.setSoyadi(rs.getString("soyadi"));
				yeniListe.setTelefon(rs.getString("telefonu"));

				list.add(yeniListe);
			}

			// NOT Burada sorgu ve baglantiyi kapatmassak bir süre sonra yapýsal hatlara
			// neden olabilir:
			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public TelefonDomain bul(int id) {

		TelefonDomain bulunacakKisi = new TelefonDomain();
		Connection baglanti = getConnection();

		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM kisiler WHERE id = " + id + "");

			while (rs.next()) {

				bulunacakKisi.setId(rs.getInt("id"));
				bulunacakKisi.setAdi(rs.getString("adi"));
				bulunacakKisi.setSoyadi(rs.getString("soyadi"));
				bulunacakKisi.setTelefon(rs.getString("telefonu"));

			}

			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bulunacakKisi;

	}

	public void sil(TelefonDomain sil) {

		Connection baglanti = getConnection();
		try {

			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate("DELETE FROM kisiler WHERE id = " + sil.getId() + "");

			// NOT Burada sorgu ve baglantiyi kapatmassak bir süre sonra yapýsal hatlara
			// neden olabilir:
			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void duzenle(TelefonDomain duzenlenecekKisi) {

		Connection baglanti = getConnection();
		try {

			Statement sorgu = baglanti.createStatement();

			sorgu.executeUpdate("UPDATE kisiler SET adi ='" + duzenlenecekKisi.getAdi() + "', soyadi='"
					+ duzenlenecekKisi.getSoyadi() + "', telefonu='" + duzenlenecekKisi.getTelefon() + "' WHERE id ="
					+ duzenlenecekKisi.getId() + "");

			// NOT Burada sorgu ve baglantiyi kapatmassak bir süre sonra yapýsal hatlara
			// neden olabilir:
			sorgu.close();
			baglanti.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}

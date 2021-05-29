package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import database.DerbyJdbcBaglantisiDb;
import domain.TelefonDomain;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import test.initCagir;

public class AnaPencereGui extends JFrame implements initCagir{

	DerbyJdbcBaglantisiDb baglanti = new DerbyJdbcBaglantisiDb();
	
	
	
	
	public AnaPencereGui() {
		
		initPencere();
	}


	public void initPencere() {

		JPanel panel = initPanel();
		JMenuBar bar = initBar();
		
		add(panel);
		setJMenuBar(bar);
		
		// Burada penceremizin baþlýk, size, ortada açýlma, x e basýnca kapanma tanýttýk.
		setTitle("Telefon Rehberi v1.0");
		setSize(350, 500);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}


	public JPanel initPanel() {

		JPanel panel = new JPanel(new BorderLayout());
		
		// Gridlayout 4 satir 2 þer boþluk
		JPanel bulPanel = new JPanel(new GridLayout(4,2,2,2));
		JPanel buttonPanel= new JPanel(new GridLayout(1,1));
		
		
		JLabel idJLabel = new JLabel("id", JLabel.RIGHT);
		bulPanel.add(idJLabel);
		final JTextField idField = new JTextField(25);
		bulPanel.add(idField);
		JLabel adiJLabel = new JLabel("Adý : ", JLabel.RIGHT);
		bulPanel.add(adiJLabel);
		final JTextField adiField = new JTextField(15);
		bulPanel.add(adiField);
		JLabel soyadiJLabel = new JLabel("Soyadý : ", JLabel.RIGHT);
		bulPanel.add(soyadiJLabel);
		final JTextField soyadiField = new JTextField(15);
		bulPanel.add(soyadiField);
		JLabel telefonJLabel = new JLabel("Telefonu : ", JLabel.RIGHT);
		bulPanel.add(telefonJLabel);
		final JTextField telefonField = new JTextField(15);
		bulPanel.add(telefonField);
		
		JButton bulButton = new JButton("Bul");
		buttonPanel.add(bulButton);
		bulButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {

				TelefonDomain bulunacakKisi = baglanti.bul(Integer.parseInt(idField.getText()));
				
				adiField.setText(bulunacakKisi.getAdi());
				soyadiField.setText(bulunacakKisi.getSoyadi());
				telefonField.setText(bulunacakKisi.getTelefon());
				
				idField.setBackground(Color.GRAY);
				idField.setEditable(false);
				idField.setToolTipText("ID'yi düzenleyemezsiniz");
			}
		});
		
		
		JButton silButton = new JButton("Sil");
		buttonPanel.add(silButton);
		JButton duzenleButton = new JButton("Düzenle");
		buttonPanel.add(duzenleButton);
		JList kisilerList = new JList();
		JScrollPane pane = new JScrollPane(kisilerList);
		
		kisilerList.setListData(baglanti.listele().toArray());
		silButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {

				TelefonDomain silinecekKisi = new TelefonDomain();
				silinecekKisi = (TelefonDomain) kisilerList.getSelectedValue();
				
				baglanti.sil(silinecekKisi);
				
				kisilerList.setListData(baglanti.listele().toArray());
			}
		});
		
		duzenleButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {

				TelefonDomain duzenlenecekKisi = new TelefonDomain();
				
				duzenlenecekKisi.setId(Integer.parseInt(idField.getText()));
				
				duzenlenecekKisi.setAdi(adiField.getText());
				duzenlenecekKisi.setSoyadi(soyadiField.getText());
				duzenlenecekKisi.setTelefon(telefonField.getText());
				
				baglanti.duzenle(duzenlenecekKisi);
				
				kisilerList.setListData(baglanti.listele().toArray());
			}
		});
		
		panel.add(pane, BorderLayout.CENTER);
		panel.add(bulPanel, BorderLayout.NORTH);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		
		return panel;
	}

	
	public JMenuBar initBar() {

		JMenuBar bar = new JMenuBar();
		JMenu dosyaJMenu = new JMenu("Dosya");
		bar.add(dosyaJMenu);
		JMenu raporJMenu = new JMenu("Rapor");
		bar.add(raporJMenu);
		JMenuItem kayitYapItem = new JMenuItem("Kayýt Yap");
		dosyaJMenu.add(kayitYapItem);
		
		JMenuItem raporItem = new JMenuItem("Yazdýr");
		raporJMenu.add(raporItem);
		raporItem.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {

				DerbyJdbcBaglantisiDb raporDb = new DerbyJdbcBaglantisiDb();
				Connection baglanti = raporDb.getConnection();
				
				// BURALAR EKSÝK KALDI........
				try {
					JasperPrint print = JasperFillManager.fillReport("Coffee.jasper", null, baglanti);
					JasperViewer.viewReport(print,false);
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		
		kayitYapItem.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				new kayitYapGui();
			}
		});
		dosyaJMenu.add(kayitYapItem);
		
		return bar;
	}

}

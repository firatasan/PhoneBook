package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.DerbyJdbcBaglantisiDb;
import domain.TelefonDomain;
import test.initCagir;

public class kayitYapGui extends JDialog implements initCagir {

	
	DerbyJdbcBaglantisiDb baglanti = new DerbyJdbcBaglantisiDb();
	TelefonDomain yeniKayit= new TelefonDomain();
	
	public kayitYapGui() {
		
		initPencere();
	}


	public void initPencere() {

		JPanel panel = initPanel();
		
		add(panel);
		setTitle("Kayit Yap");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		
	}


	public JPanel initPanel() {

		JPanel panel = new JPanel(new GridLayout(4,2));
		
		JLabel adiJLabel = new JLabel("Adý : " , JLabel.RIGHT);
		panel.add(adiJLabel);
		final JTextField adiField = new JTextField(15);
		panel.add(adiField);
		JLabel soyadiJLabel = new JLabel("Soyadi : " , JLabel.RIGHT);
		panel.add(soyadiJLabel);
		final JTextField soyadiField = new JTextField(15);
		panel.add(soyadiField);
		JLabel telJLabel = new JLabel("Telefonu : " , JLabel.RIGHT);
		panel.add(telJLabel);
		final JTextField telField = new JTextField(15);
		panel.add(telField);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		
		kaydetButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {

				yeniKayit.setAdi(adiField.getText());
				yeniKayit.setSoyadi(soyadiField.getText());
				yeniKayit.setTelefon(telField.getText());
				
				
				baglanti.yeniKayit(yeniKayit);
				
				
				JOptionPane.showMessageDialog(null, "Kayit Baþarýlý");
				
			}
		});
		
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);
		
		
		return panel;
	}


	@Override
	public JMenuBar initBar() {

		return null;
	}


}

package modellierung;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.ShutdownChannelGroupException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame implements Beobachter {

	private JPanel contentPane;
	private JPanel oben;
	private JLabel lblGemietetesAuto;
	private JTextField tFgefahreneKM;
	private Autovermietung av;
	private Kunde aktiverKunde;
	private JComboBox<String> cbMietbareAutos = new JComboBox<>();
	private JComboBox<String> cbKunden = new JComboBox<>();
	private JLabel lblAuto;
	private JLabel lblGefahreneKilometer;
	private JButton btnAutoAbgeben;
	private JPanel mitte;
	private JButton btnAutoMieten;
	private JScrollPane scrollPane;
	private JPanel unten;
	private JLabel lblOffeneBetraege;

	/**
	 * Create the frame.
	 */
	public GUI(Autovermietung av) {
		this.av = av;
		av.addObserver(this);
		aktiverKunde = av.getKunden()[0];

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1054, 639);

		autoComboBoxEinrichten();
		kundenComboBoxEinrichten();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnKunde = new JMenu("Kunde");
		menuBar.add(mnKunde);

		JMenuItem mntmKundeWaehlen = new JMenuItem("Kunde wählen");
		mntmKundeWaehlen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dia = new JDialog();
				dia.setSize(400, 400);

				JPanel namePanel = new JPanel();
				namePanel.setVisible(true);
				namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));

				JPanel buttonPanel = new JPanel();
				buttonPanel.setVisible(true);
				buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

				JButton zuAnderenKunde = new JButton("zum gewaehlten Kunden wechseln");
				zuAnderenKunde.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int i = 0; i < av.getKunden().length; i++) {
							if (av.getKunden()[i].getName().equals(cbKunden.getSelectedItem())) {
								aktiverKunde = av.getKunden()[i];
								aktualisiereAlles();
							}
						}
					}
				});

				buttonPanel.add(zuAnderenKunde);

				JSplitPane choose = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, cbKunden, buttonPanel);
				choose.setVisible(true);

				dia.getContentPane().add(choose);
				dia.setVisible(true);
			}
		});
		mnKunde.add(mntmKundeWaehlen);

		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mnKunde.add(mntmBeenden);

		JMenuItem mntmInfo = new JMenuItem("Info");
		mntmInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Erstellt von Julian Joachim - s0574380");
			}
		});
		menuBar.add(mntmInfo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		btnAutoMieten = new JButton("mieten");
		btnAutoMieten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (aktiverKunde.isHasCar() == false) {
					for (int i = 0; i < av.getAutos().length; i++) {
						if (cbMietbareAutos.getSelectedItem().equals(av.getAutos()[i].getKennzeichen())) {
							aktiverKunde.setCar(av.getAutos()[i]);
							av.getAutos()[i].setIstVermietet(true);
							aktualisiereAlles();
						}
					}
				}
			}
		});

		oben = new JPanel();
		contentPane.add(oben);

		lblGemietetesAuto = new JLabel("gemietetes Auto:");
		oben.add(lblGemietetesAuto);

		lblAuto = new JLabel(aktiverKunde.getCar().getKennzeichen());
		oben.add(lblAuto);

		lblGefahreneKilometer = new JLabel("gefahrene Kilometer:");
		oben.add(lblGefahreneKilometer);

		tFgefahreneKM = new JTextField();
		oben.add(tFgefahreneKM);
		tFgefahreneKM.setColumns(10);

		btnAutoAbgeben = new JButton("abgeben");
		if (aktiverKunde.isHasCar() == false) {
			btnAutoAbgeben.setEnabled(false);
		} else {
			btnAutoAbgeben.setEnabled(true);
		}
		btnAutoAbgeben.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double km = 0.0;

				if (tFgefahreneKM.getText().isEmpty() == false) {
					try {
						km = Double.parseDouble(tFgefahreneKM.getText());
						Auto tempCar = aktiverKunde.getCar();
						aktiverKunde.getCar().setIstVermietet(false);
						aktiverKunde.getCar().setKmStand(aktiverKunde.getCar().getKmStand() + km);
						aktiverKunde.removeCar();
						av.erstelleRechnung(aktiverKunde.getKundenNr(), km, km * tempCar.getPreisProKM(), false);
					} catch (Exception e) {
						tFgefahreneKM.setText("Ungueltige Eingabe.");
					}
				}

			}
		});
		oben.add(btnAutoAbgeben);

		mitte = new JPanel();
		contentPane.add(mitte);

		mitte.add(cbMietbareAutos);

		mitte.add(btnAutoMieten);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		unten = new JPanel();
		scrollPane.setViewportView(unten);

		aktualisiereAlles();

		lblOffeneBetraege = new JLabel("Offene Rechnungsbetraege: " + aktiverKunde.getTotalBetrag() + " €");
		contentPane.add(lblOffeneBetraege);
	}

	private void autoComboBoxEinrichten() {
		DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel<>();

		dcbm.addElement("Wähle Auto hier");
		for (int i = 0; i < av.autosLaenge(); i++) {
			if (av.getAutos()[i].isIstVermietet() == false) {
				dcbm.addElement(av.getAutos()[i].getKennzeichen());
			}
		}
		cbMietbareAutos.setModel(dcbm);
	}

	private void kundenComboBoxEinrichten() {
		DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel<>();
		for (int i = 0; i < av.getKunden().length; i++) {
			dcbm.addElement(av.getKunden()[i].getName());
		}
		cbKunden.setModel(dcbm);
	}

	@Override
	public void update(Rechnung r, Kunde k, String who) {
		if (who.equals("GUI")) {
			aktualisiereAlles();
			lblOffeneBetraege.setText("Offene Rechnungsbeträge: " + Double.toString(aktiverKunde.getTotalBetrag()));
		}
	}

	public void aktualisiereAlles() {
		aktualisiereDaten();
		aktualisiereUnten();
	}

	public void aktualisiereDaten() {
		autoComboBoxEinrichten();
		this.setTitle(aktiverKunde.getName());

		lblAuto.setText(aktiverKunde.getCar().getKennzeichen());

		if (aktiverKunde.isHasCar() == true) {
			btnAutoMieten.setEnabled(false);
		} else {
			btnAutoMieten.setEnabled(true);
		}

		if (aktiverKunde.isHasCar() == true) {
			btnAutoAbgeben.setEnabled(true);
		} else {
			btnAutoAbgeben.setEnabled(false);
		}

	}

	public void aktualisiereUnten() {

		unten.removeAll();
		List<JPanel> panelList = new ArrayList<>();
		List<JLabel> labelListNummer = new ArrayList<>();
		List<JLabel> labelListBetrag = new ArrayList<>();
		List<JButton> buttonList = new ArrayList<>();
		for (int i = 0; i < aktiverKunde.getRechnung().length; i++) {
			panelList.add(new JPanel());
			unten.add(panelList.get(i));
			panelList.get(i).setBackground(new Color(240, 240, 240));
			panelList.get(i).setLayout(new BoxLayout(panelList.get(i), BoxLayout.Y_AXIS));

			labelListNummer.add(new JLabel("Rechnungsnummer: " + aktiverKunde.getRechnung()[i].getRechnungsnummer()));
			panelList.get(i).add(labelListNummer.get(i));

			labelListBetrag.add(new JLabel("Rechnungsbetrag: " + aktiverKunde.getRechnung()[i].getRechnungsbetrag()));
			panelList.get(i).add(labelListBetrag.get(i));

			buttonList.add(new JButton("begleichen"));
			if (aktiverKunde.getRechnung()[i].isBezahlt() == true) {
				buttonList.get(i).setEnabled(false);
			}


			buttonList.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					for (int j = 0; j < buttonList.size(); j++) {

						if (arg0.getSource().hashCode() == (buttonList.get(j).hashCode())) {
							av.zahleRechnung(aktiverKunde,
									av.getRechnung(aktiverKunde.getRechnung()[j].getRechnungsnummer()));
						}
					}
				}
			});

			panelList.get(i).add(buttonList.get(i));
		}
	}

}

package fr.eni.papeterie.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class EcranArticle extends JFrame {
	private static final long serialVersionUID = 1479850413930297379L;
	
	private JTextField txtReference, txtDesignation, txtMarque, txtStock, txtPrix;
	private JLabel lblReference, lblDesignation, lblMarque, lblStock, lblPrix, lblType, lblGrammage, lblCouleur;
	private JPanel panType, panGrammage;
	private JRadioButton rdbTypeRamette, rdbTypeStylo;
	private JCheckBox ckbGrammage80, ckbGrammage100;
	private JList<?> listCouleur;

	public EcranArticle() {
		this.setTitle("Article");
		this.setSize(new Dimension(400, 500));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initIHM();
	}
	
	private void initIHM() {
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridy = 0;
		gbc.gridx = 0;
		panel.add(getLblReference(), gbc);
		gbc.gridx = 1;
		panel.add(getTxtReference(), gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 0;
		panel.add(getLblDesignation(), gbc);
		gbc.gridx = 1;
		panel.add(getTxtDesignation(), gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 0;
		panel.add(getLblMarque(), gbc);
		gbc.gridx = 1;
		panel.add(getTxtMarque(), gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 0;
		panel.add(getLblStock(), gbc);
		gbc.gridx = 1;
		panel.add(getTxtStock(), gbc);
		
		gbc.gridy = 4;
		gbc.gridx = 0;
		panel.add(getLblPrix(), gbc);
		gbc.gridx = 1;
		panel.add(getTxtPrix(), gbc);
		
		gbc.gridy = 5;
		gbc.gridx = 0;
		panel.add(getLblType(), gbc);
		gbc.gridx = 1;
		panel.add(getPanType(), gbc);
		
		gbc.gridy = 6;
		gbc.gridx = 0;
		panel.add(getLblGrammage(), gbc);
		gbc.gridx = 1;
		panel.add(getPanGrammage(), gbc);
		
		gbc.gridy = 7;
		gbc.gridx = 0;
		panel.add(getLblCouleur(), gbc);
		gbc.gridx = 1;
		//panel.add();
		
		// Affecte le panel à l'écran
		this.setContentPane(panel);
	}

	/**
	 * @return the txtReference
	 */
	public JTextField getTxtReference() {
		if(txtReference == null) {
			txtReference = new JTextField(30);
		}
		return txtReference;
	}

	/**
	 * @return the txtDesignation
	 */
	public JTextField getTxtDesignation() {
		if(txtDesignation == null) {
			txtDesignation = new JTextField(30);
		}
		return txtDesignation;
	}

	/**
	 * @return the txtMarque
	 */
	public JTextField getTxtMarque() {
		if(txtMarque == null) {
			txtMarque = new JTextField(30);
		}
		return txtMarque;
	}

	/**
	 * @return the txtStock
	 */
	public JTextField getTxtStock() {
		if(txtStock == null) {
			txtStock = new JTextField(30);
		}
		return txtStock;
	}

	/**
	 * @return the txtPrix
	 */
	public JTextField getTxtPrix() {
		if(txtPrix == null) {
			txtPrix = new JTextField(30);
		}
		return txtPrix;
	}

	/**
	 * @return the lblReference
	 */
	public JLabel getLblReference() {
		if(lblReference == null) {
			lblReference = new JLabel("Référence ");
		}
		return lblReference;
	}

	/**
	 * @return the lblDesignation
	 */
	public JLabel getLblDesignation() {
		if(lblDesignation == null) {
			lblDesignation = new JLabel("Désignation ");
		}
		return lblDesignation;
	}

	/**
	 * @return the lblMarque
	 */
	public JLabel getLblMarque() {
		if(lblMarque == null) {
			lblMarque = new JLabel("Marque ");
		}
		return lblMarque;
	}

	/**
	 * @return the lblStock
	 */
	public JLabel getLblStock() {
		if(lblStock == null) {
			lblStock = new JLabel("Stock ");
		}
		return lblStock;
	}

	/**
	 * @return the lblPrix
	 */
	public JLabel getLblPrix() {
		if(lblPrix == null) {
			lblPrix = new JLabel("Prix ");
		}
		return lblPrix;
	}

	/**
	 * @return the lblType
	 */
	public JLabel getLblType() {
		if(lblType == null) {
			lblType = new JLabel("Type ");
		}
		return lblType;
	}

	/**
	 * @return the lblGrammage
	 */
	public JLabel getLblGrammage() {
		if(lblGrammage == null) {
			lblGrammage = new JLabel("Grammage ");
		}
		return lblGrammage;
	}

	/**
	 * @return the lblCouleur
	 */
	public JLabel getLblCouleur() {
		if(lblCouleur == null) {
			lblCouleur = new JLabel("Couleur ");
		}
		return lblCouleur;
	}
	
	/**
	 * @return the panType
	 */
	public JPanel getPanType() {
		if(panType == null) {
			panType = new JPanel();
			panType.add(getRdbTypeRamette());
			panType.add(getRdbTypeStylo());
		}
		return panType;
	}
	
	/**
	 * @return the rdbTypeRamette
	 */
	public JRadioButton getRdbTypeRamette() {
		if(rdbTypeRamette == null) {
			rdbTypeRamette = new JRadioButton("Ramette");
		}
		return rdbTypeRamette;
	}
	
	/**
	 * @return the rdbTypeStylo
	 */
	public JRadioButton getRdbTypeStylo() {
		if(rdbTypeStylo == null) {
			rdbTypeStylo = new JRadioButton("Stylo");
		}
		return rdbTypeStylo;
	}
	
	/**
	 * @return the panGrammage
	 */
	public JPanel getPanGrammage() {
		if(panGrammage == null) {
			panGrammage = new JPanel();
			panGrammage.add(getCkbGrammage80());
			panGrammage.add(getCkbGrammage100());
		}
		return panGrammage;
	}
	
	/**
	 * @return the ckbGrammage80
	 */
	public JCheckBox getCkbGrammage80() {
		if(ckbGrammage80 == null) {
			ckbGrammage80 = new JCheckBox("80 grammes");
		}
		return ckbGrammage80;
	}
	
	/**
	 * @return the ckbGrammage100
	 */
	public JCheckBox getCkbGrammage100() {
		if(ckbGrammage100 == null) {
			ckbGrammage100 = new JCheckBox("100 grammes");
		}
		return ckbGrammage100;
	}

	/**
	 * @return the listCouleur
	 */
	public JList<?> getListCouleur() {
		return listCouleur;
	}
	
}

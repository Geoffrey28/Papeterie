package fr.eni.papeterie.ihm;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

public class EcranArticle extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField txtReference, txtDesignation, txtMarque, txtStock, txtPrix;
	private JLabel lblReference, lblDesignation, lblMarque, lblStock, lblPrix, lblType, lblGrammage, lblCouleur;
	private JPanel panType, panGrammage, panMenu;
	private JButton previousBtn, nextBtn, newBtn, saveBtn, deleteBtn;
	private JRadioButton rdbTypeRamette, rdbTypeStylo;
	private JCheckBox ckbGrammage80, ckbGrammage100;
	private JComboBox<String> listCouleur;
	private String[] couleurs = {"noir", "rouge", "vert", "bleu", "jaune"};
	
	private Integer indexAfficher;

	public EcranArticle() {
		this.setTitle("Article");
		this.setSize(new Dimension(500, 400));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initIHM();
	}
	
	private void initIHM() {
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5,5,5,5);
		
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
		gbc.gridheight = 1;
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
		panel.add(getListCouleur(), gbc);
		
		gbc.gridy = 8;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		panel.add(getPanMenu(), gbc);
		
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
			panType.setLayout(new BoxLayout(panType, BoxLayout.Y_AXIS));
			panType.add(getRdbTypeRamette());
			panType.add(getRdbTypeStylo());
			ButtonGroup bg = new ButtonGroup();
			bg.add(getRdbTypeRamette());
			bg.add(getRdbTypeStylo());
		}
		return panType;
	}
	
	/**
	 * @return the rdbTypeRamette
	 */
	public JRadioButton getRdbTypeRamette() {
		if(rdbTypeRamette == null) {
			rdbTypeRamette = new JRadioButton("Ramette");
			rdbTypeRamette.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getCkbGrammage80().setEnabled(true);
					getCkbGrammage100().setEnabled(true);
					getListCouleur().setEnabled(false);

				}

			});
		}
		return rdbTypeRamette;
	}
	
	/**
	 * @return the rdbTypeStylo
	 */
	public JRadioButton getRdbTypeStylo() {
		if(rdbTypeStylo == null) {
			rdbTypeStylo = new JRadioButton("Stylo");
			rdbTypeStylo.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getCkbGrammage80().setEnabled(false);
					getCkbGrammage100().setEnabled(false);
					getListCouleur().setEnabled(true);

				}

			});
		}
		return rdbTypeStylo;
	}
	
	/**
	 * @return the panGrammage
	 */
	public JPanel getPanGrammage() {
		if(panGrammage == null) {
			panGrammage = new JPanel();
			panGrammage.setLayout(new BoxLayout(panGrammage, BoxLayout.Y_AXIS));
			panGrammage.add(getCkbGrammage80());
			panGrammage.add(getCkbGrammage100());
			ButtonGroup bg = new ButtonGroup();
			bg.add(getCkbGrammage80());
			bg.add(getCkbGrammage100());
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
	public JComboBox<String> getListCouleur() {
		if(listCouleur == null) {
			listCouleur = new JComboBox<String>(couleurs);
		}
		return listCouleur;
	}
	
	/**
	 * Affiche l'article de base qui est une ramette
	 */
	public void afficherNouveau() {
		// Par défaut un article est une rammette
		Ramette a = new Ramette(null, "", "", "", 0.0f, 0, 0);
		afficherArticle(a);
	}
	
	/**
	 * Permet de récuperer les infos des articles à afficher
	 */
	public void afficherArticle(Article a) {
		// Récuperer les infos de l'article
		indexAfficher = a.getIdArticle();
		getTxtReference().setText(a.getReference());
		getTxtMarque().setText(a.getMarque());
		getTxtDesignation().setText(a.getDesignation());
		getTxtPrix().setText(String.valueOf(a.getQteStock()));
		getTxtStock().setText(String.valueOf(a.getQteStock()));
		// Pour Ramette
		if(a.getClass().equals(Ramette.class)) {
			// selectionne le type Ramette
			getRdbTypeRamette().setSelected(true);
			// active la zone grammage
			getCkbGrammage80().setEnabled(true);
			getCkbGrammage100().setEnabled(true);
			// sélectionne le bon grammage
			getCkbGrammage80().setSelected(((Ramette) a).getGrammage() == 80 );
			getCkbGrammage100().setSelected(((Ramette) a).getGrammage() == 100 );
			// désactive la selection de couleur
			getListCouleur().setSelectedItem(null);
			getListCouleur().setEnabled(false);
		}
		// Pour Stylo
		if(a.getClass().equals(Stylo.class)) {
			// selectionne le type Stylo
			getRdbTypeStylo().setSelected(true);
			// active la selection de couleur
			getListCouleur().setEnabled(true);
			// récupere la bonne couleur
			getListCouleur().setSelectedItem(((Stylo) a).getCouleur());
			// désactive la zone grammage
			getCkbGrammage80().setEnabled(false);
			getCkbGrammage100().setEnabled(false);
		}
		getRdbTypeRamette().setEnabled(a.getIdArticle() == null);
		getRdbTypeStylo().setEnabled(a.getIdArticle() == null);
	}
	
	public Article getArticle() {
		Article article = null;
		if (getRdbTypeRamette().isSelected()) {
			article = new Ramette();
		} else if (getRdbTypeStylo().isSelected()) {
			article = new Stylo();
		}
		try {
			article.setIdArticle(indexAfficher);
			article.setReference(getTxtReference().getText());
			article.setMarque(getTxtMarque().getText());
			article.setDesignation(getTxtDesignation().getText());
			article.setPrixUnitaire(Float.parseFloat(getTxtPrix().getText()));
			article.setQteStock(Integer.parseInt(getTxtStock().getText()));
			if (getListCouleur().isEnabled()) {
				((Stylo) article).setCouleur((String) getListCouleur().getSelectedItem());
			} else if (getCkbGrammage80().isEnabled()) {
				Ramette r = (Ramette) article;
				if (getCkbGrammage80().isSelected()) {
					r.setGrammage(80);
				} else if (getCkbGrammage100().isSelected()) {
					r.setGrammage(100);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	
	public void infoErreur(String msg) {
		JOptionPane.showMessageDialog(EcranArticle.this, msg, "", JOptionPane.ERROR_MESSAGE);
	}
	
	public void information(String msg) {
		JOptionPane.showMessageDialog(EcranArticle.this, msg, "", JOptionPane.INFORMATION_MESSAGE);
	}
	
	// GESTION DES INFOS ET DU MENU
	
	/**
	 * @return the panMenu
	 */
	public JPanel getPanMenu() {
		if(panMenu == null) {
			panMenu = new JPanel();
			panMenu.setLayout(new FlowLayout());
			panMenu.add(getPreviousBtn());
			panMenu.add(getNewBtn());
			panMenu.add(getSaveBtn());
			panMenu.add(getDeleteBtn());
			panMenu.add(getNextBtn());
		}
		return panMenu;
	}

	/**
	 * @return the previous
	 */
	public JButton getPreviousBtn() {
		if(previousBtn == null) {
			previousBtn = new JButton();
			ImageIcon icon = new ImageIcon(this.getClass().getResource("resources/Back24.gif"));
			previousBtn.setIcon(icon);
			previousBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleController.get().precedent();
				}
			});
		}
		return previousBtn;
	}

	/**
	 * @return the next
	 */
	public JButton getNextBtn() {
		if(nextBtn == null) {
			nextBtn = new JButton();
			ImageIcon icon = new ImageIcon(this.getClass().getResource("resources/Forward24.gif"));
			nextBtn.setIcon(icon);
			nextBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleController.get().suivant();
				}
			});
		}
		return nextBtn;
	}

	/**
	 * @return the new article button
	 */
	public JButton getNewBtn() {
		if(newBtn == null) {
			newBtn = new JButton();
			ImageIcon icon = new ImageIcon(this.getClass().getResource("resources/New24.gif"));
			newBtn.setIcon(icon);
			newBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleController.get().nouveau();
				}
			});
		}
		return newBtn;
	}

	/**
	 * @return the save
	 */
	public JButton getSaveBtn() {
		if(saveBtn == null) {
			saveBtn = new JButton();
			ImageIcon icon = new ImageIcon(this.getClass().getResource("resources/Save24.gif"));
			saveBtn.setIcon(icon);
			saveBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleController.get().enregistrer();
				}
			});
		}
		return saveBtn;
	}

	/**
	 * @return the delete
	 */
	public JButton getDeleteBtn() {
		if(deleteBtn == null) {
			deleteBtn = new JButton();
			ImageIcon icon = new ImageIcon(this.getClass().getResource("resources/Delete24.gif"));
			deleteBtn.setIcon(icon);
			deleteBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleController.get().supprimer();
				}
			});
		}
		return deleteBtn;
	}
	
}

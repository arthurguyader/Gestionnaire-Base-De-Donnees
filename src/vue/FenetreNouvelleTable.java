package vue;
import controleur.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import java.sql.*;

/**
 * Cette classe est la classe d'IHM de la fenêtre permettant la connexion à la base de donnée. Trois champs de texte sont présents, demandant
 * à l'utilsiateur son identifiant, son mot de passe et l'adresse de la base. Une fois ces champs remplis, l'utilsateur n'a qu'à cliquer sur le bouton de connexion pour
 * se connecter à la base, si bien sûr aucune erreur de connexion ne survient.
 * <P>Le champs de saisie du mot de passe cache les caractères saisis.
 */
public class FenetreNouvelleTable extends JFrame{
		
	/**
	 * La JTable qui affiche le contenu d'une table
	 */
	private JTable jTable;
	
	/**
	 *
	 */
	private JTextField nomTableTF;
	
	/**
	 *
	 */
	private JSpinner nbColonne;
	
	/**
	 *
	 */
	private JComboBox listeType;
	
	/**
	 *
	 */
	private JButton creerTable;
	
	/**
	 * Le modèle utilisé par le JTable pour se créer et se mettre à jour correctement
	 */
	private MyTableModel mtm;
	
	/**
	 * Le constructeur de la classe. Créé le panneau avec le constructeur de sa super-classe JPanel et lui applique un BorderLayout. Appelle ensuite sa méthode miseEnPlace() pour générer les éléments
	 * et les placer dans le panneau.
	 */
	public FenetreNouvelleTable(){
		super("Nouvelle Table");
		this.setLayout(new BorderLayout(10,10));
		this.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		miseEnPlace();
		this.setSize(800,600);
		this.setVisible(true);
	}
	
	/**
	 * Génère les éléments graphiques et les dispose dans la fenêtre.
	 */
	private void miseEnPlace(){
		
		// Initialisation des composants
		
		this.nomTableTF = new JTextField("");
		JLabel nomTableLabel = new JLabel("Nom de la table");

		JLabel nbColonneLabel = new JLabel("Nombre de colonne");
		nbColonne = new JSpinner(new SpinnerNumberModel(1, 1, 99999, 1));
		
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		data.add((new Object[]{"", model.Type.INT, "", false, false, false, false, "", ""}));
		
		mtm = new MyTableModel(data);
		
		jTable = new JTable(mtm);
		jTable.getTableHeader().setReorderingAllowed(false);
		comboBoxTable(jTable, jTable.getColumnModel().getColumn(1));
		
		creerTable = new JButton("Créer la table");
		
		
		// Création des sous-panneaux
		JScrollPane scrollPane = new JScrollPane(jTable);
		JPanel pannEnTete = new JPanel(new GridLayout(1,4, 5, 5));
		JPanel pannBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		// Ajout des composants dans leurs sous-panneaux respectifs
		pannEnTete.add(nomTableLabel);
		pannEnTete.add(nomTableTF);
		pannEnTete.add(nbColonneLabel);
		pannEnTete.add(nbColonne);
		pannBouton.add(creerTable);
		
		// Ajout des sous-panneaux dans le panneau principal
		this.add(pannEnTete, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(pannBouton, BorderLayout.SOUTH);
	}
	
	public void comboBoxTable(JTable jTable, TableColumn colType) {
	//Set up the editor for the sport cells.
	listeType = new JComboBox();
	for(model.Type t : model.Type.values()) {
		listeType.addItem(t);
	}
	colType.setCellEditor(new DefaultCellEditor(listeType));
    }
	
	public JTable getTable() {
		
		return this.jTable;
	}
	
	public JTextField getNomTableTF() {
		
		return this.nomTableTF;
	}
	
	public JSpinner getNbColonne() {
		
		return this.nbColonne;
	}
	
	public JButton getCreerTableBouton() {
		
		return this.creerTable;
	}
	
	public MyTableModel getMTM() {
		
		return this.mtm;
	}
}
package controleur;
import utilitaire.*;
import vue.*;
import model.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.sql.*;

/**
* Cette classe est l'écouteur des boutons de la classe FenetreRequete. Elle associe à chaque bouton l'action qu'il est censé déclencher.
*/
public class EcouteurFenetreNouvTable implements ActionListener, ChangeListener {
	
	/**
	 * La FenetreNouvelleTable
	 */
	private FenetreNouvelleTable fnt;
	
	private int spinnerValue;
	
	private ArrayList<Attribut> listeAtt;
	
	private String nomTable;
	
	private Requete requ;
	
	private EcouteurMouseAdapter ema;
	
	/**
	 * Le constructeur de la classe. Prend en paramètre une FenetreRequete, une Connection et une FenetrePrincipale et les associe à ses 
	 * attributs, puis ajoute l'écouteur à la FenetreRequete.
	 * @param fr la FenetreRequete à écouter
	 * @param maConnexion la Connection associée
	 * @param fp la FenetrePrincipale dont dépend la FenetreRequete
	 */
	public EcouteurFenetreNouvTable(FenetreNouvelleTable fnt, Requete requ, EcouteurMouseAdapter ema){
		this.fnt = fnt;
		this.requ=requ;
		this.ema = ema;
		spinnerValue = (int)fnt.getNbColonne().getValue();
		addListener();
	}
	
	/**
	 * La méthode qui associe chaque bouton à son action.
	 * <P>Si le bouton est le bouton "Lancer", exécute la méthode lancer()
	 * <P>Si le bouton est le bouton "Enregistrer", exécute la méthode enregistrer()
	 * <P>Si le bouton est le bouton "Enregistrer Sous", exécute la méthode enregistrerSous()
	 * <P>Si le bouton est le bouton "Ouvrir", exécute la méthode ouvrir()
	 */
	public void actionPerformed(ActionEvent e){
		
		listeAtt = new ArrayList<Attribut>();
		nomTable = fnt.getNomTableTF().getText();
		
		for (Object[] o :((MyTableModel)fnt.getTable().getModel()).getData()){
			
			int res = 0;
			try {
				res = Integer.parseInt((String)o[2]);
			}
			catch(NumberFormatException nfe) {
				res = -1;
			}
			Attribut att = new Attribut((String)o[0], (Type)o[1], res, (boolean)o[3], (boolean)o[4], (boolean)o[5], (boolean)o[6], (String)o[7], (String)o[8]);
			listeAtt.add(att);
		}
		
		try{
			ema.nouvelleTable(requ, this);
			fnt.dispose();
		}
		catch(SQLException sqle) {
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, "Erreur SQL, vérifiez vos informations.", "Erreur", JOptionPane.ERROR_MESSAGE);
			sqle.printStackTrace();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void stateChanged(ChangeEvent e) {
		
		int newValue = (int)fnt.getNbColonne().getValue();
		
		if ((newValue - spinnerValue) > 0) {
			
			for(int i = 0; i < newValue - spinnerValue; i++) {
				((MyTableModel)fnt.getTable().getModel()).addRow(new Object[]{"", model.Type.INT, "", false, false, false, false, "", ""});
			}
		}
		else if ((newValue - spinnerValue) < 0) {
			
			for(int i = 0; i > newValue - spinnerValue; i--) {
				((MyTableModel)fnt.getTable().getModel()).removeRow(fnt.getTable().getModel().getRowCount()-1);
			}
		}
		
		this.spinnerValue = newValue;
    }
	
	
	
	public void addListener() {
		
		fnt.getNbColonne().addChangeListener(this);
		fnt.getCreerTableBouton().addActionListener(this);
	}
	
	public String getNomTable(){
		return this.nomTable;
	}
	
	public ArrayList<Attribut> getListeAtt(){
		return this.listeAtt;
	}
}

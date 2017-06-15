package test;
import model.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.sql.*;


public class BaseDeDonneesTest{

	private BaseDeDonnees maBase;

	public BaseDeDonneesTest(){
		
	}


	@Before()
	public void miseEnPlace(){		
	}

	@Test()
	public void testConstructeur(){
		boolean test=true;
		
		try{
			BaseDeDonnees maBase = new BaseDeDonnees("jdbc:mysql://raspberry.arthurguyader.fr:3306/","admin", "admin");
			assertNotNull(maBase.getConnection());
		}
		catch(ClassNotFoundException ce){
			assertFalse(true);
		}
		catch(SQLException se){
			assertFalse(true);
		}
		catch(Exception e){
			assertFalse(true);
		}


    	try{
    		BaseDeDonnees maBase2 = new BaseDeDonnees("jdbc:mysql://raspberry.arthurguyader.fr:3306/","qfqsfn", "admin");
    	}
    	catch(ClassNotFoundException ce){
			assertFalse(false);
		}
		catch(SQLException se){
			assertFalse(false);
		}
		catch(Exception e){
			assertFalse(false);
		}


	}
	

	@Test()
	public void testAjouterSupprimerUtilisateur(){
		boolean test=true;
		
		try{
			BaseDeDonnees maBase = new BaseDeDonnees("jdbc:mysql://raspberry.arthurguyader.fr:3306/","admin", "admin");
			maBase.ajouterNouvelUtilisateur("admin","admin",1);
			// BaseDeDonnees maBase2 = new BaseDeDonnees("jdbc:oracle:thin:@localhost:1521:xe","admin", "admin");
			maBase.supprimerUtilisateur("admin");
			System.out.println("test ajout fait");
		}
		catch(Exception e){
			System.out.println("test ajout échoué");
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
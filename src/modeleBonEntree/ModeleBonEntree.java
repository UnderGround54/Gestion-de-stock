/**
 * 
 */
package modeleBonEntree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author UnderGround
 *
 */
public class ModeleBonEntree {
	private Connection conn = null;
	private java.sql.PreparedStatement statement = null;
	private ResultSet resultat = null;
	
	//constructeur
	public ModeleBonEntree() {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionstock","root","");
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Probleme de pilote base de données");
			System.out.println(ex.getMessage());
		}
		catch(SQLException ex)
		{
			System.out.println("Probleme de connexion ou de requetes");
			System.out.println(ex.getMessage());
		}
	}
	
	//liste bonEntree
		public ArrayList<BonEntree> ListeBe() {
			ArrayList<BonEntree> listeBe = new ArrayList<BonEntree>();
			try {
				
				statement = conn.prepareStatement("SELECT numBe,dateEntree FROM bonentree");
				resultat = statement.executeQuery();
				while(resultat.next())
				{
					BonEntree bonentree = new BonEntree();
					bonentree.setNumBe(resultat.getString("numBe"));
					bonentree.setDateEntree(resultat.getString("dateEntree"));
					
					listeBe.add(bonentree);
				}
				statement.close();	
			}catch(SQLException ex)
			{
				System.out.println(ex.getMessage());
			}
			return listeBe;	
		}
		
		
		//liste bonEntreerecherche
				public ArrayList<BonEntree> rechercheBes(String value) {
					ArrayList<BonEntree> listeBes = new ArrayList<BonEntree>();
					try {
						
						statement = conn.prepareStatement("SELECT numBe,dateEntree FROM bonentree WHERE numBe LIKE ? OR dateEntree LIKE ?");
						statement.setString(1,"%" + value + "%");
						statement.setString(2,"%" + value + "%");
						resultat = statement.executeQuery();
						while(resultat.next())
						{
							BonEntree bonentree = new BonEntree();
							bonentree.setNumBe(resultat.getString("numBe"));
							bonentree.setDateEntree(resultat.getString("dateEntree"));
							
							listeBes.add(bonentree);
						}
						statement.close();	
					}catch(SQLException ex)
					{
						System.out.println(ex.getMessage());
					}
					return listeBes;	
				}
				
				//liste bonEntreerecherche
				public ArrayList<BonEntree> rechercheBeDate(String date1,String date2) {
					ArrayList<BonEntree> listeBes = new ArrayList<BonEntree>();
					try {
						
						statement = conn.prepareStatement("SELECT numBe,dateEntree FROM bonentree WHERE dateEntree BETWEEN ? AND ?");
						statement.setString(1,date1);
						statement.setString(2,date2);
						resultat = statement.executeQuery();
						while(resultat.next())
						{
							BonEntree bonentree = new BonEntree();
							bonentree.setNumBe(resultat.getString("numBe"));
							bonentree.setDateEntree(resultat.getString("dateEntree"));
							
							System.out.println(resultat.getString("numBe") +" "+ resultat.getString("dateEntree"));
							
							listeBes.add(bonentree);
						}
						statement.close();	
					}catch(SQLException ex)
					{
						System.out.println(ex.getMessage());
					}
					return listeBes;	
				}
		
		
		//ajout BonEntree
		
		public void AjoutBe(BonEntree bonentree) {
			
			try
			{
				statement = conn.prepareStatement("INSERT INTO bonentree(numBe,dateEntree) VALUES(?,?)");
				statement.setString(1,bonentree.getNumBe());
				statement.setString(2,bonentree.getDateEntree());
				statement.executeUpdate();
				System.out.println("inserer avec succes");
			}
			catch(SQLException ex)
			{
				System.out.println(ex.getMessage());
			}
			
		}
		
		
		//recherche bonEntree
		public BonEntree rechercheBe(String numBe) {
			BonEntree bonentree = null;
			try
			{
				statement = conn.prepareStatement("SELECT * FROM bonentree WHERE numBe = ?");
				
				statement.setString(1,numBe);
				
				resultat = statement.executeQuery();
				
				while(resultat.next())
				{
					bonentree = new BonEntree();
					bonentree.setNumBe(resultat.getString("numBe"));
					bonentree.setDateEntree(resultat.getString("dateEntree"));			
				}
				statement.close();	
			}
			catch(SQLException ex)
			{
				
				System.out.println("recherche " +ex.getMessage());
			}
			return bonentree;	
		}
	
		//Update bonEntree
		
		public void updateBe(BonEntree bonentree)
		{
			try
			{
				statement = conn.prepareStatement("UPDATE bonentree SET dateEntree = ? WHERE numBe = ?");
			
				statement.setString(1, bonentree.getDateEntree());
				statement.setString(2, bonentree.getNumBe());
							
				statement.executeUpdate();
				System.out.println("Update avec succes");
			}
			catch(SQLException ex)
			{
				System.out.println(ex.getMessage());
			}	
		}

		//delete bonEntree
		
		public void deleteBe(String numBe)
		{
			try
			{
				
				statement = conn.prepareStatement("DELETE FROM bonentree WHERE numBe = ?");
				
				statement.setString(1, numBe);
				
				statement.executeUpdate();
				
				System.out.println("suppression avec succes");
			}
			catch(SQLException ex)
			{
				System.out.println("suppression " +ex.getMessage());
			}	
		}
		//validation
		public ArrayList<BonEntree> validerNum(String numbe)
		{
			ArrayList<BonEntree> log = new ArrayList<BonEntree>();
			try
			{
				statement= conn.prepareStatement("SELECT * FROM bonentree WHERE numBe = ?");
				
				statement.setString(1,numbe);
				
				resultat = statement.executeQuery();
				
				while(resultat.next())
				{
					BonEntree be = new BonEntree();
					
					be.setNumBe(resultat.getString("numBe"));
					be.setDateEntree(resultat.getString("dateEntree"));
					
					log.add(be);
				}
				statement.close();	
			}
			catch(SQLException ex)
			{
				
				System.out.println("Error");
			}
			return log;
		}
}

package modeleBonSortie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeleBonSortie.BonSortie;

public class ModeleBonSortie {
	private Connection conn = null;
	private java.sql.PreparedStatement statement = null;
	private ResultSet resultat = null;
	
	public ModeleBonSortie() {
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
	
	//liste bonSortie
			public ArrayList<BonSortie> ListeBs() {
				ArrayList<BonSortie> listeBs = new ArrayList<BonSortie>();
				try {
					
					statement = conn.prepareStatement("SELECT numBs,dateSortie FROM bonsortie");
					resultat = statement.executeQuery();
					while(resultat.next())
					{
						BonSortie bonsortie = new BonSortie();
						bonsortie.setNumBs(resultat.getString("numBs"));
						bonsortie.setDateSortie(resultat.getString("dateSortie"));
						
						System.out.println(resultat.getString("numBs") +" "+ resultat.getString("dateSortie"));
						
						listeBs.add(bonsortie);
					}
					statement.close();	
				}catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				return listeBs;	
			}
			
			//liste bonSortierecherche
			public ArrayList<BonSortie> rechercheBss(String value) {
				ArrayList<BonSortie> listeBss = new ArrayList<BonSortie>();
				try {
					
					statement = conn.prepareStatement("SELECT numBs,dateSortie FROM bonsortie WHERE numBs LIKE ? OR dateSortie LIKE ?");
					statement.setString(1,"%" + value + "%");
					statement.setString(2,"%" + value + "%");
					resultat = statement.executeQuery();
					while(resultat.next())
					{
						BonSortie bonsortie = new BonSortie();
						bonsortie.setNumBs(resultat.getString("numBs"));
						bonsortie.setDateSortie(resultat.getString("dateSortie"));
						
						listeBss.add(bonsortie);
					}
					statement.close();	
				}catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				return listeBss;	
			}
			
			//liste bonSortierecherche
			public ArrayList<BonSortie> rechercheBsDate(String date1,String date2) {
				ArrayList<BonSortie> listeBss = new ArrayList<BonSortie>();
				try {
					
					statement = conn.prepareStatement("SELECT numBs,dateSortie FROM bonsortie WHERE dateSortie BETWEEN ? AND ?");
					statement.setString(1,date1);
					statement.setString(2,date2);
					resultat = statement.executeQuery();
					while(resultat.next())
					{
						BonSortie bonsortie = new BonSortie();
						bonsortie.setNumBs(resultat.getString("numBs"));
						bonsortie.setDateSortie(resultat.getString("dateSortie"));						
						listeBss.add(bonsortie);
					}
					statement.close();	
				}catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				return listeBss;	
			}
			
	//ajout BonSortie
			
	public void AjoutBs(BonSortie bonsortie) {
				
				try
				{
					statement = conn.prepareStatement("INSERT INTO bonsortie(numBs,dateSortie) VALUES(?,?)");
					statement.setString(1,bonsortie.getNumBs());
					statement.setString(2,bonsortie.getDateSortie());
					statement.executeUpdate();
					System.out.println("inserer avec succes");
				}
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				
			}
			
			
			//recherche bonSortie
			public BonSortie rechercheBs(String numBs) {
				BonSortie bonsortie = null;
				try
				{
					statement = conn.prepareStatement("SELECT * FROM bonsortie WHERE numBs = ?");
					
					statement.setString(1,numBs);
					
					resultat = statement.executeQuery();
					
					while(resultat.next())
					{
						bonsortie = new BonSortie();
						
						bonsortie.setNumBs(resultat.getString("numBs"));
						bonsortie.setDateSortie(resultat.getString("dateSortie"));
								
					}
					statement.close();	
				}
				catch(SQLException ex)
				{
					
					System.out.println("recherche " +ex.getMessage());
				}
				return bonsortie;	
			}
		
			//Update bonSortie
			
			public void updateBs(BonSortie bonsortie)
			{
				try
				{
					statement = conn.prepareStatement("UPDATE bonsortie SET dateSortie = ? WHERE numBs = ?");
				
					statement.setString(1, bonsortie.getDateSortie());
					statement.setString(2, bonsortie.getNumBs());
								
					statement.executeUpdate();
					System.out.println("Update avec succes");
				}
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}	
			}

			//delete bonSortie
			
			public void deleteBs(String numBs)
			{
				try
				{
					
					statement = conn.prepareStatement("DELETE FROM bonsortie WHERE numBs = ?");
					
					statement.setString(1, numBs);
					
					statement.executeUpdate();
					
					System.out.println("suppression avec succes");
				}
				catch(SQLException ex)
				{
					System.out.println("suppression " +ex.getMessage());
				}	
			}
			
			//validation
			public ArrayList<BonSortie> validerNum(String numbs)
			{
				ArrayList<BonSortie> log = new ArrayList<BonSortie>();
				try
				{
					statement= conn.prepareStatement("SELECT * FROM bonsortie WHERE numBs = ?");
					
					statement.setString(1,numbs);
					
					resultat = statement.executeQuery();
					
					while(resultat.next())
					{
						BonSortie bs = new BonSortie();
						
						bs.setNumBs(resultat.getString("numBs"));
						bs.setDateSortie(resultat.getString("dateSortie"));
						
						log.add(bs);
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

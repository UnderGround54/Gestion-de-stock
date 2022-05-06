/**
 * 
 */
package modeleLigneBs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author UnderGround
 *
 */
public class ModeleLigneBs {
	
	private Connection conn = null;
	private java.sql.PreparedStatement statement = null;
	private ResultSet resultat = null;

	public ModeleLigneBs() {
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
	
	//liste ligneBe
	public ArrayList<LigneBs> ListeLigneBs() {
		ArrayList<LigneBs> listeLigneBs = new ArrayList<LigneBs>();
		try {
					
				statement = conn.prepareStatement("SELECT idLigneBs,lignebs.numProduit,numBs,produit.design,qteSortie FROM lignebs,produit WHERE lignebs.numProduit = produit.numProduit");
				resultat = statement.executeQuery();
				while(resultat.next())
				{
					LigneBs lignebs = new LigneBs();
					lignebs.setIdLigneBs(resultat.getInt("idLigneBs"));
					lignebs.setNumBs(resultat.getString("numBs"));
					lignebs.setDesignProduit(resultat.getString("design"));
					lignebs.setNumProduit(resultat.getString("numProduit"));
					lignebs.setQteSortie(resultat.getInt("qteSortie"));
						
					//System.out.println(resultat.getInt("idLigneBs") +" "+ resultat.getString("numBs")+" "+ resultat.getString("numProduit")+" "+resultat.getInt("qteSortie"));
					
					listeLigneBs.add(lignebs);
				}
				statement.close();	
			}catch(SQLException ex)
			{
				System.out.println(ex.getMessage());
			}
		return listeLigneBs;	
	}
	
	//ajout lignebs
	
			public void AjoutLigneBs(LigneBs lignebs) {
				
				try
				{
					statement = conn.prepareStatement("INSERT INTO lignebs(numBs,numProduit,qteSortie) VALUES(?,?,?)");
					statement.setString(1,lignebs.getNumBs());
					statement.setString(2,lignebs.getNumProduit());
					statement.setInt(3,lignebs.getQteSortie());
					statement.executeUpdate();
					System.out.println("inserer avec succes");
				}
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				
			}
			
			//recherche lignebs
			public LigneBs rechercheLigneBs(int idLigneBs) {
				LigneBs lignebs = null;
				try
				{
					statement = conn.prepareStatement("SELECT * FROM lignebs WHERE idLigneBs = ?");
					
					statement.setInt(1,idLigneBs);
					
					resultat = statement.executeQuery();
					
					while(resultat.next())
					{
						lignebs = new LigneBs();
						
						lignebs.setIdLigneBs(resultat.getInt("idLigneBs"));
						lignebs.setNumBs(resultat.getString("numBs"));					
						lignebs.setNumProduit(resultat.getString("numProduit"));
						lignebs.setQteSortie(resultat.getInt("qteSortie"));
								
					}
					statement.close();	
				}
				catch(SQLException ex)
				{
					
					System.out.println("recherche " +ex.getMessage());
				}
				return lignebs;	
			}
			//Update lignebs
			
			public void updateLigneBs(LigneBs lignebs)
			{
				try
				{
					statement = conn.prepareStatement("UPDATE lignebs SET numBs = ?, numProduit = ?, qteSortie = ? WHERE idLigneBs = ?");
				
					statement.setString(1, lignebs.getNumBs());
					statement.setString(2, lignebs.getNumProduit());
					statement.setInt(3, lignebs.getQteSortie());
					statement.setInt(4, lignebs.getIdLigneBs());
								
					statement.executeUpdate();
					System.out.println("Update avec succes");
				}
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}	
			}
			//delete lignebs
			
			public void deleteLigneBs(int idlignebs)
			{
				try
				{
					
					statement = conn.prepareStatement("DELETE FROM lignebs WHERE idLigneBs = ?");
					
					statement.setInt(1, idlignebs);
					
					statement.executeUpdate();
					
					System.out.println("suppression avec succes");
				}
				catch(SQLException ex)
				{
					System.out.println("suppression " +ex.getMessage());
				}	
			}

}

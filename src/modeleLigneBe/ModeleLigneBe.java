/**
 * 
 */
package modeleLigneBe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author UnderGround
 *
 */
public class ModeleLigneBe {
	private Connection conn = null;
	private java.sql.PreparedStatement statement = null;
	private ResultSet resultat = null;

	public ModeleLigneBe() {
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
	public ArrayList<LigneBe> ListeLigneBe() {
		ArrayList<LigneBe> listeLigneBe = new ArrayList<LigneBe>();
		try {
					
				statement = conn.prepareStatement("SELECT idLigneBe,numBe,qteEntree,lignebe.numProduit,produit.design FROM lignebe,produit WHERE lignebe.numProduit = produit.numProduit");
				resultat = statement.executeQuery();
				while(resultat.next())
				{
					LigneBe lignebe = new LigneBe();
					lignebe.setIdLigneBe(resultat.getInt("idLigneBe"));
					lignebe.setNumBe(resultat.getString("numBe"));
					lignebe.setDesignProduit(resultat.getString("design"));
					lignebe.setNumProduit(resultat.getString("numProduit"));
					lignebe.setQteEntree(resultat.getInt("qteEntree"));
					
					listeLigneBe.add(lignebe);
				}
				statement.close();	
			}catch(SQLException ex)
			{
				System.out.println(ex.getMessage());
			}
		return listeLigneBe;	
	}
	
	//ajout lignebe
	
			public void AjoutLigneBe(LigneBe lignebe) {
				
				try
				{
					statement = conn.prepareStatement("INSERT INTO lignebe(numBe,numProduit,qteEntree) VALUES(?,?,?)");
					statement.setString(1,lignebe.getNumBe());
					statement.setString(2,lignebe.getNumProduit());
					statement.setInt(3,lignebe.getQteEntree());
					statement.executeUpdate();
					System.out.println("inserer avec succes");
				}
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				
			}
			
			//recherche lignebe
			public LigneBe rechercheLigneBe(int idLigneBe) {
				LigneBe lignebe = null;
				try
				{
					statement = conn.prepareStatement("SELECT * FROM lignebe WHERE idLigneBe = ?");
					
					statement.setInt(1,idLigneBe);
					
					resultat = statement.executeQuery();
					
					while(resultat.next())
					{
						lignebe = new LigneBe();
						
						lignebe.setIdLigneBe(resultat.getInt("idLigneBe"));
						lignebe.setNumBe(resultat.getString("numBe"));					
						lignebe.setNumProduit(resultat.getString("numProduit"));
						lignebe.setQteEntree(resultat.getInt("qteEntree"));
								
					}
					statement.close();	
				}
				catch(SQLException ex)
				{
					
					System.out.println("recherche " +ex.getMessage());
				}
				return lignebe;	
			}
			//Update lignebe
			
			public void updateLigneBe(LigneBe lignebe)
			{
				try
				{
					statement = conn.prepareStatement("UPDATE lignebe SET numBe = ?, numProduit = ?, qteEntree = ? WHERE idLigneBe = ?");
				
					statement.setString(1, lignebe.getNumBe());
					statement.setString(2, lignebe.getNumProduit());
					statement.setInt(3, lignebe.getQteEntree());
					statement.setInt(4, lignebe.getIdLigneBe());
								
					statement.executeUpdate();
					System.out.println("Update avec succes");
				}
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}	
			}
			//delete lignebe
			
			public void deleteLigneBe(int idlignebe)
			{
				try
				{
					
					statement = conn.prepareStatement("DELETE FROM lignebe WHERE idLigneBe = ?");
					
					statement.setInt(1, idlignebe);
					
					statement.executeUpdate();
					
					System.out.println("suppression avec succes");
				}
				catch(SQLException ex)
				{
					System.out.println("suppression " +ex.getMessage());
				}	
			}
}

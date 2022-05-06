/**
 * 
 */
package modeleProduit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author UnderGround
 *
 */
public class ModeleProduit {
	private Connection conn = null;
	private java.sql.PreparedStatement statement = null;
	private ResultSet resultat = null;
	
	public ModeleProduit() {
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
	
	//liste Produit
	public ArrayList<Produit> ListeProduit() {
		ArrayList<Produit> listeProduit = new ArrayList<Produit>();
		try {
	
			statement = conn.prepareStatement("SELECT numProduit,design,stock FROM produit");
			resultat = statement.executeQuery();
			while(resultat.next())
			{
				Produit produit = new Produit();
				produit.setNumProduit(resultat.getString("numProduit"));
				produit.setDesign(resultat.getString("design"));
				produit.setStock(resultat.getInt("stock"));
						
				System.out.println(resultat.getString("numProduit") +" "+ resultat.getString("design")+" "+ resultat.getInt("stock"));
				
				listeProduit.add(produit);
			}
				statement.close();	
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
			return listeProduit;	
		}
	//ajout Produit
	
			public void AjoutProduit(Produit produit) {
				
				try
				{
					statement = conn.prepareStatement("INSERT INTO produit(numProduit,design,stock) VALUES(?,?,?)");
					statement.setString(1,produit.getNumProduit());
					statement.setString(2,produit.getDesign());
					statement.setInt(3,produit.getStock());
					statement.executeUpdate();
					System.out.println("inserer avec succes");
				}
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				
			}
			
			//recherche produit
			public Produit rechercheProduit(String numProduit) {
				Produit produit = null;
				try
				{
					statement = conn.prepareStatement("SELECT * FROM produit WHERE numProduit = ?");
					
					statement.setString(1,numProduit);
					
					resultat = statement.executeQuery();
					
					while(resultat.next())
					{
						produit = new Produit();
						
						produit.setNumProduit(resultat.getString("numProduit"));
						produit.setDesign(resultat.getString("design"));
						produit.setStock(resultat.getInt("stock"));
								
					}
					statement.close();	
				}
				catch(SQLException ex)
				{
					
					System.out.println("recherche " +ex.getMessage());
				}
				return produit;	
			}
			//Update produit
			
			public void updateProduit(Produit produit)
			{
				try
				{
					statement = conn.prepareStatement("UPDATE produit SET design = ? , stock = ? WHERE numProduit = ?");
				
					statement.setString(1, produit.getDesign());
					statement.setInt(2, produit.getStock());
					statement.setString(3, produit.getNumProduit());
								
					statement.executeUpdate();
					System.out.println("Update avec succes");
				}
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}	
			}
			
			//delete produit
			
			public void deleteProduit(String numProduit)
			{
				try
				{
					
					statement = conn.prepareStatement("DELETE FROM produit WHERE numProduit = ?");
					
					statement.setString(1, numProduit);
					
					statement.executeUpdate();
					
					System.out.println("suppression avec succes");
				}
				catch(SQLException ex)
				{
					System.out.println("suppression " +ex.getMessage());
				}	
			}
			
			//liste Produitrecherche
			public ArrayList<Produit> rechercheProd(String value) {
				ArrayList<Produit> listeProd = new ArrayList<Produit>();
				try {
					
					statement = conn.prepareStatement("SELECT * FROM produit WHERE numproduit LIKE ?");
					statement.setString(1,"%" + value + "%");
					resultat = statement.executeQuery();
					while(resultat.next())
					{
						Produit produit = new Produit();
						produit.setNumProduit(resultat.getString("numproduit"));
						produit.setDesign(resultat.getString("design"));
						produit.setStock(resultat.getInt("stock"));
						
						
						listeProd.add(produit);
					}
					statement.close();	
				}catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				return listeProd;	
			}
			
			
			//liste entree
			public ArrayList<Produit> ListeStockEntree() {
				ArrayList<Produit> listeStockEntree = new ArrayList<Produit>();
				try {
			
					statement = conn.prepareStatement("SELECT bonentree.numBe,design,qteentree,dateentree FROM produit,lignebe,bonentree WHERE lignebe.numBe = bonentree.numBe AND produit.numProduit = lignebe.numProduit ");
					resultat = statement.executeQuery();
					while(resultat.next())
					{
						Produit produit = new Produit();
						produit.setNumBe(resultat.getString("numBe"));
						produit.setDesign(resultat.getString("design"));					
						produit.setQteEntree(resultat.getInt("qteentree"));
						produit.setDateEntree(resultat.getString("dateentree"));
						
						
						listeStockEntree.add(produit);
					}
						statement.close();	
				}
				catch(SQLException ex)
				{
					System.out.println(" stock non trouve"+ex.getMessage());
				}
					return listeStockEntree;	
			}
			
			//liste sortie
			public ArrayList<Produit> ListeStockSortie() {
				ArrayList<Produit> listeStockSortie = new ArrayList<Produit>();
				try {
			
					statement = conn.prepareStatement("SELECT bonsortie.numBs,design,qtesortie,datesortie FROM produit,lignebs,bonsortie WHERE lignebs.numBs = bonsortie.numBs AND produit.numProduit = lignebs.numProduit");
					resultat = statement.executeQuery();
					while(resultat.next())
					{
						Produit produit = new Produit();
						
						produit.setNumBs(resultat.getString("numBs"));
						produit.setDesign(resultat.getString("design"));
						produit.setQteSortie(resultat.getInt("qtesortie"));
						produit.setDateSortie(resultat.getString("datesortie"));	
						
						listeStockSortie.add(produit);
					}
						statement.close();	
				}
				catch(SQLException ex)
				{
					System.out.println(" stock non trouve"+ex.getMessage());
				}
					return listeStockSortie;	
			}
			
			//validation
			public ArrayList<Produit> validerNum(String numproduit)
			{
				ArrayList<Produit> log = new ArrayList<Produit>();
				try
				{
					statement= conn.prepareStatement("SELECT * FROM produit WHERE numProduit = ?");
					
					statement.setString(1,numproduit);
					
					resultat = statement.executeQuery();
					
					while(resultat.next())
					{
						Produit prod = new Produit();
						
						prod.setNumBe(resultat.getString("numProduit"));
						prod.setDesign(resultat.getString("design"));
						prod.setStock(resultat.getInt("stock"));						
						log.add(prod);
					}
					statement.close();	
				}
				catch(SQLException ex)
				{
					
					System.out.println("Error");
				}
				return log;
			}
			
			//liste dateEntree
			public ArrayList<Produit> rechercheDateEntree(String date1,String date2) {
				ArrayList<Produit> listedateentree = new ArrayList<Produit>();
				try {
					
					statement = conn.prepareStatement("SELECT bonentree.numBe,design,qteentree,dateentree FROM produit,lignebe,bonentree WHERE lignebe.numBe = bonentree.numBe AND produit.numProduit = lignebe.numProduit AND bonentree.dateentree BETWEEN ? AND ? ");
					statement.setString(1,date1);
					statement.setString(2,date2);
					resultat = statement.executeQuery();
					while(resultat.next())
					{
						Produit produit = new Produit();
						
						produit.setNumBe(resultat.getString("bonentree.numBe"));
						produit.setDateEntree(resultat.getString("dateEntree"));
						produit.setDesign(resultat.getString("design"));
						produit.setQteEntree(resultat.getInt("qteentree"));
						
						listedateentree.add(produit);
					}
					statement.close();	
				}catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				return listedateentree;	
			}
			
			//liste dateSOrtie
			public ArrayList<Produit> rechercheDateSortie(String date1,String date2) {
				ArrayList<Produit> listedatesortie = new ArrayList<Produit>();
				try {
					
					statement = conn.prepareStatement("SELECT bonsortie.numBs,design,qtesortie,datesortie FROM produit,lignebs,bonsortie WHERE lignebs.numBs = bonsortie.numBs AND produit.numProduit = lignebs.numProduit AND bonsortie.datesortie BETWEEN ? AND ? ");
					statement.setString(1,date1);
					statement.setString(2,date2);
					resultat = statement.executeQuery();
					while(resultat.next())
					{
						Produit produit = new Produit();
						
						produit.setNumBs(resultat.getString("bonsortie.numBs"));
						produit.setDateSortie(resultat.getString("datesortie"));
						produit.setDesign(resultat.getString("design"));
						produit.setQteSortie(resultat.getInt("qtesortie"));
						
						listedatesortie.add(produit);
					}
					statement.close();	
				}catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				return listedatesortie;	
			}
			
			//liste dateEntree
			public ArrayList<Produit> rechercheProduitEntree(String numproduit) {
				ArrayList<Produit> listeproduitentree = new ArrayList<Produit>();
				try {
					
					statement = conn.prepareStatement("SELECT bonentree.numBe,qteentree,dateentree FROM produit,lignebe,bonentree WHERE lignebe.numBe = bonentree.numBe AND produit.numProduit = lignebe.numProduit AND produit.numProduit = ?");
					statement.setString(1,numproduit);
					resultat = statement.executeQuery();
					while(resultat.next())
					{
						Produit produit = new Produit();
						
						produit.setNumBe(resultat.getString("bonentree.numBe"));
						produit.setDateEntree(resultat.getString("dateEntree"));
						produit.setQteEntree(resultat.getInt("qteentree"));
						
						listeproduitentree.add(produit);
					}
					statement.close();	
				}catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				return listeproduitentree;	
			}
			
			//recherche produit sortie
			public ArrayList<Produit> rechercheProduitSortie(String numproduit) {
				ArrayList<Produit> listeproduitsortie = new ArrayList<Produit>();
				try {
					
					statement = conn.prepareStatement("SELECT bonsortie.numBs,stock,qtesortie,datesortie FROM produit,lignebs,bonsortie WHERE lignebs.numBs = bonsortie.numBs AND produit.numProduit = lignebs.numProduit AND produit.numProduit = ? ");
					statement.setString(1,numproduit);
					
					resultat = statement.executeQuery();
					while(resultat.next())
					{
						Produit produit = new Produit();
						
						produit.setNumBs(resultat.getString("bonsortie.numBs"));
						produit.setDateSortie(resultat.getString("datesortie"));
						produit.setQteSortie(resultat.getInt("qtesortie"));
						produit.setStock(resultat.getInt("stock"));
						
						listeproduitsortie.add(produit);
					}
					statement.close();	
				}catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				return listeproduitsortie;	
			}

}

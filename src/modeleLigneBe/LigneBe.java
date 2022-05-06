/**
 * 
 */
package modeleLigneBe;

/**
 * @author UnderGround
 *
 */
public class LigneBe {
	
	private int idLigneBe;
	private String numBe;
	private String numProduit;
	private String designProduit;
	private int qteEntree;
	
	
	
	public int getIdLigneBe() {
		return idLigneBe;
	}
	public void setIdLigneBe(int idLigneBe) {
		this.idLigneBe = idLigneBe;
	}
	public String getNumProduit() {
		return numProduit;
	}
	public void setNumProduit(String numProduit) {
		this.numProduit = numProduit;
	}
	public String getNumBe() {
		return numBe;
	}
	public void setNumBe(String numBe) {
		this.numBe = numBe;
	}
	public int getQteEntree() {
		return qteEntree;
	}
	public void setQteEntree(int qteEntree) {
		this.qteEntree = qteEntree;
	}
	public String getDesignProduit() {
		return designProduit;
	}
	public void setDesignProduit(String designProduit) {
		this.designProduit = designProduit;
	}	
}

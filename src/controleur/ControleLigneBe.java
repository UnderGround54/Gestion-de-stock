package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeleBonEntree.BonEntree;
import modeleBonEntree.ModeleBonEntree;
import modeleLigneBe.LigneBe;
import modeleLigneBe.ModeleLigneBe;
import modeleProduit.ModeleProduit;
import modeleProduit.Produit;

/**
 * Servlet implementation class ControleLigneBe
 */
@WebServlet("/ControleLigneBe")
public class ControleLigneBe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<LigneBe> listelignebe = null;
	private ArrayList<BonEntree> listebe = null;
	private ArrayList<Produit> listeproduit = null;
	
	ModeleLigneBe donneelignebe = new ModeleLigneBe();
	ModeleBonEntree be = new ModeleBonEntree();
	ModeleProduit produit = new ModeleProduit();
       
   
    public ControleLigneBe() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		String lienview = request.getParameter("resultat");
		
		listelignebe = donneelignebe.ListeLigneBe();
		listebe = be.ListeBe();
		listeproduit = produit.ListeProduit();
		
		if(listelignebe != null || listebe != null || listeproduit != null ) {
			request.setAttribute("listelignebe", listelignebe);
			request.setAttribute("listebe", listebe);
			request.setAttribute("listeproduit", listeproduit);
		}
		if(path.equals("/ListeLigneBe")) {	
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/LigneBe/ListeLigneBe.jsp").forward(request,response);
		}
		
		if(lienview.equals("suprrlignebe"))
		{
			String idlignebe = request.getParameter("idlignebe");
			String numproduit = request.getParameter("numproduit");
			String qteentree = request.getParameter("qteentree");
			int resteStock = 0;
			Produit prod = produit.rechercheProduit(numproduit);
			resteStock = prod.getStock() - Integer.parseInt(qteentree);
			prod.setStock(resteStock);
			produit.updateProduit(prod);
			donneelignebe.deleteLigneBe(Integer.parseInt(idlignebe));	
			response.sendRedirect("ListeLigneBe");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModeleLigneBe donneelignebe = new ModeleLigneBe();
		
		String numbe = request.getParameter("numbe");
		String numproduit = request.getParameter("numproduit");
		String qteentree = request.getParameter("qteentree");
		
		LigneBe lignebe= new LigneBe();
		
		
		lignebe.setNumBe(numbe);	
		lignebe.setNumProduit(numproduit);
		lignebe.setQteEntree(Integer.parseInt(qteentree));
	
			
		String ajout = request.getParameter("ajoutlignebe");
			
		if(ajout.equals("addlignebe"))
		{
			int Stock = 0;
			Produit prod = produit.rechercheProduit(numproduit);
			Stock = prod.getStock()+Integer.parseInt(qteentree);
			
				prod.setStock(Stock);
				produit.updateProduit(prod);
				donneelignebe.AjoutLigneBe(lignebe);
				response.sendRedirect("ListeLigneBe");
			
		}
		
		if(ajout.equals("modiflignebe"))
		{
			String idlignebe = request.getParameter("idlignebe");
			
			int resteStock = 0;
			int stockfin = 0;
			int stocks = 0;
			Produit prod = produit.rechercheProduit(numproduit);
			LigneBe bs = donneelignebe.rechercheLigneBe(Integer.parseInt(idlignebe));
			stocks = bs.getQteEntree();
			
			resteStock = prod.getStock() - stocks;
			stockfin = resteStock + Integer.parseInt(qteentree);
			
			prod.setStock(stockfin);
			produit.updateProduit(prod);
			
			lignebe.setIdLigneBe(Integer.parseInt(idlignebe));			
			donneelignebe.updateLigneBe(lignebe);
			listelignebe = donneelignebe.ListeLigneBe();
			request.setAttribute("listelignebe",listelignebe);
			response.sendRedirect("ListeLigneBe");
		}
		
	}

}

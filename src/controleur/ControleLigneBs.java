package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeleBonSortie.BonSortie;
import modeleBonSortie.ModeleBonSortie;
import modeleLigneBs.LigneBs;
import modeleLigneBs.ModeleLigneBs;
import modeleProduit.ModeleProduit;
import modeleProduit.Produit;

/**
 * Servlet implementation class ControleLigneBs
 */
@WebServlet("/ControleLigneBs")
public class ControleLigneBs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<LigneBs> listelignebs = null;
	private ArrayList<BonSortie> listebs = null;
	private ArrayList<Produit> listeproduit = null;
	
	ModeleLigneBs donneelignebs = new ModeleLigneBs();
	ModeleBonSortie bs = new ModeleBonSortie();
	ModeleProduit produit = new ModeleProduit();
       
   
    public ControleLigneBs() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();
		String lienview = request.getParameter("resultat");
		
		listelignebs = donneelignebs.ListeLigneBs();
		listebs = bs.ListeBs();
		listeproduit = produit.ListeProduit();
		
		if(listelignebs != null || listebs != null || listeproduit != null ) {
			request.setAttribute("listelignebs", listelignebs);
			request.setAttribute("listebs", listebs);
			request.setAttribute("listeproduit", listeproduit);
		}
		
		
		if(path.equals("/ListeLigneBs")) {	
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/LigneBs/ListeLigneBs.jsp").forward(request,response);
		}
		
		if(lienview.equals("suprrlignebs"))
		{
			String idlignebs = request.getParameter("idlignebs");
			String numproduit = request.getParameter("numproduit");
			String qtesortie = request.getParameter("qtesortie");
			
			int resteStock = 0;
			Produit prod = produit.rechercheProduit(numproduit);
			resteStock = prod.getStock()+Integer.parseInt(qtesortie);
			prod.setStock(resteStock);
			produit.updateProduit(prod);
			donneelignebs.deleteLigneBs(Integer.parseInt(idlignebs));
			response.sendRedirect("ListeLigneBs");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeleLigneBs donneelignebs = new ModeleLigneBs();
		
		String numbs = request.getParameter("numbs");
		String numproduit = request.getParameter("numproduit");
		String qtesortie = request.getParameter("qtesortie");
		
		listelignebs = donneelignebs.ListeLigneBs();
		listebs = bs.ListeBs();
		listeproduit = produit.ListeProduit();
		
		if(listelignebs != null || listebs != null || listeproduit != null ) {
			request.setAttribute("listelignebs", listelignebs);
			request.setAttribute("listebs", listebs);
			request.setAttribute("listeproduit", listeproduit);
		}
		
		LigneBs lignebs= new LigneBs();
		
		
		lignebs.setNumBs(numbs);	
		lignebs.setNumProduit(numproduit);
		lignebs.setQteSortie(Integer.parseInt(qtesortie));
	
			
		String ajout = request.getParameter("ajoutlignebs");
			
		if(ajout.equals("addlignebs"))
		{
			int resteStock = 0;
			Produit prod = produit.rechercheProduit(numproduit);
			resteStock = prod.getStock()-Integer.parseInt(qtesortie);
			if(resteStock >= 0)
			{
				prod.setStock(resteStock);
				produit.updateProduit(prod);
				donneelignebs.AjoutLigneBs(lignebs);
				response.sendRedirect("ListeLigneBs");
				
			}else{
				request.setAttribute("stock", "stock negatif");
				this.getServletContext().getRequestDispatcher("/WEB-INF/LigneBs/ListeLigneBs.jsp").forward(request,response);
			}
			
		}
		
		if(ajout.equals("modiflignebs"))
		{
			String idlignebs = request.getParameter("idlignebs");
			
			int resteStock = 0;
			int stockfin = 0;
			int stocks = 0;
			Produit prod = produit.rechercheProduit(numproduit);
			LigneBs bs = donneelignebs.rechercheLigneBs(Integer.parseInt(idlignebs));
			stocks = bs.getQteSortie();
			
			resteStock = prod.getStock() + stocks;
			stockfin = resteStock - Integer.parseInt(qtesortie);
					
					if(stockfin >= 0)
					{
						prod.setStock(stockfin);
						produit.updateProduit(prod);
						
						lignebs.setIdLigneBs(Integer.parseInt(idlignebs));
						donneelignebs.updateLigneBs(lignebs);
						listelignebs = donneelignebs.ListeLigneBs();
						request.setAttribute("listelignebs",listelignebs);
						response.sendRedirect("ListeLigneBs");
						
					}else{
						request.setAttribute("stock", "stock negatif");
						this.getServletContext().getRequestDispatcher("/WEB-INF/LigneBs/ListeLigneBs.jsp").forward(request,response);
					}
		}
		
	}

}

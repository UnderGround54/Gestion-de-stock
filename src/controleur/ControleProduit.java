package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeleProduit.ModeleProduit;
import modeleProduit.Produit;

/**
 * Servlet implementation class ControleProduit
 */
@WebServlet("/ControleProduit")
public class ControleProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Produit> listeproduit = null;
	private ArrayList<Produit> listeStockEntree = null;
	private ArrayList<Produit> listeStockSortie = null;
	
	ModeleProduit donneeproduit = new ModeleProduit();
       
   
    public ControleProduit() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();
		String lienview = request.getParameter("resultat");
		listeproduit = donneeproduit.ListeProduit();
		listeStockEntree = donneeproduit.ListeStockEntree();
		listeStockSortie = donneeproduit.ListeStockSortie();
		
		if(listeproduit != null) {
			request.setAttribute("listeproduit", listeproduit);
			
		}
		if(path.equals("/ListeProduit")) {			
			this.getServletContext().getRequestDispatcher("/WEB-INF/Produit/ListeProduit.jsp").forward(request,response);
		}
		
		if(path.equals("/Stock")) {
			request.setAttribute("listeStockEntree", listeStockEntree);
			request.setAttribute("listeStockSortie", listeStockSortie);
			this.getServletContext().getRequestDispatcher("/WEB-INF/Produit/Stock.jsp").forward(request,response);
		}
		
		if(lienview.equals("supprproduit"))
		{
			String numproduit = request.getParameter("numproduit");
			donneeproduit.deleteProduit(numproduit);	
			response.sendRedirect("ListeProduit");
		}
		
		if(lienview.equals("searchdate")) {			
			String date1 = request.getParameter("date1");
			String date2 = request.getParameter("date2");
			ArrayList<Produit> listedatesortie = donneeproduit.rechercheDateSortie(date1, date2);
			ArrayList<Produit> listedateentree = donneeproduit.rechercheDateEntree(date1, date2);
			request.setAttribute("listeStockSortie", listedatesortie);
			request.setAttribute("listeStockEntree", listedateentree);
			this.getServletContext().getRequestDispatcher("/WEB-INF/Produit/Stock.jsp").forward(request,response);
		}
		
		if(lienview.equals("searchproduit")) {			
			String searchprod = request.getParameter("searchnumprod");
			if(searchprod == "") {
				response.sendRedirect("Stock");
			}else {
				ArrayList<Produit> listeproduitsortie = donneeproduit.rechercheProduitSortie(searchprod);
				ArrayList<Produit> listeproduitentree = donneeproduit.rechercheProduitEntree(searchprod);
				ArrayList<Produit> listeproduit = donneeproduit.rechercheProd(searchprod);
				request.setAttribute("listeStockSortie", listeproduitsortie);
				request.setAttribute("listeStockEntree", listeproduitentree);
				request.setAttribute("stockprod", listeproduit);
				this.getServletContext().getRequestDispatcher("/WEB-INF/Produit/Stock.jsp").forward(request,response);
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModeleProduit donneeproduit = new ModeleProduit();
		
		String numproduit = request.getParameter("numproduit");
		String design = request.getParameter("design");
		String stock = request.getParameter("stock");
		
		Produit produit = new Produit();
		
		produit.setNumProduit(numproduit);
		produit.setDesign(design);
		produit.setStock(Integer.parseInt(stock));
		listeproduit = donneeproduit.ListeProduit();
		if(listeproduit != null) {
			request.setAttribute("listeproduit", listeproduit);
		}
		
		String ajout = request.getParameter("ajoutproduit");
		
		if(ajout.equals("addproduit"))
		{
			
			listeproduit = donneeproduit.validerNum(numproduit);
			if(listeproduit.size()!=0) {
				request.setAttribute("trouve", "numero existe");
				this.getServletContext().getRequestDispatcher("/WEB-INF/Produit/ListeProduit.jsp").forward(request,response);
			}else {
				donneeproduit.AjoutProduit(produit);
				response.sendRedirect("ListeProduit");
			}
			
		}
		
		if(ajout.equals("editproduit"))
		{
			int Stock = 0;
			Produit prod = donneeproduit.rechercheProduit(numproduit);
			Stock = Integer.parseInt(stock);
			
			if(prod.getStock() > Stock)
			{
				request.setAttribute("stock", "modification pas possible");
				request.setAttribute("listeproduit",listeproduit);
				response.sendRedirect("ListeProduit");
			
			}else {
				donneeproduit.updateProduit(produit);
				listeproduit = donneeproduit.ListeProduit();
				request.setAttribute("listeproduit",listeproduit);
				response.sendRedirect("ListeProduit");
			}
		}
		
	}

}

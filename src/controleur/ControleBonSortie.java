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
import modeleProduit.ModeleProduit;
import modeleProduit.Produit;

/**
 * Servlet implementation class ControleBonSortie
 */
@WebServlet("/ControleBonSortie")
public class ControleBonSortie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<BonSortie> listebs = null;
	private ArrayList<Produit> listeproduit = null;
	
	ModeleBonSortie donneebs = new ModeleBonSortie();
	ModeleProduit produit = new ModeleProduit();
   
    public ControleBonSortie() {
        super();  
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		String lienview = request.getParameter("resultat");
		listebs = donneebs.ListeBs();
		listeproduit = produit.ListeProduit();
		
		if(listebs != null || listeproduit != null) {
			request.setAttribute("listeproduit", listeproduit);
			request.setAttribute("listebs", listebs);
		}
		if(path.equals("/ListeBs")) {			
			this.getServletContext().getRequestDispatcher("/WEB-INF/BonSortie/ListeBs.jsp").forward(request,response);
		}
		if(lienview.equals("supprbs"))
		{
			String numbs = request.getParameter("numbs");
			donneebs.deleteBs(numbs);	
			response.sendRedirect("ListeBs");
		}
		if(lienview.equals("search")) {			
			String value = request.getParameter("search");
			ArrayList<BonSortie> listeBss = donneebs.rechercheBss(value);
			request.setAttribute("listebs", listeBss);
			this.getServletContext().getRequestDispatcher("/WEB-INF/BonSortie/ListeBs.jsp").forward(request,response);
		}
		
		if(lienview.equals("searchdate")) {			
			String date1 = request.getParameter("date1");
			String date2 = request.getParameter("date2");
			ArrayList<BonSortie> listeBss = donneebs.rechercheBsDate(date1,date2);
			request.setAttribute("listebs", listeBss);
			this.getServletContext().getRequestDispatcher("/WEB-INF/BonSortie/ListeBs.jsp").forward(request,response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModeleBonSortie donneebs = new ModeleBonSortie();
		
		String numbs = request.getParameter("numbs");
		String datesortie = request.getParameter("datesortie");
		
		listebs = donneebs.ListeBs();
		listeproduit = produit.ListeProduit();
		
		if(listebs != null || listeproduit != null) {
			request.setAttribute("listebe", listebs);
			request.setAttribute("listeproduit", listeproduit);
		}
		if(listebs != null) {
			request.setAttribute("listebs", listebs);
		}
		
		BonSortie bonsortie= new BonSortie();
		
		bonsortie.setNumBs(numbs);
		bonsortie.setDateSortie(datesortie);	
		String ajout = request.getParameter("ajoutbs");
			
		if(ajout.equals("addbs"))
		{	
			listebs = donneebs.validerNum(numbs);
			if(listebs.size()!=0) {
				request.setAttribute("trouve", "numero existe");
				this.getServletContext().getRequestDispatcher("/WEB-INF/BonSortie/ListeBs.jsp").forward(request,response);
			}else {
				donneebs.AjoutBs(bonsortie);
				response.sendRedirect("ListeBs");
			}
			
		}
		
		if(ajout.equals("editbs"))
		{
			donneebs.updateBs(bonsortie);
			listebs = donneebs.ListeBs();
			request.setAttribute("listebs",listebs);
			response.sendRedirect("ListeBs");
		}
		
	}

}

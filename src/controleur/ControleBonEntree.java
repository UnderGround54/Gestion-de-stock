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
import modeleProduit.ModeleProduit;
import modeleProduit.Produit;

/**
 * Servlet implementation class ControleurBonEntree
 */
@WebServlet("/ControleurBonEntree")
public class ControleBonEntree extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<BonEntree> listebe = null;
	private ArrayList<Produit> listeproduit = null;
	
	ModeleBonEntree donneebe = new ModeleBonEntree();
	ModeleProduit produit = new ModeleProduit();
       
   
    public ControleBonEntree() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		String lienview = request.getParameter("resultat");
		
		listebe = donneebe.ListeBe();
		listeproduit = produit.ListeProduit();
		
		if(listebe != null || listeproduit != null) {
			request.setAttribute("listebe", listebe);
			request.setAttribute("listeproduit", listeproduit);
		}
		if(path.equals("/index")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
		}
		
		if(path.equals("/")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
		}
		
		if(path.equals("/ListeBe")) {			
			this.getServletContext().getRequestDispatcher("/WEB-INF/BonEntree/ListeBe.jsp").forward(request,response);
		}
		
		if(lienview.equals("supprbe"))
		{
			String numbe = request.getParameter("numbe");
			donneebe.deleteBe(numbe);	
			response.sendRedirect("ListeBe");
		}
		
		if(lienview.equals("search")) {			
			String value = request.getParameter("search");
			ArrayList<BonEntree> listeBes = donneebe.rechercheBes(value);
			request.setAttribute("listebe", listeBes);
			this.getServletContext().getRequestDispatcher("/WEB-INF/BonEntree/ListeBe.jsp").forward(request,response);
		}
		
		if(lienview.equals("searchdate")) {			
			String date1 = request.getParameter("date1");
			String date2 = request.getParameter("date2");
			ArrayList<BonEntree> listeBes = donneebe.rechercheBeDate(date1,date2);
			request.setAttribute("listebe", listeBes);
			this.getServletContext().getRequestDispatcher("/WEB-INF/BonEntree/ListeBe.jsp").forward(request,response);
		}
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeleBonEntree donneebe = new ModeleBonEntree();
		
		String numbe = request.getParameter("numbe");
		String dateentree = request.getParameter("dateentree");		
		listebe = donneebe.ListeBe();
		listeproduit = produit.ListeProduit();
		
		
		if(listebe != null || listeproduit != null) {
			request.setAttribute("listebe", listebe);
			request.setAttribute("listeproduit", listeproduit);
		}
		
		BonEntree bonentree= new BonEntree();
		
		bonentree.setNumBe(numbe);
		bonentree.setDateEntree(dateentree);
	
			
		String ajout = request.getParameter("ajoutbe");
			
		if(ajout.equals("addbe"))
		{
			listebe = donneebe.validerNum(numbe);
			if(listebe.size()!=0) {
				request.setAttribute("trouve", "numero existe");
				this.getServletContext().getRequestDispatcher("/WEB-INF/BonEntree/ListeBe.jsp").forward(request,response);
			}else {
				donneebe.AjoutBe(bonentree);
				response.sendRedirect("ListeBe");
			}
			
		}
		
		if(ajout.equals("editbe"))
		{
			donneebe.updateBe(bonentree);
			listebe = donneebe.ListeBe();
			request.setAttribute("listebe",listebe);
			response.sendRedirect("ListeBe");
		}
		
	}
}

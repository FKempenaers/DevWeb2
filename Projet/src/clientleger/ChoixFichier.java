package clientleger;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChoixFichier
 */
@WebServlet("/ChoixFichier")
public class ChoixFichier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChoixFichier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("index.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("pseudo");

		if ((boolean) session.getAttribute("auth") == true) {
		
		
		
		ServletContext context = getServletContext();
		String idFichier = request.getParameter("id");
		String lienFichier = request.getParameter("lien");
		serveur.Serveur serv = (serveur.Serveur) context.getAttribute("serveur");
		System.out.println(idFichier+" "+lienFichier);
		context.setAttribute("fichier", serv.getFichierMap(idFichier,lienFichier) );
		context.setAttribute("idFichier", idFichier);
		context.setAttribute("lienFichier", lienFichier);
		getServletContext().getRequestDispatcher("/WEB-INF/tchatche.jsp").forward(request, response);
		
		}else {
			response.sendRedirect("index.html");
		}
	}

}

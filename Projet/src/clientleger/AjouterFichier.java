package clientleger;

import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class AjouterFichier
 */
@WebServlet("/AjouterFichier")
@MultipartConfig
public class AjouterFichier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjouterFichier() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/affichage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session =  request.getSession();
		String pseudo = (String) session.getAttribute("pseudo");

		
		if(!(boolean) session.getAttribute("auth")) {
			response.sendRedirect("index.html");
		}
		
		
		String description = request.getParameter("description");
		Part filePart = request.getPart("fichier"); 
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

		if(!basededonnees.GestionFichiers.ajouterFichier(pseudo, description, filePart, fileName))
			response.sendRedirect("index.html");
		
		response.sendRedirect("Connexion");
	}
}



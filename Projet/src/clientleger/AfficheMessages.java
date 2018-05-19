package clientleger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import basededonnees.GestionMessages;
import serveur.Serveur;

/**
 * Servlet implementation class AfficheMessages
 */
@WebServlet("/AfficheMessages")
public class AfficheMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GestionMessages liste;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficheMessages() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) Affiche les messages du chat
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idFichier = (String) request.getServletContext().getAttribute("idFichier");
		serveur.Serveur serv = (Serveur) request.getServletContext().getAttribute("serveur");
		liste = serv.getChatMap(idFichier);

		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("pseudo");
		if (pseudo == null)
			response.sendRedirect("Init");

		response.setContentType("text/event-stream");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();
		if (liste != null)
			writer.write("data: " + liste.afficher() + "\n\n");
		writer.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

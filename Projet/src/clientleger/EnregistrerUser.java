package clientleger;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EnregistrerUser
 */
@WebServlet("/EnregistrerUser")
public class EnregistrerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnregistrerUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) Affiche le formulaire permettant au nouvel utilisateur de
	 *      s'enregistrer
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletContext().getRequestDispatcher("/WEB-INF/enregistrerUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) Enregistre le nouvel utilisateur dans la base de donnees et le
	 *      renvoie vers la page d'accueil
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Ajouter code pour mettre user dans bdd
		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");

		try {
			basededonnees.Request.addUser(pseudo, mdp);

			response.sendRedirect("index.html");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

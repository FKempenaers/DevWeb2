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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletContext().getRequestDispatcher("/WEB-INF/enregistrerUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Ajouter code pour mettre user dans bdd
		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		
		try {
			boolean addUser = basededonnees.Request.addUser(pseudo, mdp);
			
			if (addUser) {
				
				HttpSession session =  request.getSession();
				session.setAttribute("pseudo", pseudo);
				session.setAttribute("auth", true);
				request.getServletContext().getRequestDispatcher("/WEB-INF/accueilUser.jsp").forward(request, response);
				/*
				 * L'utilisateur a ajouté son pseudo et son mdp dans la base de données
				 */
			}
			else {
				response.sendRedirect("index.html");
				/*
				 * Il y a eu un problème lors de l''inscription de l'utilisateur
				 */
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

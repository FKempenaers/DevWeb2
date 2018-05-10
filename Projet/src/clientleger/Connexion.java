package clientleger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//ajouter code pour verifier username + pass et set la session
		
		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		
		try {
			boolean check_user = basededonnees.Request.check_user(pseudo, mdp);
			
			if (check_user) {
				/*
				 * L'utilisateur a un compte enregistré sur la base de données et les infos correspondent.
				 * Il faut le rediriger vers l'accueil.
				 */
				
				HttpSession session =  request.getSession();
				session.setAttribute("pseudo", pseudo);
				session.setAttribute("auth", true);
				
				/*
				Pecho la liste des fichiers du user et mettre dans une var
				comme ca dans la jsp on recup et on fait des boutons avec pour que l'user puisse ouvrir les fichiers
				*/
				
				ArrayList<String[]> files = basededonnees.Request.user_file("tao");
				
				getServletContext().setAttribute("files", files);
				
				request.getServletContext().getRequestDispatcher("/WEB-INF/accueilUser.jsp").forward(request, response);				
				
				
			}
			else {
				response.sendRedirect("index.html");
				/*
				 * L'utilisateur n'a pas de données correspondantes dans la base de données.
				 * Il est donc redirigé vers une page d'inscription.
				 */
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		//doGet(request, response);
	}

}

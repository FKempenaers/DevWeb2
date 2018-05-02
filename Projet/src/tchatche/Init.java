package tchatche;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Init
 */

public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Init() {
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
		
		//response.setContentType("text/html; charset=UTF-8");
		//PrintWriter out = response.getWriter();
		
		//out.println(request.getParameter("pseudo"));
		this.init();
		HttpSession session = request.getSession();
		String pseudo = request.getParameter("pseudo");
		session.setAttribute("pseudo", pseudo);
		
		ServletContext context = this.getServletContext();
		Hashtable<String, Integer> listeUsers = (Hashtable<String, Integer>)context.getAttribute("listeUsers");
		if (!listeUsers.containsValue(pseudo)) {
			listeUsers.put(pseudo,0);
		}
		context.setAttribute("listeUsers", listeUsers);
		
		getServletContext().getRequestDispatcher("/WEB-INF/tchatche.jsp").forward(request, response);
	}

	
	public void init() {
		//exercice 5
		ServletContext context = this.getServletContext();
		Hashtable<String, Integer> listeUsers = new Hashtable<String, Integer>();
		// l'entier sert à stocker le nombre de messages envoyé par un meme utilisateur
		
		ArrayList<Message> listeMessages = new ArrayList<Message>();
		
		
		context.setAttribute("listeUsers", listeUsers);
		context.setAttribute("listeMessages", listeMessages);
		
		// exo6
				context.setAttribute("nbRefresh", 0);
				context.setAttribute("tailleDonnees", 0);
				// fin exo6
		
	}

}

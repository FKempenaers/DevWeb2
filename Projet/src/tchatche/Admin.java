package tchatche;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Admin
 */

public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//exo6
		if (request.getParameter("sauvegarde") != null) {
			response.setContentType("text/plain");
		     response.setHeader("Content-Disposition","attachment;filename=sauvegarde_chat.txt");
				PrintWriter out = response.getWriter();
				ServletContext context = this.getServletContext();
				ArrayList<Message> listeMessages = (ArrayList<Message>)context.getAttribute("listeMessages");
				for (Message m : listeMessages) {
					System.out.println("toto");
					out.println("["+m.getDate()+"] "+m.getUser()+" : "+m.getMessage()+"<br />");
				}
		     
		} else {
			ServletContext context = this.getServletContext();
			ArrayList<Message> listeMessages = (ArrayList<Message>)context.getAttribute("listeMessages");
			Hashtable<String, Integer> listeUsers = (Hashtable<String, Integer>)context.getAttribute("listeUsers");
			PrintWriter out = response.getWriter();
			out.println("<h1>Liste des utilisateurs connectés</h1>");
			out.println("<ul>");
			Enumeration users = listeUsers.keys();
			while (users.hasMoreElements()) {
				String user = (String)users.nextElement();
				out.println("<li>"+user+" ("+listeUsers.get(user)+" messages)");
			}
			out.println("</ul>");
			
			out.println("<a href=\"Admin?sauvegarde\">Sauvegarde</a>");
			//exo7
			out.println("Nombre de refresh : "+context.getAttribute("nbRefresh")+" (taille "+context.getAttribute("tailleDonnees")+")");
		}
		
		
		// exo5 
		/*
		ServletContext context = this.getServletContext();
		ArrayList<Message> listeMessages = (ArrayList<Message>)context.getAttribute("listeMessages");
		Hashtable<String, Integer> listeUsers = (Hashtable<String, Integer>)context.getAttribute("listeUsers");
		PrintWriter out = response.getWriter();
		out.println("<h1>Liste des utilisateurs connectés</h1>");
		out.println("<ul>");
		Enumeration users = listeUsers.keys();
		while (users.hasMoreElements()) {
			String user = (String)users.nextElement();
			out.println("<li>"+user+" ("+listeUsers.get(user)+" messages)");
		}
		out.println("</ul>");
		*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}

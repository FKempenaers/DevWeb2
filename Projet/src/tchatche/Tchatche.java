package tchatche;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Tchatche
 */

public class Tchatche extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Message> listeMessages;
	long lastModified;
	
	// exercice 4
	int timeRefresh = 100;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tchatche() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
    	String param = this.getInitParameter("time_refresh");
        try {
        	timeRefresh = Integer.parseInt(param);
        } catch(NumberFormatException e) {
          System.out.println(e);
        }
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// raffraichir exercice 4
		response.setHeader("Refresh", ""+timeRefresh);
		// exo7 part2
		response.setHeader("Last-Modified", ""+lastModified );
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("pseudo");
		
		getServletContext().getRequestDispatcher("/WEB-INF/tchatche.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		// raffraichir
			response.setHeader("Refresh", ""+timeRefresh);
			// exo7 part2
			response.setHeader("Last-Modified", ""+lastModified );
		
		PrintWriter out = response.getWriter();
		String message = request.getParameter("message");
	    HttpSession session = request.getSession();
	    String pseudo = (String)session.getAttribute("pseudo");
		
	    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:SS", Locale.FRANCE);
	    newMessage(pseudo, message, dateFormat.format(new Date()));
	    
	    long lastModifiedClient = -1;
		if (request.getHeader("If-Modified-Since")!=null) {
			lastModifiedClient = Long.parseLong(request.getHeader("If-Modified-Since"));
		}
	    
		if (lastModifiedClient<lastModified) {
			getServletContext().getRequestDispatcher("/WEB-INF/tchatche.jsp").forward(request, response);
	    } else {
	    	response.setStatus(304);
	    }
	}
	
	private void affEntete(PrintWriter out, String pseudo) {
		String texte = "<h1>Bonjour "+pseudo+" !</h1>";
		ServletContext context = this.getServletContext();
		Hashtable<String, Integer> listeUsers = (Hashtable<String, Integer>)context.getAttribute("listeUsers");
		texte += "Nombre d'utilisateurs connectés : "+listeUsers.size()+"<hr />";
		
		//exo7
		int tailleDonnees = Integer.parseInt(""+context.getAttribute("tailleDonnees")) +1;
		tailleDonnees += texte.length();
		context.setAttribute("tailleDonnees", tailleDonnees);
		//fin exo7
		out.println(texte);
	}
	
	
	private void affMessages(PrintWriter out) {
		
		ServletContext context = this.getServletContext();
		
		//exo7
		int nbRefresh = Integer.parseInt(""+context.getAttribute("nbRefresh")) +1;
		int tailleDonnees = Integer.parseInt(""+context.getAttribute("tailleDonnees")) +1;
		//System.out.println("nbrefresh = "+nbRefresh+"-"+context.getAttribute("nbRefresh"));
		context.setAttribute("nbRefresh", nbRefresh);
		//fin exo7
		ArrayList<Message> listeMessages = (ArrayList<Message>)context.getAttribute("listeMessages");
		String texte = "";
		if (listeMessages.size()!=0) {
			for (Message m : listeMessages) {
				String s = "["+m.getDate()+"] "+m.getUser()+" : "+m.getMessage()+"<br />";
				texte += s;
			}
		}
		//exo7
		tailleDonnees += texte.length();
		context.setAttribute("tailleDonnees", tailleDonnees);
		// fin exo7
		out.println(texte);
	}
	
	private void newMessage(String pseudo, String message, String date) {
		// exo5
		ServletContext context = this.getServletContext();
		
		Hashtable<String, Integer> listeUsers = (Hashtable<String, Integer>)context.getAttribute("listeUsers");
		int nb = 0;
		nb = listeUsers.get(pseudo); // nb sert à differencier les messages d'un meme utilisateur,
		listeUsers.put(pseudo,nb+1);
		ArrayList<Message> listeMessages = (ArrayList<Message>)context.getAttribute("listeMessages");
		listeMessages.add(new Message(message,pseudo,date));
		
		// enregistre l'heure du message
		lastModified = System.currentTimeMillis();
		
		context.setAttribute("listeMessages", listeMessages);
		context.setAttribute("listeUsers", listeUsers);
	}
	

}

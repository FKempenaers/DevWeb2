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

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tchatche() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		 String pseudo = (String)session.getAttribute("pseudo");
		
		
		getServletContext().getRequestDispatcher("/WEB-INF/tchatche.jsp").forward(request, response);
		//response.sendRedirect("WEB-INF/tchatche.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		

		String message = request.getParameter("message");
	    HttpSession session = request.getSession();
	    String pseudo = (String)session.getAttribute("pseudo");
		
	    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:SS", Locale.FRANCE);
	    newMessage(pseudo, message, dateFormat.format(new Date()));
	    
			getServletContext().getRequestDispatcher("/WEB-INF/tchatche.jsp").forward(request, response);
	}
	
	private void newMessage(String pseudo, String message, String date) {
		// exo5
		ServletContext context = this.getServletContext();
		GestionMessages.get().add(message,pseudo);
		
		context.setAttribute("listeMessages", GestionMessages.get());
	}
	

}

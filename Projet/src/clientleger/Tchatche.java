package clientleger;

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

import basededonnees.GestionMessages;
import serveur.Serveur;

/**
 * Servlet implementation class Tchatche
 */
@WebServlet("/Tchatche")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		if ((boolean) session.getAttribute("auth") == true) {
			getServletContext().getRequestDispatcher("/WEB-INF/tchatche.jsp").forward(request, response);
		} else
			response.sendRedirect("index.html");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		if ((boolean) session.getAttribute("auth") == true) {

			String message = request.getParameter("message");
			String pseudo = (String) session.getAttribute("pseudo");

			if (pseudo == null || message == null || request.getServletContext().getAttribute("liste") == null)
				response.sendRedirect("Init");
			else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:SS", Locale.FRANCE);
				newMessage(pseudo, message, dateFormat.format(new Date()));

				getServletContext().getRequestDispatcher("/WEB-INF/tchatche.jsp").forward(request, response);
			}
		} else
			response.sendRedirect("index.html");
	}

	private void newMessage(String pseudo, String message, String date) {
		ServletContext context = this.getServletContext();
		String idFichier = (String) context.getAttribute("idFichier");
		Serveur serv = (Serveur) context.getAttribute("serveur");
		GestionMessages liste = serv.getChatMap(idFichier);
		if (pseudo != null && message != null && liste != null)
			liste.add(message, pseudo);
	}

}

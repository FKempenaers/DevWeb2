package clientleger;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tchatche.GestionMessages;

/**
 * Servlet implementation class AfficheFichier
 */
@WebServlet("/AfficheFichier")
public class AfficheFichier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String fichier;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficheFichier() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		fichier = (String) request.getServletContext().getAttribute("fichier");

		// response.setContentType("text/event-stream");
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();
		if (fichier != null)
			// writer.write("data: "+ fichier +"\n\n");
			writer.write(fichier);
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context = this.getServletContext();
		fichier = request.getParameter("fichier");
		context.setAttribute("fichier", fichier);
		serveur.Serveur serv = (serveur.Serveur) request.getServletContext().getAttribute("serveur");
		String idFichier = (String) context.getAttribute("idFichier");
		String lienFichier = (String) context.getAttribute("lienFichier");
		serv.setFichierMap(idFichier, fichier, lienFichier);
		System.out.println(fichier);
		/*
		 * Manque le code pour remettre le fichier dans la map du serv et dans le
		 * filesystem du coup realtime buggé atm parce que les modifs sont pas mises au
		 * bon endroit
		 */
	}

}

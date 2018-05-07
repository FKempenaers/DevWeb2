package clientleger;

import java.io.IOException;
import java.io.PrintWriter;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        fichier = (String) request.getServletContext().getAttribute("fichier");
        
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("pseudo");
		if(pseudo == null)
			response.sendRedirect("Init");

		response.setContentType("text/event-stream");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();
		if(fichier != null)
		writer.write("data: "+ fichier +"\n\n");
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

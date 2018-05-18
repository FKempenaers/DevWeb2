package clientleger;

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
@WebServlet("/Init")
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static serveur.Serveur serv = new serveur.Serveur();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Init() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.init();
		response.sendRedirect("index.html");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.init();;

		response.sendRedirect("index.html");
	}

	
	public void init() {
		ServletContext context = this.getServletContext();
		context.setAttribute("serveur", serv);
		
	}

}

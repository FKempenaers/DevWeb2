package clientleger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class AjouterFichier
 */
@WebServlet("/AjouterFichier")
@MultipartConfig
public class AjouterFichier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjouterFichier() {
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
		getServletContext().getRequestDispatcher("/WEB-INF/affichageFichier.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//PrintWriter out = response.getWriter();
		/* Ajouter fonction pour mettre fichier dans bdd */
		//String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
		Part filePart = request.getPart("fichier"); // Retrieves <input type="file" name="file">
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		System.out.println(fileName);
		InputStream fileContent = filePart.getInputStream();
		File uploads = new File(fileName);
		Files.copy(fileContent, uploads.toPath(),StandardCopyOption.REPLACE_EXISTING);

		try {
			if (fileContent != null) {
				InputStreamReader isr = new InputStreamReader(fileContent);
				BufferedReader reader = new BufferedReader(isr);
				int n = 0;
				String word = "";
				while ((word = reader.readLine()) != null) {
					n = Integer.parseInt(word);
			//		out.println(n);
				}
				
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(uploads)));
				
				String fichier = "";
				String line = br.readLine();
				while(line!=null){
				// out.println(line);
				 fichier += line;
				 line = br.readLine();
				}

				serveur.Serveur serv = (serveur.Serveur) request.getServletContext().getAttribute("serveur");
				serv.setfichier(fichier);
				
				getServletContext().setAttribute("fichier", fichier);
				getServletContext().getRequestDispatcher("/WEB-INF/affichageFichier.jsp").forward(request, response);
				//response.sendRedirect("/AffichageFichier");
			}
		} finally {
			//out.close();
		}
	}

}

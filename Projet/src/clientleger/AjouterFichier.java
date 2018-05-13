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
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
		getServletContext().getRequestDispatcher("/WEB-INF/affichage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session =  request.getSession();
		String pseudo = (String) session.getAttribute("pseudo");

		
		if(!(boolean) session.getAttribute("auth")) {
			response.sendRedirect("index.html");
		}
		
		
		String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
		Part filePart = request.getPart("fichier"); // Retrieves <input type="file" name="file">
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		int ajout_fichier=-1;

		InputStream fileContent = filePart.getInputStream();
		new File("uploads/"+pseudo+"/").mkdirs();
		File uploads = new File("uploads/"+pseudo+"/"+fileName+"/");
		Files.copy(fileContent, uploads.toPath(),StandardCopyOption.REPLACE_EXISTING);

		try {
			ajout_fichier = basededonnees.Request.addFile(pseudo, fileName, uploads.toString());
			if(ajout_fichier == -1)
			response.sendRedirect("index.html");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			if (fileContent != null) {
//				InputStreamReader isr = new InputStreamReader(fileContent);
//				BufferedReader reader = new BufferedReader(isr);
//				int n = 0;
//				String word = "";
//				while ((word = reader.readLine()) != null) {
//					n = Integer.parseInt(word);
//
//				}
//				
//				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(uploads)));
//				
//				String fichier = "";
//				String line = br.readLine();
//				while(line!=null){
//				// out.println(line);
//				 fichier += line;
//				 line = br.readLine();
//				}
//
////				serveur.Serveur serv = (serveur.Serveur) request.getServletContext().getAttribute("serveur");
////				serv.setfichier(fichier);
////				
////				getServletContext().setAttribute("fichier", fichier);
////				getServletContext().getRequestDispatcher("/WEB-INF/tchatche.jsp").forward(request, response);
//				
//				ArrayList<String[]> files = basededonnees.Request.user_file(pseudo);
//				
//				/*ne pas s'embeter et revenir à la page d'accueil de l'user après upload */
//				ServletContext context = getServletContext();
//				String idFichier = String.valueOf(ajout_fichier);
//				String lienFichier = "uploads/"+pseudo+"/"+fileName+"/";
//				serveur.Serveur serv = (serveur.Serveur) context.getAttribute("serveur");
//				context.setAttribute("fichier", serv.getFichierMap(idFichier,lienFichier) );
//				context.setAttribute("idFichier", Integer.parseInt(idFichier));
//				response.sendRedirect("Tchatche");
//				
//				
//				
//			} 
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{}
		
		response.sendRedirect("Connexion");
	}
}



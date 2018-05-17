package basededonnees;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

import javax.servlet.http.Part;

public class GestionFichiers {
	
	public static boolean ajouterFichier(String pseudo, String description, Part filePart, String fileName) throws IOException {
		int ajout_fichier=-1;

		InputStream fileContent = filePart.getInputStream();
		new File("uploads/"+pseudo+"/").mkdirs();
		File uploads = new File("uploads/"+pseudo+"/"+fileName+"/");
		Files.copy(fileContent, uploads.toPath(),StandardCopyOption.REPLACE_EXISTING);

		try {
			ajout_fichier = basededonnees.Request.addFile(pseudo, fileName, uploads.toString());
			if(ajout_fichier == -1)
			return false;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	
}

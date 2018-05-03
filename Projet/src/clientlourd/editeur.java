package clientlourd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class editeur extends JFrame implements ActionListener {

   private static final long serialVersionUID = 1L;

   private RSyntaxTextArea textArea;
   private JTextField searchField;
   private JCheckBox regexCB;
   private JCheckBox matchCaseCB;

   public editeur(String doc,String style) {

      JPanel cp = new JPanel(new BorderLayout());

      textArea = new RSyntaxTextArea(doc,45, 100);
      textArea.setSyntaxEditingStyle(style);
      textArea.setCodeFoldingEnabled(true);
      RTextScrollPane sp = new RTextScrollPane(textArea);
      cp.add(sp);

      // Create a toolbar with searching options.
/*      JToolBar toolBar = new JToolBar();
      searchField = new JTextField(30);
      toolBar.add(searchField);
      final JButton nextButton = new JButton("Find Next");
      nextButton.setActionCommand("FindNext");
      nextButton.addActionListener(this);
      toolBar.add(nextButton);
      searchField.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            nextButton.doClick(0);
         }
      });
      JButton prevButton = new JButton("Find Previous");
      prevButton.setActionCommand("FindPrev");
      prevButton.addActionListener(this);
      toolBar.add(prevButton);
      regexCB = new JCheckBox("Regex");
      toolBar.add(regexCB);
      matchCaseCB = new JCheckBox("Match Case");
      toolBar.add(matchCaseCB);
      cp.add(toolBar, BorderLayout.NORTH);*/

      setContentPane(cp);
      setTitle("Editeur");
      //setDefaultCloseOperation(EXIT_ON_CLOSE);
      pack();
      setLocationRelativeTo(null);

   }
   public RSyntaxTextArea gettextarea() {
	   return textArea;
   }

   public void actionPerformed(ActionEvent e) {

      // "FindNext" => search forward, "FindPrev" => search backward
      String command = e.getActionCommand();
      boolean forward = "FindNext".equals(command);

      // Create an object defining our search parameters.
      SearchContext context = new SearchContext();
      String text = searchField.getText();
      if (text.length() == 0) {
         return;
      }
      context.setSearchFor(text);
      context.setMatchCase(matchCaseCB.isSelected());
      context.setRegularExpression(regexCB.isSelected());
      context.setSearchForward(forward);
      context.setWholeWord(false);

      boolean found = SearchEngine.find(textArea, context).wasFound();
      if (!found) {
         JOptionPane.showMessageDialog(this, "Text not found");
      }

   }

}
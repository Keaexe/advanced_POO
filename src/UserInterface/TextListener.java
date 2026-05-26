package UserInterface;

import Exceptions.DataAccessException;
import Interfaces.IController;
import Model.Referent;
import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TextListener implements ActionListener {

    IController controller;
    DefaultListModel<String> listModel;
    JTextField searchBar;
    CheckBoxListener checkBoxListener;

    public TextListener(
        IController controller,
        DefaultListModel<String> listModel,
        JTextField searchBar,
        CheckBoxListener checkBoxListener
    ) {
        this.controller = controller;
        this.listModel = listModel;
        this.searchBar = searchBar;
        this.checkBoxListener = checkBoxListener;
    }

    public void actionPerformed(ActionEvent event) {
        try {
            listModel.clear();
            ArrayList<Referent> referents;
            if (checkBoxListener.isSelected()) {
                referents = new ArrayList<>();
                referents.add(
                    controller.getReferentById(
                        Integer.parseInt(searchBar.getText())
                    )
                );
            } else {
                referents = controller.getReferentsByDesignation(
                    searchBar.getText()
                );
            }
            for (Referent referent : referents) {
                listModel.addElement(Utils.Concatenation.concatenate(referent));
            }
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(
                null,
                "Could not perform search\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                null,
                "Please enter an ID (numeric)\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

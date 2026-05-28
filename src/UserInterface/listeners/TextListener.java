package UserInterface.listeners;

import Exceptions.DataAccessException;
import Interfaces.IController;
import Models.Referent;

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

    @Override
    public void actionPerformed(ActionEvent event) {
        try {

            String searchText = searchBar.getText();

            if (searchText == null || searchText.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please enter a search value.",
                        "Missing search value",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            searchText = searchText.trim();

            listModel.clear();

            ArrayList<Referent> referents;

            if (checkBoxListener.isSelected()) {
                referents = new ArrayList<>();

                int referentId = Integer.parseInt(searchText);

                Referent referent = controller.getReferentById(referentId);

                if (referent != null) {
                    referents.add(referent);
                }
            } else {
                referents = controller.getReferentsByDesignation(searchText);
            }

            if (referents == null || referents.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "No referent found.",
                        "No result",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }

            for (Referent referent : referents) {
                if (referent != null) {
                    listModel.addElement(
                            referent.toString() +
                                    ", " +
                                    controller
                                            .getSchoolsByID(referent.getIdSchoolOfThought())
                                            .getName()
                    );
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter an ID using numbers only.",
                    "Invalid ID",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Could not perform search\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

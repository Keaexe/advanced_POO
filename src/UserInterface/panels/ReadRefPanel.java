package UserInterface.panels;

import Exceptions.DataAccessException;
import Interfaces.IController;
import Models.Referent;
import UserInterface.listeners.CheckBoxListener;
import UserInterface.listeners.TextListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class ReadRefPanel extends JPanel {

    private JLabel title, searchLabel, boxLabel;
    private JTextField searchBar;
    private JCheckBox byIdBox;
    private CheckBoxListener checkBoxListener;
    private TextListener textListener;
    private JList<String> results;
    private DefaultListModel<String> listModel;
    private JButton button;
    private ButtonListener buttonListener;
    private String mode;

    public ReadRefPanel(IController controller, String mode)
        throws DataAccessException {
        this.mode = mode;

        this.setLayout(new BorderLayout());
        title = new JLabel("Here is the place to " + mode + "referents");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(Font.SERIF, Font.ITALIC, 28));
        this.add(title, BorderLayout.NORTH);

        var wrapper = new JPanel(new GridBagLayout());
        var searchGrid = new JPanel(new GridLayout(3, 2));
        searchLabel = new JLabel("Search for referent by designation");
        searchGrid.add(searchLabel);
        boxLabel = new JLabel("Check this box to search by ID");
        searchGrid.add(boxLabel);

        listModel = new DefaultListModel<>();

        searchBar = new JTextField();

        byIdBox = new JCheckBox("Search id");
        checkBoxListener = new CheckBoxListener(searchLabel, boxLabel);
        byIdBox.addItemListener(checkBoxListener);

        textListener = new TextListener(
                controller,
                listModel,
                searchBar,
                checkBoxListener
        );
        searchBar.addActionListener(textListener);

        searchGrid.add(searchBar);
        searchGrid.add(byIdBox);

        var referents = controller.getAllReferent();
        for (Referent referent : referents) {
            listModel.addElement(
                referent.toString() +
                    ", " +
                    controller
                        .getSchoolsByID(referent.getIdSchoolOfThought())
                        .getName()
            );
        }
        results = new JList<>(listModel);
        results.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (mode.compareTo("view") != 0) {
            button = new JButton(mode + " selected");
            buttonListener = new ButtonListener(controller, results);
            button.addActionListener(buttonListener);
            searchGrid.add(button);
        }
        searchGrid.setPreferredSize(new Dimension(600, 80));
        wrapper.add(searchGrid);

        results.setPreferredSize(new Dimension(600, 100));
        wrapper.add(results);

        this.add(wrapper, BorderLayout.CENTER);
    }

    public ReadRefPanel(IController controller) throws DataAccessException {
        this(controller, "view");
    }

    private class ButtonListener implements ActionListener {

        private IController controller;
        private JList<String> list;

        public ButtonListener(IController controller, JList<String> list) {
            this.controller = controller;
            this.list = list;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                String element = list.getSelectedValue();

                if (element == null) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please select a referent first.",
                            "No referent selected",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                String string = "";
                int i = 0;
                while (element.charAt(i) != ')') {
                    string += element.charAt(i);
                    i++;
                }
                if (mode.compareTo("delete") == 0) {
                    controller.deleteReferent(Integer.parseInt(string));
                    JOptionPane.showMessageDialog(
                        null,
                        "This referent has been deleted",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    controller.displayDeleteReferent();
                } else {
                    controller.displayUpdateReferent(
                        controller.getReferentById(Integer.parseInt(string))
                    );
                }
            } catch (DataAccessException e) {
                JOptionPane.showMessageDialog(
                    null,
                    "Could not delete the referent\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}

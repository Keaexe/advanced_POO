package UserInterface;

import Exceptions.DataAccessException;
import Interfaces.IController;
import Model.Referent;
import Utils.Concatenation;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class ReadRefPanel extends JPanel {

    private IController controller;
    private JLabel title, searchLabel, boxLabel;
    private JTextField searchBar;
    private JCheckBox byIdBox;
    private CheckBoxListener checkBoxListener;
    private TextListener textListener;
    private JList<String> results;
    private DefaultListModel<String> listModel;

    public ReadRefPanel(IController controller) throws DataAccessException {
        this.setLayout(new BorderLayout());
        title = new JLabel("Here is the place to read referents");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(Font.SERIF, Font.ITALIC, 28));
        this.add(title, BorderLayout.NORTH);

        var wrapper = new JPanel(new GridBagLayout());
        var searchGrid = new JPanel(new GridLayout(2, 2));
        searchLabel = new JLabel("Search for referent by designation");
        searchGrid.add(searchLabel);
        boxLabel = new JLabel("Check this box to search by ID");
        searchGrid.add(boxLabel);
        searchBar = new JTextField();
        textListener = new TextListener(controller);
        searchBar.addActionListener(textListener);
        searchGrid.add(searchBar);
        byIdBox = new JCheckBox("Search id");
        checkBoxListener = new CheckBoxListener();
        byIdBox.addItemListener(checkBoxListener);
        searchGrid.add(byIdBox);

        searchGrid.setPreferredSize(new Dimension(600, 50));
        wrapper.add(searchGrid);

        listModel = new DefaultListModel<>();
        var referents = controller.getAllReferent();
        for (Referent referent : referents) {
            listModel.addElement(Concatenation.concatenate(referent));
        }
        results = new JList<>(listModel);
        results.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        results.setPreferredSize(new Dimension(600, 100));
        wrapper.add(results);

        this.add(wrapper, BorderLayout.CENTER);
    }

    private class CheckBoxListener implements ItemListener {

        private boolean isSelected;

        public CheckBoxListener() {
            this.isSelected = false;
        }

        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                searchLabel.setText("Search for referent by id");
                boxLabel.setText("Uncheck this box to search by designation");
                isSelected = true;
            } else {
                searchLabel.setText("Search for referent by designation");
                boxLabel.setText("Check this box to search by ID");
                isSelected = false;
            }
        }

        public boolean isSelected() {
            return isSelected;
        }
    }

    private class TextListener implements ActionListener {

        IController controller;

        public TextListener(IController controller) {
            this.controller = controller;
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
                    listModel.addElement(
                        Utils.Concatenation.concatenate(referent)
                    );
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
}

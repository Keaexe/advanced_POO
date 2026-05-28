package UserInterface.panels;

import Exceptions.DataAccessException;
import Interfaces.IController;
import Models.Referent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReadItemsPanel extends JPanel {

    private JLabel title;
    private JLabel referentLabel;

    private JComboBox<Referent> referentComboBox;
    private JButton searchButton;

    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public ReadItemsPanel(IController controller) throws DataAccessException {
        this.setLayout(new BorderLayout());

        title = new JLabel("Search items by referent");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(Font.SERIF, Font.ITALIC, 28));
        this.add(title, BorderLayout.NORTH);

        JPanel wrapper = new JPanel(new BorderLayout());

        JPanel searchPanel = new JPanel(new FlowLayout());

        referentLabel = new JLabel("Referent : ");
        searchPanel.add(referentLabel);

        referentComboBox = new JComboBox<>();
        fillReferentComboBox(controller);
        searchPanel.add(referentComboBox);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonListener(controller));
        searchPanel.add(searchButton);

        wrapper.add(searchPanel, BorderLayout.NORTH);

        String[] columnNames = {
                "Item name",
                "Price ex VAT",
                "Quantity",
                "Price at the time",
                "Category"
        };

        tableModel = new DefaultTableModel(columnNames, 0);
        resultsTable = new JTable(tableModel);
        resultsTable.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(resultsTable);
        wrapper.add(scrollPane, BorderLayout.CENTER);

        this.add(wrapper, BorderLayout.CENTER);
    }

    private void fillReferentComboBox(IController controller)
            throws DataAccessException {
        ArrayList<Referent> referents = controller.getAllReferent();

        for (Referent referent : referents) {
            referentComboBox.addItem(referent);
        }
    }

    private void fillTable(ArrayList<Object[]> rows) {
        tableModel.setRowCount(0);

        for (Object[] row : rows) {
            tableModel.addRow(row);
        }
    }

    private class SearchButtonListener implements ActionListener {

        private IController controller;

        public SearchButtonListener(IController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                Referent selectedReferent =
                        (Referent) referentComboBox.getSelectedItem();

                if (selectedReferent == null) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please select a referent.",
                            "No referent selected",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                ArrayList<Object[]> rows =
                        controller.getItemSearchResultsByReferentId(selectedReferent.getId());

                fillTable(rows);

                if (rows.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "No sold item found for this referent.",
                            "No result",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            } catch (DataAccessException exception) {
                JOptionPane.showMessageDialog(
                        null,
                        "Could not search items.\n" + exception.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
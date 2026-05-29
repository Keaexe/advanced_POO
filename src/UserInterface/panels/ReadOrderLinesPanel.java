package UserInterface.panels;

import Exceptions.DataAccessException;
import Interfaces.IController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReadOrderLinesPanel extends JPanel {

    private JLabel title;
    private JLabel clientLabel;

    private JComboBox<ComboBoxItem> clientComboBox;
    private JButton searchButton;

    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public ReadOrderLinesPanel(IController controller) throws DataAccessException {
        this.setLayout(new BorderLayout());

        title = new JLabel("Search order lines by client");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(Font.SERIF, Font.ITALIC, 28));
        this.add(title, BorderLayout.NORTH);

        JPanel wrapper = new JPanel(new BorderLayout());

        JPanel searchPanel = new JPanel(new FlowLayout());

        clientLabel = new JLabel("Client : ");
        searchPanel.add(clientLabel);

        clientComboBox = new JComboBox<>();
        fillClientComboBox(controller);
        searchPanel.add(clientComboBox);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonListener(controller));
        searchPanel.add(searchButton);

        wrapper.add(searchPanel, BorderLayout.NORTH);

        String[] columnNames = {
                "Order id",
                "Creation time",
                "Item",
                "Quantity",
                "Price at the time"
        };

        tableModel = new DefaultTableModel(columnNames, 0);
        resultsTable = new JTable(tableModel);
        resultsTable.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(resultsTable);
        wrapper.add(scrollPane, BorderLayout.CENTER);

        this.add(wrapper, BorderLayout.CENTER);
    }

    private void fillClientComboBox(IController controller)
            throws DataAccessException {
        ArrayList<Object[]> clients = controller.getAllClientsForCombo();

        for (Object[] client : clients) {
            Integer id = (Integer) client[0];
            String firstName = (String) client[1];
            String lastName = (String) client[2];

            clientComboBox.addItem(
                    new ComboBoxItem(
                            id,
                            firstName + " " + lastName + " (" + id + ")"
                    )
            );
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
                ComboBoxItem selectedClient =
                        (ComboBoxItem) clientComboBox.getSelectedItem();

                if (selectedClient == null) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please select a client.",
                            "No client selected",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                ArrayList<Object[]> rows =
                        controller.getOrderLinesByClientId(selectedClient.getId());

                fillTable(rows);

                if (rows.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "No order line found for this client.",
                            "No result",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            } catch (DataAccessException exception) {
                JOptionPane.showMessageDialog(
                        null,
                        "Could not search order lines.\n" + exception.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private class ComboBoxItem {

        private Integer id;
        private String label;

        public ComboBoxItem(Integer id, String label) {
            this.id = id;
            this.label = label;
        }

        public Integer getId() {
            return id;
        }

        @Override
        public String toString() {
            return label;
        }
    }
}
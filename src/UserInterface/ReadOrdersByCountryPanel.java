package UserInterface;

import Exceptions.DataAccessException;
import Interfaces.IController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class ReadOrdersByCountryPanel extends JPanel {

    private JLabel title;
    private JLabel countryLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;

    private JComboBox<String> countryComboBox;
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;
    private JButton searchButton;

    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public ReadOrdersByCountryPanel(IController controller)
            throws DataAccessException {
        this.setLayout(new BorderLayout());

        title = new JLabel("Search orders by country and dates");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(Font.SERIF, Font.ITALIC, 28));
        this.add(title, BorderLayout.NORTH);

        JPanel wrapper = new JPanel(new BorderLayout());

        JPanel searchPanel = new JPanel(new FlowLayout());

        countryLabel = new JLabel("Country : ");
        searchPanel.add(countryLabel);

        countryComboBox = new JComboBox<>();
        fillCountryComboBox(controller);
        searchPanel.add(countryComboBox);

        startDateLabel = new JLabel("Start date : ");
        searchPanel.add(startDateLabel);

        startDateSpinner = createDateSpinner();
        searchPanel.add(startDateSpinner);

        endDateLabel = new JLabel("End date : ");
        searchPanel.add(endDateLabel);

        endDateSpinner = createDateSpinner();
        searchPanel.add(endDateSpinner);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonListener(controller));
        searchPanel.add(searchButton);

        wrapper.add(searchPanel, BorderLayout.NORTH);

        String[] columnNames = {
                "Order id",
                "Creation time",
                "Client first name",
                "Client last name",
                "Number",
                "Street",
                "Locality",
                "Zip code"
        };

        tableModel = new DefaultTableModel(columnNames, 0);
        resultsTable = new JTable(tableModel);
        resultsTable.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(resultsTable);
        wrapper.add(scrollPane, BorderLayout.CENTER);

        this.add(wrapper, BorderLayout.CENTER);
    }

    private JSpinner createDateSpinner() {
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(dateModel);

        JSpinner.DateEditor editor =
                new JSpinner.DateEditor(spinner, "yyyy-MM-dd");
        spinner.setEditor(editor);

        return spinner;
    }

    private void fillCountryComboBox(IController controller)
            throws DataAccessException {
        ArrayList<String> countries = controller.getAllCountryNames();

        for (String country : countries) {
            countryComboBox.addItem(country);
        }
    }

    private LocalDate getLocalDateFromSpinner(JSpinner spinner) {
        Date date = (Date) spinner.getValue();

        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
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
                String selectedCountry =
                        (String) countryComboBox.getSelectedItem();

                if (selectedCountry == null) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please select a country.",
                            "No country selected",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                LocalDate startDate = getLocalDateFromSpinner(startDateSpinner);
                LocalDate endDate = getLocalDateFromSpinner(endDateSpinner);

                if (startDate.isAfter(endDate)) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Start date cannot be after end date.",
                            "Invalid dates",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                ArrayList<Object[]> rows =
                        controller.getOrdersByCountryAndDates(
                                selectedCountry,
                                startDate,
                                endDate
                        );

                fillTable(rows);

                if (rows.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "No order found for this country and date range.",
                            "No result",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            } catch (DataAccessException exception) {
                JOptionPane.showMessageDialog(
                        null,
                        "Could not search orders.\n" + exception.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
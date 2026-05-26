package UserInterface;

import Exceptions.DataAccessException;
import Exceptions.ValidationException;
import Interfaces.*;
import Model.Referent;
import Model.SchoolOfThought;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

public class CreateRefPanel extends JPanel {

    private JLabel designationLabel, firstNameLabel, lastNameLabel, birthDateLabel, isAliveLabel, schoolsOfThoughtLabel, websiteLabel, nicknameLabel, title;
    private JTextField designation, firstName, lastName, website, nickname;
    private JComboBox<String> schoolsOfThought;
    private Checkbox isAlive;
    private JSpinner birthDate;
    private JButton button;
    private ButtonListener buttonListener;
    private ArrayList<SchoolOfThought> schools;

    public CreateRefPanel(IController controller) throws DataAccessException {
        this.setLayout(new BorderLayout());
        title = new JLabel("Here is the place to add referents");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(Font.SERIF, Font.ITALIC, 28));
        this.add(title, BorderLayout.NORTH);

        var fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(9, 2, 5, 5));

        firstNameLabel = new JLabel("First name ");
        fieldsPanel.add(firstNameLabel);
        firstName = new JTextField();
        fieldsPanel.add(firstName);

        lastNameLabel = new JLabel("Last name ");
        fieldsPanel.add(lastNameLabel);
        lastName = new JTextField();
        fieldsPanel.add(lastName);

        designationLabel = new JLabel("Designation ");
        fieldsPanel.add(designationLabel);
        designation = new JTextField();
        fieldsPanel.add(designation);

        birthDateLabel = new JLabel("Birth Date ");
        fieldsPanel.add(birthDateLabel);
        Date today = new Date();
        SpinnerDateModel dateModel = new SpinnerDateModel(
            today,
            null,
            today,
            Calendar.YEAR
        );
        birthDate = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(
            birthDate,
            "yyyy-MM-dd"
        );
        birthDate.setEditor(dateEditor);

        fieldsPanel.add(birthDate);

        isAliveLabel = new JLabel("Is dead");
        fieldsPanel.add(isAliveLabel);
        isAlive = new Checkbox();
        fieldsPanel.add(isAlive);

        schoolsOfThoughtLabel = new JLabel("School of Thought ID ");
        fieldsPanel.add(schoolsOfThoughtLabel);
        schools = controller.getAllSchools();
        var schoolsNames = new String[schools.size()];
        for (int i = 0; i < schools.size(); i++) {
            schoolsNames[i] = schools.get(i).getName();
        }
        schoolsOfThought = new JComboBox<String>(schoolsNames);
        fieldsPanel.add(schoolsOfThought);

        websiteLabel = new JLabel("Website ");
        fieldsPanel.add(websiteLabel);
        website = new JTextField();
        fieldsPanel.add(website);

        nicknameLabel = new JLabel("Nickname ");
        fieldsPanel.add(nicknameLabel);
        nickname = new JTextField();
        fieldsPanel.add(nickname);

        button = new JButton("Submit");
        buttonListener = new ButtonListener(controller);
        button.addActionListener(buttonListener);
        fieldsPanel.add(button);

        var wrapper = new JPanel(new GridBagLayout());
        wrapper.add(fieldsPanel);
        fieldsPanel.setPreferredSize(new java.awt.Dimension(500, 250));
        this.add(wrapper, BorderLayout.CENTER);
    }

    private class ButtonListener implements ActionListener {

        private IController controller;

        public ButtonListener(IController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                controller.addReferent(
                    new Referent(
                        designation.getText(),
                        firstName.getText(),
                        lastName.getText(),
                        ((java.util.Date) birthDate.getValue())
                            .toInstant()
                            .atZone(java.time.ZoneId.systemDefault())
                            .toLocalDate(),
                        isAlive.getState(),
                        schoolsOfThought.getSelectedIndex(),
                        website.getText(),
                        nickname.getText()
                    )
                );
                controller.displayCreateReferent();
            } catch (ValidationException e) {
                JOptionPane.showMessageDialog(
                    null,
                    "Invalid value entered for referent\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            } catch (DataAccessException e) {
                JOptionPane.showMessageDialog(
                    null,
                    "Could not create referent",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}

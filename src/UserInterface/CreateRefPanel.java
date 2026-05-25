package UserInterface;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CreateRefPanel extends JPanel {

    private JLabel designationLabel, firstNameLabel, lastNameLabel, birthDateLabel, isAliveLabel, idSchoolOfThoughtLabel, websiteLabel, nicknameLabel, title;
    private JTextField designation, firstName, lastName, birthDate, isAlive, website, nickname;
    private JComboBox<String> idSchoolOfThought;

    public CreateRefPanel() {
        this.setLayout(new BorderLayout());
        title = new JLabel("Here is the place to add referents");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(Font.SERIF, Font.ITALIC, 28));
        this.add(title, BorderLayout.NORTH);

        var fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(8, 2, 5, 5));

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
        birthDate = new JTextField();
        fieldsPanel.add(birthDate);

        isAliveLabel = new JLabel("Is Alive ");
        fieldsPanel.add(isAliveLabel);
        isAlive = new JTextField();
        fieldsPanel.add(isAlive);

        idSchoolOfThoughtLabel = new JLabel("School of Thought ID ");
        fieldsPanel.add(idSchoolOfThoughtLabel);
        String[] schools = { "test1", "test2", "test3" };
        idSchoolOfThought = new JComboBox<String>(schools);
        fieldsPanel.add(idSchoolOfThought);

        fieldsPanel.add(idSchoolOfThought);
        websiteLabel = new JLabel("Website ");
        fieldsPanel.add(websiteLabel);
        website = new JTextField();
        fieldsPanel.add(website);

        nicknameLabel = new JLabel("Nickname ");
        fieldsPanel.add(nicknameLabel);
        nickname = new JTextField();
        fieldsPanel.add(nickname);

        var wrapper = new JPanel(new GridBagLayout());
        wrapper.add(fieldsPanel);
        fieldsPanel.setPreferredSize(new java.awt.Dimension(500, 250));
        this.add(wrapper, BorderLayout.CENTER);
    }
}

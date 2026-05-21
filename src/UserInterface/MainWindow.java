package UserInterface;

import Interfaces.IUserInterface;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class MainWindow extends JFrame implements IUserInterface {

    private JMenuBar menuBar;
    private JMenu systemMenu, modifications, search;
    private JMenuItem exit, referentDel, referentUp, referentCr, referentSearch, itemSearch;
    private Container mainContainer;
    private MonkPanel monkPanel;

    public MainWindow() {
        super("Advanced_POO");

        final int WINDOW_WIDTH = 1500;
        final int WINDOW_HEIGHT = 1000;
        setBounds(100, 50, WINDOW_WIDTH, WINDOW_HEIGHT);
        addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        systemMenu = new JMenu("System");
        systemMenu.setMnemonic('S');
        menuBar.add(systemMenu);
        modifications = new JMenu("Modify");
        modifications.setMnemonic('M');
        menuBar.add(modifications);
        search = new JMenu("Search");
        search.setMnemonic('F');
        menuBar.add(search);

        exit = new JMenuItem("Exit");
        exit.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK)
        );
        ExitListener exitListener = new ExitListener();
        exit.addActionListener(exitListener);
        systemMenu.add(exit);

        referentCr = new JMenuItem("Encode");
        referentCr.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK)
        );
        CreateListener createListener = new CreateListener();
        referentCr.addActionListener(createListener);
        modifications.add(referentCr);
        modifications.addSeparator();
        referentUp = new JMenuItem("Update");
        referentUp.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK)
        );
        UpdateListener updateListener = new UpdateListener();
        referentUp.addActionListener(updateListener);
        modifications.add(referentUp);
        modifications.addSeparator();
        referentDel = new JMenuItem("Delete");
        referentDel.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK)
        );
        DeleteListener deleteListener = new DeleteListener();
        referentDel.addActionListener(deleteListener);
        modifications.add(referentDel);

        referentSearch = new JMenuItem("Referent");
        referentSearch.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK)
        );
        ReferentSearchListener referentSearchListener =
            new ReferentSearchListener();
        referentSearch.addActionListener(referentSearchListener);
        search.add(referentSearch);
        itemSearch = new JMenuItem("Item");
        itemSearch.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK)
        );
        ItemSearchListener itemSearchListener = new ItemSearchListener();
        itemSearch.addActionListener(itemSearchListener);
        search.add(itemSearch);

        monkPanel = new MonkPanel();
        monkPanel.setLayout(null);
        JLabel label = new JLabel();
        final int MONK_WIDTH = 100;
        try {
            label.setIcon(
                new ImageIcon(
                    new ImageIcon("resources/monk.png")
                        .getImage()
                        .getScaledInstance(
                            MONK_WIDTH,
                            MONK_WIDTH + 10,
                            Image.SCALE_SMOOTH
                        )
                )
            );
            label.setBounds(
                WINDOW_WIDTH - MONK_WIDTH,
                WINDOW_HEIGHT - (int) (MONK_WIDTH * 1.65),
                MONK_WIDTH,
                MONK_WIDTH + 10
            );
            monkPanel.add(label);
        } catch (NullPointerException exception) {
            System.out.println("Image could not be loaded"); // TO MODIFY
            monkPanel = null;
        }

        mainContainer = this.getContentPane();
        mainContainer.add(menuBar);

        if (monkPanel != null) {
            mainContainer.add(monkPanel);
            var thread = new FloatingThread(monkPanel);
            thread.start();
        }

        setVisible(true);
    }

    @Override
    public void displaySearch() {}

    @Override
    public void displayCreate() {}

    @Override
    public void displayUpdate() {}

    @Override
    public void displayDelete() {}

    @Override
    public void displayItemSearch() {}

    @Override
    public void displayReferentSearch() {}
}

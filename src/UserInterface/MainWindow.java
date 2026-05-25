package UserInterface;

import Exceptions.UIException;
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
    private JMenuItem exit, referentDel, referentUp, referentCr, referentSearch, itemSearch, backHome;
    private Container mainContainer;
    private JPanel currentPanel;

    public MainWindow() {
        super("Advanced_POO");
        mainContainer = this.getContentPane();

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
        modifications = new JMenu("Modify referents");
        modifications.setMnemonic('M');
        menuBar.add(modifications);
        search = new JMenu("Search");
        search.setMnemonic('F');
        menuBar.add(search);

        backHome = new JMenuItem("Home");
        backHome.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK)
        );
        HomeListener backHomeListener = new HomeListener();
        backHome.addActionListener(backHomeListener);
        systemMenu.add(backHome);

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

        referentSearch = new JMenuItem("Referents");
        referentSearch.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK)
        );
        ReferentSearchListener referentSearchListener =
            new ReferentSearchListener();
        referentSearch.addActionListener(referentSearchListener);
        search.add(referentSearch);
        itemSearch = new JMenuItem("Items");
        itemSearch.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK)
        );
        ItemSearchListener itemSearchListener = new ItemSearchListener();
        itemSearch.addActionListener(itemSearchListener);
        search.add(itemSearch);

        displayHome();
        setVisible(true);
    }

    @Override
    public void displayCreateReferent() {
        updateContainer(new CreateRefPanel());
    }

    @Override
    public void displayUpdateReferent() {
        updateContainer(new UpdateRefPanel());
    }

    @Override
    public void displayDeleteReferent() {
        updateContainer(new DeleteRefPanel());
    }

    @Override
    public void displayItemSearch() {
        updateContainer(new ReadItemPanel());
    }

    @Override
    public void displayReferentSearch() {
        updateContainer(new ReadRefPanel());
    }

    @Override
    public void displayHome() {
        try {
            HomePanel homePanel = new HomePanel();
            updateContainer(homePanel);
            var thread = new FloatingThread(homePanel.getMonk());
            thread.start();
        } catch (UIException e) {
            JOptionPane.showMessageDialog(
                this,
                e.getMessage() + "\n(" + e.getOriginalMessage() + ")",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalThreadStateException e) {
            JOptionPane.showMessageDialog(
                this,
                "Another instance seems to be running, please close it" +
                    e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void updateContainer(JPanel panel) {
        if (currentPanel != null) {
            mainContainer.remove(currentPanel);
        }
        currentPanel = panel;
        try {
            mainContainer.add(currentPanel);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(
                this,
                "Could not display this panel because it's null\n" +
                    e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
        mainContainer.revalidate();
        mainContainer.repaint();
    }
}

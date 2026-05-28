package UserInterface;

import Exceptions.DataAccessException;
import Exceptions.UIException;
import Interfaces.*;
import Model.Referent;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class MainWindow extends JFrame implements IUserInterface {

    private JMenuBar menuBar;
    private JMenu systemMenu, modifications, search;
    private JMenuItem exit, referentDel, referentUp, referentCr, referentSearch, itemSearch, orderLinesSearch, backHome;
    private Container mainContainer;
    private JPanel currentPanel;
    private IController controller;
    private FloatingThread thread;

    public MainWindow(IController controller) {
        super("Advanced_POO");
        mainContainer = this.getContentPane();
        this.controller = controller;

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
            KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK)
        );
        HomeListener backHomeListener = new HomeListener(controller);
        backHome.addActionListener(backHomeListener);
        systemMenu.add(backHome);

        exit = new JMenuItem("Exit");
        exit.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK)
        );
        ExitListener exitListener = new ExitListener(controller);
        exit.addActionListener(exitListener);
        systemMenu.add(exit);

        referentCr = new JMenuItem("Encode");
        referentCr.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK)
        );
        CreateListener createListener = new CreateListener(controller);
        referentCr.addActionListener(createListener);
        modifications.add(referentCr);
        modifications.addSeparator();
        referentUp = new JMenuItem("Update");
        referentUp.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK)
        );
        UpdateListener updateListener = new UpdateListener(controller);
        referentUp.addActionListener(updateListener);
        modifications.add(referentUp);
        modifications.addSeparator();
        referentDel = new JMenuItem("Delete");
        referentDel.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK)
        );
        DeleteListener deleteListener = new DeleteListener(controller);
        referentDel.addActionListener(deleteListener);
        modifications.add(referentDel);

        referentSearch = new JMenuItem("Referents");
        referentSearch.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK)
        );
        ReferentSearchListener referentSearchListener =
            new ReferentSearchListener(controller);
        referentSearch.addActionListener(referentSearchListener);
        search.add(referentSearch);
        itemSearch = new JMenuItem("Items");
        itemSearch.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK)
        );
        ItemSearchListener itemSearchListener = new ItemSearchListener(
            controller
        );
        itemSearch.addActionListener(itemSearchListener);
        search.add(itemSearch);

        orderLinesSearch = new JMenuItem("Order lines");
        orderLinesSearch.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK)
        );
        OrderLinesSearchListener orderLinesSearchListener =
                new OrderLinesSearchListener(controller);
        orderLinesSearch.addActionListener(orderLinesSearchListener);
        search.add(orderLinesSearch);

        displayHome();
        setVisible(true);
    }

    @Override
    public void displayCreateReferent() {
        try {
            updateContainer(new CreateRefPanel(controller));
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(
                this,
                "Could not display this panel, could not fetch schools\n" +
                    e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void displayUpdateReferent() {
        try {
            updateContainer(new UpdateRefPanel(controller));
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(
                this,
                "Could not display this panel, could not fetch referents\n" +
                    e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void displayUpdateReferent(Referent referent) {
        try {
            updateContainer(new UpdateRefPanel(controller, referent));
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(
                this,
                "Could not display this panel, could not fetch schools\n" +
                    e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void displayDeleteReferent() {
        try {
            updateContainer(new DeleteRefPanel(controller));
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(
                this,
                "Could not display this panel, could not fetch referents\n" +
                    e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void displayItemSearch() {
        try {
            updateContainer(new ReadItemPanel(controller));
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(
                this,
                "Could not display this panel, could not fetch referents\n" +
                    e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void displayReferentSearch() {
        try {
            updateContainer(new ReadRefPanel(controller));
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(
                this,
                "Could not display this panel, could not fetch referents\n" +
                    e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void displayHome() {
        try {
            HomePanel homePanel = new HomePanel();
            updateContainer(homePanel);
            SwingUtilities.invokeLater(() -> {
                thread = new FloatingThread(homePanel.getMonk());
                thread.start();
            });
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
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
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

    @Override
    public void displayOrderLinesSearch() {
        try {
            updateContainer(new ReadOrderLinesPanel(controller));
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Could not display this panel, could not fetch clients\n" +
                            e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

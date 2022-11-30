package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class Tetris extends JFrame implements KeyListener, ActionListener {
    JPanel panel;
    private JLabel label;
    private static final int COLS = 16;
    private static final int ROWS = 20;
    private static final int IMAGE_SIZE = 24;

    public static void main(String[] args) {
        new Tetris();
    }

    Tetris() {
        setImages();
        initLabel();
        initPanel();
        initFrame();
        GameField.createGameField();
        Game.start();
        Timer timer = new Timer(5, this);
        timer.start();
        addKeyListener(this);
    }

    private void initLabel() {
        label = new JLabel("Left button => Start Game                     <<  Welcome! >>");
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (int y = 0; y < getRows(); y++)
                    for (int x = 0; x < getCols(); x++)
                        g.drawImage(GameField.getObj(new Coor(x, y)).getImg(),
                                x * getImageSize(), y * getImageSize(), this);
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //int x = e.getX()/getImageSize();
                //int y = e.getY()/getImageSize();
                if (e.getButton() == MouseEvent.BUTTON1)
                    Game.leftButtonPressed();
                if (e.getButton() == MouseEvent.BUTTON3)
                    Game.rightButtonPressed();
                label.setText(Game.getMsg());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(COLS * IMAGE_SIZE, ROWS * IMAGE_SIZE));
        add(panel);
    }

    // void paint(){panel.repaint();}

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tetris");
        setLocationRelativeTo(null);
        setLocation(20, 20);
        setResizable(false);
        setVisible(true);
        setIconImage(Images.ICON.img);
        pack();
    }

    static int getCols() {
        return COLS;
    }

    static int getRows() {
        return ROWS;
    }

    static int getImageSize() {
        return IMAGE_SIZE;
    }

    private void setImages() {
        Images.N0.img = getImage("0");
        Images.N1.img = getImage("1");
        Images.NONE.img = getImage("none");
        Images.ICON.img = getImage("Icon");

    }

    private Image getImage(String name) {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + name + ".png"))).getImage();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        label.setText(Game.getMsg());
        panel.repaint();
        panel.setPreferredSize(new Dimension(COLS * IMAGE_SIZE, ROWS * IMAGE_SIZE));
        add(panel);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)
            Game.upArrowPressed();
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            Game.leftArrowPressed();
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            Game.rightArrowPressed();
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            Game.downArrowPressed();
        label.setText(Game.getMsg());
        panel.repaint();
        panel.setPreferredSize(new Dimension(COLS * IMAGE_SIZE, ROWS * IMAGE_SIZE));
        add(panel);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        label.setText(Game.getMsg());
        panel.repaint();
        panel.setPreferredSize(new Dimension(COLS * IMAGE_SIZE, ROWS * IMAGE_SIZE));
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
}

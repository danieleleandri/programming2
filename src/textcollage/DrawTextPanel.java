package textcollage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * A panel that contains a large drawing area where strings
 * can be drawn. The strings are represented by objects of
 * type DrawTextItem. An input box under the panel allows
 * the user to specify what string will be drawn when the
 * user clicks on the drawing area.
 */
public class DrawTextPanel extends JPanel {

    // As it now stands, this class can only show one string at at
    // a time! The data for that string is in the DrawTextItem object
    // named theString. (If it's null, nothing is shown. This
    // variable should be replaced by a variable of type
    // ArrayList<DrawStringItem> that can store multiple items.

    private ArrayList<DrawTextItem> theStrings; // change to an ArrayList<DrawTextItem> !

    private Color currentTextColor = Color.BLACK; // Color applied to new strings.
    private double currentTextBackGroundTransparency = 0.0; // Color applied to new strings.
    private Font currentFont = new Font("Arial", Font.PLAIN, 15);
    private double currentRotation = 0.0;
    private Color currentBackgroundColor;

    private Canvas canvas; // the drawing area.
    private JTextField input; // where the user inputs the string that will be added to the canvas
    private SimpleFileChooser fileChooser; // for letting the user select files
    private JMenuBar menuBar; // a menu bar with command that affect this panel
    private MenuHandler menuHandler; // a listener that responds whenever the user selects a menu command
    private JMenuItem undoMenuItem; // the "Remove Item" command from the edit menu

    /**
     * An object of type Canvas is used for the drawing area.
     * The canvas simply displays all the DrawTextItems that
     * are stored in the ArrayList, strings.
     */
    String checkString = "TextCollage";
    char fieldSeparator = ',';
    char recordSeparator = '\n';

    private class Canvas extends JPanel {

        Canvas() {
            /* Here I made the first modify. I used an ArrayList instead of a single
             * DrawTextItem */
            theStrings = new ArrayList<DrawTextItem>();
            setPreferredSize(new Dimension(800, 600));
            /*I initialized the background color to avoid null pointer error*/
            setBackground(Color.LIGHT_GRAY);
            /*Here I defined the default FONT*/
            setFont(new Font("Serif", Font.BOLD, 24));
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            /*The following cycle paints the string on the canvas*/
            for (DrawTextItem theString : theStrings)
                if (theString != null)
                    theString.draw(g);
        }


        
    }

    /**
     * An object of type MenuHandler is registered as the ActionListener
     * for all the commands in the menu bar. The MenuHandler object
     * simply calls doMenuCommand() when the user selects a command
     * from the menu.
     */
    private class MenuHandler implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            doMenuCommand(evt.getActionCommand());
        }
    }

    /**
     * Creates a DrawTextPanel. The panel has a large drawing area and
     * a text input box where the user can specify a string. When the
     * user clicks the drawing area, the string is added to the drawing
     * area at the point where the user clicked.
     */
    public DrawTextPanel() {
        fileChooser = new SimpleFileChooser();
        undoMenuItem = new JMenuItem("Remove Item");
        undoMenuItem.setEnabled(false);
        menuHandler = new MenuHandler();
        setLayout(new BorderLayout(3, 3));
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        canvas = new Canvas();
        add(canvas, BorderLayout.CENTER);
        JPanel bottom = new JPanel();
        bottom.add(new JLabel("Text to add: "));
        input = new JTextField("Hello World!", 40);
        bottom.add(input);
        add(bottom, BorderLayout.SOUTH);
        canvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                doMousePress(e);
            }
        });
    }

    /**
     * This method is called when the user clicks the drawing area.
     * A new string is added to the drawing area. The center of
     * the string is at the point where the user clicked.
     * 
     * @param e the mouse event that was generated when the user clicked
     */
    public void doMousePress(MouseEvent e) {
        String text = input.getText().trim();
        if (text.length() == 0) {
            input.setText("Hello World!");
            text = "Hello World!";
        }
        DrawTextItem s = new DrawTextItem(text, e.getX(), e.getY());
        s.setFont(currentFont);
        s.setBackgroundTransparency(currentTextBackGroundTransparency);
        s.setTextColor(currentTextColor); // Default is null, meaning default color of the canvas (black).
        s.setRotationAngle(currentRotation);
//   SOME OTHER OPTIONS THAT CAN BE APPLIED TO TEXT ITEMS:
//		s.setFont( new Font( "Serif", Font.ITALIC + Font.BOLD, 12 ));  // Default is null, meaning font of canvas.
//		s.setMagnification(3);  // Default is 1, meaning no magnification.
//		s.setBorder(true);  // Default is false, meaning don't draw a border.
//		s.setRotationAngle(25);  // Default is 0, meaning no rotation.
//		s.setTextTransparency(0.3); // Default is 0, meaning text is not at all transparent.
//		s.setBackground(Color.BLUE);  // Default is null, meaning don't draw a background area.
//		s.setBackgroundTransparency(0.7);  // Default is 0, meaning background is not transparent.

        /* 2 Added the element to the array */
        theStrings.add(s); // Set this string as the ONLY string to be drawn on the canvas!
        undoMenuItem.setEnabled(true);
        canvas.repaint();
    }

    /**
     * Returns a menu bar containing commands that affect this panel. The menu
     * bar is meant to appear in the same window that contains this panel.
     */
    public JMenuBar getMenuBar() {
        if (menuBar == null) {
            menuBar = new JMenuBar();

            String commandKey; // for making keyboard accelerators for menu commands
            if (System.getProperty("mrj.version") == null)
                commandKey = "control "; // command key for non-Mac OS
            else
                commandKey = "meta "; // command key for Mac OS

            JMenu fileMenu = new JMenu("File");
            menuBar.add(fileMenu);
            JMenuItem saveItem = new JMenuItem("Save...");
            saveItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "N"));
            saveItem.addActionListener(menuHandler);
            fileMenu.add(saveItem);
            JMenuItem openItem = new JMenuItem("Open...");
            openItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "O"));
            openItem.addActionListener(menuHandler);
            fileMenu.add(openItem);
            fileMenu.addSeparator();
            JMenuItem saveImageItem = new JMenuItem("Save Image...");
            saveImageItem.addActionListener(menuHandler);
            fileMenu.add(saveImageItem);

            JMenu editMenu = new JMenu("Edit");
            menuBar.add(editMenu);
            undoMenuItem.addActionListener(menuHandler); // undoItem was created in the constructor
            undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "Z"));
            editMenu.add(undoMenuItem);
            editMenu.addSeparator();
            JMenuItem clearItem = new JMenuItem("Clear");
            clearItem.addActionListener(menuHandler);
            editMenu.add(clearItem);

            JMenu optionsMenu = new JMenu("Options");
            menuBar.add(optionsMenu);
            JMenuItem colorItem = new JMenuItem("Set Text Color...");
            colorItem.setAccelerator(KeyStroke.getKeyStroke(commandKey + "T"));
            colorItem.addActionListener(menuHandler);
            optionsMenu.add(colorItem);
            JMenuItem bgColorItem = new JMenuItem("Set Background Color...");
            bgColorItem.addActionListener(menuHandler);
            optionsMenu.add(bgColorItem);

            // Adding the font selector
            JMenuItem bigger = new JMenuItem("Make it bigger...");
            bigger.setAccelerator(KeyStroke.getKeyStroke(commandKey + "+"));
            bigger.addActionListener(menuHandler);
            optionsMenu.add(bigger);

            JMenuItem smaller = new JMenuItem("Make it smaller...");
            smaller.setAccelerator(KeyStroke.getKeyStroke(commandKey + "-"));
            smaller.addActionListener(menuHandler);
            optionsMenu.add(smaller);

            JMenuItem rotate = new JMenuItem("Rotate 90° clockwise...");
            rotate.setAccelerator(KeyStroke.getKeyStroke(commandKey + "r"));
            rotate.addActionListener(menuHandler);
            optionsMenu.add(rotate);
            
            JMenuItem gradient = new JMenuItem("Gradient");
            gradient.setAccelerator(KeyStroke.getKeyStroke(commandKey + "d"));
            gradient.addActionListener(menuHandler);
            optionsMenu.add(gradient);

        }
        return menuBar;
    }


    /**
     * Carry out one of the commands from the menu bar.
     * 
     * @param command the text of the menu command.
     * @throws InterruptedException 
     */
    private void doMenuCommand(String command)  {

         if (command.equals("Gradient")) {
           double inc = 1/(double)theStrings.size();
           double i = 0;
           for (DrawTextItem item : theStrings) {
            item.setTextTransparency(i);
            i = i + inc;
           }
           canvas.repaint();
        }

        if (command.equals("Make it bigger...")) {
            int inc = 10;
            if ((currentFont.getSize() + inc) <= 200) {
                currentFont = new Font(
                        currentFont.getFontName(),
                        currentFont.getStyle(),
                        currentFont.getSize() + inc);
            }

        }
        if (command.equals("Make it smaller...")) {
            int dec = 15;
            if ((currentFont.getSize() - dec) >= 4) {
                currentFont = new Font(
                        currentFont.getFontName(),
                        currentFont.getStyle(),
                        currentFont.getSize() - dec);
            }

        }
        if (command.equals("Rotate 90° clockwise...")) {
            currentRotation = currentRotation + 90;
        }

        if (command.equals("Save...")) { // save all the string info to a file
            File projectFile = fileChooser.getOutputFile(this, "Select project File Name", "project.prj");
            PrintWriter pW = null;
            try {
                pW = new PrintWriter(projectFile);
            } catch (FileNotFoundException e) {
                System.out.println("The file was not found. ");
                // e.printStackTrace();
            }
            pW.write("TextCollage" + recordSeparator);
            pW.write("" +
                    canvas.getBackground().getRed() + fieldSeparator + // 3
                    canvas.getBackground().getGreen() + fieldSeparator + // 4
                    canvas.getBackground().getBlue() +
                    recordSeparator);
            for (DrawTextItem str : theStrings) {
                int backRed = -1, backGreen = -1, backBlue = -1;
                if (str.getBackground() != null) {
                    backRed = str.getBackground().getRed();
                    backGreen = str.getBackground().getGreen();
                    backBlue = str.getBackground().getBlue();
                }
                pW.write("" +
                        str.getString() + fieldSeparator + // 0
                        str.getX() + fieldSeparator + // 1
                        str.getY() + fieldSeparator + // 2
                        backRed + fieldSeparator + // 3
                        backGreen + fieldSeparator + // 4
                        backBlue + fieldSeparator + // 5
                        str.getBackgroundTransparency() + fieldSeparator + // 6
                        str.getMagnification() + fieldSeparator + // 7
                        str.getRotationAngle() + fieldSeparator + // 8
                        str.getTextColor().getRed() + fieldSeparator + // 9
                        str.getTextColor().getGreen() + fieldSeparator + // 10
                        str.getTextColor().getBlue() + fieldSeparator + // 11
                        str.getTextTransparency() + fieldSeparator + // 12
                        str.getBorder() + fieldSeparator + // 13
                        str.getFont().getFontName() + fieldSeparator + // 14
                        str.getFont().getStyle() + fieldSeparator + // 15
                        str.getFont().getSize() + // 16
                        recordSeparator);
            }

            pW.close();
            // JOptionPane.showMessageDialog(this, "Sorry, the Save command is not
            // implemented.");
        } else if (command.equals("Open...")) { // read a previously saved file, and reconstruct the list of strings
            // JOptionPane.showMessageDialog(this, "Sorry, the Open command is not
            // implemented.");
            File projectFile = fileChooser.getInputFile();
            Scanner scan = null;
            try {
                scan = new Scanner(projectFile);
            } catch (FileNotFoundException e) {
                System.out.println("The file was not found. ");
                // e.printStackTrace();
            }
            ArrayList<DrawTextItem> loadedStrings = new ArrayList<DrawTextItem>();
            if (scan.hasNext()) {
                // String cont = scan.next();
                if (!checkString.equals(scan.next())) {
                    throw new IllegalArgumentException("Not a valid file!");
                }
                ;
            }
            scan.useDelimiter("\n");
            // Load the canvas Background
            if (scan.hasNext()) {
                String a = scan.next();
                String[] record = a.split(",");

                canvas.setBackground(new Color(Integer.parseInt(record[0]),
                        Integer.parseInt(record[1]),
                        Integer.parseInt(record[2])));
            }
            //Reading the senteces
            while (scan.hasNext()) {

                String a = scan.next();
                String[] record = a.split(",");
                int i = 0;
                DrawTextItem dTI = new DrawTextItem(record[0]);
                dTI.setX(Integer.parseInt(record[1]));
                dTI.setY(Integer.parseInt(record[2]));
                // IT will load just if back is not null
                if ((record[3].charAt(0) != '-') && (record[4].charAt(0) != '-') && (record[5].charAt(0) != '-')) {
                    canvas.setBackground(new Color(
                            Integer.parseInt(record[3]),
                            Integer.parseInt(record[4]),
                            Integer.parseInt(record[5])));
                }

                dTI.setBackgroundTransparency(Double.parseDouble(record[6]));
                dTI.setMagnification(Double.parseDouble(record[7]));
                dTI.setRotationAngle(Double.parseDouble(record[8]));
                dTI.setTextColor(new Color(
                        Integer.parseInt(record[9]),
                        Integer.parseInt(record[10]),
                        Integer.parseInt(record[11])));
                dTI.setTextTransparency(Double.parseDouble(record[12]));
                dTI.setBorder(Boolean.parseBoolean(record[13]));

                Font textFont = new Font(record[14],
                        Integer.parseInt(record[15]),
                        Integer.parseInt(record[16]));
                dTI.setFont(textFont);

                loadedStrings.add(dTI);
            }
            theStrings = loadedStrings;

//            pW.close();

            canvas.repaint(); // (you'll need this to make the new list of strings take effect)
        } else if (command.equals("Clear")) { // remove all strings
            theStrings.clear(); // Remove the ONLY string from the canvas.
            undoMenuItem.setEnabled(false);
            canvas.repaint();
        } else if (command.equals("Remove Item")) {
            // remove the most recently added string
            theStrings.remove(theStrings.size() - 1); // Remove the ONLY string from the canvas.
            if (theStrings.size() <= 0)
                undoMenuItem.setEnabled(false);
            canvas.repaint();
        } else if (command.equals("Set Text Color...")) {
            Color c = JColorChooser.showDialog(this, "Select Text Color", currentTextColor);
            if (c != null)
                currentTextColor = c;
        } else if (command.equals("Set Background Color...")) {
            Color c = JColorChooser.showDialog(this, "Select Background Color", canvas.getBackground());
            if (c != null) {
                canvas.setBackground(c);
                canvas.repaint();
            }
        } else if (command.equals("Save Image...")) { // save a PNG image of the drawing area
            File imageFile = fileChooser.getOutputFile(this, "Select Image File Name", "textimage.png");
            if (imageFile == null)
                return;
            try {
                // Because the image is not available, I will make a new BufferedImage and
                // draw the same data to the BufferedImage as is shown in the panel.
                // A BufferedImage is an image that is stored in memory, not on the screen.
                // There is a convenient method for writing a BufferedImage to a file.
                BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(),
                        BufferedImage.TYPE_INT_RGB);
                Graphics g = image.getGraphics();
                g.setFont(canvas.getFont());
                canvas.paintComponent(g); // draws the canvas onto the BufferedImage, not the screen!
                boolean ok = ImageIO.write(image, "PNG", imageFile); // write to the file
                if (ok == false)
                    throw new Exception("PNG format not supported (this shouldn't happen!).");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Sorry, an error occurred while trying to save the image:\n" + e);
            }
        }
    }



}

/**
 * Gai Shukrun 315809863 + Vlad Furman 206818239
 */

package GUI;
import States.*;
import factory.RaceBuilder;
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.air.Airplane;
import game.racers.air.Helicopter;
import game.racers.land.Bicycle;
import game.racers.land.Car;
import game.racers.land.Horse;
import game.racers.naval.RowBoat;
import game.racers.naval.SpeedBoat;
import utilities.Point;
import utilities.EnumContainer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.lang.management.LockInfo;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * MainScreen Class (Frame) implement ActionListener for the buttons
 * mainPanel - Panel that got all the sub Panels
 *
 * rightPanel-A panel that have all the input for the arena(BuildArenaPanel),racers( RacerPanel),start/info button(infoPanel)
 *
 *  myPanel displayPanel - a class of JPanel that have a img field
 *  IconWidth/IconHeight -> for the size of the racers image(to fit to the screen)
 *  index1 -> for the racers gap in the screen
 *  index 2-> for the racer place (via Point) on the screen
 *
 */
public class MainScreen extends JFrame implements ActionListener,MyObserver{
    static int IconWidth = 50;
    static int IconHight =50;
    
    // Track positioning constants based on LandArena.jpg analysis
    static final int TRACK_START_Y = 127;  // Y position where racing lanes start
    static final int TRACK_END_Y = 700;    // Y position where racing lanes end  
    static final int LANE_HEIGHT = 31;     // Height of each racing lane
    static final int START_LINE_X = 14;    // X position of start line
    static final int TRACK_WIDTH = 1580;   // Width of racing area
    // Calculate finish line position as percentage of arena width (95% of arena + icon width for complete crossing)
    public static int getVisualFinishLineX(int arenaLength) {
        int panelWidth = arenaLength + 110;  // Panel width as set in displayPanel.setPreferredSize
        int finishLineStart = (int)(panelWidth * 0.95);  // Finish line at 95% of panel width
        return finishLineStart + IconWidth;  // Add icon width so entire icon crosses finish line
    }
    private  static  int index1 =0;
    private static int index2=0;
    List<Thread> threads = new ArrayList<>();
    final static  String IMAGES_PATH = "GUI/icons";
    private static Arena arena;
    private static RaceBuilder builder = RaceBuilder.getInstance();
    private  Racer racers ;
    private Image image;
    private ImageIcon icon;
    private JComboBox<String> arenaComboBox;
    private JComboBox<String> racerTypeComboBox;
    private JComboBox<String> racerColorComboBox;
    final static String[] ArenaNames = {"LandArena", "NavalArena", "AerialArena"};
    final static String[] Color = {"Red", "Green", "Blue", "Black", "Yellow"};
    final static String[] RacerType = {"Airplane", "Helicopter", "Car", "Horse", "Bicycle", "RowBoat", "SpeedBoat"};
    final static String[] columnNames = {"Racer name", "Current speed", "Max speed", "Current x location", "Finished"};
    private JTextField lengthField;
    private JTextField maxRacersField;
    private JTextField maxSpeedField;
    private JTextField accelerationField;
    private JTextField racerNameField;
    private JButton createArenaBtn;
    private JButton RacerBtn,startRaceBtn,showInfoBtn,AddFromExistingRacer,BuilderRace;

    private JPanel rightPanel,  mainPanel,BuildArenaPanel, RacerPanel,infoPanel;
    private myPanel displayPanel;
    private JFrame infoFrame = new JFrame("JTable Example");
    private DefaultTableModel model;
    private  JTable table;
    static Map<String,Racer> RacerMap = new HashMap<String,Racer>();

    /**
     * MainScreen Constructor.
     * initializing all the panels,buttons,Labels  & table(for info button)
     * adding ActionListener , sizes,Borders to each Panel,button
     */
    public MainScreen() {
        super("Racing Simulator");
        mainPanel = new JPanel();
        infoPanel = new JPanel(new GridLayout(0,1,1,0));
        mainPanel = new JPanel(new BorderLayout());
        displayPanel = new myPanel();
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        BuildArenaPanel = new JPanel(new GridLayout(0,1,1,0));
        mainPanel.add(rightPanel,BorderLayout.EAST);
        mainPanel.add(displayPanel,BorderLayout.CENTER);
        mainPanel.setSize(900,700);

        BuildArenaPanel.add(new JLabel("choose arena"));
        arenaComboBox = new JComboBox<>(ArenaNames);
        BuildArenaPanel.add(arenaComboBox);

        lengthField = new JTextField(1);
        BuildArenaPanel.add(new JLabel("Arena length:"));
        lengthField.setText("1000");
        BuildArenaPanel.add(lengthField);

        maxRacersField = new JTextField(1);
        BuildArenaPanel.add(new JLabel("Max racer number"));
        maxRacersField.setText("8");
        BuildArenaPanel.add(maxRacersField);

        createArenaBtn = new JButton("Build Arena");

        createArenaBtn.addActionListener(this);
        BuildArenaPanel.add(createArenaBtn);

        BuildArenaPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(2,2,2,2)));

        RacerPanel = new JPanel(new GridLayout(0,1,1,0));
        RacerPanel.add(new JLabel("choose racer"));
        racerTypeComboBox = new JComboBox<>(RacerType);
        RacerPanel.add(racerTypeComboBox);

        RacerPanel.add(new JLabel("choose color"));
        racerColorComboBox = new JComboBox<>(Color);
        RacerPanel.add(racerColorComboBox);
        racerNameField = new JTextField(1);

        RacerPanel.add(new JLabel("Racer name:"));
        RacerPanel.add(racerNameField);
        maxSpeedField = new JTextField(1);

        RacerPanel.add(new JLabel("Max speed:"));
        RacerPanel.add(maxSpeedField);

        accelerationField = new JTextField();
        RacerPanel.add(new JLabel("acceleration:"));
        RacerPanel.add(accelerationField);
        RacerPanel.add(new JLabel(""));
        RacerBtn = new JButton("Add racer");
        RacerBtn.addActionListener(this);
        RacerPanel.add(RacerBtn);

       RacerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(2,2,2,2)));

       startRaceBtn = new JButton("Start Race");
       startRaceBtn.addActionListener(this);
       showInfoBtn = new JButton("info");
        showInfoBtn.setPreferredSize(new Dimension(50,50));
       infoPanel.add(startRaceBtn);
       showInfoBtn.addActionListener(this);
       infoPanel.add(showInfoBtn);
       infoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(1,1,1,1)));
        infoPanel.add(new JLabel(""));

        rightPanel.add(BuildArenaPanel);
        rightPanel.add(RacerPanel);

        model= new DefaultTableModel(columnNames, 0);;
        table = new JTable(model);


        //////HM3////

        AddFromExistingRacer = new JButton("AddFromExistingRacer");
        AddFromExistingRacer.addActionListener(this);
        infoPanel.add(AddFromExistingRacer);

        BuilderRace = new JButton("Builder");
        BuilderRace.addActionListener(this);
        infoPanel.add(BuilderRace);
        rightPanel.add(infoPanel);

    }

    /**
     * for all the buttons exists a actionListener
     * @param e the event to be processed
     *       e.getSource() == createArenaBtn:
     *          when pressing the create arena button->make sure that all the input data is valid
     *          using the loadimage method to load the corresponding image to the displayPanel
     *          if the maxracers>14 the Racer images will be a little smaller + the size of the frame will be maxed
     *          NOTE: IF I DON'T DO THIS I CANT MAKE SURE ALL THE RACERS WILL BE SHOWN IN THE DISPLAYPANEL
     *          even if the frame is in fullscreen!!
     *
     *       (e.getSource() == RacerBtn) :
     *          making sure the input racers is valid
     *          taking the corresponding image of the racer,putting it in a label and adding it to the displayPanel
     *
     *        e.getSource() == showInfoBtn
     *          making the table for the racers data
     *          taking it from a class i made to pass all the data from the run()
     *
     *        e.getSource() == startRaceBtn:
     *          the runnable class is being activated using Threads
     *          to each of them, using run().
     *
     *
     *
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createArenaBtn) {
            displayPanel.removeAll();
            displayPanel.repaint();
            // Retrieve the selected values from the JComboBox and JTextFields
            String arenaType = (String) arenaComboBox.getSelectedItem();
            String arenaLength = lengthField.getText();
            String maxRacers = maxRacersField.getText();
            if (arenaLength.isBlank() || maxRacers.isBlank()) {
                JOptionPane.showMessageDialog(this, "Invalid input values. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int ArenaLength = Integer.parseInt(arenaLength);
                int MaxRacers = Integer.parseInt(maxRacers);
                if ((ArenaLength > 99 && ArenaLength < 3001) && (MaxRacers > 0 && MaxRacers < 21)) {
                    loadImage(arenaType);
                    displayPanel.setImage(this.image);
                    FactoryMethod(arenaType,ArenaLength,MaxRacers);
                    if (MaxRacers > 14) {
                        this.setSize(displayPanel.getWidth(), displayPanel.getHeight() + MaxRacers * 100);
                        this.repaint();
                        mainPanel.repaint();
                        displayPanel.setPreferredSize(new Dimension(this.getWidth() - rightPanel.getWidth(), this.getHeight() - rightPanel.getHeight()));
                        IconHight = 40;
                        IconWidth = 40;

                        displayPanel.repaint();
                    }
                    racers = null;
                    index1 = 0;
                    index2 = 0;
                    displayPanel.setPreferredSize(new Dimension(ArenaLength + 110, displayPanel.getHeight()));
                    threads = null;
                    displayPanel.removeAll();
                    displayPanel.repaint();

                } else {
                    JOptionPane.showMessageDialog(this, "Invalid input values. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (e.getSource() == RacerBtn) {

            if (!racerNameField.getText().isBlank() && !accelerationField.getText().isBlank() && !maxSpeedField.getText().isBlank()
                    && Integer.parseInt(accelerationField.getText()) >= 1 && Integer.parseInt(maxSpeedField.getText()) >= 1) {
                if (arena != null) {
//                    arena.registerObserver(this::updateRaceInfo);
                    double maxSpeed = Double.parseDouble(maxSpeedField.getText());
                    double acceleration = Double.parseDouble(accelerationField.getText());
                    String RacerType = (String) racerTypeComboBox.getSelectedItem();
                    String ColorType = (String) racerColorComboBox.getSelectedItem();
                    String racername = racerNameField.getText();

                    try {
                        BuildRacer(RacerType, racername, maxSpeed, acceleration, EnumColor(ColorType));
                    } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                             InstantiationException | IllegalAccessException e1) {
                        JOptionPane.showMessageDialog(this, "An error occurred1: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        arena.addRacer(racers);
                    } catch (RacerLimitException d) {
                        JOptionPane.showMessageDialog(this, "An error occurred2: " + d.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                        return;
                    } catch (RacerTypeException d) {
                        JOptionPane.showMessageDialog(this, "An error occurred3: " + d.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    loadImage(RacerType + ColorType);
                    Image img = this.icon.getImage();
                    Image fittedImg = img.getScaledInstance(IconWidth, IconHight, Image.SCALE_SMOOTH);
                    this.icon = new ImageIcon(fittedImg);
                    JLabel imgLabel = new JLabel();
                    imgLabel.setIcon(this.icon);
                    int laneY = calculateLaneYPosition(index2);
                    imgLabel.setBounds(START_LINE_X, laneY, IconWidth, IconHight);
                    Point p = new Point(START_LINE_X, laneY);
                    arena.getActiveRacers().get(index2).setCurrentLocation(p);
                    index2++;
                    displayPanel.add(imgLabel);
                    displayPanel.repaint();

                } else {
                    JOptionPane.showMessageDialog(this, "Build Arena First!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input values. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
//
        else if (e.getSource() == showInfoBtn) {
            if (arena != null && arena.hasActiveRacers()) {
                arena.addObserver(this);
                Object[][] data = new Object[arena.getActiveRacers().size()][5];

                this.model = (DefaultTableModel) table.getModel();
                model.setDataVector(data, columnNames);

                // Repaint the table to reflect the changes
                table.repaint();
                JFrame infoFrame = new JFrame("Race Information");
                infoFrame.add(new JScrollPane(table));
                infoFrame.pack();
                infoFrame.setLocationRelativeTo(null);
                infoFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(mainPanel, "you dont build arena of add racers", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == startRaceBtn) {
            threads = new ArrayList<>();
            DefaultTableModel model = new DefaultTableModel(columnNames, arena.getActiveRacers().size());
            Point p = new Point(VISUAL_FINISH_LINE_X, IconHight); // Finish line position at visual finish line
            for (int i = 0; i < arena.getActiveRacers().size(); i++) {
                threads.add(new Thread(new RaceRunnable(arena, displayPanel, arena.getActiveRacers().get(i), i, p)));
            }
            // Start all the threads at once
            for (Thread thread : threads) {
                thread.start();
            }
            /**
             * HM3
             * Calling Prototype method when the user want to copy existing Racer
             */
        } else if (e.getSource() == AddFromExistingRacer) {
            Prototype();
            /**
             * HM3
             * BuilderRace for the def builder , that make for the user an arena and  n Racer
             * the first Racer inizialize in BuilderDefRef .
             * using prototype , coping the first def Racer that we inizialize.
              */
        }else if(e.getSource() == BuilderRace){
            RacerMap.clear();
            index1 =0 ; index2=0;
            displayPanel.removeAll();
            displayPanel.repaint();
            String userInput = null;
            boolean validInput = false;
            while (!validInput) {

                userInput = JOptionPane.showInputDialog(null, "Enter integer number:");

                if (userInput == null) {
                    return;
                }else{

                    int number = Integer.parseInt(userInput);
                    if(number > 0 && number <=20){
                        loadImage("LandArena");
                        displayPanel.setImage(this.image);
                        FactoryMethod("LandArena",1000 , number);
                        if(arena != null) {
                            for (int i = 0; i < number; i++) {
                                if(i == 0) {
                                    try {
                                        BuildDefRacer();
                                    } catch (ClassNotFoundException | InvocationTargetException |
                                             NoSuchMethodException |
                                             InstantiationException | IllegalAccessException e1) {
                                        JOptionPane.showMessageDialog(this, "An error occurred1: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                }else {
                                    racers = getRacer(arena.getActiveRacers().get(0).className());

                                }
                                try {

                                    arena.addRacer(racers);
                                } catch (RacerLimitException d) {
                                    JOptionPane.showMessageDialog(this, "An error occurred2: " + d.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                                    return;
                                } catch (RacerTypeException d) {
                                    JOptionPane.showMessageDialog(this, "An error occurred3: " + d.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                loadImage("Car" + "RED");
                                Image img = this.icon.getImage();
                                Image fittedImg = img.getScaledInstance(IconWidth, IconHight, Image.SCALE_SMOOTH);
                                this.icon = new ImageIcon(fittedImg);
                                JLabel imgLabel = new JLabel();
                                imgLabel.setIcon(this.icon);
                                int laneY = calculateLaneYPosition(index2);
                                imgLabel.setBounds(START_LINE_X, laneY, IconWidth, IconHight);
                                Point p = new Point(START_LINE_X, laneY);
                                arena.getActiveRacers().get(index2).setCurrentLocation(p);
                                index2++;
                                displayPanel.add(imgLabel);
                                displayPanel.repaint();
                                validInput= true;
                            }
                        }
                    }else{

                        return;
                    }
                }
            }

        }
    }

    /**
     * this method puts the neccerray def conts , with its key(Class name)
     * to the Hashmap , to use it via RaceBuilder.
     * @param RacerType
     */
    public static void initializeRacers(String RacerType){
        switch (RacerType) {
            case "Horse":
                RacerMap.put("Horse", new Horse());
                break;
            case "Car":
                RacerMap.put("Car", new Car());
                break;
            case "AirPlane":
                RacerMap.put("AirPlane", new Airplane());
                break;
            case "Helicopter":
                RacerMap.put("Helicopter", new Helicopter());
                break;
            case "Bicycle":
                RacerMap.put("Bicycle", new Bicycle());
                break;
            case "RowBoat":
                RacerMap.put("RowBoat", new RowBoat());
                break;
            case "SpeedBoat":
                RacerMap.put("SpeedBoat", new SpeedBoat());
                break;

        }
    }

    /**
     * Here we using RacerMap (Hashmap) to get the RacerType(Car,Plane,etc) that given to inizialize its def const
     *
     * @param RacerType
     * @return
     */
    public static Racer getRacer(String RacerType){
        initializeRacers(RacerType);
        return (Racer) RacerMap.get(RacerType).clone();
    }

    /**
     * HM3
     * Prototype -> that copies the given racer index and adding it to the race itself .
     * asking for the changed color if the user wants to .
     *
     */
    public void Prototype(){
        if(arena != null) {
            String userInput = null;
            boolean validInput = false;
            String Colorinput = null;
            while (!validInput) {
                userInput = JOptionPane.showInputDialog(null, "Enter a Racer index you want to copy:");
                if (userInput == null) {
                    return;
                } else {
                    try {
                        int number = Integer.parseInt(userInput);

                        if (number >= 0 && number <= arena.getActiveRacers().size()) {
                            validInput = true;
                            racers = getRacer(arena.getActiveRacers().get(number).className());
                            int confirmOption = JOptionPane.showConfirmDialog(null, "Do you want to change the racer color?");

                            if (confirmOption == JOptionPane.YES_OPTION) {
                                Colorinput = JOptionPane.showInputDialog(null, "Enter the desired color:");
                                racers.setColor(EnumColor(Colorinput));

                            }
                            try{
                                arena.addRacer(racers);
                            } catch (RacerLimitException d) {
                                JOptionPane.showMessageDialog(this, "An error occurred2: " + d.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                                return;
                            } catch (RacerTypeException d) {
                                JOptionPane.showMessageDialog(this, "An error occurred3: " + d.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            loadImage(arena.getActiveRacers().get(number).className() + Colorinput);
                            Image img = this.icon.getImage();
                            Image fittedImg = img.getScaledInstance(IconWidth, IconHight, Image.SCALE_SMOOTH);
                            this.icon = new ImageIcon(fittedImg);
                            JLabel imgLabel = new JLabel();
                            imgLabel.setIcon(this.icon);
                            int laneY = calculateLaneYPosition(index2);
                            imgLabel.setBounds(START_LINE_X, laneY, IconWidth, IconHight);
                            Point p = new Point(START_LINE_X, laneY);
                            arena.getActiveRacers().get(index2).setCurrentLocation(p);
                            index2++;
                            displayPanel.add(imgLabel);
                            displayPanel.repaint();

                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a number between 0 and " + arena.getActiveRacers().size());
                        }
                    } catch (NumberFormatException d) {
                        JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.");
                    }
                }
            }
        }else{
            JOptionPane.showMessageDialog(this, "Build Arena First!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    @Override
    public void update(Object data) {
        RacerData racerData = (RacerData) data;
        Object[][] newData = racerData.getData();
        int racerIndex = racerData.getIndex();
        this.model.setValueAt(newData[racerIndex][0], racerIndex, 0);
        this.model.setValueAt(newData[racerIndex][1], racerIndex, 1);
        this.model.setValueAt(newData[racerIndex][2], racerIndex, 2);
        this.model.setValueAt(newData[racerIndex][3], racerIndex, 3);
        this.model.setValueAt(newData[racerIndex][4], racerIndex, 4);
        table.repaint();
    }

    /**
     * H.m3 FactoryMethod
     * @param arenaType
     * @param ArenaLength
     * @param MaxRacers
     */
    public void FactoryMethod(String arenaType,int ArenaLength,int MaxRacers ){
        RaceBuilder builder = RaceBuilder.getInstance();
        if (arenaType.equals("AerialArena")) {
            try {
                arena = builder.buildArena("game.arenas.air.AerialArena", ArenaLength, MaxRacers);
                System.out.println("Aerial Arena created successfully!");
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
                     InstantiationException
                     | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                System.out.println("Unable to build arena: " + e1.getMessage());
                e1.printStackTrace();
            }
        } else if (arenaType.equals("LandArena")) {
            try {
                arena = builder.buildArena("game.arenas.land.LandArena", ArenaLength, MaxRacers);
                System.out.println("Land Arena created successfully!");
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
                     InstantiationException
                     | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                System.out.println("Unable to build arena: " + e1.getMessage());
                e1.printStackTrace();
            }
        } else if (arenaType.equals("NavalArena")) {
            try {
                arena = builder.buildArena("game.arenas.naval.NavalArena", ArenaLength, MaxRacers);
                System.out.println("Naval Arena created successfully!");
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
                     InstantiationException
                     | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                System.out.println("Unable to build arena: " + e1.getMessage());
                e1.printStackTrace();
            }
        }
    }


    /**
     * H.m3 Car Builder for Builder( Added an def constructor caller ).
     * the implemation is in RaceBuilder , new method for def constructor .
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void BuildDefRacer() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
        racers=(builder.buildWheeledRacer("game.racers.land.Car"));
    }
    /**
     * Racer Builder , switch for all the racers
     * @param RacerType
     * @param Name
     * @param Maxspeed
     * @param Acceleration
     * @param color
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void BuildRacer(String RacerType,String Name,double Maxspeed,double Acceleration,EnumContainer.Color color) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        switch (RacerType){
            case "Airplane":
                racers = (builder.buildWheeledRacer("game.racers.air.Airplane", Name, Maxspeed, Acceleration, color, 3));
                break;
            case "Helicopter":
                racers= (builder.buildRacer("game.racers.air.Helicopter", Name, Maxspeed, Acceleration, color));
                break;
            case "Horse":
                racers=(builder.buildRacer("game.racers.land.Horse", Name, Maxspeed, Acceleration, color));
                break;
            case "Car":
                racers=(builder.buildWheeledRacer("game.racers.land.Car", Name, Maxspeed, Acceleration, color, 4));
                break;
            case "Bicycle":
                racers=(builder.buildWheeledRacer("game.racers.land.Bicycle", Name, Maxspeed, Acceleration, color, 3));
                break;
            case "RowBoat":
                racers=(builder.buildRacer("game.racers.naval.RowBoat", Name, Maxspeed, Acceleration, color));
                break;
            case "SpeedBoat":
                racers=(builder.buildRacer("game.racers.naval.SpeedBoat", Name, Maxspeed, Acceleration, color));
                break;
        }

    }

    /**
     * switch-case for the enumcolor of the racer
     * @param cName (String)
     * @return  EnumContainer.Color
     */
    public EnumContainer.Color EnumColor(String cName){
        EnumContainer.Color color;
        switch(cName) {
            case "Red":
                color = EnumContainer.Color.RED;
                break;
            case "Green":
                color = EnumContainer.Color.GREEN;
                break;
            case "Blue":
                color = EnumContainer.Color.BLUE;
                break;
            case "Black":
                color = EnumContainer.Color.BLACK;
                break;
            case "Yellow":
                color = EnumContainer.Color.YELLOW;
                break;
            default:
                color = null;
                break;
        }
        return color;
    }

    /**
     * Calculate the Y position for a vehicle based on its lane number
     * @param laneNumber The lane number (0-based, 0 = first lane)
     * @return Y position for the vehicle center
     */
    public static int calculateLaneYPosition(int laneNumber) {
        return TRACK_START_Y + (laneNumber * LANE_HEIGHT) + (LANE_HEIGHT / 2) - (IconHight / 2);
    }
    
    /**
     * load the images from the folder using IMAGES_PATH
     * @param filename
     */
    public  void loadImage(String filename) {
        String imagePath;
        try {
            if(filename.contains("Arena")) {
                 imagePath = IMAGES_PATH + "/" + filename + ".jpg";
                File imageFile = new File(imagePath);
                if(imageFile.exists()) {
                    this.image = ImageIO.read(imageFile);
                    System.out.println("Arena image loaded successfully: " + imagePath);
                } else {
                    System.out.println("Arena image not found: " + imagePath);
                }
            }
            else{
                imagePath = IMAGES_PATH + "/" + filename + ".png";
                File imageFile = new File(imagePath);
                if(imageFile.exists()) {
                    ImageIcon icon = new ImageIcon(imagePath);
                    this.icon = icon;
                    System.out.println("Racer image loaded successfully: " + imagePath);
                } else {
                    System.out.println("Racer image not found: " + imagePath);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        MainScreen Frame1 = new MainScreen();
        Frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame1.add(Frame1.mainPanel);
        
        // Get screen dimensions and set window to full screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Frame1.setSize(screenSize);
        Frame1.setLocationRelativeTo(null); // Center the window
        
        // Alternative: Try both approaches
        Frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Make visible
        Frame1.setVisible(true);
    }
}


/**
 * Class myPanel for the displayPanel -> image field, setimage method,and PaintComponent for the repaint()
 * method, to repaint the racers for each of the point they move on the screen.
 */
class myPanel extends  JPanel{
    private  Image img;
    private  int Width=1000;
    public myPanel() {
        super();
        setLayout(null); // Use absolute positioning to prevent layout manager interference
       setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(""),
                BorderFactory.createEmptyBorder(2, 2, 2, 2)));

       setPreferredSize(new Dimension(Width, 700));
        img = null;
    }
    public void setImage(Image img) {
        this.img = img;
        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Scale the image to fit the size of the panel.
        if (img != null) {
            Image scaledImage = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

/**
 * Runnable class for the Threads
 * here we override the run() method, and doing  the race+appearance on the screen
 * for the racer to be "simultaneously" run.
 *
 */
class RaceRunnable implements Runnable {
    private Arena arena;
    private JPanel displayPanel;
    private  Racer racers;
    private static int index3=1;
    private int i;
    private Point point;

    private ArrayList<Racer> AllRacers = new ArrayList<>();
    double probability = 0.01;
    Lock l1 = new ReentrantLock();
    Lock l2 = new ReentrantLock();
    Lock l3 = new ReentrantLock();

    /**
     * RaceRunnable Construction
     * @param arena
     * @param displayPanel
     * @param racer
     * @param index
     * @param p
     */
    public RaceRunnable(Arena arena, JPanel displayPanel,Racer racer,int index,Point p) {
        this.arena = arena;
        this.displayPanel = displayPanel;
        this.racers = racer;

        this.i=index;
        this.point = p;
        this.AllRacers = arena.getActiveRacers();
    }
    public static synchronized int RacerPlace(Racer[] racersArr,String name){
        double[] PositionalX = new double[racersArr.length];
        int position = 0;
        for (int i = 0; i < racersArr.length; i++) {
            if(racersArr[i]!=null){
                PositionalX[i] = racersArr[i].getCurrentLocation().getX();
            }
        }
        Arrays.sort(PositionalX);
        for (int i = 0; i < racersArr.length; i++) {
            for (int j = 0; j < PositionalX.length; j++) {
                if(racersArr[i]!=null) {
                    if((racersArr[i].getRacerName() == name )&& PositionalX[j] == racersArr[i].getCurrentLocation().getX()){
                        position = j;
                    }
                }

            }
        }
        return PositionalX.length - position;
    }


    /**
     * Object[][] newData acting like a matrix to pass to the RacerData instance for infobutton.
     * moving the racers.
     * repainting every move of the racer to the screen
     * arena notify!!
     */
    @Override
    public void run() {
        int WidthArenaRatio = (int)((point.getX() - arena.getLength())) ;
        Object[][] newData = new Object[arena.getActiveRacers().size()][5];
        racers.setState(new ActiveState());
        while (racers.getCurrentLocation().getX() < point.getX() ) {
            l3.lock();
            racers.move(arena.getFRICTION());
            l3.unlock();
            if (racers.hasMishap() && !racers.getMishap().isFixable()) {
                Random random = new Random();
                double randomNumber = random.nextDouble();
                if(randomNumber < probability) {
                    racers.setState(new FailedState());
                    newData[i][0] = "Failed";
                    newData[i][1] = "Failed";
                    newData[i][2] = "Failed";
                    newData[i][3] = "Failed";
                    newData[i][4] = "Failed";
                    arena.notifyObservers(new RacerData(newData, i));
                    return;
                }
            }

            if (racers.getCurrentLocation().getX() >= point.getX()) {
                displayPanel.getComponent(i).setBounds((int) racers.getCurrentLocation().getX(), (int) racers.getCurrentLocation().getY(), MainScreen.IconWidth, MainScreen.IconHight);
                newData[i][0] = racers.getRacerName();
                newData[i][1] = racers.getCurrentSpeed();
                newData[i][2] = racers.getMaxSpeed();
                newData[i][3] = point.getX();
                newData[i][4] = (racers.getCurrentLocation().getX() >= point.getX()) ? "Yes" : "No";
                arena.notifyObservers(new RacerData(newData,i));

            }
            else {
                l2.lock();
                if(racers.getState() instanceof ActiveState){
                    System.out.println(racers.getName() + " in Position " +  RacerPlace(AllRacers.toArray(new Racer [AllRacers.size()]), racers.getRacerName()));
                }
                if(racers.hasMishap() && (racers.getState() instanceof ActiveState)){racers.setState(new BrokenState());}
                if (!racers.hasMishap() && racers.getState() instanceof BrokenState) {
                    racers.setState(new ActiveState());
                }
                l2.unlock();
                displayPanel.getComponent(i).setBounds((int) racers.getCurrentLocation().getX(), (int) racers.getCurrentLocation().getY(), MainScreen.IconWidth, MainScreen.IconHight);
                displayPanel.getComponent(i).repaint();
                newData[i][0] = racers.getRacerName();
                newData[i][1] = racers.getCurrentSpeed();
                newData[i][2] = racers.getMaxSpeed();
                newData[i][3] = racers.getCurrentLocation().getX();
                newData[i][4] = (racers.getCurrentLocation().getX() > point.getX()) ? "Yes" : "No";
                arena.notifyObservers(new RacerData(newData,i));

            }
            displayPanel.repaint();
            try {
                Thread.sleep(100); // add a small delay between each movement
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
            newData[i][0] = racers.getRacerName();
            newData[i][1] = racers.getCurrentSpeed();
            newData[i][2] = racers.getMaxSpeed();
            newData[i][3] = point.getX();
            newData[i][4] = (racers.getCurrentLocation().getX() >= point.getX()) ? "Yes" : "No";
            arena.notifyObservers(new RacerData(newData,i));
            displayPanel.getComponent(i).setBounds((int) racers.getCurrentLocation().getX(), (int) racers.getCurrentLocation().getY(), MainScreen.IconWidth, MainScreen.IconHight);
            displayPanel.getComponent(i).repaint();
            displayPanel.repaint();

            l1.lock();
            racers.setState(new FinishedState());
            System.out.println(index3 + " place: " + racers.getRacerName());
            index3 ++;
            l1.unlock();
        arena.crossFinishLine(racers);

    }


}

/**
 * RacerData Class -> to pass the data in an instance for the info table
 */
class RacerData {
    private Object[][] data;
    private int index;

    public RacerData(Object[][] data, int index) {
        this.data = data;
        this.index = index;
    }

    public Object[][] getData() {
        return data;
    }

    public int getIndex() {
        return index;
    }
}




package Game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.TextField;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.awt.Point;
import java.net.NetworkInterface;
import java.util.Enumeration;
import org.newdawn.slick.Sound;
public class Game extends BasicGame {
    public static GameContainer container;
    public static int mouseX, mouseY;
                    MULTIPLAYERGAMEOVERSTATE = 4, LEADERBOARDSTATE = 5, PAUSESTATE = 6, GAMEOVERSTATE = 7;
    static boolean canCreateConnection = true;

    public static Button quitButton;
    public static Button multiplayerButton;
    public static Button resumeButton;
    public static Button serverButton;

    static GameFont playFont;
    static GameFont leaderboardFont;
    static GameFont multiplayerFont;
    static Image titleImage;
    static Image[] ghosts = new Image[4];

    static ArrayList<Enemy> enemyList = new ArrayList<>();
    static Player player;
    static int bulletCount = 0, enemyCount = 0, ammosCount = 0;
    static int ENEMY_DELAY = 500;
    static int canSpawnEnemy = ENEMY_DELAY;
    static int increaseDifficulty = NEW_DIFFICULTY_DELAY;
    static Random enemyPositionX = new Random();

    static GameFont DestinationPortFont;
    static TextField IPTextField, DestinationPortTextField, SourcePortTextField;
    static int SourcePort, DestinationPort;
    static boolean opponentFired = false;
    static ArrayList<Bullet> opponentBulletList = new ArrayList<>();
    static Point opponentCoordinates = new Point(Window.HALF_WIDTH, Window.HALF_HEIGHT);
    static int opponentScreenHeight = 0, opponentScreenWidth = 0;
    public static Sound shootSound;
    static Connection connection;
    static String infoString = "";
    public static int players = 0;

    static boolean isServer = false;
    static UDPReceiverThread receiver;


            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                NetworkInterface iface = interfaces.nextElement();
                if (iface.isLoopback() || !iface.isUp()) {
                }
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                    InetAddress address = addresses.nextElement();
                }
        } catch (SocketException e) {
        }
        AppGameContainer app = new AppGameContainer(new Game("Pac Man: The Top Down Shooter"));
        app.start();

        super(title);

    @Override

        gc.setTargetFrameRate(60);
        gc.setAlwaysRender(true);
        gc.setClearEachFrame(true);
        gc.setVerbose(true);

        replayFont = new GameFont(64f);
        escFont = new GameFont(44f);
        smallFont = new GameFont(24f);
        DestinationPortFont = new GameFont(44f);

        Score.resetScore();
        IPTextField = new TextField(gc, IPFont.getFont(), Window.HALF_WIDTH - 180, Window.HALF_HEIGHT - 200,         IPTextField.setBorderColor(Color.transparent);
        IPTextField.setCursorVisible(false);
ourcePortTextField = new TextField(gc, DestinationPortFont.getFont(), Window.HALF_WIDTH, Window.HALF_HEIGHT - 120        SourcePortTextField.setBorderColor(Color.transparent);
        SourcePortTextField.setCursorVisible(false);
        DestinationPortTextField = new TextField(gc, SourcePortFont.getFont(), Window.HALF_WIDTH - 40, Window.        DestinationPortTextField.setBorderColor(Color.transparent);
        DestinationPortTextField.setCursorVisible(false);
playButton = new Button("PLAY (ENTER)", Window.HALF_WIDTH, Window.HALF_HEIGHT, 64, optionBlue);
optionBlue);        leaderboardButton = new Button("LEADERBOARD (L)", Window.HALF_WIDTH, Window.HALF_HEIGHT + 128, 44, optionBlue);
        quitButton = new Button("MENU (ESC)", Window.HALF_WIDTH, Window.HEIGHT - Window.HEIGHT/20, 44, Color.red);
        resuyButton = new Button("RETRY (R)", Window.HALF_WIDTH, Window.HALF_HEIGHT, 64, optionBlue);
        servMultiplayerScoresButton = new Button("SHOW MULTIPLAYER HIGHSCORES", Window.HALF_WIDTH, quitButton.getY() -  Window.HEIGHT/16, 24, Color.white);
er = new Player();

            ammos[i] = new Ammo();
        try {
            youLostImage = new Image("Images/GameLost.png");
            ghosts[1] = new Image("Images/Blinky.png");
            ghosts[3] = new Image("Images/Pinky.png");
            shootSound = new Sound("Sounds/pacmanShoot.wav");
            ObjectInputStream inStream = new ObjectInputStream(new FileInputStream("pmmtds.scores"));

            System.out.println("SCORES FILE NOT FOUND");
            System.out.println("SCORES FILE EMPTY");
            System.out.println("ONE OR MORE IMAGES NOT FOUND");
        Score.resetScore();
    }
    @Override

        mouseX = input.getMouseX();



                break;
            case GAMEPLAYSTATE:
                GamePlayState.update(gc, input, delta, mouseX, mouseY);


                break;
            case MULTIPLAYERGAMEPLAYSTATE:
                createConnection();
                break;
            case MULTIPLAYERGAMEOVERSTATE:
                MultiplayerGameOverState.update(gc, input, delta, mouseX, mouseY);


                break;
            case PAUSESTATE:
                PauseState.update(gc, input, delta, mouseX, mouseY);


                break;
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        switch (state) {
            case MENUSTATE:
                MenuState.render(gc, g);


                break;
            case MULTIPLAYERMENUSTATE:
                MultiplayerMenuState.render(gc, g);


                break;
            case MULTIPLAYERGAMEOVERSTATE:
                MultiplayerGameOverState.render(gc, g);


                break;
            case PAUSESTATE:
                PauseState.render(gc, g);


                break;
    }



            try {
                connection.connect(SourcePort, address);

                System.out.println("ERROR: IP, SOURCE PORT or DESTINATION PORT NOT VALID " + e);

            if (isServer) {
                server = new UDPServerThread(serverPort, 2);
                server.start();
            try {
                InetAddress serverAddress;
                    serverAddress = InetAddress.getByName("localhost");
                    serverAddress = InetAddress.getByName(IP);
                sender = new UDPSenderThread(serverAddress, serverPort);
                receiver = new UDPReceiverThread(sender.getSocket());

                System.out.println("Socket Exception");
                System.out.println("Unknown Host");
            }
        }
}

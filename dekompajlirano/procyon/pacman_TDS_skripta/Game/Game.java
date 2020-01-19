// 
// Decompiled by Procyon v0.5.36
// 

package Game;

import java.net.UnknownHostException;
import org.newdawn.slick.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.SlickException;
import java.util.Enumeration;
import org.newdawn.slick.AppGameContainer;
import java.net.SocketException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.InetSocketAddress;
import org.newdawn.slick.Sound;
import java.awt.Point;
import org.newdawn.slick.gui.TextField;
import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.BasicGame;

public class Game extends BasicGame
{
    public static GameContainer container;
    static Input input;
    public static int mouseX;
    public static int mouseY;
    static final int MENUSTATE = 0;
    static final int GAMEPLAYSTATE = 1;
    static final int MULTIPLAYERMENUSTATE = 2;
    static final int MULTIPLAYERGAMEPLAYSTATE = 3;
    static final int MULTIPLAYERGAMEOVERSTATE = 4;
    static final int LEADERBOARDSTATE = 5;
    static final int PAUSESTATE = 6;
    static final int GAMEOVERSTATE = 7;
    static int state;
    static boolean canCreateConnection;
    static boolean paused;
    public static Button leaderboardButton;
    public static Button quitButton;
    public static Button playButton;
    public static Button multiplayerButton;
    public static Button menuButton;
    public static Button resumeButton;
    public static Button retryButton;
    public static Button serverButton;
    public static Button showMultiplayerScoresButton;
    static GameFont bigFont;
    static GameFont playFont;
    static GameFont replayFont;
    static GameFont leaderboardFont;
    static GameFont escFont;
    static GameFont multiplayerFont;
    static GameFont smallFont;
    static Image titleImage;
    static Image youLostImage;
    static Image[] ghosts;
    static ScoreManager Score;
    static LinkedList<Bullet> bulletList;
    static ArrayList<Enemy> enemyList;
    static Ammo[] ammos;
    static Player player;
    static int bulletCount;
    static int enemyCount;
    static int ammosCount;
    static final int START_DELAY = 500;
    static final int AMMOS_DELAY = 10000;
    static final int NEW_DIFFICULTY_DELAY = 15000;
    static final int BULLET_DELAY = 200;
    static int ENEMY_DELAY;
    static int startDelay;
    static int canSpawnEnemy;
    static int canSpawnAmmo;
    static int increaseDifficulty;
    static int canFire;
    static Random enemyPositionX;
    static Random enemyPositionY;
    static GameFont IPFont;
    static GameFont DestinationPortFont;
    static GameFont SourcePortFont;
    static TextField IPTextField;
    static TextField DestinationPortTextField;
    static TextField SourcePortTextField;
    static String IP;
    static int SourcePort;
    static int DestinationPort;
    static boolean opponentFired;
    static Opponent opponent;
    static ArrayList<Bullet> opponentBulletList;
    static int oppBulletCount;
    static Point opponentCoordinates;
    static Point opponentMouseCoordinates;
    static int opponentScreenHeight;
    static int opponentScreenWidth;
    public static Sound openingSound;
    public static Sound shootSound;
    static Connection connection;
    static InetSocketAddress address;
    static String infoString;
    static String winnerString;
    public static int players;
    public static int multiplayerGameID;
    static UDPServerThread server;
    static boolean isServer;
    static UDPSenderThread sender;
    static UDPReceiverThread receiver;
    public static String localIP;
    
    public static void main(final String[] args) throws SlickException {
        try {
            final Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                final NetworkInterface iface = interfaces.nextElement();
                if (!iface.isLoopback()) {
                    if (!iface.isUp()) {
                        continue;
                    }
                    final Enumeration<InetAddress> addresses = iface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        final InetAddress address = addresses.nextElement();
                        Game.localIP = address.getHostAddress();
                    }
                }
            }
        }
        catch (SocketException e) {
            throw new RuntimeException(e);
        }
        final AppGameContainer app = new AppGameContainer((org.newdawn.slick.Game)new Game("Pac Man: The Top Down Shooter"));
        app.setDisplayMode(Window.WIDTH, Window.HEIGHT, true);
        app.start();
    }
    
    public Game(final String title) {
        super(title);
    }
    
    public void init(final GameContainer gc) throws SlickException {
        gc.setMaximumLogicUpdateInterval(60);
        gc.setTargetFrameRate(60);
        gc.setVSync(true);
        gc.setAlwaysRender(true);
        gc.setShowFPS(false);
        gc.setClearEachFrame(true);
        gc.setSmoothDeltas(false);
        gc.setVerbose(true);
        gc.setMouseCursor("Images/Aim.png", 16, 16);
        Game.playFont = new GameFont(64.0f);
        Game.replayFont = new GameFont(64.0f);
        Game.leaderboardFont = new GameFont(44.0f);
        Game.escFont = new GameFont(44.0f);
        Game.multiplayerFont = new GameFont(44.0f);
        Game.smallFont = new GameFont(24.0f);
        Game.IPFont = new GameFont(54.0f);
        Game.DestinationPortFont = new GameFont(44.0f);
        Game.SourcePortFont = new GameFont(44.0f);
        (Game.Score = new ScoreManager()).resetScore();
        (Game.IPTextField = new TextField((GUIContext)gc, (Font)Game.IPFont.getFont(), Window.HALF_WIDTH - 180, Window.HALF_HEIGHT - 200, Game.IPFont.getStringWidth("555555555555555"), 54)).setBorderColor(Color.transparent);
        Game.IPTextField.setMaxLength(15);
        Game.IPTextField.setCursorVisible(false);
        (Game.SourcePortTextField = new TextField((GUIContext)gc, (Font)Game.DestinationPortFont.getFont(), Window.HALF_WIDTH, Window.HALF_HEIGHT - 120, Game.DestinationPortFont.getStringWidth("55555"), 44)).setBorderColor(Color.transparent);
        Game.SourcePortTextField.setMaxLength(5);
        Game.SourcePortTextField.setCursorVisible(false);
        (Game.DestinationPortTextField = new TextField((GUIContext)gc, (Font)Game.SourcePortFont.getFont(), Window.HALF_WIDTH - 40, Window.HALF_HEIGHT - 48, Game.SourcePortFont.getStringWidth("55555"), 44)).setBorderColor(Color.transparent);
        Game.DestinationPortTextField.setMaxLength(5);
        Game.DestinationPortTextField.setCursorVisible(false);
        Game.playButton = new Button("PLAY (ENTER)", Window.HALF_WIDTH, Window.HALF_HEIGHT, 64, GameFont.optionBlue);
        Game.multiplayerButton = new Button("MULTIPLAYER (M)", Window.HALF_WIDTH, Window.HALF_HEIGHT + 64, 44, GameFont.optionBlue);
        Game.leaderboardButton = new Button("LEADERBOARD (L)", Window.HALF_WIDTH, Window.HALF_HEIGHT + 128, 44, GameFont.optionBlue);
        Game.quitButton = new Button("QUIT (ESC)", Window.HALF_WIDTH, Window.HEIGHT - Window.HEIGHT / 20, 44, Color.red);
        Game.menuButton = new Button("MENU (ESC)", Window.HALF_WIDTH, Window.HEIGHT - Window.HEIGHT / 20, 44, Color.red);
        Game.resumeButton = new Button("RESUME (R)", Window.HALF_WIDTH, Window.HALF_HEIGHT, 64, GameFont.optionBlue);
        Game.retryButton = new Button("RETRY (R)", Window.HALF_WIDTH, Window.HALF_HEIGHT, 64, GameFont.optionBlue);
        Game.serverButton = new Button("SERVER", Window.HALF_WIDTH, Window.HALF_HEIGHT + 40, 32, Color.red);
        Game.showMultiplayerScoresButton = new Button("SHOW MULTIPLAYER HIGHSCORES", Window.HALF_WIDTH, Game.quitButton.getY() - Window.HEIGHT / 16, 24, Color.white);
        Game.player = new Player();
        Game.opponent = new Opponent();
        for (int i = 0; i < 10; ++i) {
            Game.ammos[i] = new Ammo();
        }
        try {
            Game.titleImage = new Image("Images/Title.png");
            Game.youLostImage = new Image("Images/GameLost.png");
            Game.ghosts[0] = new Image("Images/Clyde.png");
            Game.ghosts[1] = new Image("Images/Blinky.png");
            Game.ghosts[2] = new Image("Images/Inky.png");
            Game.ghosts[3] = new Image("Images/Pinky.png");
            Game.openingSound = new Sound("Sounds/openingSound.wav");
            Game.shootSound = new Sound("Sounds/pacmanShoot.wav");
            final ObjectInputStream inStream = new ObjectInputStream(new FileInputStream("pmmtds.scores"));
            Game.Score = (ScoreManager)inStream.readObject();
        }
        catch (IOException e) {
            System.out.println("SCORES FILE NOT FOUND");
        }
        catch (ClassNotFoundException e2) {
            System.out.println("SCORES FILE EMPTY");
        }
        catch (SlickException e3) {
            System.out.println("ONE OR MORE IMAGES NOT FOUND");
        }
        Game.Score.resetScore();
    }
    
    public void update(final GameContainer gc, final int delta) throws SlickException {
        Game.input = gc.getInput();
        Game.mouseX = Game.input.getMouseX();
        Game.mouseY = Game.input.getMouseY();
        switch (Game.state) {
            case 0: {
                MenuState.update(gc, Game.input, delta, Game.mouseX, Game.mouseY);
                break;
            }
            case 1: {
                GamePlayState.update(gc, Game.input, delta, Game.mouseX, Game.mouseY);
                break;
            }
            case 2: {
                MultiplayerMenuState.update(gc, Game.input, delta, Game.mouseX, Game.mouseY);
                break;
            }
            case 3: {
                this.createConnection();
                MultiplayerGamePlayState.update(gc, Game.input, delta, Game.mouseX, Game.mouseY);
                break;
            }
            case 4: {
                MultiplayerGameOverState.update(gc, Game.input, delta, Game.mouseX, Game.mouseY);
                break;
            }
            case 5: {
                LeaderBoardState.update(gc, Game.input, delta, Game.mouseX, Game.mouseY);
                break;
            }
            case 6: {
                PauseState.update(gc, Game.input, delta, Game.mouseX, Game.mouseY);
                break;
            }
            case 7: {
                GameOverState.update(gc, Game.input, delta, Game.mouseX, Game.mouseY);
                break;
            }
        }
    }
    
    public void render(final GameContainer gc, final Graphics g) throws SlickException {
        switch (Game.state) {
            case 0: {
                MenuState.render(gc, g);
                break;
            }
            case 1: {
                GamePlayState.render(gc, g);
                break;
            }
            case 2: {
                MultiplayerMenuState.render(gc, g);
                break;
            }
            case 3: {
                MultiplayerGamePlayState.render(gc, g);
                break;
            }
            case 4: {
                MultiplayerGameOverState.render(gc, g);
                break;
            }
            case 5: {
                LeaderBoardState.render(gc, g);
                break;
            }
            case 6: {
                PauseState.render(gc, g);
                break;
            }
            case 7: {
                GameOverState.render(gc, g);
                break;
            }
        }
    }
    
    private void createConnection() {
        if (Game.canCreateConnection) {
            Game.address = new InetSocketAddress(Game.IP, Game.DestinationPort);
            try {
                (Game.connection = new Connection()).connect(Game.SourcePort, Game.address);
                Game.connection.start();
            }
            catch (SocketException e) {
                System.out.println("ERROR: IP, SOURCE PORT or DESTINATION PORT NOT VALID " + e);
            }
            final int serverPort = 7777;
            if (Game.isServer) {
                (Game.server = new UDPServerThread(serverPort, 2)).isRunning(true);
                Game.server.start();
            }
            try {
                InetAddress serverAddress;
                if (Game.isServer) {
                    serverAddress = InetAddress.getByName("localhost");
                }
                else {
                    serverAddress = InetAddress.getByName(Game.IP);
                }
                (Game.sender = new UDPSenderThread(serverAddress, serverPort)).start();
                (Game.receiver = new UDPReceiverThread(Game.sender.getSocket())).start();
            }
            catch (SocketException e2) {
                System.out.println("Socket Exception");
            }
            catch (UnknownHostException e3) {
                System.out.println("Unknown Host");
            }
            Game.canCreateConnection = false;
        }
    }
    
    static {
        Game.state = 0;
        Game.canCreateConnection = true;
        Game.paused = false;
        Game.ghosts = new Image[4];
        Game.bulletList = new LinkedList<Bullet>();
        Game.enemyList = new ArrayList<Enemy>();
        Game.ammos = new Ammo[10];
        Game.bulletCount = 0;
        Game.enemyCount = 0;
        Game.ammosCount = 0;
        Game.ENEMY_DELAY = 500;
        Game.startDelay = 500;
        Game.canSpawnEnemy = Game.ENEMY_DELAY;
        Game.canSpawnAmmo = 10000;
        Game.increaseDifficulty = 15000;
        Game.canFire = 0;
        Game.enemyPositionX = new Random();
        Game.enemyPositionY = new Random();
        Game.opponentFired = false;
        Game.opponentBulletList = new ArrayList<Bullet>();
        Game.oppBulletCount = 0;
        Game.opponentCoordinates = new Point(Window.HALF_WIDTH, Window.HALF_HEIGHT);
        Game.opponentMouseCoordinates = new Point();
        Game.opponentScreenHeight = 0;
        Game.opponentScreenWidth = 0;
        Game.infoString = "";
        Game.winnerString = "";
        Game.players = 0;
        Game.multiplayerGameID = 0;
        Game.isServer = false;
        Game.localIP = null;
    }
}

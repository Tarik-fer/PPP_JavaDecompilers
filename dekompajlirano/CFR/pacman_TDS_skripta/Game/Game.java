/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  org.newdawn.slick.AppGameContainer
 *  org.newdawn.slick.BasicGame
 *  org.newdawn.slick.Color
 *  org.newdawn.slick.Font
 *  org.newdawn.slick.Game
 *  org.newdawn.slick.GameContainer
 *  org.newdawn.slick.Graphics
 *  org.newdawn.slick.Image
 *  org.newdawn.slick.Input
 *  org.newdawn.slick.SlickException
 *  org.newdawn.slick.Sound
 *  org.newdawn.slick.TrueTypeFont
 *  org.newdawn.slick.gui.GUIContext
 *  org.newdawn.slick.gui.TextField
 */
package Game;

import Game.Ammo;
import Game.Bullet;
import Game.Button;
import Game.Connection;
import Game.Enemy;
import Game.GameFont;
import Game.GameOverState;
import Game.GamePlayState;
import Game.LeaderBoardState;
import Game.MenuState;
import Game.MultiplayerGameOverState;
import Game.MultiplayerGamePlayState;
import Game.MultiplayerMenuState;
import Game.Opponent;
import Game.PauseState;
import Game.Player;
import Game.ScoreManager;
import Game.UDPReceiverThread;
import Game.UDPSenderThread;
import Game.UDPServerThread;
import Game.Window;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Random;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

public class Game
extends BasicGame {
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

    public static void main(String[] args) throws SlickException {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                if (iface.isLoopback() || !iface.isUp()) continue;
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    localIP = address.getHostAddress();
                }
            }
        }
        catch (SocketException e) {
            throw new RuntimeException(e);
        }
        AppGameContainer app = new AppGameContainer((org.newdawn.slick.Game)new Game("Pac Man: The Top Down Shooter"));
        app.setDisplayMode(Window.WIDTH, Window.HEIGHT, true);
        app.start();
    }

    public Game(String title) {
        super(title);
    }

    public void init(GameContainer gc) throws SlickException {
        gc.setMaximumLogicUpdateInterval(60);
        gc.setTargetFrameRate(60);
        gc.setVSync(true);
        gc.setAlwaysRender(true);
        gc.setShowFPS(false);
        gc.setClearEachFrame(true);
        gc.setSmoothDeltas(false);
        gc.setVerbose(true);
        gc.setMouseCursor("Images/Aim.png", 16, 16);
        playFont = new GameFont(64.0f);
        replayFont = new GameFont(64.0f);
        leaderboardFont = new GameFont(44.0f);
        escFont = new GameFont(44.0f);
        multiplayerFont = new GameFont(44.0f);
        smallFont = new GameFont(24.0f);
        IPFont = new GameFont(54.0f);
        DestinationPortFont = new GameFont(44.0f);
        SourcePortFont = new GameFont(44.0f);
        Score = new ScoreManager();
        Score.resetScore();
        IPTextField = new TextField((GUIContext)gc, (Font)IPFont.getFont(), Window.HALF_WIDTH - 180, Window.HALF_HEIGHT - 200, IPFont.getStringWidth("555555555555555"), 54);
        IPTextField.setBorderColor(Color.transparent);
        IPTextField.setMaxLength(15);
        IPTextField.setCursorVisible(false);
        SourcePortTextField = new TextField((GUIContext)gc, (Font)DestinationPortFont.getFont(), Window.HALF_WIDTH, Window.HALF_HEIGHT - 120, DestinationPortFont.getStringWidth("55555"), 44);
        SourcePortTextField.setBorderColor(Color.transparent);
        SourcePortTextField.setMaxLength(5);
        SourcePortTextField.setCursorVisible(false);
        DestinationPortTextField = new TextField((GUIContext)gc, (Font)SourcePortFont.getFont(), Window.HALF_WIDTH - 40, Window.HALF_HEIGHT - 48, SourcePortFont.getStringWidth("55555"), 44);
        DestinationPortTextField.setBorderColor(Color.transparent);
        DestinationPortTextField.setMaxLength(5);
        DestinationPortTextField.setCursorVisible(false);
        playButton = new Button("PLAY (ENTER)", Window.HALF_WIDTH, Window.HALF_HEIGHT, 64, GameFont.optionBlue);
        multiplayerButton = new Button("MULTIPLAYER (M)", Window.HALF_WIDTH, Window.HALF_HEIGHT + 64, 44, GameFont.optionBlue);
        leaderboardButton = new Button("LEADERBOARD (L)", Window.HALF_WIDTH, Window.HALF_HEIGHT + 128, 44, GameFont.optionBlue);
        quitButton = new Button("QUIT (ESC)", Window.HALF_WIDTH, Window.HEIGHT - Window.HEIGHT / 20, 44, Color.red);
        menuButton = new Button("MENU (ESC)", Window.HALF_WIDTH, Window.HEIGHT - Window.HEIGHT / 20, 44, Color.red);
        resumeButton = new Button("RESUME (R)", Window.HALF_WIDTH, Window.HALF_HEIGHT, 64, GameFont.optionBlue);
        retryButton = new Button("RETRY (R)", Window.HALF_WIDTH, Window.HALF_HEIGHT, 64, GameFont.optionBlue);
        serverButton = new Button("SERVER", Window.HALF_WIDTH, Window.HALF_HEIGHT + 40, 32, Color.red);
        showMultiplayerScoresButton = new Button("SHOW MULTIPLAYER HIGHSCORES", Window.HALF_WIDTH, quitButton.getY() - Window.HEIGHT / 16, 24, Color.white);
        player = new Player();
        opponent = new Opponent();
        for (int i = 0; i < 10; ++i) {
            Game.ammos[i] = new Ammo();
        }
        try {
            titleImage = new Image("Images/Title.png");
            youLostImage = new Image("Images/GameLost.png");
            Game.ghosts[0] = new Image("Images/Clyde.png");
            Game.ghosts[1] = new Image("Images/Blinky.png");
            Game.ghosts[2] = new Image("Images/Inky.png");
            Game.ghosts[3] = new Image("Images/Pinky.png");
            openingSound = new Sound("Sounds/openingSound.wav");
            shootSound = new Sound("Sounds/pacmanShoot.wav");
            ObjectInputStream inStream = new ObjectInputStream(new FileInputStream("pmmtds.scores"));
            Score = (ScoreManager)inStream.readObject();
        }
        catch (IOException e) {
            System.out.println("SCORES FILE NOT FOUND");
        }
        catch (ClassNotFoundException e) {
            System.out.println("SCORES FILE EMPTY");
        }
        catch (SlickException e) {
            System.out.println("ONE OR MORE IMAGES NOT FOUND");
        }
        Score.resetScore();
    }

    public void update(GameContainer gc, int delta) throws SlickException {
        input = gc.getInput();
        mouseX = input.getMouseX();
        mouseY = input.getMouseY();
        switch (state) {
            case 0: {
                MenuState.update(gc, input, delta, mouseX, mouseY);
                break;
            }
            case 1: {
                GamePlayState.update(gc, input, delta, mouseX, mouseY);
                break;
            }
            case 2: {
                MultiplayerMenuState.update(gc, input, delta, mouseX, mouseY);
                break;
            }
            case 3: {
                this.createConnection();
                MultiplayerGamePlayState.update(gc, input, delta, mouseX, mouseY);
                break;
            }
            case 4: {
                MultiplayerGameOverState.update(gc, input, delta, mouseX, mouseY);
                break;
            }
            case 5: {
                LeaderBoardState.update(gc, input, delta, mouseX, mouseY);
                break;
            }
            case 6: {
                PauseState.update(gc, input, delta, mouseX, mouseY);
                break;
            }
            case 7: {
                GameOverState.update(gc, input, delta, mouseX, mouseY);
            }
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        switch (state) {
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
            }
        }
    }

    private void createConnection() {
        if (canCreateConnection) {
            address = new InetSocketAddress(IP, DestinationPort);
            try {
                connection = new Connection();
                connection.connect(SourcePort, address);
                connection.start();
            }
            catch (SocketException e) {
                System.out.println("ERROR: IP, SOURCE PORT or DESTINATION PORT NOT VALID " + e);
            }
            int serverPort = 7777;
            if (isServer) {
                server = new UDPServerThread(serverPort, 2);
                server.isRunning(true);
                server.start();
            }
            try {
                InetAddress serverAddress = isServer ? InetAddress.getByName("localhost") : InetAddress.getByName(IP);
                sender = new UDPSenderThread(serverAddress, serverPort);
                sender.start();
                receiver = new UDPReceiverThread(sender.getSocket());
                receiver.start();
            }
            catch (SocketException e) {
                System.out.println("Socket Exception");
            }
            catch (UnknownHostException e) {
                System.out.println("Unknown Host");
            }
            canCreateConnection = false;
        }
    }

    static {
        state = 0;
        canCreateConnection = true;
        paused = false;
        ghosts = new Image[4];
        bulletList = new LinkedList();
        enemyList = new ArrayList();
        ammos = new Ammo[10];
        bulletCount = 0;
        enemyCount = 0;
        ammosCount = 0;
        ENEMY_DELAY = 500;
        startDelay = 500;
        canSpawnEnemy = ENEMY_DELAY;
        canSpawnAmmo = 10000;
        increaseDifficulty = 15000;
        canFire = 0;
        enemyPositionX = new Random();
        enemyPositionY = new Random();
        opponentFired = false;
        opponentBulletList = new ArrayList();
        oppBulletCount = 0;
        opponentCoordinates = new Point(Window.HALF_WIDTH, Window.HALF_HEIGHT);
        opponentMouseCoordinates = new Point();
        opponentScreenHeight = 0;
        opponentScreenWidth = 0;
        infoString = "";
        winnerString = "";
        players = 0;
        multiplayerGameID = 0;
        isServer = false;
        localIP = null;
    }
}

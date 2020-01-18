// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.players;

import pl.art.lach.mateusz.javaopenchess.core.players.implementation.ComputerPlayer;
import pl.art.lach.mateusz.javaopenchess.core.players.implementation.NetworkPlayer;
import pl.art.lach.mateusz.javaopenchess.core.players.implementation.HumanPlayer;
import pl.art.lach.mateusz.javaopenchess.core.Colors;

public class PlayerFactory
{
    public static Player getInstance(final String name, final Colors color, final PlayerType playerType) {
        Player player = null;
        switch (playerType) {
            case LOCAL_USER: {
                player = new HumanPlayer(name, color);
                break;
            }
            case NETWORK_USER: {
                player = new NetworkPlayer(name, color);
                break;
            }
            case COMPUTER: {
                player = new ComputerPlayer(name, color);
                player.setName("CPU");
                break;
            }
            default: {
                player = new HumanPlayer(name, color);
                break;
            }
        }
        return player;
    }
    
    public static Player getInstance(final String name, final String color, final PlayerType playerType) {
        return getInstance(name, Colors.valueOf(color.toUpperCase()), playerType);
    }
}

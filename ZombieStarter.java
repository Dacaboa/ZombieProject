/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiestarter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ZombieServer;
import world.WRoom;
import world.WorldLoader;

/**
 *
 * @author your details here
 */
public class ZombieStarter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // use a try/catch block to handle the case when opening
        // a socket fails...
        try {
            // TODO you need to define classes to represent the world
            // then here you should use WorldLoader to load the world and 
            // convert it to your representation of the world to play 
            // the game with your version of ZombieBot.
            WorldLoader worldLoader = new WorldLoader();

            World world = new World(worldLoader);

            //creates a list of items in the game
            worldLoader.getItems().stream().forEach((item) -> {
                world.addItem(item.getName(), item.getHtml());
            });
            
            //create inventory
            world.addInventory(worldLoader.getInventoryHtml());

            //adds rooms to the world
            for (WRoom room : worldLoader) {
                world.addRoom(room.getName(), room.getDescription(), room.getItems(), room.getZombieCount());
            }

            //adds entrances to rooms
            for (WRoom room : worldLoader) {
                world.addEntrances(room.getName(), room.getEntrances());
            }

            //Set currentRoom to start
            world.setStart(worldLoader.getStart());

            //Set endRoom to end
            world.setEnd(worldLoader.getEnd());

            // create an instane of our server to commnicate with the
            // web frontend.
            InetAddress ip = InetAddress.getLocalHost();
            // now connect to the server
            ZombieServer zs = new ZombieServer(
                    // get host address, rather than using 127.0.0.1, as this
                    // will then be displayed when server waits for connection
                    // which allows the address to then be typed into client.
                    ip.getHostAddress(),
                    8085,
                    new ZombieBot(world));
        } catch (UnknownHostException ex) {
            Logger.getLogger(
                    ZombieStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

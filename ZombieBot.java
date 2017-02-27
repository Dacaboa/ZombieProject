/*
 * Author: Add group details here
 * Desc: This file contains the heart of dynamic game play for Zombies. 
 *       you need to implement each of the methods, as per the game play,
 *       adding support for processing commands comming from the client.
 */
package zombiestarter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author your details
 */
/**
 * class that implements the ZombieBot interface and plays the game
 *
 * @author br-gaster
 */
public class ZombieBot implements world.ZombieBot {
//    

    private final World world;

    ZombieBot(World world) {
        this.world = world;
    }
    
    //score value
    private int score = 0;
    
    //should quit valu
    private boolean quit = false;

    //should enable timer? value
    private boolean enableTimer = false;

    //should enable timer? value
    private boolean disableTimer = false;

    /**
     * should game quit
     *
     * @return return true if exit program, otherwise false
     */
    @Override
    public boolean shouldQuit() {
        return quit;
    }

    /**
     * prompt to be displayed to user
     *
     * @return
     */
    @Override
    public String begin() {
        return world.getStartHtml();
    }

    /**
     * compute current score
     *
     * @return current score
     */
    @Override
    public int currentScore() {
        return score;
    }

    /**
     * should timer be enabled? if should be enabled, then method returns true,
     * and goes back into state of not enable.
     *
     * @return true if enable timer, otherwise false
     */
    @Override
    public boolean enableTimer() {
        return enableTimer;
    }

    /**
     * should timer be disabled? if should be disabled, then method returns
     * true, and goes back into state of don't disable.
     *
     * @return
     */
    public boolean disableTimer() {
        return disableTimer;
    }

    /**
     * process player commands
     *
     * @param cmd to be processed
     * @return output to be displayed
     */
    @Override
    public List<String> processCmd(String cmd) {
        ArrayList<String> result = new ArrayList<>();

        String[] cmds = cmd.split(" "); // split cmd by space

        switch (cmds[0]) {
            case "info":
                result.add(world.getInfo());
                break;
            case "look":
                //world.look();   what may have to use
                //world knows what current romm i
                //and from that should get details of that room
                //current room is critical
                result.add(world.look());
                break;
            case "move":
                //move requirtes logic about the direction and the direction of entrance and set surrent room as the new room
                //if room has door in direction requested? cmds[1] to equal currentroom.
                if (world.checkForExit(cmds[1])) {
                    //if said door is not locked
                    if (!world.attemptToOpenDoor(cmds[1])) {
                        //set currnetRoom to roomdest of entrance
                        world.setCurrentRoom(cmds[1]); 
                        //if zombieCount > 0, enable timer 
                        if (world.getZombieCount() > 0) {
                            enableTimer = true;
                        }   
                    } else {
                        //else locked say no, you need key
                        result.add("door is locked<br>get a key");
                    }
                } else {
                    //else say no door in that direction, and feedback which direction they inputed
                    result.add("no exit in the direction " + cmds[1]);
                }
                break;
            case "pickup":
                if(world.checkForItemRoom(cmds[1])){
                    //if item is in the room 
                    world.pickupItem(cmds[1]);
                    score++;
                //remove from room, put in inventory
                //increment score
                } else {
                result.add("you can see any " + cmds[1]);
                }
                break;
            case "kill":
                //if room contains zomnbie and chainsaw or daisy in inventory
                //zombie count decrement 
                //remove item used to kill zombie
                //if count 0
                //disableTimer = true;
                //else nothing
                //else
                result.add("handle kill command");
                break;
            case "drop":
                //switch statement for each type of item
                //if item is in the inventory 
                //remove from inventory, put in room
                //else say dont have item
                result.add("handle drop command");
                break;
            case "timerexpired":
                //set quit to true and say you died
                result.add("<h1>you died</h1>");
                quit = true;
                break;
            case "quit":
                quit = true;
                break;
            case "inventory":
                result.add(world.checkInventory());
                break;
            case "blank":
                result.add("I beg your pardon?");
                break;
            case "":
                break;
            default:
                result.add("<b>That's not a verb I recognise.</b>");
        }

        return result;
    }
}

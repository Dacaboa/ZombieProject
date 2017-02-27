package zombiestarter;

import java.util.ArrayList;
import java.util.List;
import world.WEntrance;
import world.WorldLoader;

public class World {

    //Get Info
    private final String info;
    //get start HTML
    private final String startHtml;
    //itemList htmls
    public ArrayList<Item> possibleItems = new ArrayList<>();
    //arraylist for all rooms in jason file
    private ArrayList<Room> rooms = new ArrayList();
    //the room currently in
    private Room currentRoom;
    //End Room
    private Room endRoom;
    //Inventory
    private Inventory inventory;

    //world contructor
    World(WorldLoader worldLoader) {
        this.info = worldLoader.getInfo();
        this.startHtml = worldLoader.getStartHtml();
        possibleItems = new ArrayList<>();
        rooms = new ArrayList<>();
    }

    public void addInventory(String inventoryHtml) {
        this.inventory = new Inventory(inventoryHtml);
    }

    //get info string
    public String getInfo() {
        return info;
    }

    //get start HTML string
    public String getStartHtml() {
        return startHtml;
    }

    //add room method
    public void addRoom(String name, String description, List<String> itemNames, int zombieCount) {
        ArrayList<Item> roomItems = new ArrayList<>();
        for (String itemName : itemNames) {
            for (Item item : possibleItems) {
                if (item.getName().equals(itemName)) {
                    roomItems.add(item);
                }
            }
        }
        rooms.add(new Room(name, description, roomItems, zombieCount));
    }

    public void addEntrances(String name, List<WEntrance> entrances) {
        ArrayList<Entrance> roomEntrances = new ArrayList<>();
        for (WEntrance entrance : entrances) {
            for (Room room : rooms) {
                if (room.getName().equals(entrance.getTo())) {
                    roomEntrances.add(new Entrance(entrance.getDirection(), room, entrance.isLocked()));
                }
            }
        }
        // write loop to find room from naem
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                room.addEntrances(roomEntrances);
            }
        }

    }

    //add item to possible items list method
    void addItem(String name, String html) {
        possibleItems.add(new Item(name, html));
    }

    //set start
    public void setStart(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                currentRoom = room;
                break;
            }
        }
    }

    //set End Room
    public void setEnd(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                endRoom = room;
                break;
            }
        }
    }

    //look method
    public String look() {
        return currentRoom.look();
    }

    //check inventory method
    public String checkInventory() {
        String backpack = inventory.getInventoryHtml();
        String itemList = inventory.checkContents();
        return backpack + itemList;
    }

    void setCurrentRoom(String cmd) {
        for (Entrance entrance : currentRoom.entrances) {
            if (entrance.getDirection().equalsIgnoreCase(cmd)) {
                currentRoom = entrance.getRoomDest();
            }
        }
    }

    boolean checkForItemRoom(String cmd) {
        for (Item entrance : currentRoom.items) {
            if (entrance.getName().equalsIgnoreCase(cmd)) {
                return true;
            }
        }
        return false;
    }

    boolean checkForExit(String cmd) {
        for (Entrance entrance : currentRoom.entrances) {
            if (entrance.getDirection().equalsIgnoreCase(cmd)) {
                return true;
            }
        }
        return false;
    }

    boolean attemptToOpenDoor(String cmd) {
        if (currentRoom.checkLocked(cmd)) {
            for (Item item : inventory.inventory) {
                if(item.getName().equalsIgnoreCase("key")){
                    return false;
                }
            }  
        } else {
            return false;
        }
        return true;
    }

    public int getZombieCount() {
        return currentRoom.getZombieCount();
    }

    public void pickupItem(String cmd) {
        //add item to inventory 
        for (Item item : possibleItems){
            if(item.getName().equalsIgnoreCase(cmd)){
                inventory.addItem(new Item(item.getName(),item.getHtml()));
            }
        }
        //remove item from room 
        currentRoom.removeItem(cmd);
    }

}

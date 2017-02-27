
package zombiestarter;

import java.util.List;

public class Room {

    //attribute description
    private final String description;
    //attribute name
    private final String name;
    //list of entrances
    public List<Entrance> entrances;
    //list of items
    public final List<Item> items;
    //number of zombies
    private int zombieCount;

    //constructor    take a description take a name
    Room(String name, String description, List<Item> items, int zombieCount) {
        this.description = description;
        this.name = name;
        this.items = items;
        this.zombieCount = zombieCount;

    }
    
    //add entrances method
    public void addEntrances(List<Entrance> entrances) {
        this.entrances = entrances;
    }
    
    //get entrance list
    public List<Entrance> getEntrances(){
        return entrances;
    }

    //get room name
    public String getName() {
        return name;
    }

    //get room description
    public String getDescription() {
        return description;
    }   
    
    //get zombie count
    public int getZombieCount(){
        return zombieCount;
    }
    
    //remove items from room
    public void removeItem(String cmd){
        for (Item item : items){
            if(item.getName().equalsIgnoreCase(cmd)){
                //this doesnt work
                items.remove(cmd);
            }
        }
    }
    
    //look method
    String look() {
        String directionList = "";
        for (Entrance entrance : entrances) {
            directionList = directionList + entrance.getDirection() + ", ";
        }

        String itemList = "";
        for (Item item : items) {
            itemList = itemList + " " + item.getHtml();
        }
        return "<h4>you are in the " + name + "</h4><br>" + description + "<br>there are entrances to the : " + directionList + "<br>" + itemList;
    }

//    public String getRoomDest(String cmd) {
//        return entrances.get(entrances.indexOf(cmd)).getRoomDest();
//    }
    boolean checkLocked(String cmd) {
        for (Entrance entrance : entrances){
            if(entrance.getDirection().equalsIgnoreCase(cmd)){
                    return entrance.checkLocked();
            }
        }
        return false;            
    }

}

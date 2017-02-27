
package zombiestarter;

import java.util.ArrayList;

/**
 *
 * @author Turne
 */
public class Inventory {
    
    //list of items
    public ArrayList<Item> inventory;
    
    //get inventory HTML
    private final String inventoryHtml;
    
    //constructor
    Inventory(String inventoryHtml){
        inventory = new ArrayList<>();
        this.inventoryHtml = inventoryHtml;
    }

   
    //    //have array list that is inventory
//    public ArrayList<Items> inventory = new ArrayList<>();
    //pickup and or drop
    

    public String getInventoryHtml() {
        return inventoryHtml;
    }

    public String checkContents() {
        String contentsList = "";
        for (Item item : inventory){
            contentsList = contentsList + item.getHtml();
        }
        return contentsList;
    }
    
    public void addItem(Item pickedUp){
        inventory.add(pickedUp);
    }
}

//pickup method like look
//public void pickupItem (){
//add item inventory remove form room
//}
//drop method


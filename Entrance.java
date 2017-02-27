
package zombiestarter;

class Entrance {

    private final String direction;

    private final Room roomDest;

    private final boolean locked;

    Entrance(String direction, Room roomDest, boolean locked) {
        this.direction = direction;
        this.roomDest = roomDest;
        this.locked = locked;
    }

    //get entrance direction
    public String getDirection() {
        return direction;
    }

    //get room destination
    public Room getRoomDest() {
        return roomDest;
    }

    //check door locked
    public boolean checkLocked() {
        return locked;
    }

}

import java.util.*;

public class Location {
    String name;
    String description;
    String inspection;
    Inventory inventory;
    Map<Direction, Location> directions = new HashMap<Direction, Location>();

    ArrayList<Item> items = new ArrayList<Item>();
    public void add(Item name) {
        items.add(name);
    }
    public void remove(Item name) {
        items.remove(name);
    }

    public String inventoryGetList() {
        try {
            String inventoryGetList = "[";
            for (Item o : items) {
                inventoryGetList += o.name + ", ";
            }
            System.out.println(inventoryGetList.substring(0, inventoryGetList.length() - 2) + "]");
        }

        catch (StringIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("[Предметов нет]");
            String nullInv = "[Предметов нет]";
            return nullInv; }
        String nullInv = "Good";
        return nullInv;
    }

    Location(String n)
    {
        name = n;
    }

    public void findNext() {
        Set<Direction> keys = directions.keySet();
        System.out.println("Доступные направления:" + keys);
    }
}

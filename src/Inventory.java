import java.util.ArrayList;


public class Inventory {



    ArrayList<Item> items = new ArrayList<Item>();
    public void add(Item name) {
        items.add(name);
    }
    public void remove(Item name) {
        items.remove(name);
    }


    public void inventoryGetList() {
        try {
            String inventoryGetList = "[";
            for (Item o : items) {
                inventoryGetList += o.name + ", ";
            }
            System.out.println(inventoryGetList.substring(0, inventoryGetList.length() - 2) + "]");
        }
        catch (StringIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("[Предметов нет]"); }
    }


}


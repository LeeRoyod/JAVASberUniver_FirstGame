import java.util.Scanner;



public class Player {
    Location location;
    Inventory inventory;

    public String go() {
        Scanner in = new Scanner(System.in);
        String n = in.next();
        boolean testEnum = contains(n);
        if (!testEnum) {
            System.out.println("!!Введите команду верно!!");
        }
        return n;
    }



    public static boolean contains(String check) {

        for (Direction c : Direction.values()) {
            if (c.name().equals(check)) {
                return true;
            }
        }

        return false;
    }
}


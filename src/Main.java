
import java.util.*;



public class Main {

    public static String action() {
        System.out.println("Вы можете: осмотреться - [AROUND]; идти - [GO]; инвентарь - [INVENT]");
        Scanner in = new Scanner(System.in);
        return in.next();
    }
    public static void craftBook() {
        System.out.println("Книга рецептов (Recipe Book):");
        System.out.println("Железный слиток + Палка = Железный меч");
    }



    public static void main(String[] args)  {
//Инициализация
        Location start = new Location("Cave entrance");
        Location river = new Location("Cave river");
        Location hall = new Location("Dragon hall workshop");
        Location workshop = new Location("Hall of the ice dragon");
        Location battle = new Location("Goblins fight");
        start.description = "Вы находитесь у входа в пещеру Пограничного хребта";
        start.inspection = "Вход в пещеру выглядит как песочные часы";
        start.directions.put(Direction.FORWARD, river);
        river.description = "Вы идете по горному туннелю, слева от вас медленно протекает речка...";
        river.inspection = "Вы замечаете треснувшую лужицу льда. Здесь без сомнений кто-то проходил";
        river.directions.put(Direction.FORWARD, hall);
        river.directions.put(Direction.BACK, start);
        hall.description = "Пробежав небольшое расстояние вы попадаете в огромный ледяной зал";
        hall.inspection = "В центре лежит скелет древнего дракона, а недалеко от него гоблины разжигают костры " + System.lineSeparator()
                + "Рядом с гоблинами вы замечаете деревянную телегу, а на ней лежит связанная Селка...";
        hall.directions.put(Direction.LEFT, workshop);
        hall.directions.put(Direction.FORWARD, battle);
        workshop.description = "Нужно срочно что-нибудь придумать что бы спасти Селку. Вы забегаете за угол и попадаете в древнюю мастерскую";
        workshop.inspection = "Неподалеку находится верстак и кузнечный инвентарий, опыт 2000 часов в майнкрафт дают о себе знать и у вас закрадываются идеи...";
        workshop.directions.put(Direction.BACK, hall);
        battle.description = "Вы нападаете на гоблинов и раскрамсываете их своим железным мечем";
        battle.inspection = "Гоблины побеждены и Селке более ничего не угрожает";
        battle.directions.put(Direction.BACK, hall);
        //
        Player player = new Player();
        player.location = start;
        //
        Inventory playerInventory = new Inventory();
        Item grass = new Item("GRASS");
        grass.description = "Священная травинка излучающая свет";
        grass.moveable = Moveable.MOBILE;
        Item iron = new Item("IRON");
        iron.description = "Железный слиток — материал, получаемый обжигом железной руды";
        iron.moveable = Moveable.MOBILE;
        Item stick = new Item("STICK");
        stick.description = "Палка — материал, получаемый крафтом из досок и используемый во многих прочих рецептах";
        stick.moveable = Moveable.MOBILE;
        Item sword = new Item("SWORD");
        sword.description = "Меч (англ. Sword) — оружие ближнего боя, используемое в основном для убийства мобов";
        sword.moveable = Moveable.MOBILE;
        Item bread = new Item("BREAD");
        bread.description = "Хлеба черствый кусок";
        bread.moveable = Moveable.MOBILE;
        start.items.add(grass);
        workshop.items.add(iron);
        workshop.items.add(stick);

        playerInventory.add(bread);
        player.inventory = playerInventory;


// Обучение
        System.out.println("Правила игры:");
        System.out.println("Для выбора действия необходимо ввести команду из скобок");
        System.out.println("Пример: [GO] - необходимо ввести GO и нажать Enter");
        boolean success = false;
        while (!success) {
            System.out.println("Попробуем? Всё ли понятно? [YES], [NO]");
            Scanner in = new Scanner(System.in);
            String n = in.next();
            if (n.equals("YES") || n.equals("NO")) {
                System.out.println("Молодец!");
                System.out.println("---------------------");
                success = true;
            } else {
                System.out.println("Что-то не так, давай ещё раз");
            }
        }

// Вступление
        System.out.println("Девочка по имени Селка потерялась, Сестра Азария не видела её все утро");
        System.out.println("По одной из версий она пошла к Пограничному хребту. Это очень плохо и вам срочно нужно её вернуть");
        System.out.println("");
        System.out.println(player.location.description);

// Ход игры
        boolean winGame = false;
        while (!winGame) {
            if (player.location.equals(battle)) {
                System.out.print(battle.inspection);
                winGame = true;
                break;
            }
            String make = action();
            switch (make) {
                case ("AROUND"):
                    System.out.println(player.location.inspection);
                    System.out.print("Предметы на локации: ");
                    try {
                        String TestInvLoc = player.location.inventoryGetList();
                        if (TestInvLoc.equals("[Предметов нет]")) {
                            break;
                        }
                    } catch (StringIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("[Предметов нет]");
                        break;
                    }
                    System.out.println("Взаимодействовать с предметами? [YES, NO]");
                    Scanner inThis = new Scanner(System.in);
                    String makeThisOne = inThis.next();
                    if (makeThisOne.equals("YES")) {
                        System.out.println("Вы можете: взять предмет - [TAKE], осмотреть - [INSPECT]");
                        Scanner inLocItem = new Scanner(System.in);
                        String LocItem = inLocItem.next();
                        switch (LocItem) {
                            case ("TAKE"):
                                System.out.println("Введите имя предмета которого хотите взять");
                                player.location.inventoryGetList();
                                Scanner in3 = new Scanner(System.in);
                                String makeInventoryAbort = in3.next();
                                if (makeInventoryAbort.equals("BREAD") && player.location.items.contains(bread)) {
                                    playerInventory.add(bread);
                                    player.location.remove(bread);
                                    System.out.print("Ваш инвентарь: ");
                                    player.inventory.inventoryGetList();
                                }
                                if (makeInventoryAbort.equals("IRON") && player.location.items.contains(iron)) {
                                    playerInventory.add(iron);
                                    player.location.remove(iron);
                                    System.out.print("Ваш инвентарь: ");
                                    player.inventory.inventoryGetList();
                                }
                                if (makeInventoryAbort.equals("STICK") && player.location.items.contains(stick)) {
                                    playerInventory.add(stick);
                                    player.location.remove(stick);
                                    System.out.print("Ваш инвентарь: ");
                                    player.inventory.inventoryGetList();
                                }
                                if (makeInventoryAbort.equals("GRASS") && player.location.items.contains(grass)) {
                                    playerInventory.add(grass);
                                    player.location.remove(grass);
                                    System.out.print("Ваш инвентарь: ");
                                    player.inventory.inventoryGetList();
                                }
                                if (makeInventoryAbort.equals("SWORD") && player.location.items.contains(sword)) {
                                    playerInventory.add(sword);
                                    player.location.remove(sword);
                                    System.out.print("Ваш инвентарь: ");
                                    player.inventory.inventoryGetList();
                                }
                                break;
                            case ("INSPECT"):
                                System.out.println("Введите имя предмета которого хотите осмотреть");
                                player.location.inventoryGetList();
                                Scanner in4 = new Scanner(System.in);
                                String makeInventoryAbort2 = in4.next();
                                if (makeInventoryAbort2.equals("BREAD") && player.location.items.contains(bread)) {
                                    System.out.println(bread.description);
                                }
                                if (makeInventoryAbort2.equals("IRON") && player.location.items.contains(iron)) {
                                    System.out.println(iron.description);
                                }
                                if (makeInventoryAbort2.equals("STICK") && player.location.items.contains(stick)) {
                                    System.out.println(stick.description);
                                }
                                if (makeInventoryAbort2.equals("GRASS") && player.location.items.contains(grass)) {
                                    System.out.println(grass.description);
                                }
                                if (makeInventoryAbort2.equals("SWORD") && player.location.items.contains(sword)) {
                                    System.out.println(sword.description);
                                }
                                break;
                        }
                    }
                    break;
                case ("GO"):
                    player.location.findNext();
                    Location saveLocation = player.location;
                    String locationTransition = player.go();
                    if (locationTransition.equals("FORWARD") && player.location.equals(hall) && !(player.inventory.items.contains(sword))) {
                        System.out.println("Сперва стоит добыть оружие");
                        break;
                    }
                    if (locationTransition.equals("FORWARD")) {
                        player.location = player.location.directions.get(Direction.FORWARD);
                    }
                    if (locationTransition.equals("BACK")) {
                        player.location = player.location.directions.get(Direction.BACK);
                    }
                    if (locationTransition.equals("LEFT")) {
                        player.location = player.location.directions.get(Direction.LEFT);
                    }
                    if (player.location == null) {
                        System.out.println("!!Введите команду для перехода верно!!");
                        player.location = saveLocation;
                    }
                    System.out.println(player.location.description);
                    break;
                case ("INVENT"):
                    System.out.print("У вас в инвентаре: ");
                    try {
                        String TestInv = player.inventory.inventoryGetList();
                        if (TestInv.equals("[Предметов нет]")) {
                            break;
                        }
                    } catch (StringIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("[Предметов нет]");
                        break;
                    }
                    System.out.println("Продолжить взаимодействие с инвентарем? [YES, NO]");
                    Scanner in = new Scanner(System.in);
                    String makeThis = in.next();
                    if (makeThis.equals("YES")) {
                        System.out.println("Вы можете: выкинуть предмет - [ABORT], осмотреть - [INSPECT], крафтить - [CRAFT]");
                        Scanner in2 = new Scanner(System.in);
                        String makeInventory = in2.next();
                        switch (makeInventory) {
                            case ("ABORT"):
                                System.out.println("Введите имя предмета которого хотите выкинуть");
                                player.inventory.inventoryGetList();
                                Scanner in3 = new Scanner(System.in);
                                String makeInventoryAbort = in3.next();
                                if (makeInventoryAbort.equals("BREAD") && playerInventory.items.contains(bread)) {
                                    playerInventory.remove(bread);
                                    player.location.add(bread);
                                    System.out.print("Ваш инвентарь: ");
                                    player.inventory.inventoryGetList();
                                }
                                if (makeInventoryAbort.equals("IRON") && playerInventory.items.contains(iron)) {
                                    playerInventory.remove(iron);
                                    player.location.add(iron);
                                    System.out.print("Ваш инвентарь: ");
                                    player.inventory.inventoryGetList();
                                }
                                if (makeInventoryAbort.equals("STICK") && playerInventory.items.contains(stick)) {
                                    playerInventory.remove(stick);
                                    player.location.add(stick);
                                    System.out.print("Ваш инвентарь: ");
                                    player.inventory.inventoryGetList();
                                }
                                if (makeInventoryAbort.equals("GRASS") && playerInventory.items.contains(grass)) {
                                    playerInventory.remove(grass);
                                    player.location.add(grass);
                                    System.out.print("Ваш инвентарь: ");
                                    player.inventory.inventoryGetList();
                                }
                                if (makeInventoryAbort.equals("SWORD") && playerInventory.items.contains(sword)) {
                                    playerInventory.remove(sword);
                                    player.location.add(sword);
                                    System.out.print("Ваш инвентарь: ");
                                    player.inventory.inventoryGetList();
                                }
                                break;
                            case ("INSPECT"):
                                System.out.println("Введите имя предмета которого хотите осмотреть");
                                player.inventory.inventoryGetList();
                                Scanner in4 = new Scanner(System.in);
                                String makeInventoryAbort2 = in4.next();
                                if (makeInventoryAbort2.equals("BREAD") && playerInventory.items.contains(bread)) {
                                    System.out.println(bread.description);
                                }
                                if (makeInventoryAbort2.equals("IRON") && playerInventory.items.contains(iron)) {
                                    System.out.println(iron.description);
                                }
                                if (makeInventoryAbort2.equals("STICK") && playerInventory.items.contains(stick)) {
                                    System.out.println(stick.description);
                                }
                                if (makeInventoryAbort2.equals("GRASS") && playerInventory.items.contains(grass)) {
                                    System.out.println(grass.description);
                                }
                                if (makeInventoryAbort2.equals("SWORD") && playerInventory.items.contains(sword)) {
                                    System.out.println(sword.description);
                                }
                                break;
                            case ("CRAFT"):
                                craftBook();
                                System.out.print("Ваш инвентарь: ");
                                player.inventory.inventoryGetList();
                                if (player.inventory.items.contains(iron) && player.inventory.items.contains(stick)) {
                                    System.out.println("Введите имя первого предмета для крафта");
                                    Scanner craftOne1 = new Scanner(System.in);
                                    String craftOne = craftOne1.next();
                                    System.out.println("Введите имя второго предмета для крафта");
                                    Scanner craftTwo2 = new Scanner(System.in);
                                    String craftTwo = craftTwo2.next();
                                    if ((craftOne.equals("IRON") && craftTwo.equals("STICK")) ||
                                            (craftOne.equals("STICK") && craftTwo.equals("IRON"))) {
                                        playerInventory.remove(stick);
                                        playerInventory.remove(iron);
                                        playerInventory.add(sword);
                                        System.out.println("Успешный крафт, в инвентарь добавлен железный меч");
                                        System.out.print("Ваш инвентарь: ");
                                        player.inventory.inventoryGetList();
                                        break;
                                    } else {
                                        System.out.println("Ошибка в крафте, убедитесь в отсутствии ошибок в написании");
                                    }
                                    break;
                                }
                                System.out.println("Нет доступных предметов для крафта");
                                break;
                        }
                    }
                    if (makeThis.equals("NO")) {
                        break;
                    }
                    break;
            }
        }

    }

}





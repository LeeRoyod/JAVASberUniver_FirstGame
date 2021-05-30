import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryTest {
    Inventory newInventoryTest = new Inventory();
    Item newItemTestInventory = new Item("newItemTestInventory");

    @Test
    public void add() {
        newInventoryTest.add(newItemTestInventory);
        boolean result = newInventoryTest.items.contains(newItemTestInventory);
        assertTrue(result);
    }

    @Test
    public void remove() {
        newInventoryTest.add(newItemTestInventory);
        boolean result = newInventoryTest.items.contains(newItemTestInventory);
        if (!result) {
            assertTrue(result);
        }
        newInventoryTest.remove(newItemTestInventory);
        boolean resultRemove = newInventoryTest.items.contains(newItemTestInventory);
        assertFalse(resultRemove);
    }

    @Test
    public void inventoryGetListNull() {
        String actual = newInventoryTest.inventoryGetList();
        String expected = "[Предметов нет]";
        assertEquals(actual, expected);
    }
}
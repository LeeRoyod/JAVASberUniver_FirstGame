import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    private org.junit.Assert Assert;
    Location newLocationTest = new Location("LocationTest");
    Item newItemTest = new Item("newItemTest");


    @Test
    public void inventoryGetListNull() {
        String actual = newLocationTest.inventoryGetList();
        String expected = "[Предметов нет]";
        assertEquals(actual, expected);
    }

    @Test
    public void findNextNull() {
        newLocationTest.findNext();
    }

    @Test
    public void findNextOne() {
        newLocationTest.directions.put(Direction.FORWARD, newLocationTest);
        newLocationTest.findNext();
    }

    @Test
    public void findNextMore() {
        newLocationTest.directions.put(Direction.FORWARD, newLocationTest);
        newLocationTest.directions.put(Direction.BACK, newLocationTest);
        newLocationTest.findNext();
    }

    @Test
    public void add() {
        newLocationTest.add(newItemTest);
        boolean result = newLocationTest.items.contains(newItemTest);
        assertTrue(result);
    }

    @Test
    public void remove() {
        newLocationTest.add(newItemTest);
        boolean result = newLocationTest.items.contains(newItemTest);
        if (!result) {
            assertTrue(result);
        }
        newLocationTest.remove(newItemTest);
        boolean resultRemove = newLocationTest.items.contains(newItemTest);
        assertFalse(resultRemove);
    }
}
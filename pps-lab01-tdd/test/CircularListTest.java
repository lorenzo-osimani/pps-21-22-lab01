import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;
import lab01.tdd.SelectStrategyFactory;
import lab01.tdd.SelectStrategyFactoryImpl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularList list;
    private final SelectStrategyFactory factory = new SelectStrategyFactoryImpl();
    private final static int NUMBER_OF_ELEMENTS_ADDED = 5;

    @BeforeEach
    void beforeEach() {
        list = new CircularListImpl();
    }

    private void addMultipleElements() {
        IntStream.range(0, NUMBER_OF_ELEMENTS_ADDED).forEach(index -> list.add(index));
    }

    private void doMultipleNext(final int numberOfNext) {
        IntStream.range(0, numberOfNext).forEachOrdered(n -> list.next());
    }

    private void doMultiplePrevious(final int numberOfPrevious) {
        IntStream.range(0, numberOfPrevious).forEachOrdered(n -> list.previous());
    }

    @Test
    public void testInitialSize() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testNextOnEmptyList() {
        assertEquals(Optional.empty(), list.next());
    }

    @Test
    public void testPreviousOnEmptyList() {
        assertEquals(Optional.empty(), list.previous());
    }

    @Test
    public void testAddingElement() {
        list.add(0);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    public void testAddingMultipleElements() {
        addMultipleElements();
        assertEquals(NUMBER_OF_ELEMENTS_ADDED, list.size());
    }

    @Test
    public void testNext() {
        list.add(0);
        assertEquals(Optional.of(0), list.next());
    }

    @Test
    public void testMultipleNext() {
        addMultipleElements();
        int numberOfNext = 2;
        doMultipleNext(numberOfNext);
        assertEquals(Optional.of(numberOfNext), list.next());
    }

    @Test
    public void testCircularNext() {
        addMultipleElements();
        doMultipleNext(NUMBER_OF_ELEMENTS_ADDED);
        assertEquals(Optional.of(0), list.next());
    }

    @Test
    public void testPrevious() {
        list.add(0);
        assertEquals(Optional.of(0), list.previous());
    }

    @Test
    public void testCircularPrevious() {
        addMultipleElements();
        assertEquals(Optional.of(4), list.previous());
    }

    @Test
    public void testMultiplePrevious() {
        addMultipleElements();
        doMultiplePrevious(3);
        assertEquals(Optional.of(1), list.previous());
    }

    @Test
    public void testReset() {
        addMultipleElements();
        doMultipleNext(3);
        list.reset();
        assertEquals(Optional.of(0), list.next());
    }

    @Test
    public void testSelectStrategyOnEmptyList() {
        assertEquals(Optional.empty(), list.next(factory.createEvenStrategy()));
    }


    @Test
    public void testEvenStrategy() {
        addMultipleElements();
        assertEquals(Optional.of(1), list.next(factory.createEvenStrategy()));
    }

    @Test
    public void testMultipleOfStrategy() {
        addMultipleElements();
        this.doMultipleNext(3);
        assertEquals(Optional.of(4), list.next(factory.createMultipleOfStrategy(2)));
    }

    @Test
    public void testEqualsStrategy() {
        addMultipleElements();
        assertEquals(Optional.of(3), list.next(factory.createEqualsStrategy(3)));
    }

    @Test
    public void testFailedSelectStrategy() {
        addMultipleElements();
        assertEquals(Optional.empty(), list.next(factory.createEqualsStrategy(NUMBER_OF_ELEMENTS_ADDED)));
    }
}

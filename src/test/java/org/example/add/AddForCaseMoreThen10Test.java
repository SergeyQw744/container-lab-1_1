package org.example.add;

import org.example.Container;
import org.example.IncorrectIndexException;
import org.example.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AddForCaseMoreThen10Test {
    @InjectMocks
    Container<User> actualContainer;
    @BeforeEach
    public void fillTestContainer(){
        actualContainer.add(new User("one", "1", 1));
        actualContainer.add(new User("two", "2", 2));
        actualContainer.add(new User("three", "3", 3));
        actualContainer.add(new User("four", "4", 4));
        actualContainer.add(new User("five", "5", 5));
        actualContainer.add(new User("six", "6", 6));
        actualContainer.add(new User("seven", "7", 7));
        actualContainer.add(new User("eight", "8", 8));
        actualContainer.add(new User("nine", "9", 9));
        actualContainer.add(new User("ten", "10", 10));
        actualContainer.add(new User("eleven", "11", 11));
        actualContainer.add(new User("twelve", "12", 12));
    }
    @Test
    public void testAdd(){
        actualContainer.add(new User("000", "000", 13));
        Container<User> expectedContainer = new Container<>();
        expectedContainer.add(new User("one", "1", 1));
        expectedContainer.add(new User("two", "2", 2));
        expectedContainer.add(new User("three", "3", 3));
        expectedContainer.add(new User("four", "4", 4));
        expectedContainer.add(new User("five", "5", 5));
        expectedContainer.add(new User("six", "6", 6));
        expectedContainer.add(new User("seven", "7", 7));
        expectedContainer.add(new User("eight", "8", 8));
        expectedContainer.add(new User("nine", "9", 9));
        expectedContainer.add(new User("ten", "10", 10));
        expectedContainer.add(new User("eleven", "11", 11));
        expectedContainer.add(new User("twelve", "12", 12));
        expectedContainer.add(new User("000", "000", 13));

        assertArrayEquals(expectedContainer.getData(), actualContainer.getData());
        assertEquals(expectedContainer.getSize(), actualContainer.getSize());
    }
    @Test
    public void testAddByIndexInMiddle(){
        actualContainer.addByIndex(5, new User("000", "000", 13));
        Container<User> expectedContainer = new Container<>();
        expectedContainer.add(new User("one", "1", 1));
        expectedContainer.add(new User("two", "2", 2));
        expectedContainer.add(new User("three", "3", 3));
        expectedContainer.add(new User("four", "4", 4));
        expectedContainer.add(new User("five", "5", 5));
        expectedContainer.add(new User("000", "000", 13)); //5
        expectedContainer.add(new User("six", "6", 6));
        expectedContainer.add(new User("seven", "7", 7));
        expectedContainer.add(new User("eight", "8", 8));
        expectedContainer.add(new User("nine", "9", 9));
        expectedContainer.add(new User("ten", "10", 10));
        expectedContainer.add(new User("eleven", "11", 11));
        expectedContainer.add(new User("twelve", "12", 12));

        assertArrayEquals(expectedContainer.getData(), actualContainer.getData());
        assertEquals(expectedContainer.getSize(), actualContainer.getSize());
    }
    @Test
    public void testAddByIndexInBegin(){
        actualContainer.addByIndex(0, new User("000", "000", 13));
        Container<User> expectedContainer = new Container<>();
        expectedContainer.add(new User("000", "000", 13));
        expectedContainer.add(new User("one", "1", 1));
        expectedContainer.add(new User("two", "2", 2));
        expectedContainer.add(new User("three", "3", 3));
        expectedContainer.add(new User("four", "4", 4));
        expectedContainer.add(new User("five", "5", 5));
        expectedContainer.add(new User("six", "6", 6));
        expectedContainer.add(new User("seven", "7", 7));
        expectedContainer.add(new User("eight", "8", 8));
        expectedContainer.add(new User("nine", "9", 9));
        expectedContainer.add(new User("ten", "10", 10));
        expectedContainer.add(new User("eleven", "11", 11));
        expectedContainer.add(new User("twelve", "12", 12));

        assertArrayEquals(expectedContainer.getData(), actualContainer.getData());
        assertEquals(expectedContainer.getSize(), actualContainer.getSize());
    }
    @Test
    public void testIncorrectIndexExceptionInAdd(){
        assertThrows(IncorrectIndexException.class, () ->
                actualContainer.addByIndex(500, new User("test-username-3", "test-password-3", 20)));
    }
}


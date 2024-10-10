package org.example.delete;

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
public class DeleteForCaseMoreThen10Test {
    @InjectMocks
    Container<User> actualContainer;
    @BeforeEach
    public void fillTestContainer(){
        actualContainer.add(new User("one", "1", 1));
        actualContainer.add(new User("two", "2", 2));
        actualContainer.add(new User("three", "3", 3));
        actualContainer.add(new User("four", "4", 4));
        actualContainer.add(new User("five", "5", 5)); //4
        actualContainer.add(new User("six", "6", 6));
        actualContainer.add(new User("seven", "7", 7));
        actualContainer.add(new User("eight", "8", 8));
        actualContainer.add(new User("nine", "9", 9));
        actualContainer.add(new User("ten", "10", 10));
        actualContainer.add(new User("eleven", "11", 11));
        actualContainer.add(new User("twelve", "12", 12)); //11
    }
    @Test
    public void testDeleteFromTheMiddle(){
        actualContainer.delete(4);
        Container<User> expectedContainer = new Container<>();
        expectedContainer.add(new User("one", "1", 1));
        expectedContainer.add(new User("two", "2", 2));
        expectedContainer.add(new User("three", "3", 3));
        expectedContainer.add(new User("four", "4", 4));
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
    public void testDeleteFromTheBegin(){
        actualContainer.delete(0);
        Container<User> expectedContainer = new Container<>();
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
    public void testDeleteFromEnd(){
        actualContainer.delete(11);
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

        assertArrayEquals(expectedContainer.getData(), actualContainer.getData());
        assertEquals(expectedContainer.getSize(), actualContainer.getSize());
    }

    @Test
    public void testIncorrectIndexExceptionInDelete(){
        assertThrows(IncorrectIndexException.class, () -> actualContainer.delete(-59));
    }
}


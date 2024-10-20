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
        for (int i = 1; i < 13; i++){
            actualContainer.add(new User("elem-" + i,"password-" + i, i));
        }
    }
    @Test
    public void testAdd(){
        actualContainer.add(new User("000", "000", 13));
        Container<User> expectedContainer = new Container<>();
        for(int i = 1; i < 13; i++){
            expectedContainer.add(new User("elem-" + i,"password-" + i, i));
        }
        expectedContainer.add(new User("000", "000", 13));
        assertArrayEquals(expectedContainer.getData(), actualContainer.getData());
        assertEquals(expectedContainer.getSize(), actualContainer.getSize());
    }
    @Test
    public void testAddByIndexInMiddle(){
        actualContainer.addByIndex(5, new User("000", "000", 13));
        Container<User> expectedContainer = new Container<>();
        for (int i = 1; i < 6; i++){
            expectedContainer.add(new User("elem-" + i,"password-" + i, i));
        }
        expectedContainer.add(new User("000", "000", 13));
        for (int i = 6; i < 13; i++){
            expectedContainer.add(new User("elem-" + i,"password-" + i, i));
        }
        assertArrayEquals(expectedContainer.getData(), actualContainer.getData());
        assertEquals(expectedContainer.getSize(), actualContainer.getSize());
    }

    @Test
    public void testAddByIndexInBegin(){
        actualContainer.addByIndex(0, new User("000", "000", 13));
        Container<User> expectedContainer = new Container<>();
        expectedContainer.add(new User("000", "000", 13));
        for(int i = 1; i < 13; i++){
            expectedContainer.add(new User("elem-" + i,"password-" + i, i));
        }
        assertArrayEquals(expectedContainer.getData(), actualContainer.getData());
        assertEquals(expectedContainer.getSize(), actualContainer.getSize());
    }

    @Test
    public void testIncorrectIndexExceptionInAdd(){
        assertThrows(IncorrectIndexException.class, () ->
                actualContainer.addByIndex(500, new User("test-username-3", "test-password-3", 20)));
    }
}


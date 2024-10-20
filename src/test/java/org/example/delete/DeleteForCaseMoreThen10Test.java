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
        for (int i = 1; i < 13; i++){
            actualContainer.add(new User("elem-" + i,"password-" + i, i));
        }
    }

    @Test
    public void testDeleteFromTheMiddle(){
        actualContainer.delete(4);
        Container<User> expectedContainer = new Container<>();
        for(int i = 1; i < 13; i++){
            if (i != 5){
                expectedContainer.add(new User("elem-" + i,"password-" + i, i));
            }
        }
        assertArrayEquals(expectedContainer.getData(), actualContainer.getData());
        assertEquals(expectedContainer.getSize(), actualContainer.getSize());
    }

    @Test
    public void testDeleteFromTheBegin(){
        actualContainer.delete(0);
        Container<User> expectedContainer = new Container<>();
        for(int i = 1; i < 13; i++){
            if (i != 1){
                expectedContainer.add(new User("elem-" + i,"password-" + i, i));
            }
        }
        assertArrayEquals(expectedContainer.getData(), actualContainer.getData());
        assertEquals(expectedContainer.getSize(), actualContainer.getSize());
    }

    @Test
    public void testDeleteFromEnd(){
        actualContainer.delete(11);
        Container<User> expectedContainer = new Container<>();
        for(int i = 1; i < 13; i++){
            if (i != 12){
                expectedContainer.add(new User("elem-" + i,"password-" + i, i));
            }
        }
        assertArrayEquals(expectedContainer.getData(), actualContainer.getData());
        assertEquals(expectedContainer.getSize(), actualContainer.getSize());
    }

    @Test
    public void testIncorrectIndexExceptionInDelete(){
        assertThrows(IncorrectIndexException.class, () -> actualContainer.delete(-59));
    }
}


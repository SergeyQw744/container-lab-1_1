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
public class DeleteTest {
    @InjectMocks
    Container<User> testContainer;
    @BeforeEach
    public void createTestContainer(){
        testContainer.add(new User("test-username-0", "test-password-0", 20));
        testContainer.add(new User("test-username-1", "test-password-1", 20));
        testContainer.add(new User("test-username-2", "test-password-2", 20));
    }
    @Test
    public void testDeleteFromTheMiddle(){
        testContainer.delete(1);
        Container<User> expectedResult = new Container<>();
        expectedResult.add(new User("test-username-0", "test-password-0", 20));
        expectedResult.add(new User("test-username-2", "test-password-2", 20));
        assertArrayEquals(testContainer.getData(), expectedResult.getData());
        assertEquals(testContainer.getSize(), expectedResult.getSize());
    }

    @Test
    public void testDeleteFromTheBegin(){
        testContainer.delete(0);
        Container<User> expectedResult = new Container<>();
        expectedResult.add(new User("test-username-1", "test-password-1", 20));
        expectedResult.add(new User("test-username-2", "test-password-2", 20));
        assertArrayEquals(testContainer.getData(), expectedResult.getData());
        assertEquals(testContainer.getSize(), expectedResult.getSize());
    }

    @Test
    public void testDeleteFromEnd(){
        testContainer.delete(2);
        Container<User> expectedResult = new Container<>();
        expectedResult.add(new User("test-username-0", "test-password-0", 20));
        expectedResult.add(new User("test-username-1", "test-password-1", 20));
        assertArrayEquals(testContainer.getData(), expectedResult.getData());
        assertEquals(testContainer.getSize(), expectedResult.getSize());
    }
    @Test
    public void testIncorrectIndexExceptionInDelete(){
        assertThrows(IncorrectIndexException.class, () -> testContainer.delete(12));
    }
}


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
public class AddTest {
    @InjectMocks
    Container<User> testContainer;
    @BeforeEach
    public void fillTestContainer(){
        testContainer.add(new User("test-username-0", "test-password-0", 20));
        testContainer.add(new User("test-username-1", "test-password-1", 20));
        testContainer.add(new User("test-username-2", "test-password-2", 20));
    }
    @Test
    public void testAdd(){
        testContainer.add(new User("test-username-3", "test-password-3", 20));
        Container<User> expectedResult = new Container<>();
        expectedResult.add(new User("test-username-0", "test-password-0", 20));
        expectedResult.add(new User("test-username-1", "test-password-1", 20));
        expectedResult.add(new User("test-username-2", "test-password-2", 20));
        expectedResult.add(new User("test-username-3", "test-password-3", 20));
        assertArrayEquals(testContainer.getData(), expectedResult.getData());
        assertEquals(testContainer.getSize(), expectedResult.getSize());
    }
    @Test
    public void testAddByIndexInMiddle(){
        testContainer.addByIndex(1, new User("test-username-3", "test-password-3", 20));
        Container<User> expectedResult = new Container<>();
        expectedResult.add(new User("test-username-0", "test-password-0", 20));
        expectedResult.add(new User("test-username-3", "test-password-3", 20));
        expectedResult.add(new User("test-username-1", "test-password-1", 20));
        expectedResult.add(new User("test-username-2", "test-password-2", 20));
        assertArrayEquals(testContainer.getData(), expectedResult.getData());
        assertEquals(testContainer.getSize(), expectedResult.getSize());
    }
    @Test
    public void testAddByIndexInBegin(){
        testContainer.addByIndex(0, new User("test-username-3", "test-password-3", 20));
        Container<User> expectedResult = new Container<>();
        expectedResult.add(new User("test-username-3", "test-password-3", 20));
        expectedResult.add(new User("test-username-0", "test-password-0", 20));
        expectedResult.add(new User("test-username-1", "test-password-1", 20));
        expectedResult.add(new User("test-username-2", "test-password-2", 20));
        assertArrayEquals(testContainer.getData(), expectedResult.getData());
        assertEquals(testContainer.getSize(), expectedResult.getSize());
    }
    @Test
    public void testIncorrectIndexExceptionInAdd(){
        assertThrows(IncorrectIndexException.class, () ->
                testContainer.addByIndex(-5, new User("test-username-3", "test-password-3", 20)));
    }
}


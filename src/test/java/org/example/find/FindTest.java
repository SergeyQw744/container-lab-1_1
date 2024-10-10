package org.example.find;

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
public class FindTest {
    @InjectMocks
    Container<User> testContainer;
    @BeforeEach
    public void createTestContainer(){
        testContainer.add(new User("test-username-0", "test-password-0", 20));
        testContainer.add(new User("test-username-1", "test-password-1", 20));
        testContainer.add(new User("test-username-2", "test-password-2", 20));
    }
    @Test
    public void testFind(){
        User user = testContainer.find(1);
        assertTrue(user.equals(new User("test-username-1", "test-password-1", 20)));
    }
    @Test
    public void testIncorrectIndexExceptionInFind(){
        assertThrows(IncorrectIndexException.class, () -> testContainer.find(-2));
    }
    @Test
    public void testFindNull(){
        assertEquals(testContainer.find(7), null);
    }
}

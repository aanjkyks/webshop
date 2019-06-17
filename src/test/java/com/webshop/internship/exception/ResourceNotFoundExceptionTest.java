package com.webshop.internship.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ResourceNotFoundExceptionTest {
    private static RuntimeException runtimeException = new RuntimeException();

    @Test
    public void testEmptyConstructor() {
        try {
            throw new ResourceNotFoundException();
        } catch (ResourceNotFoundException e) {
            assertNull(e.getMessage());
            assertNull(e.getCause());
        }
    }

    @Test
    public void testMessageConstructor() {
        try {
            throw new ResourceNotFoundException("message");
        } catch (ResourceNotFoundException e) {
            assertEquals("message", e.getMessage());
        }
    }

    @Test
    public void testCauseConstructor() {
        try {
            throw new ResourceNotFoundException(runtimeException);
        } catch (ResourceNotFoundException e) {
            assertEquals(runtimeException, e.getCause());
        }
    }

    @Test
    public void testMessageCauseConstructor() {
        try {
            throw new ResourceNotFoundException("message", runtimeException);
        } catch (ResourceNotFoundException e) {
            assertEquals("message", e.getMessage());
            assertEquals(runtimeException, e.getCause());
        }
    }

}
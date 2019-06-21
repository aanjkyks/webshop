package com.webshop.internship.exception;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ApiExceptionHandlerTest {
    private ApiExceptionHandler exceptionHandler = new ApiExceptionHandler();

    @Test
    public void testErrorItem() {
        ApiExceptionHandler.ErrorItem item = new ApiExceptionHandler.ErrorItem();
        item.setCode("code");
        item.setMessage("message");
        assertEquals("code", item.getCode());
        assertEquals("message", item.getMessage());
    }

    @Test
    public void testErrorResponse() {
        ApiExceptionHandler.ErrorItem item = new ApiExceptionHandler.ErrorItem();
        item.setCode("code1");
        ApiExceptionHandler.ErrorItem item1 = new ApiExceptionHandler.ErrorItem();
        item1.setCode("code2");
        List <ApiExceptionHandler.ErrorItem> errorItems = new ArrayList <>();
        errorItems.add(item);
        errorItems.add(item1);
        ApiExceptionHandler.ErrorResponse errorResponse = new ApiExceptionHandler.ErrorResponse();
        List <ApiExceptionHandler.ErrorItem> errorItemList = new ArrayList <>(errorItems);
        errorResponse.setErrors(errorItemList);
        assertEquals(errorItems, errorResponse.getErrors());
        item.setCode("code3");
        errorItems.add(item);
        assertNotEquals(errorItems, errorResponse.getErrors());
        errorResponse.addError(item);
        assertEquals(errorItems, errorResponse.getErrors());

    }

    @Test
    public void testResourceNotFoundExceptionHandler() {
        ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException("message");
        ApiExceptionHandler.ErrorItem item = new ApiExceptionHandler.ErrorItem();
        item.setMessage("message");
        ResponseEntity <ApiExceptionHandler.ErrorItem> responseEntity = new ResponseEntity <>(item,
                HttpStatus.NOT_FOUND);
        assertEquals(responseEntity, exceptionHandler.handle(resourceNotFoundException));
        resourceNotFoundException = new ResourceNotFoundException();
        assertNotEquals(responseEntity, exceptionHandler.handle(resourceNotFoundException));
        responseEntity = new ResponseEntity <>(new ApiExceptionHandler.ErrorItem(), HttpStatus.NOT_FOUND);
        assertEquals(responseEntity, exceptionHandler.handle(resourceNotFoundException));
    }

}
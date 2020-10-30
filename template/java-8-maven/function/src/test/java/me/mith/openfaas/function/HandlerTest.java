package me.mith.openfaas.function;

import com.openfaas.model.IHandler;
import com.openfaas.model.Request;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class HandlerTest {

    @Test
    public void handle() {
        IHandler handler = new Handler();
        assertEquals("Hello, !", handler.Handle(new Request("", Collections.<String, String>emptyMap())).getBody());
        assertEquals("Hello, world!", handler.Handle(new Request("world", Collections.<String, String>emptyMap())).getBody());
    }
}

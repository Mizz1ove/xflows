package com.nhnacademy.aiot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WireTest {

    private Wire wire;

    @BeforeEach
    public void setUp() {
        wire = new Wire();
    }

    @Test
    public void testPutAndHasMessage() {
        ObjectNode payload = new JsonNodeFactory(false).objectNode().put("test", "test");
        Message message = new Message("test", payload);

        wire.put(message);

        assertTrue(wire.hasMessage(), "Wire should have a message after put()");
    }

    @Test
    void hasMessage() {
    }

    @Test
    void get() {
    }
}

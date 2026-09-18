package com.netus.webhookforge.webhookevent;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(Long id) {
        super("Ивет с id: " + id + " не найден");
    }
}

package com.client.api.ws.rasmooplus.integration;

public interface MailIntegration {

    void send(String mailTo, String message, String subject);
}

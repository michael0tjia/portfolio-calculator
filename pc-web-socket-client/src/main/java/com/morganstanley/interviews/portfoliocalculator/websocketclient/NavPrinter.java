package com.morganstanley.interviews.portfoliocalculator.websocketclient;

import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class NavPrinter {
    private final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
    private final int PORT = 8080;
    private SockJsClient sockJsClient;
    private WebSocketStompClient stompClient;

    @PostConstruct
    public void init() throws Exception {
        setup();
        startReading();
    }

    public void setup() {
        final List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        this.sockJsClient = new SockJsClient(transports);

        this.stompClient = new WebSocketStompClient(this.sockJsClient);
        this.stompClient.setMessageConverter(new StringMessageConverter());
    }

    public void startReading() throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);

        final StompSessionHandler handler = new CustomSessionHandler() {
            @Override
            public void afterConnected(final StompSession session, final StompHeaders connectedHeaders) {
                session.subscribe("/topic/nav", new StompFrameHandler() {
                    @Override
                    public Type getPayloadType(final StompHeaders headers) {
                        return String.class;
                    }

                    @Override
                    public void handleFrame(final StompHeaders headers, final Object payload) {
                        try {
                            final String nav = (String) payload;
                            System.out.println("NAV:" + nav);
                        } catch (final Throwable t) {
                            t.printStackTrace();
                        }
                    }
                });
            }
        };

        this.stompClient.connect("ws://localhost:{port}/portforlio-websocket", this.headers, handler, this.PORT);

        latch.await();
    }

    private class CustomSessionHandler extends StompSessionHandlerAdapter {
        @Override
        public void handleException(final StompSession s, final StompCommand c, final StompHeaders h, final byte[] p, final Throwable ex) {

        }

        @Override
        public void handleTransportError(final StompSession session, final Throwable ex) {
        }
    }

}

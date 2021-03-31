package com.titan.loki;

import lombok.extern.java.Log;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

@Log
public class Loki implements Runnable {

    public Loki() {}

    @Override
    public void run() {
        log.info("Started Loki...");
        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            ZMQ.Socket requester = context.createSocket(SocketType.REQ);
            requester.connect("tcp://0.0.0.0:5559");
            for (int request_nbr = 0; request_nbr < 10; request_nbr++) {
                requester.send("One", 0);
                String reply = requester.recvStr(0);
                System.out.println("Received reply " + request_nbr + " [" + reply + "]");
            }
        }
    }

}
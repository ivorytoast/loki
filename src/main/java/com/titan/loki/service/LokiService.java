package com.titan.loki.service;

import com.titan.loki.model.OrderRequest;
import lombok.extern.java.Log;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

@Log
public class LokiService {

    public LokiService() {}

    public void submitRequest(OrderRequest request) {
        log.info("Submitting request");

        String fixMessage = request.toFixMessage(request);

        try (ZContext context = new ZContext()) {
            ZMQ.Socket requester = context.createSocket(SocketType.REQ);
            boolean didConnect = requester.connect("tcp://bifrost:5559");

            log.info("(Loki) Connected to bifrost: " + didConnect);
            requester.send(fixMessage, 0);
            String reply = requester.recvStr(0);
            log.info("Received reply [" + reply + "]");
        }

        log.info("Finished submitting request");
    }

}

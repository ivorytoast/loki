package com.titan.loki.service;

import com.titan.loki.database.repository.UsersRepository;
import com.titan.loki.model.OrderRequest;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

@Log
public class LokiService {

    @Autowired
    private UsersRepository usersRepository;

    public LokiService() {}

    public String submitRequest(OrderRequest request) {
        if (request == null) return "Request cannot be null";
        String validationErrors = request.validate();
        if (!validationErrors.isEmpty()) return validationErrors;
        if (!usersRepository.existsById(request.getUserID())) return "Not a valid user";

        // TODO: Check valid symbol with REDIS set

        String fixMessage = request.toFixMessage(request);

        try (ZContext context = new ZContext()) {
            ZMQ.Socket requester = context.createSocket(SocketType.REQ);
            boolean didConnect = requester.connect("tcp://bifrost:5559");

            if (!didConnect) {
                // TODO: Make this severe, like an email
                log.severe("Loki is not connecting to Bifrost!");
            }

            requester.send(fixMessage, 0);
            String reply = requester.recvStr(0);

            log.info("Received reply [" + reply + "]");

            return reply;
        }
    }

}

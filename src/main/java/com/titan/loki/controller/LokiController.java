package com.titan.loki.controller;

import com.titan.loki.model.OrderRequest;
import com.titan.loki.service.LokiService;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

@Log
@CrossOrigin
@RestController
@RequestMapping("loki")
public class LokiController {

    private final LokiService service;

    public LokiController(LokiService service) {
        this.service = service;
    }

    @PostMapping("/v1/request/submit")
    public String submitRequest(@RequestBody OrderRequest request) {
        log.info("Hit: /v1/request/submit on (Loki). Submitting new request: " + request.toString());
        return service.submitRequest(request);
    }

}

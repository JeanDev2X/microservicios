package com.mitocode.orchestrator.client.ordes.httpexchange;

import com.mitocode.orchestrator.client.ordes.restclient.dto.CreateOrderRequest;
import com.mitocode.orchestrator.client.ordes.restclient.dto.CreateOrderResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

//@HttpExchange("/v1") Ya no va el path base, se configura en el RestClient con el config server
@HttpExchange
public interface OrderServiceV1HttpExchangeClient {

    //@PostExchange("/orders") Ya no va el path base, se configura en el RestClient con el config server
    @PostExchange
    CreateOrderResponse create(@RequestBody CreateOrderRequest request);

}

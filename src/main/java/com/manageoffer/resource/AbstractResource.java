package com.manageoffer.resource;

import javax.ws.rs.core.Response;

import com.manageoffer.model.OfferResponse;

public abstract class AbstractResource {
    protected Response build(Object data) {
        return Response.ok(new OfferResponse(data)).build();
    }

    protected Response build() {
        return Response.ok(new OfferResponse()).build();
    }
}

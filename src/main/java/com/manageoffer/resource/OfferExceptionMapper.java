package com.manageoffer.resource;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.manageoffer.model.OfferResponse;
import com.manageoffer.service.exception.OfferException;

@Provider
public class OfferExceptionMapper extends Exception implements ExceptionMapper<Throwable> {
	private static final long serialVersionUID = 8887267511588413375L;
	
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(Throwable e) {
        if (e instanceof OfferException) {
            return Response.ok(new OfferResponse((OfferException) e)).build();
        }
        return Response.ok().status(500).entity(e.getMessage()).build();
    }
}

package com.manageoffer.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manageoffer.model.OfferParams;
import com.manageoffer.service.OfferService;

@Component
@Path("/offer")
public class OfferResource extends AbstractResource {
	@Autowired
    protected OfferService offerService;
	
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOffer(OfferParams offerParams) {
        return build(offerService.createOffer(offerParams));
    }

    @POST
    @Path("/cancel/{offerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelOffer(@PathParam("offerId") Long offerId) {
    	return build(offerService.cancelOffer(offerId));
    }
    
    @GET
    @Path("/get/{offerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOffer(@PathParam("offerId") Long offerId) {
    	return build(offerService.getOffer(offerId));
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listOffers() {
    	return build(offerService.listOffers());
    }
}

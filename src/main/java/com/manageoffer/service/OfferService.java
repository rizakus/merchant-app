package com.manageoffer.service;

import java.util.List;

import com.manageoffer.entity.Offer;
import com.manageoffer.model.OfferParams;

public interface OfferService {
	Offer createOffer(OfferParams offerParams);
	Offer cancelOffer(Long id);
	Offer getOffer(Long id);
	List<Offer> listOffers();
}

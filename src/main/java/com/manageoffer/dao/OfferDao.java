package com.manageoffer.dao;

import java.util.List;

import com.manageoffer.entity.Offer;

public interface OfferDao {
	Offer save(Offer offer);
	Offer get(Long id);
	List<Offer> list();
}

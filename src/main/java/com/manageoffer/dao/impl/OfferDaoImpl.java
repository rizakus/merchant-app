package com.manageoffer.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.manageoffer.dao.OfferDao;
import com.manageoffer.entity.Offer;

@Repository(value = "offerDao")
public class OfferDaoImpl implements OfferDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Offer save(Offer offer) {
		entityManager.persist(offer);
		return offer;
	}
	
	@Override
	public Offer get(Long id) {
		 return entityManager.find(Offer.class, id);
	}
	
	@Override
	public List<Offer> list() {
		 String query = "select o from Offer o order by id desc";
	     return entityManager.createQuery(query).getResultList();
	}
}

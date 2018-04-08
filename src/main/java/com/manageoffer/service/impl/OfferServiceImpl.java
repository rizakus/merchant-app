package com.manageoffer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manageoffer.entity.Offer;
import com.manageoffer.model.OfferParams;
import com.manageoffer.model.OfferStatus;
import com.manageoffer.service.OfferService;
import com.manageoffer.service.exception.OfferError;
import com.manageoffer.service.exception.OfferException;

@Service(value = "offerService")
public class OfferServiceImpl extends AbstractOfferServiceImpl implements OfferService {
	private static final Logger logger = LoggerFactory.getLogger(OfferService.class);
	
	@Override
	@Transactional
	public Offer createOffer(OfferParams offerParams) {
		try {
			checkOfferParams(offerParams);
			Offer offer = new Offer();
			offer.setCurrency(offerParams.getCurrency());
			offer.setExpireDate(checkExpireDateParameter(offerParams.getExpireDate()));
			offer.setName(offerParams.getName());
			offer.setPrice(offerParams.getPrice());
			offer.setStatus(OfferStatus.ACTIVE);
			offer.setUserDescription(offerParams.getUserDescription());
			return offerDao.save(offer);
		} catch(OfferException oe) {
			logger.error("Exception : {} - {} ", Integer.toString(oe.getErrorCode()), oe.getErrorMessage(), oe);
			throw oe;
		} catch(RuntimeException re) {
			logger.error("Exception : {} {} ", re.getMessage(), re);
			throw re;
		} catch(Exception e) {
			logger.error("Exception : {} {} ", e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	@Transactional
	public Offer cancelOffer(Long id) {
		try {
			Offer cancelOffer = validateOffer(id);
			cancelOffer.setCancelDate(new Date());;
			cancelOffer.setStatus(OfferStatus.CANCELED);
			return offerDao.save(cancelOffer);
		} catch(OfferException oe) {
			logger.error("Exception : {} - {} ", Integer.toString(oe.getErrorCode()), oe.getErrorMessage(), oe);
			throw oe;
		} catch(RuntimeException re) {
			logger.error("Exception : {} {} ", re.getMessage(), re);
			throw re;
		} catch(Exception e) {
			logger.error("Exception : {} {} ", e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	@Transactional
	public Offer getOffer(Long id) {
		try {
			Offer offer = offerDao.get(id);
			if (null == offer) {
				throw new OfferException(OfferError.INVALID_OFFER_ID, id);
			}
			offer = checkOfferExpireDate(offer);
			return offer;
		} catch(OfferException oe) {
			logger.error("Exception : {} - {} ", Integer.toString(oe.getErrorCode()), oe.getErrorMessage(), oe);
			throw oe;
		} catch(RuntimeException re) {
			logger.error("Exception : {} {} ", re.getMessage(), re);
			throw re;
		} catch(Exception e) {
			logger.error("Exception : {} {} ", e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Offer> listOffers() {
		try {
			List<Offer> tmplistOffers = new ArrayList<Offer>();
			List<Offer> listOffers = offerDao.list();
			for(Offer offer : listOffers) {
				offer = checkOfferExpireDate(offer);
				tmplistOffers.add(offer);
			}
			return tmplistOffers;
		} catch(OfferException oe) {
			logger.error("Exception : {} - {} ", Integer.toString(oe.getErrorCode()), oe.getErrorMessage(), oe);
			throw oe;
		} catch(RuntimeException re) {
			logger.error("Exception : {} {} ", re.getMessage(), re);
			throw re;
		} catch(Exception e) {
			logger.error("Exception : {} {} ", e.getMessage(), e);
			throw e;
		}
	}
}

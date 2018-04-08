package com.manageoffer.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.manageoffer.dao.OfferDao;
import com.manageoffer.entity.Offer;
import com.manageoffer.model.OfferParams;
import com.manageoffer.model.OfferStatus;
import com.manageoffer.service.exception.OfferError;
import com.manageoffer.service.exception.OfferException;

public abstract class AbstractOfferServiceImpl {
	@Autowired
	protected OfferDao offerDao;
	
	public Offer validateOffer(Long id) {
		Offer offer = offerDao.get(id);
		if (null == offer) {
			throw new OfferException(OfferError.INVALID_OFFER_ID, id);
		} 
		offer = checkOfferExpireDate(offer);
		if (OfferStatus.EXPIRED.equals(offer.getStatus())) {
			throw new OfferException(OfferError.OFFER_EXPIRED, offer.getId().toString());
		} else if (OfferStatus.CANCELED.equals(offer.getStatus())) {
			throw new OfferException(OfferError.OFFER_ALREADY_CANCELED, offer.getId().toString());
		}
		return offer;
	}

	public Date checkExpireDateParameter(String expireDateParameter) {
		Date expireDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			expireDate = sdf.parse(expireDateParameter);
		} catch(ParseException e) {
			throw new OfferException(OfferError.INVALID_DATE_FORMAT);
		}
		if (expireDate.compareTo(new Date()) < 0) {
            throw new OfferException(OfferError.INVALID_EXPIRE_DATE);
        }
		return expireDate;
	}
	
	public Offer checkOfferExpireDate(Offer offer) {
		if (OfferStatus.ACTIVE.equals(offer.getStatus()) && offer.getExpireDate().compareTo(new Date()) < 0) {
			offer.setStatus(OfferStatus.EXPIRED);
			offer = offerDao.save(offer);
        }
		return offer;
	}
	
	public void checkOfferParams(OfferParams offerParams) {
		if(null == offerParams.getName() || "".equals(offerParams.getName())) {
			throw new OfferException(OfferError.NAME_PARAMETER_CAN_NOT_BE_NULL);
		} else if (null == offerParams.getPrice()) {
			throw new OfferException(OfferError.PRICE_PARAMETER_CAN_NOT_BE_NULL);
		} else if (null == offerParams.getCurrency()) {
			throw new OfferException(OfferError.CURRENCY_PARAMETER_CAN_NOT_BE_NULL);
		} else if (null == offerParams.getCurrency()) {
			throw new OfferException(OfferError.CURRENCY_PARAMETER_CAN_NOT_BE_NULL);
		} else if (null == offerParams.getExpireDate() || "".equals(offerParams.getExpireDate())) {
			throw new OfferException(OfferError.EXPIRE_DATE_PARAMETER_CAN_NOT_BE_NULL);
		} else if (null == offerParams.getUserDescription() || "".equals(offerParams.getUserDescription())) {
			throw new OfferException(OfferError.USER_DESCRIPTION_PARAMETER_CAN_NOT_BE_NULL);
		}
	}
}

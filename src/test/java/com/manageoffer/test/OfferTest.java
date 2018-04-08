package com.manageoffer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.manageoffer.entity.Offer;
import com.manageoffer.model.Currency;
import com.manageoffer.model.OfferParams;
import com.manageoffer.model.OfferStatus;
import com.manageoffer.service.exception.OfferError;
import com.manageoffer.service.exception.OfferException;

public class OfferTest extends AbstractOfferTest {
	@Before
    public void before() {
        super.setUp();
    }
	
	@Test
	public void testCheckOfferParamsEmptyName() {
		try {
			offerService.createOffer(new OfferParams("", new Double(7.9), Currency.GBP, getExpireDate(0, 0, 7, 0, 0, 0), "Faber Castle Pen"));
			assertFalse(true);
		} catch(OfferException oe) {
			assertEquals(oe.getErrorCode(), OfferError.NAME_PARAMETER_CAN_NOT_BE_NULL.getCode());
		}
	}
	
	@Test
	public void testCheckOfferParamsNullName() {
		try {
			offerService.createOffer(new OfferParams(null, new Double(7.9), Currency.GBP, getExpireDate(0, 0, 7, 0, 0, 0), "Faber Castle Pen"));
			assertFalse(true);
		} catch(OfferException oe) {
			assertEquals(oe.getErrorCode(), OfferError.NAME_PARAMETER_CAN_NOT_BE_NULL.getCode());
		}
	}
	
	@Test
	public void testCheckOfferParamsNullPrice() {
		try {
			offerService.createOffer(new OfferParams("Pencil", null, Currency.GBP, getExpireDate(0, 0, 7, 0, 0, 0), "Faber Castle Pen"));
			assertFalse(true);
		} catch(OfferException oe) {
			assertEquals(oe.getErrorCode(), OfferError.PRICE_PARAMETER_CAN_NOT_BE_NULL.getCode());
		}
	}
	
	@Test
	public void testCheckOfferParamsNullCurrency() {
		try {
			offerService.createOffer(new OfferParams("Pencil", new Double(7.9), null, getExpireDate(0, 0, 7, 0, 0, 0), "Faber Castle Pen"));
			assertFalse(true);
		} catch(OfferException oe) {
			assertEquals(oe.getErrorCode(), OfferError.CURRENCY_PARAMETER_CAN_NOT_BE_NULL.getCode());
		}
	}
	
	@Test
	public void testCheckOfferParamsNullExpireDate() {
		try {
			offerService.createOffer(new OfferParams("Pencil", new Double(7.9), Currency.GBP, null, "Faber Castle Pen"));
			assertFalse(true);
		} catch(OfferException oe) {
			assertEquals(oe.getErrorCode(), OfferError.EXPIRE_DATE_PARAMETER_CAN_NOT_BE_NULL.getCode());
		}
	}
	
	@Test
	public void testCheckOfferParamsEmptyUserDescription() {
		try {
			offerService.createOffer(new OfferParams("Pencil", new Double(7.9), Currency.GBP, getExpireDate(0, 0, 7, 0, 0, 0), ""));
			assertFalse(true);
		} catch(OfferException oe) {
			assertEquals(oe.getErrorCode(), OfferError.USER_DESCRIPTION_PARAMETER_CAN_NOT_BE_NULL.getCode());
		}
	}
	
	@Test
	public void testCheckOfferParamsNullUserDescription() {
		try {
			offerService.createOffer(new OfferParams("Pencil", new Double(7.9), Currency.GBP, getExpireDate(0, 0, 7, 0, 0, 0), null));
			assertFalse(true);
		} catch(OfferException oe) {
			assertEquals(oe.getErrorCode(), OfferError.USER_DESCRIPTION_PARAMETER_CAN_NOT_BE_NULL.getCode());
		}
	}
	
	@Test
	public void testCreateOfferInvalidDateFormat() {
		try {
			offerService.createOffer(new OfferParams("Pencil", new Double(7.9), Currency.GBP, "2018-04-10", "Faber Castle Pen"));
			assertFalse(true);
		} catch(OfferException oe) {
			assertEquals(oe.getErrorCode(), OfferError.INVALID_DATE_FORMAT.getCode());
		}
	}
	
	@Test
	public void testCreateOfferInvalidExpireDate() {
		try {
			offerService.createOffer(new OfferParams("Pencil", new Double(7.9), Currency.GBP, "2010-02-10 17:00:00", "Faber Castle Pen"));
			assertFalse(true);
		} catch(OfferException oe) {
			assertEquals(oe.getErrorCode(), OfferError.INVALID_EXPIRE_DATE.getCode());
		}
	}
	
	@Test
	public void testCreateOffer() {
		Offer offer = offerService.createOffer(new OfferParams("Pencil", new Double(7.9), Currency.GBP, getExpireDate(0, 0, 7, 0, 0, 0), "Faber Castle Pen"));
		assertEquals(OfferStatus.ACTIVE, offer.getStatus());
		assertNotNull(offer.getId());
	}

	@Test
	public void testCancelOfferInvalidOfferId() {
		try {
			offerService.cancelOffer(new Long("289763"));
			assertFalse(true);
		} catch(OfferException oe) {
			assertEquals(oe.getErrorCode(), OfferError.INVALID_OFFER_ID.getCode());
		}
	}
	
	@Test
	public void testCancelOfferExpired() {
		try {
			String expireDate = getExpireDate(0, 0, 0, 0, 0, 3);  // 3 seconds period expireDate
			Offer offer = offerService.createOffer(new OfferParams("Pencil", new Double(7.9), Currency.GBP, expireDate, "Faber Castle Pen"));
			assertEquals(OfferStatus.ACTIVE, offer.getStatus());
			assertNotNull(offer.getId());
			sleep(6); // wait 6 seconds offer to expire
			offerService.cancelOffer(offer.getId());
			assertFalse(true);
		} catch(OfferException oe) {
			assertEquals(oe.getErrorCode(), OfferError.OFFER_EXPIRED.getCode());  // Cannot cancel expired offer.
		}
	}
	
	@Test
	public void testCancelOfferAlreadyCancelled() {
		try {
			Offer offer = offerService.createOffer(new OfferParams("Pencil", new Double(7.9), Currency.GBP, getExpireDate(0, 0, 5, 12, 0, 0), "Faber Castle Pen"));
			assertEquals(OfferStatus.ACTIVE, offer.getStatus());
			assertNotNull(offer.getId());
			offer = offerService.cancelOffer(offer.getId());  // cancel offer
			assertEquals(OfferStatus.CANCELED, offer.getStatus());
			offer = offerService.cancelOffer(offer.getId());  // try to cancel again offer already cancelled
			assertFalse(true);
		} catch(OfferException oe) {
			assertEquals(oe.getErrorCode(), OfferError.OFFER_ALREADY_CANCELED.getCode());
		}
	}
	
	@Test
	public void testCancelOffer() {
		Offer offer = offerService.createOffer(new OfferParams("Pencil", new Double(7.9), Currency.GBP, getExpireDate(0, 0, 5, 12, 0, 0), "Faber Castle Pen"));
		assertEquals(OfferStatus.ACTIVE, offer.getStatus());
		assertNotNull(offer.getId());
		offer = offerService.cancelOffer(offer.getId());
		assertEquals(OfferStatus.CANCELED, offer.getStatus());
	}
	
	@Test
	public void testGetOfferInvalidOfferId() {
		try {
			offerService.getOffer(new Long("289763"));
			assertFalse(true);
		} catch(OfferException oe) {
			assertEquals(oe.getErrorCode(), OfferError.INVALID_OFFER_ID.getCode());
		}
	}
	
	@Test
	public void testGetOffer() {
		Offer offer = offerService.createOffer(new OfferParams("Pencil", new Double(7.9), Currency.GBP, getExpireDate(0, 0, 5, 12, 0, 0), "Faber Castle Pen"));
		assertEquals(OfferStatus.ACTIVE, offer.getStatus());
		assertNotNull(offer.getId());
		offer = offerService.getOffer(offer.getId());
		assertEquals(OfferStatus.ACTIVE, offer.getStatus());
		assertNotNull(offer.getId());
	}
	
	@Test
	public void testCheckOfferExpired() {
		String expireDate = getExpireDate(0, 0, 0, 0, 0, 3);  // 3 seconds period expireDate
		Offer offer = offerService.createOffer(new OfferParams("Pencil", new Double(7.9), Currency.GBP, expireDate, "Faber Castle Pen"));
		assertEquals(OfferStatus.ACTIVE, offer.getStatus());
		assertNotNull(offer.getId());
		sleep(6); // wait 6 seconds offer to expire
		offer = offerService.getOffer(offer.getId());
		assertEquals(OfferStatus.EXPIRED, offer.getStatus());  // After the period of time defined on the offer, offer expired and further request to query the offer reflect that. 
	}
	
	@Test
	public void testGetOfferCancelled() {
		Offer offer = offerService.createOffer(new OfferParams("Pencil", new Double(7.9), Currency.GBP, getExpireDate(0, 0, 5, 12, 0, 0), "Faber Castle Pen"));
		assertEquals(OfferStatus.ACTIVE, offer.getStatus());
		assertNotNull(offer.getId());
		offer = offerService.cancelOffer(offer.getId());  // cancel offer
		assertEquals(OfferStatus.CANCELED, offer.getStatus());
		offer = offerService.getOffer(offer.getId());   // get offer
		assertEquals(OfferStatus.CANCELED, offer.getStatus());
		assertNotNull(offer.getCancelDate());
	}
	
	@Test
	public void testListOffers() {
		List<Offer> offerList = offerService.listOffers();
		assertNotNull(offerList);
		assertTrue(offerList.size() > 0);
	}
}


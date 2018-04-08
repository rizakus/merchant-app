package com.manageoffer.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.manageoffer.config.WebConfig;
import com.manageoffer.model.Currency;
import com.manageoffer.model.OfferParams;
import com.manageoffer.service.OfferService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({ @ContextConfiguration(classes = WebConfig.class) })
public abstract class AbstractOfferTest {
	@Autowired
	protected OfferService offerService;
	
	public void setUp() {
		offerService.createOffer(new OfferParams("Apple", new Double(2.1), Currency.EUR, getExpireDate(0, 0, 8, 1, 15, 0), "Green apple"));
		offerService.createOffer(new OfferParams("Banana", new Double(1.2), Currency.GBP, getExpireDate(0, 0, 15, 5, 30, 0), "Equador banana"));
		offerService.createOffer(new OfferParams("Cherry", new Double(1.8), Currency.USD, getExpireDate(0, 0, 7, 2, 30, 0), "Sour cherry"));
		offerService.createOffer(new OfferParams("Milk", new Double(2.3), Currency.TRY, getExpireDate(0, 0, 20, 0, 0, 0), "Fresh Milk"));
    }
	
	protected String getExpireDate(int year, int month, int date, int hour, int minute, int second) {
		// Needed to run JUnit tests any time we want. Generates expire date (now + time period) 
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, year);
        c.add(Calendar.MONTH, month);
        c.add(Calendar.DATE, date);
        c.add(Calendar.HOUR, hour);
        c.add(Calendar.MINUTE, minute);
        c.add(Calendar.SECOND, second);
        Date expireDate = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(expireDate);
	}
	
	protected void sleep(int seconds) {
	    try {
	        Thread.sleep(seconds * 1000);
	    } catch (InterruptedException e) {
	        // nothing to do
	    }
	}
}

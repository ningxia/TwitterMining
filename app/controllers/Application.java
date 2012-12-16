package controllers;

import play.*;
import play.db.jpa.JPA;
import play.db.jpa.JPABase;
import play.mvc.*;

import java.util.*;

import javax.persistence.Query;

import models.*;

public class Application extends Controller {

    public static void index() {
    	List<Site> siteList = Site.findAll();
    	int obama = 0;
    	int romney = 0;
    	for(int i = 0; i < siteList.size(); i ++) {
    		obama += siteList.get(i).obama;
    		romney += siteList.get(i).romney;
    	}
    	// Map Center site
    	float centerLat = 36.131294F;
    	float centerLng = -95.937332F;
    	Site site = new Site("", "US", centerLat, centerLng, obama, romney);
    	render(site);
    }
    
    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }
    
    public static void sites(String zoom, String swLat, String swLng, String neLat, String neLng) {
    	
    	int tweetsCountFilter = 10;
    	int level = Integer.parseInt(zoom);
    	
    	List<Site> site = new ArrayList<Site>();
    	Site s = null;
    	Long obama = 0L;
    	Long romney = 0L;
    	// Nation wide view: show state capital statistics
    	if(level < 5) {
    		s = new Site("", "US", 36.131294F, -95.937332F, 6724, 5746);
    		site.add(s);
    	}
    	else if(level < 6) {
    		List<Capital> capitalList = Capital.findAll();
    		for(Capital cap: capitalList) {
    			obama = (Long) JPA.em().createQuery("SELECT SUM(s.obama) FROM Site s WHERE s.state = " + "'" + cap.state + "'").getResultList().get(0);
    			romney = (Long) JPA.em().createQuery("SELECT SUM(s.romney) FROM Site s WHERE s.state = " + "'" + cap.state + "'").getResultList().get(0);
    			s = new Site(cap.city, cap.state, cap.latitude, cap.longitude, safeLongToInt(obama), safeLongToInt(romney));
    			site.add(s);
    		}
    	}
    	else {
    		site = Site.find("latitude >= ? and latitude <= ? and longitude >= ? and longitude <= ? and obama > ? and romney > ?", Float.parseFloat(swLat), Float.parseFloat(neLat), Float.parseFloat(swLng), Float.parseFloat(neLng), tweetsCountFilter, tweetsCountFilter).fetch();
    	}
    	
    	renderJSON(site);
    }

}
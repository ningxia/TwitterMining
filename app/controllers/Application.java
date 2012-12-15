package controllers;

import play.*;
import play.db.jpa.JPA;
import play.mvc.*;

import java.util.*;

import javax.persistence.Query;

import models.*;

public class Application extends Controller {

    public static void index() {
    	render();
    }
    
    public static void bounds(String zoomLevel, String swLat, String swLng, String neLat, String neLng) {
//    	System.out.println("Southwest latitude: " + swLat);
//    	System.out.println("Southwest longitude: " + swLng);
//    	System.out.println("Northeast latitude: " + neLat);
//    	System.out.println("Northeast longitude: " + neLng);
    	int level = Integer.parseInt(zoomLevel);
    	List<US> data = US.find("latitude >= ? and latitude <= ? and longitude >= ? and longitude <= ?", Float.parseFloat(swLat), Float.parseFloat(neLat), Float.parseFloat(swLng), Float.parseFloat(neLng)).fetch();
    	System.out.println("Inside bounds: " + data.size());
    	System.out.println("Zoom level: " + level);
    	render(data);
    }
    
    public static void sites() {
    	List<Site> siteList = Site.findAll();
    	int obama = 0;
    	int romney = 0;
    	for(int i = 0; i < siteList.size(); i ++) {
    		obama += siteList.get(i).obama;
    		romney += siteList.get(i).romney;
    	}
    	String obamaStat = obama * 100 / (obama + romney) + "%";
    	String romneyStat = romney * 100 / (obama + romney) + "%";
    	Site site = new Site("", "US", obama, romney, null, null, obamaStat, romneyStat);
    	renderJSON(site);
    }

}
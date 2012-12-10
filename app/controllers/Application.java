package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
    	List<Object> data = US.find("state is ?", "OH").fetch();
        render(data);
    }
    
    public static void listSites() {
    	List<US> sites = US.find("state is ?", "OH").fetch();
    	renderJSON(sites);
    }
    
    public static void postBounds(String swLat, String swLng, String neLat, String neLng) {
    	System.out.println("Southwest latitude: " + swLat);
    	System.out.println("Southwest longitude: " + swLng);
    	System.out.println("Northeast latitude: " + neLat);
    	System.out.println("Northeast longitude: " + neLng);
    	List<US> data = US.find("latitude >= ? and latitude <= ? and longitude >= ? and longitude <= ?", Float.parseFloat(swLat), Float.parseFloat(neLat), Float.parseFloat(swLng), Float.parseFloat(neLng)).fetch();
    	render(data);
    }

}
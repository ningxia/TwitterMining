package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {
	
	public static Float getFloat(String str) {
		return Float.parseFloat(str);
	}
	
	public static List<Object> originalData() {
//		Southwest latitude: 21.750686258648518
//		Southwest longitude: -132.57509062500003
//		Northeast latitude: 55.06035258768921
//		Northeast longitude: -64.89930937500003
		List<Object> list = US.find("latitude >= ? and latitude <= ? and longitude >= ? and longitude <= ?", getFloat("21.750686258648518"), getFloat("55.06035258768921"), getFloat("-132.57509062500003"), getFloat("-64.89930937500003")).fetch();
		return list;
		
	}

    public static void index() {
    	List<Object> data = US.find("state is ?", "OH").fetch();
        render(data);
    }
    
    public static void listSites() {
    	List<US> sites = US.find("state is ?", "OH").fetch();
    	renderJSON(sites);
    }
    
    public static void receiveBounds(String swLat, String swLng, String neLat, String neLng) {
    	
    }

}
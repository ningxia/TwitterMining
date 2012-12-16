package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Site extends Model {
	
	public String city;
	
	public String state;
	
	public int obama;
	
	public int romney;
	
	public Float latitude;
	
	public Float longitude;
	
	public String obamaStat;
	
	public String romneyStat;
	
	public Site(String city, String state, Float latitude, Float longitude, int obama, int romney) {
		this.city = city;
		this.state = state;
		this.latitude = latitude;
		this.longitude = longitude;
		this.obama = obama;
		this.romney = romney;
		if(obama != 0 && romney != 0) {
			this.obamaStat = obama * 100 / (obama + romney) + "%";
			this.romneyStat = romney * 100 / (obama + romney) + "%";
		}
		else {
			this.obamaStat = obama + "";
			this.romneyStat = romney + "";
		}
	}
	
	public String toString() {
		return city + ", " + state;
	}
    
}

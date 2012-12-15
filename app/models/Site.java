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
	
	public Site(String city, String state, int obama, int romney, Float latitude, Float longitude, String obamaStat, String romneyStat) {
		this.city = city;
		this.state = state;
		this.latitude = latitude;
		this.longitude = longitude;
		this.obamaStat = obamaStat;
		this.romneyStat = romneyStat;
	}
	
	public String toString() {
		return city + ", " + state;
	}
    
}

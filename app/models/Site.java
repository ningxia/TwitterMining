package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Site extends Model {
	
	public String city;
	
	public String state;
	
	public Float latitude;
	
	public Float longitude;
	
	public int obama;
	
	public int romney;
    
}

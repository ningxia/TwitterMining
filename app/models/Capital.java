package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Capital extends Model {
    
	public String city;
	
	public String state;
	
	public String fullState;
	
	public Float latitude;
	
	public Float longitude;
	
}

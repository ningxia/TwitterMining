package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Classification extends Model {
	
	@Id
    @Required
    public Long tweetId;
	
	@Required
	public int polarity;
	
}

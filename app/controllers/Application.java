package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
    	List<Object> data = US.find("city is ? and state is ?", "Cleveland", "OH").fetch();
        render(data);
    }

}
package com.mcbath.rebecca.moviecontrollerjni.Model;

/**
 * Created by Rebecca McBath
 * on 1/17/19.
 */

public class Movie extends JNIObject {

    public Movie(long ptr) {
    	super(ptr);
    }

		public native String getName();
		public native int getLastUpdate();

}

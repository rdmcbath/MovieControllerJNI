package com.mcbath.rebecca.moviecontrollerjni.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rebecca McBath
 * on 1/17/19.
 */
public class MovieDetail extends JNIObject {

	private List<Actor> mActorList;

	public MovieDetail(long ptr) {
		super(ptr);
	}

	public List<Actor> getActorsList(){
		if(mActorList == null)
			loadActors();

		return mActorList;
	}

	private void loadActors(){
		List<Actor> temp = new ArrayList<Actor>();
		long[] jniActors = getActors();

		if(jniActors != null){
			for(long ptr: jniActors){
				temp.add(new Actor(ptr));
			}
		}

		mActorList = temp;
	}

	public native String getName();
	public native float getScore();
	private native long[] getActors();
	public native String getDescription();

}

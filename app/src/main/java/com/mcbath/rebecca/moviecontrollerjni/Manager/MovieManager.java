package com.mcbath.rebecca.moviecontrollerjni.manager;

import com.mcbath.rebecca.moviecontrollerjni.model.JNIObject;
import com.mcbath.rebecca.moviecontrollerjni.model.Movie;
import com.mcbath.rebecca.moviecontrollerjni.model.MovieDetail;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rebecca McBath
 * on 1/17/19.
 */

public class MovieManager extends JNIObject {

	private static MovieManager sInstance = null;

	public static MovieManager getInstance(){
		if(sInstance == null){
			sInstance = new MovieManager(jniCreateManager());
		}
		return sInstance;
	}

	public MovieManager(long ptr) {
		super(ptr);
	}

	/**
	 * Request a list of movies from the native MovieController
	 * @return a list of Java movie objects {@link Movie}
	 */
	public List<Movie> getMovies(){
		List<Movie> result = new ArrayList<>();
		long[] jniMovies = jniGetMovieList();

		if(jniMovies != null){
			for(long ptr: jniMovies){
				result.add(new Movie(ptr));
			}
		}
		return result;
	}

	/**
	 * Request the details of the given movie from the native MovieController
	 * @param movieName, the name of the movie to get details of
	 * @return the movie details {@link MovieDetail}
	 */
	public MovieDetail getMovieDetails(String movieName){
		if(movieName == null)
			return null;

		long ptr = jniGetMovieDetails(movieName);
		if(ptr == 0)
			return null;

		return new MovieDetail(ptr);
	}

	private static native long jniCreateManager();
	private native long[] jniGetMovieList();
	private native long jniGetMovieDetails(String movie);

	/**
	 * Deallocate the native MovieController
	 */
	public native void delete();
}

package com.mcbath.rebecca.moviecontrollerjni.ui;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.mcbath.rebecca.moviecontrollerjni.adapter.MovieAdapter;
import com.mcbath.rebecca.moviecontrollerjni.manager.MovieManager;
import com.mcbath.rebecca.moviecontrollerjni.model.Movie;
import com.mcbath.rebecca.moviecontrollerjni.R;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
	public static final String TAG = "MainActivity";

	private Movie movie;
	private List<Movie> movieList;
	private MovieAdapter mAdapter;

	// load the 'native-lib' library
	static {
		System.loadLibrary("native-lib");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Test JNI String:  " + stringFromJNI());

		setContentView(R.layout.activity_main);

		Objects.requireNonNull(getSupportActionBar()).setTitle("Movie Controller JNI");

		LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		layoutManager.scrollToPosition(0);

		RecyclerView mRecyclerView = findViewById(R.id.main_recyclerview);
		DividerItemDecoration itemDecorator = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.HORIZONTAL);
		itemDecorator.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.recycler_divider)));

		mRecyclerView.setLayoutManager(layoutManager);
		mAdapter = new MovieAdapter(new MovieAdapter.OnItemClickListener() {

			@Override
			public void onItemClick(View v, int position) {

				Movie movie = mAdapter.getMovie(position);
				if(movie == null) {
					return;
				}

				String movieName = movie.getName();
				Intent intent = new Intent(MainActivity.this, DetailActivity.class);
				intent.putExtra("movie_name", movieName);
				Log.d(TAG, "movie name = " + movieName);
				startActivity(intent);
			}
		});

		mRecyclerView.setAdapter(mAdapter);

		loadMovieList();
	}

	private void loadMovieList(){
		MovieManager manager = MovieManager.getInstance();
		mAdapter.setMovieList(manager.getMovies());
	}

	/**
	 * A native method that is implemented by the 'native-lib' native library,
	 * which is packaged with this application.
	 */
	public native String stringFromJNI();
}





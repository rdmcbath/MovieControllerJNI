package com.mcbath.rebecca.moviecontrollerjni.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mcbath.rebecca.moviecontrollerjni.Adapter.DetailAdapter;
import com.mcbath.rebecca.moviecontrollerjni.Manager.MovieManager;
import com.mcbath.rebecca.moviecontrollerjni.Model.MovieDetail;
import com.mcbath.rebecca.moviecontrollerjni.R;

import java.util.Objects;

import static com.mcbath.rebecca.moviecontrollerjni.R.layout.activity_detail;

/**
 * Created by Rebecca McBath
 * on 1/18/19.
 */

public class DetailActivity extends AppCompatActivity {
	private static final String TAG ="DetailActivity";

	private static final String KEY_MOVIE = "movie_name";
	private String mMovieName = "movie_name";

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_detail);

		Objects.requireNonNull(getSupportActionBar()).setTitle("Movie Details");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		Intent intent = getIntent();

		if (savedInstanceState != null) {
			mMovieName = savedInstanceState.getString(KEY_MOVIE);
		}

		if (intent != null) {
			mMovieName = getIntent().getStringExtra(KEY_MOVIE);
			Log.d(TAG, "Movie selected is " + mMovieName);
		}

		MovieManager manager = MovieManager.getInstance();
		MovieDetail details = manager.getMovieDetails(mMovieName);

		final DetailAdapter mAdapter = new DetailAdapter();
		mAdapter.setMovieDetail(details);

		RecyclerView recyclerView = findViewById(R.id.actor_recyclerview);

		GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
		layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				switch (mAdapter.getItemViewType(position)) {
					case DetailAdapter.TYPE_DESCRIPTION:
						return 3;

					case DetailAdapter.TYPE_ACTOR:
						return 1;

					default:
						return 1;
				}
			}
		});

		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(mAdapter);
	}

	@Override
	public void onSaveInstanceState(@NonNull Bundle outState) {
		outState.putString(KEY_MOVIE, mMovieName);
		super.onSaveInstanceState(outState);
	}
}

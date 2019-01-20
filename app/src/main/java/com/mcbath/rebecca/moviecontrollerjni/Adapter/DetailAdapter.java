package com.mcbath.rebecca.moviecontrollerjni.adapter;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mcbath.rebecca.moviecontrollerjni.model.Actor;
import com.mcbath.rebecca.moviecontrollerjni.model.MovieDetail;
import com.mcbath.rebecca.moviecontrollerjni.R;

import java.util.List;

/**
 * Created by Rebecca McBath
 * on 1/17/19.
 */

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private static final String TAG = "DetailAdapter";

	public static final int TYPE_DESCRIPTION = 0;
	public static final int TYPE_ACTOR = 1;
	private List<Actor> mActorList;
	private MovieDetail mMovieDetail;

	public static class ViewHolderMovieDetail extends RecyclerView.ViewHolder {
		TextView mMovieName;
		TextView mScore;
		TextView mDescription;
		TextView mActorLabel;

		ViewHolderMovieDetail(@NonNull View itemView) {
			super(itemView);
			mMovieName = itemView.findViewById(R.id.movie_title);
			mDescription = itemView.findViewById(R.id.movie_description);
			mScore = itemView.findViewById(R.id.movie_score);
			mActorLabel = itemView.findViewById(R.id.actors_label);
		}
	}

	public static class ViewHolderActorDetail extends RecyclerView.ViewHolder {
		TextView mActorName;
		ImageView mActorImage;

		ViewHolderActorDetail(@NonNull View itemView) {
			super(itemView);
			mActorImage = itemView.findViewById(R.id.actor_image);
			mActorName = itemView.findViewById(R.id.actor_name);
		}
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		if (viewType == TYPE_ACTOR) {
			View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item_actor, parent, false);
			return new ViewHolderActorDetail(v);

		} else if (viewType == TYPE_DESCRIPTION) {
			View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item_description, parent, false);
			return new ViewHolderMovieDetail(v);
		}

		throw new RuntimeException(Resources.getSystem().getString(R.string.detail_adapter_viewtype_error) + viewType);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

		if (holder instanceof ViewHolderActorDetail) {
			ViewHolderActorDetail holderActorDetail = (ViewHolderActorDetail) holder;

			Actor actor = getActor(position);

			if (getActor(position) == null) {
				holderActorDetail.mActorName.setText("");
				holderActorDetail.mActorImage.setImageResource(R.drawable.ic_face_black_24dp);
			}

			String actorName = null;
			if (actor != null) {
				actorName = actor.getName() + "\n age " + actor.getAge();
			}
			holderActorDetail.mActorName.setText(actorName);

				String actorImageUrl = actor != null ? actor.getImageUrl() : null;
				Log.d(TAG, "Actor image url = " + actorImageUrl);

				RequestOptions options = new RequestOptions()
						.placeholder(R.drawable.ic_face_black_24dp)
						.error(R.drawable.ic_face_black_24dp);
				Glide.with(holderActorDetail.itemView.getContext()).load(actorImageUrl).apply(options).into(holderActorDetail.mActorImage);


		} else if (holder instanceof ViewHolderMovieDetail) {
			ViewHolderMovieDetail holderMovieDetail = (ViewHolderMovieDetail) holder;

			if (mMovieDetail == null) {
				holderMovieDetail.mMovieName.setText("");
				holderMovieDetail.mDescription.setText("");
				holderMovieDetail.mScore.setText("");
				return;
			}

				holderMovieDetail.mMovieName.setText(mMovieDetail.getName());
				holderMovieDetail.mDescription.setText(mMovieDetail.getDescription());
				holderMovieDetail.mScore.setText(String.format("Score: %s", String.valueOf(mMovieDetail.getScore())));
				holderMovieDetail.mActorLabel.setText(R.string.actors);
			}
		}

	@Override
	public int getItemViewType(int position) {
		if (position == 0)
		return TYPE_DESCRIPTION;
		return TYPE_ACTOR;
	}

	private Actor getActor(int position) {
		if (mActorList == null)
			return null;

		return mActorList.get(position - 1);
	}

	@Override
	public int getItemCount() {
		if (mActorList == null)
			return 0;

		Log.d(TAG, "getItemCount() = " + mActorList.size());
		return mActorList.size() + 1;
	}

	public void setMovieDetail(MovieDetail details) {
		this.mMovieDetail = details;
		if (details == null) {
			this.mActorList = null;
		} else {
			this.mActorList = details.getActorsList();
		}
		notifyDataSetChanged();
	}
}


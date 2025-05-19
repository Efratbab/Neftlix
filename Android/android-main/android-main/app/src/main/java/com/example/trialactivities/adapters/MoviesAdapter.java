package com.example.trialactivities.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trialactivities.R;
import com.example.trialactivities.entities.Category;
import com.example.trialactivities.entities.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private final TextView movieName;
        private final ImageView imgThumbnail;
        private final CardView movieCard;

        public MovieViewHolder(@NonNull View itemView){
            super(itemView);
            movieName = itemView.findViewById(R.id.movieName);
            imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
            movieCard = itemView.findViewById(R.id.movieCard);
        }
    }
    private final LayoutInflater mInflater;
    private List<Movie> movies;

    public MoviesAdapter (Context context){ mInflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        if(movies != null){
            final Movie current = movies.get(position);
            holder.movieName.setText(current.getTitle());
            holder.imgThumbnail.setImageBitmap(BitmapFactory.decodeFile(current.getThumbnail()));



        }

    }

    @Override
    public int getItemCount() {
        if(movies != null){
            return movies.size();
        }else{
            return 0;
        }
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}


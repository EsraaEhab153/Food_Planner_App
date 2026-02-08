package com.example.foodplannerapp.ui.main.mealDetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Meal;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class MealDetailsFragment extends Fragment implements MealDetailsContract.View {

    private MealDetailsContract.Presenter presenter;

    private TextView tvMealName, tvCategory, tvArea, tvInstructions;
    private YouTubePlayerView youtubePlayerView;
    private ImageView imgMeal;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_details, container, false);

        tvMealName = view.findViewById(R.id.tvMealName);
        tvCategory = view.findViewById(R.id.tvMealCategory);
        tvArea = view.findViewById(R.id.tvMealArea);
        tvInstructions = view.findViewById(R.id.tvInstructions);
        imgMeal = view.findViewById(R.id.imgMeal);

        youtubePlayerView = view.findViewById(R.id.youtubePlayerView);
        getLifecycle().addObserver(youtubePlayerView);

        presenter = new MealDetailsPresenter(this, new MealDetailsRepository());

        // Load meal details
        String mealId = getArguments().getString("meal_id");
        presenter.loadMealDetails(mealId);

        return view;
    }

    @Override
    public void showMealDetails(Meal meal) {
        tvMealName.setText(meal.getStrMeal());
        tvCategory.setText(meal.getStrCategory());
        tvArea.setText(meal.getStrArea());
        tvInstructions.setText(meal.getStrInstructions());

        Glide.with(requireContext())
                .load(meal.getStrMealThumb())
                .into(imgMeal);


        // YouTube video
        if (meal.getStrYoutube() != null && !meal.getStrYoutube().isEmpty()) {
            String videoId = extractYoutubeId(meal.getStrYoutube());
            youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    youTubePlayer.loadVideo(videoId, 0);
                }
            });
        } else {
            youtubePlayerView.setVisibility(View.GONE);
        }
    }

//    private String extractYoutubeId(String url) {
//        Uri uri = Uri.parse("https://www.youtube.com/watch?v=aqz-KE-bpKQ");
//        return uri.getQueryParameter("v");
//    }
private String extractYoutubeId(String url) {
    String videoId = null;
    if (url != null && url.trim().length() > 0) {
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";
        java.util.regex.Pattern compiledPattern = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher matcher = compiledPattern.matcher(url);
        if (matcher.find()) {
            videoId = matcher.group();
        }
    }
    return videoId;
}

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
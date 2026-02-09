package com.example.foodplannerapp.ui.main.mealDetails;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.data.weeklyplan.DataSource.local.WeeklyMealEntity;
import com.example.foodplannerapp.model.IngredientItem;
import com.example.foodplannerapp.model.Meal;
import com.example.foodplannerapp.ui.main.weeklyPlan.WeeklyPlanRepository;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsFragment extends Fragment implements MealDetailsContract.View {

    private MealDetailsContract.Presenter presenter;
    private YouTubePlayer activePlayer;
    private String currentVideoId = "";

    private TextView tvMealName, tvCategory, tvArea, tvInstructions;
    private YouTubePlayerView youtubePlayerView;
    private ImageView imgMeal;
    private RecyclerView rvIngredients;
    private IngredientsAdapter ingredientsAdapter;
    private Meal currentMeal;
    private WeeklyPlanRepository weeklyRepository;



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
        getViewLifecycleOwner().getLifecycle().addObserver(youtubePlayerView);


        youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                activePlayer = youTubePlayer;
                if (!currentVideoId.isEmpty()) {
                    activePlayer.cueVideo(currentVideoId, 0);
                }
            }

            @Override
            public void onError(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerError error) {
                Log.e("YouTubeError", "Error type: " + error.name());

                Toast.makeText(getContext(), "Error: " + error.name(), Toast.LENGTH_LONG).show();
            }});

        rvIngredients = view.findViewById(R.id.rvIngredients);
        rvIngredients.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        presenter = new MealDetailsPresenter(this, new MealDetailsRepository());
        weeklyRepository = new WeeklyPlanRepository(requireContext());

        // Load meal details
        String mealId = getArguments().getString("meal_id");
        presenter.loadMealDetails(mealId);
        ImageButton btnCalendar = view.findViewById(R.id.btn_add_to_calendar);
        btnCalendar.setOnClickListener(v -> {
            showDatePickerForMeal(currentMeal);
        });
        return view;
    }

    @Override
    public void showMealDetails(Meal meal) {
        this.currentMeal = meal;
        tvMealName.setText(meal.getStrMeal());
        tvCategory.setText(meal.getStrCategory());
        tvArea.setText(meal.getStrArea());
        tvInstructions.setText(meal.getStrInstructions());

        Glide.with(requireContext())
                .load(meal.getStrMealThumb())
                .into(imgMeal);

        List<IngredientItem> ingredientsList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String ingredient = meal.getIngredient(i);
            String measure = meal.getMeasure(i);
            if (ingredient != null && !ingredient.isEmpty()) {
                ingredientsList.add(new IngredientItem(
                        ingredient.trim(),
                        measure != null ? measure.trim() : "",
                        "https://www.themealdb.com/images/ingredients/"
                                + ingredient.trim() + "-Small.png"
                ));
            }
        }

        ingredientsAdapter = new IngredientsAdapter(ingredientsList);
        rvIngredients.setAdapter(ingredientsAdapter);


        // YouTube video
        if (meal.getStrYoutube() != null && !meal.getStrYoutube().isEmpty()) {
            String videoId = extractYoutubeId(meal.getStrYoutube());
            Log.i("youtube", "youtube url: " + meal.getStrYoutube());
            Log.i("youtube", "video id: "+ videoId);
            if(!videoId.isEmpty()){
                youtubePlayerView.setVisibility(View.VISIBLE);
                youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        youTubePlayer.cueVideo(videoId, 0);
                        Log.i("youtube", "onReady: cue "+videoId);
                    }
                } );
            }else{
                youtubePlayerView.setVisibility(View.GONE);
            }

        } else {
            youtubePlayerView.setVisibility(View.GONE);
        }

    }

//    private String extractYoutubeId(String url) {
//        Uri uri = Uri.parse("https://www.youtube.com/watch?v=aqz-KE-bpKQ");
//        return uri.getQueryParameter("v");
//    }
//private String extractYoutubeId(String url) {
//    String videoId = null;
//    if (url != null && url.trim().length() > 0) {
//        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";
//        java.util.regex.Pattern compiledPattern = java.util.regex.Pattern.compile(pattern);
//        java.util.regex.Matcher matcher = compiledPattern.matcher(url);
//        if (matcher.find()) {
//            videoId = matcher.group();
//        }
//    }
//    return videoId;
//}
private String extractYoutubeId(String youtubeUrl) {
    if (youtubeUrl == null || youtubeUrl.isEmpty()) {
        return "";
    }

    Pattern pattern = Pattern.compile(
            "(?:youtube\\.com/(?:watch\\?v=|embed/)|youtu\\.be/)([a-zA-Z0-9_-]{11})",
            Pattern.CASE_INSENSITIVE
    );

    Matcher matcher = pattern.matcher(youtubeUrl);
    if (matcher.find()) {
        return matcher.group(1);
    }

    if (youtubeUrl.contains("v=")) {
        String[] parts = youtubeUrl.split("v=");
        if (parts.length > 1) {
            String videoId = parts[1].split("&")[0];
            return videoId;
        }
    }

    return "";
}

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSavedSuccessfully() {
        Toast.makeText(getContext(), "Meal Added Successfully", Toast.LENGTH_SHORT).show();
    }

    private void showDatePickerForMeal(Meal meal) {
        if (currentMeal == null) {
            Toast.makeText(getContext(), "Meal not loaded yet", Toast.LENGTH_SHORT).show();
            return;
        }
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                requireContext(),
                R.style.MyDatePickerDialogTheme,
                (view, y, m, d) -> {
                    Calendar selected = Calendar.getInstance();
                    selected.set(y, m, d, 0, 0, 0);
                    selected.set(Calendar.MILLISECOND, 0);
                    long selectedDayMillis = selected.getTimeInMillis();

                    WeeklyMealEntity entity = new WeeklyMealEntity(
                            meal.getIdMeal(),
                            meal.getStrMeal(),
                            meal.getStrMealThumb(),
                            selectedDayMillis
                    );

                    insertMealToWeeklyPlan(entity);
                },
                year, month, day
        );
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        dialog.show();
    }

    private void insertMealToWeeklyPlan(WeeklyMealEntity entity) {
        weeklyRepository.insertMeal(entity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> Toast.makeText(requireContext(), "Saved to Weekly Plan", Toast.LENGTH_SHORT).show(),
                        throwable -> Toast.makeText(requireContext(), "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }


}
package com.example.foodplannerapp.ui.main.favorite;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.foodplannerapp.R;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodplannerapp.ui.main.favorite.FavoriteRepository;
import com.example.foodplannerapp.data.favorite.DataSource.FavoriteMealEntity;
import com.example.foodplannerapp.ui.main.favorite.adapter.FavoriteMealAdapter;

import java.util.List;

public class FavoriteMealFragment extends Fragment implements FavoriteContract.View {

    private RecyclerView rvFavorites;
    private TextView tvEmpty;
    private FavoriteMealAdapter adapter;
    private FavoriteContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        rvFavorites = view.findViewById(R.id.rvFavorites);
        tvEmpty = view.findViewById(R.id.tvEmpty);

        rvFavorites.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new FavoriteMealAdapter(meal -> {
            presenter.removeFromFavorite(meal);
        });
        rvFavorites.setAdapter(adapter);

        presenter = new FavoriteMealPresenter(this, new FavoriteRepository(requireContext()));
        presenter.getAllFavorites();

        return view;
    }

    @Override
    public void showFavorites(List<FavoriteMealEntity> meals) {
        if (meals.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvFavorites.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvFavorites.setVisibility(View.VISIBLE);
            adapter.updateData(meals);
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFavoriteAdded() {
        presenter.getAllFavorites();
    }

    @Override
    public void onFavoriteRemoved() {
        presenter.getAllFavorites();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.clear();
    }
}

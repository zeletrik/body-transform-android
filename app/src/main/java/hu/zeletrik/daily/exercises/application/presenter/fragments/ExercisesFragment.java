package hu.zeletrik.daily.exercises.application.presenter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hu.zeletrik.daily.exercises.R;
import hu.zeletrik.daily.exercises.application.adapter.ExercisesAdapter;
import hu.zeletrik.daily.exercises.application.domain.Exercise;
import hu.zeletrik.daily.exercises.application.presenter.ExerciseDetailActivity;
import hu.zeletrik.daily.exercises.application.service.WorkoutService;
import hu.zeletrik.daily.exercises.application.service.impl.WorkoutServiceImpl;

public class ExercisesFragment extends Fragment implements ExercisesAdapter.ItemClickListener {

    private static final String WORKOUT = "workout";
    private WorkoutService workoutService;
    private ExercisesAdapter adapter;
    private RecyclerView recyclerView;

    public ExercisesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        workoutService = new WorkoutServiceImpl(getContext());
        View rootView = inflater.inflate(R.layout.fragment_exercises, container, false);
        List<Exercise> exerciseList = workoutService.getBaseExercises();

        recyclerView = rootView.findViewById(R.id.exerciseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ExercisesAdapter(getContext(), exerciseList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked " + adapter.getItem(position).getWorkout().getName() + " on row number " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), ExerciseDetailActivity.class);
        intent.putExtra(WORKOUT, adapter.getItem(position).getWorkout());
        startActivity(intent);

    }
}

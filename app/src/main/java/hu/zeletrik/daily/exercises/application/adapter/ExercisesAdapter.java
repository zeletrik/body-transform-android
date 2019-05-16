package hu.zeletrik.daily.exercises.application.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hu.zeletrik.daily.exercises.R;
import hu.zeletrik.daily.exercises.application.domain.Exercise;


public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder> {

    private List<Exercise> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    public ExercisesAdapter(Context context, List<Exercise> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.exercises_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String durationPlaceholder = context.getResources().getString(R.string.duration);
        Exercise exercise = mData.get(position);
        holder.mTitle.setText(exercise.getWorkout().getName());
        holder.mDurationBasic.setText(String.format(durationPlaceholder, exercise.getWorkout().getDuration()/1000));
        holder.mDurationCurrent.setText(String.format(durationPlaceholder, exercise.getWorkout().getDuration()/1000));
        holder.mImage.setImageDrawable(exercise.getIcon());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public Exercise getItem(int id) {
        return mData.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTitle;
        TextView mDurationCurrent;
        TextView mDurationBasic;
        ImageView mImage;

        ViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.exercisesRowTitle);
            mDurationCurrent = itemView.findViewById(R.id.exercisesRowDurationCurrent);
            mDurationBasic = itemView.findViewById(R.id.exercisesRowDurationBase);
            mImage = itemView.findViewById(R.id.exercisesRowPicture);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
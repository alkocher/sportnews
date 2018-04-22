package com.example.aleksejkocergin.sportnews.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aleksejkocergin.sportnews.R;
import com.example.aleksejkocergin.sportnews.model.SportNewsData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private List<SportNewsData> sportNewsDataList;
    private OnItemClickListener itemClickListener;

    public FeedAdapter(List<SportNewsData> sportNewsDataList) {
        this.sportNewsDataList = sportNewsDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View itemView = layoutInflater.inflate(R.layout.card_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SportNewsData sportNewsData = this.sportNewsDataList.get(position);
        holder.textTitle.setText(sportNewsData.getTitle());
        holder.textCoefficient.setText(sportNewsData.getCoefficient());
        holder.textTime.setText(sportNewsData.getTime());
        holder.textPlace.setText(sportNewsData.getPlace());
        holder.textPreview.setText(sportNewsData.getPreview());
        holder.article = sportNewsData.getArticle();
    }

    @Override
    public int getItemCount() {
        return sportNewsDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.title_text)
        TextView textTitle;
        @BindView(R.id.coefficient_text)
        TextView textCoefficient;
        @BindView(R.id.time_text)
        TextView textTime;
        @BindView(R.id.place_text)
        TextView textPlace;
        @BindView(R.id.preview_text)
        TextView textPreview;
        private String article;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getAdapterPosition(), article);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, String article);
    }

    public void setOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}

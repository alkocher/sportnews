package com.example.aleksejkocergin.sportnews.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aleksejkocergin.sportnews.R;
import com.example.aleksejkocergin.sportnews.model.ArticleDetailsData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {

    private List<ArticleDetailsData> articleDetailsList;

    public ArticleAdapter (List<ArticleDetailsData> articleDetailsList){
        this.articleDetailsList = articleDetailsList;
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View itemView = layoutInflater.inflate(R.layout.list_item_details, parent, false);
        return new ArticleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
        final ArticleDetailsData articleDetailsData = this.articleDetailsList.get(position);
        holder.textArticle.setText(articleDetailsData.getText());
        holder.textHeader.setText(articleDetailsData.getHeader());
    }

    @Override
    public int getItemCount() {
        return articleDetailsList.size();
    }

    class ArticleHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.header_text)
        TextView textHeader;
        @BindView(R.id.article_text)
        TextView textArticle;

         ArticleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

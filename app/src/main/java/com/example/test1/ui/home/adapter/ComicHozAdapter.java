package com.example.test1.ui.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.R;
import com.example.test1.data.model.Comic;
import com.example.test1.databinding.ItemProductLayoutHozBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ComicHozAdapter extends RecyclerView.Adapter<ComicHozAdapter.ComicViewHolder> {

    private Context context;
    private List<Comic> mList=new ArrayList<>();

    private static ComicAdapter.OnItemClickListener onClickListener;

    public ComicHozAdapter(Context context, ComicAdapter.OnItemClickListener onClickListener) {
        this.context = context;
        this.onClickListener=onClickListener;
    }

    public static class ComicViewHolder extends RecyclerView.ViewHolder {
        ItemProductLayoutHozBinding binding;

        public ComicViewHolder(ItemProductLayoutHozBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Comic comic) {
            binding.layoutItemProduct.setOnClickListener(v -> {
                if (onClickListener != null) {
                    onClickListener.onItemClick(comic);
                }
            });
            binding.nameProduct.setText(comic.getName());
            binding.author.setText(comic.getAuthor());

            Picasso.get()
                    .load(comic.getImage())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.loading)
                    .into(binding.image);

            Log.d("img comic", "bind: " + comic.getImage());
            binding.time.setText(comic.getCreatedAt());
        }
    }

    public void setData(List<Comic> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductLayoutHozBinding binding = ItemProductLayoutHozBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ComicViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicViewHolder holder, int position) {
        Comic comic = mList.get(position);
        if (comic != null) {
            holder.bind(comic);
        }
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }
}


package com.example.test1.ui.home.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.R;
import com.example.test1.data.model.Comment;

import java.text.SimpleDateFormat;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private Context context;
    private List<Comment> comments;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    public void setData(List<Comment> listTerm){
        comments=listTerm;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimpleDateFormat format= new SimpleDateFormat("hh:mm dd-MM-yyyy");
        Comment comment = comments.get(position);
        holder.textUserId.setText(comment.getIdUser());
        holder.textContent.setText(comment.getContent());
        holder.txt_time.setText(format.format(comment.getCreatedAt()));
        holder.layoutItemProduct.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textUserId;
        TextView textContent,txt_time;
        LinearLayout layoutItemProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItemProduct=itemView.findViewById(R.id.layoutItemProduct);
            textUserId = itemView.findViewById(R.id.textUserId);
            textContent = itemView.findViewById(R.id.textContent);
            txt_time=itemView.findViewById(R.id.time);

        }
    }
}


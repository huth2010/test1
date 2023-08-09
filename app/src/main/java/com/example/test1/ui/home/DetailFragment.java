package com.example.test1.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.test1.R;
import com.example.test1.data.model.Comic;
import com.example.test1.data.model.Comment;
import com.example.test1.data.network.ApiService;
import com.example.test1.databinding.FragmentDetailBinding;
import com.example.test1.ui.home.adapter.CommentAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFragment extends Fragment {
    private FragmentDetailBinding binding;
    Comic comic;
    List<Comment> commentList;
    Comment comment;
    CommentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //lấy data được truyền từ màn(fragment) trước
        assert getArguments() != null;
        comic = DetailFragmentArgs.fromBundle(getArguments()).getComicArg();
        initUi();
    }

    private void initUi() {
        //comic
        Picasso.get().load(comic.getImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(binding.imageView);
        binding.productNameTextView.setText(comic.getName());
        binding.authorTextView.setText(comic.getAuthor());
        binding.desc.setText(comic.getDescription());
        binding.btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFragmentDirections.ActionDetailFragmentToReadFragment action=DetailFragmentDirections.actionDetailFragmentToReadFragment(comic);
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        });

        //comment
        commentList=new ArrayList<>();
        adapter=new CommentAdapter(requireContext(),commentList);
        binding.listComment.setAdapter(adapter);
        callAPIGetListComment();

    }

    private void callAPIGetListComment() {
        ApiService apiService=ApiService.Factory.create();
        apiService.getAllComment(comic.getId()).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                commentList=response.body();
                adapter.setData(commentList);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(requireContext(), "Lỗi, không thể tải bình luận", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
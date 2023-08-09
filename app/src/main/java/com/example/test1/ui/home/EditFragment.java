package com.example.test1.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.test1.R;
import com.example.test1.data.model.Comic;
import com.example.test1.data.network.ApiService;
import com.example.test1.databinding.FragmentEditBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditFragment extends BottomSheetDialogFragment {
    private FragmentEditBinding binding;
    Comic comic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        comic = DetailFragmentArgs.fromBundle(getArguments()).getComicArg();
        initUi();
        listentEvent();
    }

    private void listentEvent() {
        binding.btnDelete.setOnClickListener(v -> {
            callAPIDeleteComic();
        });
        binding.btnEdit.setOnClickListener(v -> {
            callAPIEditComic();
        });
    }

    private void callAPIEditComic() {
        String name= binding.tvName.getText().toString();
        String author= binding.tvAuthor.getText().toString();
        String desc= binding.tvDesc.getText().toString();
        String publication= binding.tvPublication.getText().toString();
        Comic tempComic=new Comic(comic.getCreatedAt(),desc,author,publication,name,comic.getImage(),comic.getId());
        ApiService apiService=ApiService.Factory.create();
        apiService.editComic(comic.getId(),tempComic).enqueue(new Callback<Comic>() {
            @Override
            public void onResponse(Call<Comic> call, Response<Comic> response) {
                HomeFragment.CallAPIGetAllComic();
                dismiss();
                Toast.makeText(requireContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Comic> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(requireContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callAPIDeleteComic() {
        ApiService apiService=ApiService.Factory.create();
        apiService.deleteComic(comic.getId()).enqueue(new Callback<Comic>() {
            @Override
            public void onResponse(Call<Comic> call, Response<Comic> response) {
                HomeFragment.CallAPIGetAllComic();
                Toast.makeText(requireContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                dismiss();
            }

            @Override
            public void onFailure(Call<Comic> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(requireContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUi() {
        Picasso.get().load(comic.getImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(binding.imageView);
        binding.tvName.setText(comic.getName());
        binding.tvAuthor.setText(comic.getAuthor());
        binding.tvDesc.setText(comic.getDescription());
        binding.tvPublication.setText(comic.getPublication());
    }
}
package com.example.test1.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.example.test1.R;
import com.example.test1.data.model.Comic;
import com.example.test1.databinding.FragmentReadBinding;
import com.example.test1.ui.home.adapter.ReadAdapter;


public class ReadFragment extends Fragment {
    private FragmentReadBinding binding;
    private ReadAdapter readAdapter;
    private List<String> imageUrls = new ArrayList<>();
    Comic comic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentReadBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        comic = DetailFragmentArgs.fromBundle(getArguments()).getComicArg();
        imageUrls=comic.getContent();
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        readAdapter = new ReadAdapter(requireContext(), imageUrls);
        binding.listContent.setAdapter(readAdapter);
    }
}


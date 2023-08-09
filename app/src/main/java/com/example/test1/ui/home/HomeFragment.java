package com.example.test1.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.test1.R;
import com.example.test1.data.model.Comic;
import com.example.test1.data.network.ApiService;
import com.example.test1.databinding.FragmentHomeBinding;
import com.example.test1.ui.MainActivity;
import com.example.test1.ui.home.adapter.ComicAdapter;
import com.example.test1.ui.home.adapter.ComicHozAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private static FragmentHomeBinding binding;
    private static List<Comic> mlist=new ArrayList<>();
    private static ComicHozAdapter hozAdapter;
    private static ComicAdapter verAdapter;

    private static Executor executor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hozAdapter = new ComicHozAdapter(requireContext(), new ComicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Comic item) {
                HomeFragmentDirections.ActionHomeFragmentToDetailFragment action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item);
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }

            @Override
            public void onItemClickMore(Comic item) {

            }
        });
        verAdapter = new ComicAdapter(requireContext(), new ComicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Comic item) {
                HomeFragmentDirections.ActionHomeFragmentToDetailFragment action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item);
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }

            @Override
            public void onItemClickMore(Comic item) {
                HomeFragmentDirections.ActionHomeFragmentToEditFragment action=HomeFragmentDirections.actionHomeFragmentToEditFragment(item);
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        });
        CallAPIGetAllComic();
        Log.d("TAG", "onCreated: "+Thread.currentThread().getName());
    }

    public static void CallAPIGetAllComic() {
        executor= Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "onResponse: "+Thread.currentThread().getName());
                ApiService apiService=ApiService.Factory.create();
                apiService.getAllComic().enqueue(new Callback<List<Comic>>() {
                    @Override
                    public void onResponse(Call<List<Comic>> call, Response<List<Comic>> response) {
                               mlist=response.body();
                               if (mlist != null) {
                                   hozAdapter.setData(mlist);
                                   verAdapter.setData(mlist);
                               }
                               binding.recyclerViewHoz.setAdapter(hozAdapter);
                               binding.recyclerViewVer.setAdapter(verAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Comic>> call, Throwable t) {
                        t.printStackTrace();
                        Log.d("TAG", "onFailure: ");
                    }
                });
            }
        });

    }
}
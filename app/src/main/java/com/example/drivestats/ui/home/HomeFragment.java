package com.example.drivestats.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.drivestats.MainActivity;
import com.example.drivestats.R;
import com.example.drivestats.databinding.FragmentHomeBinding;
import com.example.drivestats.ui.Map.MapActivity;

public class HomeFragment extends Fragment {

    private ViewFlipper viewFlipper;

    static int delay = 3000;
    private Handler handler;
    private FragmentHomeBinding binding;

    private Button map;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewFlipper = root.findViewById(R.id.viewFlipper);
        handler = new Handler();
        map = root.findViewById(R.id.navigationButton);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), MapActivity.class);
                startActivity(intent);
            }
        });

        startImageSlideshow();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void startImageSlideshow() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                viewFlipper.showNext();
                handler.postDelayed(this, delay);
            }
        };

        handler.postDelayed(runnable, delay);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
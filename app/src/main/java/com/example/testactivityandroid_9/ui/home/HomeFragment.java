package com.example.testactivityandroid_9.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.testactivityandroid_9.AvocadoActivity;
import com.example.testactivityandroid_9.DjoActivity;
import com.example.testactivityandroid_9.PpizzaActivity;
import com.example.testactivityandroid_9.R;
import com.example.testactivityandroid_9.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private static final String TAG = "MyActivity";

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    ImageButton podkrepizza_imagebutton;
    ImageButton djo_imagebutton;
    ImageButton avocado_imagebutton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        podkrepizza_imagebutton = (ImageButton)root.findViewById(R.id.podkrepizza_imagebutton);
        podkrepizza_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PpizzaActivity.class);
                startActivity(intent);
            }
        });

        djo_imagebutton = (ImageButton)root.findViewById(R.id.djo_imagebutton);
        djo_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DjoActivity.class);
                startActivity(intent);
            }
        });

        avocado_imagebutton = (ImageButton)root.findViewById(R.id.avocado_imagebutton);
        avocado_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AvocadoActivity.class);
                startActivity(intent);
            }
        });

/*        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
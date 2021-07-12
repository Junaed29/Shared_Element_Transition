package com.jpsoft.sharedelementtransition;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jpsoft.sharedelementtransition.databinding.FragmentTestBinding;

import org.jetbrains.annotations.NotNull;

public class TestFragment extends Fragment {

    FragmentTestBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //setSharedElementEnterTransition(new ChangeBounds());
        //setSharedElementReturnTransition(new ChangeBounds().setDuration(1000L));



       Transition transition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move);
        transition.setDuration(600);
        setSharedElementEnterTransition(transition);
        //setSharedElementReturnTransition(transition);

        binding = FragmentTestBinding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setSharedElementEnterTransition(new ChangeBounds());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            binding.textView2.setTransitionName(getArguments().getString("text"));
            binding.textView2.setText(getArguments().getString("text"));
        } else
            binding.textView.setText("Button");
    }
}
package com.jpsoft.sharedelementtransition.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jpsoft.sharedelementtransition.R;
import com.jpsoft.sharedelementtransition.databinding.MainFragmentBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    private MainFragmentBinding binding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        postponeEnterTransition();


       binding.list.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                startPostponedEnterTransition();
                return true;
            }
        });


        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                        .addSharedElement(binding.button, "btn")
                        .build();


                Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_testFragment, null, null, extras);

            }
        });

        setUpRecyclerView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel


    }

    private void setUpRecyclerView() {
        binding.list.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.list.setHasFixedSize(true);

        SimpleTextViewAdapter adapter = new SimpleTextViewAdapter(getList(), new SimpleTextViewAdapter.OnItemClick() {
            @Override
            public void onClick(View view, String item) {
                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                        .addSharedElement(view, item)
                        .build();

                view.setTransitionName(item);

                Bundle bundle = new Bundle();
                bundle.putString("text", item);

                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_testFragment, bundle, null, extras);
            }
        });

        binding.list.setAdapter(adapter);
    }


    ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");
        list.add("Item 4");
        list.add("Item 5");
        list.add("Item 6");

        return list;
    }

}
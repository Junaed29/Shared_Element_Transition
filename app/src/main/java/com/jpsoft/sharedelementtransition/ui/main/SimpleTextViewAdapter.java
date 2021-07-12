package com.jpsoft.sharedelementtransition.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jpsoft.sharedelementtransition.databinding.ListItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SimpleTextViewAdapter extends RecyclerView.Adapter<SimpleTextViewAdapter.AdapterViewHolder>{


    ArrayList<String> list ;
    OnItemClick listener;

    public SimpleTextViewAdapter(ArrayList<String> list,OnItemClick listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new AdapterViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterViewHolder holder, int position) {
        holder.binding.textView.setText(list.get(position));

        holder.binding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(holder.binding.textView, list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class AdapterViewHolder extends RecyclerView.ViewHolder {
        ListItemBinding binding;

        public AdapterViewHolder(@NonNull @NotNull ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public  interface  OnItemClick{
        void onClick(View view, String item);
    }
}

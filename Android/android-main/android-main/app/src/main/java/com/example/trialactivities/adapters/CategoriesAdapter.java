package com.example.trialactivities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trialactivities.R;
import com.example.trialactivities.entities.Category;




import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>{

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryName;
        private RecyclerView moviesInTheCategory;

        private CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            moviesInTheCategory = itemView.findViewById(R.id.moviesInTheCategory);
        }
    }
    private final LayoutInflater mInflater;
    private List<Category> categories;

    public CategoriesAdapter(Context context){ mInflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        if(categories != null){
            final Category current = categories.get(position);
            holder.categoryName.setText(current.getName());
            holder.moviesInTheCategory.setLayoutManager(new LinearLayoutManager(holder.moviesInTheCategory.getContext(),
                                                        LinearLayoutManager.HORIZONTAL, false));
        }

    }

    @Override
    public int getItemCount() {
        if(categories != null){
            return categories.size();
        }else{
            return 0;
        }
    }

    public void setCategories(List<Category> categories){
        this.categories = categories;
        notifyDataSetChanged();
    }
    public List<Category> getCategories(){
        return categories;
    }

}
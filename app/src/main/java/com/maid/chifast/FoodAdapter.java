package com.maid.chifast;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder> {

    List<FoodItem> data;
    int selectedItem = 0;

    public FoodAdapter(List<FoodItem> data){
        this.data = data;
    }
    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.food_holder, parent, false);

        return new FoodHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {
        holder.precio.setText(String.format("$%d",data.get(position).getPrice()));
        holder.imagen.setImageResource(data.get(position).getImage());
        holder.titulo.setText(data.get(position).getName());
        holder.puntaje.setRating(data.get(position).getRating());

        if (selectedItem == position){
            holder.cardView.animate().scaleX(1.1f);
            holder.cardView.animate().scaleY(1.1f);
            holder.titulo.setTextColor(Color.WHITE);
            holder.precio.setTextColor(Color.WHITE);
            holder.llBackground.setBackgroundResource(R.drawable.splash);
        }else {
            holder.cardView.animate().scaleX(1f);
            holder.cardView.animate().scaleY(1f);
            holder.titulo.setTextColor(Color.BLACK);
            holder.precio.setTextColor(Color.BLACK);
            holder.llBackground.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class FoodHolder extends RecyclerView.ViewHolder {
        RatingBar puntaje;
        ImageView imagen;
        TextView titulo;
        TextView precio;
        LinearLayout llBackground;
        CardView cardView;
        public FoodHolder(View holder) {
            super(holder);
            puntaje = holder.findViewById(R.id.puntaje);
            titulo = holder.findViewById(R.id.comida_titulo);
            imagen = holder.findViewById(R.id.comida_imagen);
            precio = holder.findViewById(R.id.txt_precio);
            cardView = holder.findViewById(R.id.food_background);
            llBackground = holder.findViewById(R.id.ll_background);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedItem = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }
}

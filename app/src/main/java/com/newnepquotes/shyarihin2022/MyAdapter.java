package com.newnepquotes.shyarihin2022;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.newnepquotes.shyarihin2022.Model.Model;
import com.newnepquotes.shyarihin2022.ctegories.Eight;
import com.newnepquotes.shyarihin2022.ctegories.Seven;
import com.newnepquotes.shyarihin2022.ctegories.Three;
import com.newnepquotes.shyarihin2022.ctegories.elevan;
import com.newnepquotes.shyarihin2022.ctegories.five;
import com.newnepquotes.shyarihin2022.ctegories.nine;
import com.newnepquotes.shyarihin2022.ctegories.one;
import com.newnepquotes.shyarihin2022.ctegories.six;
import com.newnepquotes.shyarihin2022.ctegories.ten;
import com.newnepquotes.shyarihin2022.ctegories.twelve;
import com.newnepquotes.shyarihin2022.ctegories.two;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    private Context c;
    private ArrayList<Model> models;

    public MyAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Convert xml to OBJ
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_item,null);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        //create bind view/data
        holder.img.setImageResource(models.get(position).getImage());
        holder.modelTitle.setText(models.get(position).getTitle());

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClick(View v, int pos) {
                switch (models.get(pos).getTitle()){
                    case "Motivational":{
                        Intent intent = new Intent(c, one.class);
                        c.startActivity(intent);
                    }
                    break;
                     case "Happy Quotes":{
                        Intent intent = new Intent(c, two.class);
                        c.startActivity(intent);
                    }
                    break;
                     case "Good Night":{
                        Intent intent = new Intent(c, Three.class);
                        c.startActivity(intent);
                    }
                    break;

                     case "Funny Quotes":{
                        Intent intent = new Intent(c, Three.class);
                        c.startActivity(intent);
                    }
                    break;

                     case "Romantic Quotes":{
                        Intent intent = new Intent(c, five.class);
                        c.startActivity(intent);
                    }
                    break;

                     case "Energetic Quotes":{
                        Intent intent = new Intent(c, six.class);
                        c.startActivity(intent);
                    }
                    break;

                     case "Life Quotes":{
                        Intent intent = new Intent(c, Seven.class);
                        c.startActivity(intent);
                    }
                    break;

                     case "Good Morning":{
                        Intent intent = new Intent(c, Eight.class);
                        c.startActivity(intent);
                    }
                    break;

                     case "Happy Birthday":{
                        Intent intent = new Intent(c, nine.class);
                        c.startActivity(intent);
                    }
                    break;

                     case "Love Quotes":{
                        Intent intent = new Intent(c, ten.class);
                        c.startActivity(intent);
                    }
                    break;

                     case "Dosti Quotes":{
                        Intent intent = new Intent(c, elevan.class);
                        c.startActivity(intent);
                    }
                    break;

                     case "Attitude Quotes":{
                        Intent intent = new Intent(c, twelve.class);
                        c.startActivity(intent);
                    }
                    break;


                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}

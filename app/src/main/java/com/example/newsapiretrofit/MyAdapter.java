package com.example.newsapiretrofit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<Article> list;

    public MyAdapter(Context context, List<Article> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_lay, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdapter.MyViewHolder myViewHolder, final int i) {

       Picasso.get().load(list.get(i).getUrlToImage()).into(myViewHolder.imageView);
        String d = list.get(i).getPublishedAt();
        d = d.substring(0, 10);
        try {
            Date date = new SimpleDateFormat("yyyy-mm-dd").parse(d);
            String dt = new SimpleDateFormat("dd MMM,yyyy").format(date);
            myViewHolder.tvDate.setText(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String t = list.get(i).getTitle();
        int n = t.length();
        int c = 0;
        for (int i1 = n; i1 > 0; i1--) {
            String ch = t.substring(i1 - 1, i1);
            c++;
            if (ch.equals("-")) {
                t = t.substring(0, t.length() - c);
                break;
            }
        }
        myViewHolder.tvTitle.setText(t);
        myViewHolder.tvDescription.setText(list.get(i).getDescription());
        myViewHolder.tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = list.get(i).getUrl();
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("url", a);
                context.startActivity(intent);
            }
        });
        myViewHolder.tvName.setText(list.get(i).getSource().getName());
        myViewHolder.tvName.setTextColor(Color.LTGRAY);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvDate, tvTitle, tvDescription, tvReadMore, tvName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvReadMore = itemView.findViewById(R.id.tv_read);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}

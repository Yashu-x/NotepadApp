package com.example.notepadapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepadapp.R;
import com.example.notepadapp.ViewActivity;
import com.example.notepadapp.dabamasa.Notedabamasa;
import com.example.notepadapp.dabamasa.entites.NoteTable;
import com.example.notepadapp.dabamasa.repo.Noterepo;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControllAdapter extends RecyclerView.Adapter<ControllAdapter.ViewHolder> {
    private List<NoteTable> data;
    private Context context;
    private Noterepo noterepo;
    private ExecutorService executorService;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;
        public ViewHolder(View view){
            super(view);
            textView=view.findViewById(R.id.TVTitle);
            imageView=view.findViewById(R.id.ivDelete);

        }
    }

    public ControllAdapter(Context context) {
        this.context = context;
        this.noterepo = new Noterepo(Notedabamasa.getInstance(context));
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void setData(List<NoteTable> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textView.setText(data.get(position).getTitle());
        holder.textView.setOnClickListener(view ->{
            Intent intent =new Intent(context, ViewActivity.class);
            intent.putExtra("date",data.get(position).getDate());
            intent.putExtra("body",data.get(position).getBody());
            context.startActivity(intent);

        } );
        holder.imageView.setOnClickListener(view -> {
            executorService.execute(()->{
                noterepo.delete(data.get(position));
                List<NoteTable> noteTables = noterepo.getallnotes();
                ((androidx.appcompat.app.AppCompatActivity) context).runOnUiThread(() -> {
                    setData(noteTables);

                });

            });
        });

    }

    @Override
    public int getItemCount() {
        return data !=null? data.size():0;
    }
}

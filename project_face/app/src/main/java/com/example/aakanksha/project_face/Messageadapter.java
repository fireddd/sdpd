package com.example.aakanksha.project_face;




import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Messageadapter extends RecyclerView.Adapter<Messageadapter.ViewHolder> {

    private ArrayList<message> messagelist;
    public interface Itemclicked
    {
        void onItemClicked(int index);
    }
    Itemclicked activitycreated;
    public Messageadapter(Context context, ArrayList<message> list)
    {
        messagelist=list;
        activitycreated = (Itemclicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView messagepic;
        TextView messagetext;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            messagepic=itemView.findViewById(R.id.messagepic1);
            messagetext=itemView.findViewById(R.id.messagetext1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activitycreated.onItemClicked(messagelist.indexOf((message) v.getTag()));
                }
            });
        }
    }

    public Messageadapter(ArrayList<message> messagelist) {
        this.messagelist = messagelist;
    }

    @NonNull
    @Override
    public Messageadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitems,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Messageadapter.ViewHolder viewHolder, int i) {

        viewHolder.itemView.setTag(messagelist.get(i));
        viewHolder.messagetext.setText(messagelist.get(i).getMsg());
        if(messagelist.get(i).getMsg().contains("discount")) {
            viewHolder.messagepic.setImageResource(R.drawable.creditcard);
        }
        else if (messagelist.get(i).getMsg().contains("baby"))
        {
            viewHolder.messagepic.setImageResource(R.drawable.childproduct);
        }
        else if(messagelist.get(i).getMsg().contains("delivery"))
        {
            viewHolder.messagepic.setImageResource(R.drawable.freedelivery);
        }
        else
        {
            viewHolder.messagepic.setImageResource(R.drawable.givediscount);
        }
    }

    @Override
    public int getItemCount() {
        return messagelist.size();
    }
}
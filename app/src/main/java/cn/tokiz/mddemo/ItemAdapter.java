package cn.tokiz.mddemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lounnig on 2017/6/11.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    Context mContext;
    List<Item> items;

    public ItemAdapter(List<Item> items) {
        this.items = items ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
        final ViewHolder viewHolder =  new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Item item = items.get(position);
                Intent intent = new Intent(mContext,ItemActivity.class);
                intent.putExtra(ItemActivity.ITEM_NAME,item.getName());
                intent.putExtra(ItemActivity.ITEM_RES_ID,item.getResid());
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            Item item = items.get(position);
            holder.textView.setText(item.getName());
        Glide.with(mContext).load(item.getResid()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imageView = (ImageView) cardView.findViewById(R.id.item_image);
            textView = (TextView) cardView.findViewById(R.id.item_name);
        }
    }
}

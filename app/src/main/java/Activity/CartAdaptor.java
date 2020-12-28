package Activity;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finaltry.R;

import java.util.List;

import Model.Place;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,dis,price;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cart_title);
            dis = itemView.findViewById(R.id.cart_des);
            price = itemView.findViewById(R.id.cart_price);
            img = itemView.findViewById(R.id.cart_img);

        }
    }

    private Context c;
    private List<Place> list;

    public CartAdaptor(Context c, List<Place> list){
        this.c = c;
        this.list = list;
    }
    @NonNull
    @Override
    public CartAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.cart_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdaptor.ViewHolder holder, int position) {
        Place p = list.get(position);
        holder.title.setText(p.getTitle());
        holder.dis.setText(p.getLocation());
        holder.price.setText(p.getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

package Activity;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finaltry.R;

import java.util.List;

import Model.Place;

public class PlaceAdaptor extends RecyclerView.Adapter<PlaceAdaptor.ViewHolder> {
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        this.mListener = listener;
        Log.i("adap", "setOnItemClickListener: " + (this.mListener == null));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,dis,more;
        ImageView img;
        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.place_title);
            dis = itemView.findViewById(R.id.place_dis);
            more = itemView.findViewById(R.id.place_more);
            img = itemView.findViewById(R.id.place_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    private List<Place> list;

    public PlaceAdaptor(Context c, List<Place> list){
        this.list = list;
    }
    @NonNull
    @Override
    public PlaceAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item,parent,false);
        Log.i("adap", "onCreateViewHolder: "+(mListener == null));

        PlaceAdaptor.ViewHolder n = new PlaceAdaptor.ViewHolder(v,this.mListener);
        return n;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceAdaptor.ViewHolder holder, int position) {
        Place p = list.get(position);
        holder.title.setText(p.getTitle());
        holder.dis.setText(p.getLocation());
        holder.more.setText(p.getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

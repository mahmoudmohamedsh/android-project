package Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finaltry.R;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import Model.Place;

public class PlaceRecyclerAdapter  extends RecyclerView.Adapter<PlaceRecyclerAdapter.ViewHolder>  {
    private Context context;
    private List<Place> placeList;

    public PlaceRecyclerAdapter(Context context, List<Place> placeList) {
        this.context = context;
        this.placeList = placeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_row,parent,false);
        return new ViewHolder(view , context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Place place = placeList.get(position);
        String imageUrl = null;

        holder.title.setText(place.getTitle());

        holder.location.setText(place.getLocation());
        holder.capacity.setText(place.getCapacity());
        holder.type.setText(place.getType());
        holder.desc.setText(place.getDec());

        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
        String formatedDate = dateFormat.format(new Date(Long.valueOf(place.getTimeStamp())).getTime());
        holder.timeStamp.setText(formatedDate);

        imageUrl = place.getImage();

        // Use picaso
        Picasso.with(context)
                .load(imageUrl)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView desc;
        public TextView type;
        public TextView capacity;
        public TextView location;
        public TextView timeStamp;
        public ImageView image;
        String userid;

        public ViewHolder( View view , Context cxt) {
            super(view);
            context = cxt;
            title = (TextView) view.findViewById(R.id.placeTitleList);
            desc = (TextView) view.findViewById(R.id.placeDesList);
            type = (TextView) view.findViewById(R.id.placeTypeList);
            capacity = (TextView) view.findViewById(R.id.placeCapacityList);
            location = (TextView) view.findViewById(R.id.placeLocationList);
            timeStamp = (TextView) view.findViewById(R.id.placeTimestampList);
            image = (ImageView) view.findViewById(R.id.placeImageList);
            userid = null;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // we can go to next
                }
            });

        }
    }
}

package Activity.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finaltry.R;

import java.util.ArrayList;

import Activity.CartAdaptor;
import Model.Place;

public class Cart  extends Fragment {
    private RecyclerView rv;
    private CartAdaptor adaptor;
    private ArrayList<Place> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cart_fragment,container,false);
        rv = v.findViewById(R.id.recycleview);
        list = new ArrayList<Place>();
        for(int i = 0;i<10;i++){
            list.add(new Place("title","des","location",i+"","img","type","3","time","id"));
        }
        adaptor = new CartAdaptor(getActivity(),list);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adaptor);
        return v;
    }
}

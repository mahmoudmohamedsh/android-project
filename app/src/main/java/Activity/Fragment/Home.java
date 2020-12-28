package Activity.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finaltry.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

import Activity.DetailsActivity;
import Activity.HomeActivity;
import Activity.PlaceAdaptor;
import Data.PlaceRecyclerAdapter;
import Model.Place;

public class Home extends Fragment {
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private RecyclerView rv;
    private PlaceAdaptor adaptor;
    private ArrayList<Place> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment,container,false);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("Places");
        mDatabaseReference.keepSynced(true);
        createrv(v);
        return v;
    }
    public void createrv(View v){
        rv = v.findViewById(R.id.recycleview);
        list = new ArrayList<Place>();
        adaptor = new PlaceAdaptor(getActivity(),list);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        rv.setAdapter(adaptor);
        adaptor.setOnItemClickListener(new PlaceAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                test(position);
            }
        });
    }
    void test(int position){
        Log.i("TAG", "test: ");
        list.get(position);
        Intent intent = new Intent(getActivity(),DetailsActivity.class);
        startActivity(intent);
//        list.add(new Place("title","des","location","ee","img","type","3","time","id"));
        adaptor.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        list = new ArrayList<Place>();
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, @Nullable String previousChildName) {
                Place place = snapshot.getValue(Place.class);
                list.add(place);

                Collections.reverse(list);

                adaptor = new PlaceAdaptor(getActivity(), list);
                rv.setAdapter(adaptor);
                adaptor.setOnItemClickListener(new PlaceAdaptor.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        test(position);
                    }
                });
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

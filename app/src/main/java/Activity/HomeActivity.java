package Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.finaltry.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Activity.Fragment.Cart;
import Activity.Fragment.Home;
import Activity.Fragment.Profile;
import Data.PlaceRecyclerAdapter;
import Model.Place;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bnv;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private FloatingActionButton fbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        bnv = findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home(),"one").commit();

        makeFbtn();

    }
    public void makeFbtn(){
        fbtn = findViewById(R.id.fbtn);
        fbtn.setVisibility(View.VISIBLE);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddActivity();
            }
        });
    }

    public void openAddActivity (){
        Intent intent = new Intent(this , AddPostActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch(item.getItemId()){
                case R.id.nav_home :
                    if(getSupportFragmentManager().findFragmentByTag("one") != null) {
                        //if the fragment exists, show it.
                        getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("one")).commit();
                    } else {
                        //if the fragment does not exist, add it to fragment manager.
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Home(), "one").commit();
                    }
                    if(getSupportFragmentManager().findFragmentByTag("two") != null){
                        //if the other fragment is visible, hide it.
                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("two")).commit();
                    }
                    if(getSupportFragmentManager().findFragmentByTag("three") != null){
                        //if the other fragment is visible, hide it.
                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("three")).commit();
                    }
                    break;
                case R.id.nav_cart :
                    if(getSupportFragmentManager().findFragmentByTag("two") != null) {
                        //if the fragment exists, show it.
                        getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("two")).commit();
                    } else {
                        //if the fragment does not exist, add it to fragment manager.
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Cart(), "two").commit();
                    }
                    if(getSupportFragmentManager().findFragmentByTag("one") != null){
                        //if the other fragment is visible, hide it.
                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("one")).commit();
                    }
                    if(getSupportFragmentManager().findFragmentByTag("three") != null){
                        //if the other fragment is visible, hide it.
                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("three")).commit();
                    }
                    break;
                case R.id.nav_profile :
                    if(getSupportFragmentManager().findFragmentByTag("three") != null) {
                        //if the fragment exists, show it.
                        getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("three")).commit();
                    } else {
                        //if the fragment does not exist, add it to fragment manager.
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Profile(), "three").commit();
                    }
                    if(getSupportFragmentManager().findFragmentByTag("one") != null){
                        //if the other fragment is visible, hide it.
                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("one")).commit();
                    }
                    if(getSupportFragmentManager().findFragmentByTag("two") != null){
                        //if the other fragment is visible, hide it.
                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("two")).commit();
                    }
                    break;
            }
            return  true;
        }

    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_add:
                    if(mUser != null &&
                            mAuth != null &&
                            mUser.getEmail().toString().toLowerCase().equals("mo@mo.com")){
                        startActivity(new Intent(HomeActivity.this,AddPostActivity.class));
                        finish();
                    }
                    break;
                case R.id.action_signout:
                    if(mUser != null &&
                            mAuth != null){
                        mAuth.signOut();
                        startActivity(new Intent(HomeActivity.this,MainActivity.class));
                        finish();
                    }
                    break;
            }
        return super.onOptionsItemSelected(item);
    }


}
package Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.finaltry.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class AddPostActivity extends AppCompatActivity {
    private ImageButton mplaceImage;
    private TextInputEditText mPlaceTitle;
    private TextInputEditText mPlaceDesc;
    private TextInputEditText mPlacePrice;
    private TextInputEditText mPlaceType;
    private TextInputEditText mPlaceLocation;
    private TextInputEditText mPlaceCap;
    private Button mPlaceAdd;
    private DatabaseReference mPlaceDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;
    private static final int GALLERY_CODE = 1;
    private Uri mImageUri;
    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mStorage = FirebaseStorage.getInstance().getReference();

        mProgress = new ProgressDialog(this);

        mPlaceCap = (TextInputEditText)findViewById(R.id.add_Cap);
        mPlaceDatabase = FirebaseDatabase.getInstance().getReference().child("Places");
        mplaceImage = (ImageButton) findViewById(R.id.imageButton);
        mPlaceTitle = (TextInputEditText) findViewById(R.id.add_name);
        mPlaceDesc = (TextInputEditText) findViewById(R.id.add_Des);
        mPlacePrice = (TextInputEditText) findViewById(R.id.add_price);
        mPlaceType = (TextInputEditText) findViewById(R.id.add_type);
        mPlaceLocation = (TextInputEditText) findViewById(R.id.add_location);
        mPlaceAdd = (Button) findViewById(R.id.add_place);

        mplaceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_CODE);
            }
        });
        mPlaceAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_CODE && resultCode == RESULT_OK){
            mImageUri = data.getData();
            mplaceImage.setImageURI(mImageUri);
        }
    }

    private void startPosting(){
        mProgress.setMessage("Posting to Places");
        mProgress.show();

        String titleVal = mPlaceTitle.getText().toString().trim();
        String desc = mPlaceDesc.getText().toString().trim();
        String location = mPlaceLocation.getText().toString().trim();
        String price = mPlacePrice.getText().toString().trim();
        String type = mPlaceType.getText().toString().trim();
        String Cap = mPlaceCap.getText().toString().trim();
        if (!TextUtils.isEmpty(titleVal)
            &&!TextUtils.isEmpty(desc)
                &&!TextUtils.isEmpty(location)
                &&!TextUtils.isEmpty(price)
                &&!TextUtils.isEmpty(type)
                &&!TextUtils.isEmpty(Cap)
                && mImageUri != null){

                StorageReference filepath = mStorage.child("Places_images").child(mImageUri.getLastPathSegment());
                filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //Uri downloadurl = taskSnapshot.getMetadata().getReference().getDownloadUrl().getResult();
                        DatabaseReference newPlace = mPlaceDatabase.push();

                        Map<String,String> dataTosave = new HashMap<>();
                        /*
                        * private String title;
                            private String dec;
                            private String location;
                            private String price;
                            private String image;
                            //private List<User>;
                            private String type;
                            private String capacity ;
                            private String timeStamp;
                            private String userId;
                        * */

                        dataTosave.put("title",titleVal);
                        dataTosave.put("dec",desc);
                        dataTosave.put("location",location);
                        dataTosave.put("price",price);
                        dataTosave.put("image","mfcm");
                        dataTosave.put("type",type);
                        dataTosave.put("capacity",Cap);
                        dataTosave.put("timestamp",String.valueOf(java.lang.System.currentTimeMillis()));
                        dataTosave.put("userid",mUser.getUid());

                        newPlace.setValue(dataTosave);

                        mProgress.dismiss();

                        startActivity(new Intent(AddPostActivity.this, HomeActivity.class));
                        finish();
                    }
                });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== R.id.action_signout){

                    mAuth.signOut();
                    startActivity(new Intent(AddPostActivity.this,Login.class));
                    finish();

        }
        return super.onOptionsItemSelected(item);
    }

}
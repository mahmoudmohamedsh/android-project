package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finaltry.R;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    Animation topAnimation , bottomAnimation;
    ImageView image_logo;
    TextView text_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        image_logo = (ImageView)findViewById(R.id.image_logo);
        text_logo = (TextView)findViewById(R.id.text_logo);
        topAnimation.setDuration(2000);
        bottomAnimation.setDuration(2000);

        image_logo.setAnimation(topAnimation);
        text_logo.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }
}
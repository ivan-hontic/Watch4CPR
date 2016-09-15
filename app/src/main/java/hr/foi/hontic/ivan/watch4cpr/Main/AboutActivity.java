package hr.foi.hontic.ivan.watch4cpr.Main;

import android.os.Bundle;
import android.app.Activity;
import android.view.WindowManager;

import hr.foi.hontic.ivan.watch4cpr.R;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //this is stopping device from dimming or closing the app(because of saving energy)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

}

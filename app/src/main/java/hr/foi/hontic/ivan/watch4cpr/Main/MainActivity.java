package hr.foi.hontic.ivan.watch4cpr.Main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import hr.foi.hontic.ivan.watch4cpr.R;

public class MainActivity extends WearableActivity {


    private BoxInsetLayout mContainerView;
    private TextView mTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this is stopping device from dimming or closing the app(because of saving energy)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
        mTextView = (TextView) findViewById(R.id.text);

    }

    //opens AboutActivity
    public void openAboutActivity(View v){
        Intent aboutIntent = new Intent(this, AboutActivity.class);
        startActivity(aboutIntent);
    }

    //Opens CPRContainer
    public void openCPRContainer(View v){
        Intent i = new Intent(this, CPRContainer.class);
        startActivity(i);
    }
    // openShortCPR
    public void openShortCPR(View v){
        Intent i = new Intent(this, CPRContainerShortActivity.class);
        startActivity(i);
    }


    //opens Settings
    public void openSettingsActivity(View v){
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    //opens Settings
    public void openInstructions(View v){
        Intent settingsIntent = new Intent(this, InstructionsActivity.class);
        startActivity(settingsIntent);
    }



}

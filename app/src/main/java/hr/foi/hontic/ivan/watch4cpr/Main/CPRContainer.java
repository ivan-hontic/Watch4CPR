package hr.foi.hontic.ivan.watch4cpr.Main;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;

import android.view.WindowManager;

import hr.foi.hontic.ivan.watch4cpr.Main.Fragments.Step1Fragment;

import hr.foi.hontic.ivan.watch4cpr.R;

public class CPRContainer extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cprcontainer);

        //this is stopping device from dimming or closing the app(because of saving energy)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Step1Fragment s1f = new Step1Fragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.add(R.id.fragmentContainer, s1f);
        transaction.addToBackStack(null);
        transaction.commit();


    }


}

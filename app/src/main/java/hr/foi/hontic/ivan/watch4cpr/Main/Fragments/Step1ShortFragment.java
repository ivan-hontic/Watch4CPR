package hr.foi.hontic.ivan.watch4cpr.Main.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.wearable.view.DismissOverlayView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;

import hr.foi.hontic.ivan.watch4cpr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Step1ShortFragment extends Fragment {

    private DismissOverlayView mDismissOverlay;
    ScrollView sv;
    TextView tv;

    public Step1ShortFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        //this is stopping device from dimming or closing the app(because of saving energy)
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_step1_short, container, false);


        sv = (ScrollView)root.findViewById(R.id.probaScroll);
        tv = (TextView) root.findViewById(R.id.instNumber);
        String step = getString(R.string.step1TitleTextShort);
        String steps = getString(R.string.number_of_instructionsShort);
        tv.setText(step+" / "+ steps);

        // Obtain the DismissOverlayView element
        mDismissOverlay = (DismissOverlayView) root.findViewById(R.id.dismiss_overlay);

        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public void onLongPress(MotionEvent ev) {
                        mDismissOverlay.show();

                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                        final int SWIPE_MIN_DISTANCE = 120;
                        final int SWIPE_MAX_OFF_PATH = 250;
                        final int SWIPE_THRESHOLD_VELOCITY = 200;
                        try {

                            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH){
                                return false;
                            }
                            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                                Step2ShortFragment s2f = new Step2ShortFragment();

                                FragmentManager fm = getActivity().getSupportFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                                transaction.replace(R.id.fragmentContainerShort, s2f);
                                transaction.addToBackStack(null);
                                transaction.commit();


                            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                //for left
                            }

                        } catch (Exception e) {
                            // nothing
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }


                });


        //gesture detector
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gesture.onTouchEvent(event);
                return false;
            }

        });
        //scrollview
        sv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (gesture != null) {
                    return gesture.onTouchEvent(event);}
                return false;
            }

        });

        return root;

    }

}

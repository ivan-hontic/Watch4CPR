package hr.foi.hontic.ivan.watch4cpr.Main.Fragments;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.wearable.view.DismissOverlayView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import hr.foi.hontic.ivan.watch4cpr.Main.CPRContainer;
import hr.foi.hontic.ivan.watch4cpr.R;


public class Step7Fragment extends Fragment {

    private DismissOverlayView mDismissOverlay;
    ScrollView sv;
    TextView tv;
    Button btn1;
    Button btn2;

    public Step7Fragment() {
        // Required empty public constructor
    }


    public static Step7Fragment newInstance() {
        Step7Fragment fragment = new Step7Fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
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
        View root = inflater.inflate(R.layout.fragment_step7, container, false);

        sv = (ScrollView)root.findViewById(R.id.probaScroll);
        tv = (TextView) root.findViewById(R.id.instNumber);
        String step = getString(R.string.step7TitleText);
        tv.setText(step);

        btn1 = (Button) root.findViewById(R.id.step4Button);
        btn2 = (Button) root.findViewById(R.id.step5Button);

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
                                //next step
                                Step4Fragment s4f = new Step4Fragment();

                                FragmentManager fm = getActivity().getSupportFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                                transaction.replace(R.id.fragmentContainer, s4f);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                //previous step
                                Step6Fragment s6f = new Step6Fragment();

                                FragmentManager fm = getActivity().getSupportFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                                transaction.replace(R.id.fragmentContainer, s6f);
                                transaction.addToBackStack(null);
                                transaction.commit();

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
                    return gesture.onTouchEvent(event);
                }
                return false;
            }

        });


        btn1.setOnTouchListener(new View.OnTouchListener() {
            float startX=0;
            float startY=0;
            long lastTouchDown=0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        lastTouchDown = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP: {
                        float endX = event.getX();
                        float endY = event.getY();
                        if (System.currentTimeMillis() - lastTouchDown < 200) {
                            if (isAClick(startX, endX, startY, endY)) {

                                Step4Fragment s4f = new Step4Fragment();

                                FragmentManager fm = getActivity().getSupportFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                                transaction.replace(R.id.fragmentContainer, s4f);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }
                        }

                        break;
                    }
                }
                if (gesture != null) {
                    return gesture.onTouchEvent(event);
                }
                return false;
            }
        });
        btn2.setOnTouchListener(new View.OnTouchListener() {
            float startX=0;
            float startY=0;
            long lastTouchDown=0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        lastTouchDown = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP: {
                        float endX = event.getX();
                        float endY = event.getY();
                        if (System.currentTimeMillis() - lastTouchDown < 200) {
                            if (isAClick(startX, endX, startY, endY)) {

                                Step5Fragment s5f = new Step5Fragment();

                                FragmentManager fm = getActivity().getSupportFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                                transaction.replace(R.id.fragmentContainer, s5f);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }
                        }

                        break;
                    }
                }
                if (gesture != null) {
                    return gesture.onTouchEvent(event);
                }
                return false;
            }
        });

        return root;
    }

    private boolean isAClick(float startX, float endX, float startY, float endY) {
        float differenceX = Math.abs(startX - endX);
        float differenceY = Math.abs(startY - endY);
        if (differenceX > 5/* =5 */ || differenceY > 5) {
            return false;
        }
        return true;
    }

}

package com.example.aaveg2020.aboutus;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.aaveg2020.MainActivity;
import com.example.aaveg2020.R;



public class CurlFragment extends Fragment {
    private CurlView mCurlView;
    public CurlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        int index = 0;
        try {
            v = inflater.inflate(R.layout.main_curl, container, false);
            mCurlView = (CurlView) v.findViewById(R.id.curl);
            mCurlView.setPageProvider(new CurlFragment.PageProvider());
            mCurlView.setSizeChangedObserver(new CurlFragment.SizeChangedObserver());
            mCurlView.setCurrentIndex(index);
            mCurlView.setBackgroundColor(getResources().getColor(R.color.transparent));
        }catch (Exception e) {
            AboutUsFrag2 frag = new AboutUsFrag2();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_framelayout, frag);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            v = inflater.inflate(R.layout.layout_aboutusfrag, container, false);
            //mCurlView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        }
        return v;
    }
    @Override
    public void onPause() {
        super.onPause();
        mCurlView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCurlView.onResume();
    }



    private class PageProvider implements CurlView.PageProvider {
        // Bitmap resources.
        private int[] mBitmapIds = { R.layout.page_1};

        @Override
        public int getPageCount() {
            return 6;
        }

        private Bitmap loadBitmap(int width, int height, int index) {
            LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View v;
            TextView text;
            LinearLayout layout;
            ImageView hostelLogo;
            switch (index){

                case 1: v = inflater.inflate(R.layout.page_1,null);
                    text=v.findViewById(R.id.hostelText);
                    layout=v.findViewById(R.id.hostelBack);
                    hostelLogo=v.findViewById(R.id.hostelLogo);
                    text.setText("Bands of heroes embarked on a quest to find the golden fleece. With anger gushing through their veins and blood boiling, watch them unravel the mysteries on the way as they set sail to the end of the black sea. The spirit, the commitment, the passion all coming together to protect, what they call HOME");
                    layout.setBackgroundResource(R.drawable.agatecard);
                    hostelLogo.setImageResource(R.drawable.agatelogo);break;

                case 2: v = inflater.inflate(R.layout.page_1,null);
                    text=v.findViewById(R.id.hostelText);
                    layout=v.findViewById(R.id.hostelBack);
                    hostelLogo=v.findViewById(R.id.hostelLogo);
                    text.setTextColor(Color.BLACK);
                    text.setText("Besides the rage and the war, what's more important is the journey itself. It's about the choices made along the way, the paths chosen to victory, the bonds that sprout through the expedition and the lessons learnt. With strength and resilience they defeat, and with fair play and team spirit, they overtake. They are equipped and ready to triumph this Aaveg'20.");
                    layout.setBackgroundResource(R.drawable.opalcard);
                    hostelLogo.setImageResource(R.drawable.opallogo);break;
                case 3: v = inflater.inflate(R.layout.page_1,null);
                    text=v.findViewById(R.id.hostelText);
                    layout=v.findViewById(R.id.hostelBack);
                    hostelLogo=v.findViewById(R.id.hostelLogo);
                    text.setText("They overcome their enemies with their fierce strength and conquer every kingdom they set their foot upon. There is no going back once they set their minds on war and with their unique strategies and mighty warriors they are impossible to be defeated. Beware of these champions, for they will never let go until they acheive a magnificent triumph.");
                    layout.setBackgroundResource(R.drawable.cobaltcard);
                    hostelLogo.setImageResource(R.drawable.cobaltlogo);break;
                case 4: v = inflater.inflate(R.layout.page_1,null);
                    text=v.findViewById(R.id.hostelText);
                    layout=v.findViewById(R.id.hostelBack);
                    hostelLogo=v.findViewById(R.id.hostelLogo);
                    text.setText("Fierce competitors, fighting even against the heaven, descend into earth to claim what's theirs. Feel the wrath of the demons engulf the arena.Feel the bonds forged by battles and blood. Witness their brawl with even gods, witness their rise to pinnacle, witness their glory in their journey to the top of Aaveg'20");
                    layout.setBackgroundResource(R.drawable.azuritecard);
                    hostelLogo.setImageResource(R.drawable.azuritelogo);break;
                case 5: v = inflater.inflate(R.layout.page_1,null);
                    text=v.findViewById(R.id.hostelText);
                    layout=v.findViewById(R.id.hostelBack);
                    hostelLogo=v.findViewById(R.id.hostelLogo);
                    text.setText("Besides the rage and the war, what's more important is the journey itself. It's about the choices made along the way, the paths chosen to victory, the bonds that sprout through the expedition and the lessons learnt. With strength and resilience they defeat, and with fair play and team spirit, they overtake. They are equipped and ready to triumph this Aaveg'20.");
                    layout.setBackgroundResource(R.drawable.bloodstonecard);
                    hostelLogo.setImageResource(R.drawable.bloodstonelogo);break;

                default:
                    v = inflater.inflate(R.layout.page_1,null);
                    text=v.findViewById(R.id.hostelText);
                    layout=v.findViewById(R.id.hostelBack);
                    hostelLogo=v.findViewById(R.id.hostelLogo);
                    text.setText("Aaveg, the ultimate inter-hostel showdown, scheduled to be held on 7th, 8th and 9th of February is a fest to invoke the rawest of talents with the utmost of team spirit. With the hostels competing to secure multiple trophies in the areas of sports, culturals and so on, Aaveg'20, The Abyss, is here to deconstruct reality and rekindle the battlefield."
                    );
                    layout.setBackgroundResource(R.color.darkBackground);
                    hostelLogo.setImageResource(R.drawable.aaveglogowhite);



            }
            v.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
            v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
            Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            v.draw(c);
            return b;
        }
        @Override
        public void updatePage(CurlPage page, int width, int height, int index) {
            Log.d("Current Page ",index+"");
            Bitmap front = loadBitmap(width, height, index);
            page.setTexture(front, CurlPage.SIDE_FRONT);

            page.setColor(Color.rgb(180, 180, 180), CurlPage.SIDE_BACK);
        }
    }
    /**
     * CurlView size changed observer.
     */
    private class SizeChangedObserver implements CurlView.SizeChangedObserver {
        @Override
        public void onSizeChanged(int w, int h) {
            if (w > h) {
                mCurlView.setViewMode(CurlView.SHOW_TWO_PAGES);
                mCurlView.setMargins(.1f, .05f, .1f, .05f);
            } else {
                mCurlView.setViewMode(CurlView.SHOW_ONE_PAGE);
                //mCurlView.setMargins(.1f, .1f, .1f, .1f);
            }
        }
    }
}
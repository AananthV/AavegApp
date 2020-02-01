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
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

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
        View v = inflater.inflate(R.layout.main_curl, container, false);
        int index = 0;

        mCurlView = (CurlView) v.findViewById(R.id.curl);
        mCurlView.setPageProvider(new CurlFragment.PageProvider());
        mCurlView.setSizeChangedObserver(new CurlFragment.SizeChangedObserver());
        mCurlView.setCurrentIndex(index);
        mCurlView.setBackgroundColor(getResources().getColor(R.color.transparent));
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
        private int[] mBitmapIds = { R.layout.page_1,R.layout.page_2, R.layout.page_3,R.layout.page_4,R.layout.page_5, R.layout.page_6};

        @Override
        public int getPageCount() {
            return mBitmapIds.length;
        }

        private Bitmap loadBitmap(int width, int height, int index) {
            LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(mBitmapIds[index],null);
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
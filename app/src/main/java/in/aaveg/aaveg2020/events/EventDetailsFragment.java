package in.aaveg.aaveg2020.events;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aaveg2020.R;

import java.util.ArrayList;
import java.util.List;

public class EventDetailsFragment extends Fragment {

    private Event event;
    private EventPointAdapter adapter;
    View dialog;
    AlertDialog loadingDialog;
    Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialog = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null);
        TextView tv = dialog.findViewById(R.id.progressDialog_textView);
        tv.setText("Loading...");
        loadingDialog = new AlertDialog.Builder(context).setView(dialog).setCancelable(false).create();
        loadingDialog.show();
    }

    public EventDetailsFragment(Event event) {
        this.event = event;
    }

    public EventDetailsFragment(int contentLayoutId, Event event) {
        super(contentLayoutId);
        this.event = event;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_event_details, container, false);

        final LinearLayout baseLayout = mView.findViewById(R.id.event_detail_base_layout);
        final LinearLayout rulesLayout =  mView.findViewById(R.id.event_detail_rules_layout);
        final TextView titleText, descriptionText;
        final ImageView cupImg, clusterImg;
        final TextView cupText, clusterText;
        final TextView locationText, dateText, timeText, rulesText;

        final LinearLayout headerLayout = mView.findViewById(R.id.event_header_layout);
        headerLayout.setBackground(getResources().getDrawable(R.color.event_bloodstone));

        titleText = mView.findViewById(R.id.event_detail_title);
        descriptionText = mView.findViewById(R.id.event_detail_description);

        locationText = mView.findViewById(R.id.event_detail_location_name);
        dateText = mView.findViewById(R.id.event_detail_date);
        timeText = mView.findViewById(R.id.event_detail_time);
        rulesText = mView.findViewById(R.id.event_detail_rules);

        titleText.setText(event.getName());
        descriptionText.setText(event.getDescription());

        locationText.setText(event.getVenue());
        dateText.setText(event.getDate());
        timeText.setText(event.getStartTime());

        cupImg = mView.findViewById(R.id.event_detail_cup_img);
        clusterImg = mView.findViewById(R.id.event_detail_cluster_img);

        cupText = mView.findViewById(R.id.event_detail_cup_name);
        clusterText = mView.findViewById(R.id.event_detail_cluster_name);

        cupText.setText(event.getCup());
        clusterText.setText(event.getCluster());
        cupImg.setImageDrawable(EventsUtils.getCupDrawable(event.getCup(), getResources()));
        clusterImg.setImageDrawable(EventsUtils.getClusterDrawableGold(event.getCluster(), getResources()));

        rulesText.setMovementMethod(LinkMovementMethod.getInstance());
        rulesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(event.getRules()));
                    startActivity(browserIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final String iconTag = mView.getResources().getString(R.string.icon_tag);
        final Typeface font = Typeface.createFromAsset(mView.getResources().getAssets(), "solid.otf");
        for (View view : EventsUtils.getViewsByTag(baseLayout, iconTag)) {
            if (view instanceof TextView) {
                ((TextView) view).setTypeface(font);
            }
        }
        final RecyclerView pointsRecyclerView = mView.findViewById(R.id.event_detail_points_recycler);
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < event.getPoints().size(); ++i) {
            points.add(new Point(i + 1, event.getPoints().get(i)));
        }
        adapter = new EventPointAdapter(points, mView.getContext());
        pointsRecyclerView.setHasFixedSize(true);
        pointsRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        pointsRecyclerView.setAdapter(adapter);
        loadingDialog.dismiss();
        return mView;
    }
}

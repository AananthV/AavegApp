package com.example.aaveg2020.events;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aaveg2020.R;
import com.example.aaveg2020.UserUtils;
import com.example.aaveg2020.api.AavegApi;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClustersFragment extends Fragment implements OnClusterClickListener {
    private static final String TAG = "ClustersFragment";
    private RecyclerView clustersRecyclerView;
    private ClusterAdapter adapter;
    private OnFragmentChangeListener fragmentChangeListener;
    private List<Event> events = new ArrayList<>();
    View dialog;
    AlertDialog loadingDialog;
    Context context;
    Handler handler;
    Runnable runnable;
    Snackbar snackbar;

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

    public ClustersFragment(OnFragmentChangeListener fragmentChangeListener) {
        this.fragmentChangeListener = fragmentChangeListener;
    }

    public ClustersFragment(int contentLayoutId, OnFragmentChangeListener fragmentChangeListener) {
        super(contentLayoutId);
        this.fragmentChangeListener = fragmentChangeListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_clusters, container, false);
        clustersRecyclerView = mView.findViewById(R.id.clusters_recycler_view);
        clustersRecyclerView.setHasFixedSize(true);
        clustersRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        Log.d(TAG, "Hostel: " + UserUtils.hostel);
        final LinearLayout headerLayout = mView.findViewById(R.id.event_header_layout);
        headerLayout.setBackground(getResources().getDrawable(R.color.event_bloodstone));
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                removeSnackBarTimer();
                snackbar = Snackbar.make(container, "Check your internet and try again.", Snackbar.LENGTH_LONG);
                snackbar.setAction("Retry", v -> {
                    getClusters();
                    loadingDialog.show();
                    getSnackBarAfterFixedTime();
                })
                        .show();
                loadingDialog.dismiss();
            }
        };
        handler.post(runnable);
        return mView;
    }

    private void getClusters() {
        AavegApi api;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AavegApi.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(AavegApi.class);
        Call<ClusterResponse> call = api.getAllClusters();

        call.enqueue(new Callback<ClusterResponse>() {
            @Override
            public void onResponse(Call<ClusterResponse> call, Response<ClusterResponse> response) {
                Log.d(TAG, "Response: " + response + ", status: " + response.code());
                updateRecyclerView(response.body());
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ClusterResponse> call, Throwable t) {
                Log.d(TAG, "Error: ", t);
                loadingDialog.dismiss();
            }
        });
    }

    private void updateRecyclerView(ClusterResponse response) {
        List<Cluster> clusters = response.getEventsData();
        events = EventsUtils.getAllEventsFromClusters(clusters);
        Log.d(TAG, "All events: " + events);
        Log.d(TAG, "Recent events: " + EventsUtils.getRecentEvents(events, 10 * 24));

        adapter = new ClusterAdapter(clusters, this);
        clustersRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickCluster(Cluster cluster) {
        EventsFragment fragment = new EventsFragment(cluster, fragmentChangeListener);
        fragmentChangeListener.onFragmentChange(fragment,"EventFragment");
    }

    private void getSnackBarAfterFixedTime() {
        handler.postDelayed(runnable, 8000);
    }

    private void removeSnackBarTimer() {
        handler.removeCallbacks(runnable);
    }
}

package com.example.aaveg2020.Home;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.aaveg2020.R;
import com.example.aaveg2020.Scoreboard.EventsModel;
import com.example.aaveg2020.Scoreboard.ScoreboardModel;
import com.example.aaveg2020.Scoreboard.ScoreboardPresenter;
import com.example.aaveg2020.Scoreboard.ScoreboardPresenterImpl;
import com.example.aaveg2020.Scoreboard.ui.Overall.OverallModel;
import com.example.aaveg2020.UserUtils;

import java.util.ArrayList;
import java.util.Collections;

public class HomeFragment extends Fragment implements HomeView {

    ScoreboardPresenter presenter;
    ScoreboardModel scoreboardModel;
    RecyclerView recentEvents;
    RecentEventsAdapter adapter;
    ImageView hostelImage;
    ArrayList<EventsModel> events;
    SnapHelper helper;
    TextView overallPlace,culturalsPlace,sportsPlace,spectrumPlace;
    OverallModel total,culturals,sports,spectrum;
    final int spacing = 20;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.fragment_home,container,false);
        presenter = new ScoreboardPresenterImpl(this);
        presenter.getTotal();
        recentEvents=mView.findViewById(R.id.home_hostel_events);
        overallPlace=mView.findViewById(R.id.overall_place);
        culturalsPlace=mView.findViewById(R.id.culturals_place);
        sportsPlace=mView.findViewById(R.id.sports_place);
        spectrumPlace=mView.findViewById(R.id.spectrum_place);
        hostelImage=mView.findViewById(R.id.home_hostel_img);
        setHostelImg();
        return mView;
    }

    @Override
    public void onGetScoreboardSuccess(ScoreboardModel scoreboardModel) {
        this.scoreboardModel=scoreboardModel;
        this.setHostelEvents();
        this.calculateTotal();
        this.setPositions();
    }
    private void setHostelImg(){
        // crash here if userutils.hostel primarily because login function not updating val.
        // TODO Fix this.
        switch ("agate"){
            case "agate":
                hostelImage.setImageResource(R.drawable.agate);
                break;
            case "azurite":
                hostelImage.setImageResource(R.drawable.azurite);
                break;
            case "bloodstone":
                hostelImage.setImageResource(R.drawable.bloodstone);
                break;
            case "cobalt":
                hostelImage.setImageResource(R.drawable.cobalt);
                break;
            case "opal":
                hostelImage.setImageResource(R.drawable.opal);
                break;
        }
    }
    private void assignDataToRecycler()
    {
        loadingDialog.dismiss();
        adapter=new RecentEventsAdapter(events);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        helper = new LinearSnapHelper();
        recentEvents.setLayoutManager(layoutManager);
        recentEvents.setAdapter(adapter);
        helper.attachToRecyclerView(recentEvents);
        layoutManager.scrollToPosition(1);
        recentEvents.setPadding(spacing, spacing, spacing, spacing);
        recentEvents.setClipToPadding(false);
        recentEvents.setClipChildren(false);
        recentEvents.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(spacing, spacing, spacing, spacing);
            }
        });
        //recentEvents.smoothScrollToPosition(1);

    }
    private void setHostelEvents()
    {
        if(UserUtils.hostel!=null){

            switch (UserUtils.hostel){
                case "agate":
                    events=scoreboardModel.getRecents().getAgate();
                    break;
                case "azurite":
                    events=scoreboardModel.getRecents().getAzurite();
                    break;
                case "bloodstone":
                    events=scoreboardModel.getRecents().getBloodstone();
                    break;
                case "cobalt":
                    events=scoreboardModel.getRecents().getCobalt();
                    break;
                case "opal":
                    events=scoreboardModel.getRecents().getOpal();
                    break;
                default:
                    events=scoreboardModel.getRecents().getAgate();
            }

        }
        else {
            events=scoreboardModel.getRecents().getAgate();
        }
        assignDataToRecycler();
    }
    private void calculateTotal()
    {
        total=new OverallModel(0,0,0,0,0);
        culturals=scoreboardModel.getStandings().getCulturals();
        sports=scoreboardModel.getStandings().getSports();
        spectrum=scoreboardModel.getStandings().getSpectrum();
        total.setAgate(culturals.getAgate()+sports.getAgate()+spectrum.getAgate());
        total.setAzurite(culturals.getAzurite()+sports.getAzurite()+spectrum.getAzurite());
        total.setBloodstone(culturals.getBloodstone()+sports.getBloodstone()+spectrum.getBloodstone());
        total.setCobalt(culturals.getCobalt()+sports.getCobalt()+spectrum.getCobalt());
        total.setOpal(culturals.getOpal()+sports.getOpal()+spectrum.getOpal());
    }
    private int getPosition(OverallModel overallModel)
    {
        int pos = -1;
        ArrayList<HostelScore> scores=new ArrayList<HostelScore>();
        scores.add(new HostelScore("agate",overallModel.getAgate()));
        scores.add(new HostelScore("azurite",overallModel.getAzurite()));
        scores.add(new HostelScore("bloodstone",overallModel.getBloodstone()));
        scores.add(new HostelScore("cobalt",overallModel.getCobalt()));
        scores.add(new HostelScore("opal",overallModel.getOpal()));
        scores=this.doSelectionSort(scores);
        for (int i=0;i<5;i++)
        {
            if(scores.get(i).name==UserUtils.hostel){
                pos=i;
                break;
            }
        }
        return pos;
    }
    private void setPositions(){
        this.setSuffix(getPosition(total),overallPlace);
        this.setSuffix(getPosition(culturals),culturalsPlace);
        this.setSuffix(getPosition(sports),sportsPlace);
        this.setSuffix(getPosition(spectrum),spectrumPlace);
    }
    public ArrayList<HostelScore> doSelectionSort(ArrayList<HostelScore> arr) {
        for (int i = 0; i < arr.size(); i++) {
            // find position of smallest num between (i + 1)th element and last element
            int pos = i;
            for (int j = i; j < arr.size(); j++) {
                if (arr.get(j).score > arr.get(pos).score)
                    pos = j;
            }
            // Swap min (smallest num) to current position on array
            HostelScore min = arr.get(pos);
            arr.set(pos, arr.get(i));
            arr.set(i, min);
        }
        return arr;
    }
    private void setSuffix(int pos,TextView tv){
        switch (pos){
            case 0:
                tv.setText(Integer.toString(pos+1)+"st\nplace");
                break;
            case 1:
                tv.setText(Integer.toString(pos+1)+"nd\nplace");
                break;
            case 2:
                tv.setText(Integer.toString(pos+1)+"rd\nplace");
                break;
            default:
                tv.setText(Integer.toString(pos+1)+"th\nplace");
                break;
        }
    }
    public class HostelScore{
        String name;
        int score;

        public HostelScore(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

}
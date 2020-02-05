package com.example.aaveg2020.Scoreboard.ui.Overall;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aaveg2020.Home.HomeFragment;
import com.example.aaveg2020.R;
import com.example.aaveg2020.Scoreboard.ScoreboardModel;
import com.example.aaveg2020.Scoreboard.ScoreboardPresenter;
import com.example.aaveg2020.Scoreboard.ScoreboardPresenterImpl;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.LargeValueFormatter;

import java.util.ArrayList;

public class OverallFragment extends Fragment implements IOverallView {

    ScoreboardPresenter presenter;
    ScoreboardModel scoreboardModel;
    BarChart chart;
    RecyclerView points;
    View dialog;
    AlertDialog loadingDialog;
    Context context;
    ArrayList<HomeFragment.HostelScore> scores;

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_overall, container, false);
        presenter = new ScoreboardPresenterImpl(this);
        presenter.getTotal();
        chart = root.findViewById(R.id.overall_chart);
        points=root.findViewById(R.id.overall_standings);
        return root;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("Agate");
        xAxis.add("Azurite");
        xAxis.add("Bloodstone");
        xAxis.add("Cobalt");
        xAxis.add("Opal");
        return xAxis;
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(new float[]{scoreboardModel.getStandings().getCulturals().getAgate(),scoreboardModel.getStandings().getSpectrum().getAgate(),scoreboardModel.getStandings().getSports().getAgate()}, 0); // agate
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(new float[]{scoreboardModel.getStandings().getCulturals().getAzurite(),scoreboardModel.getStandings().getSpectrum().getAzurite(),scoreboardModel.getStandings().getSports().getAzurite()}, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(new float[]{scoreboardModel.getStandings().getCulturals().getBloodstone(),scoreboardModel.getStandings().getSpectrum().getBloodstone(),scoreboardModel.getStandings().getSports().getBloodstone()}, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(new float[]{scoreboardModel.getStandings().getCulturals().getCobalt(),scoreboardModel.getStandings().getSpectrum().getCobalt(),scoreboardModel.getStandings().getSports().getCobalt()}, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(new float[]{scoreboardModel.getStandings().getCulturals().getOpal(),scoreboardModel.getStandings().getSpectrum().getOpal(),scoreboardModel.getStandings().getSports().getOpal()}, 4); // May
        valueSet1.add(v1e5);
        BarDataSet barDataSet1 = new BarDataSet(valueSet1,"");
        barDataSet1.setColors(new int[]{Color.parseColor("#09bc9a"),Color.parseColor("#f49d6e"),Color.parseColor("#75dddd")});
        barDataSet1.setValueFormatter(new LargeValueFormatter());
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    @Override
    public void onGetScoreboardSuccess(ScoreboardModel scoreboard) {
        this.scoreboardModel = scoreboard;
        try {
            assignDataToGraph();
            assignPointsToHostel();
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),"Slow Down",Toast.LENGTH_SHORT).show();
        }
    }
    public void assignDataToGraph()
    {
        loadingDialog.dismiss();
        BarData data = new BarData(getXAxisValues(), getDataSet());
         data.setValueTextColor(getResources().getColor(R.color.white));
        chart.setData(data);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisLeft().setTextColor(getResources().getColor(R.color.white));
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawAxisLine(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setTextColor(getResources().getColor(R.color.white));
        chart.setDrawGridBackground(false);
        chart.setDrawBorders(false);
        chart.setDrawValuesForWholeStack(false);
        chart.setDescription("");
        chart.animateY(1000);
        chart.setDragEnabled(false);
        chart.setBackgroundColor(Color.parseColor("#8f000000"));
        chart.setBorderColor(getResources().getColor(R.color.white));
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getAxisLeft().setValueFormatter(new LargeValueFormatter());
        chart.invalidate();
        Legend legend=chart.getLegend();
        legend.setLabels(new String[]{"Culturals","Spectrum","Sports"});
        legend.setTextColor(getResources().getColor(R.color.white));
    }
    public void assignPointsToHostel(){
        scores=new ArrayList<>();
        scores.add(new HomeFragment.HostelScore("Agate",scoreboardModel.getTotal().getAgate()));
        scores.add(new HomeFragment.HostelScore("Azurite",scoreboardModel.getTotal().getAzurite()));
        scores.add(new HomeFragment.HostelScore("Bloodstone",scoreboardModel.getTotal().getBloodstone()));
        scores.add(new HomeFragment.HostelScore("Cobalt",scoreboardModel.getTotal().getCobalt()));
        scores.add(new HomeFragment.HostelScore("Opal",scoreboardModel.getTotal().getOpal()));
        scores=HomeFragment.doSelectionSort(scores);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        points.setLayoutManager(layoutManager);
        TotalPointsAdapter adapter=new TotalPointsAdapter(getContext(),scores);
        points.setAdapter(adapter);
    }
}
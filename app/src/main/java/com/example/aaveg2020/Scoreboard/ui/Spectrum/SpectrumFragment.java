package com.example.aaveg2020.Scoreboard.ui.Spectrum;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.example.aaveg2020.Scoreboard.ui.Overall.TotalPointsAdapter;
import com.example.aaveg2020.Scoreboard.ui.sports.SportsView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.LargeValueFormatter;

import java.util.ArrayList;

public class SpectrumFragment extends Fragment implements SportsView {
    ScoreboardPresenter presenter;
    BarChart chart;
    ScoreboardModel scoreboardModel;
    RecyclerView standings;
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
        scores=new ArrayList<>();
        loadingDialog = new AlertDialog.Builder(context).setView(dialog).setCancelable(false).create();
        loadingDialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_spectrum, container, false);
        presenter = new ScoreboardPresenterImpl(this);
        presenter.getTotal();
        chart = root.findViewById(R.id.spectrum_graph);
        standings=root.findViewById(R.id.spectrum_standings);
        return root;
    }
    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("Agate");
        xAxis.add("Azurite");
        xAxis.add("BloodStone");
        xAxis.add("Cobalt");
        xAxis.add("Opal");
        return xAxis;
    }
    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(scoreboardModel.getStandings().getSpectrum().getAgate(), 0); // agate
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(scoreboardModel.getStandings().getSpectrum().getAzurite(), 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(scoreboardModel.getStandings().getSpectrum().getBloodstone(), 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(scoreboardModel.getStandings().getSpectrum().getCobalt(), 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(scoreboardModel.getStandings().getSpectrum().getOpal(), 4); // May
        valueSet1.add(v1e5);
        BarDataSet barDataSet1 = new BarDataSet(valueSet1,"Spectrum");
        barDataSet1.setColor(Color.parseColor("#f49d6e"));
        barDataSet1.setValueFormatter(new LargeValueFormatter());
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }
    public void assignDataToGraph()
    {
        loadingDialog.dismiss();
        BarData data = new BarData(getXAxisValues(), getDataSet());
        Log.d("!!!!!!!!!!", data.toString());
        chart.setData(data);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawAxisLine(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.setDrawGridBackground(false);
        chart.setDrawBorders(false);
        chart.setDrawValuesForWholeStack(false);
        chart.setDescription("");
        chart.animateY(1000);
        chart.setDragEnabled(false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getAxisLeft().setValueFormatter(new LargeValueFormatter());
        chart.invalidate();
    }
    public void assignDataToTable(){
        scores.add(new HomeFragment.HostelScore("Agate",scoreboardModel.getStandings().getSpectrum().getAgate()));
        scores.add(new HomeFragment.HostelScore("Azurite",scoreboardModel.getStandings().getSpectrum().getAzurite()));
        scores.add(new HomeFragment.HostelScore("Bloodstone",scoreboardModel.getStandings().getSpectrum().getBloodstone()));
        scores.add(new HomeFragment.HostelScore("Cobalt",scoreboardModel.getStandings().getSpectrum().getCobalt()));
        scores.add(new HomeFragment.HostelScore("Opal",scoreboardModel.getStandings().getSpectrum().getOpal()));
        scores=HomeFragment.doSelectionSort(scores);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        standings.setLayoutManager(layoutManager);
        TotalPointsAdapter adapter=new TotalPointsAdapter(getContext(),scores);
        standings.setAdapter(adapter);
    }

    @Override
    public void onGetScoreboardSuccess(ScoreboardModel scoreboardModel) {
        this.scoreboardModel=scoreboardModel;
        assignDataToGraph();
        assignDataToTable();
    }
}

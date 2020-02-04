package com.example.aaveg2020.login;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.aaveg2020.MainActivity;
import com.example.aaveg2020.R;
import com.example.aaveg2020.UserUtils;
import com.example.aaveg2020.api.AavegApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseHostel extends Fragment {
    TextView agate, bloodstone, opal, cobalt, azurite;
    Button hostelSelect;
    String chosenHostel;
    CardView a, b, c, o, az;
    LinearLayout background;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    CardView hostelBanner;
    ImageView agateBack,azuriteBack,bloodstoneBack,opalBack,cobaltBack;


    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().onBackPressed();
    }

    public ChooseHostel() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_choose_hostel, container, false);
        agate = v.findViewById(R.id.agatetext);
        bloodstone = v.findViewById(R.id.bloodstonetext);
        opal = v.findViewById(R.id.opaltext);
        cobalt = v.findViewById(R.id.cobalttext);
        azurite = v.findViewById(R.id.azuritetext);
        hostelSelect = v.findViewById(R.id.chooseButton);
        background = v.findViewById(R.id.hostelback);
        a = v.findViewById(R.id.agate);
        b = v.findViewById(R.id.bloodstone);
        o = v.findViewById(R.id.opal);
        c = v.findViewById(R.id.cobalt);
        az = v.findViewById(R.id.azurite);
        agateBack = v.findViewById(R.id.agatecard);
        bloodstoneBack = v.findViewById(R.id.bloodstonecard);
        opalBack = v.findViewById(R.id.opalcard);
        cobaltBack = v.findViewById(R.id.cobaltcard);
        azuriteBack = v.findViewById(R.id.azuritecard);
        hostelBanner=v.findViewById(R.id.hostelbanner);
        hostelBanner.setBackgroundResource(R.drawable.cardbanner);
        hostelSelect = v.findViewById(R.id.chooseButton);
        chosenHostel = "";

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCard(agate,R.drawable.agatecard,agateBack);

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCard(bloodstone,R.drawable.bloodstonecard,bloodstoneBack);

            }
        });
        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectCard(opal,R.drawable.opalcard,opalBack);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCard(cobalt,R.drawable.cobaltcard,cobaltBack);
;
            }
        });
        az.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectCard(azurite,R.drawable.azuritecard,azuriteBack);

            }
        });
        hostelSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!chosenHostel.isEmpty()) {
                    setHostel(chosenHostel);

                } else {
                    Toast.makeText(getActivity(), "Please choose a hostel", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

    private void setHostel(final String chosenHostel) {
        Toast.makeText(getActivity(), chosenHostel, Toast.LENGTH_LONG).show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://aaveg.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AavegApi api = retrofit.create(AavegApi.class);

        Call<LoginBody> call = api.setHostel(chosenHostel, UserUtils.APIToken);

        call.enqueue(new Callback<LoginBody>() {

            @Override
            public void onResponse(Call<LoginBody> call, Response<LoginBody> response) {
                pref= getActivity().getSharedPreferences("Aaveg2020", MODE_PRIVATE);
                editor = pref.edit();
                editor.putString("hostel",chosenHostel);
                editor.apply();
                Log.d("Fukk",pref.getString("hostel",null));
               Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<LoginBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void selectCard(TextView hostel,int background,ImageView hostelBack){

    int x=0;
        switch(chosenHostel){
            case "Agate": agateBack.setBackgroundResource(x);
            break;
            case "Azurite": azuriteBack.setBackgroundResource(x);break;
            case "Bloodstone":bloodstoneBack.setBackgroundResource(x);break;
            case "Cobalt":cobaltBack.setBackgroundResource(x); break;
            case "Opal":opalBack.setBackgroundResource(x);break;

        }

        chosenHostel = hostel.getText().toString();
        hostelBack.setBackgroundResource(background);
        Toast.makeText(getActivity(),chosenHostel,Toast.LENGTH_SHORT).show();
    }

}

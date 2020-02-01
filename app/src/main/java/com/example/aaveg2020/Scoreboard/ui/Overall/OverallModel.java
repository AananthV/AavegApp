package com.example.aaveg2020.Scoreboard.ui.Overall;

import com.google.gson.annotations.SerializedName;

public class OverallModel {
    @SerializedName("Azurite")
    int azurite;
    @SerializedName("Bloodstone")
    int bloodstone;
    @SerializedName("Cobalt")
    int cobalt;
    @SerializedName("Agate")
    int agate;
    @SerializedName("Opal")
    int opal;

    public OverallModel(int azurite, int bloodstone, int cobalt, int agate, int opal) {
        this.azurite = azurite;
        this.bloodstone = bloodstone;
        this.cobalt = cobalt;
        this.agate = agate;
        this.opal = opal;
    }

    public int getAzurite() {
        return azurite;
    }

    public int getBloodstone() {
        return bloodstone;
    }

    public int getCobalt() {
        return cobalt;
    }

    public int getAgate() {
        return agate;
    }

    public int getOpal() {
        return opal;
    }

    public void setAzurite(int azurite) {
        this.azurite = azurite;
    }

    public void setBloodstone(int bloodstone) {
        this.bloodstone = bloodstone;
    }

    public void setCobalt(int cobalt) {
        this.cobalt = cobalt;
    }

    public void setAgate(int agate) {
        this.agate = agate;
    }

    public void setOpal(int opal) {
        this.opal = opal;
    }
}
package in.aaveg.aaveg2020.Scoreboard;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecentsModel {
    @SerializedName("Agate")
    ArrayList<EventsModel> agate;
    @SerializedName("Azurite")
    ArrayList<EventsModel> azurite;
    @SerializedName("Bloodstone")
    ArrayList<EventsModel> bloodstone;
    @SerializedName("Cobalt")
    ArrayList<EventsModel> cobalt;
    @SerializedName("Opal")
    ArrayList<EventsModel> opal;

    public RecentsModel(ArrayList<EventsModel> agate, ArrayList<EventsModel> azurite, ArrayList<EventsModel> bloodstone, ArrayList<EventsModel> cobalt, ArrayList<EventsModel> opal) {
        this.agate = agate;
        this.azurite = azurite;
        this.bloodstone = bloodstone;
        this.cobalt = cobalt;
        this.opal = opal;
    }

    public ArrayList<EventsModel> getAgate() {
        return agate;
    }

    public ArrayList<EventsModel> getAzurite() {
        return azurite;
    }

    public ArrayList<EventsModel> getBloodstone() {
        return bloodstone;
    }

    public ArrayList<EventsModel> getCobalt() {
        return cobalt;
    }

    public ArrayList<EventsModel> getOpal() {
        return opal;
    }
}

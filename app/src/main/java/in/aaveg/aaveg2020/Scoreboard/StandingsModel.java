package in.aaveg.aaveg2020.Scoreboard;

import in.aaveg.aaveg2020.Scoreboard.ui.Overall.OverallModel;
import com.google.gson.annotations.SerializedName;

public class StandingsModel {
    @SerializedName("culturals")
    OverallModel culturals;
    @SerializedName("spectrum")
    OverallModel spectrum;
    @SerializedName("sports")
    OverallModel sports;

    public OverallModel getCulturals() {
        return culturals;
    }

    public OverallModel getSpectrum() {
        return spectrum;
    }

    public OverallModel getSports() {
        return sports;
    }
}

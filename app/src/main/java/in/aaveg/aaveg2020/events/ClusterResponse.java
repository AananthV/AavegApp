package in.aaveg.aaveg2020.events;

import java.util.List;

public class ClusterResponse {
    private String title;
    private List<Cluster> eventsData;

    public ClusterResponse(String title, List<Cluster> eventsData) {
        this.title = title;
        this.eventsData = eventsData;
    }

    public ClusterResponse() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Cluster{" +
                "title='" + title + '\'' +
                ", eventsData=" + eventsData +
                '}';
    }

    public List<Cluster> getEventsData() {
        return eventsData;
    }

    public void setEventsData(List<Cluster> eventsData) {
        this.eventsData = eventsData;
    }
}

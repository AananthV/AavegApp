package in.aaveg.aaveg2020.events;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import com.example.aaveg2020.R;
import in.aaveg.aaveg2020.UserUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventsUtils {

    public static final String TIME_FORMAT = "hh:mm a";
    public static final String DATE_FORMAT = "yyyy/MM/dd";
    public static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm";
    public static final String ALL_EVENTS = "All";
    public static final int DEFAULT_TIME = 2;

    public static final SimpleDateFormat SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.US);

    public static final String MESSAGE_UNREGISTER_FAIL = "Failed to Unregister";
    public static final String MESSAGE_REGISTER_FAIL = "Failed to Register";
    public static final String MESSAGE_REGISTERED_ALREADY = "Already Registered";
    public static final String RESPONSE_REGISTERED_ALREADY = "You have already registered for the" +
            " event";

    public static final String TAG = "EventsUtils";

    public static String parseEventName(String event) {
        String[] events = event.split("_");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < events.length; i++) {
            if (i == 0 && events[0] != null && events[0].length() != 0) {
                stringBuilder.append(Character.toUpperCase(events[0].charAt(0)))
                        .append(events[0].substring(1));
            } else {
                stringBuilder.append(" ").append(events[i]);
            }
        }
        return stringBuilder.toString();
    }

    public static String getFormattedTime(String time) {
        String formattedTime;
        SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
        Date date = null;
        try {
            date = timeFormat.parse(time);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm aa", Locale.getDefault());
        formattedTime = simpleDateFormat.format(date);
        return formattedTime;
    }

    public static String getFormattedDate(String date) {
        String formattedTime;
        SimpleDateFormat timeFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        Date givenDate = null;
        try {
            givenDate = timeFormat.parse(date);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM dd ", Locale.getDefault());
        formattedTime = simpleDateFormat.format(givenDate);
        return formattedTime;
    }

    public static ArrayList<View> getViewsByTag(ViewGroup root, String tag) {
        ArrayList<View> views = new ArrayList<View>();
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                views.addAll(getViewsByTag((ViewGroup) child, tag));
            }

            final Object tagObj = child.getTag();
            if (tagObj != null && tagObj.equals(tag)) {
                views.add(child);
            }

        }
        return views;
    }


    public static Drawable getCupDrawable(String cupName, Resources resources) {
        switch (cupName) {
            case "Spectrum":
            default:
                return resources.getDrawable(R.drawable.spectrum_cup);
            case "Sports":
                return resources.getDrawable(R.drawable.sports_cup);
        }
    }

    public static Drawable getClusterDrawable(String clusterName, Resources resources) {
        switch (clusterName) {
            case "Arts":
                return resources.getDrawable(R.drawable.arts);
            case "Dance":
                return resources.getDrawable(R.drawable.dance);
            case "Lits":
                return resources.getDrawable(R.drawable.lits);
            case "Dramatics":
                return resources.getDrawable(R.drawable.dramatics);
            case "Sports":
                return resources.getDrawable(R.drawable.sports);
            case "Media":
                return resources.getDrawable(R.drawable.media);
            case "Misc":
            default:
                return resources.getDrawable(R.drawable.misc);
        }
    }

    public static Drawable getClusterDrawableOrange(String clusterName, Resources resources) {
        switch (clusterName) {
            case "Arts":
                return resources.getDrawable(R.drawable.arts_orange);
            case "Dance":
                return resources.getDrawable(R.drawable.dance_orange);
            case "Lits":
                return resources.getDrawable(R.drawable.lits_orange);
            case "Dramatics":
                return resources.getDrawable(R.drawable.dramatics_orange);
            case "Sports":
                return resources.getDrawable(R.drawable.sports_orange);
            case "Media":
                return resources.getDrawable(R.drawable.media_orange);
            case "Misc":
            default:
                return resources.getDrawable(R.drawable.misc_orange);
        }
    }

    public static Drawable getClusterDrawableGold(String clusterName, Resources resources) {
        switch (clusterName) {
            case "Arts":
                return resources.getDrawable(R.drawable.arts_gold);
            case "Dance":
                return resources.getDrawable(R.drawable.dance_gold);
            case "Lits":
                return resources.getDrawable(R.drawable.lits_gold);
            case "Dramatics":
                return resources.getDrawable(R.drawable.dramatics_gold);
            case "Sports":
                return resources.getDrawable(R.drawable.sports_gold);
            case "Media":
                return resources.getDrawable(R.drawable.media_gold);
            case "Misc":
            default:
                return resources.getDrawable(R.drawable.misc_gold);
        }
    }

    public static List<Event> getAllEventsFromClusters(List<Cluster> clusters) {
        List<Event> events = new ArrayList<>();
        for (Cluster cluster : clusters) {
            events.addAll(cluster.getEvents());
        }
        return events;
    }

    public static List<Event> getRecentEvents(List<Event> events, long hoursDiff) {
        List<Event> recentEvents = new ArrayList<>();
        Date now = new Date();
        for (Event event : events) {
            try {
                Date eventDateTime = SIMPLE_DATE_TIME_FORMAT.parse(event.getStartDateTime());
                long diff = (Math.abs(now.getTime() - eventDateTime.getTime())) / (60 * 60 * 1000);
                if (diff <= hoursDiff) {
                    recentEvents.add(event);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return recentEvents;
    }

    public static Drawable getHostelCard(Resources resources) {
        if (UserUtils.hostel == null) {
            return resources.getDrawable(R.drawable.gold_bg_drawable);
        }
        switch (UserUtils.hostel) {
            case "Agate":
                return resources.getDrawable(R.drawable.agate_bg_drawable);
            case "Azurite":
                return resources.getDrawable(R.drawable.azurite_bg_drawable);
            case "Bloodstone":
                return resources.getDrawable(R.drawable.bloodstone_bg_drawable);
            case "Cobalt":
                return resources.getDrawable(R.drawable.cobalt_bg_drawable);
            case "Opal":
                return resources.getDrawable(R.drawable.opal_bg_drawable);
            default:
                return resources.getDrawable(R.drawable.gold_bg_drawable);
        }
    }

    public static Drawable getHostelColor(Resources resources) {
        if (UserUtils.hostel == null) {
            return resources.getDrawable(R.drawable.gold_bg_drawable);
        }
        switch (UserUtils.hostel) {
            case "Agate":
                return resources.getDrawable(R.color.event_agate);
            case "Azurite":
                return resources.getDrawable(R.color.event_azurite);
            case "Bloodstone":
                return resources.getDrawable(R.color.event_bloodstone);
            case "Cobalt":
                return resources.getDrawable(R.color.event_cobalt);
            case "Opal":
                return resources.getDrawable(R.color.event_opal);
            default:
                return resources.getDrawable(R.color.events_header_bg);
        }
    }
    public static Drawable getTabColor(Resources resources) {
        if (UserUtils.hostel == null) {
            return resources.getDrawable(R.drawable.gold_bg_drawable);
        }
        switch (UserUtils.hostel) {
            case "Agate":
                return resources.getDrawable(R.color.tab_agate);
            case "Azurite":
                return resources.getDrawable(R.color.tab_azurite);
            case "Bloodstone":
                return resources.getDrawable(R.color.tab_bloodstone);
            case "Cobalt":
                return resources.getDrawable(R.color.tab_cobalt);
            case "Opal":
                return resources.getDrawable(R.color.tab_opal);
            default:
                return resources.getDrawable(R.color.events_header_bg);
        }
    }
}

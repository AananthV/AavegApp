package com.example.aaveg2020.events;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import com.example.aaveg2020.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EventsUtils {

    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String ALL_EVENTS = "All";
    public static final int DEFAULT_TIME = 2;

    public static final String MESSAGE_UNREGISTER_FAIL = "Failed to Unregister";
    public static final String MESSAGE_REGISTER_FAIL = "Failed to Register";
    public static final String MESSAGE_REGISTERED_ALREADY = "Already Registered";
    public static final String RESPONSE_REGISTERED_ALREADY = "You have already registered for the" +
            " event";

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

    public static ArrayList<View> getViewsByTag(ViewGroup root, String tag){
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
}

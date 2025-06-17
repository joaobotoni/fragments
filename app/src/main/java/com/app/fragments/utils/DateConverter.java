package com.app.fragments.utils;

import android.util.Log;

import androidx.room.TypeConverter;
import java.util.Date;


public class DateConverter {
    @TypeConverter
    public static Date toDate(Long time) {
        return time == null ? null : new Date(time);
    }
    @TypeConverter
    public static Long toLong(Date date) {
        return date == null ? null : date.getTime();
    }
}

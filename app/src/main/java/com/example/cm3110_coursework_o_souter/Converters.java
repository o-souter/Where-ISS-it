package com.example.cm3110_coursework_o_souter;

import androidx.room.TypeConverter;

import java.util.Calendar;
import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }
    @TypeConverter
    public static Long dateToTimeStamp(Date date) {
        return date == null ? null : date.getTime();
    }
    @TypeConverter
    public static Date calToTimeStamp(Calendar cal) {
        return cal == null ? null : cal.getTime();
    }
    //@TypeConverter
    //public static Calendar timeStampToCal(Date date) {
    //    return date == null ? null : new Calendar(date);
    //}
}

package net.ticherhaz.tarikhmasa;

import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.FormatStyle;

import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TarkihMasa {

    /*


    Note:
    1. Instant is new way to store date which is from latest Java.
    2. We are using the threetenabp to make it work because it supports lower Android version.
    3. Instant is store as UTC format. for example: "2019-05-13T14:13:02.291Z"

    4. If you want to display back by using method convertInstant2LocalTime().
    5. You can change how to display by change the 'ofPattern' at variable DateTimeFormatter formatter.




    ThreeTen Android Backport
    Copyright (C) 2015 Jake Wharton

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.




     */

    /* Date UTC Static */
    //TODO: so we can change how to display the date by using ofPattern, just change this one.
    private static DateTimeFormatter formatter = DateTimeFormatter
            /* You can change as ofPatter or ofLocalizedDateTime

            Example:
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss")  //or
            .ofPattern("HH:mm:ssa dd/MM/yyyy")   //or
            .ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)

             */
            .ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)   //Using the system type
            .withLocale(Locale.US);

    public TarkihMasa() {
    }

    /* Date UTC Static Method */
    public static String convertInstant2LocalTime(String tarikhMasa) {
        //We get the value from the database (date) which is from Instant
        return Instant.parse(tarikhMasa)
                .atZone(ZoneId.systemDefault())
                .format(formatter);
    }

    public static String convertInstant2LocalTimePattern(String tarikhMasa, String pattern) {
        //We get the value from the database (date) which is from Instant
        return Instant.parse(tarikhMasa)
                .atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern(pattern).withLocale(Locale.US));
    }

    public static String getTarikhMasa() {
        Instant instant = Instant.now();
        return instant.toString();
    }

    /*
    This method is to display ago.
    Example: 3 minutes ago.
    I already implement the latest which is including the Instant.
    Convert from String to Instant and then parse to Date.
     */
    public static String convertTimeToAgo(final String tarikhMasa, final String language) {
        //Initialize
        String conversionTime = null;
        Date pastTime;

        //Parse from String (which is stored as Instant.now().toString()
        //And then convert to become Date
        pastTime = DateTimeUtils.toDate(Instant.parse(tarikhMasa));

        //Today date
        Date nowTime = new Date();

        long dateDiff = nowTime.getTime() - pastTime.getTime();
        long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
        long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
        long hour = TimeUnit.MILLISECONDS.toHours(dateDiff);
        long day = TimeUnit.MILLISECONDS.toDays(dateDiff);

        switch (language) {
            case "English":
                conversionTime = conversionToString(null, "Ago", second, minute, hour, day,
                        " Second ", " Minute ", " Hour ", " Day ", " Week ", " Month ", " Year ");
                break;
            case "Malay":
                conversionTime = conversionToString(null, "Yang Lalu", second, minute, hour, day,
                        " Saat ", " Minit ", " Jam ", " Hari ", " Minggu ", " Bulan ", " Tahun ");
                break;
            default:
                break;
        }
        return conversionTime;
    }

    private static String conversionToString(String conversionTime, final String suffix,
                                             final long second, final long minute, final long hour, final long day,
                                             final String sSecond, final String sMinute, final String sHour, final String sDay, final String sWeek, final String sMonth, final String sYear) {
        if (second < 60) {
            conversionTime = second + sSecond + suffix;
        } else if (minute < 60) {
            conversionTime = minute + sMinute + suffix;
        } else if (hour < 24) {
            conversionTime = hour + sHour + suffix;
        } else if (day >= 7) {
            if (day > 30) {
                conversionTime = (day / 30) + sMonth + suffix;
            } else if (day > 360) {
                conversionTime = (day / 360) + sYear + suffix;
            } else {
                conversionTime = (day / 7) + sWeek + suffix;
            }
        } else if (day < 7) {
            conversionTime = day + sDay + suffix;
        }
        return conversionTime;
    }
}

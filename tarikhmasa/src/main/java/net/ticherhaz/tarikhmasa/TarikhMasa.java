package net.ticherhaz.tarikhmasa;

import android.app.Application;
import android.text.format.DateUtils;

import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.Duration;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.FormatStyle;

import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TarikhMasa {

    /*
    TarikhMasa
    https://github.com/ticherhaz/tarikhmasa
    Copyright (C) 2015 Ticherhaz

    Introduction: First of all, thanks to Jake Wharton (https://github.com/JakeWharton/ThreeTenABP) for the ThreeTenABP
    and thanks to Basil Bourque (https://github.com/basil-bourque) for the clean code.

    Note:
    1. Instant is new way to store date which is from latest Java.
    2. We are using the threetenabp to make it work because it supports lower Android version.
    3. Instant is store as UTC format. for example: "2019-05-13T14:13:02.291Z"

    4. If you want to display back by using method ConvertTarikhMasa2LocalTime().
    5. You can change how to display by change the 'ofPattern' at variable DateTimeFormatter formatter.

    HOW TO USE? go here: https://github.com/ticherhaz/tarikhmasa
    Refer to README.md

    ************************************************************************

    ThreeTen Android Backport
    https://github.com/JakeWharton/ThreeTenABP
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

    ************************************************************************

    TarikhMasa
    https://github.com/ticherhaz/tarikhmasa
    Copyright (C) 2015 Ticherhaz

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

    /**
     * Explanation:
     * We use DateTimeFormatter instead SimpleDateFormat because it is
     * the latest Java and better to use.
     * We set the pattern as localized and with localeUS
     *
     * @since 4/6/2019 3:51AM GMT+8
     */
    private static DateTimeFormatter formatter = DateTimeFormatter

            /* You can change as ofPatter or ofLocalizedDateTime

            Example:
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss")  //or
            .ofPattern("HH:mm:ssa dd/MM/yyyy")   //or
            .ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT) //System type

             */

            .ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)
            .withLocale(Locale.US);

    /**
     * ##              IMPORTANT                ##
     * <p>
     * >>>>>>>This method need to be done first!<<<<<<<<<<<<
     * <p>
     * Explanation:
     * To use the Instant.now(), we need to use the AndroidThreeTenABP,
     * I will call the method from there inside here and then can access to the Application
     * <p>
     * How to use:
     * 1. Make a new class named "MyApplication.java"
     * 2. Generate class "onCreate"...
     * 3. Call this method after the super().
     *
     * @param application will be send to the original library of AndroidThreeTenABP
     * @since 6/6/2019 10:31PM GMT+8
     */
    public static void AndroidThreeTenBP(Application application) {
        AndroidThreeTen.init(application);
    }

    /**
     * Explanation:
     * We get the value of tarikhMasa which is UTC from the database or anything which is before the date now
     * and then we convert it to local time according where we live for example here is GMT+8
     *
     * @param tarikhMasa is the date, time and timezone as String
     * @return localized date and time not customized
     */
    public static String ConvertTarikhMasa2LocalTime(String tarikhMasa) {
        //We get the value from the database (date) which is from Instant
        return Instant.parse(tarikhMasa)
                .atZone(ZoneId.systemDefault())
                .format(formatter);
    }

    /**
     * @param tarikhMasa is the date, time and timezone as String
     * @param pattern    is for formatter syntax for date time
     * @return localized date and time that has been customized
     */
    public static String ConvertTarikhMasa2LocalTimePattern(String tarikhMasa, String pattern) {
        //We get the value from the database (date) which is from Instant
        return Instant.parse(tarikhMasa)
                .atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern(pattern).withLocale(Locale.US));
    }

    /**
     * Explanation:
     * This code get the current date time and timezone in ISO 8601
     * and stored as String.
     *
     * @return current Date Time and Timezone
     * @since 4/6/2019 3:00AM GMT+8
     **/
    public static String getTarikhMasa() {
        Instant instant = Instant.now();
        return instant.toString();
    }

    /**
     * Explanation:
     * This is where we want to convert to become 2 minutes ago.
     * The code is prepared with 3 parameters.
     * <p>
     * 1. language: comes with "EN" stands for English and "MY" stands for Malay
     * 2. isJustNow: depends on us if we want to use it or stick with 0 second ago
     *
     * @param tarikhMasa is the date, time and timezone as String
     * @param language   choose the language EN : English or MY : Malay
     * @param isJustNow  check whether to use the just now as string or 0 second ago
     * @return conversion of time ago
     * @since 5/6/2019 10:01PM GMT+8
     */
    public static String get(final String tarikhMasa, final String language, final boolean isJustNow) {

        //Get Instant for tarikhMasa (before) and right now.
        Instant instantNow = Instant.now();
        Instant instantBefore = Instant.parse(tarikhMasa);

        /*
         * Explanation:
         * We are using Duration to compare the value from 2 different date and time.
         * Duration is from the ThreeTenABP
         * It is the latest method that we can use.
         *
         * After we changed it, we divided them to second, minute, hour and day.
         *
         * @since 5/6/2019 11:21PM GMT+8
         *
         */
        Duration duration = Duration.between(instantBefore, instantNow);

        //Variable
        String conversionTime = null;
        long second = duration.getSeconds();
        long minute = duration.toMinutes();
        long hour = duration.toHours();
        long day = duration.toDays();

        /*
         * Explanation:
         * Making display for the today, yesterday and so on by using DateTimeUtils library.
         * First, need to convert from Instant to become Date and get the Millis.
         *
         * @since 5/6/2019 12:40PM GMT+8
         */
        Date dateBefore = DateTimeUtils.toDate(Instant.parse(tarikhMasa));

        long dateMillis = TimeUnit.MILLISECONDS.toMillis(dateBefore.getTime());
        CharSequence relativeToday = DateUtils.getRelativeTimeSpanString
                (
                        dateMillis,
                        System.currentTimeMillis(),
                        DateUtils.DAY_IN_MILLIS
                );
        /*
         ***************** END ****************
         */

        /*
         * Explanation:
         * Convert the relativeToday to another language.
         * (usage of DateUtils.getRelativeTimeSpanString)
         *
         * @since 5/6/2019 12:38PM GMT+8
         */
        if (language.equals("MY")) {
            if (relativeToday.equals("Today")) {
                relativeToday = "Hari ini";
            } else if (relativeToday.equals("Yesterday")) {
                relativeToday = "Semalam";
            } else {
                relativeToday = "";
            }
        }
        if (language.equals("EN")) {
            if (relativeToday.equals("Today")) {
                relativeToday = "Today";
            } else if (relativeToday.equals("Yesterday")) {
                relativeToday = "Yesterday";
            } else {
                relativeToday = "";
            }
        }
        /*
         ***************** END ****************
         */

        //Convert tarikhMasa divided to time and date
        final String beforeTime = ConvertTarikhMasa2LocalTimePattern(tarikhMasa, "h:mma");
        final String beforeDate = ConvertTarikhMasa2LocalTimePattern(tarikhMasa, "d MMM");

        //Checking for second
        if (second < 60) {
            //Usage of just now
            if (isJustNow) {
                //Checking for language chose
                if (language.equals("EN"))
                    conversionTime = "Just now";
                if (language.equals("MY"))
                    conversionTime = "Sebentar tadi";
            } else {
                //Checking for language chose
                if (language.equals("EN")) {
                    if (second == 0 || second == 1)
                        conversionTime = second + " second ago";
                    else
                        conversionTime = second + " seconds ago";
                }
                if (language.equals("MY"))
                    conversionTime = second + " saat yang lalu";
            }
        }
        //Checking for minute
        else if (minute < 60) {
            //Checking for language chose
            if (language.equals("EN")) {
                if (minute == 1)
                    conversionTime = minute + " minute ago";
                else
                    conversionTime = minute + " minutes ago";
            }
            if (language.equals("MY"))
                conversionTime = minute + " minit yang lalu";
        }
        //Checking for hour
        else if (hour < 24) {
            //Checking for language chose
            if (language.equals("EN")) {
                if (hour == 1)
                    conversionTime = relativeToday + ", " + hour + " hour ago, " + beforeTime;
                else
                    conversionTime = relativeToday + ", " + hour + " hours ago, " + beforeTime;
            }
            if (language.equals("MY"))
                conversionTime = relativeToday + ", " + hour + " jam yang lalu, " + beforeTime;
        }
        //Checking for day
        else if (day < 7) {
            //Checking for language chose
            if (language.equals("EN")) {
                if (day == 1)
                    conversionTime = relativeToday + ", " + beforeTime + ", " + beforeDate;
                else
                    conversionTime = day + " days ago, " + beforeTime + ", " + beforeDate;
            }
            if (language.equals("MY")) {
                if (day == 1)
                    conversionTime = relativeToday + ", " + beforeTime + ", " + beforeDate;
                else
                    conversionTime = day + " hari yang lalu, " + beforeTime + ", " + beforeDate;
            }
        }
        //When the day above 7 days, it will display only time and date
        else
            conversionTime = beforeTime + ", " + beforeDate;

        return conversionTime;
    }
}
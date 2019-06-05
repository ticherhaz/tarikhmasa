package net.ticherhaz.tarikhmasa;

import android.content.Context;
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


    Note:
    1. Instant is new way to store date which is from latest Java.
    2. We are using the threetenabp to make it work because it supports lower Android version.
    3. Instant is store as UTC format. for example: "2019-05-13T14:13:02.291Z"

    4. If you want to display back by using method convertInstant2LocalTime().
    5. You can change how to display by change the 'ofPattern' at variable DateTimeFormatter formatter.




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

    public TarikhMasa() {
    }

    public static void AndroidThreeTenBPCustom(Context context) {
        AndroidThreeTen.init(context);
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
     * This code get the current date time and timezone in ISO 8601
     * and stored as String.
     *
     * @return current Date Time and Timezone
     * @since 4/6/2019 3.33PM GMT+8
     **/
    public static String convertTime2AgoDateUtils(long pastTimeMillis) {
        CharSequence relativeDate =
                DateUtils.getRelativeTimeSpanString(pastTimeMillis,
                        System.currentTimeMillis(),
                        DateUtils.DAY_IN_MILLIS,
                        DateUtils.FORMAT_NUMERIC_DATE);
        return String.valueOf(relativeDate);
    }


    private static String conversionToString(final String language, final long pastTime, final String tarikhMasa, final String tarikhMasaSekarang, String conversionTime, final String suffix,
                                             final long second, final long minute, final long hour, final long day,
                                             final String sSecond, final String sJustNow, final boolean isJustNow, final String sMinute, final String sHour, final String sDay, final String sWeek, final String sMonth, final String sYear,
                                             final String sToday, final String sYesterday) {


        /*
         * Explanation:
         * get the String of the today, yesterday, 2 days ago and so on
         *
         * @since 5/6/2019 12:40PM GMT+8
         */
        //Making Day display
        CharSequence relativeToday = DateUtils.getRelativeTimeSpanString
                (
                        pastTime,
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
                relativeToday = "Hari ini, ";
            } else if (relativeToday.equals("Yesterday")) {
                relativeToday = "Semalam, ";
            } else {
                relativeToday = "";
            }
        }
        if (language.equals("EN")) {
            if (relativeToday.equals("Today")) {
                relativeToday = "Today, ";
            } else if (relativeToday.equals("Yesterday")) {
                relativeToday = "Yesterday, ";
            } else {
                relativeToday = "";
            }
        }
        /*
         ***************** END ****************
         */


        /*
         * Explanation:
         * This is conversion time for SECOND
         * It will check whether true of false if developer want to use just now instead using second ago
         * After that, check the language
         * @since 5/6/2019 1:05PM GMT+8
         */
        if (second < 60) {
            if (isJustNow) {
                //Output: Just Now
                conversionTime = sJustNow + ", ";
            }

            if (!isJustNow) {
                if (second == 0 || second == 1) {
                    //Output: 0 Second Ago or 1 Second Ago
                    conversionTime = second + " " + sSecond + " " + suffix + ", ";
                } else {
                    //Output: 2 Seconds Ago ++
                    if (sSecond.equals(" second")) {
                        conversionTime = second + " seconds " + suffix + ", ";
                    }
                    if (sSecond.equals(" saat")) {
                        conversionTime = second + sSecond + " " + suffix + ", ";
                    }

                }
            }
        }
        /*
         *
         *
         *
         *
         */
        else if (minute < 60) {
            if (language.equals("EN")) {
                if (minute == 1)
                    conversionTime = minute + " " + sMinute + " " + suffix + ", ";
                else
                    conversionTime = minute + " minutes " + suffix + ", ";
            } else if (language.equals("MY"))
                conversionTime = minute + " " + sMinute + " " + suffix + ", ";
        }
        /*
         *
         *
         *
         *
         */
        else if (hour < 24) {
            conversionTime = hour + " " + sHour + " " + suffix + ", ";
        }
        /*
         *
         *
         *
         *
         *
         */
        else if (day >= 7) {

            if (day > 30) {
                if ((day / 30) == 1) {
                    conversionTime = (day / 30) + sMonth + suffix;
                } else {
                    if (sWeek.equals(" month")) {
                        conversionTime = (day / 30) + " months" + suffix;
                    }

                    if (sWeek.equals(" month")) {
                        conversionTime = (day / 30) + sMonth + suffix;
                    }
                }

            } else {

                conversionTime = (day / 7) + sWeek + suffix;
            }


        } else {

            if (day == 1 || day == 2) {
                // conversionTime = sYesterday + day + sDay + suffix;
                conversionTime = "";
            } else {
                conversionTime = day + sDay + suffix;
            }

        }
        return relativeToday + conversionTime;

    }

    /*
    This method is to display ago.
    Example: 3 minutes ago.
    I already implement the latest which is including the Instant.
    Convert from String to Instant and then parse to Date.
     */
    public static String convertTime2Ago(final String tarikhMasa, final String language, final boolean isJustNow) {
        //Initialize
        String conversionTime = null;
        Date pastTime, nowTime2;

        //Parse from String (which is stored as Instant.now().toString()
        //And then convert to become Date
        pastTime = DateTimeUtils.toDate(Instant.parse(tarikhMasa));

        //Convert to get the time
        final String pastTimePattern = convertInstant2LocalTimePattern(tarikhMasa, " h:mma");
        final String pastDatePattern = convertInstant2LocalTimePattern(tarikhMasa, " d MMM");

        //or we using like this one
        nowTime2 = DateTimeUtils.toDate(Instant.parse(getTarikhMasa()));

        long pastTimeMillis = TimeUnit.MILLISECONDS.toMillis(pastTime.getTime());

        long dateDiff = nowTime2.getTime() - pastTime.getTime();
        long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
        long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
        long hour = TimeUnit.MILLISECONDS.toHours(dateDiff);
        long day = TimeUnit.MILLISECONDS.toDays(dateDiff);

        switch (language) {
            case "EN":
                conversionTime = conversionToString("EN", pastTimeMillis, tarikhMasa, getTarikhMasa(), "", "ago", second, minute, hour, day,
                        "second", "just now", isJustNow, "minute", "hour", "day", "week", "month", "year",
                        "today", "yesterday");
                break;
            case "MY":
                conversionTime = conversionToString("MY", pastTimeMillis, tarikhMasa, getTarikhMasa(), "", "yang lalu", second, minute, hour, day,
                        "saat", "sebentar tadi", isJustNow, "minit", "jam", "hari", "minggu", "bulan", "tahun",
                        "hari ini", "semalam");
                break;
            default:
                break;
        }
        return conversionTime + pastTimePattern + ", " + pastDatePattern;
    }

    /**
     * @param tarikhMasa
     * @param language
     * @param isJustNow
     * @return conversion of time ago
     * @since 5/6/2019 10:01PM GMT+8
     */
    public static String nMethod(final String tarikhMasa, final String language, final boolean isJustNow) {
        Instant instantNow = Instant.now();
        Instant instantBefore = Instant.parse(tarikhMasa);

        //Use Duration
        Duration duration = Duration.between(instantBefore, instantNow);

        //Variable
        String conversionTime = null;
        long second = duration.getSeconds();
        long minute = duration.toMinutes();
        long hour = duration.toHours();
        long day = duration.toDays();

        /*
         * Explanation:
         * get the String of the today, yesterday, 2 days ago and so on
         *
         * @since 5/6/2019 12:40PM GMT+8
         */
        //Making Day display
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


        //Convert to get the time
        final String beforeTime = convertInstant2LocalTimePattern(tarikhMasa, "h:mma");
        final String beforeDate = convertInstant2LocalTimePattern(tarikhMasa, "d MMM");


        //Checking for second
        if (second < 60) {

            //Usage of just now
            if (isJustNow) {

                if (language.equals("EN")) {
                    conversionTime = "Just now";
                }

                if (language.equals("MY")) {
                    conversionTime = "Sebentar tadi";
                }

            } else {

                if (language.equals("EN")) {
                    if (second == 0 || second == 1) {
                        conversionTime = second + " second ago";
                    } else {
                        conversionTime = second + " seconds ago";
                    }
                }

                if (language.equals("MY")) {
                    conversionTime = second + " saat yang lalu";
                }


            }

        } else if (minute < 60) {

            if (language.equals("EN")) {
                if (minute == 1) {
                    conversionTime = minute + " minute ago";
                } else {
                    conversionTime = minute + " minutes ago";
                }
            }

            if (language.equals("MY")) {
                conversionTime = minute + " minit yang lalu";
            }

        } else if (hour < 24) {

            if (language.equals("EN")) {

                if (hour == 1) {
                    conversionTime = relativeToday + ", " + hour + " hour ago, " + beforeTime;
                } else {
                    conversionTime = relativeToday + ", " + hour + " hours ago, " + beforeTime;
                }

            }

            if (language.equals("MY")) {
                conversionTime = relativeToday + ", " + hour + " jam yang lalu, " + beforeTime;
            }

        } else if (day < 7) {

            if (language.equals("EN")) {
                if (day == 1) {

                    conversionTime = relativeToday + ", " + beforeTime + ", " + beforeDate;

                } else {
                    conversionTime = day + " days ago, " + beforeTime + ", " + beforeDate;
                }
            }

            if (language.equals("MY")) {

                if (day == 1) {
                    conversionTime = relativeToday + ", " + beforeTime + ", " + beforeDate;

                } else {

                    conversionTime = day + " hari yang lalu, " + beforeTime + ", " + beforeDate;
                }
            }

        }

        //After too many days
        else {
            conversionTime = beforeTime + ", " + beforeDate;
        }
        return conversionTime;
    }
}

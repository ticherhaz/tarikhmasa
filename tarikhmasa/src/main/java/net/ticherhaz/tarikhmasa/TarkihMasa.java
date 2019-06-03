package net.ticherhaz.tarikhmasa;

import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;

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
     */

    /* Date UTC Static */
    //TODO: so we can change how to display the date by using ofPattern, just change this one.
    private static DateTimeFormatter formatter = DateTimeFormatter
            /* You can change as ofPatter or ofLocalizedDateTime

            Example:
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            .ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)

             */
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            .withLocale(Locale.US);

    public TarkihMasa() {
    }

    /* Date UTC Static Method */
    public static String convertInstant2LocalTime(String instant) {
        //We get the value from the database (date) which is from Instant
        return Instant.parse(instant)
                .atZone(ZoneId.systemDefault())
                .format(formatter);
    }

    public static String getInstantNow() {
        Instant instant = Instant.now();
        return instant.toString();
    }

    /*
    This method is to display ago.
    Example: 3 minutes ago.
    I already implement the latest which is including the Instant.
    Convert from String to Instant and then parse to Date.
     */
    public static String convertTimeToAgo(String instant) {
        //Initialize
        String conversionTime = null;
        String suffix = "Yang Lalu";
        Date pastTime;

        //Parse from String (which is stored as Instant.now().toString()
        //And then convert to become Date
        pastTime = DateTimeUtils.toDate(Instant.parse(instant));

        //Today date
        Date nowTime = new Date();

        long dateDiff = nowTime.getTime() - pastTime.getTime();
        long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
        long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
        long hour = TimeUnit.MILLISECONDS.toHours(dateDiff);
        long day = TimeUnit.MILLISECONDS.toDays(dateDiff);

        if (second < 60) {
            conversionTime = second + " Saat " + suffix;
        } else if (minute < 60) {
            conversionTime = minute + " Minit " + suffix;
        } else if (hour < 24) {
            conversionTime = hour + " Jam " + suffix;
        } else if (day >= 7) {
            if (day > 30) {
                conversionTime = (day / 30) + " Bulan " + suffix;
            } else if (day > 360) {
                conversionTime = (day / 360) + " Tahun " + suffix;
            } else {
                conversionTime = (day / 7) + " Minggu " + suffix;
            }
        } else if (day < 7) {
            conversionTime = day + " Hari " + suffix;
        }
        return conversionTime;
    }
}

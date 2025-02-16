TarikhMasa 2.0

ANDROID STUDIO LADYBUG

Updated:
(11/02/2025)
1. Updated dependencies
2. Using Java 21
3. Min SDK 21

Updated:
(04/05/2021)
1. Updated dependencies

Updated:
(03/04/2021)
1. Moved to Kotlin
1. Updated dependencies

Updated:
(12/06/2020)
1. Updated dependencies

Updated:
(3/10/2019)
1. Added new method `ConvertCustomDate_ddMMyyyy_2TarikhMasa`

Updated:
(11/9/2019)
1. Fixed the negative day and updates gradle.

(21/8/2019)
1. Update to Android 3.5.

(14/7/2019)
1. Support AndroidX.
2. Adding convert timestamp to TarikhMasa.
3. Fix to get precise value of day for GetTarikhMasaTimeAgo method.

Note: Support API15 and above.
Tutorial Video: https://youtu.be/_Jr89NzGmig

# Initial


Step 1. Add the JitPack repository to your build file

gradle
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	    ...
		implementation 'com.github.ticherhaz:tarikhmasa:1.5.14'
	}

Step 3: Create new class "MyApplication.java"

	...
	import static net.ticherhaz.tarikhmasa.TarikhMasa.AndroidThreeTenBP;
	
	public class MyApplication extends Application {
    
    	@Override
    	public void onCreate() {
        	super.onCreate();
       	    AndroidThreeTenBP(this);
		}
	}

Step 4: Add android:name in AndroidManifest.xml

	<application
	...
	android:name=".MyApplication">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

# List Methods

	public static String GetTarikhMasa()
	public static String ConvertTarikhMasa2LocalTime(final String tarikhMasa)
	public static String ConvertTarikhMasa2LocalTimePattern(final String tarikhMasa, final String pattern)
	public static String GetTarikhMasaTimeAgo(final String tarikhMasa, final String language, final boolean isJustNow, final boolean onlyTodayYesterday)
	public static String ConvertTimeStamp2TarikhMasa(final long timestamp)
	public static String ConvertCustomDate_ddMMyyyy_2TarikhMasa(final String date)

# Usage

No 1: Get the current time UTC (tarikhMasa)

    ...
	import static net.ticherhaz.tarikhmasa.TarikhMasa.GetTarikhMasa;
	
	textViewDate.setText(GetTarikhMasa());
	
Output:

	2019-06-05T17:38:44.079Z

No 2: Convert from tarikhMasa to Local time

    ...
	import static net.ticherhaz.tarikhmasa.TarikhMasa.ConvertTarikhMasa2LocalTime;
	
	textViewDate.setText(ConvertTarikhMasa2LocalTime(GetTarikhMasa()));

Output:

	June 6, 2019 1:41 AM

~another example

	textViewDate.setText(ConvertTarikhMasa2LocalTime("2019-06-02T17:38:44.079Z"));
	
Output:

	June 3, 2019 1:38 AM

No 3: Convert from tarikhMasa to Local time by Pattern (Customize yours!)

Note: More pattern, can go here http://tutorials.jenkov.com/java-internationalization/simpledateformat.html

    ...
	import static net.ticherhaz.tarikhmasa.TarikhMasa.ConvertTarikhMasa2LocalTimePattern;
	import static net.ticherhaz.tarikhmasa.TarikhMasa.GetTarikhMasa;
	
	textViewDate.setText(ConvertTarikhMasa2LocalTimePattern(GetTarikhMasa(), "dd/MMM/yyyy"));
	
Output:

	06/Jun/2019

No 4: Get Time Ago (example, 3 minutes ago)

    ...
	import static net.ticherhaz.tarikhmasa.TarikhMasa.GetTarikhMasaTimeAgo;
	
	textViewDate.setText(GetTarikhMasaTimeAgo("2019-06-05T17:38:44.079Z", "EN", true));

Output:

	15 minutes ago

~another example

	textViewDate.setText(GetTarikhMasaTimeAgo("2019-06-04T17:38:44.079Z", "MY", true));

Output:

	Semalam, 1:38AM, 5 Jun

No 4: Convert Timestamp to TarikhMasa

    ...
	import static net.ticherhaz.tarikhmasa.TarikhMasa.ConvertTimeStamp2TarikhMasa;
	
	textViewDate.setText(ConvertTimeStamp2TarikhMasa(Long.parseLong("1563089349272"));

Output:

	2019-07-14T11:43:44.079Z

No 4: Convert Custom Date (dd/MM/yyyy) to TarikhMasa

    ...
	import static net.ticherhaz.tarikhmasa.TarikhMasa.ConvertCustomDate_ddMMyyyy_2TarikhMasa;
	
	 String dateCustom = "31/12/2019";
	 String tarikhMasaCustomDate = ConvertCustomDate_ddMMyyyy_2TarikhMasa(dateCustom);

Output:

	2019-12-31T00:00:00.000Z



# More Information

Go to the TarikhMasa.class more details about explanation.

How to get to that class?
1. Hold 'Ctrl'
2. Click the method : example, GetTarikhMasa()
3. It will go to the TarikhMasa.class

# Question?

Email me at ticherhaz@gmail.com

    ************************************************************************

    TarikhMasa
    https://github.com/ticherhaz/tarikhmasa
    Copyright (C) 2019 Ticherhaz

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

# License

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
    Copyright (C) 2019 Ticherhaz

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

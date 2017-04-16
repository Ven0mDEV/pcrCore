package net.brightblack9911.pcrCore.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StringUtils
{
	public static String getDurationBreakdown(long millis) {
		if (millis == 0) {
			return "0";
		}

		long days = TimeUnit.MILLISECONDS.toDays(millis);
		if (days > 0) {
			millis -= TimeUnit.DAYS.toMillis(days);
		}

		long hours = TimeUnit.MILLISECONDS.toHours(millis);
		if (hours > 0) {
			millis -= TimeUnit.HOURS.toMillis(hours);
		}

		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		if (minutes > 0) {
			millis -= TimeUnit.MINUTES.toMillis(minutes);
		}

		long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
		if (seconds > 0) {
			millis -= TimeUnit.SECONDS.toMillis(seconds);
		}

		StringBuilder sb = new StringBuilder();

		if (days > 0) {
			sb.append(days + " ");
			long i = days;

			if (i == 1) {
				sb.append(" dzien ");
			} else {
				sb.append(" dni ");
			}
		}
		if (hours > 0) {
			sb.append(hours);
			long i = hours;

			if (i == 1) {
				sb.append(" godzine ");
			} else if (i < 5) {
				sb.append(" godziny ");
			} else {
				sb.append(" godzin ");
			}
		}
		if (minutes > 0) {
			sb.append(minutes);
			long i = minutes;

			if (i == 1) {
				sb.append(" minute ");
			} else if (i < 5) {
				sb.append(" minuty ");
			} else {
				sb.append(" minut ");
			}
		}
		if (seconds > 0) {
			sb.append(seconds);
			long i = seconds;

			if (i == 1) {
				sb.append(" sekunde ");
			} else if (i < 5) {
				sb.append(" sekundy ");
			} else {
				sb.append(" sekund ");
			}
		}

		return (sb.replace(sb.length() - 1, sb.length(), "").toString());
	}
	
    public static String toString(List<String> list, boolean send) {
        StringBuilder sb = new StringBuilder();

        for (String s : list) {
            sb.append(s);
            sb.append(',');
            if (send) {
                sb.append(' ');
            }
        }

        String s = sb.toString();

        if (send) {
            if (s.length() > 2) {
                s = s.substring(0, s.length() - 2);
            }
            else if (s.length() > 1) {
                s = s.substring(0, s.length() - 1);
            }
        }

        return s;
    }

    public static List<String> fromString(String s) {
        List<String> list = new ArrayList<>();

        if (s == null || s.isEmpty()) {
            return list;
        }

        list = Arrays.asList(s.split(","));
        return list;
    }

}

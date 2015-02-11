package lumaceon.mods.clockworkphase.util;

public class TimeSandParser
{
    //1 time sand = 1 second
    //60 time sand = 1 minute
    //3,600 time sand = 1 hour
    //86,400 time sand = 1 day
    //604,800 time sand = 1 week (Not shown; it's cleaner to just go straight to days)
    //2,592,000 time sand = 1 month
    //31,104,000 time sand = 1 year
    //311,040,000 time sand = 1 decade (Not shown; too large to be realistic)
    //3,110,400,000 time sand = 1 century (Not shown; WAY too large to be realistic)
    public static String getStringForRenderingFromTimeSand(int timeSand)
    {
        TimeSandMode mode;
        if(timeSand >= 31104000)
        {
            mode = TimeSandMode.YEAR;
        }
        else if(timeSand >= 2592000)
        {
            mode = TimeSandMode.MONTH;
        }
        else if(timeSand >= 86400)
        {
            mode = TimeSandMode.DAY;
        }
        else if(timeSand >= 3600)
        {
            mode = TimeSandMode.HOUR;
        }
        else if(timeSand >= 60)
        {
            mode = TimeSandMode.MINUTE;
        }
        else if(timeSand > 0)
        {
            mode = TimeSandMode.SECOND;
        }
        else
        {
            return "No Time";
        }
        double timeSandD = (double)timeSand;
        int numberOfYears, numberOfMonths, numberOfDays, numberOfHours, numberOfMinutes, numberOfSeconds;
        switch(mode)
        {
            case YEAR:
                numberOfYears = (int)(timeSandD / 31104000.0);
                numberOfMonths = (int)((timeSandD % 31104000.0) / 2592000.0);
                return numberOfYears + "Yrs " + numberOfMonths + "Months";
            case MONTH:
                numberOfMonths = (int)(timeSandD / 2592000.0);
                numberOfDays = (int)((timeSandD % 2592000.0) / 86400.0);
                return numberOfMonths + "Months " + numberOfDays + "Days";
            case DAY:
                numberOfDays = (int)(timeSandD / 86400.0);
                numberOfHours = (int)((timeSandD % 86400.0) / 3600.0);
                return numberOfDays + "Days " + numberOfHours + "Hrs";
            case HOUR:
                numberOfHours = (int)(timeSandD / 3600.0);
                numberOfMinutes = (int)((timeSandD % 3600.0) / 60.0);
                return numberOfHours + "Hrs " + numberOfMinutes + "Mins";
            case MINUTE:
                numberOfMinutes = (int)(timeSandD / 100.0);
                numberOfSeconds = (int)(timeSandD % 100.0);
                return numberOfMinutes + "Mins " + numberOfSeconds + "Secs";
            case SECOND:
                return timeSand + " Seconds";
        }
        return "Natural Time";
    }

    public enum TimeSandMode
    {
        YEAR, MONTH, DAY, HOUR, MINUTE, SECOND
    }
}


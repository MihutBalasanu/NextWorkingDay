import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class NextWorkingDay {

    private static String[] permanentLegalDays = {
            "01.01", "02.01", "24,01", "01.06", "15.08", "30.11", "01.12", "25.12", "26.12" //...toate zilele legale fixe
    };


    public static Calendar getOrthodoxEaster(int myear) {
        Calendar dof = Calendar.getInstance();

        int r1 = myear % 4;
        int r2 = myear % 7;
        int r3 = myear % 19;
        int r4 = (19 * r3 + 15) % 30;
        int r5 = (2 * r1 + 4 * r2 + 6 * r4 + 6) % 7;
        int mdays = r5 + r4 + 13;

        if (mdays > 39) {
            mdays = mdays - 39;
            dof.set(myear, 4, mdays);
        } else if (mdays > 9) {
            mdays = mdays - 9;
            dof.set(myear, 3, mdays);
        } else {
            mdays = mdays + 22;
            dof.set(myear, 2, mdays);
        }
        return dof;

    }

    public Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public boolean isLegalHoliday(Date date) {
        List<String> allLegalDays = getLegalHolidaysForYear(getYearOfDate(date));
        String checkedDateLiteral = toStringDayAndMonth(date);
        return allLegalDays.contains(checkedDateLiteral);
    }

    private int getYearOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    private List<String> getLegalHolidaysForYear(int year) {
        List<String> allLegalDays = Arrays.asList(permanentLegalDays);
        Calendar easter = getOrthodoxEaster(year);
        Date easterSunday = (Date)easter.getTime().clone();
        easter.add(Calendar.DATE, 1);
        Date easterMonday = easter.getTime();
        allLegalDays.add(toStringDayAndMonth(easterSunday));
        allLegalDays.add(toStringDayAndMonth(easterMonday));

        return allLegalDays;
    }

    private String toStringDayAndMonth(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM");
        return formatter.format(date);
    }
     public Date getNextWorkingDay(Date date) {
         Date nextWorkingDay;
         Calendar cal = Calendar.getInstance();
         cal.setTime(date);

         if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            nextWorkingDay = addDays(date, 3);
        } else {
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                nextWorkingDay = addDays(date, 2);
            } else {
                nextWorkingDay = addDays(date, 1);
            }
            while (!isLegalHoliday(nextWorkingDay)) {
                nextWorkingDay = addDays(nextWorkingDay, 1);
            }
        }
        return nextWorkingDay;
    }
}

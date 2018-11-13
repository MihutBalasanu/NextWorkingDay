import java.util.Calendar;
import java.util.Date;

public class NextWorkingDay {


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

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();

    }

    public static boolean testDay(Date date) {
        boolean testDay = true;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        if(day == 1 && month == 0){
            testDay = false;
        }
        if(day == 2 && month == 0){
            testDay = false;
        }
        if(day == 24 && month == 0){
            testDay = false;
        }
        Date easterSundayDate = getOrthodoxEaster(year).getTime();
        if(date == addDays(easterSundayDate,1)){
            testDay = false;
        }
        if(day == 1 && month == 4){
            testDay = false;
        }
        if(date == addDays(easterSundayDate,50)) {
            testDay = false;
        }
        if(day == 15 && month == 7){
            testDay = false;
        }
        if(day == 30 && month == 10){
            testDay = false;
        }
        if(day == 1 && month == 11){
            testDay = false;
        }
        if(day == 25 && month == 11){
            testDay = false;
        }if(day == 26 && month == 11){
            testDay = false;
        }
        return testDay;

    }

     public static Date getNextWorkingDay(Date date) {
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
            while (!testDay(nextWorkingDay)) {
                nextWorkingDay = addDays(nextWorkingDay, 1);
            }
        }
        return nextWorkingDay;
    }
}

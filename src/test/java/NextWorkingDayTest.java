import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class NextWorkingDayTest {

    public boolean isSameDay(Date date1, Date date2){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        return sameDay;
    }

    @Test
    public void getOrthodoxEasterTest() {
        Calendar cal = Calendar.getInstance();
        cal.set(2019, 3, 28);
        Calendar result2019 = NextWorkingDay.getOrthodoxEaster(2019);
        Assert.assertEquals(true,isSameDay(cal.getTime(),result2019.getTime()));


        cal.set(2022, 3, 24);
        Calendar result2022 = NextWorkingDay.getOrthodoxEaster(2022);
        Assert.assertEquals(true,isSameDay(cal.getTime(),result2022.getTime()));


    }
    @Test
    public void addDaysTest(){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.set(2018, 5, 8);
        cal1.add(Calendar.DATE, 4);
        cal2.set(2018,5,12);
        Assert.assertEquals(true,isSameDay(cal2.getTime(),cal1.getTime()));
    }

    @Test
    public void testDayTest() {
        Calendar cal = Calendar.getInstance();
        NextWorkingDay day = new NextWorkingDay();
        cal.set(2019, 0, 1);
        Assert.assertEquals(false, day.isLegalHoliday(cal.getTime()));


//      Rusalii 2021 - luni
        cal.set(2021,5,21);
        Assert.assertEquals(false,day.isLegalHoliday(cal.getTime()));


        cal.set(2019, 7, 15);
        Assert.assertEquals(false, day.isLegalHoliday(cal.getTime()));
    }
    @Test
    public void getNextWorkingDayTest(){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        NextWorkingDay day = new NextWorkingDay();
        cal1.set(2021,3,30);
        cal2.set(2021,4,3);
        Assert.assertEquals(true,isSameDay(cal2.getTime(),day.getNextWorkingDay(cal1.getTime())));
    }
}

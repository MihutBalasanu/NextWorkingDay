import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        NextWorkingDay myDay = new NextWorkingDay();
        System.out.println("Input a date in the format: yyyy/mm/dd.");
        Scanner s = new Scanner(System.in);
        String date = s.nextLine();
        String[] coords = date.split("/");
        Integer year = Integer.valueOf(coords[0]);
        Integer month = Integer.valueOf(coords[1]);
        Integer day = Integer.valueOf(coords[2]);
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        Date myDate = cal.getTime();

        System.out.println("The next working date is: " + myDay.getNextWorkingDay(myDate));
    }
}

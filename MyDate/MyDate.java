

public class MyDate {

    private int day;
    private int month;
    private int year;
    private int dayInWeek;
    private String error = "Invalid year, month, or day!";
    private String[] strMonth = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aut", "Sep", "Oct", "Nov", "Dec"};
    private String[] strDay = {"Monday", "Tuesday", "Wednesday", "Thusday", "Friday", "Saturday", "Sunday"};
    private int[] daysInMonth;
    private int[] daysInMonthLeapYear = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int[] daysInMonthNormalYear = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public void validate() {
        if (month < 0 || month > 11) {
            System.out.println(error);
        }

        if (day < 0 || day > daysInMonth[month]) {
            System.out.println(error);
        }

        if (dayInWeek < 0 || dayInWeek > 6) {
            System.out.println(error);
        }
    }

    public MyDate(int year, int month, int day, int dayInWeek) {
        this.year = year;
        this.month = --month;
        this.day = day;
        this.dayInWeek = --dayInWeek;

        if (isLeapYear() == true) {
            daysInMonth = daysInMonthLeapYear;
        } else {
            daysInMonth = daysInMonthNormalYear;
        }

        validate();
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = --month;
        this.day = day;
        this.dayInWeek = zellerAlg();

        if (isLeapYear() == true) {
            daysInMonth = daysInMonthLeapYear;
        } else {
            daysInMonth = daysInMonthNormalYear;
        }

        validate();
    }

    @Override
    public String toString() {
        return strDay[dayInWeek] + " " + day + ". " + strMonth[month] + " " + year;
    }

    public String nextDay() {
        if (day == daysInMonth[month]) {
            day = 1;
            ++month;
        } else {
            ++day;
        }
        if (dayInWeek == 6) {
            dayInWeek = 0;
        } else {
            ++dayInWeek;
        }

        return toString();
    }

    public String previousDay() {
        if (day == 1) {
            --month;
            day = daysInMonth[month];
        } else {
            --day;
        }
        if (dayInWeek == 0) {
            dayInWeek = 6;
        } else {
            --dayInWeek;
        }

        return toString();
    }

    public boolean isLeapYear() {
        if (year % 4 == 0 && year % 100 != 0) {
            return true;
        } else {
            return false;
        }
    }

    public int zellerAlg() {
        int q = day;
        int m = month;
        int y = year;

        if (m < 2) {    //březen 3, duben 4, ..., leden 13 předchozího roku
            m += 13;
            y -= 1;
        } else {
            m += 1;
        }

        int k = y % 100; //rok století
        int j = y / 100; //století

        int h = (q + (((m + 1) * 26) / 10) + k + (k / 4) + (j / 4)
                + (5 * j)) % 7;

        if (h < 2) {        //sobota 1, neděle 2; 
            h += 5;        
        } else {
            h -= 2;
        }
        return h;
    }
}
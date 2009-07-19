package javi.http.view.applicationobjects;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateRanges {

    public final static int FIRST_DAY = 1;
    public final static int LAST_DAY = 31;
    public final static int FIRST_MONTH = 1;
    public final static int LAST_MONTH = 12;
    public final static int FIRST_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    private List days;
    private List months;
    
    public DateRanges() {    
        days = getRange(FIRST_DAY, LAST_DAY);
        months = getRange(FIRST_MONTH, LAST_MONTH);
    }
    
    public List getDays() {
        return days;
    }
    
    public List getMonths() {
        return months;
    }
    
    public List getYears() {    
        return getRange(FIRST_YEAR, getLastYear());
    }
    
    public int getLastYear() {
        return Calendar.getInstance().get(Calendar.YEAR)+3;
    }
    
    private List getRange(int start, int end) {
    
        List range = new ArrayList();
        
        for (int i=start; i<=end; i++) {
            range.add(new Integer(i));
        }
        
        return range;
    
    }

}

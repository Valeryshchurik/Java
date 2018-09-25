package adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DateAdapter extends XmlAdapter<String, Date> {
    @Override
    public Date unmarshal(String v) throws Exception {
        String[] parts = v.split("-");
        java.util.Date date = new GregorianCalendar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) - 1, Integer.parseInt(parts[2])).getTime();
        return new Date(date.getTime());
    }

    @Override
    public String marshal(Date v) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(v);
    }
}

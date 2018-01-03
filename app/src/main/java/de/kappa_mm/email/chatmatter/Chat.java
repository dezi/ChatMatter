package de.kappa_mm.email.chatmatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@SuppressWarnings("WeakerAccess")
public class Chat
{
    private static final String INTERNALCHATDATESHORT = "yyyyMMddHHmm";
    private static final String INTERNALCHATDATELONG = "yyyyMMddHHmmss";

    private final String chatname;
    private String lastdate;

    public Chat(String chatname)
    {
        this.chatname = chatname;
    }

    public String getChatName()
    {
        return chatname;
    }

    public void setLastDate(String datestring)
    {
        lastdate = datestring;
    }

    public String getLastDate()
    {
        return lastdate;
    }

    public boolean isNewDay(String datestring)
    {
        return (lastdate == null) || ((datestring != null) && ! lastdate.startsWith(datestring.substring(0, 8)));
    }

    public long getTimeStamp()
    {
        if (lastdate == null) return 0;

        try
        {
            SimpleDateFormat df = new SimpleDateFormat((lastdate.length() == 14) ? INTERNALCHATDATELONG : INTERNALCHATDATESHORT, Locale.getDefault());
            df.setTimeZone(TimeZone.getDefault());

            return df.parse(lastdate).getTime();
        }
        catch (ParseException ignore)
        {
            ignore.printStackTrace();
        }

        return 0;
    }

    public String getLocaleDateString()
    {
        DateFormat df = new SimpleDateFormat("dd. MMMM yyyy", Locale.getDefault());
        df.setTimeZone(TimeZone.getDefault());

        return df.format(new Date(getTimeStamp())).toUpperCase();
    }
}

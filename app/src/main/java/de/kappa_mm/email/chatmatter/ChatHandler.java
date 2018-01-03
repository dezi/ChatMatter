package de.kappa_mm.email.chatmatter;

import android.support.annotation.Nullable;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

@SuppressWarnings("WeakerAccess")
public class ChatHandler
{
    private final static String LOGTAG = ChatHandler.class.getSimpleName();

    private final static String intentEntry = "intent.marshalled";

    public static boolean writeToStorage(Context context, Intent intent)
    {
        if (! intent.hasExtra(Intent.EXTRA_SUBJECT)) return false;
        if (! intent.hasExtra(Intent.EXTRA_STREAM)) return false;

        ArrayList list = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (list == null) return false;

        DateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss", Locale.getDefault());
        df.setTimeZone(TimeZone.getDefault());

        String timestamp = df.format(new Date());
        String subject = intent.getStringExtra(Intent.EXTRA_SUBJECT);
        String filename = subject + " " + timestamp;

        filename = "_LastExport";

        File fileZip = new File(context.getFilesDir(), filename + ".zip");
        Log.d(LOGTAG, "writeToFile: zip=" + fileZip);

        try
        {
            ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(fileZip)));

            //
            // Write intent to zip file.
            //

            Parcel parcel = Parcel.obtain();
            intent.writeToParcel(parcel, 0);
            byte[] bytes = parcel.marshall();

            ZipEntry entry = new ZipEntry(intentEntry);
            zip.putNextEntry(entry);

            zip.write(bytes, 0, bytes.length);

            Log.d(LOGTAG, "writeToZip: entry=" + intentEntry + " size=" + bytes.length);

            parcel.recycle();

            //
            // Write attachments to zip file.
            //

            for (Object obj : list)
            {
                if (obj instanceof Uri)
                {

                    try
                    {
                        Uri uri = (Uri) obj;

                        InputStream inputStream = context.getContentResolver().openInputStream(uri);
                        if (inputStream == null) continue;

                        String zipname = uri.getLastPathSegment();
                        entry = new ZipEntry(zipname);
                        zip.putNextEntry(entry);

                        byte data[] = new byte[8192];
                        long total = 0;
                        int xfer;

                        while ((xfer = inputStream.read(data, 0, data.length)) != -1)
                        {
                            zip.write(data, 0, xfer);

                            total += xfer;
                        }

                        Log.d(LOGTAG, "writeToZip: entry=" + zipname + " size=" + total);

                        inputStream.close();
                    }
                    catch (Exception ex)
                    {
                        Log.d(LOGTAG, ex.getMessage());
                    }
                }
            }

            zip.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean readIntentFromStorage(Context context, Intent intent, String filename)
    {
        try
        {
            File zipFilePath = new File(context.getFilesDir(), (filename == null) ? "_LastExport.zip" : filename);

            Log.d(LOGTAG, "readIntentFromStorage: zip=" + zipFilePath);

            ZipFile zipFile = new ZipFile(zipFilePath);

            ZipEntry entry = zipFile.getEntry(intentEntry);
            InputStream entryStream = zipFile.getInputStream(entry);
            byte[] bytes = Simple.getAllInputBytes(entryStream);
            if (bytes == null) return false;

            Log.d(LOGTAG, "readIntentFromStorage: name=" + intentEntry + " bytes=" + bytes.length);

            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);

            intent.readFromParcel(parcel);

            parcel.recycle();
            zipFile.close();

            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return false;
    }

    @Nullable
    public static InputStream readProtocollFromStorage(Context context, String zipfile, Uri entryuri)
    {
        try
        {
            File zipFilePath = new File(context.getFilesDir(), (zipfile == null) ? "_LastExport.zip" : zipfile);

            Log.d(LOGTAG, "readProtocollFromStorage: zip=" + zipFilePath);

            ZipFile zipFile = new ZipFile(zipFilePath);

            String zipName = entryuri.getLastPathSegment();

            Log.d(LOGTAG, "readProtocollFromStorage: protocoll=" + zipName);

            Log.d(LOGTAG, "readProtocollFromStorage: chat=" + LanguageTags.getSubjectChatname(zipName));

            ZipEntry entry = zipFile.getEntry(entryuri.getLastPathSegment());

            return zipFile.getInputStream(entry);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    @Nullable
    public static String getChatNameFromStorage(Context context, String zipfile, Uri entryuri)
    {
        try
        {
            String zipName = entryuri.getLastPathSegment();
            String chatName = LanguageTags.getSubjectChatname(zipName);

            Log.d(LOGTAG, "getChatNameFromStorage: text=" + zipName);
            Log.d(LOGTAG, "getChatNameFromStorage: chat=" + chatName);

            return chatName;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    @Nullable
    public static String getUserNameFromMessage(String message)
    {
        String regex = "[0-9][0-9]\\.[0-9][0-9]\\.[0-9][0-9], [0-9][0-9]:[0-9][0-9] - (.*?): .*";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);

        if (matcher.matches()) return matcher.group(1);

        return null;
    }

    @Nullable
    public static String getDateStringFromMessage(String message)
    {
        String regex = "([0-9][0-9])\\.([0-9][0-9])\\.([0-9][0-9]), ([0-9][0-9]):([0-9][0-9]) - .*";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);

        if (matcher.matches())
        {
            String day = matcher.group(1);
            String month = matcher.group(2);
            String year = matcher.group(3);
            String hour = matcher.group(4);
            String minute = matcher.group(5);

            if (year.length() == 2) year = "20" + year;

            return year + month + day + hour + minute;
        }

        return null;
    }

    @Nullable
    public static String getAttachmentFromMessage(String message)
    {
        return LanguageTags.getAttachment(message);
    }

    @Nullable
    public static String removeDateStringAndUserNameFromMessage(String datestring, String username, String message)
    {
        if (datestring != null)
        {
            String regex = (username == null)
                    ? "[0-9][0-9]\\.[0-9][0-9]\\.[0-9][0-9], [0-9][0-9]:[0-9][0-9] - (.*?)"
                    : "[0-9][0-9]\\.[0-9][0-9]\\.[0-9][0-9], [0-9][0-9]:[0-9][0-9] - .*?: (.*)";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(message);

            return matcher.matches() ? matcher.group(1) : message;
        }

        return message;
    }

    @Nullable
    public static String removeAttachmentTextFromMessage(String attachment, String message)
    {
        if (attachment != null) return null;

        return message;
    }
}

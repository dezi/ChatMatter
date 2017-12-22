package de.kappa_mm.email.chatmatter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MatterScrollView extends ScrollView
{
    private final static String LOGTAG = MatterScrollView.class.getSimpleName();

    private LinearLayout scrollContent;
    private String chatName;

    public MatterScrollView(Context context)
    {
        super(context);

        scrollContent = new LinearLayout(getContext());
        scrollContent.setOrientation(LinearLayout.VERTICAL);
        scrollContent.setBackgroundColor(0xdddddddd);

        addView(scrollContent);
    }

    public void setContent(Intent intent, String zipfile)
    {
        if (intent == null) return;

        String action = intent.getAction();
        if (action == null) return;

        if (! Intent.ACTION_SEND_MULTIPLE.equals(action)) return;

        Bundle bundle = intent.getExtras();
        if (bundle == null) return;

        for (String key : bundle.keySet())
        {
            Object value = bundle.get(key);
            Log.d(LOGTAG, String.format("%s %s (%s)", key, value.toString(), value.getClass().getName()));
        }

        if (! intent.hasExtra(Intent.EXTRA_STREAM)) return;

        ArrayList list = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (list == null) return;

        for (Object obj : list)
        {
            if (! (obj instanceof Uri)) continue;

            Uri uri = (Uri) obj;
            String uristr = uri.getPath();

            if (uristr.endsWith(".txt"))
            {
                chatName = ChatHandler.getChatNameFromStorage(getContext(),null, uri);

                try
                {
                    InputStream inputStream = ChatHandler.readProtocollFromStorage(getContext(),null, uri);
                    if (inputStream == null) continue;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;

                    while ((line = reader.readLine()) != null)
                    {
                        ChatFragment cfrag = new ChatFragment(getContext());

                        String attachment = LanguageTags.getAttachment(line);

                        if (attachment != null) Log.d(LOGTAG, "setContent: attachment=" + attachment);
                        
                        cfrag.setContent(line, line.length() % 2 == 0);

                        scrollContent.addView(cfrag);
                    }

                    reader.close();
                    inputStream.close();
                }
                catch (Exception ex)
                {
                    Log.d(LOGTAG, ex.getMessage());
                }
            }
        }
    }

    @Nullable
    public String getChatName()
    {
        return chatName;
    }
}

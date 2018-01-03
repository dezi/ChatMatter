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
    private Chat chat;

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

                chat = new Chat(chatName);

                try
                {
                    InputStream inputStream = ChatHandler.readProtocollFromStorage(getContext(),null, uri);
                    if (inputStream == null) continue;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    ChatFragment cfrag = null;
                    String line;

                    while ((line = reader.readLine()) != null)
                    {
                        String datestring = ChatHandler.getDateStringFromMessage(line);
                        if (datestring != null) Log.d(LOGTAG, "setContent: datestring=" + datestring);

                        String username = ChatHandler.getUserNameFromMessage(line);
                        if (username != null) Log.d(LOGTAG, "setContent: username=" + username);

                        line = ChatHandler.removeDateStringAndUserNameFromMessage(datestring, username, line);

                        String attachment = ChatHandler.getAttachmentFromMessage(line);
                        if (attachment != null) Log.d(LOGTAG, "setContent: attachment=" + attachment);

                        line = ChatHandler.removeAttachmentTextFromMessage(attachment, line);

                        if ((datestring == null) && (username == null) && (cfrag != null))
                        {
                            cfrag.addContent(line);
                        }
                        else
                        {
                            if (datestring != null)
                            {
                                Log.d(LOGTAG, "#### datestring=" + datestring);

                                boolean isnewday = chat.isNewDay(datestring);
                                chat.setLastDate(datestring);

                                if (isnewday)
                                {
                                    cfrag = new ChatFragment(getContext(), chat);
                                    cfrag.setContentInfo(chat.getLocaleDateString());

                                    scrollContent.addView(cfrag);
                                }
                            }

                            boolean send = (username != null) && (username.equals("Dennis Zierahnowitsch"));

                            cfrag = new ChatFragment(getContext(), chat);
                            cfrag.setContent(send, datestring, username, attachment, line);

                            scrollContent.addView(cfrag);
                        }
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

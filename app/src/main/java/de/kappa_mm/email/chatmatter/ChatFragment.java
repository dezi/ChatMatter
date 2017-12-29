package de.kappa_mm.email.chatmatter;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.Gravity;

public class ChatFragment extends LinearLayout
{
    private final static String LOGTAG = ChatFragment.class.getSimpleName();

    private final static String ENDINDENT = "\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0";

    private LinearLayout recvPart;
    private LinearLayout sendPart;
    private FrameLayout bubbleBox;
    private LinearLayout contentBox;
    private TextView userBox;
    private TextView attachmentBox;
    private TextView messageBox;
    private TextView timeBox;

    public ChatFragment(Context context)
    {
        super(context);

        setOrientation(HORIZONTAL);
        setLayoutParams(new LayoutParams(Simple.MP, Simple.WC));
        Simple.setPaddingDip(this, 8);
    }

    public void setContent(boolean send, String datestring, String username, String attachment, String message)
    {
        if (username == null)
        {
            setContentInfo(message);
        }
        else
        {
            setContentMessage(send, datestring, username, attachment, message);
        }
    }

    public void setContentMessage(boolean send, String datestring, String username, String attachment, String message)
    {
        if (message != null) message += ENDINDENT;

        String timeTag = ((datestring == null) || (datestring.length() < 12)) ? null
                : datestring.substring(8, 10) + ":" + datestring.substring(10, 12);

        recvPart = new LinearLayout(getContext());
        recvPart.setOrientation(VERTICAL);
        recvPart.setGravity(Gravity.START);

        addView(recvPart);

        sendPart = new LinearLayout(getContext());
        sendPart.setOrientation(VERTICAL);
        sendPart.setGravity(Gravity.END);

        addView(sendPart);

        ((LayoutParams) recvPart.getLayoutParams()).weight = send ? 0.75f : 0.25f;
        ((LayoutParams) sendPart.getLayoutParams()).weight = send ? 0.25f : 0.75f;

        bubbleBox = new FrameLayout(getContext());
        bubbleBox.setBackgroundColor(send ? 0xffccffcc : 0xffffffff);
        Simple.setSizeDip(bubbleBox, Simple.WC, Simple.WC);
        Simple.setPaddingDip(bubbleBox, 4);

        if (send)
        {
            sendPart.addView(bubbleBox);
        }
        else
        {
            recvPart.addView(bubbleBox);
        }

        contentBox = new LinearLayout(getContext());
        contentBox.setOrientation(VERTICAL);
        contentBox.setGravity(send ? Gravity.END : Gravity.START);
        Simple.setSizeDip(contentBox, Simple.WC, Simple.WC);

        bubbleBox.addView(contentBox);

        if (username != null)
        {
            userBox = new TextView(getContext());
            userBox.setSingleLine(true);
            userBox.setText(username);
            Simple.setSizeDip(userBox, Simple.WC, Simple.WC);
            Simple.setTextSizeDip(userBox, 16);

            contentBox.addView(userBox);
        }

        if (attachment != null)
        {
            attachmentBox = new TextView(getContext());
            attachmentBox.setSingleLine(true);
            attachmentBox.setText(attachment);
            attachmentBox.setBackgroundColor(0xccccccff);
            Simple.setSizeDip(attachmentBox, Simple.WC, Simple.WC);
            Simple.setTextSizeDip(attachmentBox, 16);

            contentBox.addView(attachmentBox);
        }

        messageBox = new TextView(getContext());
        messageBox.setGravity(send ? Gravity.END : Gravity.START);
        Simple.setSizeDip(messageBox, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(messageBox, 22);
        Simple.setPaddingDip(messageBox, 0, 0, 0, 4);

        FrameLayout.LayoutParams lptexttag = new FrameLayout.LayoutParams(Simple.WC, Simple.WC);
        lptexttag.gravity = Gravity.TOP + Gravity.END;

        contentBox.addView(messageBox, lptexttag);

        if (message != null)
        {
            messageBox.setText(message);
        } else
        {
            Simple.setTextSizeDip(messageBox, 12);
        }

        if (timeTag != null)
        {
            timeBox = new TextView(getContext());
            timeBox.setSingleLine(true);
            Simple.setTextSizeDip(timeBox, 12);

            FrameLayout.LayoutParams lptimetag = new FrameLayout.LayoutParams(Simple.WC, Simple.WC);
            lptimetag.gravity = Gravity.BOTTOM + Gravity.END;

            bubbleBox.addView(timeBox, lptimetag);

            timeBox.setText(timeTag);
        }
    }

    public void setContentInfo(String message)
    {
        RelativeLayout centerBox = new RelativeLayout(getContext());
        centerBox.setGravity(Gravity.CENTER);
        Simple.setSizeDip(centerBox, Simple.MP, Simple.WC);

        addView(centerBox);

        bubbleBox = new FrameLayout(getContext());
        bubbleBox.setBackgroundColor(0xffbbddff);
        bubbleBox.setBackgroundColor(0xffffeebb);
        Simple.setSizeDip(bubbleBox, Simple.WC, Simple.WC);
        Simple.setPaddingDip(bubbleBox, 4);
        Simple.setMarginDip(bubbleBox, 40, 0, 40, 0);

        centerBox.addView(bubbleBox);

        messageBox = new TextView(getContext());
        messageBox.setGravity(Gravity.CENTER_HORIZONTAL);
        Simple.setSizeDip(messageBox, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(messageBox, 22);

        bubbleBox.addView(messageBox);

        messageBox.setText(message);
    }

    public void addContent(String message)
    {
        if (messageBox != null)
        {
            String oldmess = messageBox.getText().toString();

            if (oldmess.endsWith(ENDINDENT))
            {
                oldmess = oldmess.substring(0, oldmess.length() - ENDINDENT.length());
            }

            String newmess = oldmess + (oldmess.equals("") ? "" : "\n") + message + ENDINDENT;

            Simple.setTextSizeDip(messageBox, 22);
            messageBox.setText(newmess);
        }
    }
}

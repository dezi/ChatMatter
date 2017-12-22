package de.kappa_mm.email.chatmatter;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Gravity;

public class ChatFragment extends LinearLayout
{
    private final static String LOGTAG = ChatFragment.class.getSimpleName();

    private final static String ENDINDENT = "\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0";

    private LinearLayout recvPart;
    private LinearLayout sendPart;
    private FrameLayout recvBubble;
    private FrameLayout sendBubble;
    private LinearLayout recvBox;
    private LinearLayout sendBox;
    private TextView recvUser;
    private TextView sendUser;
    private TextView recvAttachment;
    private TextView sendAttachment;
    private TextView recvText;
    private TextView recvTime;
    private TextView sendText;
    private TextView sendTime;

    private boolean send;

    public ChatFragment(Context context)
    {
        super(context);

        setOrientation(HORIZONTAL);
        setLayoutParams(new LayoutParams(Simple.MP, Simple.WC));
        Simple.setPaddingDip(this, 8);

        FrameLayout.LayoutParams lptimetag = new FrameLayout.LayoutParams(Simple.WC, Simple.WC);
        lptimetag.gravity = Gravity.BOTTOM + Gravity.END;

        FrameLayout.LayoutParams lptexttag = new FrameLayout.LayoutParams(Simple.WC, Simple.WC);
        lptexttag.gravity = Gravity.TOP + Gravity.END;

        recvPart = new LinearLayout(getContext());
        recvPart.setOrientation(VERTICAL);
        recvPart.setGravity(Gravity.START);
        Simple.setSizeDip(recvPart, Simple.MP, Simple.WC, 0.5f);

        addView(recvPart);

        recvBubble = new FrameLayout(getContext());
        Simple.setSizeDip(recvBubble, Simple.WC, Simple.WC);
        Simple.setPaddingDip(recvBubble, 4);

        recvPart.addView(recvBubble);

        recvBox = new LinearLayout(getContext());
        recvBox.setOrientation(VERTICAL);
        recvBox.setGravity(Gravity.START);
        Simple.setSizeDip(recvBox, Simple.WC, Simple.WC);

        recvBubble.addView(recvBox);

        recvText = new TextView(getContext());
        recvText.setGravity(Gravity.START);
        Simple.setSizeDip(recvText, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(recvText, 22);
        Simple.setPaddingDip(recvText, 0, 0, 0 , 4);

        recvBox.addView(recvText, lptexttag);

        recvTime = new TextView(getContext());
        recvTime.setSingleLine(true);
        Simple.setTextSizeDip(recvTime, 12);

        recvBubble.addView(recvTime, lptimetag);

        sendPart = new LinearLayout(getContext());
        sendPart.setOrientation(VERTICAL);
        sendPart.setGravity(Gravity.END);
        Simple.setSizeDip(sendPart, Simple.MP, Simple.WC, 0.5f);

        addView(sendPart);

        sendBubble = new FrameLayout(getContext());
        Simple.setSizeDip(sendBubble, Simple.WC, Simple.WC);
        Simple.setPaddingDip(sendBubble, 4);

        sendPart.addView(sendBubble);

        sendBox = new LinearLayout(getContext());
        sendBox.setOrientation(VERTICAL);
        sendBox.setGravity(Gravity.END);
        Simple.setSizeDip(sendBox, Simple.WC, Simple.WC);

        sendBubble.addView(sendBox);

        sendText = new TextView(getContext());
        sendText.setGravity(Gravity.END);
        Simple.setSizeDip(sendText, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(sendText, 22);
        Simple.setPaddingDip(sendText, 0, 0, 0 , 4);

        sendBox.addView(sendText, lptexttag);

        sendTime = new TextView(getContext());
        sendTime.setSingleLine(true);
        Simple.setTextSizeDip(sendTime, 12);

        sendBubble.addView(sendTime, lptimetag);
    }

    public void setContent(boolean send, String datestring, String username, String attachment, String message)
    {
        this.send = send;

        if (message != null) message += ENDINDENT;

        String timeTag = ((datestring == null) || (datestring.length() < 12)) ? null
                : datestring.substring(8, 10) + ":" + datestring.substring(10, 12);

        if (send)
        {
            ((LayoutParams) recvPart.getLayoutParams()).weight = 0.75f;
            ((LayoutParams) sendPart.getLayoutParams()).weight = 0.25f;

            sendBubble.setBackgroundColor(0xffccffcc);

            if (attachment != null)
            {
                sendAttachment = new TextView(getContext());
                sendAttachment.setSingleLine(true);
                sendAttachment.setText(attachment);
                sendAttachment.setBackgroundColor(0xccccccff);
                Simple.setSizeDip(sendAttachment, Simple.WC, Simple.WC);
                Simple.setTextSizeDip(sendAttachment, 16);

                sendBox.addView(sendAttachment, 0);
            }

            if (username != null)
            {
                sendUser = new TextView(getContext());
                sendUser.setSingleLine(true);
                sendUser.setText(username);
                Simple.setSizeDip(sendUser, Simple.WC, Simple.WC);
                Simple.setTextSizeDip(sendUser, 16);

                sendBox.addView(sendUser, 0);
            }

            if (message != null)
            {
                sendText.setText(message);
            }
            else
            {
                Simple.setTextSizeDip(sendText, 12);
            }

            if (timeTag != null) sendTime.setText(timeTag);
        }
        else
        {
            ((LayoutParams) recvPart.getLayoutParams()).weight = 0.25f;
            ((LayoutParams) sendPart.getLayoutParams()).weight = 0.75f;

            recvBubble.setBackgroundColor(0xffffffff);

            if (attachment != null)
            {
                recvAttachment = new TextView(getContext());
                recvAttachment.setSingleLine(true);
                recvAttachment.setText(attachment);
                recvAttachment.setBackgroundColor(0xccccccff);
                Simple.setSizeDip(recvAttachment, Simple.WC, Simple.WC);
                Simple.setTextSizeDip(recvAttachment, 16);

                recvBox.addView(recvAttachment, 0);
            }

            if (username != null)
            {
                recvUser = new TextView(getContext());
                recvUser.setSingleLine(true);
                recvUser.setText(username);
                Simple.setSizeDip(recvUser, Simple.WC, Simple.WC);
                Simple.setTextSizeDip(recvUser, 16);

                recvBox.addView(recvUser, 0);
            }

            if (message != null)
            {
                recvText.setText(message);
            }
            else
            {
                Simple.setTextSizeDip(recvText, 12);
            }

            if (timeTag != null) recvTime.setText(timeTag);
        }
    }

    public void addContent(String message)
    {
        TextView tw = send ? sendText : recvText;

        String oldmess = tw.getText().toString();

        if (oldmess.endsWith(ENDINDENT))
        {
            oldmess = oldmess.substring(0, oldmess.length() - ENDINDENT.length());
        }

        String newmess = oldmess + (oldmess.equals("") ? "" : "\n") + message + ENDINDENT;

        Simple.setTextSizeDip(tw, 22);
        tw.setText(newmess);
    }
}

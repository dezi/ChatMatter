package de.kappa_mm.email.chatmatter;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Gravity;

public class ChatFragment extends LinearLayout
{
    private final static String LOGTAG = ChatFragment.class.getSimpleName();

    private final static String ENDINDENT = "\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0";

    private LinearLayout recvPart;
    private LinearLayout sendPart;
    private LinearLayout recvBubble;
    private LinearLayout sendBubble;
    private TextView recvUser;
    private TextView sendUser;
    private FrameLayout recvBox;
    private FrameLayout sendBox;
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

        recvBubble = new LinearLayout(getContext());
        recvBubble.setOrientation(VERTICAL);
        Simple.setSizeDip(recvBubble, Simple.WC, Simple.WC);

        recvPart.addView(recvBubble);

        recvUser = new TextView(getContext());
        recvUser.setSingleLine(true);
        Simple.setSizeDip(recvUser, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(recvUser, 16);
        Simple.setPaddingDip(recvUser, 8, 0, 8, 0);

        recvBubble.addView(recvUser);

        recvBox = new FrameLayout(getContext());
        Simple.setSizeDip(recvBox, Simple.WC, Simple.WC);
        Simple.setPaddingDip(recvBox, 4);

        recvBubble.addView(recvBox);

        recvText = new TextView(getContext());
        recvText.setGravity(Gravity.START);
        Simple.setSizeDip(recvText, Simple.WC, Simple.WC);
        Simple.setPaddingDip(recvText, 4);
        Simple.setTextSizeDip(recvText, 22);

        recvBox.addView(recvText, lptexttag);

        recvTime = new TextView(getContext());
        recvTime.setSingleLine(true);
        Simple.setTextSizeDip(recvTime, 12);

        recvBox.addView(recvTime, lptimetag);

        sendPart = new LinearLayout(getContext());
        sendPart.setOrientation(VERTICAL);
        sendPart.setGravity(Gravity.END);
        Simple.setSizeDip(sendPart, Simple.MP, Simple.WC, 0.5f);

        addView(sendPart);

        sendBubble = new LinearLayout(getContext());
        sendBubble.setOrientation(VERTICAL);
        Simple.setSizeDip(sendBubble, Simple.WC, Simple.WC);

        sendPart.addView(sendBubble);

        sendUser = new TextView(getContext());
        sendUser.setSingleLine(true);
        Simple.setSizeDip(sendUser, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(sendUser, 16);
        Simple.setPaddingDip(sendUser, 8, 0, 8, 0);

        sendBubble.addView(sendUser);

        sendBox = new FrameLayout(getContext());
        Simple.setSizeDip(sendBox, Simple.WC, Simple.WC);
        Simple.setPaddingDip(sendBox, 4);

        sendBubble.addView(sendBox);

        sendText = new TextView(getContext());
        sendText.setGravity(Gravity.END);
        Simple.setSizeDip(sendText, Simple.WC, Simple.WC);
        Simple.setPaddingDip(sendText, 4);
        Simple.setTextSizeDip(sendText, 22);

        sendBox.addView(sendText, lptexttag);

        sendTime = new TextView(getContext());
        sendTime.setSingleLine(true);
        Simple.setTextSizeDip(sendTime, 12);

        sendBox.addView(sendTime, lptimetag);
    }

    public void setContent(boolean send, String datestring, String username, String attachment, String message)
    {
        this.send = send;

        message += ENDINDENT;

        String timeTag = ((datestring == null) || (datestring.length() < 12)) ? null
                : datestring.substring(8, 10) + ":" + datestring.substring(10, 12);

        if (send)
        {
            ((LayoutParams) recvPart.getLayoutParams()).weight = 0.75f;
            ((LayoutParams) sendPart.getLayoutParams()).weight = 0.25f;

            sendBubble.setBackgroundColor(0xffccffcc);
            sendUser.setText(username);
            sendTime.setText(timeTag);
            sendText.setText(message);
        }
        else
        {
            ((LayoutParams) recvPart.getLayoutParams()).weight = 0.25f;
            ((LayoutParams) sendPart.getLayoutParams()).weight = 0.75f;

            recvBubble.setBackgroundColor(0xffffffff);
            recvUser.setText(username);
            recvTime.setText(timeTag);
            recvText.setText(message);
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

        String newmess = oldmess + "\n" + message + ENDINDENT;

        tw.setText(newmess);
    }
}

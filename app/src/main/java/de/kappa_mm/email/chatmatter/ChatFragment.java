package de.kappa_mm.email.chatmatter;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Gravity;

public class ChatFragment extends LinearLayout
{
    private final static String LOGTAG = ChatFragment.class.getSimpleName();

    private LinearLayout recvPart;
    private LinearLayout sendPart;
    private FrameLayout recvBox;
    private FrameLayout sendBox;
    private TextView recvText;
    private TextView recvTime;
    private TextView sendText;
    private TextView sendTime;

    public ChatFragment(Context context)
    {
        super(context);

        setOrientation(HORIZONTAL);
        setLayoutParams(new LayoutParams(Simple.MP, Simple.WC));
        Simple.setPaddingDip(this, 8);

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(Simple.WC, Simple.WC);
        lp.gravity = Gravity.BOTTOM + Gravity.END;

        recvPart = new LinearLayout(getContext());
        recvPart.setOrientation(VERTICAL);
        recvPart.setGravity(Gravity.START);
        Simple.setSizeDip(recvPart, Simple.MP, Simple.WC, 0.5f);

        addView(recvPart);

        recvBox = new FrameLayout(getContext());
        Simple.setSizeDip(recvBox, Simple.WC, Simple.WC);
        Simple.setPaddingDip(recvBox, 4);

        recvPart.addView(recvBox);

        recvText = new TextView(getContext());
        recvText.setGravity(Gravity.START);
        Simple.setSizeDip(recvText, Simple.WC, Simple.WC);
        Simple.setPaddingDip(recvText, 4);
        Simple.setTextSizeDip(recvText, 22);

        recvBox.addView(recvText);

        recvTime = new TextView(getContext());
        recvTime.setSingleLine(true);
        Simple.setSizeDip(recvTime, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(recvTime, 12);

        recvBox.addView(recvTime, lp);

        sendPart = new LinearLayout(getContext());
        sendPart.setOrientation(VERTICAL);
        sendPart.setGravity(Gravity.END);
        Simple.setSizeDip(sendPart, Simple.MP, Simple.WC, 0.5f);

        addView(sendPart);

        sendBox = new FrameLayout(getContext());
        Simple.setSizeDip(sendBox, Simple.WC, Simple.WC);
        Simple.setPaddingDip(sendBox, 4);

        sendPart.addView(sendBox);

        sendText = new TextView(getContext());
        sendText.setGravity(Gravity.END);
        Simple.setSizeDip(sendText, Simple.WC, Simple.WC);
        Simple.setPaddingDip(sendText, 4);
        Simple.setTextSizeDip(sendText, 22);

        sendBox.addView(sendText);

        sendTime = new TextView(getContext());
        sendTime.setSingleLine(true);
        Simple.setSizeDip(sendTime, Simple.WC, Simple.WC);
        Simple.setTextSizeDip(sendTime, 12);

        sendBox.addView(sendTime, lp);
    }

    public void setContent(String message, boolean send)
    {
        if (send)
        {
            ((LayoutParams) recvPart.getLayoutParams()).weight = 0.75f;
            ((LayoutParams) sendPart.getLayoutParams()).weight = 0.25f;

            sendBox.setBackgroundColor(0xffccffcc);
            sendTime.setText("12:33");
            sendText.setText(message + "      ");
        }
        else
        {
            ((LayoutParams) recvPart.getLayoutParams()).weight = 0.25f;
            ((LayoutParams) sendPart.getLayoutParams()).weight = 0.75f;

            recvBox.setBackgroundColor(0xffffffff);
            recvTime.setText("12:55");
            recvText.setText(message + "      ");
        }
    }
}

package de.kappa_mm.email.chatmatter;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.util.Log;

public class MatterActivity extends AppCompatActivity
{
    private final static String LOGTAG = MatterActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent == null) return;

        String action = intent.getAction();
        if (action == null) return;

        if (! Intent.ACTION_SEND_MULTIPLE.equals(action)) return;

        IntentUtil.writeToStorage(this, intent);

        MatterScrollView scrollView = new MatterScrollView(this);
        scrollView.setContent(intent, null);

        setContentView(scrollView);
    }
}

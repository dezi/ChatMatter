package de.kappa_mm.email.chatmatter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{
    private final static String LOGTAG = MainActivity.class.getSimpleName();

    private MatterScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Log.d(LOGTAG, "onCreate");

        Intent intent = new Intent();

        if (! ChatHandler.readIntentFromStorage(this, intent, null)) return;

        Log.d(LOGTAG, "onCreate: intent=" + intent.toString());

        scrollView = new MatterScrollView(this);
        scrollView.setContent(intent, null);

        setContentView(scrollView);

        setTitle(scrollView.getChatName());
    }
}

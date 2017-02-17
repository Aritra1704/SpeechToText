package com.arpaul.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arpaul.speechtotext.adapter.PhrasesAdapter;
import com.arpaul.speechtotext.common.AppConstant;
import com.arpaul.speechtotext.dataobject.PhraseDO;
import com.arpaul.utilitieslib.UnCaughtException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

import static com.arpaul.speechtotext.common.AppConstant.strPhrases;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private TextView txtSpeechInput;
    private RecyclerView rvPhrases;

    private final int REQ_CODE_SPEECH_INPUT = 100;
    private ArrayList<PhraseDO> arrPhrases = new ArrayList<>();
    private PhrasesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this,"aritra1704@gmail.com",getString(R.string.app_name)));
        setContentView(R.layout.activity_main);

        initialiseControls();

        bindControls();
    }

    void bindControls() {
        arrPhrases = AppConstant.resetPhrases();
        adapter.refresh(arrPhrases);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getString(R.string.speech_prompt), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                promptSpeechInput();
            }
        });
    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    StringBuilder strPhrase = new StringBuilder();
                    for(String phrase: result) {
                        strPhrase.append(phrase);
                        strPhrase.append("\n");
                    }
                    txtSpeechInput.setText(strPhrase.toString());
                    arrPhrases = AppConstant.checkPhrase(result);
                    Toast.makeText(MainActivity.this, "result" + result.size(), Toast.LENGTH_SHORT).show();
                    adapter.refresh(arrPhrases);
                } else
                    Toast.makeText(MainActivity.this, getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    void initialiseControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        rvPhrases = (RecyclerView) findViewById(R.id.rvPhrases);

        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);

        adapter = new PhrasesAdapter(this, new ArrayList<PhraseDO>());
        rvPhrases.setAdapter(adapter);
    }
}

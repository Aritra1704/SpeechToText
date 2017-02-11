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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static com.arpaul.speechtotext.AppConstant.strPhrases;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private TextView txtSpeechInput;
    private RecyclerView rvPhrases;

    private final int REQ_CODE_SPEECH_INPUT = 100;
    ArrayList<String> arrPhrases = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseControls();

        bindControls();
    }

    void bindControls() {
        arrPhrases = new ArrayList<String>(Arrays.asList(strPhrases));


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
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
                    Toast.makeText(MainActivity.this, "REQ_CODE_SPEECH_INPUT RESULT_OK " + result.size(), Toast.LENGTH_SHORT).show();

//                    StringBuilder stBuild = new StringBuilder();
//                    for(int i = 0; i < result.size(); i++) {
//                        String phrase = result.get(i);
//                        stBuild.append(i + " ");
//                        stBuild.append(phrase);
//                    }
//                    Toast.makeText(MainActivity.this, stBuild.toString() + result.size(), Toast.LENGTH_SHORT).show();
                    txtSpeechInput.setText(result.get(0));
                } else
                    Toast.makeText(MainActivity.this, "REQ_CODE_SPEECH_INPUT RESULT_None", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        Toast.makeText(MainActivity.this, "REQ_CODE_SPEECH_INPUT OUT", Toast.LENGTH_SHORT).show();
    }

    void initialiseControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        rvPhrases = (RecyclerView) findViewById(R.id.rvPhrases);

        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
    }
}

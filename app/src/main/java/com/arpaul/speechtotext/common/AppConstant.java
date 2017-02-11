package com.arpaul.speechtotext.common;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.arpaul.speechtotext.MainActivity;
import com.arpaul.speechtotext.dataobject.PhraseDO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by ARPaul on 11-02-2017.
 */

public class AppConstant {

    public static final String[] strPhrases = new String[]{"Hello",
                                                            "World",
                                                            "Quantum Inventions",
                                                            "Inventions",
                                                            "Twenty",
                                                            "2nd Exit",
                                                            "4th Street",
                                                            "Traffic",
                                                            "Incidents",
                                                            "Navigate to"};

    public static ArrayList<PhraseDO> checkPhrase(/*Context context, */String sentence) {
        ArrayList<PhraseDO> arrPhrases = new ArrayList<PhraseDO>();
        HashMap<String, Integer> hashWords = new HashMap<>();
        if(!TextUtils.isEmpty(sentence)) {
            String[] words = sentence.split(" ");
            ArrayList<String> arrAllPhras = getAllPhrases();

//            for(String word : words) {
//                if(arrAllPhras.contains(word)) {
//                    int iteration = 1;
//                    if(hashWords.containsKey(word)) {
//                        iteration = hashWords.get(word) + 1;
//                    }
//                    hashWords.put(word, iteration);
//                }
//            }

            for(String word : arrAllPhras) {
                if(sentence.toLowerCase().contains(word)) {
                    String[] wordOccur = sentence.split(word);
                    int iteration = wordOccur.length;

                    if(wordOccur != null && !word.contains(" ")) {
                        if(!sentence.substring(sentence.lastIndexOf(" ")+1).equalsIgnoreCase(word))
                            iteration--;
                    }

                    hashWords.put(word, iteration);

//                    Toast.makeText(context, word + " " + wordOccur.length, Toast.LENGTH_SHORT).show();
                }
            }
        }

        if(hashWords.size() > 0) {
            arrPhrases.clear();
            PhraseDO objPhraseDO;
            for(String phrase: strPhrases) {
                objPhraseDO = new PhraseDO();
                objPhraseDO.Phrase = phrase;
                if(hashWords.containsKey(phrase.toLowerCase()))
                    objPhraseDO.PhraseIteration = hashWords.get(phrase.toLowerCase());
                arrPhrases.add(objPhraseDO);
            }
        }

        return arrPhrases;
    }

    public static ArrayList<String> getAllPhrases() {
        ArrayList<String> arrAllPhras = new ArrayList<String>(Arrays.asList(strPhrases.clone()));

        for(int i = 0; i < arrAllPhras.size(); i++) {
            arrAllPhras.set(i, arrAllPhras.get(i).toLowerCase());
        }

        return arrAllPhras;
    }

    public static ArrayList<PhraseDO> resetPhrases() {
        ArrayList<PhraseDO> arrPhrases = new ArrayList<>();
        PhraseDO objPhraseDO;
        for(String phrase: strPhrases) {
            objPhraseDO = new PhraseDO();
            objPhraseDO.Phrase = phrase;
            arrPhrases.add(objPhraseDO);
        }

        return arrPhrases;
    }
}

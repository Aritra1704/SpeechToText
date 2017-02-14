package com.arpaul.speechtotext.common;

import android.text.TextUtils;

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

    public static ArrayList<PhraseDO> checkPhrase(ArrayList<String> sentences) {
        ArrayList<PhraseDO> arrPhrases = new ArrayList<PhraseDO>();
        HashMap<String, Integer> hashWords = new HashMap<>();
        for(String sentence : sentences) {

            if(!TextUtils.isEmpty(sentence)) {
                ArrayList<String> arrAllPhras = getAllPhrases();

                for(String word : arrAllPhras) {
                    if(sentence.toLowerCase().contains(word)) {
                        int iteration = 1;
                        if(sentence.toLowerCase().equals(word))//If sentence contains only one phrase
                            hashWords.put(word, iteration);
                        else {
                            String[] wordOccur = sentence.split(word);//Spliting in order to calculate repetation. Otherwise could checked using contains.
                            iteration = wordOccur.length;

                            if(wordOccur != null) {
                                if(!word.contains(" ")) {//If the word in dictionary doesn't contain spaces
                                    if(!sentence.substring(sentence.lastIndexOf(" ")+1).equalsIgnoreCase(word))
                                        iteration--;
                                } else if(word.contains(" ") && iteration > 1) {//If the word in the phrase contain spaces
                                    if(TextUtils.isEmpty(wordOccur[0]))
                                        iteration--;
                                }
                            }

                            hashWords.put(word, iteration);
                        }
                    }
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
        } else
            arrPhrases = resetPhrases();

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

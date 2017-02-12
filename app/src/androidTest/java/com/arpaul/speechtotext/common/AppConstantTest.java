package com.arpaul.speechtotext.common;

import com.arpaul.speechtotext.dataobject.PhraseDO;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

/**
 * Created by ARPaul on 12-02-2017.
 */
public class AppConstantTest {
    @Test
    public void checkPhrase() throws Exception {

        ArrayList<PhraseDO> arrPhrases = new ArrayList<>();
        PhraseDO objPhraseDO;
        for(String phrase: AppConstant.strPhrases) {
            objPhraseDO = new PhraseDO();
            objPhraseDO.Phrase = phrase;
            if("Quantum Inventions hello incidents".contains(phrase))
                objPhraseDO.PhraseIteration = 1;
            arrPhrases.add(objPhraseDO);
        }
        assertEquals(AppConstant.checkPhrase("Quantum Inventions hello incidents") , arrPhrases); ;
    }

    @Test
    public void getAllPhrases() throws Exception {

    }

    @Test
    public void resetPhrases() throws Exception {

    }

}
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

        ArrayList<PhraseDO> arrPhrase = new ArrayList<>();
        PhraseDO objPhraseDO;

        objPhraseDO = new PhraseDO();
        objPhraseDO.Phrase = "Quantum Inventions";
        objPhraseDO.PhraseIteration = 1;
        arrPhrase.add(objPhraseDO);

        objPhraseDO = new PhraseDO();
        objPhraseDO.Phrase = "Inventions";
        objPhraseDO.PhraseIteration = 1;
        arrPhrase.add(objPhraseDO);

        objPhraseDO = new PhraseDO();
        objPhraseDO.Phrase = "hello";
        objPhraseDO.PhraseIteration = 1;
        arrPhrase.add(objPhraseDO);

        objPhraseDO = new PhraseDO();
        objPhraseDO.Phrase = "incidents";
        objPhraseDO.PhraseIteration = 1;
        arrPhrase.add(objPhraseDO);
//        assertThat(AppConstant.checkPhrase("Quantum Inventions hello incidents") , hasSize(4)) ;
    }

    @Test
    public void getAllPhrases() throws Exception {

    }

    @Test
    public void resetPhrases() throws Exception {

    }

}
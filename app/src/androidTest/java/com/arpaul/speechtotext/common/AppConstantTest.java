package com.arpaul.speechtotext.common;

import com.arpaul.speechtotext.dataobject.PhraseDO;

import org.hamcrest.Matcher;
import org.junit.Assert;
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
            if("hello world twenty".contains(phrase.toLowerCase()))
                objPhraseDO.PhraseIteration = 1;
            arrPhrases.add(objPhraseDO);
        }
        ArrayList<String> arrTest = new ArrayList<>();
//        arrTest.add("hello world 20");
//        arrTest.add("hello world XX");
        arrTest.add("hello world twenty");
//        arrTest.add("helloworld xx");
//        arrTest.add("helloworld 20");
        Assert.assertEquals(AppConstant.checkPhrase(arrTest) , arrPhrases);
//        AppConstant.checkPhrase(arrTest).equals(arrPhrases);
    }

    @Test
    public void getAllPhrases() throws Exception {

    }

    @Test
    public void resetPhrases() throws Exception {

    }

}
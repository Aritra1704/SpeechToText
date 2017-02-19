package com.arpaul.speechtotext.common;

import com.arpaul.speechtotext.dataobject.PhraseDO;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

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
//        arrTest.add("hello world twenty");
//        arrTest.add("hello world XX");
        arrTest.add("helloworld xx");
//        arrTest.add("helloworld 20");
//        Assert.assertEquals(AppConstant.checkPhrase(arrTest) , arrPhrases);
        checkPhraseObject(AppConstant.checkPhrase(arrTest) , arrPhrases);
//        AppConstant.checkPhrase(arrTest).equals(arrPhrases);
    }

    @Test
    private void checkPhraseObject(ArrayList<PhraseDO> expected, ArrayList<PhraseDO> actual) throws Exception {
        if(expected == null)
            throw new NullPointerException("Expected is null");
        if(actual == null)
            throw new NullPointerException("Actual is null");
        if(expected.size() != actual.size())
            throw new Exception("Expected doesn't match actual");
        for (int i = 0; i < expected.size(); i++) {
            if (expected.get(i).Phrase.equalsIgnoreCase(actual.get(i).Phrase)) {
                if(expected.get(i).PhraseIteration == actual.get(i).PhraseIteration) {
                    continue;
                } else {
                    throw new Exception("Expected iteration result doesn't match actual");
                }
            } else {
                throw new Exception("Expected content doesn't match actual");
            }
        }
    }

    @Test
    public void getAllPhrases() throws Exception {

    }

    @Test
    public void resetPhrases() throws Exception {

    }

}
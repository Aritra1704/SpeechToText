package com.arpaul.speechtotext.dataobject;

import java.util.Comparator;

/**
 * Created by ARPaul on 12-02-2017.
 */

public class PhraseDO implements Comparator<PhraseDO> {
    public String Phrase = "";
    public int PhraseIteration = 0;

    @Override
    public int compare(PhraseDO o1, PhraseDO o2) {
        if(o1.PhraseIteration == o2.PhraseIteration)
            return 0;
        else
            return 1;
    }
}

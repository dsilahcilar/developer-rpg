package org.deniz.rpg.model;

public class OnlineEducation implements Education {

    @Override
    public Integer price() {
        return 3;
    }

    @Override
    public Integer experience() {
        return 2;
    }

    @Override
    public Integer time() {
        return 6;
    }
}

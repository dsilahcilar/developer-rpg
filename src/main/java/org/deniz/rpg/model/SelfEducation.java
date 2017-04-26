package org.deniz.rpg.model;

public class SelfEducation implements Education{

    @Override
    public Integer price() {
        return 0;
    }

    @Override
    public Integer experience() {
        return 1;
    }

    @Override
    public Integer time() {
        return 10;
    }
}

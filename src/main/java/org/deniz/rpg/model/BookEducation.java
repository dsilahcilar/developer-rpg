package org.deniz.rpg.model;

public class BookEducation implements Education{

    @Override
    public Integer price() {
        return 4;
    }

    @Override
    public Integer experience() {
        return 3;
    }

    @Override
    public Integer time() {
        return 5;
    }
}

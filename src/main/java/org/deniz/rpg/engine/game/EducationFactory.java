package org.deniz.rpg.engine.game;

import org.deniz.rpg.model.BookEducation;
import org.deniz.rpg.model.Education;
import org.deniz.rpg.model.OnlineEducation;
import org.deniz.rpg.model.SelfEducation;

import java.util.HashMap;
import java.util.Map;

public class EducationFactory {
    private Map<Integer,Education> educations = new HashMap<>();

    public EducationFactory() {
        educations.put(1, new BookEducation());
        educations.put(2, new OnlineEducation());
        educations.put(3, new SelfEducation());
    }

    public Education getEducation(Integer key) {
        return educations.get(key);
    }
}

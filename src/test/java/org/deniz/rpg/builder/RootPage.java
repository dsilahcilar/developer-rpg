package org.deniz.rpg.builder;

import org.deniz.rpg.model.Page;
import org.deniz.rpg.model.Self;

import java.util.HashMap;
import java.util.Map;

import static org.deniz.rpg.builder.BePages.bePage;
import static org.deniz.rpg.builder.FePages.fePage;

public class RootPage {

    public static Page rootPage() {
        Self self = new Self("Your development carreer is starting", "You have to chose one of these carrier paths...");

        Map<String, Page> childPages = new HashMap<>();
        childPages.put("be", bePage());
        childPages.put("fe", fePage());

        return Page.builder()
                .self(self)
                .childPages(childPages)
                .build();
    }

}

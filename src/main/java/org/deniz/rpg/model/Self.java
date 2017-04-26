package org.deniz.rpg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Self {
    private String title;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Self self = (Self) o;
        return Objects.equals(title, self.title) &&
                Objects.equals(description, self.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }
}

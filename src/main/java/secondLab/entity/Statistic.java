package secondLab.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Statistic {
    @Setter
    private int iterations = 0;
    private int endMet = 0;
    private int childrenCreated = 0;
    private int childrenInMemory = 0;

    @Setter
    private Node initialStateNode;
    @Setter
    private Result result;

    public void incrementIteration() {
        iterations++;
    }

    public void incrementEndMeet() {
        endMet++;
    }

    public void incrementChildrenCreated() {
        childrenCreated++;
    }

    public void incrementChildrenInMemory() {
        childrenInMemory++;
    }

    public void decrementChildrenInMemory() {
        childrenInMemory--;
    }
}

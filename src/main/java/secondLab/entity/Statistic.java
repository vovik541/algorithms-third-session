package secondLab.entity;

import lombok.Getter;

@Getter
public class Statistic {
    private int iterations = 0;
    private int endMeet = 0;
    private int childrenCreated = 0;
    private int childrenInMemory = 0;

    public void incrementIteration(){
        iterations++;
    }
    public void incrementEndMeet(){
        endMeet++;
    }
    public void incrementChildrenCreated(){
        childrenCreated++;
    }
    public void incrementChildrenInMemory(){
        childrenInMemory++;
    }
    public void decrementChildrenInMemory(){
        childrenInMemory--;
    }
}

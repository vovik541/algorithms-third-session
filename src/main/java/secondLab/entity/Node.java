package secondLab.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
@EqualsAndHashCode
public class Node {
    private LinkedList<Node> children = new LinkedList<>();
    private byte[] state;

    private byte depth;

    private byte f;

    public Node(byte[] state, byte depth) {
        this.state = state;
        this.depth = depth;
    }

    public LinkedList<Node> getChildren() {
        return children;
    }

    public static int isSoluted = 0;

    public boolean isSolution() {
        isSoluted++;
        for (int queen = 0; queen < state.length; queen++) {
            for (int enemy = 0; enemy < state.length; enemy++) {
                if (queen != enemy
                        && (Math.abs(queen - enemy) == Math.abs(state[queen] - state[enemy])
                        || state[queen] == state[enemy])) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public void removeChild(Node child) {
        this.children.remove(child);
    }
}

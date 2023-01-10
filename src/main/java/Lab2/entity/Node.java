package Lab2.entity;


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

    private int stepValue;

    public Node(byte[] state, byte depth) {
        this.state = state;
        this.depth = depth;
        countStepValue();
    }

    public LinkedList<Node> getChildren() {
        return children;
    }

    public boolean isSolution() {
        return f == 0;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public void removeChild(Node child) {
        this.children.remove(child);
    }

    public void countStepValue() {
        stepValue = countF() + depth;
    }

    private int countF() {
        boolean attackedByLeftDiagonal;
        boolean attackedByRightDiagonal;
        boolean attackedByColumn;

        queenLink:
        for (byte queen = 0; queen < state.length; queen++) {
            attackedByLeftDiagonal = false;
            attackedByRightDiagonal = false;
            attackedByColumn = false;

            for (byte attacker = (byte) (queen + 1); attacker < state.length; attacker++) {
                if (attackedByLeftDiagonal && attackedByRightDiagonal && attackedByColumn) {
                    continue queenLink;
                } else if ((!attackedByLeftDiagonal || !attackedByRightDiagonal)
                        && (Math.abs(queen - attacker) == Math.abs(state[queen] - state[attacker]))) {
                    if ((state[attacker] < state[queen]) && !attackedByLeftDiagonal) {
                        attackedByLeftDiagonal = true;
                        f++;
                    } else if ((state[attacker] > state[queen]) && !attackedByRightDiagonal) {
                        attackedByRightDiagonal = true;
                        f++;
                    }
                } else if (!attackedByColumn && (state[queen] == state[attacker])) {
                    f++;
                    attackedByColumn = true;
                }
            }
        }

        return f;
    }
}

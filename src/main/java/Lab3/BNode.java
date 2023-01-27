package Lab3;

import static Lab3.Constants.T;

public class BNode {
    int n;
    int key[] = new int[2 * T - 1];
    BNode child[] = new BNode[2 * T];
    boolean leaf = true;

    public int Find(int k) {
        for (int i = 0; i < this.n; i++) {
            if (this.key[i] == k) {
                return i;
            }
        }
        return -1;
    };
}

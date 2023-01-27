package Lab3;

public class Main {

    public static void main(String[] args) {
        BTree b = new BTree();
        b.Insert(8);
        b.Insert(9);
        b.Insert(10);
        b.Insert(11);
        b.Insert(15);
        b.Insert(20);
        b.Insert(17);
        b.Insert(21);
        b.Insert(22);
        b.Insert(23);
        b.Insert(24);
        b.Insert(25);

        b.Show();

        if (b.Contain(12)) {
            System.out.println("\nfound");
        } else {
            System.out.println("\nnot found");
        }
    }
}

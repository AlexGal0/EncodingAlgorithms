package lib;

public class OptimalCodeTree implements Comparable {

    private int frecuency;
    private byte code = -1;

    private OptimalCodeTree left;
    private OptimalCodeTree right;


    public OptimalCodeTree() {
    }
    public OptimalCodeTree(int frecuency) {
        this.frecuency = frecuency;
    }

    public OptimalCodeTree(int frecuency, byte code) {
        this.frecuency = frecuency;
        this.code = code;
    }

    public OptimalCodeTree merge(OptimalCodeTree node) {
        OptimalCodeTree newNode = new OptimalCodeTree(this.frecuency + node.getFrecuency());

        newNode.left = this;
        newNode.right = node;

        return newNode;
    }

    public int getFrecuency() {
        return this.frecuency;
    }

    public OptimalCodeTree getLeft() {
        return left;
    }

    public OptimalCodeTree getRight() {
        return right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public byte getCode() {
        return code;
    }

    public void setFrecuency(int frecuency) {
        this.frecuency = frecuency;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.frecuency, ((OptimalCodeTree)o).getFrecuency());
    }


    // Method only for debug proposes
    public void printPreOrder(int deep) {
        System.out.println("Deep: " + deep);
        System.out.println(this.code == -1 ? "Root": "Codigo: " + (char)this.code + ", frequency: " + this.frecuency);

        if (this.isLeaf())
            return;

        System.out.println("0");
        this.left.printPreOrder(deep+1);
        System.out.println("1");
        this.right.printPreOrder(deep+1);
        System.out.println("Back");
    }
}

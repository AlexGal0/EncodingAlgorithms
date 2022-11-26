package lib;

import java.util.Arrays;

public class ByteArrayList {

    private static final int DEFAULT_SIZE = 10;

    private int size;
    private byte[] array;

    private int index = 0;

    public ByteArrayList() {
        this.size = DEFAULT_SIZE;
        this.array = new byte[this.size];
    }

    public ByteArrayList(int capacity) {
        this.size = capacity;
        this.array = new byte[this.size];
    }


    public void add(byte b) {
        if (this.index == this.size)
            expand();
        this.array[this.index++] = b;
    }

    private void expand() {
        if (Integer.MAX_VALUE / 2 < size)
            throw new SizeOutBoundException();
        size *= 2;

        byte[] newArray = new byte[size];
        System.arraycopy( this.array, 0, newArray, 0, this.array.length );
        this.array = newArray;
    }


    public byte[] toArray() {
        return Arrays.copyOf(this.array, this.index);
    }
}

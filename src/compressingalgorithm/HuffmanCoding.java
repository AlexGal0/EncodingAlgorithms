package compressingalgorithm;

import lib.OptimalCodeTree;

import java.util.Arrays;
import java.util.PriorityQueue;

public class HuffmanCoding extends Encoder{


    @Override
    public byte[] encode(byte[] file) {
        // Calculate frequencies
        int[] frequencies = new int[1<<8]; // Size of dictionary (byte)

        Arrays.fill(frequencies, 0); // In case that compiler don't initialize the array

        for (byte b : file)
            frequencies[b]++;

        // Make Optimal code tree

        OptimalCodeTree tree = buildOptimalTreeWithFrequencies(frequencies);

        tree.printPreOrder(0);
        // Encode
        // Encode tree

        // Encode information


        return new byte[0];
    }

    @Override
    public byte[] decode(byte[] file) {
        return new byte[0];
    }


    private OptimalCodeTree buildOptimalTreeWithFrequencies(int[] frequencies) {
        PriorityQueue<OptimalCodeTree> heap = new PriorityQueue<>();

        for (byte i = 0; i >= 0; i++) {
            if (frequencies[i] > 0)
                heap.add(new OptimalCodeTree(frequencies[i], i));
        }

        int n = heap.size() - 1;

        for (int i = 0; i < n; i++) {
            heap.add(heap.poll().merge(heap.poll()));
        }

        return heap.poll();
    }

}

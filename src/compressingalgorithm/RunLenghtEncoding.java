package compressingalgorithm;

import lib.ByteArrayList;

import java.util.ArrayList;

/**
 * Implementation of RLE (https://en.wikipedia.org/wiki/Run-length_encoding)
 */

public class RunLenghtEncoding extends Encoder {

    @Override
    public byte[] encode(byte[] file) {
        ByteArrayList newFile = new ByteArrayList();

        if (file.length == 0) // Empty File
            return new byte[0];

        int count = 1;
        byte cur = (byte) ((file[0] >> 7) & 1);
        byte mod = 6;

        for (int i = 0; i < file.length; i++, mod = 7) {
            for (; mod >= 0; mod--) {
                if (((file[i] >> mod) & 1) == cur)
                    count++;
                else { // Add the code representation to the output buffer
                    newFile.add(cur == 0? W : B);
                    cur = (byte)((file[i] >> mod) & 1);
                    if (count == 1) continue;
                    while (count > 0) {
                        newFile.add((byte)('0' + count % 10));
                        count /= 10;
                    }
                }
            }

        }

        return newFile.toArray();
    }

    @Override
    public byte[] decode(byte[] file) {
        ByteArrayList newFile = new ByteArrayList();



        return newFile.toArray();
    }
}

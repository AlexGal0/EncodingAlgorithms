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
                    newFile.add(cur == 0 ? W : B);
                    cur = (byte)((file[i] >> mod) & 1);
                    if (count == 1) continue;
                    while (count > 0) {
                        newFile.add((byte)('0' + (count % 10)));
                        count /= 10;
                    }
                    count = 1;
                }
            }
        }

        newFile.add(cur == 0 ? W : B);
        if (count != 1) {
            while (count > 0) {
                newFile.add((byte)('0' + (count % 10)));
                count /= 10;
            }
        }

        return newFile.toArray();
    }

    @Override
    public byte[] decode(byte[] file) {
        ByteArrayList newFile = new ByteArrayList();

        if (file.length == 0) // Empty File
            return new byte[0];

        int mod = 7;
        byte cur = 0;
        int con = 0;
        int pot = 1;
        int val = file[0] == B ? 1 : 0;

        for (int i = 1; i < file.length; i++) {
            if (file[i] == W || file[i] == B) {
                if (con == 0) con = 1;
                while (con-- > 0) {
                    cur |= val << mod;
                    if (--mod < 0) {
                        newFile.add(cur);
                        cur = 0;
                        mod = 7;
                    }
                }
                pot = 1;
                val = file[i] == B ? 1 : 0;
                con = 0;
            }
            else {
                con += (file[i] - '0') * pot;
                pot *= 10;
            }
        }

        if (con == 0) con = 1;
        while (con-- > 0) {
            cur |= val << mod;
            if (--mod < 0) {
                newFile.add(cur);
                cur = 0;
                mod = 7;
            }
        }
        if (mod < 7)
            newFile.add(cur);

        return newFile.toArray();
    }
}

import compressingalgorithm.Encoder;
import compressingalgorithm.HuffmanCoding;
import compressingalgorithm.RunLenghtEncoding;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        testText();
    }

    public static void testImage() throws IOException {
        // Encoding
        byte[] bitFile = readFile("ImageTestCases/Gray 8 bit/artificial.pgm");

        Encoder encoder = new RunLenghtEncoding();

        byte[] array = encoder.encode(bitFile);

        writeFile("OutputFiles/artificial.rle", array);

        // Decoding

        bitFile = readFile("OutputFiles/artificial.rle");

        array = encoder.decode(bitFile);

        writeFile("OutputFiles/artificial.rled", array);
    }

    public static void testText() throws IOException {
        // Encoding
        byte[] bitFile = readFile("TestFiles/textFile1.txt");

        Encoder encoder = new HuffmanCoding();

        byte[] array = encoder.encode(bitFile);

        writeFile("OutputFiles/textFile1.hce", array);

        // Decoding

        bitFile = readFile("OutputFiles/textFile1.hce");

        array = encoder.decode(bitFile);

        writeFile("OutputFiles/textFile1.hced", array);
    }

    public static byte[] readFile(String pathString) throws IOException {
        Path path = Paths.get(pathString);
        return Files.readAllBytes(path);
    }

    public static void writeFile(String path, byte[] bitFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(bitFile);
            //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
        }
    }
}

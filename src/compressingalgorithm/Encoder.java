package compressingalgorithm;

public abstract class Encoder {

    protected static final byte W = 87;
    protected static final byte B = 66;

    public abstract byte[] encode(byte[] file);
    public abstract byte[] decode(byte[] file);

}

import java.io.Serializable;

public class Node implements Serializable {
    public final BigInteger p;
    public final BigInteger g;
    public final BigInteger n;
    public final BigInteger m;
    public final BigInteger giant_step_l;
    public final BigInteger giant_step_r;

    public Node(
        BigInteger p, 
        BigInteger g, 
        BigInteger n,
        BigInteger m,
        BigInteger giant_step_l,
        BigInteger giant_step_r
    ) {
        this.p = p;
        this.g = g;
        this.n = n;
        this.m = m;
        this.giant_step_l = giant_step_l;
        this.giant_step_r = giant_step_r;
    }
}

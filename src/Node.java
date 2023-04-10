import java.io.Serializable;

public class Node implements Serializable {
    public final int l;
    public final int r;

    public Node(int l, int r) {
        this.l = l;
        this.r = r;
    }
}

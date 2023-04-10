import java.io.Serializable;

public class Node implements Serializable {
    public final int r;
    public final int st;
    public final int step;

    public Node(int st, int r, int step) {
        this.st = st;
        this.r = r;
        this.step = step;
    }
}

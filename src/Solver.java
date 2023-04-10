import parcs.*;

public class Solver implements AM {
    private static final int NODES = 4;

    private int divNum(int x) {
        if(x == 1) return 1;
        if(x == 2) return 2;

        int div_cnt = 2; // 1 and x
        for(long i = 2; i * i <= x; i++) {
            if(x % i == 0) div_cnt += 2;
            if(i * i == x) div_cnt--;

            // Already more than 4, we don't need to count more
            if (div_cnt > 4) return div_cnt;
        }

        return div_cnt;
    }

    public void run(AMInfo info) {
        Node n = (Node) info.parent.readObject();
        System.out.println("[start = " + n.st + ", r = " + n.r + ", step = " + n.step + "] Build started.");

        long sum = 0L;
        for(int i = n.st; i <= n.r; i+=n.step) {
            if(divNum(i) == 4) {
                sum += 1;
            }
        }
        System.out.println("[" + n.l + " " + n.r + "] Build finished.");
        info.parent.write(sum);
    }
}
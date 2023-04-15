import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import parcs.*;

public class Bluck {
    private final static int NODES = 4;
    private final static int MAX_M = 400000;

    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("Solver.jar");
        ArrayList<Node> nodes = fromFile(curtask.findFile("input"));
        AMInfo info = new AMInfo(curtask, null);
        LinkedList<channel> channels = new LinkedList<>();
        for(Node n: nodes) {
            point p = info.createPoint();
            channel c = p.createChannel();
            p.execute("Solver");
            c.write(n);
            channels.add(c);
        }

        BigInteger res = BigInteger.ZERO;

        System.out.println("Waiting for result...");
        for (channel c : channels) {
            BigInteger res2 = (BigInteger) c.readObject();

            if (res2.compareTo(BigInteger.ZERO) != 0) {
                res = res2;
            }
        }
        System.out.println("Result: " + res);
        curtask.end();
    }

    private static ArrayList<BigInteger> readBigIntegersFromFile(String filename) throws FileNotFoundException {
        ArrayList<BigInteger> bigIntegers = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            BigInteger bigInteger = new BigInteger(line.trim());
            bigIntegers.add(bigInteger);
        }
        scanner.close();
        return bigIntegers;
    }

    // Returns the smallest integer m such that m^2 >= x
    private static BigInteger sqrtCeil(BigInteger x) {
        if (x.compareTo(BigInteger.ZERO) <= 0) {
            return BigInteger.ZERO;
        }

        BigInteger left = BigInteger.ONE;
        BigInteger right = x;

        while (left.compareTo(right) < 0) {
            BigInteger mid = left.add(right).shiftRight(1);
            BigInteger midSquared = mid.multiply(mid);
            int cmp = midSquared.compareTo(x);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                left = mid.add(BigInteger.ONE);
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static ArrayList<Node> fromFile(String filename) throws Exception {
        ArrayList<BigInteger> params = readBigIntegersFromFile(filename);

        BigInteger p = params.get(0);
        BigInteger g = params.get(1);
        BigInteger n = params.get(2);

        BigInteger m = sqrtCeil(p).max(BigInteger.valueOf(MAX_M));

        BigInteger giant_steps = ceilDivide(p, m);
        BigInteger giant_steps_per_node = ceilDivide(p, m).divide(BigInteger.valueOf(NODES));

        ArrayList<Node> nodes = new ArrayList<>();

        BigInteger giant_step_l = BigInteger.valueOf(0);

        for (int i = 0; i < NODES-1; i++) {
            BigInteger giant_step_r = giant_step_l.add(giant_steps_per_node);
            Node node = new Node(p, g, n, m, giant_step_l, giant_step_r);
            nodes.add(node);
            giant_step_l = giant_step_r.add(BigInteger.ONE);
        }

        Node node = new Node(p, g, n, m, giant_step_l, giant_steps);
        nodes.add(node);

        return nodes;
    }

    public static BigInteger ceilDivide(BigInteger a, BigInteger b) {
        BigInteger[] result = a.divideAndRemainder(b);
        BigInteger quotient = result[0];
        BigInteger remainder = result[1];

        if (remainder.compareTo(BigInteger.ZERO) != 0) {
            quotient = quotient.add(BigInteger.ONE);
        }

        return quotient;
    }
    
}

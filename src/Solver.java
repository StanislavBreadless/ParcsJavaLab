import parcs.*;

public class Solver implements AM {
    public static BigInteger babyStepGiantStep(
        BigInteger p, 
        BigInteger g, 
        BigInteger h,
        BigInteger giant_step_l,
        BigInteger giant_step_r,
        BigInteger m
    ) {        
        // Precompute the values of g^(1...m)
        Map<BigInteger, BigInteger> babySteps = new HashMap<>();
        BigInteger gamma = BigInteger.ONE;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(m) < 0; i = i.add(BigInteger.ONE)) {
            babySteps.put(gamma, i);
            gamma = gamma.multiply(g).mod(p);
        }

        // Compute the giant steps
        BigInteger invGamma = gamma.modInverse(p);
        BigInteger y = h.multiply(invGamma.pow(giant_step_l)).mod(p);
        for (BigInteger i = giant_step_l; i.compareTo(giant_step_r) < 0; i = i.add(BigInteger.ONE)) {
            if (babySteps.containsKey(y)) {
                return i.multiply(m).add(babySteps.get(y));
            }
            y = y.multiply(invGamma).mod(p);
        }
        
        return BigInteger.ZERO; // 0 is the marker that the item is not in the field
    }
    

    public void run(AMInfo info) {
        Node n = (Node) info.parent.readObject();
        System.out.println("[p = " + n.p + ", g = " + n.g + ", n = " + n.n + ", giant_step_l = " + n.giant_step_l + ", giant_step_r = " + n.giant_step_r + "] Build started.");

        BigInteger res = babyStepGiantStep(n.p, n.g, n.n, n.giant_step_l, n.giant_step_r, n.m);

        System.out.println("[p = " + n.p + ", g = " + n.g + ", n = " + n.n + ", giant_step_l = " + n.giant_step_l + ", giant_step_r = " + n.giant_step_r + "] Build finished.");
        System.out.println("result in this node: " + res);

        info.parent.write(res);
    }
}
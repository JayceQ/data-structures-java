import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int size = 10000000;
        int m = 10000000;
        //System.out.println("test UnionFind: " + testUF(new UnionFind(size), m) + "s");
        //System.out.println("test UnionFind2: " + testUF(new UnionFind2(size), m) + "s");
        System.out.println("test UnionFind3: " + testUF(new UnionFind3(size), m) + "s");
        System.out.println("test UnionFind4: " + testUF(new UnionFind4(size), m) + "s");
        System.out.println("test UnionFind5: " + testUF(new UnionFind5(size), m) + "s");
        System.out.println("test UnionFind6: " + testUF(new UnionFind6(size), m) + "s");
    }


    private static double testUF(UF f, int m) {
        int size = f.getSize();
        Random random = new Random();
        long time = System.nanoTime();

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            f.unionElements(a, b);
        }

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            f.isConnected(a, b);
        }
        return (System.nanoTime() - time) / 1000000000.0;
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lab5v2 {

    static Complex i = new Complex(0,1);
    static int bigN = 10;
    static List<Integer> listX = new ArrayList<>();
    static List<Double> listY = new ArrayList<>();

    public static void main(String[] args) {

        for (int i = 0; i < bigN; i++) {
            listX.add(i + 1);
        }
        listY = listX.stream().map(Math::sin).collect(Collectors.toList());
        System.out.println(listY);
        List<Complex> listY2 = new ArrayList<>();
        for (int i = 0; i < bigN; i++) {
            listY2.add(fourierTransform(listY, i));
        }

        for (int i = 0; i < bigN; i++) {
            System.out.print(oppositeFourierTransform(listY2, i).re() + " ");
        }
    }

    private static Complex degree(Complex bigN, Complex k, Complex n) {
        return i.mul((new Complex(2 * Math.PI, 0)
                .divides(bigN))
                .mul(k)
                .mul(n));
    }

    private static Complex fourierTransform(List<Double> list, int k) {
        Complex sum = new Complex(0,0);
        for (int i = 0; i < bigN; i++) {
            sum = sum.plus(new Complex(list.get(i), 0)
                    .mul(new Complex(-1, 0)
                            .mul(degree(new Complex(bigN, 0), new Complex(k, 0), new Complex(i, 0))).exp()));
        }
        return sum;
    }

    private static Complex oppositeFourierTransform(List<Complex> list, int k) {
        Complex sum = new Complex(0,0);
        for (int i = 0; i < bigN; i++) {
            sum = sum.plus(list.get(i)
            .mul(degree(new Complex(bigN, 0), new Complex(k, 0), new Complex(i, 0)).exp()));
        }
        return sum.divides(new Complex(bigN, 0));
    }
}

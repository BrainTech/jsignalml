package jsignalml;

public class Speed2 {
  public int value = 42;
  public int getValue() {
    return value;
  }

  public static void main(String[] args) {
    Speed2 test = new Speed2();
    int dummy;
    for (int i=0; i<1000000; i++) {
      dummy = test.value;
      dummy = test.getValue();
    }

    long tDirektStart = System.currentTimeMillis();
    long sum = 0L;
    for (int i=0; i<1000000000; i++) {
		    sum += test.value;
    }
    long tDirekt = System.currentTimeMillis() - tDirektStart;

    sum= 0L;
    long tGetterStart = System.currentTimeMillis();
    for (int i=0; i<1000000000; i++) {
		    sum += test.getValue();
    }
    long tGetter = System.currentTimeMillis() - tGetterStart;

    System.out.println("Direkt: " + tDirekt + " " + sum);
    System.out.println("Getter: " + tGetter + " " + sum);
  }
}

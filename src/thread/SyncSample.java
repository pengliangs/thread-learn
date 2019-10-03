package thread;

/**
 * @author pengliang
 * @date 2019/10/1 21:21
 */
public class SyncSample {

    public static void main(String[] args) {
        Couplet couplet = new Couplet();
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                new Thread(() -> couplet.one()).start();
            } else {
                new Thread(() -> couplet.two()).start();
            }
        }
    }

}

class Couplet {

    public synchronized void one() {
            System.out.printf("寂");
            System.out.printf("寞");
            System.out.printf("寒");
            System.out.printf("窗");
            System.out.printf("空");
            System.out.printf("守");
            System.out.printf("寡");
            System.out.println();
    }

    public synchronized void two() {
            System.out.printf("俊");
            System.out.printf("俏");
            System.out.printf("佳");
            System.out.printf("人");
            System.out.printf("伴");
            System.out.printf("伶");
            System.out.printf("仃");
            System.out.println();
    }

}
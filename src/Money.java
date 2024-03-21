//package term_project;
import java.util.HashMap;
import java.util.Map;
// 화폐 클래스
public class Money {
    private int c_10;
    private int c_50;
    private int c_100;
    private int c_500;
    private int c_1000;

    public Money() {
        c_10 = 5;
        c_50 = 5;
        c_100 = 5;
        c_500 = 5;
        c_1000 = 5;
    }

    // 갯수 증가시켜주는 메소드
    public void countUp(int money) {
        switch (money) {
            case 10:
                c_10++;
                break;
            case 50:
                c_50++;
                break;
            case 100:
                c_100++;
                break;
            case 500:
                c_500++;
                break;
            case 1000:
                c_1000++;
                break;
            default:
                break;
        }
    }

    // 갯수 감소시켜주는 메소드
    public void countDown(int money) {
        switch (money) {
            case 10:
                c_10--;
                break;
            case 50:
                c_50--;
                break;
            case 100:
                c_100--;
                break;
            case 500:
                c_500--;
                break;
            case 1000:
                c_1000--;
                break;
            default:
                break;
        }
    }

    public int getC_10() {
        return c_10;
    }

    public int getC_50() {
        return c_50;
    }

    public int getC_100() {
        return c_100;
    }

    public int getC_500() {
        return c_500;
    }

    public int getC_1000() {
        return c_1000;
    }

    // 각 화폐 개수 반환해주는 메소드
    public int getCount(int denomination) {
        switch (denomination) {
            case 10:
                return c_10;
            case 50:
                return c_50;
            case 100:
                return c_100;
            case 500:
                return c_500;
            case 1000:
                return c_1000;
            default:
                return 0;
        }
    }

    // 각 화폐 개수 재정의 메소드
    public void setC_10(int c) {
        this.c_10 = c;
    }

    public void setC_50(int c) {
        this.c_50 = c;
    }

    public void setC_100(int c) {
        this.c_100 = c;
    }

    public void setC_500(int c) {
        this.c_500 = c;
    }

    public void setC_1000(int c) {
        this.c_1000 = c;
    }


}

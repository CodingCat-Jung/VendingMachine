//package term_project;


// 음료의 종류, 가격, 재고를 저장하고 관리하는 클래스 Beverage
public class Beverage {
    private String name; // 음료의 이름
    private int price; // 음료의 가격
    private int stock; // 음료의 재고

    //private Map<String, Beverage> beverageMap;

    // 생성자
    public Beverage(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // 기본 생성자
    public Beverage() {

    }

    // 음료의 가격을 재정의
    // Adminmenu.java에서 가격 변경 버튼 이벤트 처리 활용 메소드
    public void setPrice(int price) {
        this.price = price;
    }

    // 음료의 이름을 재정의
    // Adminmenu.java에서 이름 변경 버튼 이벤트 처리 활용 메소드
    public void setName(String name) {
        this.name = name;
    }

    // 음료의 가격을 반환해주는 메소드
    // Setting.java에서 purchaseBeverage메소드 실행 중 음료의 가격과 잔액 비교, 잔액 차감 시
    public int getPrice() {
        return price;
    }

    // 음료의 재고를 반환해주는 메소드
    // Setting.java에서 purchaseBeverage메소드 실행 중 재고가 있는지 확인
    public int getStock() {
        return stock;
    }

    // 음료의 재고를 세팅하는 메소드
    // Setting.java에서 재고 보충 increaseStock메소드에서 활용
    public void setStock(int stock) {
        this.stock = stock;
    }

    // Setting.java increaseStock메소드 실행 중 재고 추가하기 위한 메소드
    public void increaseQuantity() {
        stock++;
    }

    // 음료 재고 차감시키는 메소드
    // Setting.java에서 purchaseBeverage메소드 실행 중 음료 구매 후 재고 차감
    public void decreaseQuantity() {
        if (stock > 0) {
            stock--;
        }
    }

    // Adminmenu.java에서 재고를 admTable에 출력하기 위한 메소드 updateStockTable에서 활용
    // 음료의 이름을 반환해주는 메소드
    public String getName() {
        return name;
    }

    // 품절임을 알려주는 메소드
    public boolean isSoldOut() {
        return stock == 0;
    }


}
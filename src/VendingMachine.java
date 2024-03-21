//package term_project;
//import Adminmenu.*;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;

import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.border.EmptyBorder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class VendingMachine extends JFrame {

    // VendingMachine 객체의 인스턴스를 저장할 정적 필드를 선언
    private static VendingMachine instance;

    // 화폐를 입력받는 변수 (동적할당을 통한 래퍼런스화)
    // 객체를 생성하면 JVM이 자동으로 메모리 할당
    private static Integer inputMoney = 0; // 기존의 inputMoney 필드


    // 관리자 메뉴 비밀번호 초기화
    public static String passwd = "@20204026";

    // 1000원짜리 화폐 입력 받을때마다 count++ -> 지폐는 3000원까지만 입력
    private int count1000 = 1;


    // Beverage.java의 Beverage 클래스 생성자 이용하여 음료수 이름, 가격, 재고 초기화
    // 초기 음료 목록 설정
    // 음료 객체 초기화
    public static Beverage water = new Beverage("water", 450, 3);
    public static Beverage coffee = new Beverage("coffee", 500, 3);
    public static Beverage sport = new Beverage("sport", 550, 3);
    public static Beverage premiumCoffee = new Beverage("premiumCoffee", 700, 3);
    public static Beverage coke = new Beverage("coke", 750, 3);


    // Beverage 객체를 저장하는 Map
    public static Map<String, Beverage> beverageMap = new HashMap<String, Beverage>() {{
        put(water.getName(), water);
        put(coffee.getName(), coffee);
        put(sport.getName(), sport);
        put(premiumCoffee.getName(), premiumCoffee);
        put(coke.getName(), coke);
    }};

    // Money.java를 이용하여 각 화폐 초기화
    public static Money c_10 = new Money();
    public static Money c_50 = new Money();
    public static Money c_100 = new Money();
    public static Money c_500 = new Money();
    public static Money c_1000 = new Money();


    // Money 객체를 저장하는 Map
    public static Map<Integer, Money> MoneyMap = new HashMap<Integer, Money>(){{
        put(10, c_10);
        put(50, c_50);
        put(100, c_100);
        put(500, c_500);
        put(1000, c_1000);
    }};

    private JPanel contentPane;
    private JLabel moneyLabel;

    // 각 음료 가격 라벨
    private static JLabel waterPrice = new JLabel("450");
    private static JLabel coffeePrice = new JLabel("500");
    private static JLabel sportPrice = new JLabel("550");
    private static JLabel premiumCoffeePrice = new JLabel("700");
    private static JLabel cokePrice = new JLabel("750");

    // 각 음료 구매 버튼
    JButton waterButton = new JButton("구매");
    JButton coffeeButton = new JButton("구매");
    JButton sportButton = new JButton("구매");
    JButton premiumCoffeeButton = new JButton("구매");
    JButton cokeButton = new JButton("구매");


    public VendingMachine() {
        setTitle("20204026 정명훈 자판기");
        Container c = getContentPane(); // 컨테이너 생성
        c.setLayout(new FlowLayout());

        c.setLayout(null); // 레이아웃 매니저 비활성화

        // 자판기 프레임
        // 물
        JLabel imgLabel = new JLabel();
        ImageIcon waterIcon = new ImageIcon("물.png");
        Image Img = waterIcon.getImage();
        Image changeImg = Img.getScaledInstance(150,150,Image.SCALE_SMOOTH);
        ImageIcon changeIcon = new ImageIcon(changeImg);

        imgLabel.setIcon(changeIcon);
        imgLabel.setBounds(50,50,150,150);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(imgLabel);

        waterPrice.setBounds(115, 80, 300, 300);
        getContentPane().add(waterPrice);


        waterButton.setLocation(75, 250);
        waterButton.setSize(100,50);
        waterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                purchaseBeverage("water", moneyLabel);
                updateButtonColor(waterButton, "water");
            }
        });

        c.add(waterButton);

        // 커피
        JLabel imgLabel1 = new JLabel();
        ImageIcon coffeeIcon = new ImageIcon("커피.png");
        Image Img1 = coffeeIcon.getImage();
        Image changeImg1 = Img1.getScaledInstance(150,150,Image.SCALE_SMOOTH);
        ImageIcon changeIcon1 = new ImageIcon(changeImg1);

        imgLabel1.setIcon(changeIcon1);
        imgLabel1.setBounds(300,50,150,150);
        imgLabel1.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(imgLabel1);


        coffeePrice.setBounds(365, 80, 300, 300);
        getContentPane().add(coffeePrice);



        coffeeButton.setLocation(325, 250);
        coffeeButton.setSize(100,50);
        coffeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                purchaseBeverage("coffee", moneyLabel);
                updateButtonColor(coffeeButton, "coffee");
            }
        });
        c.add(coffeeButton);

        // 이온음료
        JLabel imgLabel2 = new JLabel();
        ImageIcon sportsDrinkIcon = new ImageIcon("이온음료.png");
        Image Img2 = sportsDrinkIcon.getImage();
        Image changeImg2 = Img2.getScaledInstance(150,150,Image.SCALE_SMOOTH);
        ImageIcon changeIcon2 = new ImageIcon(changeImg2);

        imgLabel2.setIcon(changeIcon2);
        imgLabel2.setBounds(550,50,150,150);
        imgLabel2.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(imgLabel2);


        sportPrice.setBounds(615, 80, 300, 300);
        getContentPane().add(sportPrice);


        sportButton.setLocation(575, 250);
        sportButton.setSize(100,50);
        sportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                purchaseBeverage("sport", moneyLabel);
                updateButtonColor(sportButton, "sport");
            }
        });
        c.add(sportButton);

        // 고급커피
        JLabel imgLabel3 = new JLabel();
        ImageIcon premiumCoffeeIcon = new ImageIcon("고급커피.png");
        Image Img3 = premiumCoffeeIcon.getImage();
        Image changeImg3 = Img3.getScaledInstance(150,150,Image.SCALE_SMOOTH);
        ImageIcon changeIcon3 = new ImageIcon(changeImg3);

        imgLabel3.setIcon(changeIcon3);
        imgLabel3.setBounds(50,300,150,150);
        imgLabel3.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(imgLabel3);


        premiumCoffeePrice.setBounds(115, 325, 300, 300);
        getContentPane().add(premiumCoffeePrice);


        premiumCoffeeButton.setLocation(75, 500);
        premiumCoffeeButton.setSize(100,50);
        premiumCoffeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                purchaseBeverage("premiumCoffee", moneyLabel);
                updateButtonColor(premiumCoffeeButton, "premiumCoffee");
            }
        });
        c.add(premiumCoffeeButton);

        // 탄산음료
        JLabel imgLabel4 = new JLabel();
        ImageIcon sodaIcon = new ImageIcon("탄산음료.png");
        Image Img4 = sodaIcon.getImage();
        Image changeImg4 = Img4.getScaledInstance(150,150,Image.SCALE_SMOOTH);
        ImageIcon changeIcon4 = new ImageIcon(changeImg4);

        imgLabel4.setIcon(changeIcon4);
        imgLabel4.setBounds(300,300,150,150);
        imgLabel4.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(imgLabel4);


        cokePrice.setBounds(365, 325, 300, 300);
        getContentPane().add(cokePrice);


        cokeButton.setLocation(325, 500);
        cokeButton.setSize(100,50);
        cokeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                purchaseBeverage("coke", moneyLabel);
                updateButtonColor(cokeButton, "coke");
            }
        });
        c.add(cokeButton);


        // 화폐 입력 버튼
        JButton m = new JButton(Integer.toString(10));
        m.setLocation(75, 550);
        m.setSize(100,50);
        m.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                insertMoney(10, moneyLabel);

            }
        });
        c.add(m);

        JButton m1 = new JButton(Integer.toString(50));
        m1.setLocation(200, 550);
        m1.setSize(100,50);
        m1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                insertMoney(50, moneyLabel);

            }
        });
        c.add(m1);

        JButton m2 = new JButton(Integer.toString(100));
        m2.setLocation(325, 550);
        m2.setSize(100,50);
        m2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                insertMoney(100, moneyLabel);

            }
        });
        c.add(m2);

        JButton m3 = new JButton(Integer.toString(500));
        m3.setLocation(450, 550);
        m3.setSize(100,50);
        m3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                insertMoney(500, moneyLabel);

            }
        });
        c.add(m3);

        JButton m4 = new JButton(Integer.toString(1000));
        m4.setLocation(575, 550);
        m4.setSize(100,50);
        m4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                insertMoney(1000, moneyLabel);

            }
        });
        c.add(m4);

        // 반환 버튼
        JButton b5 = new JButton("반환");
        b5.setLocation(75, 625);
        b5.setSize(100,100);
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                returnMoney(moneyLabel);

            }
        });
        c.add(b5);

        // 관리자 메뉴 버튼
        JButton b6 = new JButton("관리자 메뉴");
        b6.setLocation(200, 625);
        b6.setSize(100,100);
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 비밀번호 입력 다이얼로그 생성
                JPasswordField passwordField = new JPasswordField();
                Object[] message = {
                        "비밀번호를 입력하세요:", passwordField
                };
                int option = JOptionPane.showOptionDialog(null, message, "비밀번호 입력",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                // OK 버튼을 눌렀을 경우 비밀번호 확인
                if (option == JOptionPane.OK_OPTION) {
                    char[] password = passwordField.getPassword(); // 비밀번호 입력받음
                    String enteredPassword = new String(password); // 입력받은 비밀번호 저장

                    // 비밀번호 확인
                    if (PasswordMatch(enteredPassword)) {
                        // 비밀번호가 일치하는 경우 AdminMenu 창 열기
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                Adminmenu adminMenu = new Adminmenu();
                                adminMenu.setVisible(true);
                                ((JButton) e.getSource()).getTopLevelAncestor().setVisible(false);
                            }
                        });
                    } else { // 비밀번호가 일치하지 않는 경우
                        JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "인증 실패", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        c.add(b6);

        // 현재 잔액 표시 창
        moneyLabel = new JLabel("현재 잔액: " + "0");
        moneyLabel.setBounds(550, 400, 150, 50);
        moneyLabel.setBackground(Color.WHITE);
        moneyLabel.setOpaque(true);
        c.add(moneyLabel);



        // 프레임 설정
        c.setBackground(Color.lightGray);
        setSize(800,800);
        setVisible(true);
    }


    //
    //
    // **********메소드 구현**********
    //
    //


    // VendingMachine 클래스의 인스턴스를 반환하는 정적 메소드를 생성
    public static VendingMachine getInstance() {
        if (instance == null) {
            instance = new VendingMachine();
        }
        return instance;
    }

    // (1) Adminmenu.java에서 음료 가격 변경 시 실행하는 메소드
    public static void changeBeveragePrice(String name, int newPrice) {
        Beverage selectedBeverage = null;
        // 해당 음료 검색
        if (name.equals(water.getName())) {
            selectedBeverage = water;
        } else if (name.equals(coffee.getName())) {
            selectedBeverage = coffee;
        } else if (name.equals(sport.getName())) {
            selectedBeverage = sport;
        } else if (name.equals(premiumCoffee.getName())) {
            selectedBeverage = premiumCoffee;
        } else if (name.equals(coke.getName())) {
            selectedBeverage = coke;
        } else {
            JOptionPane.showMessageDialog(null, "선택하신 음료가 존재하지 않습니다.");
            return; // 음료가 존재하지 않을 경우 메서드 종료
        }

        // Beverage.java에서 setPrice 메서드를 사용하여 가격 변경
        selectedBeverage.setPrice(newPrice);
        updatePriceLabel(name, newPrice); // VendingMachine 클래스의 정적 메서드인 updatePriceLabel 호출
        JOptionPane.showMessageDialog(null, name + "의 가격을 " + newPrice + "로 변경을 완료하였습니다.");
    }

    // (2) 최신화된 가격을 라벨에 최신화하는 메소드
    private static void updatePriceLabel(String name, int newPrice) {
        if (name.equals("water")) {
            waterPrice.setText(Integer.toString(newPrice));
        } else if (name.equals("coffee")) {
            coffeePrice.setText(Integer.toString(newPrice));
        } else if (name.equals("sport")) {
            sportPrice.setText(Integer.toString(newPrice));
        } else if (name.equals("premiumCoffee")) {
            premiumCoffeePrice.setText(Integer.toString(newPrice));
        } else if (name.equals("coke")) {
            cokePrice.setText(Integer.toString(newPrice));
        }
    }

    // Adminmenu.java에서 음료 이름 변경 시 실행하는 메소드
    public static void changeBeverageName(String name, String changeName) {
        Beverage selectedBeverage = null;
        // 해당 음료 검색
        if (name.equals(water.getName())) {
            selectedBeverage = water;
        } else if (name.equals(coffee.getName())) {
            selectedBeverage = coffee;
        } else if (name.equals(sport.getName())) {
            selectedBeverage = sport;
        } else if (name.equals(premiumCoffee.getName())) {
            selectedBeverage = premiumCoffee;
        } else if (name.equals(coke.getName())) {
            selectedBeverage = coke;
        } else {
            JOptionPane.showMessageDialog(null, "선택하신 음료가 존재하지 않습니다.");
        }
        // Beverage.java에서 setName메소드 이용해 이름 변경
        selectedBeverage.setName(changeName);
        JOptionPane.showMessageDialog(null, name + "을" + changeName + "으로 이름 변경을 완료하였습니다.");
    }

    public static Map<Integer, Money> getMoneyMap() {
        return MoneyMap;
    }

    // Adminmenu.java에서 재고를 admTable에 출력하기 위해 Beverage 객체 Map 전달 메소드
    public static Map<String, Beverage> getBeverageMap() {
        return beverageMap;
    }

    // Adminmenu.java에서 재고 추가 버튼 누를 시 실행하기 위한 메소드
    public static void increaseStock(String beverageName, int stockToAdd) {
        Beverage selectedBeverage = null;

        // 선택한 음료 찾기
        if (beverageName.equals(water.getName())) {
            selectedBeverage = water;
        } else if (beverageName.equals(coffee.getName())) {
            selectedBeverage = coffee;
        } else if (beverageName.equals(sport.getName())) {
            selectedBeverage = sport;
        } else if (beverageName.equals(premiumCoffee.getName())) {
            selectedBeverage = premiumCoffee;
        } else if (beverageName.equals(coke.getName())) {
            selectedBeverage = coke;
        } else {
            JOptionPane.showMessageDialog(null, "선택하신 음료가 존재하지 않습니다.");
            return;
        }

        if(stockToAdd + selectedBeverage.getStock() > 20) {
            JOptionPane.showMessageDialog(null, "재고는 20개를 넘을 수 없습니다.");
            return;
        }

        // 해당 음료 재고 추가
        for(int i=0;i<stockToAdd;i++) {
            selectedBeverage.increaseQuantity(); // Beverage.java의 increaseQuantity메소드 이용
        }
        JOptionPane.showMessageDialog(null, beverageName + "의 재고가 " + stockToAdd + "개 추가되었습니다.");

    }

    // (1)현재 구매 가능한 음료 상태를 확인하고 버튼 색상을 업데이트하는 메소드
    private void updateButtonStatus() {
        updateButtonColor(waterButton, "water");
        updateButtonColor(coffeeButton, "coffee");
        updateButtonColor(sportButton, "sport");
        updateButtonColor(premiumCoffeeButton, "premiumCoffee");
        updateButtonColor(cokeButton, "coke");
    }

    // (2)버튼 색깔 변경하는 메소드
    void updateButtonColor(JButton button, String beverageName) {
        // 구매 가능할시
        if (isBeverageAvailable(beverageName)) {
            button.setBackground(Color.GREEN);
            button.setText("구매");
        }
        // 구매 불가시
        else {
            button.setBackground(Color.RED);
        }
    }

    // (3)현재 잔액 그리고 재고 비교하여 구매 가능한지 알려주는 메소드
    private boolean isBeverageAvailable(String beverageName) {
        // 해당 음료 찾기
        Beverage beverage = beverageMap.get(beverageName);
        // 구매 가능한지 여부 판단해서 리턴
        return beverage != null && inputMoney >= beverage.getPrice() && beverage.getStock() > 0;
    }

    // 입력받은 화폐를 매게변수로 입력받아 처리하는 메소드
    // 사용자가 투입한 금액을 총합해주는 메소드
    public void insertMoney(int money, JLabel moneyLabel) {

        // 총 5000원 이하인 경우
        if(inputMoney+money <= 5000) {
            if (money == 10) {
                inputMoney += 10;
                c_10.countUp(money); // c_10 화폐 객체 개수 증가
                JOptionPane.showMessageDialog(null, "10원을 넣었습니다.\n현재 넣으신 금액 : " + inputMoney);

            }
            else if(money == 50) {
                inputMoney +=50;
                c_50.countUp(money); // c_50 화폐 객체 개수 증가
                JOptionPane.showMessageDialog(null, "50원을 넣었습니다.\n현재 넣으신 금액 : " + inputMoney);

            }
            else if(money == 100) {
                inputMoney +=100;
                c_100.countUp(money); // c_100 화폐 객체 개수 증가
                JOptionPane.showMessageDialog(null, "100원을 넣었습니다.\n현재 넣으신 금액 : " + inputMoney);
            }
            else if(money == 500) {
                inputMoney +=500;
                c_500.countUp(money); // c_500 화폐 객체 개수 증가
                JOptionPane.showMessageDialog(null, "500원을 넣었습니다.\n현재 넣으신 금액 : " + inputMoney);

            }
            else if(money == 1000) {
                // 입력 받은 money가 1000원인 경우 3000원이 넘지 않을 때
                if(count1000 <= 3) {
                    inputMoney +=1000;
                    count1000++;
                    c_1000.countUp(money); // c_1000 화폐 객체 개수 증가
                    JOptionPane.showMessageDialog(null, "1000원을 넣었습니다.\n현재 넣으신 금액 : " + inputMoney);

                }
                else {
                    JOptionPane.showMessageDialog(null, "더 이상 1000원짜리를 넣으실 수 없습니다. 다른 화폐 이용하세요.\n현재 넣으신 금액 : " + inputMoney);

                }
            }
            else {
                JOptionPane.showMessageDialog(null, "다른 화폐를 입력하세요.");

            }
        }
        else {
            JOptionPane.showMessageDialog(null, "더 이상 돈을 넣으실 수 없습니다.\n현재 넣으신 금액 : " + inputMoney);

        }


        // 현재 잔액 표시 창 최신화
        getinputMoney(moneyLabel);
        // 현재 구매 가능한 음료 표시 최신화
        updateButtonStatus();


    }

    // 입력받은 음료 이름을 매개변수로 입력받아 처리하는 메소드
    // 음료를 선택하고 구매할 수 있습니다.
    // 선택한 음료가 재고가 있고 잔액이 충분하면 음료를 제공하고 거스름돈을 계산합니다.
    public void purchaseBeverage(String name, JLabel moneyLabel) {
        Beverage selectedBeverage = null;

        // 선택한 음료 찾기
        if (name.equals(water.getName())) {
            selectedBeverage = water;
        } else if (name.equals(coffee.getName())) {
            selectedBeverage = coffee;
        } else if (name.equals(sport.getName())) {
            selectedBeverage = sport;
        } else if (name.equals(premiumCoffee.getName())) {
            selectedBeverage = premiumCoffee;
        } else if (name.equals(coke.getName())) {
            selectedBeverage = coke;
        } else {
            JOptionPane.showMessageDialog(null, "선택하신 음료가 존재하지 않습니다.");
            return;
        }

        // 선택한 음료가 재고가 있는지 확인
        if (selectedBeverage.getStock() <= 0) {
            JOptionPane.showMessageDialog(null, "해당 음료는 품절입니다.");
            return;
        }

        // 잔액이 선택한 음료의 가격보다 적은지 확인
        if (inputMoney < selectedBeverage.getPrice()) {
            JOptionPane.showMessageDialog(null, "잔액이 부족합니다.");
            return;
        }

        // 잔액에서 선택한 음료의 가격 차감
        inputMoney -= selectedBeverage.getPrice();

        // 선택한 음료의 재고 1 감소
        selectedBeverage.decreaseQuantity();

        JOptionPane.showMessageDialog(null, "고객님이 선택하신 " + name + " 나왔습니다. 현재 잔액은 " + inputMoney + "원입니다.");
        getinputMoney(moneyLabel); // 현재 잔액 최신화
        updateButtonStatus(); // 버튼 색상 최신화

        // 매출 기록 저장
        Adminmenu.saveSaleRecord(name);
        // 재고 소진 기록 저장
        if(selectedBeverage.isSoldOut()) {
            saveQuantityRecord(name);
        }

    }

    private void saveQuantityRecord(String name) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String record = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " " + name;

        try {
            FileWriter fileWriter =
                    new FileWriter("Quantity.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(record);
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // 현재 잔액을 반환하고 잔액을 null으로 만드는 메소드
    // inputMoney가 가리키던 Integer 객체는 더 이상 참조되지 않게 되므로,
    // 가비지 컬렉터가 메모리를 해제할 수 있다.
    public void returnMoney(JLabel moneyLabel) {
        int change = inputMoney;
        inputMoney = null; // 잔액을 반환하고 잔액을 null로 설정
        JOptionPane.showMessageDialog(null, "잔액 " + change + "돌려드립니다.");

        while (change > 0) {
            if (change >= 1000) {
                change -= 1000;
                c_1000.countDown(1000);
            }
            else if (change >= 500) {
                change -= 500;
                c_500.countDown(500);
            }
            else if (change >= 100) {
                change -= 100;
                c_100.countDown(100);
            }
            else if (change >= 50) {
                change -= 50;
                c_50.countDown(50);
            }
            else if (change >= 10) {
                change -= 10;
                c_10.countDown(10);
            }

            if (change == 0) {
                break;
            }
        }

        getinputMoney(moneyLabel);
    }

    // 자판기에 현재 잔액 띄워주기 위한 메소드
    public void getinputMoney(JLabel moneyLabel) {
        moneyLabel.setText("현재 잔액: " + inputMoney);
    }

    // 비밀번호와 입력된 문자열을 비교하여 같은지 확인
    public boolean PasswordMatch(String input) {
        return passwd.equals(input);
    }

    // 비밀번호 변경 시 셋팅 메소드
    public static void setPasswd(String newpasswd) {
        passwd = newpasswd;
    }

    public static void main(String[] args) {

        new VendingMachine();

    }




}

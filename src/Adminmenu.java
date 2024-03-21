//package term_project;
//import VendingMachine.*;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Adminmenu extends JFrame {


    public static JTable admTable;


    public Adminmenu() {
        setTitle("20204026 정명훈 자판기 관리자 메뉴");
        Container adm = getContentPane(); // 컨테이너 생성
        adm.setLayout(new FlowLayout());

        adm.setLayout(null);

        // 버튼 누르면 내용 출력해주는 JTable
        admTable = new JTable();
        admTable.setBackground(Color.WHITE);

        // JScrollPane을 사용하여 JTable을 감싸줍니다
        JScrollPane scrollPane = new JScrollPane(admTable);
        scrollPane.setBounds(160, 30, 500, 600);


        // 일별 매출
        JButton a = new JButton("일별 매출");
        a.setLocation(30, 10);
        a.setSize(100,40);
        a.addActionListener(e -> {
            // 버튼을 눌렀을 때 파일을 읽어와 JTable에 내용을 표시하는 로직
            try {
                FileReader fileReader = new FileReader("daily.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // 파일 내용을 읽어온 후 JTable에 표시하기
                String line;
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("일별 매출 내용"); // 열 추가
                while ((line = bufferedReader.readLine()) != null) {
                    model.addRow(new Object[]{line}); // 파일 내용을 행으로 추가
                }
                admTable.setModel(model);

                bufferedReader.close();
                fileReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        adm.add(a);

        // 월별 매출
        JButton a1 = new JButton("월별 매출");
        a1.setLocation(30, 70);
        a1.setSize(100,40);
        a1.addActionListener(e -> {
            // 버튼을 눌렀을 때 파일을 읽어와 JTable에 내용을 표시하는 로직
            try {
                FileReader fileReader = new FileReader("month.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // 파일 내용을 읽어온 후 JTable에 표시하기
                String line;
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("월별 매출 내용"); // 열 추가
                while ((line = bufferedReader.readLine()) != null) {
                    model.addRow(new Object[]{line}); // 파일 내용을 행으로 추가
                }
                admTable.setModel(model);

                bufferedReader.close();
                fileReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        adm.add(a1);

        // 각 음료 일별 매출
        JButton a2 = new JButton("음료 일별");
        a2.setLocation(30, 130);
        a2.setSize(100,40);
        a2.addActionListener(e -> {

            try {
                FileReader fileReader = new FileReader("beverage daily sales.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // 파일 내용을 읽어온 후 JTable에 표시하기
                String line;
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("음료 일별 매출 내용"); // 열 추가
                while ((line = bufferedReader.readLine()) != null) {
                    model.addRow(new Object[]{line}); // 파일 내용을 행으로 추가
                }
                admTable.setModel(model);

                bufferedReader.close();
                fileReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        adm.add(a2);

        // 각 음료 월별 매출
        JButton a3 = new JButton("음료 월별");
        a3.setLocation(30, 190);
        a3.setSize(100,40);
        a3.addActionListener(e -> {
            try {
                FileReader fileReader = new FileReader("beverage month sales.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // 파일 내용을 읽어온 후 JTable에 표시하기
                String line;
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("음료 월별 매출 내용"); // 열 추가
                while ((line = bufferedReader.readLine()) != null) {
                    model.addRow(new Object[]{line}); // 파일 내용을 행으로 추가
                }
                admTable.setModel(model);

                bufferedReader.close();
                fileReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        adm.add(a3);

        // 각 음료 재고 보충
        JButton a4 = new JButton("재고보충");
        a4.setLocation(30, 250);
        a4.setSize(100,40);
        a4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String beverageName = JOptionPane.showInputDialog(null, "음료의 이름을 입력하세요:");
                String stockInput = JOptionPane.showInputDialog(null, "추가할 재고의 수를 입력하세요:");

                if (stockInput != null && !stockInput.isEmpty()) {
                    try {
                        int stockToAdd = Integer.parseInt(stockInput); // 추가할 재고의 수량 정수형으로 저장

                        if (stockToAdd < 0) {
                            JOptionPane.showMessageDialog(null, "재고 수는 음수가 될 수 없습니다.");
                            return;
                        }

                        VendingMachine.increaseStock(beverageName, stockToAdd);
                        updateStockTable(admTable); // admTable에 재고 출력
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "유효하지 않은 재고 수입니다.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "재고 추가가 취소되었습니다.");
                }
            }
        });
        adm.add(a4);

        // 현재 화폐 현황
        JButton a5 = new JButton("화폐 현황");
        a5.setLocation(30, 310);
        a5.setSize(100,40);
        a5.addActionListener(e -> {
            MoneyTable(admTable);
        });
        adm.add(a5);

        // 화폐 수금 버튼
        JButton a6 = new JButton("수금");
        a6.setLocation(30, 370);
        a6.setSize(100,40);
        a6.addActionListener(e -> {
            try {
                collectMoney();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "유효하지 않은 금액입니다.");
            }
        });
        adm.add(a6);

        // 각 음료 이름, 가격 변경, 비밀번호 변경
        JButton a7 = new JButton("변경");
        a7.setLocation(30, 430);
        a7.setSize(100,40);
        a7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String changeMenu = JOptionPane.showInputDialog(null, "번호를 입력하세요.(1.이름변경 2.가격변경 3.비밀번호변경)");

                if (changeMenu != null && !changeMenu.isEmpty()) {
                    int change = Integer.parseInt(changeMenu);

                    // 이름 변경
                    if (change == 1) {
                        String name = JOptionPane.showInputDialog(null, "이름 변경할 제품을 입력하세요: ");
                        String changeName = JOptionPane.showInputDialog(null, "변경할 이름을 입력하세요: ");
                        VendingMachine.changeBeverageName(name, changeName);
                    }

                    // 가격 변경
                    else if (change == 2) {
                        String name = JOptionPane.showInputDialog(null, "가격 변경할 제품을 입력하세요: ");
                        String changePrice = JOptionPane.showInputDialog(null, "변경할 가격을 입력하세요: ");
                        if (changePrice != null) {
                            try {
                                int newPrice = Integer.parseInt(changePrice);
                                if (newPrice < 0) {
                                    JOptionPane.showMessageDialog(null, "가격은 음수가 될 수 없습니다.");
                                    return;
                                }
                                if (newPrice > 5000) {
                                    JOptionPane.showMessageDialog(null, "가격은 5000원을 초과할 수 없습니다.");
                                    return;
                                }
                                if (newPrice % 10 != 0) {
                                    JOptionPane.showMessageDialog(null, "가격은 10원 단위로 입력되어야 합니다.");
                                    return;
                                }
                                VendingMachine.changeBeveragePrice(name, newPrice);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "유효하지 않은 가격입니다.");
                            }
                        }
                    }


                    // 비밀번호 변경
                    else if (change == 3) {
                        String name1 = JOptionPane.showInputDialog(null, "특수문자 하나를 포함하여 변경할 비밀번호를 입력하세요(!,@,#): ");
                        String name2 = JOptionPane.showInputDialog(null, "변경할 비밀번호 한 번 더 입력하세요: ");

                        // 특수문자가 포함되어 있는지 검사
                        if (name2.matches(".*[!,@,#].*")) {
                            if (name1.equals(name2)) {
                                VendingMachine.setPasswd(name2);
                                JOptionPane.showMessageDialog(null, "비밀번호 변경을 완료하였습니다.");
                            } else {
                                JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "특수문자 하나가 필요합니다. (!,@,#)");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "작업이 취소되었습니다.");
                }
            }
        });
        adm.add(a7);

        // 파일 쓰기 버튼
        JButton a8 = new JButton("입력");
        a8.setLocation(30, 490);
        a8.setSize(100,40);
        a8.addActionListener(e -> {
            String wantWrite = JOptionPane.showInputDialog(null, "번호를 입력하세요.(1.일별매출 2.월별매출 3.음료일별 4.음료월별)");

            if (wantWrite != null && !wantWrite.isEmpty()) {

                int want = Integer.parseInt(wantWrite);

                // 일별 매출 작성
                if(want == 1) {
                    try {

                        // 파일 쓰기
                        FileWriter fileWriter = new FileWriter("daily.txt", true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                        // 새로운 내용 입력
                        String newContent = JOptionPane.showInputDialog(null, "새로운 내용 입력:");
                        if (newContent != null && !newContent.isEmpty()) {
                            // 파일에 새로운 내용 저장
                            bufferedWriter.write(newContent);
                            bufferedWriter.newLine();
                        }

                        // 버퍼를 비우고 파일에 내용 저장
                        bufferedWriter.flush();

                        // 파일 닫기
                        bufferedWriter.close();
                        fileWriter.close();

                        // 파일을 다시 읽어서 JTable에 표시하기
                        FileReader fileReader = new FileReader("daily.txt");
                        BufferedReader bufferedReader = new BufferedReader(fileReader);

                        // 파일 내용을 읽어온 후 JTable에 표시하기
                        DefaultTableModel model = new DefaultTableModel();
                        model.addColumn("일별 매출 내용"); // 열 추가
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            model.addRow(new Object[]{line}); // 파일 내용을 행으로 추가
                        }

                        bufferedReader.close();
                        fileReader.close();

                        // JTable에 모델 설정
                        admTable.setModel(model);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                // 월별 매출 작성
                else if(want == 2) {
                    try {

                        // 파일 쓰기
                        FileWriter fileWriter = new FileWriter("month.txt", true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                        // 새로운 내용 입력
                        String newContent = JOptionPane.showInputDialog(null, "새로운 내용 입력:");
                        if (newContent != null && !newContent.isEmpty()) {
                            // 파일에 새로운 내용 저장
                            bufferedWriter.write(newContent);
                            bufferedWriter.newLine();
                        }

                        // 버퍼를 비우고 파일에 내용 저장
                        bufferedWriter.flush();

                        // 파일 닫기
                        bufferedWriter.close();
                        fileWriter.close();

                        // 파일을 다시 읽어서 JTable에 표시하기
                        FileReader fileReader = new FileReader("month.txt");
                        BufferedReader bufferedReader = new BufferedReader(fileReader);

                        // 파일 내용을 읽어온 후 JTable에 표시하기
                        DefaultTableModel model = new DefaultTableModel();
                        model.addColumn("월별 매출 내용"); // 열 추가
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            model.addRow(new Object[]{line}); // 파일 내용을 행으로 추가
                        }

                        bufferedReader.close();
                        fileReader.close();

                        // JTable에 모델 설정
                        admTable.setModel(model);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                // 음료 일별 매출 작성
                else if(want == 3) {
                    try {

                        // 파일 쓰기
                        FileWriter fileWriter = new FileWriter("beverage daily sales.txt",true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                        // 새로운 내용 입력
                        String newContent = JOptionPane.showInputDialog(null, "새로운 내용 입력:");
                        if (newContent != null && !newContent.isEmpty()) {
                            // 파일에 새로운 내용 저장
                            bufferedWriter.write(newContent);
                            bufferedWriter.newLine();
                        }

                        // 버퍼를 비우고 파일에 내용 저장
                        bufferedWriter.flush();

                        // 파일 닫기
                        bufferedWriter.close();
                        fileWriter.close();

                        // 파일을 다시 읽어서 JTable에 표시하기
                        FileReader fileReader = new FileReader("beverage daily sales.txt");
                        BufferedReader bufferedReader = new BufferedReader(fileReader);

                        // 파일 내용을 읽어온 후 JTable에 표시하기
                        DefaultTableModel model = new DefaultTableModel();
                        model.addColumn("음료 일별 매출 내용"); // 열 추가
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            model.addRow(new Object[]{line}); // 파일 내용을 행으로 추가
                        }

                        bufferedReader.close();
                        fileReader.close();

                        // JTable에 모델 설정
                        admTable.setModel(model);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                // 음료 월별 매출 작성
                else if(want == 4) {
                    try {

                        // 파일 쓰기
                        FileWriter fileWriter = new FileWriter("beverage month sales.txt",true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                        // 새로운 내용 입력
                        String newContent = JOptionPane.showInputDialog(null, "새로운 내용 입력:");
                        if (newContent != null && !newContent.isEmpty()) {
                            // 파일에 새로운 내용 저장
                            bufferedWriter.write(newContent);
                            bufferedWriter.newLine();
                        }

                        // 버퍼를 비우고 파일에 내용 저장
                        bufferedWriter.flush();

                        // 파일 닫기
                        bufferedWriter.close();
                        fileWriter.close();

                        // 파일을 다시 읽어서 JTable에 표시하기
                        FileReader fileReader = new FileReader("beverage month sales.txt");
                        BufferedReader bufferedReader = new BufferedReader(fileReader);

                        // 파일 내용을 읽어온 후 JTable에 표시하기
                        DefaultTableModel model = new DefaultTableModel();
                        model.addColumn("음료 월별 매출 내용"); // 열 추가
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            model.addRow(new Object[]{line}); // 파일 내용을 행으로 추가
                        }

                        bufferedReader.close();
                        fileReader.close();

                        // JTable에 모델 설정
                        admTable.setModel(model);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "작업이 취소되었습니다.");
            }
        });
        adm.add(a8);

        // 다시 VendingMachine 창으로 이동
        JButton a9 = new JButton("종료");
        a9.setLocation(30, 550);
        a9.setSize(100,40);
        a9.addActionListener(e -> {
            VendingMachine.getInstance().setVisible(true);
            ((JButton) e.getSource()).getTopLevelAncestor().setVisible(false);
        });
        adm.add(a9);

        JButton a10 = new JButton("실시간");
        a10.setLocation(30, 610);
        a10.setSize(100,40);
        a10.addActionListener(e -> {
            String live = JOptionPane.showInputDialog(null, "번호를 입력하세요.(1.판매현황 2.재고소진) ");

            if (live != null && !live.isEmpty()) {

                int want = Integer.parseInt(live);

                // 판매현황
                if(want == 1) {
                    try {
                        FileReader fileReader = new FileReader("data.txt");
                        BufferedReader bufferedReader = new BufferedReader(fileReader);

                        // 파일 내용을 읽어온 후 JTable에 표시하기
                        String line;
                        DefaultTableModel model = new DefaultTableModel();
                        model.addColumn("실시간 매출 내용"); // 열 추가
                        while ((line = bufferedReader.readLine()) != null) {
                            model.addRow(new Object[]{line}); // 파일 내용을 행으로 추가
                        }
                        admTable.setModel(model);

                        bufferedReader.close();
                        fileReader.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    DefaultTableModel model = (DefaultTableModel) admTable.getModel();
                    int rowCount = model.getRowCount();

                    try {
                        FileWriter fileWriter = new FileWriter("data.txt", true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                        // 테이블 내용을 파일에 저장
                        for (int row = 0; row < rowCount; row++) {
                            StringBuilder line = new StringBuilder();
                            for (int column = 0; column < model.getColumnCount(); column++) {
                                line.append(model.getValueAt(row, column));
                                if (column < model.getColumnCount() - 1) {
                                    line.append(" ");
                                }
                            }
                            bufferedWriter.write(line.toString());
                            bufferedWriter.newLine();
                        }

                        // 버퍼를 비우고 파일에 내용 저장
                        bufferedWriter.flush();

                        // 파일 닫기
                        bufferedWriter.close();
                        fileWriter.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                // 재고소진
                else if(want == 2) {
                    readQuantityRecords(admTable);
                }
            }

        });
        adm.add(a10);

        adm.add(scrollPane);

        adm.setBackground(Color.lightGray);
        setSize(700,700);
        setVisible(true);
    }



    // 음료 구매 시 재고 현황을 admTable에 최신화하기 위해 admTable을 반환해주는 메소드
    public JTable getStockTable() {
        return admTable;
    }

    // 음료 재고를 admTable에 출력하기 위한 메소드
    public static void updateStockTable(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("음료"); // 음료 열 추가
        model.addColumn("재고"); // 재고 열 추가

        // 각 음료의 이름과 재고를 가져와 테이블에 추가
        for (Beverage beverage : VendingMachine.getBeverageMap().values()) {
            model.addRow(new Object[]{beverage.getName(), beverage.getStock()});
        }

        updateBeverageTable(admTable);
        table.setModel(model);
    }

    // BeverageMachine.java에서 메소드 실행 시에 admTable에 최신화 하기 위한 메소드
    public static void updateTable() {
        updateBeverageTable(admTable);
        updateMoneyTable(admTable);
    }

    // 음료 재고를 업데이트하고 admTable을 최신화하는 메소드
    public static void updateBeverageTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) admTable.getModel();

        // 기존 데이터 모두 제거
        model.setRowCount(0);

        // 음료 정보를 가져와서 테이블에 추가
        for (Beverage beverage : VendingMachine.beverageMap.values()) {
            model.addRow(new Object[]{beverage.getName(), beverage.getPrice(), beverage.getStock()});
        }

        // 테이블 모델 갱신
        table.setModel(model);
    }

    // 화폐 현황 출력 메소드
    public void MoneyTable(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("화폐"); // 화폐 열 추가
        model.addColumn("수량"); // 수량 열 추가

        // 각 화폐의 이름과 수량을 가져와 테이블에 추가
        // VendingMachine.java에서 MoneyMap를 가져오기
        for (Map.Entry<Integer, Money> entry : VendingMachine.MoneyMap.entrySet()) {
            // 화폐를 받아와서 변수에 저장
            int denomination = entry.getKey();
            Money money = entry.getValue();
            String denominationLabel = denomination + "원";
            // 화폐의 수량을 받을 변수 선언
            int quantity = 0;
            // 해당 화폐 수량 Money.java의 getC_00() 메소드로 받아옴
            switch (denomination) {
                case 10:
                    quantity = money.getC_10();
                    break;
                case 50:
                    quantity = money.getC_50();
                    break;
                case 100:
                    quantity = money.getC_100();
                    break;
                case 500:
                    quantity = money.getC_500();
                    break;
                case 1000:
                    quantity = money.getC_1000();
                    break;
            }

            model.addRow(new Object[]{denominationLabel, quantity});
        }

        updateMoneyTable(admTable);
        admTable.setModel(model);
    }

    // 화폐 수금 메소드
    public void collectMoney() {
        int denomination = Integer.parseInt(JOptionPane.showInputDialog("화폐를 입력하세요 (10, 50, 100, 500, 1000):"));
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("수량을 입력하세요:"));

        if (quantity < 0) { // 수량이 음수인 경우
            JOptionPane.showMessageDialog(null, "음수 수량은 입력할 수 없습니다.");
            return;
        }

        if (VendingMachine.MoneyMap.containsKey(denomination)) {
            // VendingMachine.java에서 MoneyMap를 가져오기
            Money money = VendingMachine.MoneyMap.get(denomination);
            int currentQuantity = 0;

            // 해당 화폐 찾기
            if (denomination == 10) {
                currentQuantity = money.getC_10();
            } else if (denomination == 50) {
                currentQuantity = money.getC_50();
            } else if (denomination == 100) {
                currentQuantity = money.getC_100();
            } else if (denomination == 500) {
                currentQuantity = money.getC_500();
            } else if (denomination == 1000) {
                currentQuantity = money.getC_1000();
            }

            // 현재 화폐 수량과 수금 할 수량과 비교
            if (currentQuantity >= quantity) {

                // 원하는 수금량을 차감한 후 남은 수량 저장
                int newQuantity = currentQuantity - quantity;

                // 최소한의 화폐를 남기기 위한 조건
                if(newQuantity < 5) {
                    JOptionPane.showMessageDialog(null, "수량이 부족합니다. 현재 화폐 수량: " + currentQuantity + ": 기본 5개 수량은 남겨둬야 합니다.");
                    return;
                }

                if (denomination == 10) {
                    money.setC_10(newQuantity);
                } else if (denomination == 50) {
                    money.setC_50(newQuantity);
                } else if (denomination == 100) {
                    money.setC_100(newQuantity);
                } else if (denomination == 500) {
                    money.setC_500(newQuantity);
                } else if (denomination == 1000) {
                    money.setC_1000(newQuantity);
                }

                JOptionPane.showMessageDialog(null, quantity + "개의 " + denomination + "원이 수금되었습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "수량이 부족합니다. 현재 화폐 수량: " + currentQuantity);
            }
        } else {
            JOptionPane.showMessageDialog(null, "유효하지 않은 화폐입니다.");
        }
        // 화폐 수량 최신화
        updateMoneyTable(admTable);
    }

    // 화폐 수량을 업데이트하고 admTable을 최신화하는 메소드
    public static void updateMoneyTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // 기존 데이터 모두 제거
        model.setRowCount(0);

        // 화폐 개수를 가져와서 테이블에 추가
        for (Map.Entry<Integer, Money> entry : VendingMachine.MoneyMap.entrySet()) {
            int denomination = entry.getKey();
            Money money = entry.getValue();
            model.addRow(new Object[]{denomination, money.getCount(denomination)});
        }

        // 테이블 모델 갱신
        table.setModel(model);
    }

    // 실시간 판매 현황
    public static void saveSaleRecord(String name) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String saleDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try {
            FileWriter fileWriter = new FileWriter("data.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // 매출 기록 저장
            String saleRecord = saleDateTime + " " + name;
            bufferedWriter.write(saleRecord);
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // 재고 소진 날짜 파일에서 읽어와 출력 메소드
    public static void readQuantityRecords(JTable table) {
        try {
            FileReader fileReader = new FileReader("Quantity.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("날짜");
            model.addColumn("시간");
            model.addColumn("음료");

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(" ");

                if (data.length >= 3) {
                    String date = data[0];
                    String time = data[1];
                    String beverage = data[2];

                    model.addRow(new Object[]{date, time, beverage});
                }
            }

            bufferedReader.close();
            fileReader.close();

            table.setModel(model);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}


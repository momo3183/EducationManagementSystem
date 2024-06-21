package admin;

import java.awt.*;
import javax.swing.*;
import main.DBCon;
import java.awt.event.*;
import java.util.Vector;

public class GradeManagement extends JPanel {
    private JTable gradeTable;
    private JTextField searchField;

    public GradeManagement() {
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchField = new JTextField(20);
        JButton searchButton = new JButton("搜索");
        searchPanel.add(new JLabel("搜索成绩:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        Vector<String> columnNames = new Vector<>();
        columnNames.add("学号");
        columnNames.add("姓名");
        columnNames.add("课程号");
        columnNames.add("课程名");
        columnNames.add("成绩");

        Vector<Vector<String>> data = DBCon.queryData2("SELECT * FROM score");
        gradeTable = new JTable(data, columnNames);
        add(new JScrollPane(gradeTable), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        add(controlPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            Vector<Vector<String>> searchData = DBCon.queryData2(
                "SELECT * FROM score WHERE " +
                "`学号` LIKE '%" + searchText + "%' OR " +
                "`姓名` LIKE '%" + searchText + "%' OR " +
                "`课程号` LIKE '%" + searchText + "%' OR " +
                "`课程名` LIKE '%" + searchText + "%'"
            );
            gradeTable.setModel(new javax.swing.table.DefaultTableModel(searchData, columnNames));
        });
    }
}


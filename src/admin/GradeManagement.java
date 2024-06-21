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
        JButton searchButton = new JButton("����");
        searchPanel.add(new JLabel("�����ɼ�:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        Vector<String> columnNames = new Vector<>();
        columnNames.add("ѧ��");
        columnNames.add("����");
        columnNames.add("�γ̺�");
        columnNames.add("�γ���");
        columnNames.add("�ɼ�");

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
                "`ѧ��` LIKE '%" + searchText + "%' OR " +
                "`����` LIKE '%" + searchText + "%' OR " +
                "`�γ̺�` LIKE '%" + searchText + "%' OR " +
                "`�γ���` LIKE '%" + searchText + "%'"
            );
            gradeTable.setModel(new javax.swing.table.DefaultTableModel(searchData, columnNames));
        });
    }
}


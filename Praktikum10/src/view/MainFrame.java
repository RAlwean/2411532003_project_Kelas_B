package view;

import controller.ThreadPoolManager;
import model.Task;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTextArea logArea;
    private DefaultListModel<String> statusModel;

    private JTextField txtThreads;
    private JTextField txtTasks;
    private JLabel lblFooter;

    private ThreadPoolManager manager = new ThreadPoolManager();

    public MainFrame() {
        setTitle("Aplikasi ThreadPool dengan GUI");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initUI();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initUI() {
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTop.setBorder(BorderFactory.createTitledBorder("Pengaturan"));

        txtThreads = new JTextField("3", 5);
        txtTasks = new JTextField("20", 5);

        JButton btnStart = new JButton("Mulai Proses");
        JButton btnClear = new JButton("Bersihkan Log");

        panelTop.add(new JLabel("Jumlah Thread:"));
        panelTop.add(txtThreads);
        panelTop.add(new JLabel("Jumlah Tugas:"));
        panelTop.add(txtTasks);
        panelTop.add(btnStart);
        panelTop.add(btnClear);
        statusModel = new DefaultListModel<>();
        JList<String> statusList = new JList<>(statusModel);
        JScrollPane scrollStatus = new JScrollPane(statusList);
        scrollStatus.setBorder(BorderFactory.createTitledBorder("Status Tugas"));
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(logArea);
        scrollLog.setBorder(BorderFactory.createTitledBorder("Log Aktivitas"));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                scrollStatus, scrollLog);
        splitPane.setDividerLocation(300);

        lblFooter = new JLabel("Siap untuk proses baru...");

        add(panelTop, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(lblFooter, BorderLayout.SOUTH);
        btnStart.addActionListener(e -> startProcess());
        btnClear.addActionListener(e -> logArea.setText(""));
    }

    private void startProcess() {
        int nThreads = Integer.parseInt(txtThreads.getText());
        int nTasks = Integer.parseInt(txtTasks.getText());

        statusModel.clear();
        logArea.append("=== Memulai Proses Baru ===\n");
        lblFooter.setText("Memproses " + nTasks + " tugas dengan " + nThreads + " threads...");

        manager.createPool(nThreads);

        for (int i = 1; i <= nTasks; i++) {
            int id = i;
            statusModel.addElement("Task #" + id + " - Waiting");

            Task task = new Task(id, new Task.TaskListener() {
                @Override
                public void onTaskStatusUpdated(int taskId, String status) {
                    SwingUtilities.invokeLater(() ->
                            statusModel.set(taskId - 1, "Task #" + taskId + " - " + status)
                    );
                }

                @Override
                public void onTaskLog(String log) {
                    SwingUtilities.invokeLater(() -> logArea.append(log + "\n"));
                }
            });

            manager.submit(task);
        }

        manager.shutdown();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

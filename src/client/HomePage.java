package client;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    protected JLabel HUserName;
    protected JPanel ChatWin;
    protected TextArea ChatEdit;
    protected JButton btnUserGroup, btnMessageGroup, btnPicture, btnSend;
    Data db = new Data();
    private JFileChooser chooser = new JFileChooser();

    public HomePage(Customer cus) {
        super("Home");
        initComponent(cus);
    }

    public void initComponent(Customer cus) {
        BorderLayout function_layout = new BorderLayout();
        JPanel function_window = new JPanel(function_layout); // friend list container
        this.add(function_window, BorderLayout.WEST);
        JPanel chat_window = new JPanel(); // chat window container
        this.add(chat_window, BorderLayout.CENTER);


        final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        // friend list
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = tabbedPane.getSelectedIndex();
                String title = tabbedPane.getTitleAt(selectedIndex);
                System.out.println(title);
            }
        });
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        //�������
        HUserName = new JLabel("User " + Customer.getUserName() + "�û�");
        btnUserGroup = new JButton("�û�����");
        btnMessageGroup = new JButton("��ϢȺ��");

        //��JList�����û��б�
        final JList<String> userlist = new JList<String>();

        //������ѡ��С
        userlist.setPreferredSize(new Dimension(200, 200));

        //����ɼ�ϵĶ�ѡ
        ListSelectionModel listSelectionModel;
        userlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listSelectionModel = userlist.getSelectionModel();

        //������ʾ���ݣ��ڲ����Զ���װ��ListModel)
        int lengthoffriends = cus.friends.size();
        String[] usernames = new String[lengthoffriends];
        int i = 0;
        for (Integer friend_id : cus.friends) {
            Customer friend = db.getCustomerbyId(friend_id);
            usernames[i++] = friend.getUserName();
        }
        userlist.setListData(usernames);

        //���ѡ��ѡ��״̬���ı�ļ�����
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //��ȡ���б�ѡ�е�ѡ������
                int[] indices = userlist.getSelectedIndices();
                //��ȡѡ�����ݵ�ListModel
                ListModel<String> listModel = userlist.getModel();
                //���ѡ�е�ѡ��
                for (int index : indices) {
                    System.out.println("ѡ�У�" + index + " = " + listModel.getElementAt(index));
                }
                System.out.println();
            }
        });

        // ����Ĭ��ѡ����
        userlist.setSelectedIndex(1);
        userlist.setVisibleRowCount(10);
        JScrollPane UserListPanel = new JScrollPane(userlist);
//		UserListPanel.setHorizontalScrollBarPolicy(i);

        Icon imageIcon = null;
        ChatWin = new JPanel(); // ��Ϣ��ʾ��
        ChatWin.setFocusable(false);
        ChatEdit = new TextArea(10, 20); // ��Ϣ�����
        btnPicture = new JButton("���ͼƬ"); // ͼƬѡ��ť
        btnPicture.addActionListener(new btnListener());
        btnSend = new JButton("����"); // ���Ͱ�ť

        JPanel tabPanelA = new JPanel(new BorderLayout());
        JPanel chatwindow = new JPanel(new BorderLayout());
        JPanel func_edit = new JPanel(new BorderLayout()); // ������壬����С��ť����Ϣ�����
        JPanel func = new JPanel(); // С��ť���

        tabPanelA.add(UserListPanel, BorderLayout.WEST);
        tabPanelA.add(chatwindow, BorderLayout.CENTER);
        chatwindow.add(ChatWin, BorderLayout.CENTER);
        chatwindow.add(func_edit, BorderLayout.SOUTH);
        func_edit.add(func, BorderLayout.NORTH);
        func_edit.add(new JScrollPane(ChatEdit), BorderLayout.CENTER);
        tabbedPane.addTab("�û��б�", imageIcon, tabPanelA, "����鿴�û��б�"); // ����ǩ�����ӵ�ѡ���
        final JLabel tabLabelB = new JLabel();
        tabLabelB.setText("�û�����");
        tabbedPane.addTab("�û�����", imageIcon, tabLabelB, "����鿴�û�����");
        final JLabel tabLabelC = new JLabel();
        tabLabelC.setText("��ϢȺ��");
        tabbedPane.addTab("��ϢȺ��", imageIcon, tabLabelC, "����鿴��ϢȺ��");
        tabbedPane.setSelectedIndex(0);
        func.add(btnPicture);
        func.add(btnSend);

        this.setSize(600, 600);
        this.setLocation(300, 400);
        this.setVisible(true);
    }

    public class btnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

            if (btn == btnPicture) {
                FileNameExtensionFilter PicFilter = new FileNameExtensionFilter("ͼ���ļ���JPG/PNG/BMP/GIF��", "JPG", "JPEG", "PNG", "BMP", "GIF"); // �����ļ���������ֻ�г�ͼƬ
                chooser.setFileFilter(PicFilter);
                int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String FileName = chooser.getSelectedFile().getPath();
                    System.out.println(FileName);
                    ChatEdit.setText(FileName);
                }
            }
        }
    }
}
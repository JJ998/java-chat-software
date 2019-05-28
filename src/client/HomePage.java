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
    private Customer user;

    public HomePage(Customer cus) {
        super("Home");
        user = cus;
        initComponent();
    }

    public void initComponent() {
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

        HUserName = new JLabel("User " + user.getUserName());
        btnUserGroup = new JButton("Friends");
        btnMessageGroup = new JButton("Message");

        //user list
        final JList<String> userlist = new JList<String>();
        userlist.setPreferredSize(new Dimension(200, 200));

        ListSelectionModel listSelectionModel;
        userlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listSelectionModel = userlist.getSelectionModel();

        int lengthoffriends = user.friends.size();
        String[] usernames = new String[lengthoffriends];
        int i = 0;
        for (Integer friend_id : user.friends) {
            Customer friend = Data.getCustomerbyId(friend_id);
            if (friend != null) usernames[i++] = friend.getUserName();
        }
        userlist.setListData(usernames);

        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] indices = userlist.getSelectedIndices();
                ListModel<String> listModel = userlist.getModel();
                for (int index : indices) {
                    System.out.println("user" + index + " = " + listModel.getElementAt(index));
                }
            }
        });

        userlist.setSelectedIndex(1);
        userlist.setVisibleRowCount(10);
        JScrollPane UserListPanel = new JScrollPane(userlist);
//		UserListPanel.setHorizontalScrollBarPolicy(i);

        Icon imageIcon = null;
        ChatWin = new JPanel();
        ChatWin.setFocusable(false);
        ChatEdit = new TextArea(10, 20);
        btnPicture = new JButton("send files");
        btnPicture.addActionListener(new btnListener());
        btnSend = new JButton("send");

        JPanel tabPanelA = new JPanel(new BorderLayout());
        JPanel chatwindow = new JPanel(new BorderLayout());
        JPanel func_edit = new JPanel(new BorderLayout());
        JPanel func = new JPanel();

        tabPanelA.add(UserListPanel, BorderLayout.WEST);
        tabPanelA.add(chatwindow, BorderLayout.CENTER);
        chatwindow.add(ChatWin, BorderLayout.CENTER);
        chatwindow.add(func_edit, BorderLayout.SOUTH);
        func_edit.add(func, BorderLayout.NORTH);
        func_edit.add(new JScrollPane(ChatEdit), BorderLayout.CENTER);
        tabbedPane.addTab("Messages", imageIcon, tabPanelA, "Messages List");
        final JLabel tabLabelB = new JLabel();
        tabLabelB.setText("Friends");
        tabbedPane.addTab("Friends", imageIcon, tabLabelB, "Friends List");
        final JLabel tabLabelC = new JLabel();
        tabLabelC.setText("Group");
        tabbedPane.addTab("Group", imageIcon, tabLabelC, "Group send");
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
                FileNameExtensionFilter PicFilter = new FileNameExtensionFilter(".JPG/PNG/BMP/GIF", "JPG", "JPEG", "PNG", "BMP", "GIF");
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
package Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileFilter;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HomePage extends JFrame{
	protected JLabel HUserName;
	protected JPanel ChatWin;
	protected TextArea ChatEdit;
	protected JButton btnUserGroup,btnMessageGroup,btnPicture,btnSend;
	private JFileChooser chooser = new JFileChooser();
	Data db = new Data();
	
	public HomePage(Customer cus) {
		super("主页面");
		initComponent(cus);
	}
	
	//初始化控件
	public void initComponent(Customer cus) {
		//划分区域
		BorderLayout function_layout = new BorderLayout();
		JPanel function_window = new JPanel(function_layout); // 功能区
		this.add(function_window, BorderLayout.WEST);
		JPanel chat_window = new JPanel(); // 聊天窗
		this.add(chat_window, BorderLayout.CENTER);
		
		
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		//设置选项卡标签的布局方式
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//获得被选中选项卡的索引
				int selectedIndex = tabbedPane.getSelectedIndex();
				//获得制定索引的选项卡标签
				String title = tabbedPane.getTitleAt(selectedIndex);
				System.out.println(title);
			}
		});
		getContentPane().add(tabbedPane,BorderLayout.CENTER);
	
		//具体填充
		HUserName = new JLabel("亲爱的：" + Customer.getUserName() + "用户");
		btnUserGroup = new JButton("用户分组");
		btnMessageGroup = new JButton("信息群发");
	
		//用JList构造用户列表
		final JList<String> userlist = new JList<String>();
		
		//设置首选大小
		userlist.setPreferredSize(new Dimension(200,200));
		
		//允许可间断的多选
		ListSelectionModel listSelectionModel;
		userlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listSelectionModel = userlist.getSelectionModel();
		
		//设置显示数据（内部将自动封装成ListModel)
		int lengthoffriends = cus.friends.size();
		String[] usernames = new String[lengthoffriends];
		int i = 0;
		for(Integer friend_id : cus.friends) {
			Customer friend = db.getCustomerbyId(friend_id);
			usernames[i++] = friend.getUserName();
		}
		userlist.setListData(usernames);
		
		//添加选项选中状态被改变的监听器
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//获取所有被选中的选项索引
				int[] indices = userlist.getSelectedIndices();
				//获取选项数据的ListModel
				ListModel<String> listModel = userlist.getModel();
				//输出选中的选项
				for(int index : indices) {
					System.out.println("选中：" + index + " = " + listModel.getElementAt(index));
				}
				System.out.println();
			}
		});
		
		// 设置默认选中项
		userlist.setSelectedIndex(1);
		userlist.setVisibleRowCount(10);
		JScrollPane UserListPanel = new JScrollPane(userlist);
//		UserListPanel.setHorizontalScrollBarPolicy(i);

		Icon imageIcon = null;
		ChatWin = new JPanel(); // 信息显示框
		ChatWin.setFocusable(false);
		ChatEdit = new TextArea(10, 20); // 信息输入框
		btnPicture = new JButton("添加图片"); // 图片选择按钮
		btnPicture.addActionListener(new btnListener());
		btnSend = new JButton("发送"); // 发送按钮
		
		JPanel tabPanelA = new JPanel(new BorderLayout());
		JPanel chatwindow = new JPanel(new BorderLayout());
		JPanel func_edit = new JPanel(new BorderLayout()); // 功能面板，包括小按钮和信息输入框
		JPanel func = new JPanel(); // 小按钮面板
		
		tabPanelA.add(UserListPanel, BorderLayout.WEST);
		tabPanelA.add(chatwindow, BorderLayout.CENTER);
		chatwindow.add(ChatWin, BorderLayout.CENTER);
		chatwindow.add(func_edit, BorderLayout.SOUTH);
		func_edit.add(func, BorderLayout.NORTH);
		func_edit.add(new JScrollPane(ChatEdit), BorderLayout.CENTER);
		tabbedPane.addTab("用户列表",imageIcon,tabPanelA,"点击查看用户列表"); // 将标签组件添加到选项卡中
		final JLabel tabLabelB = new JLabel();
		tabLabelB.setText("用户分组");
		tabbedPane.addTab("用户分组",imageIcon,tabLabelB,"点击查看用户分组");
		final JLabel tabLabelC = new JLabel();
		tabLabelC.setText("信息群发");
		tabbedPane.addTab("信息群发",imageIcon,tabLabelC,"点击查看信息群发");
		tabbedPane.setSelectedIndex(0);
		func.add(btnPicture);
		func.add(btnSend);
		
        this.setSize(600,600);
    	this.setLocation(300,400);
    	this.setVisible(true);
	}
	
	public class btnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			
			if(btn == btnPicture) {
				FileNameExtensionFilter PicFilter = new FileNameExtensionFilter("图像文件（JPG/PNG/BMP/GIF）", "JPG", "JPEG", "PNG", "BMP", "GIF"); // 设置文件过滤器，只列出图片
				chooser.setFileFilter(PicFilter);
				int result = chooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION){
					String FileName = chooser.getSelectedFile().getPath();
					System.out.println(FileName);
					ChatEdit.setText(FileName);
				}
			}
		}
	}
}
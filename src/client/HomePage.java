package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class HomePage extends JFrame implements ActionListener{
	protected JLabel HUserName;
	protected JButton btnUserGroup,btnMessageGroup;
	Data db = new Data();
	
	public HomePage(Customer cus) {
		super("��ҳ��");
		initComponent(cus);
	}
	
	//��ʼ���ؼ�
	public void initComponent(Customer cus) {
		//��������
		BorderLayout function_layout = new BorderLayout();
		JPanel function_window = new JPanel(function_layout); // ������
		this.add(function_window, BorderLayout.WEST);
		JPanel chat_window = new JPanel(); // ���촰
		this.add(chat_window, BorderLayout.CENTER);	
		
		
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		//����ѡ���ǩ�Ĳ��ַ�ʽ
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//��ñ�ѡ��ѡ�������
				int selectedIndex = tabbedPane.getSelectedIndex();
				//����ƶ�������ѡ���ǩ
				String title = tabbedPane.getTitleAt(selectedIndex);
				System.out.println(title);
			}
		});
		getContentPane().add(tabbedPane,BorderLayout.CENTER);
		
		
		
		
		//�������
		HUserName = new JLabel("�װ��ģ�" + Customer.getUserName() + "�û�");
		btnUserGroup = new JButton("�û�����");
		btnMessageGroup = new JButton("��ϢȺ��");
//		left_function.add(HUserName);
//		left_function.add(btnUserGroup);
//		left_function.add(btnMessageGroup);
		
		
//��JList�����û��б�
		final JList<String> userlist = new JList<String>();
		
		//������ѡ��С
		userlist.setPreferredSize(new Dimension(200,200));
		
		//����ɼ�ϵĶ�ѡ
		ListSelectionModel listSelectionModel;
		userlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listSelectionModel = userlist.getSelectionModel();
		
		//������ʾ���ݣ��ڲ����Զ���װ��ListModel)
		int lengthoffriends = cus.friends.size();
		String[] usernames = new String[lengthoffriends];
		int i = 0;
		for(Integer friend_id : cus.friends) {
			Customer friend = db.getCustomerbyId(friend_id);
			usernames[i++] = friend.getUserName();
		}
		userlist.setListData(usernames);
		
		//�Ӹ�����
		JScrollPane listPane = new JScrollPane(userlist);
		
		//���ѡ��ѡ��״̬���ı�ļ�����
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//��ȡ���б�ѡ�е�ѡ������
				int[] indices = userlist.getSelectedIndices();
				//��ȡѡ�����ݵ�ListModel
				ListModel<String> listModel = userlist.getModel();
				//���ѡ�е�ѡ��
				for(int index : indices) {
					System.out.println("ѡ�У�" + index + " = " + listModel.getElementAt(index));
				}
				System.out.println();
			}
		});
		
		//����Ĭ��ѡ����
		userlist.setSelectedIndex(1);
		
		//��ӵ����������û��������������
//		right_users.add(userlist);

		Icon imageIcon = null;
		TextField ChatWin = new TextField(10);
		TextField ChatEdit = new TextField(10);
//		final JLabel tabLabelA = new JLabel();
//		tabLabelA.setText("�û��б�");
//		tabbedPane.addTab("�û��б�",imageIcon,tabLabelA,"����鿴�û��б�");//����ǩ�����ӵ�ѡ���
		JPanel tabPanelA = new JPanel(new BorderLayout());
		JPanel chatwindow = new JPanel(new BorderLayout());
		JPanel func_edit = new JPanel(new BorderLayout());	
		JPanel func = new JPanel();
		
		tabPanelA.add(userlist, BorderLayout.WEST);
		tabPanelA.add(chatwindow, BorderLayout.CENTER);
		chatwindow.add(ChatWin, BorderLayout.CENTER);
		chatwindow.add(func_edit, BorderLayout.SOUTH);
		func_edit.add(func, BorderLayout.NORTH);
		func_edit.add(ChatEdit, BorderLayout.CENTER);
		tabbedPane.addTab("�û��б�",imageIcon,tabPanelA,"����鿴�û��б�");//����ǩ�����ӵ�ѡ���
		final JLabel tabLabelB = new JLabel();
		tabLabelB.setText("�û�����");
		tabbedPane.addTab("�û�����",imageIcon,tabLabelB,"����鿴�û�����");
		final JLabel tabLabelC = new JLabel();
		tabLabelC.setText("��ϢȺ��");
		tabbedPane.addTab("��ϢȺ��",imageIcon,tabLabelC,"����鿴��ϢȺ��");
		tabbedPane.setSelectedIndex(0);
		
        this.setSize(600,600);
    	this.setLocation(300,400);
    	this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		
//		if(btn == btnFunc) {
//			
//		}
	}
	
	
	
}

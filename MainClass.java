
package guiapplicationpack;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class MainPanel extends JPanel implements ActionListener
{
    private JTextField txtRegnNo,txtStuName;
    private JComboBox cmbCourse;
    private String[] course = {"BTech","BCA","BBA","BSc","MTech","MCA","MBA","MSc"};
    private JLabel lblProfilePict;
    private JList lstStuList;
    private JScrollPane spnStuListPane;
    private DefaultListModel listModel = new DefaultListModel();
    private JButton btnBrowse,btnAddNew,btnRegister,btnCancel,btnExit;
    private File imageFile = null;
    
    private JLabel makeLabel(String cap,int x,int y,int w,int h,int mode)
    {
        JLabel temp = new JLabel(cap);
        if(mode == 1)
        {
            temp.setFont(new Font("Verdana", 1, 25));
            temp.setOpaque(true);
            temp.setBackground(Color.RED);
            temp.setForeground(Color.WHITE);
            temp.setHorizontalAlignment(JLabel.CENTER);
            temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));            
        }
        else
            temp.setFont(new Font("Courier New", 1, 16));
        temp.setBounds(x,y,w,h);
        super.add(temp);
        return temp;
    }
    private JTextField makeTextField(int x,int y,int w,int h)
    {
        JTextField temp = new JTextField();
        temp.setFont(new Font("Courier New",1,16));
        temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        temp.setBounds(x,y,w,h);
        temp.setHorizontalAlignment(JTextField.CENTER);
        add(temp);
        return temp;
    }
    private JComboBox makeComboBox(int x,int y,int w,int h,String[] items)
    {
        JComboBox temp = new JComboBox(items);
        temp.setFont(new Font("Verdana", 1, 12));
        temp.setBounds(x,y,w,h);
        add(temp);
        return temp;
    }
    private JButton makeButton(String caption,int x,int y,int w,int h)
    {
        JButton temp = new JButton(caption);
        temp.setBounds(x,y,w,h);
        temp.setFont(new Font("Verdana", 1, 12));
        temp.setMargin(new Insets(0,0,0,0));
        temp.addActionListener(this);
        super.add(temp);
        return temp;
    }
    private JLabel makeListLabel(String regn,String name,String cors,File path)
    {
        try
        {
            String msg = "<html>Regn No: "+regn+"<br>Name : "+name+"<br>Course : "+cors+"</html>";
            BufferedImage bimg = ImageIO.read(path);
            Image scaled = bimg.getScaledInstance(100,75,Image.SCALE_SMOOTH);
            Icon img = new ImageIcon(scaled);
            JLabel t = new JLabel(msg, img, JLabel.LEFT);
            Border br = BorderFactory.createLineBorder(Color.BLACK, 2);
            t.setBorder(br);
            t.setOpaque(true);
            t.setBackground(Color.BLUE);
            t.setForeground(Color.WHITE);
            t.setFont(new Font("Courier New", 1, 18));
            return t;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    private void checkFormCompleteness()
    {
        String regn = txtRegnNo.getText().trim();
        String name = txtStuName.getText().trim();
        
        if(!regn.isEmpty() && !name.isEmpty() && imageFile != null)
        {
            btnRegister.setEnabled(true);
        }
        else
        {
            btnRegister.setEnabled(false);
        }
    }
    public MainPanel()
    {
        makeLabel("Student Registration",   10,10,510,50,1);
        makeLabel("Registration No.",       10,70,180,30,2);
        txtRegnNo  = makeTextField(             190,70,200,30);
        makeLabel("Student Name",           10,120,180,30,2);
        txtStuName = makeTextField(             190,120,200,30);
        makeLabel("Select Course",          10,170,180,30,2);
        cmbCourse  = makeComboBox(                190,170,200,30,course);
        
        lblProfilePict = new JLabel("Image");
        lblProfilePict.setOpaque(true);
        lblProfilePict.setBackground(Color.ORANGE);
        lblProfilePict.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lblProfilePict.setBounds(400,70,120,100);
        lblProfilePict.setHorizontalAlignment(JLabel.CENTER);
        add(lblProfilePict);
        
        lstStuList = new JList(listModel);
        lstStuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstStuList.setCellRenderer(new Renderer());
        spnStuListPane = new JScrollPane(lstStuList);
        spnStuListPane.setBounds(10,210,510,200);
        spnStuListPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        spnStuListPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(spnStuListPane);
        
        btnBrowse   = makeButton("Browse",400,175,120,30);
        btnAddNew   = makeButton("Add New", 28,420,100,30);
        btnRegister = makeButton("Register",156,420,100,30);
        btnCancel   = makeButton("Cancel",284,420,100,30);
        btnExit     = makeButton("Exit",412,420,100,30);
        
        btnAddNew.setEnabled(true);
        btnRegister.setEnabled(false);
        btnCancel.setEnabled(false);
        
        txtRegnNo.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { checkFormCompleteness(); }
            public void removeUpdate(DocumentEvent e) { checkFormCompleteness(); }
            public void changedUpdate(DocumentEvent e) { checkFormCompleteness(); }
        });
        
        txtStuName.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { checkFormCompleteness(); }
            public void removeUpdate(DocumentEvent e) { checkFormCompleteness(); }
            public void changedUpdate(DocumentEvent e) { checkFormCompleteness(); }
        });
        
        lstStuList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting())
                {
                    if(lstStuList.getSelectedIndex() != -1)
                    {
                        btnCancel.setEnabled(true);
                    }
                    else
                    {
                        btnCancel.setEnabled(false);
                    }
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object ob = e.getSource();
        if(ob == btnBrowse)
        {
            try
            {
                BufferedImage bimg;
                Image scaled;
                ImageIcon icon = null;
                JFileChooser fc = new JFileChooser();
                fc.setMultiSelectionEnabled(false);
                fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int status = fc.showOpenDialog(null);
                if(status == JFileChooser.APPROVE_OPTION)
                {
                    lblProfilePict.setText("");
                    imageFile = fc.getSelectedFile();
                    bimg = ImageIO.read(imageFile);
                    scaled = bimg.getScaledInstance(120,100, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(scaled);
                    lblProfilePict.setIcon(icon);
                    checkFormCompleteness();
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        else if(ob == btnAddNew)
        {
            txtRegnNo.setText("");
            txtStuName.setText("");
            cmbCourse.setSelectedIndex(0);
            lblProfilePict.setIcon(null);
            lblProfilePict.setText("Image");
            imageFile = null;
            lstStuList.clearSelection();
            btnRegister.setEnabled(false);
            btnCancel.setEnabled(false);
        }
        else if(ob == btnRegister)
        {
            String regn = txtRegnNo.getText();
            String name = txtStuName.getText();
            String cors = (String)cmbCourse.getSelectedItem();
            listModel.addElement(makeListLabel(regn, name, cors, imageFile));
            
            txtRegnNo.setText("");
            txtStuName.setText("");
            cmbCourse.setSelectedIndex(0);
            lblProfilePict.setIcon(null);
            lblProfilePict.setText("Image");
            imageFile = null;
            btnRegister.setEnabled(false);
        }
        else if(ob == btnCancel)
        {
            int selectedIndex = lstStuList.getSelectedIndex();
            if(selectedIndex != -1)
            {
                listModel.remove(selectedIndex);
                lstStuList.clearSelection();
                btnCancel.setEnabled(false);
            }
        }
        else if(ob == btnExit)
        {
            System.exit(0);
        }
    }
    class Renderer extends DefaultListCellRenderer
    {
        @Override
        public Component getListCellRendererComponent(JList<?> list,Object value,int index,boolean isSelected,boolean cellHasFocus)
        {
            JLabel ob = (JLabel)value;
            if(isSelected)
            {
                ob.setBackground(Color.LIGHT_GRAY);
                ob.setForeground(Color.BLUE);
            }
            else
            {
                ob.setBackground(Color.BLUE);
                ob.setForeground(Color.WHITE);
            }
            return ob;
        }
    }
}
class MainFrame extends JFrame
{
    private MainPanel panel;
    public MainFrame()
    {
        panel = new MainPanel();
        panel.setBackground(new Color(225,250,160));
        panel.setLayout(new BorderLayout());
        super.add(panel);
    }
}
public class MainClass
{
    public static void main(String[] args)
    {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(540, 500);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Student Registration Form");
        frame.setResizable(false);
    }
}
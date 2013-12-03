
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Phil
 */
public class CourseFrame extends JFrame{
    
    private ArrayList<String> subjectList;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel boxPanel;
    private JPanel textPanel;
    private JRadioButton allCourses;
    private JRadioButton campusCourses;
    private JRadioButton onlineCourses;
    private JLabel subjectLabel;
    private JComboBox subjectBox;
    public JTextArea textArea;
    private JScrollPane scroll;
    ArrayList<Course> courseList;
    private String selectedCampus = " ";
    private String selectedSubject;
     
    public CourseFrame(ArrayList<Course> c)
    { 
    courseList = c;
    buttonPanel = createButtonPanel();
    textPanel = createTextPanel();
    boxPanel = createBoxPanel();
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.add(buttonPanel, BorderLayout.NORTH);
    mainPanel.add(boxPanel, BorderLayout.CENTER);
    mainPanel.add(textPanel, BorderLayout.SOUTH);
    
    this.add(mainPanel);
    
    this.setSize(600, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setTitle("ASU Course Search");
    }
    
    public JPanel createButtonPanel()
    {
        allCourses = new JRadioButton("All Courses");
        campusCourses = new JRadioButton("Campus Courses");
        onlineCourses = new JRadioButton("Online Courses");
               
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(allCourses);
        buttonGroup.add(campusCourses);
        buttonGroup.add(onlineCourses);
                                    
        class radioListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                setCampus();     
                
                    changeText();
                
            }
        }
        
        allCourses.addActionListener(new radioListener());
        campusCourses.addActionListener(new radioListener());
        onlineCourses.addActionListener(new radioListener());
                
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(allCourses, BorderLayout.WEST);
        panel.add(campusCourses, BorderLayout.CENTER);
        panel.add(onlineCourses, BorderLayout.EAST);
                
        return panel;
            
    }
    
    public JPanel createBoxPanel()
    {    
        subjectLabel = new JLabel("Search Courses by Subject: ");
        subjectBox = new JComboBox();
        subjectBox.addItem("None"); 
        subjectBox.addItem("All");
        subjectList = new ArrayList<String>();
        for(Course c : courseList)
        {
            String s = (String)c.getSubject();
            subjectList.add(s);
        }   
        Set myset = new HashSet(subjectList);
        ArrayList<String> t = new ArrayList<String>(myset);
        for(int i=0;i<myset.size();i++)
        {
            subjectBox.addItem(t.get(i));
        }  
        class BoxListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {                
               setSubject();
              if(!selectedCampus.equals(" "))
              {
                  changeText();
              }
            }
        }
        BoxListener blisten = new BoxListener();
        subjectBox.addActionListener(blisten);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(subjectLabel, BorderLayout.WEST);
        panel.add(subjectBox, BorderLayout.CENTER);
        return panel;
    }
    
    public JPanel createTextPanel()
    {
        JPanel panel = new JPanel();
        TitledBorder title;
        title = BorderFactory.createTitledBorder("Search Results");
        panel.setBorder(title);
        textArea = new JTextArea(23,50);
        textArea.setVisible(true);
        scroll = new JScrollPane(textArea);
        panel.add(textArea);
        
        
        
        return panel;
    }
    
    public void setCampus()
    {
        if (allCourses.isSelected())
        {
            selectedCampus = "all";
        }
        if(campusCourses.isSelected())
        {
            selectedCampus = "CampusCourse";
        }
        if(onlineCourses.isSelected())
        {
            selectedCampus = "OnlineCourse";
        }
    }
    
    public void setSubject()
    {
        selectedSubject = (String)subjectBox.getSelectedItem();
        
    }
    
    public void changeText()
    {
        if(allCourses.isSelected())
        {
            textArea.setText("");
            if(selectedSubject=="All")
                {
                    for(int i=0;i<courseList.size();i++)
                    {
                        Course c = courseList.get(i);
                        textArea.append(c.toString() + "\n");
                    }
                }
            else
            for(int i=0;i<courseList.size();i++)
            {
                Course c = courseList.get(i);
               if(c.getSubject().equals(selectedSubject))
                {
                    textArea.append(c.toString() + "\n");
                }
            }
        }
        if(campusCourses.isSelected())
        {
            textArea.setText("");
            if(selectedSubject=="All")
                {
                    for(int i=0;i<courseList.size();i++)
                    {
                        Course c = courseList.get(i);
                        if(c instanceof CampusCourse)
                        {
                            textArea.append(c.toString() + "\n");
                        }
                    }
                }
            else
            for(int i=0;i<courseList.size();i++)
            {
                Course c = courseList.get(i);
                if(c instanceof CampusCourse)
                {
                    if(c.getSubject().equals(selectedSubject))
                    {
                    textArea.append(c.toString() + "\n");
                    }
                }
            }
        }
            
        if(onlineCourses.isSelected())
        {
            textArea.setText("");
            if(selectedSubject=="All")
                {
                    for(int i=0;i<courseList.size();i++)
                    {
                        Course c = courseList.get(i);
                        if(c instanceof OnlineCourse)
                        {
                            textArea.append(c.toString() + "\n");
                        }
                    }
                }
            else
            for(int i=0;i<courseList.size();i++)
            {
                Course c = courseList.get(i);
                if(c instanceof OnlineCourse)
                {
                    if(c.getSubject().equals(selectedSubject))
                    {
                    textArea.append(c.toString() + "\n");
                    }
                }
            }
        }
    
    
    }     
}

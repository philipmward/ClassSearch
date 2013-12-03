

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Phil
 */
public abstract class Course implements Comparable{
    
    private String title;
    private int number;
    private int credits;
    private String subject;
    private int SLN;
    private String professor;
    
    
    public Course(String ctitle, int cnumber, int ccredits, String csubject, int cSLN, String cProfessor)
    {
        title = ctitle;
        number = cnumber;
        credits = ccredits;
        subject = csubject;
        SLN = cSLN;
        professor = cProfessor;
    }
    
      
    public String getTitle()
    {
        return title;
    }
    
    public int getNumber()
    {
        return number;
    }
    
    public int getCredits()
    {
        return credits;
    }
    
    public String getSubject()
    {
        return subject;
    }
    
    public int getSLN()
    {
        return SLN;
    }
    
    public String getProfessor()
            {
                return professor;
            }
 
    public int compareTo(Object otherObject)
    {
        Course otherCourse = (Course) otherObject;
       int subjectCompare = subject.compareTo(otherCourse.getSubject());
       
       if(subjectCompare == 0)
       {
         return number - otherCourse.getNumber();
       }
       else
        return subjectCompare;
   }
 

    public String toString()
     {
         return this.getClass().getName() + ": " + number + ", " + subject + ", " + SLN + ", " + title + ", " + credits + ", " + professor + " ";
     }
}   

     
 
    


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Phil
 */
public class CampusCourse extends Course{
    
    private String location;
    private String classRoomNumber;
    
    public CampusCourse(String ctitle, int cnumber, int ccredits, String csubject, int cSLN, String cProfessor, String clocation, String cclassRoomNumber)
    {
        super(ctitle, cnumber, ccredits, csubject, cSLN, cProfessor);
        location = clocation;
        classRoomNumber = cclassRoomNumber;
    }
    
    public String getLocation()
    {
        return location;
    }
    
    public String getClassRoomNumber()
    {
        return classRoomNumber;
    }
    
    public String toString()
    {
        return super.toString() + ", " + location + ", " + classRoomNumber;
    }
    
}

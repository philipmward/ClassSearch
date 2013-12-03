
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
Searches through a list of classes and class data. Creates a user interface that allows a user to search for classes by
 */

/**
 *
 * @author Philip Ward
 */

//Reads an input file and prints the good data to the console while writing bad data to an output file.

public class CourseViewer {
          
    public static void main(String[] args)
    {       
         CourseArrayList cl = new CourseArrayList();
         if((args.length<2) || (args.length>2))
        {
            System.out.println("Usage: java FileReader inputfile");
        }
        else
        {
            try
            {
                File inFile = new File(args[0]);
                PrintWriter out = new PrintWriter(args[1]);
                
                Scanner readFile = new Scanner(inFile);
                int lineCount = 0;
                while(readFile.hasNextLine())
                
                {
                    lineCount++;
                    String input = readFile.nextLine();
                    Scanner inputSplit = new Scanner(input);
                    inputSplit.useDelimiter("\\s*,\\s*");
                    
                    try
                    {
                        int number = inputSplit.nextInt();
                        String subject = inputSplit.next();
                        int SLN = inputSplit.nextInt();
                        String title = inputSplit.next();
                        int credits = inputSplit.nextInt();
                        String professor = inputSplit.next();
                        String campus = inputSplit.next();
                        String classRoomNumber = "";
                        if (credits <1 || credits >4)
                        {
                            throw new CourseDataException("Invalid Credits on Line " + input);
                        }
                        if(campus.equalsIgnoreCase("West") || campus.equalsIgnoreCase("Tempe") || campus.equalsIgnoreCase("Poly") || campus.equalsIgnoreCase("Online"))
                                {
                                    if (!campus.equalsIgnoreCase("Online"))
                                    {
                                    classRoomNumber = inputSplit.next();
                                    
                                    CampusCourse cc = new CampusCourse(title,number, credits, subject, SLN, professor, campus, classRoomNumber);
                                    cl.courseArrayList.add(cc);
                                    }
                                    else
                                    {
                                        OnlineCourse oc = new OnlineCourse(title,number, credits, subject, SLN, professor);
                                        cl.courseArrayList.add(oc); 
                                    }
                                }
                        
                        else throw new CourseDataException("Invalid Campus on Line " + input);
                        out.println("Valid Data: " + input);
                        
                        
                    }
                    
                    
                    
                    catch (InputMismatchException e)
                    {
                       out.println("Line Number: " + lineCount + " Invalid Data: " + input);
                    }
                    
                    catch (NoSuchElementException e)
                    {
                       out.println("Line Number: " + lineCount + " Missing Data: " + input);
                    }
                    
                    catch (CourseDataException e)
                    {
                        out.println("Line Number: " + lineCount + " " + e.getMessage());
                    }
                    
                }   
                
                readFile.close();
                out.close();
                
                
            }
                        
            catch (FileNotFoundException e)
                {
                    System.out.println("File Not Found");
                }
        }
        Collections.sort(cl.courseArrayList);
        CourseFrame cf = new CourseFrame(cl.courseArrayList);
    }
    
   
}

package practice.misc.test; 

import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.misc.*;  

/** 
* ExportKanbanData Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 29, 2015</pre> 
* @version 1.0 
*/ 
public class ExportKanbanDataTest { 
    
    ExportKanbanData testClass = new ExportKanbanData();
    long starttime;
    
    @Before
    public void before() throws Exception {
          starttime = System.nanoTime(); 
    } 
    
    @After
    public void after() throws Exception {
		long now = System.nanoTime();
		System.out.println("elapsed time " + (now-starttime)/1000000 + "milli secs");
    } 
    
        /** 
    * 
    * Method: convertToOneTaskPerFile(File exportedFile, File outputDirectory) 
    * 
    */ 
    @Test
    public void testConvertToOneTaskPerFile() throws Exception { 
        ExportKanbanData exportKanbanData = new ExportKanbanData();
        File exportfile = new File("G:\\premkumarb\\work\\freelords\\my_work\\kanban_old_data.csv");
        File exportDir = new File("G:\\premkumarb\\work\\freelords\\my_work\\export");

        exportKanbanData.convertToOneTaskPerFile(exportfile, exportDir);

    } 
    
        
} 

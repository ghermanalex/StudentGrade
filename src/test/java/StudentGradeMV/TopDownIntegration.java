package StudentGradeMV;

import org.junit.Test;

public class TopDownIntegration {

    @Test
    public void testAddStudent(){
        new AddStudent().checkName();
        new AddStudent().checkProfessor();
        new AddStudent().checkId();
        new AddStudent().checkGroup();
        new AddStudent().checkEmail();
        new AddStudent().checkDuplicates();
        new AddStudent().addStudent();
    }

    @Test
    public void testAddTheme(){
        testAddStudent();
        new AddTheme().addTheme();
        new AddTheme().checkDeadline();
        new AddTheme().checkDuplicates();
        new AddTheme().checkId();
        new AddTheme().checkSaptPrimire();
    }

    @Test
    public void testAddGrade(){
        testAddTheme();
        new AddGrade().addNota();

    }

    @Test
    public void integrationTest(){
        testAddGrade();
    }
}
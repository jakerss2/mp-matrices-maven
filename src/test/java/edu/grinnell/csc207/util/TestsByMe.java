package edu.grinnell.csc207.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

public class TestsByMe {
  
  /**
   * Testing the set method
   */
  @Test
  void testSet() {
    Matrix<String> myMatrix = new MatrixV0(3, 3, " ");
    assertMatrixEquals(
        new String[][]
            {{" ", " ", " "},
             {" ", " ", " "},
             {" ", " ", " "}},
        myMatrix,  
        "M: blank matrix");

    myMatrix.set(1, 1, "X");
    assertMatrixEquals(
        new String[][]
            {{" ", " ", " "},
             {" ", "X", " "},
             {" ", " ", " "}},
        myMatrix,  
        "M: normal set");

    myMatrix.set(1, 1, "V");
    assertMatrixEquals(
        new String[][]
            {{" ", " ", " "},
             {" ", "V", " "},
             {" ", " ", " "}},
        myMatrix,  
        "M: set a set position");

    myMatrix.set(0, 0, "X");
    assertMatrixEquals(
        new String[][]
            {{"X", " ", " "},
             {" ", "V", " "},
             {" ", " ", " "}},
        myMatrix,  
        "M: set matrix (0,0)");

    myMatrix.set(2, 2, "X");
    assertMatrixEquals(
        new String[][]
            {{"X", " ", " "},
             {" ", "V", " "},
             {" ", " ", "X"}},
        myMatrix,  
        "M: set matrix (2,2)");
  }

  @Test
  void testGet() {
      Matrix<String> myMatrix = new MatrixV0<>(3, 3, " ");
      assertEquals(" ", myMatrix.get(0, 0),
          "M: Get value (0,0)");
      assertEquals(" ", myMatrix.get(2, 2),
          "M: Get value (2,2)");
      myMatrix.set(1, 1, "X");
      assertEquals("X", myMatrix.get(1, 1),
          "M: Get value (1,1)");
  }

  @Test
  void testRowManipulation() throws ArraySizeException {
    String s0 = "zero";
    String s1 = "one";
    String s2 = "two";
    String s3 = "three";
    String s4 = "four";
    String s5 = "five";
    Matrix<String> myMatrix = new MatrixV0<>(3, 3, " ");
    myMatrix.insertRow(1);
    assertMatrixEquals(
        new String[][] {
            {" ", " ", " "},
            {" ", " ", " "},
            {" ", " ", " "},
            {" ", " ", " "}
        },
        myMatrix,
        "M: Matrix insert row 1");

    myMatrix.insertRow(2, new String[] {s0, s1, s2});
    assertMatrixEquals(
        new String[][] {
            {" ", " ", " "},
            {" ", " ", " "},
            {"zero", "one", "two"},
            {" ", " ", " "},
            {" ", " ", " "}
        },
        myMatrix,
        "M: Matrix insert row 2");

    myMatrix.deleteRow(1);
    myMatrix.deleteRow(2);
    myMatrix.deleteRow(2);
    assertMatrixEquals(
        new String[][] {
            {" ", " ", " "},
            {"zero", "one", "two"}
        },
        myMatrix,
        "M: Matrix delete rows");

    myMatrix.insertRow(2, new String[] {s3, s4, s5});
    assertMatrixEquals(
        new String[][] {
            {" ", " ", " "},
            {"zero", "one", "two"},
            {"three", "four", "five"}
        },
        myMatrix,
        "M: Matrix insert row 2");
}

  @Test
  void testColManipulation() throws ArraySizeException {
    String s0 = "zero";
    String s1 = "one";
    String s2 = "two";
    String s3 = "three";
    String s4 = "four";
    String s5 = "five";
    Matrix<String> myMatrix = new MatrixV0<>(3, 3, " ");
    myMatrix.insertCol(1);
    assertMatrixEquals(
        new String[][] {
            {" ", " ", " ", " "},
            {" ", " ", " ", " "},
            {" ", " ", " ", " "}
        },
        myMatrix,
        "M: Matrix insert row 1");

    myMatrix.insertCol(2, new String[] {s0, s1, s2});
    assertMatrixEquals(
        new String[][] {
          {" ", " ", "zero", " ", " "},
          {" ", " ", "one", " ", " "},
          {" ", " ", "two", " ", " "}
        },
        myMatrix,
        "M: Matrix insert column");

    myMatrix.deleteCol(1);
    myMatrix.deleteCol(2);
    myMatrix.deleteCol(2);
    assertMatrixEquals(
        new String[][] {
          {" ", "zero"},
          {" ", "one"},
          {" ", "two"}
        },
        myMatrix,
        "M: Matrix delete columns");

    myMatrix.insertCol(2, new String[] {s3, s4, s5});
    assertMatrixEquals(
        new String[][] {
          {" ", "zero", "three"},
          {" ", "one", "four"},
          {" ", "two", "five"}
        },
        myMatrix,
        "M: Matrix insert row 2");
  }

  @Test
  void testFillRegion() {
    Matrix<String> myMatrix = new MatrixV0<>(3, 3, " ");
    myMatrix.fillRegion(0, 0, 2, 2, "X");
    assertMatrixEquals(
        new String[][] {
            {"X", "X", " "},
            {"X", "X", " "},
            {" ", " ", " "}
        },
        myMatrix,
        "M: Matrix (0,0) to (2,2) with 'X'");

    myMatrix.fillRegion(1, 1, 3, 3, "B");
    assertMatrixEquals(
        new String[][] {
            {"X", "X", " "},
            {"X", "B", "B"},
            {" ", "B", "B"}
        },
        myMatrix,
        "M: Matrix (1,1) to (3,3) with 'B'");

    myMatrix.fillRegion(0, 0, 3, 3, "R");
    assertMatrixEquals(
        new String[][] {
            {"R", "R", "R"},
            {"R", "R", "R"},
            {"R", "R", "R"}
        },
        myMatrix,
        "M: Matrix (0,0) to (3,3) with 'R'");
  }

  @Test
  void testFillLine() {
    Matrix<String> myMatrix = new MatrixV0<>(3, 3, " ");
    myMatrix.fillLine(0, 0, 1, 1, 3, 3, "X");
    assertMatrixEquals(
        new String[][] {
            {"X", " ", " "},
            {" ", "X", " "},
            {" ", " ", "X"}
        },
        myMatrix,
        "M: Matrix FillLine 'X'");

    myMatrix.fillLine(0, 1, 1, 1, 2, 3, "M");
    assertMatrixEquals(
        new String[][] {
            {"X", "M", " "},
            {" ", "X", "M"},
            {" ", " ", "X"}
        },
        myMatrix,
        "M: Matrix FillLine 'M'");

    myMatrix.fillLine(1, 0, 1, 1, 3, 2, "W");
    assertMatrixEquals(
        new String[][] {
            {"X", "M", " "},
            {"W", "X", "M"},
            {" ", "W", "X"}
        },
        myMatrix,
        "M: Matrix FillLine 'W'");
  }

  @Test
  void testClone() {
      Matrix<String> myMatrix = new MatrixV0<>(3, 3, " ");
      myMatrix.set(1, 1, "X");
      Matrix<String> clonedMatrix = myMatrix.clone();
      
      assertMatrixEquals(
          new String[][] {
              {" ", " ", " "},
              {" ", "X", " "},
              {" ", " ", " "}
          },
          clonedMatrix,
          "M: Cloned matrix");
  }
  



}



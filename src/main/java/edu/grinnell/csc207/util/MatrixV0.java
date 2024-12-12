package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Jacob Bell
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The array that will hold all of the matrix. */
  AssociativeArray<String, T> matrix = new AssociativeArray<>();

  /** The width of the matrix. */
  int width;

  /** The height of the matrix. */
  int height;

  /** Default value for our matrix. */
  T defVal;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    // this isn't efficient but it gets the job done! :)
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        String str = "(" + x + "," + y + ")";
        try {
          matrix.set(str, def);
        } catch (Exception e) {
          System.err.println("Error in setting and getting");
        } // try/catch
      } // for
    } // for
    this.width = width;
    this.height = height;
    this.defVal = def;
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  @SuppressWarnings("unchecked")
  public T get(int row, int col) {
    if (this.height <= row || this.width <= col || row < 0 || col < 0) {
      throw new IndexOutOfBoundsException();
    } // if

    Object element = null;

    String coordinate = MatrixV0.makeCoord(col, row);

    try {
      element = this.matrix.get(coordinate);
    } catch (Exception e) {
    } // try/catch

    return (T) element;
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if (this.height <= row || this.width <= col || row < 0 || col < 0) {
      throw new IndexOutOfBoundsException();
    } // if

    String coordinate = MatrixV0.makeCoord(col, row);
    try {
      this.matrix.set(coordinate, val);
    } catch (Exception e) {
      System.err.println("Coordinate not found!?");
    } // try/catch
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if (this.height < row || row < 0) {
      throw new IndexOutOfBoundsException();
    } // if

    this.height++;

    for (int i = this.height - 1; i > row; i--) {
      for (int j = 0; j < this.width; j++) {
        this.set(i, j, this.get(i - 1, j));
      } // for
    } // for

    for (int i = 0; i < this.width; i++) {
      String coord = MatrixV0.makeCoord(i, row);
      try {
        this.matrix.set(coord, this.defVal);
      } catch (Exception e) {
        System.err.print("Error setting");
      } // try/catch
    } // for
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (this.height < row || row < 0) {
      throw new IndexOutOfBoundsException();
    } else if (vals.length != this.width) {
      throw new ArraySizeException();
    } // else if

    this.height++;

    for (int i = this.height - 1; i > row; i--) {
      for (int j = 0; j < this.width; j++) {
        this.set(i, j, this.get(i - 1, j));
      } // for
    } // for

    for (int i = 0; i < this.width; i++) {
      String coord = makeCoord(i, row);
      try {
        this.matrix.set(coord, vals[i]);
      } catch (Exception e) {
      } // try/catch
    } // for
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if (this.width < col || col < 0) {
      throw new IndexOutOfBoundsException();
    } // if

    this.width++;

    for (int i = this.width - 1; i > col; i--) {
      for (int j = 0; j < this.height; j++) {
        this.set(j, i, this.get(j, i - 1));
      } // for
    } // for

    for (int i = 0; i < this.height; i++) {
      String coord = MatrixV0.makeCoord(col, i);
      try {
        this.matrix.set(coord, this.defVal);
      } catch (Exception e) {
        System.err.print("Error setting");
      } // for
    } // try/catch
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (this.width < col || col < 0) {
      throw new IndexOutOfBoundsException();
    } else if (vals.length != this.height) {
      throw new ArraySizeException();
    } // if

    this.width++;

    for (int i = this.width - 1; i > col; i--) {
      for (int j = 0; j < this.height; j++) {
        this.set(j, i, this.get(j, i - 1));
      } // for
    } // for

    for (int i = 0; i < this.height; i++) {
      String coord = makeCoord(col, i);
      try {
        this.matrix.set(coord, vals[i]);
      } catch (Exception e) {
      } // try/catch
    } // for
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if (this.height <= row || row < 0) {
      throw new IndexOutOfBoundsException();
    } // if

    for (int i = row; i < this.height - 1; i++) {
      for (int j = 0; j < this.width; j++) {
        this.set(i, j, this.get(i + 1, j));
      } // for
    } // for

    for (int i = 0; i < this.width; i++) {
      String coord = MatrixV0.makeCoord(i, this.height - 1);
      try {
        this.matrix.remove(coord);
      } catch (Exception e) {
        System.err.print("Error Removing");
      } // try/catch
    } // try/catch
    this.height--;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    if (this.width <= col || col < 0) {
      throw new IndexOutOfBoundsException();
    } // if

    for (int i = col; i < this.width - 1; i++) {
      for (int j = 0; j < this.height; j++) {
        this.set(j, i, this.get(j, i + 1));
      } // for
    } // for

    for (int i = 0; i < this.height; i++) {
      String coord = MatrixV0.makeCoord(this.width - 1, i);
      try {
        this.matrix.remove(coord);
      } catch (Exception e) {
        System.err.print("Error Removing");
      } // try/catch
    } // for
    this.width--;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    if (this.height < endRow || endRow < startRow || this.width < endCol
        || endCol < startCol || startRow < 0 || startCol < 0) {
      throw new IndexOutOfBoundsException();
    } // if

    for (int i = startRow; i < endRow; i++) {
      for (int j = startCol; j < endCol; j++) {
        try {
          this.set(i, j, val);
        } catch (Exception e) {
          System.err.println("Error Setting!");
        } // catch
      } // for
    } // for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    if (this.height < endRow || endRow < startRow || this.width < endCol
        || endCol < startCol || startRow < 0 || startCol < 0) {
      throw new IndexOutOfBoundsException();
    } // if

    if ((startRow + deltaRow) >= endRow || (startCol + deltaCol) >= endCol) {
      try {
        this.set(startRow, startCol, val);
      } catch (Exception e) {
        System.err.println("Error Setting!");
      } // catch
    } else {
      try {
        this.set(startRow, startCol, val);
        fillLine(startRow + deltaRow, startCol + deltaCol, deltaRow, deltaCol, endRow, endCol, val);
      } catch (Exception e) {
        System.err.println("Error Setting!");
      } // catch
    } // for
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    MatrixV0<T> clonedArr = new MatrixV0<>(this.width, this.height, this.defVal);
    for (String coord : this.matrix.getAllKeys()) {
      try {
        clonedArr.matrix.set(coord, this.matrix.get(coord));
      } catch (Exception e) {
      } // try/catch
    } // for
    return clonedArr;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    } // if

    if (!(other instanceof MatrixV0)) {
      return false;
    } // if

    MatrixV0 theOtherMatrix = (MatrixV0) other;

    if (this.width != theOtherMatrix.width || this.height != theOtherMatrix.height) {
      return false;
    } // if

    for (int i = 0; i < this.width(); i++) {
      for (int j = 0; j < this.height(); j++) {
        String coord = makeCoord(i, j);
        try {
          if (!(this.get(j, i).equals(theOtherMatrix.get(j, i)))) {
            return false;
          } // if
        } catch (Exception e) {
          System.err.println("Getting did not work!");
          return false;
        } // try/catch
      } // for
    } // for
    return true;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()

  /**
   * Given two points, it creates a string of those points.
   * @param x
   * @param y
   * @return a string that represents the coordinate
   */
  public static String makeCoord(int x, int y) {
    String str = "(" + x + "," + y + ")";
    return str;
  } // makeCoord(int, int)
} // class MatrixV0

/* this is a extra file, instead of we use MazeLocation.java class, we can use this 
 */
class MazeLocationListNodeExtra {
    int row;
    int column;
    mazeLocationListNodeExtra next;

    TaskListNode(int row, int column) {
        this.row = row;
        this.column   = column;
        this.next     = null;
    }
     boolean equals(int row, int column) {
        return ((this.row == row) &&
               (this.column == column));
    }
}
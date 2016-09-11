/* Name : Ngoc Thinh Nguyen 		                          Student ID: V00817304
 * Assignment # 3: 				Recursion 					  course : csc 115
 *
 */


public class MazeTester{
	public static void main(String[] args){
			test1();
			test2();
			test3();
			test4();
			test5();
			test6();
			test7();
			
	}
	public static void test1(){
		boolean passed = true;
		String testcase[] = {"* ****",
							 "*    *",
							 "**** *"};
		Maze maze = new Maze(testcase,0,1,2,4);
		MazeLocation expected[] = new MazeLocation[6];
		expected[0] = new MazeLocation(0,1);
		expected[1] = new MazeLocation(1,1);
		expected[2] = new MazeLocation(1,2);
		expected[3] = new MazeLocation(1,3);
		expected[4] = new MazeLocation(1,4);
		expected[5] = new MazeLocation(2,4);
	    MazeLocationList result = maze.solve();
	    
	    if (result == null) {
		    passed = false;
		} else if (result.isEmpty() == true) {
		    passed = false;
		} else {
		    for (int i = 0; i < result.getLength(); i++) {
		        MazeLocation loc = result.retrieve(i);
		        if (loc.equals(expected[i]) == false) {
		            passed = false;
		            break;
		        }   
		    }   
		}   
		if (passed) {
		    System.out.println("Test (horizonal path): passed");
		} else {
		    System.out.println("Test (horizonal path): FAILED");
		}
	}
	
	
	/* test if the no path from start to finish */
	public static void test2(){
		boolean passed = true;
		String testcase[] = {"* ****",
							 "******",
							 "**** *"};
		Maze maze = new Maze(testcase,0,1,2,4);
		MazeLocation expected[] = new MazeLocation[6];
		expected[0] = new MazeLocation(0,1);
		expected[1] = new MazeLocation(1,1);
		expected[2] = new MazeLocation(1,2);
		expected[3] = new MazeLocation(1,3);
		expected[4] = new MazeLocation(1,4);
		expected[5] = new MazeLocation(2,4);
	    MazeLocationList result = maze.solve();
	    
	    if (result == null) {
		    passed = false;
		} else if (result.isEmpty() == true) {
		    passed = false;
		} else {
		    for (int i = 0; i < result.getLength(); i++) {
		        MazeLocation loc = result.retrieve(i);
		        if (loc.equals(expected[i]) == false) {
		            passed = false;
		            break;
		        }   
		    }   
		}   
		if (passed) {
		    System.out.println("Test (horizonal path): passed");
		} else {
		    System.out.println("Test (horizonal path): FAILED");
		}
	}
	
	public static void test3() {
		
		boolean passed = true;
		String testcase[] = {"* *****",
		                  	 "* *****",
		                 	 "*   * *",
		                 	 "* *****",
		                 	 "*     *",
		                 	 "* ** **",
		                 	 "**** **"};
		Maze maze = new Maze(testcase, 0, 1, 6, 4); 
		MazeLocation expected[] = new MazeLocation[10];
		expected[0] = new MazeLocation(0, 1); 
		expected[1] = new MazeLocation(1, 1); 
		expected[2] = new MazeLocation(2, 1); 
		expected[3] = new MazeLocation(3, 1); 
		expected[4] = new MazeLocation(4, 1);  
		expected[5] = new MazeLocation(4, 2); 
		expected[6] = new MazeLocation(4, 3);
		expected[7] = new MazeLocation(4, 4);
		expected[8] = new MazeLocation(5, 4);
		expected[9] = new MazeLocation(6, 4);
		
		MazeLocationList result = maze.solve();
		 System.out.println(result.getLength());
		if (result == null) {
		    passed = false;
		} else if (result.isEmpty() == true) {
		 
		    passed = false;
		} else {
			
		    for (int i = 0; i < result.getLength(); i++) {
		   
		        MazeLocation loc = result.retrieve(i);
		        System.out.println(loc.toString());
		        if (loc.equals(expected[i]) == false) {
		            passed = false;
		            break;
		        }   
		    }   
		}   
		if (passed) {
		    System.out.println("Test (horizonal path): passed");
		} else {
		    System.out.println("Test (horizonal path): FAILED");
		}
	}
	
	public static void test4() {
		
		boolean passed = true;
		String testcase[] = {
							"******** **",
							"*     *   *",
							"*** * * ***",
							"*       * *",
							"* ******* *",
							"*  ****   *",
							"* ******* *",
							"*         *",
							"********* *"
							};
		Maze maze = new Maze(testcase, 0, 8, 8, 9); 
		MazeLocation expected[] = new MazeLocation[28];
		expected[0] = new MazeLocation(0, 8); 
		expected[1] = new MazeLocation(1, 8); 
		expected[2] = new MazeLocation(1, 7); 
		expected[3] = new MazeLocation(2, 7); 
		expected[4] = new MazeLocation(3, 7);  
		expected[5] = new MazeLocation(3, 6);
		expected[6] = new MazeLocation(3, 5);
		expected[7] = new MazeLocation(2, 5);
		expected[8] = new MazeLocation(1, 5);
		expected[9] = new MazeLocation(1, 4);
		expected[10] = new MazeLocation(1, 3);
		expected[11] = new MazeLocation(2, 3);
		expected[12] = new MazeLocation(3, 3);
		expected[13] = new MazeLocation(3, 2);
		expected[14] = new MazeLocation(3, 1);
		expected[15] = new MazeLocation(4, 1);
		expected[16] = new MazeLocation(5, 1);
		expected[17] = new MazeLocation(6, 1);
		expected[18] = new MazeLocation(7, 1);
		expected[19] = new MazeLocation(7, 2);
		expected[20] = new MazeLocation(7, 3);
		expected[21] = new MazeLocation(7, 4);
		expected[22] = new MazeLocation(7, 5);
		expected[23] = new MazeLocation(7, 6);
		expected[24] = new MazeLocation(7, 7);
		expected[25] = new MazeLocation(7, 8);
		expected[26] = new MazeLocation(7, 9);
		expected[27] = new MazeLocation(8, 9);
		
		MazeLocationList result = maze.solve();
		 System.out.println(result.getLength());
		if (result == null) {
		    passed = false;
		} else if (result.isEmpty() == true) {
		    passed = false;
		} else {
			
		    for (int i = 0; i < result.getLength(); i++) {
		        MazeLocation loc = result.retrieve(i);
		         System.out.println(loc.toString());
		        if (loc.equals(expected[i]) == false) {
		            passed = false;
		            break;
		        }   
		    }   
		}   
		if (passed) {
		    System.out.println("Test (horizonal path): passed");
		} else {
		    System.out.println("Test (horizonal path): FAILED");
		}
	}
	public static void test5() {
		
		boolean passed = true;
		String testcase[] = {
							"*** *******",
							"*         *",
							"*         *",
							"*         *",
							"*         *",
							"*         *",
							"*         *",
							"*         *",
							"********* *"
							};
		Maze maze = new Maze(testcase, 0, 3, 8, 9); 
		MazeLocation expected[] = new MazeLocation[15];
		expected[0] = new MazeLocation(0, 3); 
		expected[1] = new MazeLocation(1, 3); 
		expected[2] = new MazeLocation(1, 4); 
		expected[3] = new MazeLocation(1, 5); 
		expected[4] = new MazeLocation(1, 6);  
		expected[5] = new MazeLocation(1, 7);
		expected[6] = new MazeLocation(1, 8);
		expected[7] = new MazeLocation(1, 9);
		expected[8] = new MazeLocation(2, 9);
		expected[9] = new MazeLocation(3, 9);
		expected[10] = new MazeLocation(4, 9);
		expected[11] = new MazeLocation(5, 9);
		expected[12] = new MazeLocation(6, 9);
		expected[13] = new MazeLocation(7, 9);
		expected[14] = new MazeLocation(8, 9);
		
		MazeLocationList result = maze.solve();
		 System.out.println(result.getLength());
		if (result == null) {
		    passed = false;
		} else if (result.isEmpty() == true) {
		    passed = false;
		} else {
			
		    for (int i = 0; i < result.getLength(); i++) {
		        MazeLocation loc = result.retrieve(i);
		         System.out.println(loc.toString());
		        if (loc.equals(expected[i]) == false) {
		            passed = false;
		            break;
		        }   
		    }   
		}   
		if (passed) {
		    System.out.println("Test (horizonal path): passed");
		} else {
		    System.out.println("Test (horizonal path): FAILED");
		}
	}
	public static void test6() {
		
		boolean passed = true;
		String testcase[] = { "*** **********",
                              "*           **",
                              "* *   * *   **",
                              "* * * *     **",
                              "*   *     *  *",
							  "*  *      *  *",
							  "**   *  *    *",
                              "****** *******"};
        Maze maze = new Maze(testcase, 0, 3, 7, 6); 
		MazeLocation expected[] = new MazeLocation[29];
		expected[0] = new MazeLocation(0, 3); 
		expected[1] = new MazeLocation(1, 3); 
		expected[2] = new MazeLocation(1, 4); 
		expected[3] = new MazeLocation(1, 5); 
		expected[4] = new MazeLocation(1, 6);  
		expected[5] = new MazeLocation(1, 7);
		expected[6] = new MazeLocation(1, 8);
		expected[7] = new MazeLocation(1, 9);
		expected[8] = new MazeLocation(1, 10);
		expected[9] = new MazeLocation(1, 11);
		expected[10] = new MazeLocation(2, 11);
		expected[11] = new MazeLocation(3, 11);
		expected[12] = new MazeLocation(4, 11);
		expected[13] = new MazeLocation(4, 12);
		expected[14] = new MazeLocation(5, 12);
		expected[15] = new MazeLocation(6, 12);
		expected[16] = new MazeLocation(6, 11);
		expected[17] = new MazeLocation(6, 10);
		expected[18] = new MazeLocation(6, 9);
		expected[19] = new MazeLocation(5, 9);
		expected[20] = new MazeLocation(4, 9);
		expected[21] = new MazeLocation(3, 9);
		expected[22] = new MazeLocation(3, 8);
		expected[23] = new MazeLocation(4, 8);
		expected[24] = new MazeLocation(5, 8);
		expected[25] = new MazeLocation(5, 7);
		expected[26] = new MazeLocation(6, 7);
		expected[27] = new MazeLocation(6, 6);
		expected[28] = new MazeLocation(7, 6);
		
		
		MazeLocationList result = maze.solve();
		 System.out.println(result.getLength());
		//System.out.println(maze.toString());
		if (result == null) {
		    passed = false;
		} else if (result.isEmpty() == true) {
		    passed = false;
		} else {
			
		    for (int i = 0; i < result.getLength(); i++) {
		        MazeLocation loc = result.retrieve(i);
		         //System.out.println(loc.toString());
		        if (loc.equals(expected[i]) == false) {
		            passed = false;
		            break;
		        }   
		    }   
		}   
		if (passed) {
		    System.out.println("Test (horizonal path): passed");
		} else {
		    System.out.println("Test (horizonal path): FAILED");
		}
	}
	public static void test7() {
		
		boolean passed = true;
		String testcase[] = { "* *****************************",
							  "*             *       *       *",
							  "* ******* ***** * *** ******* *",
                              "* * *     *     *   *       * *",
							  "* * * ***** ******* ******* * *",
                              "* * *       *     *     * *   *",
                              "* * ******* * *** ***** * *** *",
						      "*   *     *   *       * *     *",
                              "*** * *** * ********* * * *****",
                              "*   * * * * *   * *   * * *   *",
                              "* *** * * *** * * * *** *** * *",
                              "* * * *     * * * * * * *   * *",
                              "* * * ***** * * * * * * * *** *",
                              "*   *   * * * * *     *   *   *",
                              "************* *****************"};
        Maze maze = new Maze(testcase, 0, 1, 15, 13);
       
		MazeLocationList result = maze.solve();
		if (result == null) {
		    passed = true;
		} else if (result.isEmpty() == true) {
		    passed = false;
		} else {
		    passed = false;   
		}   
		if (passed) {
		    System.out.println("Test (horizonal path): passed");
		} else {
		    System.out.println("Test (horizonal path): FAILED");
		}
	}

	
	
}


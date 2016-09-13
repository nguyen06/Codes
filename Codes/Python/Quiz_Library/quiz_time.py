import libxml2
import sys
import quiz_library

'''
purpose
	Accept 1 or more log file names on the command line.

	For each log file, compute the total time taken for each question. 

	Write to standard output, the average time spent for each question.
preconditions
	Each command-line argument is the name of a readable and
	legal quiz log file.

	All the log_files have the same number of questions.
'''

# handle command line arguments
if len(sys.argv) < 2:
	print 'Syntax:', sys.argv[0], 'quiz_log_file ...'
	sys.exit()

else:
	 L =[]
	 parse_tree = libxml2.parseFile(sys.argv[1])
         context = parse_tree.xpathNewContext()
         root = parse_tree.getRootElement()
         childs = root.children

         log_list = quiz_library.load_quiz_log(sys.argv[1])
         N = quiz_library.compute_question_count(log_list)
	 index_At_Display, index_At_Answer = 0,0
	 total = 0
	 for y in range(N):
		time_Spend_For_This_Question = 0
		time_Spend_For_Answer_For_n_File = 0
		time_Spend_For_Display_For_n_File = 0
		for n in range(1,len(sys.argv)):
			 #print "working on file ", n, "and question ", y			
		       	 parse_tree = libxml2.parseFile(sys.argv[n])
        		 context = parse_tree.xpathNewContext()
        		 root = parse_tree.getRootElement()
        		 childs = root.children
			 log_list = quiz_library.load_quiz_log(sys.argv[n])
			
			 #set the iterator for index and time
			 iterator_Index , iterator_Time  = 0, 0
			 total_Screen_Time_For_1_File = 0
			 time_Spend_For_Answer_For_1_File = 0
			 #screen_Time = 0
			 for x in log_list:
		        	screen_Time = 0
			 	time_Spend_For_Answer = 0
				#time_Spend_For_Answer = 0
				if isinstance(x,quiz_library.Display):
					'''
					print "y is ", y, " x.index is ", x.index
					print "index iterator ", iterator_Index, "iterator_Time ", iterator_Time 
					
					if y == int(x.index):
                                                time_Spend_For_This_Question += time_Spend_For_Answer + screen_Time
                                                #print " time spend for answer22 ", time_Spend_For_This_Question
					
					if int(x.index) == y:
						screen_Time += abs(int(x.time)-iterator_Time)
						print "screen time ", screen_Time
					
					if int(x.index) != y:
						screen_Time += abs(int(x.time)-iterator_Time)
						print " screen time in x.index != y ", screen_Time
						
					'''	
						
					if x.time == None:
						x.time = 0
					iterator_Index = int(x.index)
                                        iterator_Time = int(x.time)
						
					

					
                                      			
				elif isinstance(x,quiz_library.Answer):
					#print "y is ", y, " x.index is ", x.index
					if y == x.index:
						# for the question with no answer yet, set time to 0
						if x.time == None:
							x.time = 0
					
						if x.index == y:
                                                	time_Spend_For_Answer = abs(int(x.time) - iterator_Time)
							#print "Time spend for answer ", time_Spend_For_Answer
						# set iterator to the current loaction
					
					
						iterator_Index = int(x.index)
						iterator_Time = int(x.time)
						#print iterator_Index, iterator_Time +time_Spend_For_Answer

				time_Spend_For_Answer_For_1_File += time_Spend_For_Answer
				#print " time_Spend_For_Answer_For_1_File ", time_Spend_For_Answer_For_1_File
				#total_Screen_Time_For_1_File += screen_Time
				#print " total screen time for 1 file ", total_Screen_Time_For_1_File
			 time_Spend_For_Answer_For_n_File += time_Spend_For_Answer_For_1_File
			 #print "time_Spend_For_Answer_For_n_File	", time_Spend_For_Answer_For_n_File





			 screen_Time = 0
			 screen_Time_At_the_Index = 0
			 time_Spend_For_Display_For_1_File = 0
			 for z in log_list:
				
				if isinstance(z,quiz_library.Display):
						#print " Index display ", index_At_Display, "index answer ", index_At_Answer
						#if y == z.index:
						
						
						
						if x.time == None:
							z.time = 0
						screen_Time = abs(int(z.time) - iterator_Time)
						#print " screen time ", screen_Time
						
						#print " index_At_Display ", index_At_Display
						if index_At_Display == y:
							if index_At_Answer != index_At_Display:
								#print "hhhh"
								screen_Time_At_the_Index = 0
							else:
								screen_Time_At_the_Index += screen_Time
								#print " screen time index_At_Display ",y," is ", screen_Time_At_the_Index	
						else:
							screen_Time = 0
						iterator_Index = int(z.index)
						iterator_Time = int(z.time)
						index_At_Display = iterator_Index
						
				
				elif isinstance(z,quiz_library.Answer):
						#if y == z.index:
						# for the question with no answer yet, set time to 0
						if z.time == None:
							z.time = 0
					
						if z.index == y:
                                                	time_Spend_For_Answer = abs(int(z.time) - iterator_Time)
							
					
					
						iterator_Index = int(z.index)
						iterator_Time = int(z.time)
						index_At_Answer = iterator_Index
				if screen_Time_At_the_Index == None:
					time_Spend_For_Display_For_1_File = 0
				else:
					time_Spend_For_Display_For_1_File = screen_Time_At_the_Index
			 time_Spend_For_Display_For_n_File += time_Spend_For_Display_For_1_File
			 #print "time_Spend_For_Display_For_n_File " , time_Spend_For_Display_For_n_File	
	 	total = time_Spend_For_Display_For_n_File + time_Spend_For_Answer_For_n_File
		#print "len", len(sys.argv)
		L.append(str(float(total)/(len(sys.argv)-1)))
	 print ",".join(L)


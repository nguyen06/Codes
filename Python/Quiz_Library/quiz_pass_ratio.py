import libxml2
import sys
import quiz_library

'''
purpose
	Accept 1 or more log file names on the command line.

	Accumulate across all the log files the pass ratio for each question.

        A question result is considered a pass if it is not 0 or None
	and fail otherwise.

	The pass ratio for a question is the number of passes
	divided by the number of passes + fails.
preconditions
	Each command-line argument is the name of a
	readable and legal quiz log file.

	All the log_files have the same number of questions.
'''

# check number of command line arguments
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

	


	# TotalPass_Each_Question, TotalFail_Each_Question = 0, 0
       	 total = 0
	 ratio_Each_question = 0
	 # Go to each question, and calcuate the Number of pass and number of fail
	 for y in range(N):
		# go to each xml file, and count how many questions are there
		#TotalPass, TotalFail = 0, 0
		TotalPass_Each_Question, TotalFail_Each_Question = 0, 0
		#NumPass, NumFail = 0 ,0
		for n in range(1,len(sys.argv)):
			TotalPass, TotalFail = 0, 0
			#NumPass, NumFail = 0 ,0
			parse_tree = libxml2.parseFile(sys.argv[n])
			context = parse_tree.xpathNewContext()
			root = parse_tree.getRootElement()
			childs = root.children

			log_list = quiz_library.load_quiz_log(sys.argv[n])
			for x in log_list:
					#NumPass, NumFail = 0 ,0
					if isinstance(x,quiz_library.Answer):
						if x.index == y:
							NumPass, NumFail = 0 ,0
							if x.result == None:
								NumFail += 1
								#print "NumFail ", NumFail
							elif int(x.result) == 0:
								NumFail +=1
								#print "NumFail Case 0", NumFail
							else:
								NumPass += 1
								#print "NumPass" , NumPass
									
				
			TotalPass += NumPass	
			TotalFail += NumFail
			#TotalFail = TotalFail -1
			#print"file", n,"total pass ", TotalPass,"total fail", TotalFail
			childs = childs.next
			TotalPass_Each_Question += TotalPass
			TotalFail_Each_Question += TotalFail
		#print "TotalPass_Each_Question",TotalPass_Each_Question
		#TotalFail_Each_Question += TotalFail
		#print "TotalFail_Each_Question" ,TotalFail_Each_Question 
		total = TotalPass_Each_Question + TotalFail_Each_Question
		#print total
		ratio_Each_question = float(TotalPass_Each_Question) / total
		#print "ratio", ratio_Each_question
		L.append(str(ratio_Each_question))
	 print ",".join(L)

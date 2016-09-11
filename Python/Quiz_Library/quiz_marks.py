import libxml2
import sys
import quiz_library
import fileinput
'''
purpose
	Accept 1 or more log file names on the command line.
	For each log file
		write to standard output the course mark for the log file,
		in CSV format
preconditions
	Each command-line argument is the name of a legal, readable quiz log file.
'''

# handle command line arguments
if len(sys.argv) < 2:
	print 'Syntax:', sys.argv[0], 'quiz_log_file ...'
	print len(sys.argv)
	sys.exit()
else:
	
	for x in range(len(sys.argv)):
		if x >= 1:
			a  = sys.argv[x] +"," + str(len(sys.argv)-x)
			print a
		

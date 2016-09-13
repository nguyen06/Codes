import libxml2
import sys

'''
purpose
	store the information from an answer element
'''
class Answer:
	def __init__(self, index, path, result, answer, time):
		self.index = index
		self.path = path
		self.result = result
		self.answer = answer
		self.time = time

'''
purpose
	Store the information from a display element.
'''
class Display:
	def __init__(self, index, path, time):
		self.index = index
		self.path = path
		self.time = time

'''
purpose
	Extract the information from log_file and return it as a list
	of answer and display objects.
preconditions
	log_file is the name of a legal, readable quiz log XML file
'''
def load_quiz_log(log_file):
	#pass # REPLACE THIS LINE WITH YOUR IMPLEMENTATION
	parse_tree = libxml2.parseFile(log_file)
	context = parse_tree.xpathNewContext()
	#print "bbbbb"
	root = parse_tree.getRootElement()
	#print "vvvvvv"
	childs = root.children
	#print "fffff"
	L = []
	while childs is not None:
		#print "llll"
		if childs.name == 'answer':
			answerChild = childs.children
			for x in answerChild:
				if x.name == 'index':
					index =int(x.content)
					#print index
				elif x.name == 'path':
					path = x.content
					#print path
				elif x.name == 'result':
					result = x.content
					if not result:
						result = None
					
					#print result
				elif x.name == 'answer':
					answer = x.content
					if not answer:
						answer = None
					#print answer
				elif x.name == 'time':
					time = x.content
					if not time:
						time = None
					#print time
			L.append(Answer( index, path, result, answer, time))
		if childs.name == 'display':
			displayChild = childs.children
			for y in displayChild:
				if y.name == 'index':
					index = int(y.content)
				elif y.name == 'path':
					path = y.content
				elif y.name == 'time':
					time = y.content
			L.append(Display(index,path,time))
		childs = childs.next
	return L
'''
purpose
	Return the number of distinct questions in log_list.
preconditions
	log_list was returned by load_quiz_log
'''
def compute_question_count(log_list):
	 #pass # REPLACE THIS LINE WITH YOUR IMPLEMENTATION
	 count = 0
	 for x in log_list:
		if isinstance(x,Answer):
			count +=1
		else:
			break
	 	
	 return count
'''
purpose
	Extract the list of marks.
	For each index value, use the result from the last non-empty answer,
	or 0 if there are no non-empty results.
preconditions
	log_list was returned by load_quiz_log
'''
def compute_mark_list(log_list):
	 #pass # REPLACE THIS LINE WITH YOUR IMPLEMENTATIO
	N = compute_question_count(log_list)
	#print N
	L =[]
	temp = 0
	for y in range(N):
		#print "aaa"
		for x in log_list:
			if isinstance(x,Answer):
			#print "bbbb"
			#for y in range(N):
				#print y
				if x.index == y:
					if x.result != None:
						temp = int(x.result)
		L.append(temp)
	return L	
	 	
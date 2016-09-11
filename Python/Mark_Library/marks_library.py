import libxml2
import sys

'''
purpose
	return the course mark for student s
preconditions
	student is a list of the form:
		[last_name, first_name, student_id, marks]
		where
		marks is a list of the form: [ [mark_id,score], ... ]
	assignments is a dictionary of the form:
		{mark_id:[points, percentage], ... }
'''
def compute_mark(student, assignments):
	courseMark = 0.0
	for y in student:
	   #print y
	   if type(y) == list:
                #print y
                for k in y:
		    #print k
		    for i in k:
			#print i
			if type(i) == str:
			    #print i
			    for j in assignments[i]:
				#print j
				if type(j) == float:
				    percentage = j
				    #print percentage
				else:
				    point = j
				    #print point
			else:
			    mark = i
			    #print mark
			    AssignMark = (mark*percentage)/point
			    #print AssignMark
			    courseMark += AssignMark
			    #print courseMark
	return courseMark
'''
purpose
	extract the information from a and return it as a list:
		[mark_id, points, percentage]
preconditions
	s is an assignment element from a legal students XML file
'''
def extract_assignment(a):
	#pass # REPLACE THIS LINE WITH YOUR IMPLEMENTATION
	L = []
	for x in a:
		if x.name =='points':
			value1 =int(x.content)
			L.append(value1)
		elif x.name =='mark_id':
			value2 = x.content
			type(value2)
			L.append(value2)
		elif x.name =='percentage':
			value3 =float(x.content)
			L.append(value3)
	#print L
	for y in L:
		#for x in y:
		     if type(y) == str:
			# locate the index of 'mark_id'
			a = L.index(y)
			L[a] , L[0] = L[0],L[a]
	#print L
	#print L[0]
	for i in L:
		#for z in i:
		     if type(i) == float:
			# locate the index of 'point'
			b = L.index(i)
			#print L[0]
			#print L[2]
			L[b], L[2] = L[2], L[b]

	#print L
	return L
'''
purpose
	extract the information from s and return it as a list:
		[last_name, first_name, student_id, marks]
		where
		marks is a list of the form: [ [assignment_id,score], ... ]
preconditions
	a is a student element from a legal students XML file
'''
def extract_student(s):
	L =[]
	for x in s:
	    if x.name == 'first_name':
		value1 = x.content
		L.append(value1)
	    elif x.name == 'last_name':
		value2 = x.content
		L.append(value2)
	    elif x.name == 'student_id':
		value3 = x.content
		L.append(value3)

	for y in s:
	    S =[]
	    if y.name == 'marks':
		#print "i am here"
		child = y.children
		for z in child:
			if z.name == 'mark':
				S1=[]
				#print " i am here in mark"
				grandChild =z.children
				for k in grandChild:
					if k.name == 'mark_id':
						#print k.content
						S1.append(k.content)
					elif k.name == 'score':
						value =int(k.content)
						S1.append(value)
				S.append(S1)
		L.append(S)
	# now we have list L but not in order for last_name
	for x in L:
		for y in x:
			if y == 'l':
				# locate index of last_name
				a = L.index(x)
				L[a] , L[0] = L[0],L[a]
	# the first name should be in the next position
	for z in L:
		for k in z:
			if k == 'f':
				# locate index of first_name
				b = L.index(z)
				L[b], L[1] = L[1],L[b]
	#we don't need to relocate the id since from "append" and after rearrage the 
	# first name and last name, the id must in position 3
	
	# rearrange in the marks list
	for x in L:
		if type(x) == list:
			for y in x:
			     if type(y) == list:
					for z in y:
						#for k in z:
							if type(z) == str:
								# locate index of assignment id
								a =y.index(z)
								y[a], y[0] = y[0], y[a]
	return L
		
		

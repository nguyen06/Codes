def do_twice(f, val):
	f(val)
	f(val)
def print_twice(val):
	print (val)
	print (val)
	

do_twice(print_twice, 'spam')

def do_four(f, val):
	do_twice(f,val)
	do_twice(f, val)
do_four(print_twice, 'spam')


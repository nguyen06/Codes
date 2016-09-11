class Point(object):
	"""
		Represents a point in 2-d space
	"""

def print_point(p):
	""" Print a Point object in human-readable format """
	print '(%g, %g)' % (p.x, p.y)

class Rectangle(object):
	""" Represents a rectangle.
		attribute: width, height, corner.
	"""
def find_center(rect):
	""" Returns a Point at the center of a Rectangle. """
	p = Point()
	p.x = rect.corner.x + rect.width/2.0
	p.y = rect.corner.y + rect.height/2.0
	return p
def grow_rectangle(rect, dwidth, dheight):
	"""Modify the rectangle by adding to its width and height.
	rect: rectangle object.
	dwidth: change in width (can be negative)
	dheight: change in height (can be negative).
	"""
	rect.width += dwidth
	rect.height += dheight

def main():
	blank = Point()
	blank.x = 3
	blank.y = 4
	print 'blank'
	print_point(blank)

	box = Rectangle()
	box.width = 100.0
	box.height = 200.0
	box.corner = Point()
	box.corner.x = 0.0
	box.corner.y = 0.0

	center = find_center(box)
	print 'center'
	print_point(center)

	print box.width
	print box.height
	print 'grow'
	grow_rectangle(box, 50, 100)
	print box.width
	print box.height
if __name__== '__main__':
	main()
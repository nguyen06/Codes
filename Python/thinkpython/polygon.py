# draw square
from swampy.TurtleWorld import*
import sys
import math

def draw_square(p,lenght):
	
	for i in range(4):
		fd(p,length)
		lt(p)

def draw_polygon(p,length, n):
	for i in range(10):
		fd(p,length)
		lt(p,360/n)
def polyline(t, n, length, angle):
    """Draws n line segments.

    t: Turtle object
    n: number of line segments
    length: length of each segment
    angle: degrees between segments
    """
    for i in range(n):
        fd(t, length)
        lt(t, angle)

def draw_arc(t, r, angle):
	"""
		Draws an arc with the given radius and angle
		t: Turtle
		r: radius
		angle: angle subtended by the arc, in degrees
	"""
	arc_length = 2 * math.pi * r * abs(angle) / 360
	#print arc_length
	n = int(arc_length / 2) +1
	step_length = arc_length / n
	step_angle = float(angle) / n

	# making a slight left turn before starting reduces
	# the error caused by the linear approximation of the arc

	lt(t, step_angle/20)
	polyline(t, n, step_length, step_angle)
	rt(t,step_angle/2)

def draw_circle(t, r):
	draw_arc(t,r,360)

if __name__ == '__main__':
	world = TurtleWorld()
	pip = Turtle()
	pip.delay = 0.001
	length = 50
	draw_square(pip,length)
	n = 10
	draw_polygon(pip,20,n)
	r = 50
	angle = 124
	draw_arc(pip,r, angle)
	polyline(pip, n, length,angle)
	draw_circle(pip, r)

	wait_for_user()
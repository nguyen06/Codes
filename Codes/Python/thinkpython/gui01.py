"""This module contains code from
Think Python by Allen B. Downey
http://thinkpython.com

Copyright 2012 Allen B. Downey
License: GNU GPLv3 http://www.gnu.org/licenses/gpl.html

"""

from swampy.Gui import *

g = Gui()
g.title('')

def make_label():
	g.la(text = "thank you")
button = g.bu(text='no, press me', command = make_label)

canvas = g.ca(width = 500, height = 500)
canvas.config(bg = 'blue')

item = canvas.circle([0,0], 100, fill = 'red')
g.mainloop()
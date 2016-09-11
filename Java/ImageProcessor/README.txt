Included in this directory are:

* Sample source image files
* Sample output image and .txt files (with commands used to generate them shown below)
* Skeleton code for ImageProcessor.java with two already-written methods.

=====COMMANDS USED TO CREATE SOME OF THE FILES IN THIS DIRECTORY===================

java ImageProcessor -ascii letter_Q_grayscale.jpg Q_ascii.txt
                                                                                                  
java ImageProcessor -reflectV letter_Q_grayscale.jpg reflectV_example.jpg

java ImageProcessor -reflectH letter_Q_grayscale.jpg reflectH_example.jpg

java ImageProcessor -tile 4 3 letter_Q_grayscale.jpg replicate_example.jpg

java ImageProcessor -adjustBrightness 50 gumby_grayscale.jpg gumby_grayscale_bright.jpg


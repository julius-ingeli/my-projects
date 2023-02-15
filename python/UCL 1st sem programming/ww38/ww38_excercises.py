"""
Exercise 1: Type the following statements in the Python interpreter to
see what they do
5
x = 5
x + 1


RESULT : It does not do anything, since it is printing out nothing. Apart from that, x gets an assigned value of 5, and then it should count x + 1 (which should be 6).


Exercise 2: Write a program that uses input to prompt a user for their
name and then welcomes them.
"""

nameInput = input("Enter your name: ")
print ("Hello " + nameInput + "!")
"""
 Exercise 3: Write a program to prompt the user for hours and rate per
hour to compute gross pay.
"""
hours = int(input("Enter amount of hours: "))
rate = int(input("Enter rate per hour: "))

pay = hours * rate

print("The pay is: " + str(pay) + "DKK.")
"""

Exercise 4: Assume that we execute the following assignment statements:

width = 17
height = 12.0
ň
For each of the following expressions, write the value of the expression and the
type (of the value of the expression).

1. width//2 - 8 , integer

2. width/2.0 - 8.5 - float

3. height/3 - 4 - float

4. 1 + 2 * 5 - 11 - integer
"""


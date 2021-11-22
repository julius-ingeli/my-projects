"""Exercise 1: Run the program on your system and see what numbers
you get. Run the program more than once and see what numbers you
get.
RESULTS: 7, 10, 6, 7, 9, 8
"""
import random
num = random.randint(5, 10)
print(num)

"""
Exercise 2: Move the last line of this program to the top, so the function
call appears before the definitions. Run the program and see what error
message you get.
repeat_lyrics()

def print_lyrics():
      print("I'm a lumberjack, and I'm okay.")
      print('I sleep all night and I work all day.')
def repeat_lyrics():
      print_lyrics()
      print_lyrics()


RESULT: Traceback (most recent call last):
  File "C:/Users/julko/AppData/Local/Programs/Python/Python38-32/week40 excercises.py", line 15, in <module>
    repeat_lyrics()
NameError: name 'repeat_lyrics' is not defined


Exercise 3: Move the function call back to the bottom and move the
definition of print_lyrics after the definition of repeat_lyrics. What
happens when you run this program?
RESULT:  It does the same thing.
"""
def print_lyrics():
      print("I'm a lumberjack, and I'm okay.")
      print('I sleep all night and I work all day.')
def repeat_lyrics():
      print_lyrics()
      print_lyrics()

repeat_lyrics()
"""
Exercise 4: What is the purpose of the “def” keyword in Python?
a) It is slang that means “the following code is really cool”
b) It indicates the start of a function
c) It indicates that the following indented section of code is to be stored for later
d) b and c are both true
e) None of the above

RESULT: B
Exercise 5: What will the following Python program print out?
def fred():
      print("Zap")
def jane():
      print("ABC")
jane()
fred()
jane()
a) Zap ABC jane fred jane
b) Zap ABC Zap
c) ABC Zap jane
d) ABC Zap ABC
e) Zap Zap Zap
RESULT : D

Exercise 6: Rewrite your pay computation with time-and-a-half for over-
time and create a function called computepay which takes two parameters
(hours and rate).
"""
def computepay(a,b):
      try:
            hours = int(a)
            rate = int(b)
            if hours > 40:
                  hours = hours - 40
                  increasedPay = hours * rate * 1.5
                  normPay = rate * 40
                  finalPay = normPay + increasedPay
                  return(finalPay)
            else:
                  finalPay = (rate * hours)
                  return(finalPay)
      except:
            print("Please use a numerical value.")

a = input("Input hours here: ")
b = input("Enter rate here: ")
print("The pay is: " + str(computepay(a, b)) + "€.")
c = input("Press enter to continue.")

"""
Exercise 7: Rewrite the grade program from the previous chapter using
a function called computegrade that takes a score as its parameter and
returns a grade as a string.
"""
def computegrade(a):
      score = float(a)
      if score >= 0 and score <= 1:
            if score >= 0.9:
                        print("The grade is: A")
            elif score >= 0.8:
                        print("The grade is: B")
            elif score >= 0.7:
                        print("The grade is: C")
            elif score >= 0.6:
                        print("The grade is: D")
            elif score < 0.6:
                        print("The grade is: F")
      else:
            print("the score is out of range.")

a = input("input score here")
print(str(computegrade(a)))








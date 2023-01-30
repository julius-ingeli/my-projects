"""
Exercise 1: Write a program to read through a file and print the contents
of the file (line by line) all in upper case.
"""
opener = open("D:/python test dir/" + "nice.txt")
reader = opener.readline()
upper = reader.upper()
print(upper)

count = len(open("D:/python test dir/" + "nice.txt").readlines(  ))
print (count)

"""
Exercise 2: Write a program to prompt for a file name, and then read through the file and look for lines of the form:
X-DSPAM-Confidence: 0.8475
When you encounter a line that starts with “X-DSPAM-Confidence:” pull apart the line to extract the floating-point number on the line. Count these lines and then compute the total of the spam confidence

Exercise 3: Sometimes when programmers get bored or want to have abit of fun, they add a harmless Easter Egg to their program. Modify the program that prompts the user for the file name so that it prints a funny message when the user types in the exact file name “na na booboo”. The program should behave normally for all other files which exist and don’t exist. 

"""
allNums = []

def SCcounter(opener):
    lineCount = 0
    numCount = 0
    for line in opener:
        if line.startswith("X-DSPAM-Confidence:"):
            numbers = float(line[19:])
            numCount += 1
            allNums.append(numbers)
            sumUp = sum(allNums)
            average = sumUp / numCount
            return(average)
def output():
    txt =("The average spam confidence is: ")
    return txt
    


begin = input("Which file do you want to open?")
try:
    if begin == "mbox":
        opener = open("D:/python test dir/" + "mbox.txt")
        print(str(output()) + str(SCcounter(opener)))
    if begin == "mbox-short":
        opener = open("D:/python test dir/" + "mbox-short.txt")
        print(str(output()) + str(SCcounter(opener)))
    if begin == "na na booboo":
        print("EASTER EGG!")
except:
    print("Not a valid file")


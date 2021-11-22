"""
Exercise 1: Write a function called chop that takes a list and modifies it, removing the first and last elements, and returns None. Then write a function called middle that takes a list and returns a new list that contains all but the first and last elements.
"""

def chop(listInput):
      listInput.remove("Ah")
      listInput.remove("culture")
      print(listInput)
      
removed = []
listInput = []
listInput = ["Ah","yes","the","man","of","culture"]
print(chop(listInput))


"""
Exercise 2: Figure out which line of the above program is still not properly guarded. See if you can construct a text file which causes the program to fail and then modify the program so that the line is properly guarded and test it to make sure it handles your new text file.
Exercise 3: Rewrite the guardian code in the above example without two if statements. Instead, use a compound logical expression using the or logical operator with a single if statement.
"""
fhand = open('mbox-short.txt')
count = 0
for line in fhand:
      words = line.split()
# print('Debug:', words)
      if len(words) == 0 or words[0] != 'From' : #this one
            continue
      print(words[2])

"""
Exercise 4: Download a copy of the file www.py4e.com/code3/romeo.txt. Write a program to open the file romeo.txt and read it line by line. For each line, split the line into a list of words using the split function. For each word, check to see if the word is already in a list. If the word is not in the list, add it to the list. When the program completes, sort and print the resulting words in alphabetical order.
"""
sorter = []
trash = []
openup = open("romeo.txt")
for line in openup:
      words = line.split()
      print(words)
      print()
      for word in words:
            if word in sorter:
                  trash.append(word)
            else:
                  sorter.append(word)

sorter.sort()
print("sorted:")
print(sorter)
print("trash:")
print(trash)




"""
Exercise 5: Write a program to read through the mail box data and when you find line that starts with “From”, you will split the line into words using the split function. We are interested in who sent the message, which is the second word on the From line.
"""
stuff = []
count = 0
yoopenup = open("mbox-short.txt")
for lines in yoopenup:
      if lines.startswith("From:"):
            print(lines.split(" ")[1])
            count += 1
print ("Entries : " + str(count) )
"""
Exercise 6: Rewrite the program that prompts the user for a list of numbers and prints out the maximum and minimum of the numbers at the end when the user enters “done”. Write the program to store the numbers the user enters in a list and use the max() and min() functions to compute the maximum and minimum numbers after the loop completes.
"""
#same as in ww41excercises.py

"""
Exercise 1: Write a program which repeatedly reads numbers until the user enters “done”. Once “done” is entered, print out the total, count, and average of the numbers. If the user enters anything other than a number, detect their mistake using try and except and print an error message and skip to the next number.
Exercise 2: Write another program that prompts for a list of numbers as above and at the end prints out both the maximum and minimum of the numbers instead of the average.
"""
def small (field):
      field.sort()
      smallest = min(field)
      return(smallest) #defining the function to find the smallest number using the min function for fields
def big (field):
      biggest = max(field)
      return(biggest) #defining the function to find the biggest number using the max function for fields

print("Enter numbers to count them, their total and average!\nIn order to close the program, type done")
total = 0 
numbers = 0  #setting values for the counting of numbers and the total.
field =  [] #an empty field to find the lowest and highest numbers.
while True:
      inpNum = input("enter number: ") #prompting user to enter a number
      if inpNum.isdigit():
            field.append(inpNum)
      if inpNum == "done":
            break #if user enters "done" in the prompt, it will break from the infinite loop and will proceed to the last line of code.
      try:
            floatNum = float(inpNum)# converts the value of code from string to float. 
      except:
            print("input is not valid, enter a number.") #a check whether the user enters a number or "done", if not, it will execute this line of code.
            continue
      numbers = numbers + 1 #counting the amount of numbers entered.
      total = total + floatNum #counts the numbers entered.
print(field)      
print ("The total is: " + str(total) + " ,\nthe amount of numbers is: " + str(numbers) + " ,\nsmallest is the number: " + str(small(field)) +",\nthe biggest is "+ str(big(field)) + "\nand the average is: " + str(total/numbers)+".")#here it prints the total of the inputted numbers, the amount of numbers inputted, smallest and biggest of the numbers and also its average.


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

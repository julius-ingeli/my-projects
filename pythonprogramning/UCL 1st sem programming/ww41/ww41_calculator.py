def add(a,b):
      c = float(a) + float(b)
      return(c)
def sub(a,b):
      c = float(a) - float(b)
      return(c)
def mul(a,b):
      c = float(a) * float(b)
      return(c)
def div(a,b):
      if b == "0":
            print("division by zero is not accepted.")
      else:
            c = float(a) / float(b)
            return(c)

print("INITIALIZING CALCULATOR V1\nIn order to terminate the program, type done in the input.\n")

while True:
      a = input("Enter first number: ")
      if a.isdigit() == False:
            print("enter only numbers here. ")
            continue
                  
      b = input ("Enter second number: ")
      if b.isdigit() == False:
            print("enter only numbers here. ")
            continue

      inp = input("Select what operation you want to do with the numbers:\n1. add\n2.subtract\n3.multiply\n4.divide:")
      try:
            if inp == "done":
                  break

            if inp=="1":
                  print(add(a,b))
                  continue
            if inp=="2":
                  print(sub(a,b))
                  continue
            if inp=="3":
                  print(mul(a,b))
                  continue
            if inp == "4":
                  print(div(a,b))
                  continue
            else:
                  print("not an option.")
      except  (RuntimeError, TypeError, NameError, ValueError):
                  print("not an option.")

print("program terminated.")

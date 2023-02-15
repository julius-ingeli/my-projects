bigL = []
newL = []
resD = {}


f = open("day1 calories.txt")
for x in f:    
    bigL.append(x[:-1])
    
#PART ONE
  
itr = 1
for x in bigL:
    if x.isnumeric() == True:
        x = int(x)
        newL.append(x)

    else:
        elfSum = sum(newL)
        resD['Elf no.'+str(itr)]=elfSum
        newL=[]
        itr+=1
        
for key,val in resD.items():
    print(key,':',str(val)+' cal')
    
    
#PART TWO

calL = []
for cal in resD.values():
    calL.append(cal)
    
calL = sorted(calL)
print(calL)

lasthree = [val for val in calL[-3:]]

print(sum(lasthree))
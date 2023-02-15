initL = []
fhand = open('day2 data.txt','r')


for r in fhand:
    initL.append(r.replace("\n",""))

gamescount = len(initL)
#print(initL)
yscore = 0
zscore = 0
wincount = 0
losscount = 0
drawcount = 0

for x in initL:
    #print(x)
    y=0
    z = 0
    if x[0] == 'A': #rock
        y = 1
    if x[0] == 'B': #paper
        y = 2
    if x[0] == 'C': #scissors
        y = 3 
    if x[-1] == 'X': #rock
        z = 1
    if x[-1] == 'Y': #paper
        z = 2
    if x[-1] == 'Z': #scissors
        z = 3
        
    
    #print(x[0],y,z,x[-1])
    
    if y==z: #draw
        yscore+=y+3
        zscore+=z+3
        #print('draw!',end=' ')
        drawcount+=1
    #scissor wins
    if z == 3 and y == 2: #player win
        yscore+=y
        zscore+=z+6
        #print('player wins!',end=' ')
        wincount+=1
    if z == 2 and y == 3: #opponent win
        zscore+=z
        yscore+=y+6
        #print('opponnent wins!',end=' ')
        losscount+=1
    #paper wins
    if z == 2 and y == 1:
        yscore+=y
        zscore+=z+6
        #print('player wins!',end=' ')
        wincount+=1
    if z == 1 and y == 2:
        zscore+=z
        yscore+=y+6
        #print('opponnent wins!',end=' ')
        losscount+=1
    #rock wins
    if z == 1 and y == 3:
        yscore+=y
        zscore+=z+6
        #print('player wins!',end=' ')
        wincount+=1
    if z == 3 and y == 1:
        zscore+=z
        yscore+=y+6
        #print('opponnent wins!',end=' ')
        losscount+=1
    
    
    #print('oppnent score:',yscore,'player score:',zscore)
    
print("player score:",zscore)
print("opponent score:",yscore)

if zscore > yscore:
    print('player wins!')
if zscore < yscore:
    print('opponent wins!')
if zscore == yscore:
    print('draw!')
    
winrate1= round(((wincount/gamescount)*100),3)
lossrate1= round(((losscount/gamescount)*100),3)
drawrate1= round(((drawcount/gamescount)*100),3)
    
print('stats:',"\nwins:",wincount,'\nlosses:',losscount,'\ndraws:',drawcount)
print('winrate:',winrate1,'%\nlossrate:',lossrate1,'%\ndrawrate:',drawrate1,'%')

#----PART 2 ----



yscore = 0
zscore = 0
wincount = 0
losscount = 0
drawcount = 0

for x in initL:
    #print(x)
    y = 0
    z = 0
    if x[0] == 'A': #rock
        y = 1
    if x[0] == 'B': #paper
        y = 2
    if x[0] == 'C': #scissors
        y = 3 
    
    if x[-1] == 'X': #lose
        #print('L',end=' ')
        if y == 1:
            z = 3
        if y == 2:
            z = 1
        if y == 3:
            z = 2
        
    if x[-1] == 'Y': #draw
        #print('D',end=' ')
        z = y
    if x[-1] == 'Z': #win
        #print('W',end=' ')
        if y == 1:
            z = 2
        if y == 2:
            z = 3
        if y == 3:
            z = 1 

    
    if y==z: #draw
        yscore+=y+3
        zscore+=z+3
        #print('draw!',end=' ')
        drawcount+=1
    #scissor wins
    if z == 3 and y == 2: #player win
        yscore+=y
        zscore+=z+6
        #print('player wins!',end=' ')
        wincount+=1
    if z == 2 and y == 3: #opponent win
        zscore+=z
        yscore+=y+6
        #print('opponnent wins!',end=' ')
        losscount+=1
    #paper wins
    if z == 2 and y == 1:
        yscore+=y
        zscore+=z+6
        #print('player wins!',end=' ')
        wincount+=1
    if z == 1 and y == 2:
        zscore+=z
        yscore+=y+6
        #print('opponnent wins!',end=' ')
        losscount+=1
    #rock wins
    if z == 1 and y == 3:
        yscore+=y
        zscore+=z+6
        #print('player wins!',end=' ')
        wincount+=1
    if z == 3 and y == 1:
        zscore+=z
        yscore+=y+6
        #print('opponnent wins!',end=' ')
        losscount+=1
        
    #print('opponent score:',yscore,'player score:',zscore)
        
print("player score:",zscore)
print("opponent score:",yscore)

if zscore > yscore:
    print('player wins!')
if zscore < yscore:
    print('opponent wins!')
if zscore == yscore:
    print('draw!')
    
winrate2= round(((wincount/gamescount)*100),3)
lossrate2= round(((losscount/gamescount)*100),3)
drawrate2= round(((drawcount/gamescount)*100),3)
    
print('stats:',"\nwins:",wincount,'\nlosses:',losscount,'\ndraws:',drawcount)
print('winrate:',winrate2,'%\nlossrate:',lossrate2,'%\ndrawrate:',drawrate2,'%')

winratediff = winrate1-winrate2
lossratediff = lossrate2-lossrate1
drawratediff = drawrate2-drawrate1
print("Conclusion:\nSince trusting the elf and not trusting your instincts, your winrate decrased by",winratediff,'%\nyour loss rate increased by', lossratediff,'%\nalongside with your drawrate by',drawratediff,'%.\n\n\nDO NOT TRUST ELVES, TRUST YOUR INSTINCTS!')
import sys

def harmonicky_rad(n):
    x = 0
    menovatel = 1
    while x <= n:
        a = 1/menovatel
        x+=a
        menovatel += 1
    vysledok = "menovatel:",menovatel-1,"sucet:",x
    return vysledok
n=int(sys.argv[1])

print(harmonicky_rad(n))                                                        

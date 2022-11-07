# 1065, silver4

def isHansu(num):
    numlist = list(map(int,str(num)))
    if n <= 99:
        return 1

    for i in range(len(numlist)-2):
        if (numlist[i]-numlist[i+1] != numlist[i+1]-numlist[i+2]):
            return 0

    return 1


n = int(input())
count = 0
if n <= 99:
    print(n)

else:
    for i in range(n):
        count += isHansu(i+1)
    print(count)

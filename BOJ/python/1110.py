num = int(input()) # n = 12
#initialization
#variables update -> temp necessity check!
a_0 = num // 10 # a_0 = 12/10 = 1
b_0 = num % 10 # b_0 = 12%10 = 2
a, b, i = a_0, b_0, 0 # a, b = 1, 2

while True:
    i += 1
    # a, b = b, (a+b)%10
    temp = a # temp = 1 
    a = b # new a = 2
    b = (temp + b) % 10 # new b = (1+2)%10 = 3
    if ((a==a_0) and (b==b_0)): # a = 2, b = 3
        break

print(i)

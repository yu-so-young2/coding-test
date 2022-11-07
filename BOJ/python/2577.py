# B2 2577 숫자의개수
# 1) 길이 10짜리 List로 수 세기
list1 = [0 for i in range(10)]

num = 1
for i in range(3): 
    num *= int(input())
num = str(num)

for i in range(len(num)):
    list1[int(num[i])] += 1

for a in list1:
    print(a)

# 2) list count function
num2 = input #declartion, not excute
n2 = int(num2())*int(num2())*int(num2())
list2 = list(map(int,str(n2)))
for i in range(10):
    print(list2.count(i))

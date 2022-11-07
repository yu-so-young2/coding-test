#1152 B2

sentence = input()
count, check = 0, 1 #count
    if (sentence[i] != " "):
        count += check
        check = 0
    else: check = 1
print(count)

# 2 method 
s = input().strip() 
print(s.count(' ') + 1 if s else 0)

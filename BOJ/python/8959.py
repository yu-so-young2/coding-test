case_num = int(input())
scores = [0 for i in range(case_num)]

for i in range(case_num):
    result = input()
    cur = 0
    for j in range(len(result)):
        if (result[j] == "O"):
            cur += 1
            scores[i] += 1 * cur
        else:
            cur = 0

for i in range(case_num):
    print(scores[i])



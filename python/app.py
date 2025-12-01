from threading import *
from time import * 

def taskA():
    for i in range(10):
        sleep(1)
        print("Task A : ",i)


def taskB():
    for i in range(10):
        sleep(1)
        print("Task B :",i)


t1 = Thread(target=taskA)

t2 = Thread(target=taskB)


t1.start()
t2.start()

t1.join()

t2.join()
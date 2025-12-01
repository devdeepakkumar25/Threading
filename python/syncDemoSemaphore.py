from threading import * 
from time import * 

def display(str1):
    l.acquire()
    for x in str1:
        print(x)
        sleep(1)
    l.release()

l = Semaphore(1)

t1 = Thread(target=display, args=("Hello World",))
t2 = Thread(target=display, args=("You are welcome",))
t3 = Thread(target=display, args=("0123456789",))

t1.start()
t2.start()
t3.start()   

t1.join()
t2.join()
t3.join()   
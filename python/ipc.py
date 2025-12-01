from threading import *
from time import *

class MyData:
    def __init__(self):
        self.data = 0
        self.flag = False 
        self.lock = Lock()
    
    def put(self, d):
        while self.flag is True:
            sleep(0.0001)  # prevents 100% CPU

        self.lock.acquire()
        self.data = d 
        self.flag = True 
        self.lock.release()
        sleep(1)
    
    def get(self):
        while self.flag is False:
            sleep(0.0001)

        self.lock.acquire()
        x = self.data 
        self.flag = False 
        self.lock.release()
        sleep(1)

        return x 
    

def producer(data):   # <-- FIXED (now accepts data)
    i = 1
    while True:
        data.put(i)
        print("Producer:", i)
        # sleep(1)
        i += 1
    
def consumer(data):
    while True:
        x = data.get()
        print("Consumer:", x)
        # sleep(1)


data = MyData()

t1 = Thread(target=lambda: producer(data))   # FIXED
t2 = Thread(target=lambda: consumer(data))   # FIXED

t1.start()
t2.start()

t1.join()
t2.join()

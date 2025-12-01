from threading import Condition
from threading import Thread
from time import sleep



class MyData:
    def __init__(self):
        self.data = None
        self.available = False
        self.cv = Condition()

    def put(self, d):
        with self.cv:
            while self.available:      # wait until data is consumed
                self.cv.wait()

            self.data = d
            self.available = True
            self.cv.notify()           # wake up consumer

    def get(self):
        with self.cv:
            while not self.available:  # wait until producer produces
                self.cv.wait()

            x = self.data
            self.available = False
            self.cv.notify()           # wake up producer
            return x

     

def producer(data):
    i = 1
    while True:
        data.put(i)
        print("Producer:", i)
        i += 1
        sleep(1)

def consumer(data):
    while True:
        x = data.get()
        print("Consumer:", x)
        sleep(1)

data = MyData()

t1 = Thread(target=lambda: producer(data))
t2 = Thread(target=lambda: consumer(data))

t1.start()
t2.start()

t1.join()
t2.join()

# from threading import * 
# from time import * 

# class MyData: 
#     def __init__(self):
#         self.data = 0 
#         self.cv = Condition()

#     def put(self,d):
#         self.cv.acquire()
#         self.cv.wait(timeout=0)
#         self.data = d 
#         self.cv.notify()
#         self.cv.release()
#         sleep(1)

#     def get(self):
#         self.cv.acquire()
#         self.cv.wait(timeout=0)
#         x = self.data 
#         self.cv.notify()
#         self.cv.release()
#         sleep(1)
#         return x   

     

# def producer(data):   # <-- FIXED (now accepts data)
#     i = 1
#     while True:
#         data.put(i)
#         print("Producer:", i)
#         # sleep(1)
#         i += 1
    
# def consumer(data):
#     while True:
#         x = data.get()
#         print("Consumer:", x)
#         # sleep(1)


# data = MyData()

# t1 = Thread(target=lambda: producer(data))   # FIXED
# t2 = Thread(target=lambda: consumer(data))   # FIXED

# t1.start()
# t2.start()

# t1.join()
# t2.join()

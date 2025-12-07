import threading 
import time 

# class MyData: 
#     def display(self,text):
#         # with self.lock:
#         #     for ch in text: 
#         #         print(ch,end=" ")
#                 # time.sleep(0.1)
#

class MyData: 
    def __init__(self):
        self.lock = threading.Lock()
    
    def display(self,text):
        with self.lock:
            for ch in text: 
                print(ch,end=" ")
                time.sleep(0.1)
 

class MyThread1(threading.Thread):
    def __init__(self,data):
        super().__init__()
        self.data = data
    

    def run(self):
        self.data.display("Hello World")
    
class MyThread2(threading.Thread):
    def __init__(self,data):
        super().__init__()
        self.data = data 
    
    def run(self):
        self.data.display("Welcome all")


lock = threading.Lock()

def display(text):
    with lock:
        for ch in text:
            print(ch,end="")
            time.sleep(0.1)

def thread1():
    display('Hello World')

def thread2():
    display("Welcome all")



if __name__ == "__main__":
    # d = MyData()

    # t1 = MyThread1(d)
    # t2 = MyThread2(d)

    # t1.start()
    # t2.start()

    t1 = threading.Thread(target=thread1)
    t2 = threading.Thread(target=thread2)

    t1.start()
    t2.start()
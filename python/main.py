import threading 
import time 

class MyThread(threading.Thread):
    def run(self):
        i = 1
        while True:
            print(f"{i} Hello")
            i += 1

class MyRunnable:
    def run(self):
        i = 1
        while True:
            print(f"{i} Hello")

            i += 1
        
def my_function():
    i = 1
    while True:
        print(f"{i} Hello ")
        i += 1
        

if __name__ == "__main__":

    # runnable = MyRunnable()
    # th = threading.Thread(target=runnable.run)
    # th.start()
    # th = MyThread()
    # th.start()
    th = threading.Thread(target=my_function)
    th.start()

    i = 0

    while True:
        print(f"{i} World")
        i += 1





































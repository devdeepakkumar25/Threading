import threading 
import time 

class MyData:
    def __init__(self):
        self.value = 0
        self.falg = True
        self.condition = threading.Condition()

    def set(self,v):
        with self.condition:
            while not self.falg:
                self.condition.wait()
            
            self.value = v 
            self.flag = False
            self.condition.notify_all()
    
    def get(self):
        with self.condition: 
            while  self.flag:
                self.condition.wait()
            
            x = self.value 
            self.flag = True
            self.condition.notify_all()
            return x 

class Producer(threading.Thread):
    def __init__(self,data):
        super().__init__()
        self.data = data 

    def run(self):
        count = 1 
        while True:
            self.data.set(count)
            print("Producer ",count)
            count += 1
            time.sleep(0.1)
    
class Consumer(threading.Thread):
    def __init__(self,data):
        super().__init__()
        self.data = data 

    def run(self):
        while True:
            value = self.data.get()
            print("Consumer",value)
            time.sleep(0.1)



value = 0
flag = True     
condition = threading.Condition()

def producer():
    global value, flag
    count = 1
    while True:
        with condition:
            while not flag:
                condition.wait()

            value = count
            print("Producer", count)
            count += 1

            flag = False
            condition.notify_all()

        time.sleep(0.1)


def consumer():
    global value, flag
    while True:
        with condition:
            while flag:
                condition.wait()

            print("Consumer", value)

            flag = True
            condition.notify_all()

        time.sleep(0.1)

if __name__ == "__main__":
    # data = MyData()
    # p = Producer(data)
    # c = Consumer(data)

    # p.start()
    # c.start()

    t1 = threading.Thread(target=producer)
    t2 = threading.Thread(target=consumer)

    t1.start()
    t2.start()
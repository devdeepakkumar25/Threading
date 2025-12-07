import threading 
import time 

class ATM:
    def __init__(self):
        self.lock = threading.Lock()

    
    def check_balance(self,name):
        with self.lock:
            print(name + " Checking balance")
            time.sleep(1)
            print("Balance")
        
    
    def withdraw(self,name,amount):
        with self.lock:
            print(name + "WIthdrawing ")
            time.sleep(1)
            print(amount)
    
class Customer(threading.Thread):
    def __init__(self,name,atm,amount):
        super().__init__()

        self.name = name
        self.atm = atm 
        self.amount = amount

    def use_atm(self):
        self.atm.check_balance(self.name)
        self.atm.withdraw(self.name,self.amount)
    
    def run(self):
        self.use_atm()


if __name__ == "__main__":
    atm = ATM()
    c1 = Customer("John",atm,100)
    c2 = Customer("Smith",atm,200)

    c1.run()
    c2.run()
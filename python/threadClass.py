from threading import *
from time import * 

class Alphabets(Thread):
    def run(self):
        for i in range(65,91):
            print(chr(i))
            sleep(0.1)



t = Alphabets()
t.start()

for i in range(65,91):
    print(i)
    sleep(1)


t.join()
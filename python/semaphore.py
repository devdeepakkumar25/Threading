# from threading import Semaphore ,Thread 
# import time 

# sem = Semaphore(3)


# def task(name):
#     sem.acquire()

#     for i in range(5):
#         print("{} working".format(name))
#         time.sleep(1)

#     sem.release()


# if __name__ == "__main__":
#     t1 = Thread(target=task,args=('Thread-1',))
#     t2 = Thread(target=task,args=('Thread-1',))

#     t3 = Thread(target=task,args=('Thread-1',))

#     t4 = Thread(target=task,args=('Thread-1',))

#     t5 = Thread(target=task,args=('Thread-1',))

#     t1.start()

#     t2.start()
#     t3.start()
#     t4.start()
#     t5.start()

from threading import Semaphore, Thread
import time

# Semaphore allows only 3 threads at a time
sem = Semaphore(3)


def task(name):
    sem.acquire()

    for i in range(5):
        print(f"{name} working...")
        time.sleep(1)

    sem.release()


# if __name__ == "__main__":
#     threads = []

#     # Create 5 threads with proper names
#     for i in range(1, 6):
#         t = Thread(target=task, args=(f"Thread-{i}",))
#         threads.append(t)

#     # Start all threads
#     for t in threads:
#         t.start()

#     # Wait for all to finish
#     for t in threads:
#         t.join()

if __name__ == "__main__":
    t1 = Thread(target=task,args=('Thread-1',))
    t2 = Thread(target=task,args=('Thread-2',))

    t3 = Thread(target=task,args=('Thread-3',))

    t4 = Thread(target=task,args=('Thread-4',))

    t5 = Thread(target=task,args=('Thread-5',))

    t1.start()

    t2.start()
    t3.start()
    t4.start()
    t5.start()
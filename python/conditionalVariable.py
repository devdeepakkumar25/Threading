from threading import Condition, Thread
import time

cond = Condition()
done = 1


def task(name):
    global done

    with cond:
        if done == 1:
            done = 2
            print(f"{name}: Waiting on condition variable 'cond'")
            cond.wait()
            print(f"{name}: Condition met")
        else:
            for i in range(5):
                print(".", end="", flush=True)
                time.sleep(1)

            print(f"\n{name}: Signaling condition variable 'cond'")
            cond.notify_all()
            print(f"{name}: Notification done")


if __name__ == "__main__":
    t1 = Thread(target=task, args=("t1",))
    t2 = Thread(target=task, args=("t2",))

    t1.start()
    t2.start()

    t1.join()
    t2.join()

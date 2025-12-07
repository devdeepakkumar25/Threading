let myRunnable = async () => {
  let i = 1;
  while (true) {
    console.log(i + " Hello");
    i++;
    await new Promise((resolve) => setTimeout(resolve, 0));
  }
};

myRunnable();

let mainLoop = async () => {
  let i = 0;
  while (true) {
    console.log(i + "      World");
    i++;
    await new Promise((resolve) => setTimeout(resolve, 10));
  }
};

mainLoop();

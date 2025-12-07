// helloWorker.mjs
import { parentPort } from "node:worker_threads";

let i = 1;

async function start() {
  while (true) {
    parentPort.postMessage(`${i} Hello`);
    i++;

    // yield control like Java thread scheduling
    await new Promise((resolve) => setTimeout(resolve, 1000));
    // await new Promise((resolve) => setImmediate(resolve));
  }
}

start();

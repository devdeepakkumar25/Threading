import { parentPort, workerData } from "worker_threads";

let result = 0;
let loops = 1e9 / workerData.thread_count; // adjust for thread count

for (let i = 0; i < loops; i++) {
  result++;
}

parentPort.postMessage(result);

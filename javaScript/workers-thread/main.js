// main.mjs
import { Worker } from "node:worker_threads";

async function main() {
  const worker = new Worker(new URL("./helloWorker.js", import.meta.url));

  // Listen to worker messages
  worker.on("message", (msg) => {
    console.log(msg);
  });

  let i = 0;

  // Main thread infinite loop with 1 second sleep
  while (true) {
    console.log(`${i} World`);
    i++;

    // Sleep for 1 second
    await new Promise((resolve) => setTimeout(resolve, 1000));

    // Yield to event loop (optional — can be removed)
    await new Promise((resolve) => setImmediate(resolve));
  }
}

main();

// // main.mjs
// import { Worker } from "node:worker_threads";

// async function main() {
//   const worker = new Worker(new URL("./helloWorker.js", import.meta.url));

//   // Listen to worker messages
//   worker.on("message", (msg) => {
//     console.log(msg);
//   });

//   let i = 0;

//   // Main thread infinite loop (async for smooth scheduling)
//   while (true) {
//     console.log(`${i} World`);
//     i++;

//     // Yield to event loop to avoid blocking worker
//     await new Promise((resolve) => setImmediate(resolve));
//   }
// }

// main();

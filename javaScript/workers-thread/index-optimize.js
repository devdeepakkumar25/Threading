import express from "express";
import { Worker } from "worker_threads";
import { fileURLToPath } from "url";
import { dirname, join } from "path";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const app = express();
const port = process.env.PORT || 3000;

const THREAD_COUNT = 4;

// =============================
//  FUNCTION TO CREATE WORKER
// =============================
function createWorker() {
  return new Promise((resolve, reject) => {
    const worker = new Worker(join(__dirname, "worker-optimize.js"), {
      workerData: { thread_count: THREAD_COUNT },
      type: "module",
    });

    worker.on("message", (data) => {
      //   resolve(`Result is: ${data}`);
      resolve(data);
    });

    worker.on("error", reject);

    // worker.on("error", (err) => {
    //   reject("Worker error: " + err);

    // });

    // worker.on("exit", (code) => {
    //   if (code !== 0) {
    //     console.error(`Worker exited with code ${code}`);
    //   }
    // });
  });
}

// =============================
//     ROUTES
// =============================
app.get("/non-blocking", (req, res) => {
  res.status(200).send("This page is non-blocking");
});

// A route that actually uses the worker
// app.get("/blocking", async (req, res) => {
//   const workerPromise = [];
//   for (let i = 0; i < THREAD_COUNT; i++) workerPromise.push(createWorker());
//   const threadResult = await Promise.all(workerPromise);

//   const total = threadResult.reduce((acc, curr) => {
//     return acc + curr;
//   }, 0);

//   res.status(200).send(`Result is ${total}`);
//   try {
//     const result = await createWorker();
//     res.status(200).send(result);
//   } catch (err) {
//     res.status(500).send(err);
//   }
// });

app.get("/blocking", async (req, res) => {
  try {
    const workerPromises = [];

    // Create workers equal to THREAD_COUNT
    for (let i = 0; i < THREAD_COUNT; i++) {
      workerPromises.push(createWorker());
    }

    // Wait for all workers to finish
    const threadResults = await Promise.all(workerPromises);

    // Sum numeric results
    const total = threadResults.reduce((acc, curr) => acc + Number(curr), 0);

    res.status(200).send(`Result is: ${total}`);
  } catch (error) {
    res.status(500).send("Error: " + error);
  }
});

// =============================
app.listen(port, () => {
  console.log(`Server running on http://localhost:${port}`);
});

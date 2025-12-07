import express from "express";
import { Worker } from "worker_threads";
import { fileURLToPath } from "url";
import { dirname } from "path";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const app = express();
const port = process.env.PORT || 3000;

app.get("/non-blocking", (req, res) => {
  res.status(200).send("This page is non-blocking");
});

app.get("/blocking", (req, res) => {
  const worker = new Worker(__dirname + "/worker.js", { type: "module" });

  worker.on("message", (data) => {
    res.status(200).send(`Result is: ${data}`);
  });

  worker.on("error", (err) => {
    res.status(500).send("Worker error: " + err);
  });

  worker.on("exit", (code) => {
    if (code !== 0) {
      console.error(`Worker exited with code ${code}`);
    }
  });
});

app.listen(port, () => {
  console.log(`Server running on http://localhost:${port}`);
});

// import express from "express";
// import { Worker, isMainThread, parentPort } from "worker_threads";

// const app = express();

// const port = process.env.PORT || 3000;

// app.get("/non-blocking", (req, res) => {
//   res.status(200).send("This page is non-blocking");
// });

// app.get("/blocking", (req, res) => {
//   const worker = new Worker("./worker.js");
//   // let result = 0;
//   // for (let i = 0; i < 1000000000000000; i++) {
//   //   result++;
//   // }
//   worker.on("message", (data) => {
//     res.status(200).send(`Result is ${data}`);
//   });

//   worker.on("error", (err) => {
//     res.status(400).send(`An Error occured : ${err} `);
//   });
// });

// app.listen(port, () => {
//   console.log(`App listening on port http://localhost:${port}`);
// });

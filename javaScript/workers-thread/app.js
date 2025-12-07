// main.mjs

async function runHello() {
  let i = 1;
  while (true) {
    console.log(`${i} Hello`);
    i++;

    // Yield to event loop so "World" loop gets CPU time
    await new Promise((resolve) => setImmediate(resolve));
  }
}

async function runWorld() {
  let i = 0;
  while (true) {
    console.log(`${i} World`);
    i++;

    // Yield to event loop
    await new Promise((resolve) => setImmediate(resolve));
  }
}

async function main() {
  // Start "Hello" task (like starting a thread)
  runHello(); // not awaited → runs concurrently

  // Run "World" task in main thread
  await runWorld();
}

main();

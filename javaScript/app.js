class MyData {
  constructor() {
    this.value = null;
    this.flag = true; // true = empty (producer turn), false = full (consumer turn)
    this.resolveGet = null;
    this.resolveSet = null;
  }

  async set(v) {
    while (!this.flag) {
      await new Promise((resolve) => (this.resolveSet = resolve));
    }

    this.value = v;
    this.flag = false;

    if (this.resolveGet) {
      this.resolveGet();
      this.resolveGet = null;
    }
  }

  async get() {
    while (this.flag) {
      await new Promise((resolve) => (this.resolveGet = resolve));
    }

    let x = this.value;
    this.flag = true;

    if (this.resolveSet) {
      this.resolveSet();
      this.resolveSet = null;
    }

    return x;
  }
}

// Producer
async function producer(data) {
  let count = 1;
  while (true) {
    await data.set(count);
    console.log("Producer", count);
    count++;
  }
}

// Consumer
async function consumer(data) {
  while (true) {
    let value = await data.get();
    console.log("Consumer", value);
  }
}

// Main
const data = new MyData();
producer(data);
consumer(data);

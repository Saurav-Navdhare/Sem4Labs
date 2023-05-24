<h1>Preemptive Priority CPU Scheduling Backend</h1>

<a href="https://nodejs.org"><img height=48 src="https://raw.githubusercontent.com/caiogondim/javascript-server-side-logos/master/node.js/standard/454x128.png"></a><br>
--- | ---

This is a Node.js project for implementing preemptive priority CPU scheduling algorithm. The preemptive priority CPU scheduling algorithm is a variation of priority scheduling where the highest priority task is executed first, and if a new task with a higher priority arrives, the CPU is preempted and the higher priority task is executed.
<h2>Requirements</h2>

    Node.js v14 or later
    npm v6 or later

<h2>Installation</h2>

    Clone the repository: git clone https://github.com/Aum3010/OS-Project/tree/Backend
    Install dependencies: npm install
    Add your Mongoose Credentials in .env file: MONGO_URI
    Start the server: npm start

<h2>Usage</h2>

The server runs on port 3000 by default. You can change this by setting the PORT environment variable.

To schedule a list of processes, send a POST request to the /schedule endpoint with a JSON payload in the following format:
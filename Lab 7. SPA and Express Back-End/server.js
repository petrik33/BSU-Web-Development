const express = require('express');
const path = require('path');
const app = express();
const port = 3000;

app.use(express.static(`${__dirname}/src`));

app.get('/', (req, res) => {
  res.sendFile(`${__dirname}/src/index.html`);
});

app.get('/data', (req, res) => {
  res.download(`${__dirname}/src/data/visitors.json`);
});

app.listen(port, () => {
  console.log(`App running on http://localhost:${port}`);
});

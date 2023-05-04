const express = require('express');
const app = express();
const port = 3000;

app.use(express.static('public'));

app.get('/', (req, res) => {
  res.sendFile(`${__dirname}/public/index.html`);
});

app.get('/data', (req, res) => {
  res.download(`${__dirname}/data/visitors.json`);
});

app.listen(port, () => {
  console.log(`App running on http://localhost:${port}`);
});

const port = 3000;

const express = require('express');
const app = express();

app.use((req, res, next) => {
  if (req.url.endsWith('.css')) {
    res.setHeader('Content-Type', 'text/css');
  }
  next();
});

app.use(express.static('dist'));

app.get('/', (req, res) => {
  res.sendFile(`${__dirname}/dist/index.html`);
});

app.get('/data', (req, res) => {
  res.download(`${__dirname}/data/data.json`);
});

app.listen(port, () => {
  console.log(`App running on http://localhost:${port}`);
});

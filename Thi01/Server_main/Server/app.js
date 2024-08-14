const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const sanPhamRoutes = require('./routes/SanPhamRoutes'); // Update the route file name

const app = express();
const PORT = process.env.PORT || 3000;

mongoose.connect('mongodb://localhost:27017/xeMayDB', {
    useNewUrlParser: true,
    useUnifiedTopology: true
});

const db = mongoose.connection;
db.on('error', (error) => console.error(error));
db.once('open', () => console.log('Connected to Database'));

app.use(bodyParser.json());
app.use('/api/sanphams', sanPhamRoutes); // Update the route path

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});

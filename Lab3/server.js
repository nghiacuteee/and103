// const express = require('express');

// const app = express();

// const port = 3000;

// const bodyParser = require("body-parser");
// app.use(bodyParser.json());
// app.use(bodyParser.urlencoded({ extended: true }));

// app.listen(port, () => {
//     console.log(`Example app listening on port ${port}`)
// });

// const COMMON = require('./COMMON');

// const uri = COMMON.uri;

// const mongoose = require('mongoose');

// const carModel = require('./carModel');

// const apiMobile = require('./api');
// app.use('/api', apiMobile);

// app.get('/', async (req, res) => {
//     await mongoose.connect(uri);

//     let cars = await carModel.find();

//     console.log(cars);

//     res.send(cars);

// })

// app.post('/add_xe', async (req, res) => {
//     await mongoose.connect(uri);

//     // let car = {
//     //     ten: 'xe 3',
//     //     namSX: 2024,
//     //     hang: 'Mitsubishi',
//     //     gia: 7500
//     // }

//     let car = req.body;

//     console.log(car)

//     let kq = await carModel.create(car);
//     console.log(kq);

//     let cars = await carModel.find();

//     res.send(cars);

// })

// app.get('/xoa/:id', async (req, res) => {
//     await mongoose.connect(uri);

//     let id = req.params.id;
//     console.log(id);

//     // xu ly loi khi id khong dung
//     await carModel.deleteOne({ _id: id });

//     res.redirect('../')
// })

// app.get('/update/:ten', async (req, res) => {
//     await mongoose.connect(uri);

//     console.log('Ket noi DB thanh cong');

//     let tenXe = req.params.ten;
//     console.log(tenXe);

//     let tenXeMoi = tenXe + ' Phien ban moi 2024';

//     await carModel.updateOne({ ten: tenXe }, { ten: tenXeMoi });

//     let xehois = await carModel.find({});

//     res.send(xehois);
// })


const express = require('express');
const app = express();
const port = 3000;
const bodyParser = require("body-parser");
const COMMON = require('./COMMON');
const mongoose = require('mongoose');
const carModel = require('./carModel');
const apiMobile = require('./api');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`);
});

app.use('/api', apiMobile);

app.get('/', async (req, res) => {
    try {
        await mongoose.connect(COMMON.uri);
        let cars = await carModel.find();
        console.log(cars);
        res.send(cars);
    } catch (error) {
        console.error(error);
        res.status(500).send('Error retrieving cars');
    }
});

app.post('/api/add_xe', async (req, res) => {
    try {
        await mongoose.connect(COMMON.uri);
        let car = req.body;
        console.log(car);
        let kq = await carModel.create(car);
        console.log(kq);
        let cars = await carModel.find();
        res.status(200).send(cars); // Trả về dữ liệu thành công
    } catch (error) {
        console.error(error);
        res.status(500).send('Error adding car'); // Trả về lỗi
    }
});

app.delete('/api/xoa/:id', async (req, res) => {
    try {
        await mongoose.connect(COMMON.uri);
        let id = req.params.id;
        console.log(id);
        await carModel.deleteOne({ _id: id });
        res.send({ success: true });
    } catch (error) {
        console.error(error);
        res.status(500).send('Error deleting car');
    }
});

app.put('/api/update/:id', async (req, res) => {
    try {
        await mongoose.connect(COMMON.uri);
        let id = req.params.id;
        let updateData = req.body;
        console.log(updateData);
        await carModel.updateOne({ _id: id }, updateData);
        let xehois = await carModel.find({});
        res.status(200).send(xehois); // Trả về dữ liệu thành công
    } catch (error) {
        console.error(error);
        res.status(500).send('Error updating car'); // Trả về lỗi
    }
});
